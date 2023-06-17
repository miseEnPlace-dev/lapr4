package eapli.ecourse.postitmanagement.application;

import java.io.IOException;

import eapli.ecourse.boardmanagement.application.ListBoardsService;
import eapli.ecourse.boardmanagement.domain.BoardID;
import eapli.ecourse.boardmanagement.dto.BoardDTO;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.postitmanagement.domain.Coordinates;
import eapli.ecourse.postitmanagement.domain.PostIt;
import eapli.ecourse.postitmanagement.domain.PostItDescription;
import eapli.ecourse.postitmanagement.domain.PostItID;
import eapli.ecourse.postitmanagement.domain.PostItImage;
import eapli.ecourse.postitmanagement.domain.PostItTitle;
import eapli.ecourse.postitmanagement.dto.PostItDTO;
import eapli.ecourse.postitmanagement.repositories.PostItRepository;
import eapli.framework.application.UseCaseController;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.Username;

@UseCaseController
public class ChangePostItController {

  private final TransactionalContext ctx;

  BoardRepository boardRepository;
  PostItRepository postItRepository;

  ListBoardsService lstBoardsService;
  ListPostItService lstPostItService;
  BoardService boardService;
  ImageEncoderService imageEncoderService;

  public ChangePostItController(TransactionalContext ctx, BoardRepository boardRepository,
      PostItRepository postItRepository) {
    this.ctx = ctx;
    this.boardRepository = boardRepository;
    this.postItRepository = postItRepository;
    this.imageEncoderService = new ImageEncoderService();
  }

  public Iterable<BoardDTO> listUserWritableBoards(Username username) {
    lstBoardsService = new ListBoardsService(boardRepository);

    return lstBoardsService.userWritableBoards(username);
  }

  public Iterable<PostItDTO> listBoardPostItsCreatedByUser(BoardID boardID, Username username) {
    lstPostItService = new ListPostItService(postItRepository);

    return lstPostItService.userUpdatablePostIts(boardID, username);
  }

  public boolean postItExists(PostItID postItId) {
    return postItRepository.ofIdentity(postItId).isPresent();
  }

  public boolean canEditPostIt(PostItID postItId, Username username) {
    PostIt postIt = postItRepository.ofIdentity(postItId).orElseThrow();

    return (postIt.board().canWrite(username) && postIt.owner().hasIdentity(username));
  }

  public boolean isPostItBoardArchived(PostItID postItID) {
    PostIt postIt = postItRepository.ofIdentity(postItID).orElseThrow();
    return postIt.board().isArchived();
  }

  public boolean validateCoordinates(BoardID boardID, int x, int y) {
    boardService = new BoardService(boardRepository, postItRepository);

    return boardService.isCellAvailable(boardID, x, y);
  }

  public PostIt changePostIt(PostItID postItID, String title, Integer x, Integer y,
      String description, String image) throws IOException {

    // nothing to update
    if (title == null && description == null && image == null && x == 0 && y == 0)
      return null;

    PostIt p = postItRepository.ofIdentity(postItID).orElseThrow();

    PostItTitle postItTitle;
    Coordinates coordinates;
    PostItDescription postItDescription;
    PostItImage postItImage;

    // if null, keep the same
    if (title == null)
      postItTitle = p.title();
    else
      postItTitle = PostItTitle.valueOf(title);

    // if null, keep the same
    if (x == null && y == null)
      coordinates = p.coordinates();
    else
      coordinates = Coordinates.valueOf(x, y);

    // if null, keep the same
    // if empty, delete
    if (description == null)
      postItDescription = p.description();
    else if (description.equals(""))
      postItDescription = null;
    else
      postItDescription = PostItDescription.valueOf(description);

    // if null, keep the same
    // if empty, delete
    if (image == null)
      postItImage = p.image();
    else if (image.equals(""))
      postItImage = null;
    else
      postItImage = PostItImage.valueOf(imageEncoderService.encodeImage(image));

    PostIt newPostIt = p.update(postItTitle, coordinates, postItDescription, postItImage);

    // ! this should be done in a single transaction
    this.ctx.beginTransaction();

    save(p);
    save(newPostIt);

    this.ctx.commit();

    return newPostIt;
  }

  private void save(PostIt postIt) {
    postItRepository.save(postIt);
  }

  public PostIt deletePostIt(PostItID postItID) {
    PostIt p = postItRepository.ofIdentity(postItID).orElseThrow();
    PostIt newPostIt = p.delete();

    // ! this should be done in a single transaction
    this.ctx.beginTransaction();

    save(p);
    save(newPostIt);

    this.ctx.commit();

    return newPostIt;
  }

}
