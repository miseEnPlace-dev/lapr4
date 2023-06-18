package eapli.ecourse.app.board.console.presentation.mainmenu;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eapli.ecourse.app.board.application.ArchiveBoardController;
import eapli.ecourse.app.board.application.UnsuccessfulRequestException;
import eapli.ecourse.app.common.console.presentation.board.BoardPrinter;
import eapli.ecourse.boardmanagement.dto.BoardDTO;
import eapli.ecourse.common.board.protocol.UnsupportedVersionException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class ArchiveBoardUI extends AbstractUI {
  private static Logger logger = LogManager.getLogger(ArchiveBoardUI.class);

  private ArchiveBoardController ctrl = new ArchiveBoardController();

  @Override
  protected boolean doShow() {
    try {

      Iterable<BoardDTO> boards = ctrl.listUserBoards();

      if (!boards.iterator().hasNext()) {
        System.out.println("You don't own any board.");
        return false;
      }

      System.out.println("Boards you own:\n");

      BoardPrinter printer = new BoardPrinter();

      SelectWidget<BoardDTO> selector = new SelectWidget<>(printer.header(), boards, printer);
      selector.show();

      final BoardDTO selected = selector.selectedElement();

      if (selected == null) {
        System.out.println("\nOperation cancelled by the user.");
        return false;
      }

      System.out.printf("\nThis board is currently %s.\n",
          selected.getArchived() == null ? "NOT archived" : "archived");

      String message = String.format("\nDo you want to %s the board? (y/N)",
          selected.getArchived() == null ? "archive" : "unarchive");

      String answer = Console.readLine(message);

      if (!answer.equalsIgnoreCase("y"))
        return false;

      ctrl.archiveBoard(selected);

      System.out.println("\nThe operation was successful!");
      Console.readLine("\nPress ENTER to continue...");

    } catch (ClassNotFoundException | IOException | UnsupportedVersionException
        | UnsuccessfulRequestException | IllegalArgumentException e) {
      logger.error("Error trying to archive a board", e);
    }

    return false;
  }

  @Override
  public String headline() {
    return "Archive Board";
  }
}
