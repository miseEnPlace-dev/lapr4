package eapli.ecourse.postitmanagement.application;

import eapli.ecourse.boardmanagement.domain.BoardID;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.postitmanagement.domain.Coordinates;
import eapli.ecourse.postitmanagement.domain.PostIt;
import eapli.ecourse.postitmanagement.repositories.PostItRepository;

public class BoardService {

  BoardRepository boardRepository;
  PostItRepository postItRepository;

  public BoardService(BoardRepository boardRepository, PostItRepository postItRepository) {
    this.boardRepository = boardRepository;
    this.postItRepository = postItRepository;
  }

  public boolean isCellAvailable(BoardID id, int x, int y) {
    postIts = postItRepository.findLatestVersionOfBoard(id);

    return !this.existsPostIt(postIts, x, y);
  }

  private boolean existsPostIt(Iterable<PostIt> postIts, int x, int y) {
    Coordinates c = Coordinates.valueOf(x, y);

    for (PostIt p : postIts) {
      if (p.coordinates().equals(c))
        return true;
    }

    return false;
  }

}
