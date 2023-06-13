package eapli.ecourse.app.board.console.presentation.mainmenu;

import java.io.IOException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eapli.ecourse.app.board.application.UndoPostItChangeController;
import eapli.ecourse.app.board.application.UnsuccessfulRequestException;
import eapli.ecourse.app.common.console.presentation.board.BoardPrinter;
import eapli.ecourse.app.common.console.presentation.postit.PostItPrinter;
import eapli.ecourse.boardmanagement.dto.BoardDTO;
import eapli.ecourse.common.board.protocol.UnsupportedVersionException;
import eapli.ecourse.postitmanagement.dto.PostItDTO;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class UndoPostItChangeUI extends AbstractUI {
  private static Logger logger = LogManager.getLogger(UndoPostItChangeUI.class);

  private UndoPostItChangeController ctrl = new UndoPostItChangeController();

  @Override
  protected boolean doShow() {
    try {
      Iterable<BoardDTO> boards = ctrl.listUserWritableBoards();

      if (!boards.iterator().hasNext()) {
        System.out.println("You don't own any board.");
        return false;
      }

      System.out.println("Boards where you have write permission:\n");

      BoardPrinter boardPrinter = new BoardPrinter();
      boardPrinter.printHeader();

      SelectWidget<BoardDTO> boardSelector = new SelectWidget<>("", boards, boardPrinter);
      boardSelector.show();

      final BoardDTO selectedBoard = boardSelector.selectedElement();

      if (selectedBoard == null)
        return false;

      Iterable<PostItDTO> postIts = ctrl.listLatestBoardPostIts(selectedBoard);

      if (!postIts.iterator().hasNext()) {
        System.out.println("This board does not contain any post-it.");
        return false;
      }

      System.out.println("\nBoard Post-Its:\n");

      PostItPrinter postItPrinter = new PostItPrinter();
      postItPrinter.printHeader();

      SelectWidget<PostItDTO> postItSelector = new SelectWidget<>("", postIts, postItPrinter);
      postItSelector.show();

      final PostItDTO selectedPostIt = postItSelector.selectedElement();

      if (selectedPostIt == null)
        return false;

      if (selectedPostIt.getPrevious() == null) {
        System.out.println("\nThis post-it does not have any previous version.");
        Console.readLine("\nPress ENTER to continue...");
        return false;
      }

      System.out.println("\nPreviewing the previous Post-It version:\n");

      printPostIt(selectedPostIt.getPrevious());

      String answer = Console.readLine("\nDo you want to undo this change? (y/n)");

      if (!answer.equalsIgnoreCase("y"))
        return false;

      ctrl.undoPostItChange(selectedPostIt);

      System.out.println("\nPost-It change undone successfully.");
      Console.readLine("\nPress ENTER to continue...");

    } catch (ClassNotFoundException | IOException | UnsupportedVersionException
        | UnsuccessfulRequestException e) {
      logger.error("Error undoing a post-it change", e);
    }

    return false;
  }

  private void printPostIt(PostItDTO postIt) {
    PostItPrinter printer = new PostItPrinter();
    System.out.println();
    printer.printHeader();
    System.out.printf("\n1. ");
    printer.visit(postIt);
    System.out.println();
  }

  @Override
  public String headline() {
    return "Undo Post-It Change";
  }
}
