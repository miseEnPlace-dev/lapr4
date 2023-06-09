package eapli.ecourse.boardmanagement.repositories;

import java.util.Optional;

import eapli.ecourse.boardmanagement.domain.Board;
import eapli.ecourse.boardmanagement.domain.BoardID;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;

public interface BoardRepository extends DomainRepository<BoardID, Board> {

  /**
   * Returns the board with the given code.
   *
   * @param code
   * @return
   */
  default Optional<Board> findByCode(final BoardID code) {
    return ofIdentity(code);
  }

  /**
   * Returns the boards created by a user.
   *
   * @return an iterable list of boards which the user owns
   */
  Iterable<Board> findAllBoardsCreatedByUser(Username user);

  Iterable<Board> findAllActiveBoardsWithUserWritePermission(Username user);

  Iterable<Board> findAllUserBoards(Username username);
}
