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
     * This thread will be responsible for receiving messages from the server to the queue.
     */
    Thread messageQueueThread = new Thread(new MessageQueueHandler(messageQueue, tcpClient));
    messageQueueThread.start();
  }

  public void send(ProtocolMessage message) throws IOException {

    tcpClient.send(message);
  }

  public ProtocolMessage receive()
      throws IOException, UnsupportedVersionException, ClassNotFoundException {
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
