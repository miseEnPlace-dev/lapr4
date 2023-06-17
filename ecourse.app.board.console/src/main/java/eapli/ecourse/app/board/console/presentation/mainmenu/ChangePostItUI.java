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

public class ChangePostItUI extends AbstractUI {
  private static Logger logger = LogManager.getLogger(ChangePostItUI.class);

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

      BoardPrinter printer = new BoardPrinter();
      printer.printHeader();

      SelectWidget<BoardDTO> selector = new SelectWidget<>("", boards, printer);
      selector.show();

      final BoardDTO selected = selector.selectedElement();

      // POST_ITS
      Iterable<PostItDTO> postIts = ctrl.listUserUpdatablePostIts(selected.getId());

      if (!postIts.iterator().hasNext()) {
        System.out.println("You don't have permission to update any post-it in that board.");
        return false;
      }

      PostItPrinter postItPrinter = new PostItPrinter();
      postItPrinter.printHeader();

      SelectWidget<PostItDTO> postItSelector = new SelectWidget<>("", postIts, postItPrinter);
      postItSelector.show();

      final PostItDTO selectedPostIt = postItSelector.selectedElement();

      // CHANGE
      Integer x = null, y = null;
      boolean success = true;

      if (Console.readLine("Do you want to change the post-it coordinates? (Y/N): ").equalsIgnoreCase("Y"))
        do {
          System.out.println("\nWrite the new post-it coordinates:");
          x = Console.readInteger("X: ");
          y = Console.readInteger("Y: ");

          success = ctrl.validateCoordinates(selected.getId(), x, y);
          if (!success) {
            System.out.println("Cell not available. Make sure the cell exists and is free! Try again.");
          }
        } while (!success);

      String title = null;
      if (Console.readLine("Do you want to change the post-it title? (Y/N): ").equalsIgnoreCase("Y"))
        title = Console.readLine("Write the post-it title: ");

      String description = null;
      if (Console.readLine("Do you want to change the post-it description? (Y/N): ").equalsIgnoreCase("Y"))
        description = Console.readLine("Write the post-it description (Press Enter to delete): ");

      String imagePath = null;
      if (Console.readLine("Do you want to change the post-it image? (Y/N): ").equalsIgnoreCase("Y"))
        imagePath = Console.readLine("Write the post-it image path (Press Enter to delete): ");

      ctrl.changePostIt(selectedPostIt.getId(), x, y, title, description, imagePath);

      System.out.println("\nPost-it changed successfully.");

    } catch (ClassNotFoundException | IOException | UnsupportedVersionException | UnsuccessfulRequestException e) {
      logger.error("Error trying to view a board", e);
    }

    Console.readLine("\nPress Enter to continue...");
    return false;
  }

  @Override
  public String headline() {
    return "Change Post-It";
  }
}
