package eapli.ecourse.postitmanagement.application;

import eapli.ecourse.boardmanagement.application.ListBoardsService;
import eapli.ecourse.boardmanagement.domain.Board;
import eapli.ecourse.boardmanagement.domain.BoardID;
import eapli.ecourse.boardmanagement.dto.BoardDTO;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.postitmanagement.domain.Coordinates;
import eapli.ecourse.postitmanagement.domain.PostIt;
import eapli.ecourse.postitmanagement.domain.PostItTitle;
import eapli.ecourse.postitmanagement.repositories.PostItRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

@UseCaseController
public class CreatePostItController {

  BoardRepository boardRepository;
  PostItRepository postItRepository;
  AuthorizationService authzService;

  ListBoardsService lstBoardsService;
  BoardService boardService;

  public CreatePostItController(BoardRepository boardRepository, PostItRepository postItRepository,
      AuthorizationService authzService) {
    Preconditions.noneNull(boardRepository, postItRepository, authzService);

    this.boardRepository = boardRepository;
    this.postItRepository = postItRepository;
    this.authzService = authzService;

    lstBoardsService = new ListBoardsService(boardRepository);
    boardService = new BoardService(boardRepository, postItRepository);
  }

  public Iterable<BoardDTO> listBoardsThatUserCanWrite() {
    SystemUser user = authzService.loggedinUserWithPermissions(ClientRoles.MANAGER,
        ClientRoles.POWER_USER, ClientRoles.STUDENT, ClientRoles.TEACHER).orElseThrow();

    return lstBoardsService.userWritableBoards(user.username());
  }

  public boolean validateCoordinates(BoardID boardID, int x, int y) {
    return boardService.isCellAvailable(boardID, x, y);
  }

  public void createPostIt(BoardID boardID, int x, int y, String title) {
    SystemUser user = authzService.loggedinUserWithPermissions(ClientRoles.MANAGER,
        ClientRoles.POWER_USER, ClientRoles.STUDENT, ClientRoles.TEACHER).orElseThrow();

    Board board = boardRepository.ofIdentity(boardID).orElseThrow();

    PostItTitle postItTile = PostItTitle.valueOf(title);
    Coordinates coordinates = Coordinates.valueOf(x, y);

    PostIt postIt = new PostIt(postItTile, coordinates, board, user, null);

    save(postIt);
  }

  private void save(PostIt postIt) {
    postItRepository.save(postIt);
  }

}
