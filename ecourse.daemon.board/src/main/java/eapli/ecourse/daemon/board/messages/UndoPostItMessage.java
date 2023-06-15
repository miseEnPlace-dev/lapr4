package eapli.ecourse.daemon.board.messages;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import eapli.ecourse.common.board.SafeOnlineCounter;
import eapli.ecourse.common.board.protocol.MessageCode;
import eapli.ecourse.common.board.protocol.ProtocolMessage;
import eapli.ecourse.daemon.board.clientstate.ClientState;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.postitmanagement.application.UndoPostItController;
import eapli.ecourse.postitmanagement.domain.PostItID;
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
  private final UndoPostItController ctrl;

  public UndoPostItMessage(ProtocolMessage protocolMessage, DataOutputStream output, Socket socket,
      SafeOnlineCounter onlineCounter) {
    super(protocolMessage, output, socket, onlineCounter);

    this.ctx = PersistenceContext.repositories().newTransactionalContext();
    this.postItRepository = PersistenceContext.repositories().postIts(this.ctx);
    this.ctrl = new UndoPostItController(this.ctx, this.postItRepository);
  }

  @Override
  public void handle() throws IOException {
    ClientState clientState = ClientState.getInstance();

    // ignore requests from unauthenticated clients
    if (!clientState.getCredentialStore().isAuthenticated())
      return;

    String postItIdStr = protocolMessage.getStringifiedPayload();

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

    // check if is owner of the post-it and has write permission to the board or is board's owner
    if (!ctrl.canEditPostIt(postItId, username)) {
      send(new ProtocolMessage(MessageCode.ERR, "Unauthorized"));
      return;
    }

    ctrl.undoPostIt(postItId);

    send(new ProtocolMessage(MessageCode.UNDO_POSTIT));
  }
}
