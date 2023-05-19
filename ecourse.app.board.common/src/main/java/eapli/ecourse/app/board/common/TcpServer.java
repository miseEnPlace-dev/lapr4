package eapli.ecourse.app.board.common;

import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.net.ServerSocket;
import java.net.Socket;

public class TcpServer {
  private int port;
  private Class<? extends Runnable> handlerClass;

  public TcpServer(int port, Class<? extends Runnable> handler) {
    this.port = port;
    this.handlerClass = handler;
  }

  public void init() throws IOException {
    // create a tcp socket and listen to the defined port
    ServerSocket tcpSocket = new ServerSocket(port);
    Socket socket = null;

    System.out.printf("[TCP Server] Listening on port %d!\n", port);

    while (true) {
      // establish the tcp conenction by accepting it
      socket = tcpSocket.accept();

      try {
        Runnable handler = handlerClass.getDeclaredConstructor().newInstance(socket);

        // create a new thread to handle the client
        Thread clientHandler = new Thread(handler);

        clientHandler.start();
      } catch (Exception e) {
        e.printStackTrace();
      }
    }

    // close the tcp socket
    // tcpSocket.close();
  }
}
