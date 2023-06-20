package eapli.ecourse.daemon.board.messages;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import eapli.ecourse.boardmanagement.dto.BoardDTO;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.common.board.EventListener;
import eapli.ecourse.common.board.SafeBoardUpdatesCounter;
import eapli.ecourse.common.board.SafeOnlineCounter;
import eapli.ecourse.common.board.protocol.MessageCode;
import eapli.ecourse.common.board.protocol.ProtocolMessage;
import eapli.ecourse.daemon.board.clientstate.ClientState;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.postitmanagement.application.UndoPostItController;
import eapli.ecourse.postitmanagement.domain.PostItID;
import eapli.ecourse.postitmanagement.dto.PostItDTO;
import eapli.ecourse.postitmanagement.repositories.PostItRepository;
import eapli.ecourse.usermanagement.dto.UserDTO;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.Username;

/**
 * Undo a post-it change.
 */
public class UndoPostItMessage extends Message {
  private final TransactionalContext ctx;
  private final PostItRepository postItRepository;
  private final BoardRepository boardRepository;
  private final UndoPostItController ctrl;

  public UndoPostItMessage(ProtocolMessage protocolMessage, DataOutputStream output, Socket socket,
      SafeOnlineCounter onlineCounter, SafeBoardUpdatesCounter boardUpdatesCounter,
      EventListener eventListener) {
    super(protocolMessage, output, socket, onlineCounter, boardUpdatesCounter, eventListener);

    this.ctx = PersistenceContext.repositories().newTransactionalContext();
    this.postItRepository = PersistenceContext.repositories().postIts(this.ctx);
    this.boardRepository = PersistenceContext.repositories().boards();
    this.ctrl = new UndoPostItController(this.ctx, this.postItRepository, this.boardRepository);
  }

  @Override
  public void handle() throws IOException {
    ClientState clientState = ClientState.getInstance();

    // ignore requests from unauthenticated clients
    if (!clientState.getCredentialStore().isAuthenticated())
      return;

    String postItIdStr = request.getStringifiedPayload();

    if (postItIdStr == null) {
      send(new ProtocolMessage(MessageCode.ERR, "Bad Request"));
      return;
    }

    PostItID postItId = PostItID.valueOf(postItIdStr);

    if (!ctrl.postItExists(postItId)) {
      send(new ProtocolMessage(MessageCode.ERR, "Post-it not found"));
      return;
    }

    UserDTO user = clientState.getCredentialStore().getUser();
    Username username = Username.valueOf(user.getUsername());

    // check if is owner of the post-it and has write permission to the board
    if (!ctrl.canEditPostIt(postItId, username)) {
      send(new ProtocolMessage(MessageCode.ERR, "Unauthorized"));
      return;
    }

    // check if board is archived
    if (ctrl.isPostItBoardArchived(postItId)) {
      send(new ProtocolMessage(MessageCode.ERR, "Board is archived"));
      return;
    }

    PostItDTO p = ctrl.ofIdentity(postItId);

    if (!p.getCoordinates().equals(p.getPrevious().getCoordinates())
        && !ctrl.validateCoordinates(p.getBoard().getId(), p.getPrevious().getCoordinates().getX(),
            p.getPrevious().getCoordinates().getY())) {
      send(new ProtocolMessage(MessageCode.ERR,
          "Cannot undo post-it because the previous post-it cell is currently unavailable."));
      return;
    }

    ctrl.undoPostIt(postItId);

    this.boardUpdatesCounter.incrementNumberUpdatesPostIts(Thread.currentThread().getName());

    send(new ProtocolMessage(MessageCode.UNDO_POSTIT));

    BoardDTO board = p.getBoard();
    String notification = String.format("%s undid a change of a post-it in board %s.",
        user.getUsername(), board.getTitle());
    eventListener.publish(board.getId().toString(),
        new ProtocolMessage(MessageCode.NOTIFICATION, notification));
  }
}
