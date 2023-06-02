package eapli.ecourse.app.board.backend.messages;

import java.io.DataOutputStream;
import java.io.IOException;
import eapli.ecourse.app.board.common.protocol.ProtocolMessage;

public abstract class Message {
  protected ProtocolMessage protocolMessage;
  private DataOutputStream output;

  public Message(ProtocolMessage message, DataOutputStream output) {
    this.protocolMessage = message;
    this.output = output;
  }

  public abstract void handle() throws IOException;

  public void send(ProtocolMessage response) throws IOException {
    output.write(response.toByteStream());
  }
}
