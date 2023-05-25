package eapli.ecourse.app.board.frontend;

import eapli.ecourse.app.board.common.TcpClient;

public class App {
  private static final String HOST = "localhost";
  private static final int PORT = 9999;

  public static void main(String[] args) {
    TcpClient client = new TcpClient(HOST, PORT);

    client.connect();
  }
}
