package eapli.ecourse.daemon.board.messages;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import eapli.ecourse.common.board.protocol.MessageCode;
import eapli.ecourse.common.board.protocol.ProtocolMessage;

public class BadRequestMessage extends Message {
  public BadRequestMessage(DataOutputStream output, Socket socket) {
    super(null, output, socket);
  }

  public BadRequestMessage(ProtocolMessage protocolMessage, DataOutputStream output,
      Socket socket) {
    super(protocolMessage, output, socket);
  }

  @Override
  public void handle() throws IOException {
    super.send(new ProtocolMessage(MessageCode.ERR, "Bad Request"));
  }
}
