package eapli.ecourse.app.board.backend;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import eapli.ecourse.app.board.common.protocol.MessageCode;
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

      // parse the message
      ProtocolMessage message = ProtocolMessage.fromDataStream(input);

      System.out.println("\n[Client Handler Thread] Received request!");
      System.out.println(message.toString());

      // trolha
      switch (message.getCode()) {
        case ACK:
          // normally used in responses
          break;

        case AUTH:
          // ...
          byte[] buffer = (new String("Not Implemented")).getBytes();
          output.write(new ProtocolMessage(MessageCode.ERR, buffer, buffer.length).toByteStream());
          break;

        case COMMTEST:
          System.out.println("Comm test! Sending ACK");
          output.write(new ProtocolMessage(MessageCode.ACK).toByteStream());
          break;

        case DISCONN:
          // ...
          output.write(new ProtocolMessage(MessageCode.ACK).toByteStream());
          break;

        case ERR:
          // this is an error message used in responses
          break;

        default:
          System.out.println("Unrecognized code!");
          break;
      }

      client.close();

    } catch (IOException | UnsupportedVersionException e) {
      System.out.println("[Client Handler Thread] Error: " + e.getMessage());
      e.printStackTrace();
    }
  }
}
