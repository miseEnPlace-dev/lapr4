package eapli.ecourse.daemon.board.messages;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Optional;

import javax.json.JsonStructure;
import javax.json.JsonValue.ValueType;
import eapli.ecourse.boardmanagement.domain.Board;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.common.board.SafeOnlineCounter;
import eapli.ecourse.common.board.protocol.MessageCode;
import eapli.ecourse.common.board.protocol.ProtocolMessage;
import eapli.ecourse.daemon.board.clientstate.ClientState;
import eapli.ecourse.daemon.board.clientstate.CredentialStore;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.postitmanagement.application.ChangePostItController;
import eapli.ecourse.postitmanagement.domain.PostIt;
import eapli.ecourse.postitmanagement.domain.PostItID;
import eapli.ecourse.postitmanagement.repositories.PostItRepository;
import eapli.ecourse.usermanagement.dto.UserDTO;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.Username;

public class ChangePostItMessage extends Message {
  private CredentialStore credentialStore;

  private TransactionalContext ctx;
  private ChangePostItController ctrl;

  private BoardRepository boardRepository;
  private PostItRepository postItRepository;

  public ChangePostItMessage(ProtocolMessage protocolMessage, DataOutputStream output,
      Socket socket, SafeOnlineCounter onlineCounter) {
    super(protocolMessage, output, socket, onlineCounter);

    this.credentialStore = ClientState.getInstance().getCredentialStore();

    this.ctx = PersistenceContext.repositories().newTransactionalContext();

    this.boardRepository = PersistenceContext.repositories().boards();
    this.postItRepository = PersistenceContext.repositories().postIts();
    this.ctrl = new ChangePostItController(ctx, boardRepository, postItRepository);
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

    String postItId = payload.asJsonObject().getString("postItId");

    if (postItId == null) {
      send(new ProtocolMessage(MessageCode.ERR, "Bad Request"));
      return;
    }

    Optional<PostIt> p = postItRepository.ofIdentity(PostItID.valueOf(postItId));

    if (!p.isPresent()) {
      send(new ProtocolMessage(MessageCode.ERR, "Post-it not found"));
      return;
    }

    Board board = p.get().board();

    UserDTO user = credentialStore.getUser();
    Username username = Username.valueOf(user.getUsername());

    if (!board.canWrite(username)) {
      send(new ProtocolMessage(MessageCode.ERR, "Unauthorized"));
      return;
    }

    String title = payload.asJsonObject().keySet().contains("title")
        ? payload.asJsonObject().getString("title")
        : null;
    String description = payload.asJsonObject().keySet().contains("description")
        ? payload.asJsonObject().getString("description")
        : null;
    String imagePath = payload.asJsonObject().keySet().contains("imagePath")
        ? payload.asJsonObject().getString("imagePath")
        : null;
    Integer x =
        payload.asJsonObject().keySet().contains("x") ? payload.asJsonObject().getInt("x") : null;
    Integer y =
        payload.asJsonObject().keySet().contains("y") ? payload.asJsonObject().getInt("y") : null;

    if (title == null && x == null && y == null && description == null && imagePath == null) {
      send(new ProtocolMessage(MessageCode.ERR, "Bad Request"));
      return;
    }

    if (!username.equals(p.get().owner().identity())) {
      send(new ProtocolMessage(MessageCode.ERR, "Unauthorized"));
      return;
    }

    // ? we should also check if the board is archived
    if (ctrl.isPostItBoardArchived(p.get().identity())) {
      send(new ProtocolMessage(MessageCode.ERR, "Board is archived"));
      return;
    }

    PostIt postIt = ctrl.changePostIt(p.get().identity(), title, x, y, description, imagePath);

    if (postIt == null) {
      send(new ProtocolMessage(MessageCode.ERR, "Post-it could not be created"));
      return;
    }

    send(new ProtocolMessage(MessageCode.CHANGE_POSTIT));

  }
}
