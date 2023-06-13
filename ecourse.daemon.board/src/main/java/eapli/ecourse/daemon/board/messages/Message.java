package eapli.ecourse.daemon.board.messages;

import java.io.DataOutputStream;
import java.io.IOException;

import javax.net.ssl.SSLSocket;

import eapli.ecourse.common.board.protocol.ProtocolMessage;

public abstract class Message {
  protected ProtocolMessage protocolMessage;
  private DataOutputStream output;
  private SSLSocket socket;

  public Message(ProtocolMessage message, DataOutputStream output, SSLSocket socket) {
    this.protocolMessage = message;
    this.output = output;
  }

  public abstract void handle() throws IOException;

  public void send(ProtocolMessage response) throws IOException {
    output.write(response.toByteStream());
  }

  public void close() throws IOException {
    output.close();
    socket.close();
  }
}
