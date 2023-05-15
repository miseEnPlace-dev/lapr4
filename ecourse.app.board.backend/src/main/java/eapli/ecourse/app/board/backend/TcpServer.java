package eapli.ecourse.app.board.backend;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
  private int port;

  public TcpServer(int port) {
    this.port = port;
  }

  public void init() throws IOException {
    // create a tcp socket and listen to the defined port
    ServerSocket tcpSocket = new ServerSocket(port);
    Socket socket = null;

    System.out.printf("[TCP Server] Listening on port %d!\n", port);

    while (true) {
      // establish the tcp conenction by accepting it
      socket = tcpSocket.accept();

      // create a new thread to handle the client
      Thread clientHandler = new Thread(new ClientHandler(socket));
      clientHandler.start();
    }

    // close the tcp socket
    // tcpSocket.close();
  }
}
