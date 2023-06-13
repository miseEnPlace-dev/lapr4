package eapli.ecourse.common.board;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.net.ssl.SSLSocket;
import javax.net.ssl.SSLSocketFactory;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eapli.ecourse.AppSettings;
import eapli.ecourse.common.board.protocol.ProtocolMessage;
import eapli.ecourse.common.board.protocol.UnsupportedVersionException;

public class TcpClient {
  private SSLSocket socket;
  private DataInputStream input;
  private DataOutputStream output;

  private final AppSettings settings = new AppSettings();

  private final String TRUSTED_STORE = settings.sslClientTrustedStore();
  private final String STORE_PATH = "ecourse.common.board/src/main/resources/" + TRUSTED_STORE;
  private final String KEYSTORE_PASS = settings.sslKeystorePassword();

  private final Logger logger = LogManager.getLogger(TcpClient.class);

  public TcpClient() {
  }

  public void connect(String hostname, int port) throws UnknownHostException, IOException {
    final String fileName = new File(STORE_PATH).getAbsolutePath();

    settings.setSSLTrustStore(fileName, KEYSTORE_PASS);

    // connect to a tcp server
    socket = (SSLSocket) SSLSocketFactory.getDefault().createSocket(hostname, port);

    logger.debug("Connected to server!");
    socket.startHandshake();

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
