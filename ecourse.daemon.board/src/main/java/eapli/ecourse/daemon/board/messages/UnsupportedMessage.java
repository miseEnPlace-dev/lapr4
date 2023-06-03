package eapli.ecourse.daemon.board.messages;

import java.io.DataOutputStream;
import java.io.IOException;
import eapli.ecourse.common.board.protocol.MessageCode;
import eapli.ecourse.common.board.protocol.ProtocolMessage;

public class UnsupportedMessage extends Message {
  public UnsupportedMessage(DataOutputStream output) {
    super(null, output);
  }

  public UnsupportedMessage(ProtocolMessage protocolMessage, DataOutputStream output) {
    super(protocolMessage, output);
  }

  @Override
  public void handle() throws IOException {
    super.send(new ProtocolMessage(MessageCode.ERR, "Unsupported message format"));
  }
}
