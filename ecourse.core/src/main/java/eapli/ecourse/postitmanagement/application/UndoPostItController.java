package eapli.ecourse.postitmanagement.application;

import eapli.ecourse.boardmanagement.domain.BoardID;
import eapli.ecourse.boardmanagement.dto.BoardDTO;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.postitmanagement.domain.PostIt;
import eapli.ecourse.postitmanagement.domain.PostItID;
import eapli.ecourse.postitmanagement.dto.PostItDTO;
import eapli.ecourse.postitmanagement.repositories.PostItRepository;
import eapli.framework.application.UseCaseController;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.Username;

@UseCaseController
public class UndoPostItController {
  private final TransactionalContext ctx;
  private final PostItRepository postItRepository;
  private final BoardService boardSvc;

  public UndoPostItController(TransactionalContext ctx, PostItRepository postItRepository,
      BoardRepository boardRepository) {
    this.ctx = ctx;
    this.postItRepository = postItRepository;
    this.boardSvc = new BoardService(boardRepository, postItRepository);
  }

  public boolean postItExists(PostItID postItId) {
    return postItRepository.ofIdentity(postItId).isPresent();
  }

  public boolean canEditPostIt(PostItID postItId, Username username) {
    PostIt postIt = postItRepository.ofIdentity(postItId).orElseThrow();

    // check if is owner of the post-it and has write permission to the board
    return (postIt.board().canWrite(username) && postIt.owner().hasIdentity(username));
    // || postIt.board().owner().hasIdentity(username);
  }

  public boolean isPostItBoardArchived(PostItID postItID) {
    PostIt postIt = postItRepository.ofIdentity(postItID).orElseThrow();
    return postIt.board().isArchived();
  }

  public boolean validateCoordinates(BoardID boardID, int x, int y) {
    return boardSvc.isCellAvailable(boardID, x, y);
  }

  public PostItDTO ofIdentity(PostItID id) {
    return postItRepository.ofIdentity(id).orElseThrow(IllegalArgumentException::new).toDto();
  }

  public void undoPostIt(PostItID postItId) {
    PostIt postIt = postItRepository.ofIdentity(postItId).orElseThrow();
    PostIt previous = postIt.previous();

    if (previous == null)
      throw new IllegalStateException("Post-It has no previous version");

    previous.toggleIsLatest();

    ctx.beginTransaction();

    postItRepository.delete(postIt);
    postItRepository.save(previous);

    ctx.commit();
  }
}
