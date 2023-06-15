package eapli.ecourse.daemon.board.messages;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Optional;
import eapli.ecourse.common.board.SafeOnlineCounter;
import eapli.ecourse.common.board.protocol.MessageCode;
import eapli.ecourse.common.board.protocol.ProtocolMessage;
import eapli.ecourse.daemon.board.clientstate.ClientState;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.postitmanagement.domain.PostIt;
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

  public UndoPostItMessage(ProtocolMessage protocolMessage, DataOutputStream output, Socket socket,
      SafeOnlineCounter onlineCounter) {
    super(protocolMessage, output, socket, onlineCounter);

    this.ctx = PersistenceContext.repositories().newTransactionalContext();
    this.postItRepository = PersistenceContext.repositories().postIts(this.ctx);
  }

  @Override
  public void handle() throws IOException {
    ClientState clientState = ClientState.getInstance();

    // ignore requests from unauthenticated clients
    if (!clientState.getCredentialStore().isAuthenticated())
      return;

    String postItId = protocolMessage.getStringifiedPayload();

    if (postItId == null) {
      send(new ProtocolMessage(MessageCode.ERR, "Bad Request"));
      return;
    }

    Optional<PostIt> p = postItRepository.ofIdentity(PostItID.valueOf(postItId));

    if (p.isEmpty()) {
      send(new ProtocolMessage(MessageCode.ERR, "Post-it not found"));
      return;
    }

    PostIt postIt = p.get();

    UserDTO user = clientState.getCredentialStore().getUser();
    Username username = Username.valueOf(user.getUsername());

    if (!postIt.board().canWrite(username)) {
      send(new ProtocolMessage(MessageCode.ERR, "Unauthorized"));
      return;
    }

    PostIt previous = postIt.previous();
    previous.toggleIsLatest();

    postIt.delete();

    ctx.beginTransaction();

    postItRepository.save(postIt);
    postItRepository.save(previous);

    ctx.commit();

    send(new ProtocolMessage(MessageCode.UNDO_POSTIT));
  }
}
