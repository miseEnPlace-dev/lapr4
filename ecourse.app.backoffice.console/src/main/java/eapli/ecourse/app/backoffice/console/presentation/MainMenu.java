package eapli.ecourse.app.backoffice.console.presentation;

import eapli.ecourse.Application;
import eapli.ecourse.app.backoffice.console.presentation.authz.AddUserUI;
import eapli.ecourse.app.backoffice.console.presentation.authz.DeactivateUserAction;
import eapli.ecourse.app.backoffice.console.presentation.authz.ListUsersAction;
import eapli.ecourse.app.backoffice.console.presentation.student.AcceptRefuseSignupRequestAction;
import eapli.ecourse.app.common.console.presentation.authz.MyUserMenu;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.ShowMessageAction;
import eapli.framework.presentation.console.menu.HorizontalMenuRenderer;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

/**
 * TODO split this class in more specialized classes for each menu
 *
 * @author Paulo Gandra Sousa
 */
public class MainMenu extends AbstractUI {

  private static final String RETURN_LABEL = "Return ";

  private static final int EXIT_OPTION = 0;

  // USERS
  private static final int ADD_USER_OPTION = 1;
  private static final int LIST_USERS_OPTION = 2;
  private static final int DEACTIVATE_USER_OPTION = 3;
  private static final int ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION = 4;

  // SETTINGS
  // private static final int <something> = 1;

  // MAIN MENU
  private static final int MY_USER_OPTION = 1;
  private static final int USERS_OPTION = 2;
  private static final int SETTINGS_OPTION = 3;
  private static final int SOMETHING_OPTION = 4;

  private static final String SEPARATOR_LABEL = "--------------";

  private final AuthorizationService authz = AuthzRegistry.authorizationService();

  @Override
  public boolean show() {
    drawFormTitle();
    return doShow();
  }

  /**
   * @return true if the user selected the exit option
   */
  @Override
  public boolean doShow() {
    final Menu menu = buildMainMenu();
    final MenuRenderer renderer;
    if (Application.settings().isMenuLayoutHorizontal()) {
      renderer = new HorizontalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
    } else {
      renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
    }
    return renderer.render();
  }

  @Override
  public String headline() {

    return authz.session().map(s -> "eCOURSE [ @" + s.authenticatedUser().identity() + " ]")
        .orElse("eCOURSE [ ==Anonymous== ]");
  }

  private Menu buildMainMenu() {
    final Menu mainMenu = new Menu();

    final Menu myUserMenu = new MyUserMenu();
    mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);

    if (!Application.settings().isMenuLayoutHorizontal()) {
      mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
    }

    if (authz.isAuthenticatedUserAuthorizedTo(ClientRoles.POWER_USER, ClientRoles.MANAGER)) {
      final Menu usersMenu = buildUsersMenu();
      mainMenu.addSubMenu(USERS_OPTION, usersMenu);
      final Menu settingsMenu = buildAdminSettingsMenu();
      mainMenu.addSubMenu(SETTINGS_OPTION, settingsMenu);
    }

    if (!Application.settings().isMenuLayoutHorizontal()) {
      mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
    }

    mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

    return mainMenu;
  }

  private Menu buildAdminSettingsMenu() {
    final Menu menu = new Menu("Settings >");

    menu.addItem(SOMETHING_OPTION, "Test", new ShowMessageAction("Not implemented yet"));
    menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

    return menu;
  }

  private Menu buildUsersMenu() {
    final Menu menu = new Menu("Users >");

    menu.addItem(ADD_USER_OPTION, "Add User", new AddUserUI()::show);
    menu.addItem(LIST_USERS_OPTION, "List all Users", new ListUsersAction());
    menu.addItem(DEACTIVATE_USER_OPTION, "Deactivate User", new DeactivateUserAction());
    menu.addItem(ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION, "Accept/Refuse Signup Request",
        new AcceptRefuseSignupRequestAction());
    menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

    return menu;
  }

  // private Menu buildManagerMenu() {
  // final Menu menu = new Menu("Manager >");

  // // menu.addItem(..., "...", ...);

  // menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

  // return menu;
  // }
}
