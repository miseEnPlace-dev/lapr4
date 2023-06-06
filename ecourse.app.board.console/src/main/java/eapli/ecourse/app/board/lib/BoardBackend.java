package eapli.ecourse.app.board.lib;

import java.io.IOException;
import java.net.UnknownHostException;
import eapli.ecourse.app.board.authz.CredentialStore;
import eapli.ecourse.common.board.TcpClient;

public class BoardBackend {
  private final TcpClient tcpClient;
  private final CredentialStore credentialStore;

  private BoardBackend() {
    this.tcpClient = new TcpClient();
    this.credentialStore = new CredentialStore();
  }

  public TcpClient getTcpClient() {
    return tcpClient;
  }

  public CredentialStore getCredentialStore() {
    return credentialStore;
  }

  // connect to the backend
  public void connect(String host, int port) throws UnknownHostException, IOException {
    tcpClient.connect(host, port);
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
