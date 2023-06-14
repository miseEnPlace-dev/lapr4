package eapli.ecourse.app.board.application;

import eapli.ecourse.boardmanagement.application.ListBoardsService;
import eapli.ecourse.boardmanagement.domain.BoardID;
import eapli.ecourse.boardmanagement.dto.BoardDTO;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.postitmanagement.application.ListPostItService;
import eapli.ecourse.postitmanagement.dto.PostItDTO;
import eapli.ecourse.postitmanagement.repositories.PostItRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

@UseCaseController
public class ViewBoardHistoryController {

  BoardRepository boardRepository;
  PostItRepository postItRepository;
  AuthorizationService authzService;

  ListBoardsService listBoardsService;
  ListPostItService listPostItService;

  public ViewBoardHistoryController(BoardRepository boardRepository, PostItRepository postItRepository,
      AuthorizationService authzService) {
    this.boardRepository = boardRepository;
    this.postItRepository = postItRepository;
    this.authzService = authzService;

    this.listBoardsService = new ListBoardsService(boardRepository);
    this.listPostItService = new ListPostItService(postItRepository);
  }

  public Iterable<BoardDTO> listUserAccessibleBoards() {
    SystemUser user = authzService.loggedinUserWithPermissions(ClientRoles.MANAGER,
        ClientRoles.POWER_USER, ClientRoles.STUDENT, ClientRoles.TEACHER).orElseThrow();

    return listBoardsService.userAccessibleBoards(user.identity());
  }

  public Iterable<PostItDTO> listBoardHistory(BoardID boardID) {
    return listPostItService.boardHistory(boardID);
  }

}
