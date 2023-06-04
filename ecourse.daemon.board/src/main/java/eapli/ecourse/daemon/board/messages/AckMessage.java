package eapli.ecourse.daemon.board.messages;

import java.io.DataOutputStream;
import java.net.Socket;
import eapli.ecourse.common.board.protocol.ProtocolMessage;

public class AckMessage extends Message {
  public AckMessage(ProtocolMessage protocolMessage, DataOutputStream output, Socket socket) {
    super(protocolMessage, output, socket);
  }

  @Override
  public void handle() {
    // ignore for now
  }
}
