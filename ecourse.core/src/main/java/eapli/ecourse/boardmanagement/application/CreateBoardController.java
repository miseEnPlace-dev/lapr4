package eapli.ecourse.boardmanagement.application;

import java.util.Map;

import eapli.ecourse.boardmanagement.domain.Board;
import eapli.ecourse.boardmanagement.domain.BoardBuilder;
import eapli.ecourse.boardmanagement.domain.PermissionType;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

@UseCaseController
public class CreateBoardController {

  private BoardRepository boardRepo;
  private UserManagementService userSvc;
  private AuthorizationService authz;

  public CreateBoardController(BoardRepository boardRepo, UserManagementService userSvc, AuthorizationService authz) {
    this.boardRepo = boardRepo;
    this.userSvc = userSvc;
    this.authz = authz;
  }

  public Board createBoard(final String title, final Map<SystemUser, PermissionType> permissions,
      final Map<String, Integer> columns, final Map<String, Integer> rows) {

    Preconditions.noneNull(title, columns, rows);

    SystemUser user = authz.loggedinUserWithPermissions(ClientRoles.MANAGER,
        ClientRoles.POWER_USER, ClientRoles.STUDENT, ClientRoles.TEACHER).orElseThrow();

    Board board = new BoardBuilder().withTitle(title).withUser(user).withPermissions(permissions)
        .withColumns(columns).withRows(rows).build();

    if (boardRepo.containsOfIdentity(board.identity()))
      throw new IllegalStateException("There is already a board with that id.");

    return saveBoard(board);
  }

  public Iterable<SystemUser> listAllUsers() {
    return userSvc.allUsers();
  }

  private Board saveBoard(Board board) {
    if (board == null)
      throw new IllegalArgumentException("Board cannot be null.");

    return boardRepo.save(board);
  }
}
