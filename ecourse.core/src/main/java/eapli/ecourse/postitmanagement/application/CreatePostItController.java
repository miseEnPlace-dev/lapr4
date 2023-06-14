package eapli.ecourse.postitmanagement.application;

import java.io.IOException;

import eapli.ecourse.boardmanagement.application.ListBoardsService;
import eapli.ecourse.boardmanagement.domain.Board;
import eapli.ecourse.boardmanagement.domain.BoardID;
import eapli.ecourse.boardmanagement.dto.BoardDTO;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.postitmanagement.domain.PostIt;
import eapli.ecourse.postitmanagement.domain.PostItBuilder;
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
  ImageEncoderService imageEncoderService;

  ListBoardsService lstBoardsService;
  BoardService boardService;

  public CreatePostItController(BoardRepository boardRepository, PostItRepository postItRepository,
      AuthorizationService authzService, ImageEncoderService imageEncoderService) {
    Preconditions.noneNull(boardRepository, postItRepository, authzService);

    this.boardRepository = boardRepository;
    this.postItRepository = postItRepository;
    this.authzService = authzService;
    this.imageEncoderService = imageEncoderService;

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

  public PostIt createPostIt(BoardID boardID, int x, int y, String title, String description,
      String imagePath) throws IOException {
    SystemUser user = authzService.loggedinUserWithPermissions(ClientRoles.MANAGER,
        ClientRoles.POWER_USER, ClientRoles.STUDENT, ClientRoles.TEACHER).orElseThrow();

    Board board = boardRepository.ofIdentity(boardID).orElseThrow();

    PostItBuilder builder =
        new PostItBuilder().withBoard(board).withCoordinates(x, y).withTitle(title).withUser(user);

    if (description != null) {
      builder.withDescription(description);
    }

    if (imagePath != null) {
      String encodedImage = imageEncoderService.encodeImage(imagePath);

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
