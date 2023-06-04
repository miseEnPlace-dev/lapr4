package eapli.ecourse.app.board.console.authz;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eapli.ecourse.app.board.console.lib.BoardBackend;
import eapli.ecourse.common.board.TcpClient;
import eapli.ecourse.common.board.dto.UserDTO;
import eapli.ecourse.common.board.protocol.MessageCode;
import eapli.ecourse.common.board.protocol.ProtocolMessage;
import eapli.ecourse.common.board.protocol.UnsupportedVersionException;
import eapli.ecourse.infrastructure.authz.CredentialHandler;

public class CredentialStore {
  private final static Logger LOGGER = LogManager.getLogger(CredentialStore.class);

  private UserDTO user;

  public UserDTO getUser() {
    return new UserDTO(user);
  }

  public final CredentialHandler AUTHENTICATE = (u, p, r) -> {
    BoardBackend boardBackend = BoardBackend.getInstance();

    TcpClient client = boardBackend.getTcpClient();

    try {
      client.send(new ProtocolMessage(MessageCode.AUTH, new String(u + "\0" + p + "\0")));

      ProtocolMessage response = client.receive();

      if (response.getCode() == MessageCode.ACK) {
        user = UserDTO.fromJson(response.getStringifiedPayload());
        return true;
      }

      return false;
    } catch (IOException | UnsupportedVersionException e) {
      LOGGER.error("Error while authenticating", e);
      return false;
    }
  };
}
