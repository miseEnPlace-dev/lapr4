package eapli.ecourse.app.board.authz;

import java.io.IOException;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eapli.ecourse.app.board.lib.BoardBackend;
import eapli.ecourse.common.board.TcpClient;
import eapli.ecourse.common.board.protocol.MessageCode;
import eapli.ecourse.common.board.protocol.ProtocolMessage;
import eapli.ecourse.common.board.protocol.UnsupportedVersionException;
import eapli.ecourse.infrastructure.authz.CredentialHandler;
import eapli.ecourse.usermanagement.dto.UserDTO;

public class CredentialStore {
  private final static Logger LOGGER = LogManager.getLogger(CredentialStore.class);

  private UserDTO user;

  public Optional<UserDTO> getUser() {
    return Optional.ofNullable(this.user);
  }

  public final CredentialHandler AUTHENTICATE = (u, p, r) -> {
    BoardBackend boardBackend = BoardBackend.getInstance();

    TcpClient client = boardBackend.getTcpClient();

    try {
      ProtocolMessage response =
          client.sendRecv(new ProtocolMessage(MessageCode.AUTH, new String(u + "\0" + p + "\0")));

      if (response.getCode() == MessageCode.ACK) {
        user = (UserDTO) response.getPayloadAsObject();
        return true;
      }

      return false;
    } catch (IOException | UnsupportedVersionException | ClassNotFoundException e) {
      LOGGER.error("Error while authenticating", e);
      return false;
    }
  };
}
