package eapli.ecourse.postitmanagement.application;

import java.io.IOException;
import java.util.Base64;

import eapli.ecourse.boardmanagement.application.ListBoardsService;
import eapli.ecourse.boardmanagement.domain.Board;
import eapli.ecourse.boardmanagement.domain.BoardID;
import eapli.ecourse.boardmanagement.dto.BoardDTO;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.postitmanagement.domain.PostIt;
import eapli.ecourse.postitmanagement.domain.PostItBuilder;
import eapli.ecourse.postitmanagement.repositories.PostItRepository;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.validations.Preconditions;

@UseCaseController
public class CreatePostItController {

  BoardRepository boardRepository;
  PostItRepository postItRepository;
  ImageEncoderService imageEncoderService;

  ListBoardsService lstBoardsService;
  BoardService boardService;

  public CreatePostItController(BoardRepository boardRepository, PostItRepository postItRepository,
      ImageEncoderService imageEncoderService) {
    Preconditions.noneNull(boardRepository, postItRepository, imageEncoderService);

    this.boardRepository = boardRepository;
    this.postItRepository = postItRepository;
    this.imageEncoderService = imageEncoderService;

    lstBoardsService = new ListBoardsService(boardRepository);
    boardService = new BoardService(boardRepository, postItRepository);
  }

  public Iterable<BoardDTO> listBoardsThatUserCanWrite(Username username) {
    return lstBoardsService.userWritableBoards(username);
  }

  public boolean isBoardParticipant(BoardID boardId, Username username) {
    Board board = boardRepository.ofIdentity(boardId).orElseThrow();
    return board.participates(username);
  }

  public boolean validateCoordinates(BoardID boardID, int x, int y) {
    return boardService.isCellAvailable(boardID, x, y);
  }

  public boolean isBoardArchived(BoardID boardID) {
    Board board = boardRepository.ofIdentity(boardID).orElseThrow();
    return board.isArchived();
  }

  public PostIt createPostIt(BoardID boardID, int x, int y, String title, String description,
      String encodedImage, SystemUser user) throws IOException {

    if (!validateCoordinates(boardID, x, y))
      throw new IllegalArgumentException("Invalid coordinates");

    Board board = boardRepository.ofIdentity(boardID).orElseThrow();

    PostItBuilder builder = new PostItBuilder().withBoard(board).withCoordinates(x, y).withTitle(title).withUser(user);

    if (description != null)
      builder.withDescription(description);

    if (encodedImage != null)
      builder.withImage(encodedImage);

    PostIt postIt = builder.build();

    save(postIt);

    return postIt;
  }

  /**
   * Used to bootstrap
   */
  public PostIt createPostIt(BoardID boardID, int x, int y, String title, String description,
      byte[] image, SystemUser user) throws IOException {

    Board board = boardRepository.ofIdentity(boardID).orElseThrow();

    PostItBuilder builder = new PostItBuilder().withBoard(board).withCoordinates(x, y).withTitle(title).withUser(user);

    if (description != null) {
      builder.withDescription(description);
    }

    if (image != null) {
      String encodedImage = new String(Base64.getEncoder().encodeToString(image));

      builder.withImage(encodedImage);
    }

    PostIt postIt = builder.build();

    save(postIt);

    return postIt;
  }

  private void save(PostIt postIt) {
    postItRepository.save(postIt);
  }

}
