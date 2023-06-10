package eapli.ecourse.postitmanagement.application;

import eapli.ecourse.boardmanagement.domain.BoardID;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.postitmanagement.domain.Coordinates;
import eapli.ecourse.postitmanagement.domain.PostIt;
import eapli.ecourse.postitmanagement.domain.PostItID;
import eapli.ecourse.postitmanagement.dto.PostItDTO;
import eapli.ecourse.postitmanagement.repositories.PostItRepository;

public class PostItService {
  private BoardRepository boardRepository;
  private PostItRepository postItRepository;

  public PostItService(BoardRepository boardRepository, PostItRepository postItRepository) {
    this.boardRepository = boardRepository;
    this.postItRepository = postItRepository;
  }

  public boolean isCellAvailable(BoardID id, int x, int y) {
    Iterable<PostIt> postIts = postItRepository.findAllByBoardId(id);

    postIts = postItRepository.findLatestVersionOfBoard(id);

    return !this.existsPostIt(postIts, x, y);

  }

  public PostItDTO ofIdentity(PostItID id) {
    PostIt p = postItRepository.ofIdentity(id).orElseThrow(
        () -> new IllegalArgumentException("There is no post-it with the given id: " + id));

    return p.toDto();
  }

  public boolean existsPostIt(BoardID id, int x, int y) {
    // TODO
    return false;
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
