package eapli.ecourse.common.board;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.net.ssl.SSLParameters;
import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

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

  public void connect(String hostname, int port, boolean secure)
      throws UnknownHostException, IOException {
    // connect to a tcp server
    if (secure) {
      socket = SSLSocketFactory.getDefault().createSocket(hostname, port);

      SSLParameters sslParams = new SSLParameters();
      sslParams.setEndpointIdentificationAlgorithm("HTTPS");
      ((SSLSocket) socket).setSSLParameters(sslParams);

      ((SSLSocket) socket).startHandshake();
    } else {
      socket = new Socket(hostname, port);
    }

    logger.debug("Connected to the server!");

    // create a data input stream to read from the client
    input = new DataInputStream(socket.getInputStream());

    // and a data output stream to write to the client
    output = new DataOutputStream(socket.getOutputStream());
  }

  public void send(ProtocolMessage msg) throws IOException {
    output.write(msg.toByteStream());
  }

  public ProtocolMessage receive()
      throws IOException, UnsupportedVersionException, ClassNotFoundException {
    return ProtocolMessage.fromDataStream(input);
  }

  public ProtocolMessage sendRecv(ProtocolMessage msg)
      throws IOException, UnsupportedVersionException, ClassNotFoundException {
    synchronized (this) {
      send(msg);
      return receive();
    }
  }

  public void close() throws IOException {
    output.close();
    input.close();
    socket.close();
  }
}
