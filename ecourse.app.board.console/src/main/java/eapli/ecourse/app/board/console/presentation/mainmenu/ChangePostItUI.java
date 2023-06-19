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
          System.out.println("\nYou don't have permission to update any post-it in that board.");
          return false;
        }

        System.out.println("\nPost-its you can update:");
        PostItPrinter postItPrinter = new PostItPrinter();

        SelectWidget<PostItDTO> postItSelector = new SelectWidget<>(postItPrinter.header(), postIts, postItPrinter);
        postItSelector.show();
        selectedPostIt = postItSelector.selectedElement();

      } while (selectedPostIt == null);

      // CHANGE
      Integer x = null, y = null;
      boolean success = true;

      if (!Console.readLine("\nDo you want to change the post-it coordinates? (Y/n): ")
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
      if (!Console.readLine("\nDo you want to change the post-it title? (Y/n): ")
          .equalsIgnoreCase("n"))
        title = Console.readLine("Write the post-it title: ");

      String description = null;
      if (!Console.readLine("\nDo you want to change the post-it description? (Y/n): ")
          .equalsIgnoreCase("n"))
        description = Console.readLine("Write the post-it description (Press Enter to delete): ");

      String imagePath = null;
      if (!Console.readLine("\nDo you want to change the post-it image? (Y/n): ")
          .equalsIgnoreCase("n"))
        imagePath = Console.readLine("Write the post-it image path (Press Enter to delete): ");

      if (imagePath != null)
        while (!imagePath.isEmpty() && !ctrl.validateImagePath(imagePath)) {
          System.out.println("\nInvalid image path. Try again.");
          imagePath = Console.readLine("Write the post-it image path (Press Enter to delete): ");
        }

      if (x == null && y == null && title == null && description == null && imagePath == null) {
        System.out.println("\nNo changes were made.");
        Console.readLine("\nPress Enter to continue...");
        return false;
      }

      printChanges(selectedPostIt, x, y, title, description, imagePath);
      if (!Console.readLine("\nAre you sure you want to edit the post-it \""
          + selectedPostIt.getTitle() + "\"? (y/N)").equalsIgnoreCase("y")) {
        System.out.println("\nOperation cancelled by the user.");
        Console.readLine("\nPress Enter to continue...");
        return false;
      }

      ctrl.changePostIt(selectedPostIt.getId(), x, y, title, description, imagePath);

      System.out.println("\nPost-it changed successfully.");

    } catch (ClassNotFoundException | IOException | UnsupportedVersionException | IllegalArgumentException e) {
      logger.error("Error: ", e);
    } catch (UnsuccessfulRequestException e) {
      System.out.println("Error: " + e.getMessage());
    }

    Console.readLine("\nPress Enter to continue...");
    return false;
  }

  @Override
  public String headline() {
    return "Change Post-It";
  }

  private void printChanges(PostItDTO postIt, Integer x, Integer y, String title,
      String description, String imagePath) {

    System.out.println("\nChanges made: ");

    if (x != null && y != null)
      System.out.println("Coordinates: (" + postIt.getCoordinates().getX() + ", " + postIt.getCoordinates().getY()
          + ") --> (" + x + ", " + y + ")");

    if (title != null)
      System.out.println("Title: " + postIt.getTitle() + " --> " + title);

    if (description != null)
      System.out.println("Description: " + postIt.getDescription() + " --> " + description);

    if (imagePath != null)
      if (imagePath.isEmpty())
        System.out.println("Image: removed");
      else
        System.out.println("Image: changed");
  }
}
