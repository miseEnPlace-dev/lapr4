package eapli.ecourse.daemon.board.messages;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.json.JsonStructure;
import javax.json.JsonValue.ValueType;
import eapli.ecourse.boardmanagement.domain.BoardID;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.common.board.SafeOnlineCounter;
import eapli.ecourse.common.board.protocol.MessageCode;
import eapli.ecourse.common.board.protocol.ProtocolMessage;
import eapli.ecourse.daemon.board.clientstate.ClientState;
import eapli.ecourse.daemon.board.clientstate.CredentialStore;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.postitmanagement.application.CreatePostItController;
import eapli.ecourse.postitmanagement.application.ImageEncoderService;
import eapli.ecourse.postitmanagement.repositories.PostItRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;

public class IsCellAvailableMessage extends Message {

  private CreatePostItController ctrl;

  private BoardRepository boardRepository;
  private PostItRepository postItRepository;
  private CredentialStore credentialStore;

  public IsCellAvailableMessage(ProtocolMessage protocolMessage, DataOutputStream output,
      Socket socket, SafeOnlineCounter onlineCounter) {
    super(protocolMessage, output, socket, onlineCounter);

    this.boardRepository = PersistenceContext.repositories().boards();
    this.postItRepository = PersistenceContext.repositories().postIts();
    this.ctrl =
        new CreatePostItController(boardRepository, postItRepository, new ImageEncoderService());
    this.credentialStore = ClientState.getInstance().getCredentialStore();
  }

  @Override
  public void handle() throws IOException {
    // ignore requests from unauthenticated clients
    if (!credentialStore.isAuthenticated())
      return;

    JsonStructure json = request.getPayloadAsJson();

    // check if json is valid
    if (json == null || !json.getValueType().equals(ValueType.OBJECT)) {
      send(new ProtocolMessage(MessageCode.ERR, "Bad Request"));
      return;
    }

    BoardID boardId = BoardID.valueOf(json.asJsonObject().getString("boardId"));
    Integer x = json.asJsonObject().getInt("x");
    Integer y = json.asJsonObject().getInt("y");

    if (boardId == null || x == null || y == null) {
      send(new ProtocolMessage(MessageCode.ERR, "Bad Request"));
      return;
    }

    Username authUsername = Username.valueOf(credentialStore.getUser().getUsername());

    // do not allow users who don't have access to the board
    if (!ctrl.isBoardParticipant(boardId, authUsername)) {
      send(new ProtocolMessage(MessageCode.ERR, "Unauthorized"));
      return;
    }

    boolean isCellAvailable = ctrl.validateCoordinates(boardId, x, y);

    send(new ProtocolMessage(MessageCode.IS_CELL_AVAILABLE, isCellAvailable));
  }
}
