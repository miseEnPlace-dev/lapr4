package eapli.ecourse.app.board.backend;

import java.io.IOException;
import eapli.ecourse.app.board.common.TcpServer;

/**
 * Shared Board Server.
 *
 * This application holds a TCP server used to communicate with the Shared Board App by using the
 * defined protocol and an HTTP server used to provide a view-only render of the board in the
 * browser.
 */
public class App {
  private static final int BOARD_SERVER_PORT = 9999;
  private static final int HTTP_SERVER_PORT = 8080;

  public static void main(String[] args) {
    TcpServer httpServer = new TcpServer(HTTP_SERVER_PORT, HttpRequestHandler.class);
    Thread httpServerThread = new Thread(httpServer);

    TcpServer appServer = new TcpServer(BOARD_SERVER_PORT, ClientHandler.class);
    Thread appServerThread = new Thread(appServer);

    try {
      httpServerThread.start();
      appServerThread.start();
    } catch (Exception e) {
      System.out.println("Error creating the tcp server");
      e.printStackTrace();
    }
  }
}
