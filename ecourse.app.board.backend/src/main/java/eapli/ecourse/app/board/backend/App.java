package eapli.ecourse.app.board.backend;

import java.io.IOException;

public class App {
  private static final int PORT = 9999;

  public static void main(String[] args) {
    TcpServer server = new TcpServer(PORT);

    try {
      server.init();
    } catch (IOException e) {
      System.out.println("Error creating the tcp server");
      e.printStackTrace();
    }
  }
}
