package eapli.ecourse.app.board.common;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.net.Socket;
import eapli.ecourse.app.board.common.protocol.MessageCode;
import eapli.ecourse.app.board.common.protocol.ProtocolMessage;

public class TcpClient {
  private String hostname;
  private int port;

  public TcpClient(String hostname, int port) {
    this.hostname = hostname;
    this.port = port;
  }

  public void connect() {
    // connect to a tcp server
    try {
      Socket socket = new Socket(this.hostname, this.port);

      System.out.println("Connected to server!");

      // create a data input stream to read from the client
      DataInputStream input = new DataInputStream(socket.getInputStream());

      // and a data output stream to write to the client
      DataOutputStream output = new DataOutputStream(socket.getOutputStream());

      // send a conntest message
      ProtocolMessage message = new ProtocolMessage(MessageCode.COMMTEST, new byte[1], 1);

      output.write(message.toByteStream());
      System.out.println("Sent COMMTEST packet!");

      socket.close();
    } catch (Exception e) {
      System.out.println("Error connecting to server: " + e.getMessage());
    }

  }
}
