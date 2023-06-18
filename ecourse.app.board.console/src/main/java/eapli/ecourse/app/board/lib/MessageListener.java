package eapli.ecourse.app.board.lib;

import java.io.IOException;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eapli.ecourse.common.board.TcpClient;
import eapli.ecourse.common.board.protocol.MessageCode;
import eapli.ecourse.common.board.protocol.ProtocolMessage;
import eapli.ecourse.common.board.protocol.UnsupportedVersionException;

public class MessageListener implements Runnable {
  private static Logger logger = LogManager.getLogger(MessageListener.class);

  private List<ProtocolMessage> queue;
  private Map<MessageCode, MessageHandler> events;

  private TcpClient tcpClient;

  public MessageListener(TcpClient tcpClient) {
    this.queue = new LinkedList<>();
    this.events = new HashMap<>();
    this.tcpClient = tcpClient;
  }

  public void run() {
    while (true) {
      try {
        // ProtocolMessage incoming;
        // synchronized (tcpClient) {
        ProtocolMessage incoming = tcpClient.receive();
        // }

        // get event handler
        MessageHandler handler = events.get(incoming.getCode());

        // if there is a handler, call it
        if (handler != null) {
          handler.handle(incoming);
        } else {
          // notify all threads waiting for a message
          synchronized (queue) {
            queue.add(incoming);
            queue.notifyAll();
          }
        }
      } catch (IOException | ClassNotFoundException | UnsupportedVersionException e) {
        logger.error("Error receiving message from server", e);
      }
    }
  }

  public void on(MessageCode code, MessageHandler handler) {
    events.put(code, handler);
  }

  public ProtocolMessage receive(MessageCode... codes) {
    // add ERR to the expected codes
    MessageCode[] codesWithErr = new MessageCode[codes.length + 1];
    System.arraycopy(codes, 0, codesWithErr, 0, codes.length);
    codesWithErr[codes.length] = MessageCode.ERR;

    while (true) {
      synchronized (queue) {
        for (ProtocolMessage message : queue) {
          for (MessageCode code : codesWithErr) {
            if (message.getCode() == code) {
              queue.remove(message);
              return message;
            }
          }
          // discard read messages
          queue.remove(message);
        }

        try {
          queue.wait();
        } catch (InterruptedException e) {
          logger.error("Error waiting for message", e);
        }
      }
    }
  }

  public void send(ProtocolMessage response) throws IOException {
    tcpClient.send(response);
  }

  /**
   * The receive() method receives the expected codes and returns the first occurrence of a message
   * in the queue with the expected code. If there are no occurrences of the expected codes in the
   * queue, the method blocks until a message with one of the expected codes is received. When
   * checking the queue, the messages that do not correspond to the expected codes are discarded.
   *
   * When reading from a socket, it works like a FIFO queue (because we are using TCP!): the first
   * message sent by the server will be the first to be received by the client. We can do the same
   * with a thread that reads from the socket and puts the messages in a queue. Then, we can read
   * from the queue instead of reading from the socket directly. This way, we can have a thread that
   * is waiting for specific messages without blocking the entire socket or using two sockets,
   * enabling us to implement, for example, notifications about changes in a board.
   *
   * @param codes expected codes
   * @return the message
   */
  public ProtocolMessage sendRecv(ProtocolMessage response, MessageCode... codes)
      throws IOException {
    synchronized (this) {
      send(response);
      return receive(codes);
    }
  }
}
