package eapli.ecourse.postitmanagement.repositories;

import eapli.ecourse.boardmanagement.domain.BoardID;
import eapli.ecourse.postitmanagement.domain.PostIt;
import eapli.ecourse.postitmanagement.domain.PostItID;
import eapli.framework.domain.repositories.DomainRepository;

public interface PostItRepository extends DomainRepository<PostItID, PostIt> {

  public Iterable<PostIt> findAllByBoardId(BoardID boardId);

}
