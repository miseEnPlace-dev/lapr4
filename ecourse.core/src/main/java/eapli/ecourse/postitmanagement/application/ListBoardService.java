package eapli.ecourse.postitmanagement.application;

import java.util.stream.StreamSupport;

import eapli.ecourse.boardmanagement.domain.Board;
import eapli.ecourse.boardmanagement.dto.BoardDTO;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class ListBoardService {

  BoardRepository boardRepository;

  public ListBoardService(BoardRepository boardRepository) {
    this.boardRepository = boardRepository;
  }

  public Iterable<BoardDTO> listBoardsThatUserCanWrite(SystemUser user) {
    Iterable<Board> boards = boardRepository.findAllActiveBoardsWithUserWritePermission(user.username());

    return convertToDTO(boards);
  }

  private Iterable<BoardDTO> convertToDTO(Iterable<Board> enrollments) {
    return StreamSupport.stream(enrollments.spliterator(), true)
        .map(Board::toDto)
        .collect(java.util.stream.Collectors.toUnmodifiableList());
  }

}
