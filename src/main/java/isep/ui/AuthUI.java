package isep.ui;

import java.util.ArrayList;
import java.util.List;

// import isep.auth.AuthController;
// import isep.shared.Constants;
// import isep.shared.SystemRole;
// import isep.ui.utils.Utils;
import isep.util.Console;

public class AuthUI implements Runnable {
  // private AuthController ctrl;

  public AuthUI() {
    // ctrl = new AuthController();
  }

  public void run() {
    boolean success = doLogin();

    if (!success) {
      System.out.println("Login failed.");
      this.logout();
      return;
    }

    // SystemRole role = this.ctrl.getUserRole();

    // if (role == null) {
    // System.out.println("User has not any role assigned.");
    // this.logout();
    // return;
    // }

    // List<MenuItem> rolesUI = getMenuItemForRoles();
    // this.redirectToRoleUI(rolesUI, role);

    // this.logout();
  }

  // private void redirectToRoleUI(List<MenuItem> rolesUI, SystemRole role) {
  // boolean found = false;
  // Iterator<MenuItem> it = rolesUI.iterator();

  // while (it.hasNext() && !found) {
  // MenuItem item = it.next();
  // found = item.hasDescription(role.toString());
  // if (found)
  // item.run();
  // }

  // if (!found)
  // System.out.println("There is no UI for users with role '" + role.toString() +
  // "'");
  // }

  private List<MenuItem> getMenuItemForRoles() {
    List<MenuItem> rolesUI = new ArrayList<>();

    // rolesUI.add(new MenuItem(SystemRole.AGRICULTURAL_MANAGER.toString(),
    // new AgriculturalManagerUI()));
    // rolesUI.add(new MenuItem(SystemRole.DRIVER.toString(), new DriverUI()));
    // rolesUI.add(new MenuItem(SystemRole.CLIENT.toString(), new ClientUI()));
    // rolesUI.add(new MenuItem(SystemRole.DISTRIBUTION_MANAGER.toString(),
    // new DistributionManagerUI()));

    return rolesUI;
  }

  private boolean doLogin() {
    System.out.println("\nLogin:");

    // int maxAttempts = Constants.MAX_OF_PASSWORD_TRIES;
    int maxAttempts = 1;
    boolean success = false;

    do {
      maxAttempts--;
      String id = Console.readLineFromConsole("Enter UserId/Email: ");
      String pwd = Console.readLineFromConsole("Enter Password: ");

      // success = ctrl.doLogin(id, pwd);
      if (!success) {
        System.out.println(
            "\nInvalid UserId and/or Password. \n You have  " + maxAttempts + " more attempt(s).");
      }

    } while (!success && maxAttempts > 0);

    return success;
  }

  private void logout() {
    // ctrl.doLogout();
  }
}
