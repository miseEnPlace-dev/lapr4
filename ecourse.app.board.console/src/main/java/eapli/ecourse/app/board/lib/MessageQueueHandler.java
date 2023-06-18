package eapli.ecourse.app.board.lib;

import java.io.IOException;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eapli.ecourse.common.board.TcpClient;
import eapli.ecourse.common.board.protocol.ProtocolMessage;
import eapli.ecourse.common.board.protocol.UnsupportedVersionException;

public class MessageQueueHandler implements Runnable {
  private static Logger logger = LogManager.getLogger(MessageQueueHandler.class);

  private List<ProtocolMessage> messageQueue;
  private TcpClient tcpClient;

  public MessageQueueHandler(List<ProtocolMessage> messageQueue, TcpClient tcpClient) {
    this.messageQueue = messageQueue;
    this.tcpClient = tcpClient;
  }

  public void run() {
    while (true) {
      try {
        ProtocolMessage incoming = tcpClient.receive();
        messageQueue.add(incoming);

        // notify all threads waiting for a message
        synchronized (messageQueue) {
          messageQueue.notifyAll();
        }
      } catch (IOException | ClassNotFoundException | UnsupportedVersionException e) {
        logger.error("Error receiving message from server", e);
      }
    }
  }

  public List<ProtocolMessage> getMessageQueue() {
    // TODO
    synchronized (messageQueue) {
      return messageQueue;
    }
  }
}
