package eapli.ecourse.boardmanagment.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;

import java.util.Optional;

import eapli.ecourse.boardmanagment.domain.Board;
import eapli.ecourse.boardmanagment.domain.BoardID;

public interface BoardRepository extends DomainRepository<BoardID, Board> {

  /**
   * Returns the course with the given code.
   *
   * @param code
   * @return
   */
  default Optional<Board> findByCode(final BoardID code) {
    return ofIdentity(code);
  }

  /**
   * Returns the courses that are opened for enrollment.
   *
   * @return
   */
  Iterable<Board> findAllBoardsCreatedByUser(Username user);

}
