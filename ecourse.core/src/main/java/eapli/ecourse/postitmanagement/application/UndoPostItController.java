package eapli.ecourse.postitmanagement.application;

import eapli.ecourse.postitmanagement.domain.PostIt;
import eapli.ecourse.postitmanagement.domain.PostItID;
import eapli.ecourse.postitmanagement.repositories.PostItRepository;
import eapli.framework.application.UseCaseController;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.Username;

@UseCaseController
public class UndoPostItController {
  private final TransactionalContext ctx;
  private final PostItRepository postItRepository;

  public UndoPostItController(TransactionalContext ctx, PostItRepository postItRepository) {
    this.ctx = ctx;
    this.postItRepository = postItRepository;
  }

  public boolean postItExists(PostItID postItId) {
    return postItRepository.ofIdentity(postItId).isPresent();
  }

  public boolean canEditPostIt(PostItID postItId, Username username) {
    PostIt postIt = postItRepository.ofIdentity(postItId).orElseThrow();

    // check if is owner of the post-it and has write permission to the board or is board's owner
    return (postIt.board().canWrite(username) && postIt.owner().hasIdentity(username))
        || postIt.board().owner().hasIdentity(username);
  }

  public boolean isPostItBoardArchived(PostItID postItID) {
    PostIt postIt = postItRepository.ofIdentity(postItID).orElseThrow();
    return postIt.board().isArchived();
  }

  public void undoPostIt(PostItID postItId) {
    PostIt postIt = postItRepository.ofIdentity(postItId).orElseThrow();
    PostIt previous = postIt.previous();

    previous.toggleIsLatest();

    ctx.beginTransaction();

    postItRepository.delete(postIt);
    postItRepository.save(previous);

    ctx.commit();
  }
}
