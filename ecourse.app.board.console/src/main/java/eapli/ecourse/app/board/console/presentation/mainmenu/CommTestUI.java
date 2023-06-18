package eapli.ecourse.app.board.console.presentation.mainmenu;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eapli.ecourse.app.board.lib.BoardBackend;
import eapli.ecourse.app.board.lib.MessageListener;
import eapli.ecourse.common.board.protocol.MessageCode;
import eapli.ecourse.common.board.protocol.ProtocolMessage;
import eapli.framework.presentation.console.AbstractUI;

public class CommTestUI extends AbstractUI {
  private static Logger logger = LogManager.getLogger(CommTestUI.class);

  @Override
  protected boolean doShow() {
    MessageListener listener = BoardBackend.getInstance().getListener();

    try {
      long start = System.nanoTime();
      listener.sendRecv(new ProtocolMessage(MessageCode.COMMTEST), MessageCode.ACK);
      long end = System.nanoTime();

      long elapsed = end - start;
      float ms = elapsed / 1000000f;

      System.out.printf("Communication test successful. Round-trip time: %f ms\n", ms);
    } catch (IOException e) {
      logger.error("Error sending COMMTEST message", e);
    }

    return false;
  }

  @Override
  public String headline() {
    return "CommTest";
  }
}
