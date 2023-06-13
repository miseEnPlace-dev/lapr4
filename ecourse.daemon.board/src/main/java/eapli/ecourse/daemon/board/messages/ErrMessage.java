package eapli.ecourse.daemon.board.messages;

import java.io.DataOutputStream;
import java.io.IOException;

import javax.net.ssl.SSLSocket;

import eapli.ecourse.common.board.protocol.ProtocolMessage;

public class ErrMessage extends Message {
  public ErrMessage(ProtocolMessage protocolMessage, DataOutputStream output, SSLSocket socket) {
    super(protocolMessage, output, socket);
  }

  @Override
  public void handle() throws IOException {
    // ignore for now
  }
}
