package eapli.ecourse.app.board.frontend;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class HttpRequestHandler implements Runnable {
  private Socket client;

  public HttpRequestHandler(Socket socket) {
    this.client = socket;
  }

  @Override
  public void run() {
    try {
      System.out.printf("[Client Handler Thread] Connected to %s port %d!\n",
          client.getInetAddress().toString(), client.getPort());

      // buffer
      byte[] buffer = new byte[1024];

      // create a data input stream to read from the client
      DataInputStream input = new DataInputStream(client.getInputStream());

      // and a data output stream to write to the client
      DataOutputStream output = new DataOutputStream(client.getOutputStream());

      // process http message
      // ...

      client.close();

    } catch (IOException e) {
      System.out.println("[Client Handler Thread] Error: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
