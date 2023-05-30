package eapli.ecourse.app.board.backend;

import eapli.ecourse.app.board.common.TcpServer;

/**
 * Shared Board Server.
 *
 * This application holds a TCP server used to communicate with the Shared Board App by using the
 * defined protocol.
 */
public class App {
  // move to properties
  private static final int BOARD_SERVER_PORT = 9999;

  public static void main(String[] args) {
    try {
      TcpServer appServer = new TcpServer(BOARD_SERVER_PORT, ClientHandler.class);
      appServer.run();
    } catch (Exception e) {
      System.out.println("Error creating the tcp server");
      e.printStackTrace();
    }
  }
}
