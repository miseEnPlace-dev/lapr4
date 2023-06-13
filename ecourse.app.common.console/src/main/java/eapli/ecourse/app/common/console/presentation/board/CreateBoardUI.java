package eapli.ecourse.app.common.console.presentation.board;

import java.util.HashMap;
import java.util.Map;

import eapli.ecourse.app.common.console.presentation.authz.SystemUserPrinter;
import eapli.ecourse.boardmanagement.application.CreateBoardController;
import eapli.ecourse.boardmanagement.domain.PermissionType;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class CreateBoardUI extends AbstractUI {

  CreateBoardController ctrl = new CreateBoardController(PersistenceContext.repositories().boards(),
      AuthzRegistry.userService(), AuthzRegistry.authorizationService());

  @Override
  protected boolean doShow() {

    String title = Console.readLine("Enter the title of the board: ");

    Map<String, Integer> rows = new HashMap<>();

    System.out.println("Add rows: ");

    int maxRows = this.ctrl.boardMaxRows();
    int i = 1;
    do {
      String rowName = Console.readLine("Enter the name of the row " + i + ": ");
      rows.put(rowName, i);
      i++;
    } while (i <= maxRows && Console.readLine("Do you want to add other a Row? (Y/N)").toUpperCase().equals("Y"));

    Map<String, Integer> columns = new HashMap<>();

    System.out.println("Add columns: ");

    int maxColumns = this.ctrl.boardMaxColumns();
    i = 1;
    do {
      String columnName = Console.readLine("Enter the name of the column " + i + ": ");
      columns.put(columnName, i);
      i++;
    } while (i <= maxColumns && Console.readLine("Do you want to add other a Column? (Y/N)").toUpperCase().equals("Y"));

    final Iterable<SystemUser> users = this.ctrl.listAllUsers();

    Map<SystemUser, PermissionType> permissions = new HashMap<>();
    while (Console.readLine("Do you want to add a User Permission? (Y/N)").toUpperCase().equals("Y")) {

      final SelectWidget<SystemUser> selector = new SelectWidget<>("\nUsers:", users, new SystemUserPrinter());
      selector.show();
      SystemUser selected = selector.selectedElement();

      if (selected == null) {
        return false;
      }

      System.out.println("Select the permission type:");
      System.out.println("1. Read");
      System.out.println("2. Write");
      System.out.println("0. Exit");
      Integer permission = Console.readOption(1, 2, 0);

      if (permission == 0) {
        return false;
      }

      PermissionType permissionType = (permission == 1) ? PermissionType.read() : PermissionType.write();

      permissions.put(selected, permissionType);

    }

    try {
      this.ctrl.createBoard(title, permissions, columns, rows);
    } catch (Exception e) {
      System.out.println("Error creating board: " + e.getMessage());
      Console.readLine("Press Enter to continue...");
      return true;
    }

    System.out.println("\nBoard successfully created");
    Console.readLine("Press Enter to continue...");

    return false;
  }

  @Override
  public String headline() {
    return "Create Board";
  }

}
