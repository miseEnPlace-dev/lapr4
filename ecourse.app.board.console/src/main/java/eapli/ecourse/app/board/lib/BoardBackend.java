package eapli.ecourse.app.board.lib;

import java.io.IOException;
import java.net.UnknownHostException;
import eapli.ecourse.app.board.authz.CredentialStore;
import eapli.ecourse.common.board.TcpClient;
import eapli.ecourse.common.board.protocol.MessageCode;

public class BoardBackend {
  private final TcpClient tcpClient;
  private final CredentialStore credentialStore;
  private MessageListener listener;

  private BoardBackend() {
    this.tcpClient = new TcpClient();
    this.credentialStore = new CredentialStore();
    this.listener = null;
  }

  // ? deprecated way of sending messages, use message listener instead
  // public TcpClient getTcpClient() {
  // return tcpClient;
  // }

  public CredentialStore getCredentialStore() {
    return credentialStore;
  }

  public MessageListener getListener() {
    return listener;
  }

  // connect to the backend
  public void connect(String host, int port, boolean secure)
      throws UnknownHostException, IOException {
    tcpClient.connect(host, port, secure);

    listener = new MessageListener(tcpClient);

    listener.on(MessageCode.NOTIFICATION, (message) -> {
      System.out.printf(" -- NOTIFICATION --\n%s\n", message.getStringifiedPayload());
    });

    /**
     * This thread will be responsible for receiving messages from the server.
     */
    Thread listenerThread = new Thread(listener);
    listenerThread.start();
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
