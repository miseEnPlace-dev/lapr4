package eapli.ecourse.common.board;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

import eapli.ecourse.common.board.http.HttpClientHandler;
import eapli.ecourse.common.board.http.Router;

public class HttpServer implements Runnable {
  private int port;
  private Router router;
  private boolean secure;

  public HttpServer(int port, Router router, boolean secure) {
    this.port = port;
    this.router = router;
    this.secure = secure;
  }

  @Override
  public void run() {
    // create a tcp socket and listen to the defined port
    ServerSocket tcpSocket;
    Socket socket;

    try {
      if (secure)
        tcpSocket = (SSLServerSocket) SSLServerSocketFactory.getDefault().createServerSocket(port);
      else
        tcpSocket = new ServerSocket(port);
    } catch (IOException e) {
      System.out.println("Error creating the tcp socket");
      e.printStackTrace();
      return;
    }

    System.out.printf("[HTTP%s Server] Listening on port %d!\n", this.secure ? "S" : "", port);

    while (!tcpSocket.isClosed()) {
      try {
        // establish the tcp connection by accepting it
        socket = tcpSocket.accept();

        // create a new client handler
        HttpClientHandler handler = new HttpClientHandler(socket, this.router, this.secure);

        // create a new thread to handle the client
        Thread clientHandler = new Thread(handler);

        clientHandler.start();
      } catch (Exception e) {
        System.out.println("Error creating the client handler thread");
        e.printStackTrace();
      }
    }

    // close the tcp socket
    try {
      tcpSocket.close();
    } catch (IOException e) {
      System.out.println("Error closing the tcp socket");
      e.printStackTrace();
    }
  }
}
