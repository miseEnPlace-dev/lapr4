package eapli.ecourse.daemon.board.messages;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import eapli.ecourse.boardmanagement.repositories.BoardRepository;
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

public class DeletePostItMessage extends Message {
  private CredentialStore credentialStore;

  private ChangePostItController ctrl;

  private BoardRepository boardRepository;
  private PostItRepository postItRepository;

  public DeletePostItMessage(ProtocolMessage protocolMessage, DataOutputStream output, Socket socket,
      SafeOnlineCounter onlineCounter) {
    super(protocolMessage, output, socket, onlineCounter);

    this.credentialStore = ClientState.getInstance().getCredentialStore();

    this.boardRepository = PersistenceContext.repositories().boards();
    this.postItRepository = PersistenceContext.repositories().postIts();
    this.ctrl = new ChangePostItController(boardRepository, postItRepository);
  }

  @Override
  public void handle() throws IOException {
    // ignore requests from unauthenticated clients
    if (!credentialStore.isAuthenticated())
      return;

    String postItId = protocolMessage.getStringifiedPayload();

    if (postItId == null) {
      send(new ProtocolMessage(MessageCode.ERR, "Bad Request"));
      return;
    }

    PostIt deletedPostIt = ctrl.deletePostIt(PostItID.valueOf(postItId));

    if (deletedPostIt == null) {
      send(new ProtocolMessage(MessageCode.ERR, "Post-it not found"));
      return;
    }

    send(new ProtocolMessage(MessageCode.CHANGE_POSTIT));

  }
}
