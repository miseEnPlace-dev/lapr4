package eapli.ecourse.postitmanagement.application;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;
import eapli.ecourse.boardmanagement.domain.Board;
import eapli.ecourse.boardmanagement.domain.BoardID;
import eapli.ecourse.boardmanagement.dto.BoardDTO;
import eapli.ecourse.postitmanagement.domain.PostIt;
import eapli.ecourse.postitmanagement.domain.PostItID;
import eapli.ecourse.postitmanagement.dto.PostItDTO;
import eapli.ecourse.postitmanagement.repositories.PostItRepository;

public class ListPostItService {
  private final PostItRepository postItRepository;

  public ListPostItService(PostItRepository postItRepository) {
    this.postItRepository = postItRepository;
  }

  public PostItDTO ofIdentity(PostItID id) {
    final PostIt e = postItRepository.ofIdentity(id).orElseThrow(
        () -> new IllegalArgumentException("There is no post-it with the given id: " + id));
    return e.toDto();
  }

  public Iterable<PostItDTO> ofBoard(BoardID boardId) {
    Iterable<PostIt> list = postItRepository.findAllByBoardId(boardId);
    return toDto(list);
  }

  private Iterable<PostItDTO> toDto(Iterable<PostIt> list) {
    return StreamSupport.stream(list.spliterator(), true).map(PostIt::toDto)
        .collect(Collectors.toUnmodifiableList());
  }
}
