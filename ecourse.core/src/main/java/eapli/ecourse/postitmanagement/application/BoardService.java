package eapli.ecourse.postitmanagement.application;

import java.util.List;

import eapli.ecourse.boardmanagement.domain.Board;
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

  public boolean existsPostIt(BoardID id, int x, int y) {
    // TODO
    return false;
  }

  private Iterable<PostIt> getLatestVersionOfBoard(Board board) {

    List<PostIt> postIts = (List<PostIt>) postItRepository.findAllByBoardId(board.identity());

    for (PostIt p : postIts) {
      if (postIts.contains(p)) {
        postIts.remove(p);
      }
    }

    return postIts;
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
