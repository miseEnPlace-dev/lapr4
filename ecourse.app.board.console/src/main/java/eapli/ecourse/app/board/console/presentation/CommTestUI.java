package eapli.ecourse.app.board.console.presentation;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eapli.ecourse.app.board.lib.BoardBackend;
import eapli.ecourse.common.board.TcpClient;
import eapli.ecourse.common.board.protocol.MessageCode;
import eapli.ecourse.common.board.protocol.ProtocolMessage;
import eapli.ecourse.common.board.protocol.UnsupportedVersionException;
import eapli.framework.presentation.console.AbstractUI;

public class CommTestUI extends AbstractUI {
  private static final Logger LOGGER = LogManager.getLogger(CommTestUI.class);

  // ShareBoardController ctrl = new ShareBoardController();

  @Override
  protected boolean doShow() {
    TcpClient client = BoardBackend.getInstance().getTcpClient();

    try {
      ProtocolMessage response = client.sendRecv(new ProtocolMessage(MessageCode.COMMTEST));

      System.out.println(response.toString());
    } catch (IOException | UnsupportedVersionException e) {
      LOGGER.error("Error sending COMMTEST message", e);
    }

    return false;
  }

  @Override
  public String headline() {
    return "CommTest";
  }

}

