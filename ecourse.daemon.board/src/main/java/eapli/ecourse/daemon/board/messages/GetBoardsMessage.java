package eapli.ecourse.daemon.board.messages;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;

import eapli.ecourse.boardmanagement.application.ListBoardsService;
import eapli.ecourse.boardmanagement.dto.BoardDTO;
import eapli.ecourse.common.board.EventListener;
import eapli.ecourse.common.board.SafeBoardUpdatesCounter;
import eapli.ecourse.common.board.SafeOnlineCounter;
import eapli.ecourse.common.board.protocol.MessageCode;
import eapli.ecourse.common.board.protocol.ProtocolMessage;
import eapli.ecourse.daemon.board.clientstate.ClientState;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usermanagement.dto.UserDTO;
import eapli.framework.infrastructure.authz.domain.model.Username;

/**
 * Get boards which the user has permission to access.
 */
public class GetBoardsMessage extends Message {
  private ListBoardsService listBoardsService;

  public GetBoardsMessage(ProtocolMessage protocolMessage, DataOutputStream output, Socket socket,
      SafeOnlineCounter onlineCounter, SafeBoardUpdatesCounter boardUpdatesCounter,
      EventListener eventListener) {
    super(protocolMessage, output, socket, onlineCounter, boardUpdatesCounter, eventListener);
    this.listBoardsService = new ListBoardsService(PersistenceContext.repositories().boards());
  }

  @Override
  public void handle() throws IOException {
    ClientState clientState = ClientState.getInstance();

    // ignore requests from unauthenticated clients
    if (!clientState.getCredentialStore().isAuthenticated())
      return;

    UserDTO user = clientState.getCredentialStore().getUser();
    Username username = Username.valueOf(user.getUsername());

    Iterable<BoardDTO> boards = listBoardsService.userAccessibleBoards(username);

    // make sure the boards are fully loaded before sending
    ListBoardsService.eagerLoad(boards);

    send(new ProtocolMessage(MessageCode.GET_BOARDS, boards));
  }
}
