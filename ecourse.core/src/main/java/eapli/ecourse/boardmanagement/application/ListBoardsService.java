package eapli.ecourse.boardmanagement.application;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import org.hibernate.Hibernate;
import eapli.ecourse.boardmanagement.domain.Board;
import eapli.ecourse.boardmanagement.domain.BoardID;
import eapli.ecourse.boardmanagement.dto.BoardDTO;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;

public class ListBoardsService {
  private BoardRepository boardRepo;

  public ListBoardsService(BoardRepository boardRepo) {
    this.boardRepo = boardRepo;
  }

  public BoardDTO ofIdentity(BoardID boardId) {
    final Board board = boardRepo.ofIdentity(boardId).orElseThrow(
        () -> new IllegalArgumentException("There is no board with the given id: " + boardId));
    return board.toDto();
  }

  public Iterable<BoardDTO> userBoards(Username username) {
    final Iterable<Board> boardsList = boardRepo.findAllBoardsCreatedByUser(username);
    return toDto(boardsList);
  }

  public Iterable<BoardDTO> userAccessibleBoards(Username username) {
    final Iterable<Board> boardsList = boardRepo.findAllBoardsAccessibleByUser(username);
    return toDto(boardsList);
  }

  public Iterable<BoardDTO> userWritableBoards(Username username) {
    Iterable<Board> boards = boardRepo.findAllActiveBoardsWithUserWritePermission(username);
    return toDto(boards);
  }

  public static void eagerLoad(BoardDTO boardDto) {
    Hibernate.initialize(boardDto.getRows());
    Hibernate.initialize(boardDto.getColumns());
    Hibernate.initialize(boardDto.getPermissions());
  }

  public static void eagerLoad(Iterable<BoardDTO> boards) {
    boards.forEach(ListBoardsService::eagerLoad);
  }

  private Iterable<BoardDTO> toDto(Iterable<Board> list) {
    return StreamSupport.stream(list.spliterator(), true).map(Board::toDto)
        .collect(Collectors.toUnmodifiableList());
  }
}
