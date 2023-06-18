package eapli.ecourse.app.board.console.presentation.mainmenu;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eapli.ecourse.app.board.authz.CredentialStore;
import eapli.ecourse.app.board.lib.BoardBackend;
import eapli.ecourse.app.board.lib.MessageListener;
import eapli.ecourse.common.board.protocol.MessageCode;
import eapli.ecourse.common.board.protocol.ProtocolMessage;
import eapli.framework.presentation.console.AbstractUI;

public class LogoutUI extends AbstractUI {
  private static Logger logger = LogManager.getLogger(LogoutUI.class);

  @Override
  protected boolean doShow() {
    MessageListener listener = BoardBackend.getInstance().getListener();
    CredentialStore credentialStore = BoardBackend.getInstance().getCredentialStore();

    try {
      ProtocolMessage response =
          listener.sendRecv(new ProtocolMessage(MessageCode.LOGOUT), MessageCode.ACK);
      if (response.getCode() == MessageCode.ACK) {
        credentialStore.clear();
        System.out.println("Bye!");
        return true;
      }
      System.out.println("Unable to log you out.");
    } catch (IOException e) {
      logger.error("Error sending logout message", e);
    }
    return false;
  }

  @Override
  public String headline() {
    return "Logout";
  }
}

