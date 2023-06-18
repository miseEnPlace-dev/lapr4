package eapli.ecourse.daemon.board.messages;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Optional;

import eapli.ecourse.boardmanagement.application.ViewBoardHistoryController;
import eapli.ecourse.boardmanagement.domain.Board;
import eapli.ecourse.boardmanagement.domain.BoardID;
import eapli.ecourse.boardmanagement.dto.BoardHistoryDTO;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.common.board.SafeBoardUpdatesCounter;
import eapli.ecourse.common.board.SafeOnlineCounter;
import eapli.ecourse.common.board.protocol.MessageCode;
import eapli.ecourse.common.board.protocol.ProtocolMessage;
import eapli.ecourse.daemon.board.clientstate.ClientState;
import eapli.ecourse.daemon.board.clientstate.CredentialStore;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.postitmanagement.repositories.PostItRepository;
import eapli.ecourse.usermanagement.dto.UserDTO;
import eapli.framework.infrastructure.authz.domain.model.Username;

public class GetPostItBoardHistoryMessage extends Message {
  private ViewBoardHistoryController ctrl;
  private CredentialStore credentialStore;

  private BoardRepository boardRepository;
  private PostItRepository postItRepository;

  public GetPostItBoardHistoryMessage(ProtocolMessage protocolMessage, DataOutputStream output,
      Socket socket, SafeOnlineCounter onlineCounter, SafeBoardUpdatesCounter boardUpdatesCounter) {
    super(protocolMessage, output, socket, onlineCounter, boardUpdatesCounter);

    this.credentialStore = ClientState.getInstance().getCredentialStore();
    this.boardRepository = PersistenceContext.repositories().boards();
    this.postItRepository = PersistenceContext.repositories().postIts();
    this.ctrl = new ViewBoardHistoryController(boardRepository, postItRepository);
  }

  @Override
  public void handle() throws IOException {
    // ignore requests from unauthenticated clients
    if (!credentialStore.isAuthenticated())
      return;

    String boardId = request.getStringifiedPayload();

    if (boardId == null) {
      send(new ProtocolMessage(MessageCode.ERR, "Bad Request"));
      return;
    }

    Optional<Board> b = boardRepository.ofIdentity(BoardID.valueOf(boardId));

    if (b.isEmpty()) {
      send(new ProtocolMessage(MessageCode.ERR, "Board not found"));
      return;
    }

    Board board = b.get();

    UserDTO user = credentialStore.getUser();
    Username username = Username.valueOf(user.getUsername());

    if (!board.participates(username)) {
      send(new ProtocolMessage(MessageCode.ERR, "Unauthorized"));
      return;
    }

    Iterable<BoardHistoryDTO> history = ctrl.listBoardPostItHistory(board.identity());
    send(new ProtocolMessage(MessageCode.GET_BOARD_POST_IT_HISTORY, history));
  }
}
