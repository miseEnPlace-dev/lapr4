package eapli.ecourse.daemon.board.messages;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import eapli.ecourse.common.board.SafeOnlineCounter;
import eapli.ecourse.common.board.protocol.ProtocolMessage;

public abstract class Message {
  protected ProtocolMessage request;
  protected SafeOnlineCounter onlineCounter;
  private DataOutputStream output;
  private Socket socket;

  public Message(ProtocolMessage message, DataOutputStream output, Socket socket,
      SafeOnlineCounter onlineCounter) {
    this.request = message;
    this.output = output;
    this.socket = socket;
    this.onlineCounter = onlineCounter;
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
