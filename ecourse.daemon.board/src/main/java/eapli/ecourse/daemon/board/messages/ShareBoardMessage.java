package eapli.ecourse.daemon.board.messages;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.json.JsonObject;
import eapli.ecourse.boardmanagement.application.ShareBoardController;
import eapli.ecourse.boardmanagement.domain.BoardID;
import eapli.ecourse.boardmanagement.domain.PermissionType;
import eapli.ecourse.boardmanagement.dto.UserPermissionDTO;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.common.board.SafeBoardUpdatesCounter;
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
 * Modify another user's permission to a board.
 */
public class ShareBoardMessage extends Message {
  private final BoardRepository boardRepository;
  private final UserManagementService userSvc;
  private final ShareBoardController ctrl;

  private static final Map<String, PermissionType> permissionMap = new HashMap<>() {
    {
      put("read", PermissionType.read());
      put("write", PermissionType.write());
    }
  };

  public ShareBoardMessage(ProtocolMessage protocolMessage, DataOutputStream output, Socket socket,
      SafeOnlineCounter onlineCounter, SafeBoardUpdatesCounter boardUpdatesCounter) {
    super(protocolMessage, output, socket, onlineCounter, boardUpdatesCounter);

    this.boardRepository = PersistenceContext.repositories().boards();
    this.userSvc = AuthzRegistry.userService();
    this.ctrl = new ShareBoardController(boardRepository, userSvc);
  }

  @Override
  public void handle() throws IOException {
    ClientState clientState = ClientState.getInstance();

    // ignore requests from unauthenticated clients
    if (!clientState.getCredentialStore().isAuthenticated())
      return;

    JsonObject json = protocolMessage.getPayloadAsJson();

    String boardIdStr = json.getString("boardId");
    String usernameStr = json.getString("username");
    String newPermissionStr = json.getString("permission");

    if (boardIdStr == null || usernameStr == null || newPermissionStr == null) {
      send(new ProtocolMessage(MessageCode.ERR, "Bad Request"));
      return;
    }

    BoardID boardId = BoardID.valueOf(boardIdStr);
    Username authUsername = Username.valueOf(clientState.getCredentialStore().getUser().getUsername());

    // verify if the board with the given id exists
    if (!ctrl.boardExists(boardId)) {
      send(new ProtocolMessage(MessageCode.ERR, "Board not found"));
      return;
    }

    // only the board owner can do this action
    // check if the authenticated user is the owner of the board
    if (!ctrl.isBoardOwner(boardId, authUsername)) {
      send(new ProtocolMessage(MessageCode.ERR, "Unauthorized"));
      return;
    }

    // verify if the user with the given username exists
    Optional<SystemUser> user = userSvc.userOfIdentity(Username.valueOf(usernameStr));

    if (!user.isPresent()) {
      send(new ProtocolMessage(MessageCode.ERR, "User not found"));
      return;
    }

    Username username = Username.valueOf(usernameStr);

    // check if the user is owner of the board
    if (ctrl.isBoardOwner(boardId, username)) {
      send(new ProtocolMessage(MessageCode.ERR, "User is the owner of the board"));
      return;
    }

    // check if board is archived
    if (ctrl.isBoardArchived(boardId)) {
      send(new ProtocolMessage(MessageCode.ERR, "Board is archived"));
      return;
    }

    // update the permission
    if (newPermissionStr.equals("none")) {
      // remove the permission
      ctrl.removePermission(boardId, username);
      send(new ProtocolMessage(MessageCode.SHARE_BOARD, (Object) null));
      return;
    }

    PermissionType newPermission = permissionMap.get(newPermissionStr);

    if (newPermission == null) {
      send(new ProtocolMessage(MessageCode.ERR, "Invalid permission"));
      return;
    }

    // get current permission
    UserPermissionDTO userPermission = ctrl.getUserPermission(boardId, username);

    if (userPermission == null)
      userPermission = ctrl.addPermission(boardId, username, newPermission);
    else
      userPermission = ctrl.updatePermission(boardId, username, newPermission);

    send(new ProtocolMessage(MessageCode.SHARE_BOARD, userPermission));
  }
}
