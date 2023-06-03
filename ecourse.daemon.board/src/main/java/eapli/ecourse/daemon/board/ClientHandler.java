package eapli.ecourse.daemon.board;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eapli.ecourse.daemon.board.messages.AckMessage;
import eapli.ecourse.daemon.board.messages.AuthMessage;
import eapli.ecourse.daemon.board.messages.CommTestMessage;
import eapli.ecourse.daemon.board.messages.DisconnMessage;
import eapli.ecourse.daemon.board.messages.ErrMessage;
import eapli.ecourse.daemon.board.messages.Message;
import eapli.ecourse.daemon.board.messages.UnsupportedMessage;
import eapli.ecourse.common.board.protocol.ProtocolMessage;
import eapli.ecourse.common.board.protocol.UnsupportedVersionException;

public class ClientHandler implements Runnable {
  private Socket client;
  private final Logger logger = LogManager.getLogger(ClientHandler.class);

  public ClientHandler(Socket socket) {
    this.client = socket;
  }

  @Override
  public void run() {
    try {
      logger.debug("[Client Handler Thread] Connected to "
          + client.getInetAddress().getHostAddress() + " port " + client.getPort() + "!");

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

      while (!client.isClosed()) {
        // parse the message
        ProtocolMessage message = ProtocolMessage.fromDataStream(input);

        if (message == null)
          break;

        logger.debug("\n" + message.toString());

        processMessage(message, output);
      }

      logger.debug("Connection closed.");

      output.close();
      input.close();
      client.close();
    } catch (IOException | UnsupportedVersionException e) {
      logger.error("\n[Client Handler Thread] Error", e);
    }
  }

  private void processMessage(ProtocolMessage message, DataOutputStream output) throws IOException {
    Message handleMessage;

    switch (message.getCode()) {
      case ACK:
        handleMessage = new AckMessage(message, output);
        break;
      case AUTH:
        handleMessage = new AuthMessage(message, output);
        break;
      case COMMTEST:
        handleMessage = new CommTestMessage(message, output);
        break;
      case DISCONN:
        handleMessage = new DisconnMessage(message, output);
        break;
      case ERR:
        handleMessage = new ErrMessage(message, output);
        break;
      default:
        handleMessage = new UnsupportedMessage(output);
        break;
    }

    handleMessage.handle();
  }
}
