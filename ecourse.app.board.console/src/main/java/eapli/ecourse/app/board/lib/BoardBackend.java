package eapli.ecourse.app.board.lib;

import java.io.IOException;
import java.net.UnknownHostException;
import java.util.LinkedList;
import java.util.List;
import eapli.ecourse.app.board.authz.CredentialStore;
import eapli.ecourse.common.board.TcpClient;
import eapli.ecourse.common.board.protocol.ProtocolMessage;
import eapli.ecourse.common.board.protocol.UnsupportedVersionException;

public class BoardBackend {
  private final TcpClient tcpClient;
  private final CredentialStore credentialStore;
  private final List<ProtocolMessage> messageQueue;

  private BoardBackend() {
    this.tcpClient = new TcpClient();
    this.credentialStore = new CredentialStore();
    this.messageQueue = new LinkedList<>();
  }

  public TcpClient getTcpClient() {
    return tcpClient;
  }

  public CredentialStore getCredentialStore() {
    return credentialStore;
  }

  // connect to the backend
  public void connect(String host, int port, boolean secure)
      throws UnknownHostException, IOException {
    tcpClient.connect(host, port, secure);

    /**
     * This thread will be responsible for receiving messages from the server and putting them in
     * the queue.
     */
    Thread messageQueueThread = new Thread(new MessageQueueHandler(messageQueue, tcpClient));
    messageQueueThread.start();
  }

  public void send(ProtocolMessage message) throws IOException {
    tcpClient.send(message);
  }

  public ProtocolMessage receive()
      throws IOException, UnsupportedVersionException, ClassNotFoundException {

    /**
     * When receiving from a socket, it works like a FIFO queue: the first message sent by the
     * server will be the first to be received by the client. We can do the same with a thread that
     * reads from the socket and puts the messages in a queue. Then, we can read from the queue
     * instead of reading from the socket directly. This way, we can have a thread that is waiting
     * for specific messages without blocking the entire socket or using two sockets, enabling us to
     * implement the notifications about changes in a board.
     */

    // TODO

    return tcpClient.receive();
  }

  // singleton
  private static BoardBackend instance;

  public static BoardBackend getInstance() {
    if (instance == null) {
      synchronized (BoardBackend.class) {
        instance = new BoardBackend();
      }
    }

    return instance;
  }
}
