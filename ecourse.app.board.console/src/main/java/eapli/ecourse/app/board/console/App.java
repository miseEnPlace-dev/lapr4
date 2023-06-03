package eapli.ecourse.app.board.console;

import java.io.IOException;
import java.util.Scanner;
import eapli.ecourse.common.board.HttpServer;
import eapli.ecourse.common.board.TcpClient;
import eapli.ecourse.common.board.http.Router;
import eapli.ecourse.common.board.http.StaticMiddleware;
import eapli.ecourse.common.board.protocol.MessageCode;
import eapli.ecourse.common.board.protocol.ProtocolMessage;
import eapli.ecourse.common.board.protocol.UnsupportedVersionException;
import eapli.ecourse.app.board.console.authz.CredentialStore;
import eapli.ecourse.app.board.console.controllers.ApiController;
import eapli.ecourse.app.common.console.ECourseBaseApplication;
import eapli.ecourse.app.common.console.presentation.authz.LoginAction;
import eapli.ecourse.app.common.console.presentation.authz.LoginUI;
import eapli.framework.infrastructure.pubsub.EventDispatcher;
import eapli.framework.io.util.Console;

public class App extends ECourseBaseApplication {
  // move to properties
  private static final String BOARD_SERVER_HOST = "localhost";
  private static final int BOARD_SERVER_PORT = 9999;

  private static final int HTTP_SERVER_PORT = 8080;
  private static final String WWW_PATH = "www/src";

  private CredentialStore credentialStore;

  /**
   * Empty constructor is private to avoid instantiation of this class.
   */
  private App() {}

  public static void main(final String[] args) {
    new App().run(args);
  }

  @Override
  protected void doMain(String[] args) {
    credentialStore = new CredentialStore();

    boolean logged = new LoginUI(credentialStore.STORE_CREDENTIALS).show();

    if (logged) {
      // next ui
    }

    /**
     * // create the router Router router = new Router();
     *
     * // retrieve static files middleware router.use(new StaticMiddleware(WWW_PATH));
     *
     * // add your routes here router.get("/api", new ApiController());
     *
     * // ...
     *
     * // create the http server HttpServer httpServer = new HttpServer(HTTP_SERVER_PORT, router);
     * Thread httpServerThread = new Thread(httpServer);
     *
     * httpServerThread.start();
     *
     * // connect to the shared board server TcpClient tcpClient = new TcpClient(BOARD_SERVER_HOST,
     * BOARD_SERVER_PORT); tcpClient.connect();
     *
     * int option = 0;
     *
     * do { System.out.println("Menu"); System.out.println(" 1. Login"); System.out.println(" 2.
     * Comm Test"); System.out.println(" 0. Exit"); System.out.print("> ");
     *
     * Scanner scanner = new Scanner(System.in);
     *
     * option = scanner.nextInt();
     *
     * switch (option) { // login case 1: String username = Console.readLine("Username:"); String
     * password = Console.readLine("Password:");
     *
     * tcpClient.send( new ProtocolMessage(MessageCode.AUTH, new String(username + "\0" + password +
     * "\0")));
     *
     * ProtocolMessage response = tcpClient.receive();
     *
     * if (response.getCode() == MessageCode.ACK) { System.out.println("Login successful!"); break;
     * } else { System.out.println("Login failed: " + response.getStringifiedPayload()); } break;
     *
     * // comm test case 2: tcpClient.send(new ProtocolMessage(MessageCode.COMMTEST));
     *
     * ProtocolMessage commtest = tcpClient.receive();
     *
     * System.out.println(commtest.toString());
     *
     * break; case 0: return; }
     *
     * } while (option != 0);
     */
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
