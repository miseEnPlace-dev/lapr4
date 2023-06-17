package eapli.ecourse.daemon.board.messages;

import java.io.DataOutputStream;
import java.net.Socket;

import eapli.ecourse.common.board.SafeBoardUpdatesCounter;
import eapli.ecourse.common.board.SafeOnlineCounter;
import eapli.ecourse.common.board.protocol.ProtocolMessage;

public class AckMessage extends Message {
  public AckMessage(ProtocolMessage protocolMessage, DataOutputStream output, Socket socket,
      SafeOnlineCounter onlineCounter, SafeBoardUpdatesCounter boardUpdatesCounter) {
    super(protocolMessage, output, socket, onlineCounter, boardUpdatesCounter);
  }

  @Override
  public void handle() {
    // ignore for now
  }
}
