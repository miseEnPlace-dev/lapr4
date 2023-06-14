package eapli.ecourse.common.board;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;

public class TcpServer {
  private int port;
  private Class<? extends Runnable> handlerClass;
  private boolean secure;

  public TcpServer(int port, Class<? extends Runnable> handler, boolean secure) {
    this.port = port;
    this.handlerClass = handler;
    this.secure = secure;
  }

  public void run() {
    // create a tcp socket and listen to the defined port
    ServerSocket tcpSocket;
    Socket socket;

    try {
      if (this.secure) {
        tcpSocket = SSLServerSocketFactory.getDefault().createServerSocket(port);
        SSLServerSocket sslListener = (SSLServerSocket) tcpSocket;
        sslListener.setNeedClientAuth(true);
      } else
        tcpSocket = new ServerSocket(port);
    } catch (IOException e) {
      System.out.println("Error creating the tcp socket");
      e.printStackTrace();
      return;
    }

    System.out.printf("[TCP%s Server] Listening on port %d!\n", this.secure ? " SSL" : "", port);

    while (!tcpSocket.isClosed()) {
      try {
        // establish the tcp connection by accepting it
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
