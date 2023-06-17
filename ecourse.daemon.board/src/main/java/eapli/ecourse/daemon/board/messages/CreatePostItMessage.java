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

    String title = payload.asJsonObject().getString("title");
    String description = payload.asJsonObject().getString("description");
    String imagePath = payload.asJsonObject().getString("imagePath");
    Integer x = payload.asJsonObject().getInt("x");
    Integer y = payload.asJsonObject().getInt("y");

    if (title == null || x == null || y == null || description == null || imagePath == null) {
      send(new ProtocolMessage(MessageCode.ERR, "Bad Request"));
      return;
    }

    if (description.isEmpty())
      description = null;

    if (imagePath.isEmpty())
      imagePath = null;

    SystemUser owner = userService.userOfIdentity(username).orElseThrow();

    PostIt postIt = ctrl.createPostIt(board.identity(), x, y, title, description, imagePath, owner);

    if (postIt == null) {
      send(new ProtocolMessage(MessageCode.ERR, "Post-it could not be created"));
      return;
    }

    this.boardUpdatesCounter.incrementNumberPostIts();

    send(new ProtocolMessage(MessageCode.CREATE_POSTIT));
  }
}
