package eapli.ecourse.boardmanagement.application;

import java.util.Calendar;
import java.util.stream.StreamSupport;
import eapli.ecourse.boardmanagement.domain.Board;
import eapli.ecourse.boardmanagement.domain.BoardID;
import eapli.ecourse.boardmanagement.domain.PermissionType;
import eapli.ecourse.boardmanagement.domain.UserPermission;
import eapli.ecourse.boardmanagement.dto.BoardDTO;
import eapli.ecourse.boardmanagement.dto.UserPermissionDTO;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;

@UseCaseController
public class ShareBoardController {
  private final BoardRepository boardRepository;
  private final UserManagementService userSvc;
  private final ListBoardsService listBoardsSvc;

  public ShareBoardController(BoardRepository boardRepository, UserManagementService userSvc) {
    this.boardRepository = boardRepository;
    this.userSvc = userSvc;
    this.listBoardsSvc = new ListBoardsService(boardRepository);
  }

  public boolean boardExists(BoardID boardId) {
    return boardRepository.ofIdentity(boardId).isPresent();
  }

  public BoardDTO ofIdentity(BoardID boardId) {
    return boardRepository.ofIdentity(boardId).orElseThrow(IllegalArgumentException::new).toDto();
  }

  public boolean isBoardParticipant(BoardID boardId, Username username) {
    Board board = boardRepository.ofIdentity(boardId).orElseThrow();
    return board.participates(username);
  }

  public boolean isBoardOwner(BoardID boardId, Username username) {
    Board board = boardRepository.ofIdentity(boardId).orElseThrow();
    return board.owner().hasIdentity(username);
  }

  public boolean isBoardArchived(BoardID boardId) {
    Board board = boardRepository.ofIdentity(boardId).orElseThrow();
    return board.isArchived();
  }

  public Iterable<BoardDTO> listOwnActiveBoards(Username username) {
    return listBoardsSvc.userActiveBoards(username);
  }

  public UserPermissionDTO getUserPermission(BoardID boardId, Username username) {
    Board board = boardRepository.ofIdentity(boardId).orElseThrow();

    UserPermission userPermission = StreamSupport.stream(board.permissions().spliterator(), true)
        .filter(p -> p.user().hasIdentity(username)).findFirst().orElse(null);

    return userPermission == null ? null : userPermission.toDto();
  }

  public UserPermissionDTO addPermission(BoardID boardId, Username username,
      PermissionType permissionType) {
    Board board = boardRepository.ofIdentity(boardId).orElseThrow();
    Calendar createdAt = Calendar.getInstance();
    SystemUser user = userSvc.userOfIdentity(username).orElseThrow();
    UserPermission userPermission = new UserPermission(createdAt, createdAt, permissionType, user);
    board.permissions().add(userPermission);
    boardRepository.save(board);
    return userPermission.toDto();
  }

  public UserPermissionDTO updatePermission(BoardID boardId, Username username,
      PermissionType permissionType) {
    Board board = boardRepository.ofIdentity(boardId).orElseThrow();

    UserPermission userPermission = StreamSupport.stream(board.permissions().spliterator(), true)
        .filter(p -> p.user().hasIdentity(username)).findFirst().orElseThrow();

    // modify the existing permission
    if (userPermission.permissionType().isRead() && permissionType.isWrite())
      userPermission.permissionType().changeToWrite();
    else if (userPermission.permissionType().isWrite() && permissionType.isRead())
      userPermission.permissionType().changeToRead();

    boardRepository.save(board);

    return userPermission.toDto();
  }

  public void removePermission(BoardID boardId, Username username) {
    Board board = boardRepository.ofIdentity(boardId).orElseThrow();

    UserPermission userPermission = StreamSupport.stream(board.permissions().spliterator(), true)
        .filter(p -> p.user().hasIdentity(username)).findFirst().orElse(null);

    if (userPermission == null)
      return;

    board.permissions().remove(userPermission);
    board = boardRepository.save(board);
  }
}
