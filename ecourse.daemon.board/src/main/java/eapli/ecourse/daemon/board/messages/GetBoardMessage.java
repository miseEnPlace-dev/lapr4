package eapli.ecourse.daemon.board.messages;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Optional;
import org.hibernate.Hibernate;
import eapli.ecourse.boardmanagement.domain.Board;
import eapli.ecourse.boardmanagement.domain.BoardID;
import eapli.ecourse.boardmanagement.dto.BoardDTO;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.common.board.dto.UserDTO;
import eapli.ecourse.common.board.protocol.MessageCode;
import eapli.ecourse.common.board.protocol.ProtocolMessage;
import eapli.ecourse.daemon.board.clientstate.ClientState;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.domain.model.Username;

public class GetBoardMessage extends Message {
  private BoardRepository boardRepo;

  public GetBoardMessage(ProtocolMessage protocolMessage, DataOutputStream output, Socket socket) {
    super(protocolMessage, output, socket);
    this.boardRepo = PersistenceContext.repositories().boards();
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

    // make sure the board is fully loaded before sending
    BoardDTO boardDto = b.get().toDto();
    Hibernate.initialize(boardDto.getRows());
    Hibernate.initialize(boardDto.getColumns());
    Hibernate.initialize(boardDto.getPermissions());

    send(new ProtocolMessage(MessageCode.GET_BOARD, boardDto));
  }
}
