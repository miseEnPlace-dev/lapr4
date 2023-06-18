package eapli.ecourse.app.board.authz;

import java.io.IOException;
import java.util.Optional;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eapli.ecourse.app.board.lib.BoardBackend;
import eapli.ecourse.app.board.lib.MessageListener;
import eapli.ecourse.common.board.protocol.MessageCode;
import eapli.ecourse.common.board.protocol.ProtocolMessage;
import eapli.ecourse.infrastructure.authz.CredentialHandler;
import eapli.ecourse.usermanagement.dto.UserDTO;

public class CredentialStore {
  private final static Logger LOGGER = LogManager.getLogger(CredentialStore.class);

  private UserDTO user;

  public Optional<UserDTO> getUser() {
    return Optional.ofNullable(this.user);
  }

  public void clear() {
    this.user = null;
  }

  public final CredentialHandler AUTHENTICATE = (u, p, r) -> {
    BoardBackend boardBackend = BoardBackend.getInstance();

    MessageListener listener = boardBackend.getListener();

    try {
      ProtocolMessage response = listener.sendRecv(
          new ProtocolMessage(MessageCode.AUTH, new String(u + "\0" + p + "\0")), MessageCode.ACK);

      if (response.getCode() == MessageCode.ACK) {
        user = (UserDTO) response.getPayloadAsObject();
        return true;
      }

      return false;
    } catch (IOException | ClassNotFoundException e) {
      LOGGER.error("Error while authenticating", e);
      return false;
    }
  };
}
