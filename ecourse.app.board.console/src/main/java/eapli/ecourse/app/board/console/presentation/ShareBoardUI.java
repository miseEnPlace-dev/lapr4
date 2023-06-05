package eapli.ecourse.app.board.console.presentation;

import eapli.ecourse.app.board.application.ShareBoardController;
import eapli.ecourse.boardmanagement.dto.BoardDTO;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class ShareBoardUI extends AbstractUI {

  ShareBoardController ctrl = new ShareBoardController();

  @Override
  protected boolean doShow() {

    // SelectWidget<BoardDTO> select = new SelectWidget<>("Boards you own:", boards, new
    // BoardPrinter());

    return false;
  }

  @Override
  public String headline() {
    return "Share Board";
  }

}

