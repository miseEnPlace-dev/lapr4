package eapli.ecourse.app.board.backend;

import java.io.IOException;

public class App {
  private static final int PORT = 1337;

  public static void main(String[] args) {
    System.out.println("Hello World!");

    TcpServer server = new TcpServer(PORT);

    try {
      server.init();
    } catch (IOException e) {
      System.out.println("Error creating the tpc server");
      e.printStackTrace();
    }
  }
}
