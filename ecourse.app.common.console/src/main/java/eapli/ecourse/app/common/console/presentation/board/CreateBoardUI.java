package eapli.ecourse.app.common.console.presentation.board;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.autoconfigure.integration.IntegrationProperties.RSocket.Client;

import eapli.ecourse.boardmanagement.application.CreateBoardController;
import eapli.ecourse.boardmanagement.domain.PermissionType;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.SelectWidget;

public class CreateBoardUI extends AbstractUI {

  CreateBoardController ctrl = new CreateBoardController(PersistenceContext.repositories().boards(),
      AuthzRegistry.userService());

  @Override
  protected boolean doShow() {
    SystemUser user = AuthzRegistry.authorizationService().loggedinUserWithPermissions(ClientRoles.MANAGER,
        ClientRoles.POWER_USER, ClientRoles.STUDENT, ClientRoles.TEACHER).orElseThrow();

    String title = Console.readLine("Enter the title of the board: ");

    Map<String, Integer> rows = new HashMap<>();
    while (Console.readLine("Do you want to add a Row? (Y/N)").toUpperCase().equals("Y")) {
      String rowName = Console.readLine("Enter the name of the row: ");
      int rowNumber = Console.readInteger("Enter the number of the row: ");
      rows.put(rowName, rowNumber);
    }

    Map<String, Integer> columns = new HashMap<>();
    while (Console.readLine("Do you want to add a Column? (Y/N)").toUpperCase().equals("Y")) {
      String columnName = Console.readLine("Enter the name of the column: ");
      int columnNumber = Console.readInteger("Enter the number of the column: ");
      columns.put(columnName, columnNumber);
    }

    final Iterable<SystemUser> users = this.ctrl.listAllUsers();

    Map<SystemUser, PermissionType> permissions = new HashMap<>();
    while (Console.readLine("Do you want to add a Column? (Y/N)").toUpperCase().equals("Y")) {

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
      PermissionType permissionType;

      permissions.put(selected, permissionType);

    }

    try {
      this.ctrl.createBoard(title, permissions, columns, rows, null, user);
    } catch (Exception e) {
      System.out.println("Error creating board: " + e.getMessage());
    }

    return true;

  }

  @Override
  public String headline() {
    return "Create Board";
  }

}
