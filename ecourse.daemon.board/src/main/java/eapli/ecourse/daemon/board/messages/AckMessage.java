package eapli.ecourse.daemon.board.messages;

import java.io.DataOutputStream;

import javax.net.ssl.SSLSocket;

import eapli.ecourse.common.board.protocol.ProtocolMessage;

public class AckMessage extends Message {
  public AckMessage(ProtocolMessage protocolMessage, DataOutputStream output, SSLSocket socket) {
    super(protocolMessage, output, socket);
  }

  @Override
  public void handle() {
    // ignore for now
  }
}
