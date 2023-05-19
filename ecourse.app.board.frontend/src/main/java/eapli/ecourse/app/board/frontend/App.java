package eapli.ecourse.app.board.frontend;

import java.io.IOException;
import eapli.ecourse.app.board.common.TcpClient;
import eapli.ecourse.app.board.common.TcpServer;

public class App {
  private static final int PORT = 8080;

  public static void main(String[] args) {
    TcpClient client = new TcpClient("localhost", 9999);

    client.connect();

    // TcpServer server = new TcpServer(PORT, HttpRequestHandler.class);

    // try {
    // server.init();
    // } catch (IOException e) {
    // System.out.println("Error creating the tcp server");
    // e.printStackTrace();
    // }
  }
}
