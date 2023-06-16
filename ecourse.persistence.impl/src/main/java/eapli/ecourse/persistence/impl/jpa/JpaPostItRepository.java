package eapli.ecourse.persistence.impl.jpa;

import java.util.HashMap;
import java.util.Map;

import eapli.ecourse.boardmanagement.domain.BoardID;
import eapli.ecourse.postitmanagement.domain.PostIt;
import eapli.ecourse.postitmanagement.domain.PostItID;
import eapli.ecourse.postitmanagement.repositories.PostItRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.Username;
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
  public Iterable<PostIt> findLatestByBoardId(BoardID boardId) {
    return match("e.board.id = :boardId AND e.isLatest = true AND e.state = 'ACTIVE'", "boardId",
        boardId);
  }

  @Override
  public Iterable<PostIt> findLatestFromUserByBoardId(BoardID boardId, Username username) {
    Map<String, Object> params = new HashMap<>();
    params.put("boardId", boardId);
    params.put("username", username);

    return match(
        "e.board.id = :boardId AND e.owner.username = :username AND e.isLatest = true AND e.state = 'ACTIVE'",
        params);
  }

  @Override
  public Iterable<PostIt> findAllPostItsOrderedByDate(BoardID boardId) {
    return match("e.board.id = :boardId ORDER BY e.createdAt DESC", "boardId", boardId);
  }

}
