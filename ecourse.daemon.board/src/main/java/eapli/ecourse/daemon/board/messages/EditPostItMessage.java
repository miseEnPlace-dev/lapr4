package eapli.ecourse.daemon.board.messages;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import javax.json.JsonObject;
import eapli.ecourse.common.board.SafeOnlineCounter;
import eapli.ecourse.common.board.protocol.MessageCode;
import eapli.ecourse.common.board.protocol.ProtocolMessage;
import eapli.ecourse.daemon.board.clientstate.ClientState;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.postitmanagement.application.EditPostItController;
import eapli.ecourse.postitmanagement.domain.PostItID;
import eapli.ecourse.postitmanagement.repositories.PostItRepository;
import eapli.ecourse.usermanagement.dto.UserDTO;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.Username;

/**
 * Edit a post-it.
 */
public class EditPostItMessage extends Message {
  private final TransactionalContext ctx;
  private final PostItRepository postItRepository;
  private final EditPostItController ctrl;

  public EditPostItMessage(ProtocolMessage protocolMessage, DataOutputStream output, Socket socket,
      SafeOnlineCounter onlineCounter) {
    super(protocolMessage, output, socket, onlineCounter);

    this.ctx = PersistenceContext.repositories().newTransactionalContext();
    this.postItRepository = PersistenceContext.repositories().postIts(this.ctx);
    this.ctrl = new EditPostItController(this.ctx, this.postItRepository);
  }

  @Override
  public void handle() throws IOException {
    ClientState clientState = ClientState.getInstance();

    // ignore requests from unauthenticated clients
    if (!clientState.getCredentialStore().isAuthenticated())
      return;

    JsonObject json = protocolMessage.getPayloadAsJson();

    String postItIdStr = json.getString("postItId");

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

    // ...
    // ctrl.editPostIt(postItId);

    send(new ProtocolMessage(MessageCode.EDIT_POSTIT));
  }
}
