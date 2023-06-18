package eapli.ecourse.daemon.board.messages;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import eapli.ecourse.common.board.EventListener;
import eapli.ecourse.common.board.SafeBoardUpdatesCounter;
import eapli.ecourse.common.board.SafeOnlineCounter;
import eapli.ecourse.common.board.protocol.ProtocolMessage;

public abstract class Message {
  protected ProtocolMessage request;
  protected SafeOnlineCounter onlineCounter;
  protected SafeBoardUpdatesCounter boardUpdatesCounter;
  protected EventListener eventListener;
  protected Socket socket;
  private DataOutputStream output;

  public Message(ProtocolMessage message, DataOutputStream output, Socket socket,
      SafeOnlineCounter onlineCounter, SafeBoardUpdatesCounter boardUpdatesCounter,
      EventListener eventListener) {
    this.request = message;
    this.output = output;
    this.socket = socket;
    this.onlineCounter = onlineCounter;
    this.boardUpdatesCounter = boardUpdatesCounter;
    this.eventListener = eventListener;
  }

  public abstract void handle() throws IOException;

  public void send(ProtocolMessage response) throws IOException {
    synchronized (output) {
      output.write(response.toByteStream());
    }
  }

  public void close() throws IOException {
    output.close();
    socket.close();
  }
}
