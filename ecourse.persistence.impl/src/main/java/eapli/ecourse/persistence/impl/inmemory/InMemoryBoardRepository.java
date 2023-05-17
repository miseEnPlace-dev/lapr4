package eapli.ecourse.persistence.impl.inmemory;

import eapli.ecourse.boardmanagment.domain.Board;
import eapli.ecourse.boardmanagment.domain.BoardID;
import eapli.ecourse.boardmanagment.repositories.BoardRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryBoardRepository extends InMemoryDomainRepository<Board, BoardID>
    implements BoardRepository {

  static {
    InMemoryInitializer.init();
  }

  @Override
  public Iterable<Board> findAllBoardsCreatedByUser(Username userName) {
    return match(e -> e.user().username().equals(userName));
  }

}
