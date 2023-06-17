package eapli.ecourse.daemon.board.messages;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Optional;

import javax.json.JsonObject;

import eapli.ecourse.boardmanagement.domain.Board;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.common.board.SafeBoardUpdatesCounter;
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
import eapli.framework.infrastructure.authz.domain.model.Username;

public class ChangePostItMessage extends Message {
  private CredentialStore credentialStore;

  private ChangePostItController ctrl;

  private BoardRepository boardRepository;
  private PostItRepository postItRepository;

  public ChangePostItMessage(ProtocolMessage protocolMessage, DataOutputStream output, Socket socket,
      SafeOnlineCounter onlineCounter, SafeBoardUpdatesCounter boardUpdatesCounter) {
    super(protocolMessage, output, socket, onlineCounter, boardUpdatesCounter);

    this.credentialStore = ClientState.getInstance().getCredentialStore();

    this.boardRepository = PersistenceContext.repositories().boards();
    this.postItRepository = PersistenceContext.repositories().postIts();
    this.ctrl = new ChangePostItController(boardRepository, postItRepository);
  }

  @Override
  public void handle() throws IOException {
    // ignore requests from unauthenticated clients
    if (!credentialStore.isAuthenticated())
      return;

    JsonObject payload = protocolMessage.getPayloadAsJson();

    String postItId = payload.getString("postItId");

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

    String title = payload.keySet().contains("title") ? payload.getString("title") : null;
    String description = payload.keySet().contains("description") ? payload.getString("description") : null;
    String imagePath = payload.keySet().contains("imagePath") ? payload.getString("imagePath") : null;
    Integer x = payload.keySet().contains("x") ? payload.getInt("x") : null;
    Integer y = payload.keySet().contains("y") ? payload.getInt("y") : null;

    if (title == null && x == null && y == null && description == null && imagePath == null) {
      send(new ProtocolMessage(MessageCode.ERR, "Bad Request"));
      return;
    }

    if (!username.equals(p.get().owner().identity())) {
      send(new ProtocolMessage(MessageCode.ERR, "Unauthorized"));
      return;
    }

    PostIt postIt = ctrl.changePostIt(p.get().identity(), title, x, y, description, imagePath);

    if (postIt == null) {
      send(new ProtocolMessage(MessageCode.ERR, "Post-it could not be created"));
      return;
    }

    this.boardUpdatesCounter.increment();

    send(new ProtocolMessage(MessageCode.CHANGE_POSTIT));

  }
}
