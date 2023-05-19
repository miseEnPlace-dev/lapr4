package eapli.ecourse.app.board.backend;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.Socket;
import eapli.ecourse.app.board.common.protocol.ProtocolMessage;
import eapli.ecourse.app.board.common.protocol.UnsupportedVersionException;

public class ClientHandler implements Runnable {
  private Socket client;

  public ClientHandler(Socket socket) {
    this.client = socket;
  }

  @Override
  public void run() {
    try {
      System.out.printf("[Client Handler Thread] Connected to %s port %d!\n",
          client.getInetAddress().getHostAddress(), client.getPort());

      // in udp applications, each send must match one receive in the
      // counterpart and the number of bytes transported by each datagram is
      // not required to be known prior to the reception; however, in tcp
      // applications, the number of bytes being read must exactly match the
      // number of bytes written in the counterpart, meaning that it is
      // required to apply a protocol design to ensure applications always
      // know exactly how many bytes they are supposed to read

      // * raw byte reading/writing: use DataInputStream and DataOutputStream

      // create a data input stream to read from the client
      DataInputStream input = new DataInputStream(client.getInputStream());

      // and a data output stream to write to the client
      DataOutputStream output = new DataOutputStream(client.getOutputStream());

      ProtocolMessage message = ProtocolMessage.fromDataStream(input);

      System.out.println("Protocol version: " + message.getProtocolVersion());
      System.out.println("Code: " + message.getCode());
      System.out.println("Data length: " + message.getDataLength());

      // while (!client.isClosed()) {
      // // read from the client
      // input.read(buffer, 0, buffer.length);

      // System.out.println("[Client Handler Thread] Received: " + new String(buffer));

      // // echo back
      // output.write(buffer);

      // System.out.println("[Client Handler Thread] Echoed back");
      // }

      client.close();

    } catch (IOException | UnsupportedVersionException e) {
      System.out.println("[Client Handler Thread] Error: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
