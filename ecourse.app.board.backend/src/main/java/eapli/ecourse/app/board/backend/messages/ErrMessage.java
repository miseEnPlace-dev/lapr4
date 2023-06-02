package eapli.ecourse.app.board.backend.messages;

import java.io.DataOutputStream;
import java.io.IOException;
import eapli.ecourse.app.board.common.protocol.MessageCode;
import eapli.ecourse.app.board.common.protocol.ProtocolMessage;

public class ErrMessage extends Message {
  public ErrMessage(ProtocolMessage protocolMessage, DataOutputStream output) {
    super(protocolMessage, output);
  }

  @Override
  public void handle() throws IOException {
    send(new ProtocolMessage(MessageCode.ERR));
  }
}
