package eapli.ecourse.boardmanagement.application;

import eapli.ecourse.boardmanagement.domain.BoardID;
import eapli.ecourse.boardmanagement.dto.BoardDTO;
import eapli.ecourse.boardmanagement.dto.BoardHistoryDTO;
import eapli.ecourse.boardmanagement.repositories.BoardRepository;
import eapli.ecourse.postitmanagement.application.ListPostItService;
import eapli.ecourse.postitmanagement.dto.PostItDTO;
import eapli.ecourse.postitmanagement.repositories.PostItRepository;
import eapli.framework.application.UseCaseController;
import eapli.framework.infrastructure.authz.domain.model.Username;

@UseCaseController
public class ViewBoardHistoryController {

  BoardRepository boardRepository;
  PostItRepository postItRepository;

  ListBoardsService listBoardsService;
  ListPostItService listPostItService;

  public ViewBoardHistoryController(BoardRepository boardRepository, PostItRepository postItRepository) {
    this.boardRepository = boardRepository;
    this.postItRepository = postItRepository;

    this.listBoardsService = new ListBoardsService(boardRepository);
    this.listPostItService = new ListPostItService(postItRepository);
  }

  public Iterable<BoardDTO> listUserAccessibleBoards(Username username) {
    return listBoardsService.userAccessibleBoards(username);
  }

  public Iterable<PostItDTO> listBoardHistory(BoardID boardID) {
    return listPostItService.boardHistory(boardID);
  }

  public Iterable<BoardHistoryDTO> listBoardPostItHistory(BoardID boardID) {
    return listPostItService.boardPostItHistory(boardID);
  }
}
