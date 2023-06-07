package eapli.ecourse.app.board.console.presentation;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import eapli.ecourse.app.board.application.ShareBoardController;
import eapli.ecourse.app.board.application.UnsuccessfulRequestException;
import eapli.ecourse.app.common.console.presentation.board.BoardHeader;
import eapli.ecourse.app.common.console.presentation.board.BoardPrinter;
import eapli.ecourse.app.common.console.presentation.board.UserPermissionHeader;
import eapli.ecourse.app.common.console.presentation.board.UserPermissionPrinter;
import eapli.ecourse.boardmanagement.dto.BoardDTO;
import eapli.ecourse.boardmanagement.dto.UserPermissionDTO;
import eapli.ecourse.common.board.protocol.UnsupportedVersionException;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class ShareBoardUI extends AbstractUI {
  private static final Logger LOGGER = LogManager.getLogger(ShareBoardUI.class);

  private ShareBoardController ctrl = new ShareBoardController();

  @Override
  protected boolean doShow() {
    try {
      Iterable<BoardDTO> boards = ctrl.listUserBoards();

      if (!boards.iterator().hasNext()) {
        System.out.println("You don't own any board.");
        return false;
      }

      System.out.println("Boards you own:\n");

      new BoardHeader().printHeader();

      SelectWidget<BoardDTO> selector = new SelectWidget<>("", boards, new BoardPrinter());
      selector.show();

      final BoardDTO selected = selector.selectedElement();

      if (selected == null)
        return false;

      String username =
          Console.readLine("\nInsert the username of the user you want to share the board with: ");
      UserPermissionDTO permission = ctrl.userPermissions(selected, username);

      if (permission == null)
        System.out.printf("\nThe user %s doesn't have any permission on the board.\n", username);
      else
        printPermission(permission);

      System.out.println("\nPermissions:\n  1. None\n  2. Read\n  3. Write\n  0. Cancel\n");
      int option = Console.readOption(1, 3, 0);

      if (option == 0) {
        System.out.println("\nOperation cancelled by the user.");
        return false;
      }

      List<String> options = new ArrayList<>() {
        {
          add("none");
          add("read");
          add("write");
        }
      };

      UserPermissionDTO updated =
          ctrl.updateUserPermissions(selected, username, options.get(option - 1));

      System.out.printf("\nUser permissions updated to %s!\n",
          (updated == null) ? "none" : updated.getPermissionType());

      Console.readLine("\nPress ENTER to continue...");

    } catch (ClassNotFoundException | IOException | UnsupportedVersionException
        | UnsuccessfulRequestException | IllegalArgumentException e) {
      LOGGER.error("Error trying to share a board", e);
    }

    return false;
  }

  private void printPermission(UserPermissionDTO permission) {
    System.out.println();
    new UserPermissionHeader().printHeader();
    System.out.printf("\n1. ");
    new UserPermissionPrinter().visit(permission);
    System.out.println();
  }

  @Override
  public String headline() {
    return "Share Board";
  }
}
