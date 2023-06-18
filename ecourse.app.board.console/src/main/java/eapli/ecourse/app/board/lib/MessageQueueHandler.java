package eapli.ecourse.app.board.lib;

import java.io.IOException;
import java.util.List;
import eapli.ecourse.common.board.TcpClient;
import eapli.ecourse.common.board.protocol.ProtocolMessage;

public class MessageQueueHandler implements Runnable {
  private List<ProtocolMessage> messageQueue;
  private TcpClient tcpClient;

  public MessageQueueHandler(List<ProtocolMessage> messageQueue, TcpClient tcpClient) {
    this.messageQueue = messageQueue;
    this.tcpClient = tcpClient;
  }

  public void run() {
    while (true) {
      try {
        ProtocolMessage message = messageQueue.remove(0);
        tcpClient.send(message);
      } catch (IOException e) {
        System.out.println("Error sending message");
        e.printStackTrace();
      }
    }
  }
}
