package eapli.ecourse.daemon.board.messages;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;
import java.util.stream.StreamSupport;
import javax.json.JsonObject;
import eapli.ecourse.boardmanagement.domain.Board;
import eapli.ecourse.boardmanagement.domain.BoardID;
import eapli.ecourse.boardmanagement.domain.PermissionType;
import eapli.ecourse.boardmanagement.domain.UserPermission;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.common.board.protocol.MessageCode;
import eapli.ecourse.common.board.protocol.ProtocolMessage;
import eapli.ecourse.daemon.board.clientstate.ClientState;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;

/**
 * Modify another user's permission to a board.
 */
public class ShareBoardMessage extends Message {
  private final BoardRepository boardRepository;
  private final UserManagementService userSvc;

  private static final Map<String, PermissionType> permissionMap = new HashMap<>() {
    {
      put("read", PermissionType.read());
      put("write", PermissionType.write());
    }
  };

  public ShareBoardMessage(ProtocolMessage protocolMessage, DataOutputStream output,
      Socket socket) {
    super(protocolMessage, output, socket);

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
    String newPermissionStr = json.getString("permission");

    if (boardId == null || username == null || newPermissionStr == null) {
      send(new ProtocolMessage(MessageCode.ERR, "Bad Request"));
      return;
    }

    if (!boardRepository.ofIdentity(BoardID.valueOf(boardId)).isPresent()) {
      send(new ProtocolMessage(MessageCode.ERR, "Board not found"));
      return;
    }

    Board board = boardRepository.ofIdentity(BoardID.valueOf(boardId)).get();

    // only the board owner can do this action
    Username authenticatedUsername =
        Username.valueOf(clientState.getCredentialStore().getUser().getUsername());

    if (!board.owner().hasIdentity(authenticatedUsername)) {
      send(new ProtocolMessage(MessageCode.ERR, "Unauthorized"));
      return;
    }

    Optional<SystemUser> user = userSvc.userOfIdentity(Username.valueOf(username));

    if (!user.isPresent()) {
      send(new ProtocolMessage(MessageCode.ERR, "User not found"));
      return;
    }

    UserPermission userPermission = StreamSupport.stream(board.permissions().spliterator(), true)
        .filter(p -> p.user().equals(user.get())).findFirst().orElse(null);

    if (newPermissionStr.equals("none")) {
      // remove the permission
      board.permissions().remove(userPermission);
      board = boardRepository.save(board);
      send(new ProtocolMessage(MessageCode.SHARE_BOARD, (Object) null));
      return;
    }

    PermissionType newPermission = permissionMap.get(newPermissionStr);

    if (newPermission == null) {
      send(new ProtocolMessage(MessageCode.ERR, "Invalid permission"));
      return;
    }

    if (userPermission == null) {
      Calendar createdAt = Calendar.getInstance();
      board.permissions().add(new UserPermission(createdAt, createdAt, newPermission, user.get()));
    } else {
      // modify the existing permission
      if (userPermission.permissionType().isRead()
          && newPermissionStr.toUpperCase().equals(PermissionType.Type.WRITE.name())) {
        userPermission.permissionType().changeToWrite();
      } else if (userPermission.permissionType().isWrite()
          && newPermissionStr.toUpperCase().equals(PermissionType.Type.READ.name())) {
        userPermission.permissionType().changeToRead();
      }
    }

    board = boardRepository.save(board);

    UserPermission permission = StreamSupport.stream(board.permissions().spliterator(), true)
        .filter(p -> p.user().equals(user.get())).findFirst().orElse(null);

    if (permission == null) {
      send(new ProtocolMessage(MessageCode.ERR, "Failed to share board"));
      return;
    }

    send(new ProtocolMessage(MessageCode.SHARE_BOARD, permission.toDto()));
  }
}
