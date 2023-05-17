package eapli.ecourse.boardmanagement.application;

import java.util.Map;

import eapli.ecourse.boardmanagement.domain.Board;
import eapli.ecourse.boardmanagement.domain.BoardBuilder;
import eapli.ecourse.boardmanagement.domain.PermissionType;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

@UseCaseController
public class CreateBoardController {

  private BoardRepository boardRepo;
  private UserManagementService userSvc;

  public CreateBoardController(BoardRepository boardRepo, UserManagementService userSvc) {
    this.boardRepo = boardRepo;
    this.userSvc = userSvc;
  }

  public Board createBoard(final String title, final Map<SystemUser, PermissionType> permissions,
      final Map<String, Integer> columns, final Map<String, Integer> rows, final String id, final SystemUser user) {

    Preconditions.noneNull(title, columns, rows, user);

    BoardBuilder boardBuilder = new BoardBuilder().withTitle(title).withUser(user).withPermissions(permissions)
        .withColumns(columns).withRows(rows);

    if (id != null)
      boardBuilder.withId(id);

    Board board = boardBuilder.build();

    // if (boardRepo.containsOfIdentity(board.identity()))
    // throw new IllegalStateException("There is already a board with that id.");

    return saveBoard(board);

  }

  public Iterable<SystemUser> listAllUsers() {
    return userSvc.allUsers();
  }

  public Board saveBoard(Board board) {
    if (board == null)
      throw new IllegalArgumentException();

    return boardRepo.save(board);
  }

}
