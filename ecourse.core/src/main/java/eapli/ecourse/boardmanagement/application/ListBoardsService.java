package eapli.ecourse.boardmanagement.application;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import eapli.ecourse.boardmanagement.domain.Board;
import eapli.ecourse.boardmanagement.dto.BoardDTO;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;

public class ListBoardsService {
  private BoardRepository boardRepo;

  public ListBoardsService(BoardRepository boardRepo) {
    this.boardRepo = boardRepo;
  }

  public Iterable<BoardDTO> userBoards(Username username) {
    final Iterable<Board> boardsList = boardRepo.findAllBoardsCreatedByUser(username);
    return toDto(boardsList);
  }

  private Iterable<BoardDTO> toDto(Iterable<Board> list) {
    return StreamSupport.stream(list.spliterator(), true).map(Board::toDto)
        .collect(Collectors.toUnmodifiableList());
  }
}
