package eapli.ecourse.app.board.console;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eapli.ecourse.AppSettings;
import eapli.ecourse.app.board.console.presentation.FrontMenu;
import eapli.ecourse.app.board.lib.BoardBackend;
import eapli.ecourse.app.common.console.ECourseBaseApplication;
import eapli.framework.infrastructure.pubsub.EventDispatcher;

public class App extends ECourseBaseApplication {
  private final static Logger LOGGER = LogManager.getLogger(App.class);

  /**
   * Empty constructor is private to avoid instantiation of this class.
   */
  private App() {}

  public static void main(final String[] args) {
    new App().run(args);
  }

  @Override
  protected void doMain(String[] args) {
    BoardBackend boardBackend = BoardBackend.getInstance();

    AppSettings appSettings = new AppSettings();

    boolean isSecure = appSettings.isSSLEnabled();
    String host = appSettings.boardServerHost();
    Integer port = appSettings.boardServerPort();

    // connect to the board server
    try {
      boardBackend.connect(host, port, isSecure);
    } catch (IOException e) {
      LOGGER.error("Error connecting to the Shared Board Server", e);
      return;
    }

    new FrontMenu().mainLoop();
  }

  @Override
  protected String appTitle() {
    return "Shared Board App";
  }

  @Override
  protected String appGoodbye() {
    return "Bye!";
  }

  @Override
  protected void doSetupEventHandlers(EventDispatcher dispatcher) {}

  @Override
  protected void configureAuthz() {}
}
