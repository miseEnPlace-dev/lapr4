package eapli.ecourse.app.board.backend;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;

public class ClientHandler implements Runnable {
  private Socket client;

  public ClientHandler(Socket socket) {
    this.client = socket;
  }

  @Override
  public void run() {
    try {

      // process

      System.out.printf("[Client Handler Thread] Connected to %s port %d!\n",
          client.getInetAddress().toString(), client.getPort());

      // in udp applications, each send must match one receive in the
      // counterpart and the number of bytes transported by each datagram is
      // not required to be known prior to the reception; however, in tcp
      // applications, the number of bytes being read must exactly match the
      // number of bytes written in the counterpart, meaning that it is
      // required to apply a protocol design to ensure applications always
      // know exactly how many bytes they are supposed to read

      // * raw byte reading/writing: use DataInputStream and DataOutputStream

      // buffer
      byte[] buffer = new byte[1024];

      // create a data input stream to read from the client
      DataInputStream input = new DataInputStream(client.getInputStream());

      // and a data output stream to write to the client
      DataOutputStream output = new DataOutputStream(client.getOutputStream());

      while (!client.isClosed()) {
        // read from the client
        input.read(buffer, 0, buffer.length);

        System.out.println("[Client Handler Thread] Received: " + new String(buffer));

        // echo back
        output.write(buffer);

        System.out.println("[Client Handler Thread] Echoed back");
      }

      client.close();

    } catch (IOException e) {
      System.out.println("[Client Handler Thread] Error: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
