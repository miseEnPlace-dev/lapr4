package eapli.ecourse.app.board.backend;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eapli.ecourse.app.board.backend.messages.AckMessage;
import eapli.ecourse.app.board.backend.messages.AuthMessage;
import eapli.ecourse.app.board.backend.messages.CommTestMessage;
import eapli.ecourse.app.board.backend.messages.DisconnMessage;
import eapli.ecourse.app.board.backend.messages.ErrMessage;
import eapli.ecourse.app.board.backend.messages.Message;
import eapli.ecourse.app.board.backend.messages.UnsupportedMessage;
import eapli.ecourse.app.board.common.protocol.ProtocolMessage;
import eapli.ecourse.app.board.common.protocol.UnsupportedVersionException;
import eapli.ecourse.infrastructure.auth.PasswordEncoderContext;
import eapli.ecourse.infrastructure.authz.AuthenticationCredentialHandler;
import eapli.ecourse.infrastructure.authz.CredentialHandler;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usermanagement.domain.ClientPasswordPolicy;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class ClientHandler implements Runnable {
  private Socket client;
  private Logger logger;

  public ClientHandler(Socket socket) {
    this.client = socket;
  }

  @Override
  public void run() {
    try {
      logger = LogManager.getLogger(ClientHandler.class);

      logger.debug("[Client Handler Thread] Connected to "
          + client.getInetAddress().getHostAddress() + " port " + client.getPort() + "!");

      AuthzRegistry.configure(PersistenceContext.repositories().users(), new ClientPasswordPolicy(),
          PasswordEncoderContext.passwordHash());

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

        handle(message, output);
      }

      logger.debug("Connection closed.");

      output.close();
      input.close();
      client.close();
    } catch (IOException | UnsupportedVersionException e) {
      logger.error("\n[Client Handler Thread] Error: " + e.getMessage());
      e.printStackTrace();
    }
  }

  private void handle(ProtocolMessage message, DataOutputStream output) throws IOException {
    Message handleMessage;

    // trolha
    switch (message.getCode()) {
      case ACK:
        // normally used in responses
        handleMessage = new AckMessage(message, output);
        break;

      case AUTH:
        // ...
        handleMessage = new AuthMessage(message, output);
        break;

      case COMMTEST:
        handleMessage = new CommTestMessage(message, output);
        break;

      case DISCONN:
        // ...
        handleMessage = new DisconnMessage(message, output);
        break;

      case ERR:
        // ...
        handleMessage = new ErrMessage(message, output);
        break;

      default:
        handleMessage = new UnsupportedMessage(output);
        break;
    }

    handleMessage.handle();
  }
}
