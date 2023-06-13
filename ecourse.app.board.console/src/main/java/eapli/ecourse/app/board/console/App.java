package eapli.ecourse.app.board.console;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eapli.ecourse.AppSettings;
import eapli.ecourse.app.board.console.presentation.MainMenu;
import eapli.ecourse.app.board.lib.BoardBackend;
import eapli.ecourse.app.board.lib.BoardHttpServer;
import eapli.ecourse.app.common.console.ECourseBaseApplication;
import eapli.ecourse.app.common.console.presentation.authz.LoginUI;
import eapli.framework.infrastructure.pubsub.EventDispatcher;

public class App extends ECourseBaseApplication {
  // move to properties
  private static final String BOARD_SERVER_HOST = "localhost";
  private static final int BOARD_SERVER_PORT = 9999;

  private final static Logger LOGGER = LogManager.getLogger(App.class);

  /**
   * Empty constructor is private to avoid instantiation of this class.
   */
  private App() {
  }

  public static void main(final String[] args) {
    new App().run(args);
  }

  @Override
  protected void doMain(String[] args) {
    BoardBackend boardBackend = BoardBackend.getInstance();
    boolean isSecure = new AppSettings().isSSLEnabled();

    // connect to the board server
    try {
      boardBackend.connect(BOARD_SERVER_HOST, BOARD_SERVER_PORT, isSecure);
    } catch (IOException e) {
      LOGGER.error("Error connecting to the Shared Board Server", e);
      return;
    }

    final boolean logged = (new LoginUI(boardBackend.getCredentialStore().AUTHENTICATE)).show();

    if (logged) {
      // start the board http server
      BoardHttpServer.run(isSecure);

      // next ui
      (new MainMenu()).mainLoop();
    }
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
  protected void doSetupEventHandlers(EventDispatcher dispatcher) {
  }

  @Override
  protected void configureAuthz() {
  }
}
