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

      BoardDTO selectedBoard = null;
      PostItDTO selectedPostIt = null;

      do {
        System.out.println("Boards you can edit:\n");

        BoardPrinter printer = new BoardPrinter();

        SelectWidget<BoardDTO> selector = new SelectWidget<>(printer.header(), boards, printer);
        selector.show();

        selectedBoard = selector.selectedElement();

        if (selectedBoard == null) {
          System.out.println("\nOperation cancelled by the user.");
          return false;
        }

        // POST_ITS
        Iterable<PostItDTO> postIts = ctrl.listUserUpdatablePostIts(selectedBoard.getId());

        if (!postIts.iterator().hasNext()) {
          System.out.println("You don't have permission to update any post-it in that board.");
          return false;
        }

        PostItPrinter postItPrinter = new PostItPrinter();

        SelectWidget<PostItDTO> postItSelector = new SelectWidget<>(postItPrinter.header(), postIts, postItPrinter);
        postItSelector.show();
        selectedPostIt = postItSelector.selectedElement();

      } while (selectedPostIt == null);

      // CHANGE
      Integer x = null, y = null;
      boolean success = true;

      if (!Console.readLine("Do you want to change the post-it coordinates? (Y/n): ")
          .equalsIgnoreCase("n"))
        do {
          System.out.println("\nWrite the new post-it coordinates:");
          x = Console.readInteger("X: ");
          y = Console.readInteger("Y: ");

          success = ctrl.validateCoordinates(selectedBoard.getId(), x, y);
          if (!success) {
            System.out
                .println("Cell not available. Make sure the cell exists and is free! Try again.");
          }
        } while (!success);

      String title = null;
      if (!Console.readLine("Do you want to change the post-it title? (Y/n): ")
          .equalsIgnoreCase("n"))
        title = Console.readLine("Write the post-it title: ");

      String description = null;
      if (!Console.readLine("Do you want to change the post-it description? (Y/n): ")
          .equalsIgnoreCase("n"))
        description = Console.readLine("Write the post-it description (Press Enter to delete): ");

      String imagePath = null;
      if (!Console.readLine("Do you want to change the post-it image? (Y/n): ")
          .equalsIgnoreCase("n"))
        imagePath = Console.readLine("Write the post-it image path (Press Enter to delete): ");

      if (!Console.readLine("\nReview the changes. Are you sure you want to edit the post-it \""
          + selectedPostIt.getTitle() + "\"? (y/N)").equalsIgnoreCase("y")) {
        System.out.println("\nOperation cancelled by the user.");
        return false;
      }

      ctrl.changePostIt(selectedPostIt.getId(), x, y, title, description, imagePath);

      System.out.println("\nPost-it changed successfully.");

    } catch (ClassNotFoundException | IOException | UnsupportedVersionException
        | UnsuccessfulRequestException e) {
      logger.error("Error trying to edit a post-it", e);
    }

    Console.readLine("\nPress Enter to continue...");
    return false;
  }

  @Override
  public String headline() {
    return "Change Post-It";
  }
}
