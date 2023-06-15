package eapli.ecourse.daemon.board.messages;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Optional;
import java.util.stream.StreamSupport;

import javax.json.JsonObject;

import eapli.ecourse.boardmanagement.domain.Board;
import eapli.ecourse.boardmanagement.domain.BoardID;
import eapli.ecourse.boardmanagement.domain.UserPermission;
import eapli.ecourse.boardmanagement.dto.UserPermissionDTO;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.common.board.SafeOnlineCounter;
import eapli.ecourse.common.board.protocol.MessageCode;
import eapli.ecourse.common.board.protocol.ProtocolMessage;
import eapli.ecourse.daemon.board.clientstate.ClientState;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;

/**
 * Get the user permissions to a specific board.
 */
public class GetUserPermissionsMessage extends Message {
  private final BoardRepository boardRepository;
  private final UserManagementService userSvc;

  public GetUserPermissionsMessage(ProtocolMessage protocolMessage, DataOutputStream output,
      Socket socket, SafeOnlineCounter onlineCounter) {
    super(protocolMessage, output, socket, onlineCounter);

    this.boardRepository = PersistenceContext.repositories().boards();
    this.userSvc = AuthzRegistry.userService();
  }

  @Override
  public void handle() throws IOException {
    ClientState clientState = ClientState.getInstance();

    // ignore requests from unauthenticated clients
    if (!clientState.getCredentialStore().isAuthenticated())
      return;

    JsonObject json = protocolMessage.getPayloadAsJson();

    String boardId = json.getString("boardId");
    String username = json.getString("username");

    if (boardId == null || username == null) {
      send(new ProtocolMessage(MessageCode.ERR, "Bad Request"));
      return;
    }

    Optional<Board> board = boardRepository.ofIdentity(BoardID.valueOf(boardId));

    if (!board.isPresent()) {
      send(new ProtocolMessage(MessageCode.ERR, "Board not found"));
      return;
    }

    // only the board owner can get the permissions of other users
    Username authenticatedUsername =
        Username.valueOf(clientState.getCredentialStore().getUser().getUsername());

    if (!board.get().owner().hasIdentity(authenticatedUsername)) {
      send(new ProtocolMessage(MessageCode.ERR, "Unauthorized"));
      return;
    }

    Optional<SystemUser> user = userSvc.userOfIdentity(Username.valueOf(username));

    if (!user.isPresent()) {
      send(new ProtocolMessage(MessageCode.ERR, "User not found"));
      return;
    }

    UserPermission permission = StreamSupport.stream(board.get().permissions().spliterator(), true)
        .filter(p -> p.user().equals(user.get())).findFirst().orElse(null);

    if (permission == null) {
      send(new ProtocolMessage(MessageCode.GET_USER_PERMISSIONS));
      return;
    }

    UserPermissionDTO permissionDTO = permission.toDto();

    send(new ProtocolMessage(MessageCode.GET_USER_PERMISSIONS, permissionDTO));
  }
}
