package eapli.ecourse.daemon.board;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.net.ssl.SSLSocket;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eapli.ecourse.common.board.protocol.MessageCode;
import eapli.ecourse.common.board.protocol.ProtocolMessage;
import eapli.ecourse.common.board.protocol.UnsupportedVersionException;
import eapli.ecourse.daemon.board.messages.AckMessage;
import eapli.ecourse.daemon.board.messages.AuthMessage;
import eapli.ecourse.daemon.board.messages.BadRequestMessage;
import eapli.ecourse.daemon.board.messages.CommTestMessage;
import eapli.ecourse.daemon.board.messages.DisconnMessage;
import eapli.ecourse.daemon.board.messages.ErrMessage;
import eapli.ecourse.daemon.board.messages.GetBoardMessage;
import eapli.ecourse.daemon.board.messages.GetBoardPostItsMessage;
import eapli.ecourse.daemon.board.messages.GetBoardsMessage;
import eapli.ecourse.daemon.board.messages.GetOwnBoardsMessage;
import eapli.ecourse.daemon.board.messages.GetUserPermissionsMessage;
import eapli.ecourse.daemon.board.messages.GetWritableBoardsMessage;
import eapli.ecourse.daemon.board.messages.Message;
import eapli.ecourse.daemon.board.messages.ShareBoardMessage;
import eapli.ecourse.daemon.board.messages.UndoPostItMessage;

public class ClientHandler implements Runnable {
  private SSLSocket client;

  // define here the handler for the pretended message code
  private final static Map<MessageCode, Class<? extends Message>> MESSAGE_MAP = new HashMap<>() {
    {
      put(MessageCode.ACK, AckMessage.class);
      put(MessageCode.AUTH, AuthMessage.class);
      put(MessageCode.COMMTEST, CommTestMessage.class);
      put(MessageCode.DISCONN, DisconnMessage.class);
      put(MessageCode.ERR, ErrMessage.class);
      put(MessageCode.GET_BOARDS, GetBoardsMessage.class);
      put(MessageCode.GET_OWN_BOARDS, GetOwnBoardsMessage.class);
      put(MessageCode.GET_WRITABLE_BOARDS, GetWritableBoardsMessage.class);
      put(MessageCode.GET_BOARD, GetBoardMessage.class);
      put(MessageCode.GET_USER_PERMISSIONS, GetUserPermissionsMessage.class);
      // put(MessageCode.GET_BOARD_HISTORY, GetBoardHistoryMessage.class);
      put(MessageCode.SHARE_BOARD, ShareBoardMessage.class);
      // put(MessageCode.ARCHIVE_BOARD, ArchiveBoardMessage.class);
      // put(MessageCode.CREATE_POSTIT, CreatePostItMessage.class);
      // put(MessageCode.EDIT_POSTIT, EditPostItMessage.class);
      put(MessageCode.UNDO_POSTIT, UndoPostItMessage.class);
      put(MessageCode.GET_BOARD_POSTITS, GetBoardPostItsMessage.class);
    }
  };

  private final Logger logger = LogManager.getLogger(ClientHandler.class);

  public ClientHandler(SSLSocket socket) {
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
        try {
          // parse the message
          ProtocolMessage message = ProtocolMessage.fromDataStream(input);

          if (message == null)
            break;

          logger.debug("\n" + message.toString());

          processMessage(message, output);
        } catch (UnsupportedVersionException e) {
          (new BadRequestMessage(output, client)).handle();
        }
      }

      logger.debug("Connection closed.");

      output.close();
      input.close();
    } catch (IOException e) {
      logger.error("\n[Client Handler Thread] Error", e);
    }
  }

  private void processMessage(ProtocolMessage message, DataOutputStream output) throws IOException {
    Message handleMessage;

    Class<? extends Message> clazz = MESSAGE_MAP.get(message.getCode());

    if (clazz == null) {
      handleMessage = new BadRequestMessage(output, client);
    } else {
      try {
        handleMessage = clazz
            .getDeclaredConstructor(ProtocolMessage.class, DataOutputStream.class, SSLSocket.class)
            .newInstance(message, output, this.client);
      } catch (Exception e) {
        logger.error("\n[Client Handler Thread] Error", e);
        return;
      }
    }

    handleMessage.handle();
  }
}
