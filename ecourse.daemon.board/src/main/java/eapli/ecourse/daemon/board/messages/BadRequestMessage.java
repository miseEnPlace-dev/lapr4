package eapli.ecourse.daemon.board.messages;

import java.io.DataOutputStream;
import java.io.IOException;

import javax.net.ssl.SSLSocket;

import eapli.ecourse.common.board.protocol.MessageCode;
import eapli.ecourse.common.board.protocol.ProtocolMessage;

public class BadRequestMessage extends Message {
  public BadRequestMessage(DataOutputStream output, SSLSocket socket) {
    super(null, output, socket);
  }

  public BadRequestMessage(ProtocolMessage protocolMessage, DataOutputStream output,
      SSLSocket socket) {
    super(protocolMessage, output, socket);
  }

  @Override
  public void handle() throws IOException {
    super.send(new ProtocolMessage(MessageCode.ERR, "Bad Request"));
  }
}
