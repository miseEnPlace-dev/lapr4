package eapli.ecourse.app.board.console.presentation.mainmenu;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eapli.ecourse.app.board.application.UnsuccessfulRequestException;
import eapli.ecourse.app.board.application.ViewBoardHistoryController;
import eapli.ecourse.app.common.console.presentation.board.BoardPrinter;
import eapli.ecourse.boardmanagement.dto.BoardDTO;
import eapli.ecourse.boardmanagement.dto.BoardHistoryDTO;
import eapli.ecourse.common.board.protocol.UnsupportedVersionException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class ViewPostItHistoryUI extends AbstractUI {
  private static Logger logger = LogManager.getLogger(ViewPostItHistoryUI.class);

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

      Iterable<BoardHistoryDTO> history = ctrl.getBoardPostItHistory(selected);
      printHistory(history);

      Console.readLine("\nPress Enter to continue...");

    } catch (ClassNotFoundException | IOException | UnsupportedVersionException | IllegalArgumentException e) {
      logger.error("Error: ", e);
    } catch (UnsuccessfulRequestException e) {
      System.out.println("Error: " + e.getMessage());
    }

    return false;
  }

  private void printHistory(Iterable<BoardHistoryDTO> history) {
    System.out.println("> Board History:");
    for (BoardHistoryDTO h : history) {

      if (h.getPreviousTitle().equals("")) {
        System.out.println("\n----------------------------------------\n");
        System.out.println("> New!");
        System.out.println("Owner: " + h.getOwner());
        System.out.println("Date: " + h.getDate());
        System.out.println("Title: " + h.getCurrentTitle());
        System.out.println("Description: " + h.getCurrentDescription());
        System.out.println("State: " + h.getCurrentState());
        System.out.println("Image: " + h.getCurrentImage());
        System.out.println("Coordinates: " + h.getCurrentCoordinates());
        continue;
      }

      System.out.println("\n> Update");
      System.out.println("Date: " + h.getDate());

      if (!h.getPreviousTitle().equals(h.getCurrentTitle()))
        System.out.println("Title: " + h.getPreviousTitle() + " -> " + h.getCurrentTitle());

      if (!h.getPreviousDescription().equals(h.getCurrentDescription()))
        System.out.println("Description: " + h.getPreviousDescription() + " -> " + h.getCurrentDescription());

      if (!h.getPreviousState().equals(h.getCurrentState()))
        System.out.println("State: " + h.getPreviousState() + " -> " + h.getCurrentState());

      if (!h.getPreviousImage().equals(h.getCurrentImage()))
        System.out.println("Image: " + h.getPreviousImage() + " -> " + h.getCurrentImage());

      if (!h.getPreviousCoordinates().equals(h.getCurrentCoordinates()))
        System.out.println("Image: " + h.getPreviousCoordinates() + " -> " + h.getCurrentCoordinates());
    }
  }

  @Override
  public String headline() {
    return "View Board History";
  }
}
