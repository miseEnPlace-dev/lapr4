package eapli.ecourse.app.board.backend;

import java.io.IOException;
import eapli.ecourse.app.board.common.TcpServer;

public class App {
  private static final int PORT = 9999;

  public static void main(String[] args) {
    TcpServer server = new TcpServer(PORT, ClientHandler.class);

    try {
      server.init();
    } catch (IOException e) {
      System.out.println("Error creating the tcp server");
      e.printStackTrace();
    }
  }
}
