package eapli.ecourse.app.board.console.presentation.mainmenu;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eapli.ecourse.app.board.application.ChangePostItController;
import eapli.ecourse.app.board.application.UnsuccessfulRequestException;
import eapli.ecourse.app.common.console.presentation.board.BoardPrinter;
import eapli.ecourse.app.common.console.presentation.postit.PostItPrinter;
import eapli.ecourse.boardmanagement.dto.BoardDTO;
import eapli.ecourse.common.board.protocol.UnsupportedVersionException;
import eapli.ecourse.postitmanagement.dto.PostItDTO;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class DeletePostItUI extends AbstractUI {
  private static Logger logger = LogManager.getLogger(DeletePostItUI.class);

  private ChangePostItController ctrl = new ChangePostItController();

  @Override
  protected boolean doShow() {

    try {
      // BOARDS
      Iterable<BoardDTO> boards = ctrl.listUserWritableBoards();

      if (!boards.iterator().hasNext()) {
        System.out.println("You don't have permission to write in any board.");
        return false;
      }

      BoardDTO selectedBoard = null;
      PostItDTO selectedPostIt = null;

      do {
        System.out.println("Boards you can edit:\n");

        BoardPrinter printer = new BoardPrinter();
        printer.printHeader();

        SelectWidget<BoardDTO> selector = new SelectWidget<>("", boards, printer);
        selector.show();

        selectedBoard = selector.selectedElement();

        // POST_ITS
        Iterable<PostItDTO> postIts = ctrl.listUserUpdatablePostIts(selectedBoard.getId());

        if (!postIts.iterator().hasNext()) {
          System.out.println("You don't have permission to delete any post-it in that board.");
          return false;
        }

        PostItPrinter postItPrinter = new PostItPrinter();
        postItPrinter.printHeader();

        SelectWidget<PostItDTO> postItSelector = new SelectWidget<>("", postIts, postItPrinter);
        postItSelector.show();

        selectedPostIt = postItSelector.selectedElement();
      } while (selectedPostIt == null);

      // confirmation
      if (!Console.readLine("\nAre you sure you want to delete the post-it \""
          + selectedPostIt.getTitle() + "\"? (y/N)").equalsIgnoreCase("y")) {
        System.out.println("\nOperation cancelled by the user.");
        return false;
      }

      // Delete
      ctrl.deletePostIt(selectedPostIt.getId());

      System.out.println("\nPost-it deleted successfully.");

    } catch (ClassNotFoundException | IOException | UnsupportedVersionException
        | UnsuccessfulRequestException e) {
      logger.error("Error trying to view a board", e);
    }

    Console.readLine("\nPress Enter to continue...");
    return false;
  }

  @Override
  public String headline() {
    return "Delete Post-It";
  }
}
