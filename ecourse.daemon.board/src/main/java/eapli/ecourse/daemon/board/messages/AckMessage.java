package eapli.ecourse.daemon.board.messages;

import java.io.DataOutputStream;
import java.net.Socket;
import eapli.ecourse.common.board.SafeOnlineCounter;
import eapli.ecourse.common.board.protocol.ProtocolMessage;

public class AckMessage extends Message {
  public AckMessage(ProtocolMessage protocolMessage, DataOutputStream output, Socket socket,
      SafeOnlineCounter onlineCounter) {
    super(protocolMessage, output, socket, onlineCounter);
  }

  @Override
  public void handle() {
    // ignore for now
  }
}
