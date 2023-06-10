package eapli.ecourse.persistence.impl.jpa;

import javax.persistence.TypedQuery;
import org.hibernate.annotations.QueryHints;
import eapli.ecourse.boardmanagement.domain.Board;
import eapli.ecourse.boardmanagement.domain.BoardID;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
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
    // return match("e.owner.username = :username", "username", user);

    // we are using this function to send the boards to the shared board app when
    // the user asks for
    // the boards he owns. we send a list of BoardDTO serialized - the problem is
    // that when
    // retrieving the boards from the database, jpa creates a proxy for the Board
    // entity (the
    // default behavior for loading lists is lazy loading which we don't want here
    // since the client
    // does not have access to the database). we could have made the default
    // behavior eager loading
    // but that would impact the performance of the app, so we will force the
    // loading of the lists
    // here.

    // Reference: https://stackoverflow.com/a/51055523/15339625

    TypedQuery<Board> query = createQuery(
        "SELECT DISTINCT b FROM Board b LEFT JOIN FETCH b.permissions WHERE b.owner.username = :username",
        Board.class);

    query.setParameter("username", user);
    query.setHint(QueryHints.PASS_DISTINCT_THROUGH, false);

    Iterable<Board> boards = query.getResultList();

    query = createQuery("SELECT DISTINCT b FROM Board b LEFT JOIN FETCH b.rows WHERE b in :boards",
        Board.class);

    query.setParameter("boards", boards);
    query.setHint(QueryHints.PASS_DISTINCT_THROUGH, false);

    boards = query.getResultList();

    query = createQuery(
        "SELECT DISTINCT b FROM Board b LEFT JOIN FETCH b.columns WHERE b in :boards", Board.class);

    query.setParameter("boards", boards);
    query.setHint(QueryHints.PASS_DISTINCT_THROUGH, false);

    boards = query.getResultList();

    return boards;
  }

  @Override
  public Iterable<Board> listActiveBoardsThatUserCanWrite(SystemUser user) {
    TypedQuery<Board> query = createQuery(
        "SELECT DISTINCT b FROM Board b LEFT JOIN FETCH b.permissions WHERE b.owner = :user OR b.permissions IN (SELECT p FROM UserPermission p WHERE p.user = :user AND p.type = 'WRITE')",
        Board.class);

    query.setParameter("user", user);

    return query.getResultList();
  }

}
