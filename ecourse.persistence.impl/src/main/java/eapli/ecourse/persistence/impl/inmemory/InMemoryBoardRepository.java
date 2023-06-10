package eapli.ecourse.persistence.impl.inmemory;

import eapli.ecourse.boardmanagement.domain.Board;
import eapli.ecourse.boardmanagement.domain.BoardID;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryBoardRepository extends InMemoryDomainRepository<Board, BoardID>
    implements BoardRepository {

  static {
    InMemoryInitializer.init();
  }

  @Override
  public Iterable<Board> findAllBoardsCreatedByUser(Username username) {
    return match(e -> e.owner().username().equals(username));
  }

  @Override
  public Iterable<Board> findAllActiveBoardsWithUserWritePermission(Username username) {
    return match(e -> e.canWrite(username) && !e.isArchived());
  }

  @Override
  public Iterable<Board> findAllBoardsAccessibleByUser(Username username) {
    return match(e -> e.participates(username) && !e.isArchived());
  }
}
