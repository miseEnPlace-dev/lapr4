package eapli.ecourse.persistence.impl.jpa;

import eapli.ecourse.boardmanagement.domain.BoardID;
import eapli.ecourse.postitmanagement.domain.PostIt;
import eapli.ecourse.postitmanagement.domain.PostItID;
import eapli.ecourse.postitmanagement.repositories.PostItRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaPostItRepository extends JpaAutoTxRepository<PostIt, PostItID, PostItID>
    implements PostItRepository {

  public JpaPostItRepository(final TransactionalContext autoTx) {
    super(autoTx, "id");
  }

  public JpaPostItRepository(final String puname) {
    super(puname, "id");
  }

  @Override
  public Iterable<PostIt> findAllByBoardId(BoardID boardId) {
    return match("e.board.id = :boardId", "boardId", boardId);
  }

  @Override
  public Iterable<PostIt> findLatestVersionOfBoard(BoardID boardId) {
    return match("e.board.id = :boardId AND e.isLatest = true", "boardId", boardId);
  }

}
