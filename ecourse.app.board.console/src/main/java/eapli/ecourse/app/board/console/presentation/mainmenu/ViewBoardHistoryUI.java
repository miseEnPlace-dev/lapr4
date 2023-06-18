package eapli.ecourse.app.board.console.presentation.mainmenu;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eapli.ecourse.app.board.application.UnsuccessfulRequestException;
import eapli.ecourse.app.board.application.ViewBoardHistoryController;
import eapli.ecourse.app.common.console.presentation.board.BoardPrinter;
import eapli.ecourse.app.common.console.presentation.postit.PostItPrinter;
import eapli.ecourse.boardmanagement.dto.BoardDTO;
import eapli.ecourse.common.board.protocol.UnsupportedVersionException;
import eapli.ecourse.postitmanagement.dto.PostItDTO;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ListWidget;
import eapli.framework.presentation.console.SelectWidget;

public class ViewBoardHistoryUI extends AbstractUI {
  private static Logger logger = LogManager.getLogger(ViewBoardHistoryUI.class);

  private ViewBoardHistoryController ctrl = new ViewBoardHistoryController();

  @Override
  protected boolean doShow() {

    try {
      Iterable<BoardDTO> boards = ctrl.listUserAccessibleBoards();

      if (!boards.iterator().hasNext()) {
        System.out.println("You don't own any board.");
        return false;
      }

      System.out.println("Boards you can access:\n");

      BoardPrinter printer = new BoardPrinter();

      SelectWidget<BoardDTO> selector = new SelectWidget<>(printer.header(), boards, printer);
      selector.show();

      final BoardDTO selected = selector.selectedElement();

      if (selected == null) {
        System.out.println("\nOperation cancelled by the user.");
        return false;
      }

      Iterable<PostItDTO> postIts = ctrl.listBoardHistory(selected);

      ListWidget<PostItDTO> lister = new ListWidget<>(new PostItPrinter().header(), postIts, new PostItPrinter());
      lister.show();

      Console.readLine("\nPress Enter to continue...");

    } catch (ClassNotFoundException | IOException | UnsupportedVersionException
        | UnsuccessfulRequestException e) {
      logger.error("Error trying to view the board history", e);
    }

    return false;
  }

  @Override
  public String headline() {
    return "View Board History";
  }
}
