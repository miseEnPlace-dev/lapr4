package eapli.ecourse.app.board.backend.messages;

import java.io.DataOutputStream;
import java.io.IOException;
import eapli.ecourse.app.board.common.protocol.MessageCode;
import eapli.ecourse.app.board.common.protocol.ProtocolMessage;

public class CommTestMessage extends Message {
  public CommTestMessage(ProtocolMessage protocolMessage, DataOutputStream output) {
    super(protocolMessage, output);
  }

  @Override
  public void handle() throws IOException {
    System.out.println("Comm test! Sending ACK");
    send(new ProtocolMessage(MessageCode.ACK));
  }
}
