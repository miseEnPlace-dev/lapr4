package eapli.ecourse.boardmanagement.application;

import eapli.ecourse.boardmanagement.domain.BoardID;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.domain.model.Username;

@UseCaseController
public class ArchiveBoardController {
  private BoardRepository boardRepository;

  public ArchiveBoardController(BoardRepository boardRepo) {
    this.boardRepository = boardRepo;
  }

  public boolean boardExists(BoardID boardId) {
    return boardRepository.ofIdentity(boardId).isPresent();
  }

  public boolean isOwner(BoardID boardId, Username username) {
    return boardRepository.ofIdentity(boardId).map(board -> board.owner().hasIdentity(username))
        .orElse(false);
  }

  public void archiveBoard(BoardID boardId) {
    boardRepository.ofIdentity(boardId).ifPresent(board -> {
      board.toggleArchive();
      boardRepository.save(board);
    });
  }
}
