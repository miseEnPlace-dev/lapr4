package eapli.ecourse.boardmanagement.application;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import eapli.ecourse.boardmanagement.domain.UserPermission;
import eapli.ecourse.boardmanagement.dto.BoardDTO;
import eapli.ecourse.boardmanagement.dto.UserPermissionDTO;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;

@UseCaseController
public class ShareBoardController {
  private AuthorizationService authz;
  private UserManagementService userService;
  private ListBoardsService listBoardsService;

  public ShareBoardController(BoardRepository boardRepo) {
    this.authz = AuthzRegistry.authorizationService();
    this.userService = AuthzRegistry.userService();
    this.listBoardsService = new ListBoardsService(boardRepo);
  }

  public Iterable<BoardDTO> listUserBoards() {
    // get username of the logged user
    Username username = authz.session().get().authenticatedUser().username();
    return listBoardsService.userBoards(username);
  }

  public Iterable<SystemUser> listUsers() {
    return userService.allUsers();
  }

  // public void userFromEmail() {
  // // TODO
  // }

  /**
   * Get the user permission to the board.
   *
   * @param boardDTO
   * @param user
   * @return the user permission, null if no permission
   */
  public UserPermissionDTO userPermissions(BoardDTO boardDTO, SystemUser user) {
    Iterable<UserPermission> permissions = boardDTO.getPermissions();

    return StreamSupport.stream(permissions.spliterator(), true).filter(e -> e.user().equals(user))
        .map(UserPermission::toDto).collect(Collectors.toList()).get(0);
  }

  // TODO update user permissions to the board
  public void updateUserPermission(BoardDTO boardDTO, SystemUser user, UserPermission permission) {
    // ...
  }
}
