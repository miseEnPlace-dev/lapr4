package eapli.ecourse.daemon.board;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eapli.ecourse.common.board.SafeBoardUpdatesCounter;
import eapli.ecourse.common.board.SafeBoardUpdatesShared;
import eapli.ecourse.common.board.SafeOnlineCounter;
import eapli.ecourse.common.board.protocol.MessageCode;
import eapli.ecourse.common.board.protocol.ProtocolMessage;
import eapli.ecourse.common.board.protocol.UnsupportedVersionException;
import eapli.ecourse.daemon.board.messages.AckMessage;
import eapli.ecourse.daemon.board.messages.ArchiveBoardMessage;
import eapli.ecourse.daemon.board.messages.AuthMessage;
import eapli.ecourse.daemon.board.messages.BadRequestMessage;
import eapli.ecourse.daemon.board.messages.ChangePostItMessage;
import eapli.ecourse.daemon.board.messages.CommTestMessage;
import eapli.ecourse.daemon.board.messages.CreatePostItMessage;
import eapli.ecourse.daemon.board.messages.DeletePostItMessage;
import eapli.ecourse.daemon.board.messages.DisconnMessage;
import eapli.ecourse.daemon.board.messages.ErrMessage;
import eapli.ecourse.daemon.board.messages.GetBoardHistoryMessage;
import eapli.ecourse.daemon.board.messages.GetBoardMessage;
import eapli.ecourse.daemon.board.messages.GetBoardsMessage;
import eapli.ecourse.daemon.board.messages.GetOnlineCountMessage;
import eapli.ecourse.daemon.board.messages.GetOwnBoardsMessage;
import eapli.ecourse.daemon.board.messages.GetOwnPostItsBoardMessage;
import eapli.ecourse.daemon.board.messages.GetPostItsBoardMessage;
import eapli.ecourse.daemon.board.messages.GetUserPermissionsMessage;
import eapli.ecourse.daemon.board.messages.GetWritableBoardsMessage;
import eapli.ecourse.daemon.board.messages.IsCellAvailableMessage;
import eapli.ecourse.daemon.board.messages.LogoutMessage;
import eapli.ecourse.daemon.board.messages.Message;
import eapli.ecourse.daemon.board.messages.ShareBoardMessage;
import eapli.ecourse.daemon.board.messages.UndoPostItMessage;

public class ClientHandler implements Runnable {

  // ? define here the handler for the pretended message code
  private final static Map<MessageCode, Class<? extends Message>> MESSAGE_MAP = new HashMap<>() {
    {
      put(MessageCode.ACK, AckMessage.class);
      put(MessageCode.AUTH, AuthMessage.class);
      put(MessageCode.COMMTEST, CommTestMessage.class);
      put(MessageCode.DISCONN, DisconnMessage.class);
      put(MessageCode.ERR, ErrMessage.class);
      put(MessageCode.LOGOUT, LogoutMessage.class);

      // board
      put(MessageCode.GET_BOARDS, GetBoardsMessage.class);
      put(MessageCode.GET_OWN_BOARDS, GetOwnBoardsMessage.class);
      put(MessageCode.GET_WRITABLE_BOARDS, GetWritableBoardsMessage.class);
      put(MessageCode.GET_BOARD, GetBoardMessage.class);
      put(MessageCode.GET_USER_PERMISSIONS, GetUserPermissionsMessage.class);
      put(MessageCode.SHARE_BOARD, ShareBoardMessage.class);
      put(MessageCode.ARCHIVE_BOARD, ArchiveBoardMessage.class);

      // post-its
      put(MessageCode.GET_POSTITS_BOARD, GetPostItsBoardMessage.class);
      put(MessageCode.GET_OWN_POSTITS_BOARD, GetOwnPostItsBoardMessage.class);
      put(MessageCode.GET_BOARD_HISTORY, GetBoardHistoryMessage.class);
      put(MessageCode.CREATE_POSTIT, CreatePostItMessage.class);
      put(MessageCode.CHANGE_POSTIT, ChangePostItMessage.class);
      put(MessageCode.UNDO_POSTIT, UndoPostItMessage.class);
      put(MessageCode.IS_CELL_AVAILABLE, IsCellAvailableMessage.class);
      put(MessageCode.DELETE_POSTIT, DeletePostItMessage.class);

      put(MessageCode.GET_ONLINE_COUNT, GetOnlineCountMessage.class);
    }
  };

  private final Logger logger = LogManager.getLogger(ClientHandler.class);

  private Socket client;
  private SafeOnlineCounter onlineCounter;
  private SafeBoardUpdatesCounter boardUpdatesCounter;
  private SafeBoardUpdatesShared boardUpdatesShared = new SafeBoardUpdatesShared();

  public ClientHandler(Socket socket, SafeOnlineCounter onlineCounter,
      SafeBoardUpdatesCounter boardUpdatesCounter) {
    this.client = socket;
    this.onlineCounter = onlineCounter;
    this.boardUpdatesCounter = boardUpdatesCounter;
  }

  @Override
  public void run() {
    try {
      logger.debug("[Client Handler Thread] Connected to "
          + client.getInetAddress().getHostAddress() + " port " + client.getPort() + "!");

      // safely increment the online counter
      this.onlineCounter.increment();

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

      boardUpdatesShared.printTotalStats(boardUpdatesCounter.getUpdates(), boardUpdatesCounter.getNumberPostIts(),
          boardUpdatesCounter.getNumberUpdatesPostIts(), boardUpdatesCounter.getNumberDeletesPostIts(),
          boardUpdatesCounter.getNumberArchivedBoards());

      logger.debug("Connection closed.");

      output.close();
      input.close();
    } catch (IOException | ClassNotFoundException e) {
      // ? don't show stack trace to make the app usable
      // logger.error("\n[Client Handler Thread] Error", e);
    }

    // safely decrement the online counter
    this.onlineCounter.decrement();
  }

  private void processMessage(ProtocolMessage message, DataOutputStream output) throws IOException {
    Message handleMessage;

    Class<? extends Message> clazz = MESSAGE_MAP.get(message.getCode());

    if (clazz == null) {
      handleMessage = new BadRequestMessage(output, client);
    } else {
      try {
        handleMessage = clazz.getDeclaredConstructor(ProtocolMessage.class, DataOutputStream.class,
            Socket.class, SafeOnlineCounter.class, SafeBoardUpdatesCounter.class).newInstance(
                message, output, this.client, this.onlineCounter, this.boardUpdatesCounter);
      } catch (Exception e) {
        logger.error("\n[Client Handler Thread] Error", e);
        return;
      }
    }

    handleMessage.handle();
  }
}
