package eapli.ecourse.daemon.board.messages;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import javax.json.JsonObject;

import eapli.ecourse.boardmanagement.domain.BoardID;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.common.board.SafeOnlineCounter;
import eapli.ecourse.common.board.protocol.MessageCode;
import eapli.ecourse.common.board.protocol.ProtocolMessage;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.postitmanagement.application.CreatePostItController;
import eapli.ecourse.postitmanagement.application.ImageEncoderService;
import eapli.ecourse.postitmanagement.repositories.PostItRepository;

public class IsCellAvailableMessage extends Message {

  private CreatePostItController ctrl;

  private BoardRepository boardRepository;
  private PostItRepository postItRepository;

  public IsCellAvailableMessage(ProtocolMessage protocolMessage, DataOutputStream output, Socket socket,
      SafeOnlineCounter onlineCounter) {
    super(protocolMessage, output, socket, onlineCounter);

    this.boardRepository = PersistenceContext.repositories().boards();
    this.postItRepository = PersistenceContext.repositories().postIts();
    this.ctrl = new CreatePostItController(boardRepository, postItRepository, new ImageEncoderService());
  }

  @Override
  public void handle() throws IOException {

    JsonObject json = protocolMessage.getPayloadAsJson();

    BoardID boardId = BoardID.valueOf(json.getString("boardId"));
    Integer x = json.getInt("x");
    Integer y = json.getInt("y");

    if (boardId == null || x == null || y == null) {
      send(new ProtocolMessage(MessageCode.ERR, "Bad Request"));
      return;
    }

    boolean isCellAvailable = ctrl.validateCoordinates(boardId, x, y);

    send(new ProtocolMessage(MessageCode.IS_CELL_AVAILABLE, isCellAvailable));
  }
}
