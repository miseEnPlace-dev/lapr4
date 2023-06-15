package eapli.ecourse.app.board.console.presentation.mainmenu;

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
  private static Logger logger = LogManager.getLogger(CommTestUI.class);

  @Override
  protected boolean doShow() {
    TcpClient client = BoardBackend.getInstance().getTcpClient();

    try {
      long start = System.nanoTime();
      client.sendRecv(new ProtocolMessage(MessageCode.COMMTEST));
      long end = System.nanoTime();

      long elapsed = end - start;
      float ms = elapsed / 1000000f;

      System.out.printf("Communication test successful. Round-trip time: %f ms\n", ms);
    } catch (IOException | UnsupportedVersionException | ClassNotFoundException e) {
      logger.error("Error sending COMMTEST message", e);
    }

    return false;
  }

  @Override
  public String headline() {
    return "CommTest";
  }
}

