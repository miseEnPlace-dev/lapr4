package eapli.ecourse.app.board.console.presentation.mainmenu;

import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eapli.ecourse.app.board.application.CreatePostItController;
import eapli.ecourse.app.board.application.UnsuccessfulRequestException;
import eapli.ecourse.app.common.console.presentation.board.BoardPrinter;
import eapli.ecourse.boardmanagement.dto.BoardDTO;
import eapli.ecourse.common.board.protocol.UnsupportedVersionException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class CreatePostItUI extends AbstractUI {
  private static Logger logger = LogManager.getLogger(CreatePostItUI.class);

  private CreatePostItController ctrl = new CreatePostItController();

  @Override
  protected boolean doShow() {

    try {
      Iterable<BoardDTO> boards = ctrl.listUserWritableBoards();

      if (!boards.iterator().hasNext()) {
        System.out.println("You don't have permission to write in any board.");
        return false;
      }

      System.out.println("Boards you can edit:\n");

      BoardPrinter printer = new BoardPrinter();

      SelectWidget<BoardDTO> selector = new SelectWidget<>(printer.header(), boards, printer);
      selector.show();

      final BoardDTO selected = selector.selectedElement();

      if (selected == null) {
        System.out.println("\nOperation cancelled by the user.");
        return false;
      }

      Integer x, y;
      boolean success = true;
      do {
        System.out.println("\nWrite the post-it coordinates:");
        x = Console.readInteger("X: ");
        y = Console.readInteger("Y: ");

        success = ctrl.validateCoordinates(selected.getId(), x, y);

        if (!success) {
          System.out
              .println("Cell not available. Make sure the cell exists and is free!");
          String option = Console.readLine(
              "\nDo you want to try with a different cell? (Y/n) ");

          if (option.equalsIgnoreCase("n"))
            return false;
        }
      } while (!success);

      String title = null;
      do {
        title = Console.readLine("\nWrite the post-it title: ");
      } while (title.length() == 0);

      String description = Console.readLine("\nWrite the post-it description (Press Enter to skip): ");

      String imagePath = Console.readLine("Write the post-it image path (Press Enter to skip): ");
      while (!imagePath.isEmpty() && !ctrl.validateImagePath(imagePath)) {
        System.out.println("\nInvalid image path. Try again.");
        imagePath = Console.readLine("Write the post-it image path (Press Enter to skip): ");
      }

      ctrl.createPostIt(selected.getId(), x, y, title, description, imagePath);

      System.out.println("\nPost-it created successfully.");
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
    return "Create Post-It";
  }
}
