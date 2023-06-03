package eapli.ecourse.daemon.board.messages;

import java.io.DataOutputStream;
import eapli.ecourse.common.board.protocol.ProtocolMessage;

public class AckMessage extends Message {
  public AckMessage(ProtocolMessage protocolMessage, DataOutputStream output) {
    super(protocolMessage, output);
  }

  @Override
  public void handle() {
    throw new UnsupportedOperationException("Unimplemented method 'handle'");
  }
}
