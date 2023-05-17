package eapli.ecourse.persistence.impl.jpa;

import eapli.ecourse.boardmanagment.domain.Board;
import eapli.ecourse.boardmanagment.domain.BoardID;
import eapli.ecourse.boardmanagment.repositories.BoardRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaBoardRepository extends JpaAutoTxRepository<Board, BoardID, BoardID>
    implements BoardRepository {
  JpaBoardRepository(final TransactionalContext autoTx) {
    super(autoTx, "id");
  }

  JpaBoardRepository(final String puName) {
    super(puName, "id");
  }

  @Override
  public Iterable<Board> findAllBoardsCreatedByUser(Username user) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'findAllBoardsCreatedByUser'");
  }
}
