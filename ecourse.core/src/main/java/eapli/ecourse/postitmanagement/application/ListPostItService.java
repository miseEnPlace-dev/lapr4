package eapli.ecourse.postitmanagement.application;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import eapli.ecourse.boardmanagement.domain.BoardID;
import eapli.ecourse.boardmanagement.dto.BoardHistoryDTO;
import eapli.ecourse.postitmanagement.domain.PostIt;
import eapli.ecourse.postitmanagement.domain.PostItID;
import eapli.ecourse.postitmanagement.dto.PostItDTO;
import eapli.ecourse.postitmanagement.repositories.PostItRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;

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

  public Iterable<PostItDTO> latestOfBoard(BoardID boardId) {
    Iterable<PostIt> list = postItRepository.findLatestByBoardId(boardId);
    return toDto(list);
  }

  public Iterable<PostItDTO> latestOfBoardByUser(BoardID boardId, Username username) {
    Iterable<PostIt> list = postItRepository.findLatestFromUserByBoardId(boardId, username);
    return toDto(list);
  }

  public Iterable<PostItDTO> boardHistory(BoardID boardId) {
    Iterable<PostIt> list = postItRepository.findAllPostItsOrderedByDate(boardId);
    return toDto(list);
  }

  public Iterable<BoardHistoryDTO> boardPostItHistory(BoardID boardId) {
    Iterable<PostIt> list = postItRepository.findLatestByBoardId(boardId);
    List<BoardHistoryDTO> history = new ArrayList<>();

    for (PostIt postIt : list) {
      Queue<PostIt> postItHistory = getPostItHistory(postIt);
      PostIt p = postItHistory.poll();
      while (!postItHistory.isEmpty()) {
        PostIt previousPostIt = postItHistory.poll();
        history.add(toDto(p, previousPostIt));
        p = previousPostIt;
      }
    }

    return history;
  }

  private BoardHistoryDTO toDto(PostIt postIt, PostIt previousPostIt) {
    SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy HH:mm:ss");

    String currentDescription = postIt.description() == null ? "N/a" : postIt.description().toString();
    String currentImage = postIt.image() == null ? "No" : "Yes";

    if (previousPostIt == null) {
      return new BoardHistoryDTO("", postIt.title().toString(),
          "", currentDescription, "",
          postIt.state().toString(), "", postIt.coordinates().toString(),
          "", currentImage, postIt.owner().name().toString(),
          formatter.format(postIt.createdAt().getTime()));
    }

    String previousImage = previousPostIt.image() == null ? "No" : "Yes";

    String previousDescription = previousPostIt.description() == null ? "N/a" : previousPostIt.description().toString();

    return new BoardHistoryDTO(previousPostIt.title().toString(), postIt.title().toString(),
        previousDescription, currentDescription, previousPostIt.state().toString(),
        postIt.state().toString(), previousPostIt.coordinates().toString(), postIt.coordinates().toString(),
        previousImage, currentImage, postIt.owner().name().toString(), formatter.format(postIt.createdAt().getTime()));
  }

  private Queue<PostIt> getPostItHistory(PostIt postIt) {
    Queue<PostIt> postItHistory = new LinkedList<>();
    postItHistory.add(postIt);
    while (postIt != null) {
      postItHistory.add(postIt.previous());
      postIt = postIt.previous();
    }

    return postItHistory;
  }

  public Iterable<PostItDTO> userUpdatablePostIts(BoardID boardId, Username username) {
    Iterable<PostIt> list = postItRepository.findLatestFromUserByBoardId(boardId, username);
    return toDto(list);
  }

  private Iterable<PostItDTO> toDto(Iterable<PostIt> list) {
    return StreamSupport.stream(list.spliterator(), true).map(PostIt::toDto)
        .collect(Collectors.toUnmodifiableList());
  }
}
