package eapli.ecourse.app.board.console.authz;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eapli.ecourse.app.board.console.lib.BoardBackend;
import eapli.ecourse.common.board.TcpClient;
import eapli.ecourse.common.board.protocol.MessageCode;
import eapli.ecourse.common.board.protocol.ProtocolMessage;
import eapli.ecourse.common.board.protocol.UnsupportedVersionException;
import eapli.ecourse.infrastructure.authz.CredentialHandler;

public class CredentialStore {
  private final static Logger LOGGER = LogManager.getLogger(CredentialStore.class);

  private String username;
  private String password;

  public String getUsername() {
    return username;
  }

  public String getPassword() {
    return password;
  }

  public final CredentialHandler AUTHENTICATE = (u, p, r) -> {
    BoardBackend boardBackend = BoardBackend.getInstance();

    TcpClient client = boardBackend.getTcpClient();

    try {
      client.send(new ProtocolMessage(MessageCode.AUTH, new String(u + "\0" + p + "\0")));

      ProtocolMessage response = client.receive();

      if (response.getCode() == MessageCode.ACK) {
        this.username = u;
        this.password = p;
        return true;
      }

      return false;
    } catch (IOException | UnsupportedVersionException e) {
      LOGGER.error("Error while authenticating", e);
      return false;
    }
  };
}
