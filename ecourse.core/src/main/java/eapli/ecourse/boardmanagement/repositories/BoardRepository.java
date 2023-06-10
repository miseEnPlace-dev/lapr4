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

  /**
   * Returns the boards the user has write permissions to.
   *
   * @param user
   * @return boards
   */
  Iterable<Board> findAllActiveBoardsWithUserWritePermission(Username user);

  /**
   * Returns the boards the user can access to.
   *
   * @param username
   * @return boards
   */
  Iterable<Board> findAllBoardsAccessibleByUser(Username username);
}
