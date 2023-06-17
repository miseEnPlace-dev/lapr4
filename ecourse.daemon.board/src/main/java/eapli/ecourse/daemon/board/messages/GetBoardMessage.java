package eapli.ecourse.daemon.board.messages;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Optional;
import javax.json.JsonStructure;
import javax.json.JsonValue.ValueType;
import eapli.ecourse.boardmanagement.application.ListBoardsService;
import eapli.ecourse.boardmanagement.domain.Board;
import eapli.ecourse.boardmanagement.domain.BoardID;
import eapli.ecourse.boardmanagement.dto.BoardDTO;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.common.board.SafeBoardUpdatesCounter;
import eapli.ecourse.common.board.SafeOnlineCounter;
import eapli.ecourse.common.board.dto.BoardWithPostItsDTO;
import eapli.ecourse.common.board.protocol.MessageCode;
import eapli.ecourse.common.board.protocol.ProtocolMessage;
import eapli.ecourse.daemon.board.clientstate.ClientState;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.postitmanagement.application.ListPostItService;
import eapli.ecourse.postitmanagement.dto.PostItDTO;
import eapli.ecourse.usermanagement.dto.UserDTO;
import eapli.framework.infrastructure.authz.domain.model.Username;

public class GetBoardMessage extends Message {
  private BoardRepository boardRepo;
  private ListPostItService listPostItsSvc;

  public GetBoardMessage(ProtocolMessage protocolMessage, DataOutputStream output, Socket socket,
      SafeOnlineCounter onlineCounter, SafeBoardUpdatesCounter boardUpdatesCounter) {
    super(protocolMessage, output, socket, onlineCounter, boardUpdatesCounter);
    this.boardRepo = PersistenceContext.repositories().boards();
    this.listPostItsSvc = new ListPostItService(PersistenceContext.repositories().postIts());
  }

  @Override
  public void handle() throws IOException {
    ClientState clientState = ClientState.getInstance();

    // ignore requests from unauthenticated clients
    if (!clientState.getCredentialStore().isAuthenticated())
      return;

    JsonStructure json = request.getPayloadAsJson();

    // check if json is valid
    if (json == null || !json.getValueType().equals(ValueType.OBJECT)) {
      send(new ProtocolMessage(MessageCode.ERR, "Bad Request"));
      return;
    }

    String boardIdStr = json.asJsonObject().getString("boardId");

    if (boardIdStr == null) {
      send(new ProtocolMessage(MessageCode.ERR, "Bad Request"));
      return;
    }

    UserDTO user = clientState.getCredentialStore().getUser();
    Username username = Username.valueOf(user.getUsername());

    Optional<Board> b = boardRepo.ofIdentity(BoardID.valueOf(boardIdStr));

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
    ListBoardsService.eagerLoad(boardDto);

    Iterable<PostItDTO> list = listPostItsSvc.latestOfBoard(b.get().identity());
    BoardWithPostItsDTO boardWithPostItsDto = new BoardWithPostItsDTO(boardDto, list);

    if (json.asJsonObject().containsKey("hash")) {
      try {
        String hashStr = json.asJsonObject().getString("hash");
        int userHash = Integer.parseInt(hashStr);

        int currentHash = boardWithPostItsDto.hashCode();

        if (userHash == currentHash) {
          send(new ProtocolMessage(MessageCode.NOT_MODIFIED));
          return;
        }
      } catch (NumberFormatException e) {
      }
    }

    send(new ProtocolMessage(MessageCode.GET_BOARD, boardWithPostItsDto));
  }
}
