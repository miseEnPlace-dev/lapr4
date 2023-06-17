package eapli.ecourse.daemon.board.messages;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Optional;

import javax.json.JsonStructure;
import javax.json.JsonValue.ValueType;
import eapli.ecourse.boardmanagement.domain.Board;
import eapli.ecourse.boardmanagement.domain.BoardID;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.common.board.SafeBoardUpdatesCounter;
import eapli.ecourse.common.board.SafeOnlineCounter;
import eapli.ecourse.common.board.protocol.MessageCode;
import eapli.ecourse.common.board.protocol.ProtocolMessage;
import eapli.ecourse.daemon.board.clientstate.ClientState;
import eapli.ecourse.daemon.board.clientstate.CredentialStore;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.postitmanagement.application.CreatePostItController;
import eapli.ecourse.postitmanagement.application.ImageEncoderService;
import eapli.ecourse.postitmanagement.domain.PostIt;
import eapli.ecourse.postitmanagement.repositories.PostItRepository;
import eapli.ecourse.usermanagement.dto.UserDTO;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;

public class CreatePostItMessage extends Message {
  private CredentialStore credentialStore;

  private CreatePostItController ctrl;

  private BoardRepository boardRepository;
  private PostItRepository postItRepository;
  private UserManagementService userService;

  public CreatePostItMessage(ProtocolMessage protocolMessage, DataOutputStream output,
      Socket socket, SafeOnlineCounter onlineCounter, SafeBoardUpdatesCounter boardUpdatesCounter) {
    super(protocolMessage, output, socket, onlineCounter, boardUpdatesCounter);

    this.credentialStore = ClientState.getInstance().getCredentialStore();

    this.boardRepository = PersistenceContext.repositories().boards();
    this.postItRepository = PersistenceContext.repositories().postIts();
    this.ctrl = new CreatePostItController(boardRepository, postItRepository, new ImageEncoderService());
    this.userService = AuthzRegistry.userService();
  }

  @Override
  public void handle() throws IOException {
    // ignore requests from unauthenticated clients
    if (!credentialStore.isAuthenticated())
      return;

    JsonStructure payload = request.getPayloadAsJson();

    // check if json is valid
    if (payload == null || !payload.getValueType().equals(ValueType.OBJECT)) {
      send(new ProtocolMessage(MessageCode.ERR, "Bad Request"));
      return;
    }

    String boardId = payload.asJsonObject().getString("boardId");

    if (boardId == null) {
      send(new ProtocolMessage(MessageCode.ERR, "Bad Request"));
      return;
    }

    Optional<Board> b = boardRepository.ofIdentity(BoardID.valueOf(boardId));

    if (b.isEmpty()) {
      send(new ProtocolMessage(MessageCode.ERR, "Board not found"));
      return;
    }

    Board board = b.get();

    UserDTO user = credentialStore.getUser();
    Username username = Username.valueOf(user.getUsername());

    if (!board.canWrite(username)) {
      send(new ProtocolMessage(MessageCode.ERR, "Unauthorized"));
      return;
    }

    String title;
    String description = null;
    String image = null;
    Integer x;
    Integer y;

    try {
      title = payload.asJsonObject().getString("title");
      x = payload.asJsonObject().getInt("x");
      y = payload.asJsonObject().getInt("y");

      if (title == null || x == null || y == null) {
        send(new ProtocolMessage(MessageCode.ERR, "Bad Request"));
        return;
      }

    } catch (NullPointerException e) {
      send(new ProtocolMessage(MessageCode.ERR, "Bad Request"));
      return;
    }

    if (payload.asJsonObject().containsKey("description"))
      description = payload.asJsonObject().getString("description");

    if (payload.asJsonObject().containsKey("image"))
      image = payload.asJsonObject().getString("image");

    if (description != null && description.isEmpty())
      description = null;

    if (image != null && image.isEmpty())
      image = null;

    SystemUser owner = userService.userOfIdentity(username).orElseThrow();

    // ? we should also check if the board is archived
    if (ctrl.isBoardArchived(b.get().identity())) {
      send(new ProtocolMessage(MessageCode.ERR, "Board is archived"));
      return;
    }

    PostIt postIt = ctrl.createPostIt(board.identity(), x, y, title, description, image, owner);

    if (postIt == null) {
      send(new ProtocolMessage(MessageCode.ERR, "Post-it could not be created"));
      return;
    }

    this.boardUpdatesCounter.incrementNumberPostIts();

    send(new ProtocolMessage(MessageCode.CREATE_POSTIT));
  }
}
