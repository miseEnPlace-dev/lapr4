package eapli.ecourse.app.board.backend.messages;

import java.io.DataOutputStream;
import eapli.ecourse.app.board.common.protocol.ProtocolMessage;

public class AckMessage extends Message {
  public AckMessage(ProtocolMessage protocolMessage, DataOutputStream output) {
    super(protocolMessage, output);
  }

  @Override
  public void handle() {
    throw new UnsupportedOperationException("Unimplemented method 'handle'");
  }
}
