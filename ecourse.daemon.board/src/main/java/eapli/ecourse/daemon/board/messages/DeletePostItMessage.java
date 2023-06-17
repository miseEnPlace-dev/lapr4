package eapli.ecourse.daemon.board.messages;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.common.board.SafeBoardUpdatesCounter;
import eapli.ecourse.common.board.SafeOnlineCounter;
import eapli.ecourse.common.board.protocol.MessageCode;
import eapli.ecourse.common.board.protocol.ProtocolMessage;
import eapli.ecourse.daemon.board.clientstate.ClientState;
import eapli.ecourse.daemon.board.clientstate.CredentialStore;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.postitmanagement.application.ChangePostItController;
import eapli.ecourse.postitmanagement.domain.PostIt;
import eapli.ecourse.postitmanagement.domain.PostItID;
import eapli.ecourse.postitmanagement.repositories.PostItRepository;
import eapli.ecourse.usermanagement.dto.UserDTO;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.Username;

public class DeletePostItMessage extends Message {
  private CredentialStore credentialStore;

  private TransactionalContext ctx;
  private ChangePostItController ctrl;

  private BoardRepository boardRepository;
  private PostItRepository postItRepository;

  public DeletePostItMessage(ProtocolMessage protocolMessage, DataOutputStream output, Socket socket,
      SafeOnlineCounter onlineCounter, SafeBoardUpdatesCounter boardUpdatesCounter) {
    super(protocolMessage, output, socket, onlineCounter, boardUpdatesCounter);

    this.credentialStore = ClientState.getInstance().getCredentialStore();

    this.ctx = PersistenceContext.repositories().newTransactionalContext();

    this.boardRepository = PersistenceContext.repositories().boards();
    this.postItRepository = PersistenceContext.repositories().postIts();
    this.ctrl = new ChangePostItController(ctx, boardRepository, postItRepository);
  }

  @Override
  public void handle() throws IOException {
    // ignore requests from unauthenticated clients
    if (!credentialStore.isAuthenticated())
      return;

    String postItIdStr = protocolMessage.getStringifiedPayload();

    if (postItIdStr == null) {
      send(new ProtocolMessage(MessageCode.ERR, "Bad Request"));
      return;
    }

    // ? we cannot trust the client to send a valid post-it id
    // so we need to check every input

    PostItID postItId = PostItID.valueOf(postItIdStr);

    if (!ctrl.postItExists(postItId)) {
      send(new ProtocolMessage(MessageCode.ERR, "Post-it not found"));
      return;
    }

    UserDTO user = credentialStore.getUser();
    Username username = Username.valueOf(user.getUsername());

    // check if is owner of the post-it and has write permission to the board
    if (!ctrl.canEditPostIt(postItId, username)) {
      send(new ProtocolMessage(MessageCode.ERR, "Unauthorized"));
      return;
    }

    // ? we should also check if the board is archived
    if (ctrl.isPostItBoardArchived(postItId)) {
      send(new ProtocolMessage(MessageCode.ERR, "Board is archived"));
      return;
    }

    PostIt deletedPostIt = ctrl.deletePostIt(PostItID.valueOf(postItIdStr));

    if (deletedPostIt == null) {
      send(new ProtocolMessage(MessageCode.ERR, "Post-it not found"));
      return;
    }

    this.boardUpdatesCounter.increment();

    send(new ProtocolMessage(MessageCode.DELETE_POSTIT));

  }
}
