package eapli.ecourse.postitmanagement.application;

import eapli.ecourse.boardmanagement.domain.Board;
import eapli.ecourse.boardmanagement.domain.BoardColumn;
import eapli.ecourse.boardmanagement.domain.BoardID;
import eapli.ecourse.boardmanagement.domain.BoardRow;
import eapli.ecourse.boardmanagement.dto.BoardDTO;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.postitmanagement.domain.Coordinates;
import eapli.ecourse.postitmanagement.domain.PostIt;
import eapli.ecourse.postitmanagement.domain.PostItID;
import eapli.ecourse.postitmanagement.repositories.PostItRepository;

public class BoardService {

  BoardRepository boardRepository;
  PostItRepository postItRepository;

  public BoardService(BoardRepository boardRepository, PostItRepository postItRepository) {
    this.boardRepository = boardRepository;
    this.postItRepository = postItRepository;
  }

  public boolean isCellAvailable(BoardID id, int x, int y) {
    if (!checkCellExists(id, x, y))
      return false;

    Iterable<PostIt> postIts = postItRepository.findLatestByBoardId(id);

    return !this.existsPostIt(postIts, x, y);
  }

  public PostIt ofIdentity(PostItID id) throws IllegalArgumentException {
    return postItRepository.ofIdentity(id).orElseThrow(IllegalArgumentException::new);
  }

  private boolean existsPostIt(Iterable<PostIt> postIts, int x, int y) {
    Coordinates c = Coordinates.valueOf(x, y);

    for (PostIt p : postIts) {
      if (p.coordinates().equals(c))
        return true;
    }

    return false;
  }

  private boolean checkCellExists(BoardID id, int x, int y) {
    Board b = boardRepository.ofIdentity(id).orElseThrow();
    boolean exists = false;

    for (BoardRow r : b.rows())
      if (r.rowNumber().equals(y))
        exists = true;

    if (!exists)
      return false;

    for (BoardColumn c : b.columns())
      if (c.columnNumber().equals(x))
        return true;

    return false;
  }
}
