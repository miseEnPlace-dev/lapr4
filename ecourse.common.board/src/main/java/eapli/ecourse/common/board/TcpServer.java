package eapli.ecourse.common.board;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
  private int port;
  private Class<? extends Runnable> handlerClass;

  public TcpServer(int port, Class<? extends Runnable> handler) {
    this.port = port;
    this.handlerClass = handler;
  }

  public void run() {
    // create a tcp socket and listen to the defined port
    ServerSocket tcpSocket;
    Socket socket;

    try {
      tcpSocket = new ServerSocket(port);
    } catch (IOException e) {
      System.out.println("Error creating the tcp socket");
      e.printStackTrace();
      return;
    }

    System.out.printf("[TCP Server] Listening on port %d!\n", port);

    while (!tcpSocket.isClosed()) {
      try {
        // establish the tcp conenction by accepting it
        socket = tcpSocket.accept();

        // create a new client handler
        Runnable handler = handlerClass.getConstructor(Socket.class).newInstance(socket);

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
