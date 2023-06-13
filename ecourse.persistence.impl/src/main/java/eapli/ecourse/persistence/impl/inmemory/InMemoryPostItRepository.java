package eapli.ecourse.persistence.impl.inmemory;

import eapli.ecourse.boardmanagement.domain.BoardID;
import eapli.ecourse.postitmanagement.domain.PostIt;
import eapli.ecourse.postitmanagement.domain.PostItID;
import eapli.ecourse.postitmanagement.repositories.PostItRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryPostItRepository extends InMemoryDomainRepository<PostIt, PostItID>
    implements PostItRepository {

  @Override
  public Iterable<PostIt> findAllByBoardId(BoardID boardId) {
    return match(e -> e.board().identity().equals(boardId));
  }

  @Override
  public Iterable<PostIt> findLatestByBoardId(BoardID boardId) {
    return match(e -> e.board().identity().equals(boardId) && e.isLatest());
  }

  @Override
  public Iterable<PostIt> findLatestPostItsCreatedByUser(BoardID boardId, Username username) {
    return match(e -> e.board().identity().equals(boardId) && e.isLatest() && e.owner().identity().equals(username)
        && e.isLatest());
  }
}
