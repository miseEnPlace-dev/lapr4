package eapli.ecourse.common.board;

import java.io.File;
import java.io.IOException;

import javax.net.ssl.SSLServerSocket;
import javax.net.ssl.SSLServerSocketFactory;
import javax.net.ssl.SSLSocket;

import eapli.ecourse.AppSettings;

public class TcpServer {
  private int port;
  private Class<? extends Runnable> handlerClass;

  private final AppSettings settings = new AppSettings();

  private final String TRUSTED_STORE = settings.sslServerTrustedStore();
  private final String STORE_PATH = "ecourse.common.board/src/main/resources/" + TRUSTED_STORE;
  private final String KEYSTORE_PASS = settings.sslKeystorePassword();

  public TcpServer(int port, Class<? extends Runnable> handler) {
    this.port = port;
    this.handlerClass = handler;
  }

  public void run() {
    // create a tcp socket and listen to the defined port
    SSLServerSocket tcpSocket;
    SSLSocket socket;

    try {
      final String fileName = new File(STORE_PATH).getAbsolutePath();

      settings.setSSLTrustStore(fileName, KEYSTORE_PASS);

      tcpSocket = (SSLServerSocket) SSLServerSocketFactory.getDefault().createServerSocket(port);
    } catch (IOException e) {
      System.out.println("Error creating the tcp socket");
      e.printStackTrace();
      return;
    }

    System.out.printf("[TCP Server] Listening on port %d!\n", port);

    while (!tcpSocket.isClosed()) {
      try {
        // establish the tcp connection by accepting it
        socket = (SSLSocket) tcpSocket.accept();

        // create a new client handler
        Runnable handler = handlerClass.getConstructor(SSLSocket.class).newInstance(socket);

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
