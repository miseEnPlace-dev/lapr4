package eapli.ecourse.daemon.board.messages;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import eapli.ecourse.boardmanagement.application.ArchiveBoardController;
import eapli.ecourse.boardmanagement.domain.BoardID;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.common.board.SafeBoardUpdatesCounter;
import eapli.ecourse.common.board.SafeOnlineCounter;
import eapli.ecourse.common.board.protocol.MessageCode;
import eapli.ecourse.common.board.protocol.ProtocolMessage;
import eapli.ecourse.daemon.board.clientstate.ClientState;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usermanagement.dto.UserDTO;
import eapli.framework.infrastructure.authz.domain.model.Username;

/**
 * Archive a board.
 */
public class ArchiveBoardMessage extends Message {
  private final BoardRepository boardRepository;
  private final ArchiveBoardController ctrl;

  public ArchiveBoardMessage(ProtocolMessage protocolMessage, DataOutputStream output,
      Socket socket, SafeOnlineCounter onlineCounter, SafeBoardUpdatesCounter boardUpdatesCounter) {
    super(protocolMessage, output, socket, onlineCounter, boardUpdatesCounter);

    this.boardRepository = PersistenceContext.repositories().boards();
    this.ctrl = new ArchiveBoardController(boardRepository);
  }

  @Override
  public void handle() throws IOException {
    ClientState clientState = ClientState.getInstance();

    // ignore requests from unauthenticated clients
    if (!clientState.getCredentialStore().isAuthenticated())
      return;

    String boardIdStr = request.getStringifiedPayload();

    if (boardIdStr == null) {
      send(new ProtocolMessage(MessageCode.ERR, "Bad Request"));
      return;
    }

    BoardID boardId = BoardID.valueOf(boardIdStr);

    if (!ctrl.boardExists(boardId)) {
      send(new ProtocolMessage(MessageCode.ERR, "Board not found"));
      return;
    }

    UserDTO user = clientState.getCredentialStore().getUser();
    Username username = Username.valueOf(user.getUsername());

    if (!ctrl.isOwner(boardId, username)) {
      send(new ProtocolMessage(MessageCode.ERR, "Unauthorized"));
      return;
    }

    ctrl.archiveBoard(boardId);

    this.boardUpdatesCounter.increment();

    send(new ProtocolMessage(MessageCode.ARCHIVE_BOARD));
  }
}
