package eapli.ecourse.common.board;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eapli.ecourse.common.board.protocol.ProtocolMessage;
import eapli.ecourse.common.board.protocol.UnsupportedVersionException;

public class TcpClient {
  private Socket socket;
  private DataInputStream input;
  private DataOutputStream output;

  private final Logger logger = LogManager.getLogger(TcpClient.class);

  public TcpClient() {}

  public void connect(String hostname, int port) throws UnknownHostException, IOException {
    // connect to a tcp server
    socket = new Socket(hostname, port);

    logger.debug("Connected to server!");

    // create a data input stream to read from the client
    input = new DataInputStream(socket.getInputStream());

    // and a data output stream to write to the client
    output = new DataOutputStream(socket.getOutputStream());
  }

  public void send(ProtocolMessage msg) throws IOException {
    output.write(msg.toByteStream());
  }

  public ProtocolMessage receive() throws IOException, UnsupportedVersionException {
    return ProtocolMessage.fromDataStream(input);
  }

  public ProtocolMessage sendRecv(ProtocolMessage msg)
      throws IOException, UnsupportedVersionException {
    send(msg);
    return receive();
  }

  public void close() throws IOException {
    output.close();
    input.close();
    socket.close();
  }
}
