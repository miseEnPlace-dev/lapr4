package eapli.ecourse.postitmanagement.application;

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
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

@UseCaseController
public class ChangePostItController {

  BoardRepository boardRepository;
  PostItRepository postItRepository;
  AuthorizationService authzService;

  ListBoardsService lstBoardsService;
  ListPostItService lstPostItService;
  BoardService boardService;

  public ChangePostItController(BoardRepository boardRepository, PostItRepository postItRepository,
      AuthorizationService authzService) {
    this.boardRepository = boardRepository;
    this.postItRepository = postItRepository;
    this.authzService = authzService;
  }

  public Iterable<BoardDTO> listUserWritableBoards() {
    lstBoardsService = new ListBoardsService(boardRepository);

    SystemUser user = authzService.loggedinUserWithPermissions(ClientRoles.MANAGER,
        ClientRoles.POWER_USER, ClientRoles.STUDENT, ClientRoles.TEACHER).orElseThrow();

    return lstBoardsService.userWritableBoards(user.username());
  }

  public Iterable<PostItDTO> listBoardPostItsCreatedByUser(BoardID boardID) {
    lstPostItService = new ListPostItService(postItRepository);

    SystemUser user = authzService.loggedinUserWithPermissions(ClientRoles.MANAGER,
        ClientRoles.POWER_USER, ClientRoles.STUDENT, ClientRoles.TEACHER).orElseThrow();

    return lstPostItService.userUpdatablePostIts(boardID, user.username());
  }

  public boolean validateCoordinates(BoardID boardID, int x, int y) {
    boardService = new BoardService(boardRepository, postItRepository);

    return boardService.isCellAvailable(boardID, x, y);
  }

  public PostIt changePostIt(PostItID postItID, String title, Integer x, Integer y, String description,
      String image) {

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
      postItImage = PostItImage.valueOf(image);

    PostIt newPostIt = p.update(postItTitle, coordinates, postItDescription, postItImage);

    save(p);
    save(newPostIt);

    return newPostIt;
  }

  private void save(PostIt postIt) {
    postItRepository.save(postIt);
  }

}
