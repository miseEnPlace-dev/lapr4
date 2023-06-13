package eapli.ecourse.daemon.board.messages;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Optional;
import eapli.ecourse.boardmanagement.domain.Board;
import eapli.ecourse.boardmanagement.domain.BoardID;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.common.board.protocol.MessageCode;
import eapli.ecourse.common.board.protocol.ProtocolMessage;
import eapli.ecourse.daemon.board.clientstate.ClientState;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.postitmanagement.application.ListPostItService;
import eapli.ecourse.postitmanagement.dto.PostItDTO;
import eapli.ecourse.usermanagement.dto.UserDTO;
import eapli.framework.infrastructure.authz.domain.model.Username;

public class GetBoardPostItsMessage extends Message {
  private BoardRepository boardRepo;
  private ListPostItService listPostItsSvc;

  public GetBoardPostItsMessage(ProtocolMessage protocolMessage, DataOutputStream output,
      Socket socket) {
    super(protocolMessage, output, socket);

    this.boardRepo = PersistenceContext.repositories().boards();
    this.listPostItsSvc = new ListPostItService(PersistenceContext.repositories().postIts());
  }

  @Override
  public void handle() throws IOException {
    ClientState clientState = ClientState.getInstance();

    // ignore requests from unauthenticated clients
    if (!clientState.getCredentialStore().isAuthenticated())
      return;

    UserDTO user = clientState.getCredentialStore().getUser();
    Username username = Username.valueOf(user.getUsername());

    Optional<Board> b =
        boardRepo.ofIdentity(BoardID.valueOf(protocolMessage.getStringifiedPayload()));

    if (b.isEmpty()) {
      send(new ProtocolMessage(MessageCode.ERR, "Board not found"));
      return;
    }

    if (!b.get().participates(username)) {
      send(new ProtocolMessage(MessageCode.ERR, "Forbidden"));
      return;
    }

    Iterable<PostItDTO> list = listPostItsSvc.ofBoard(b.get().identity());

    send(new ProtocolMessage(MessageCode.GET_BOARD_POSTITS, list));
  }
}
