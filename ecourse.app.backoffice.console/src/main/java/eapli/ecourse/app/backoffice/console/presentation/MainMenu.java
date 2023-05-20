package eapli.ecourse.app.backoffice.console.presentation;

import eapli.ecourse.Application;
import eapli.ecourse.app.backoffice.console.presentation.course.CoursesMenu;
import eapli.ecourse.app.backoffice.console.presentation.enrolment.EnrollmentsMenu;
import eapli.ecourse.app.backoffice.console.presentation.users.UsersMenu;
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

public class MainMenu extends AbstractUI {
  private static final String RETURN_LABEL = "Return ";

  private static final int EXIT_OPTION = 0;

  // SETTINGS
  // private static final int <something> = 1;

  // MAIN MENU
  private static final int MY_USER_OPTION = 1;
  private static final int USERS_OPTION = 2;
  private static final int COURSES_OPTION = 3;
  private static final int ENROLLMENTS_OPTION = 4;
  private static final int SETTINGS_OPTION = 5;
  private static final int CREATE_BOARD_OPTION = 6;
  private static final int SOMETHING_OPTION = 7;

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

    if (Application.settings().isMenuLayoutHorizontal())
      renderer = new HorizontalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
    else
      renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);

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

    if (!Application.settings().isMenuLayoutHorizontal())
      mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));

    if (authz.isAuthenticatedUserAuthorizedTo(ClientRoles.POWER_USER, ClientRoles.MANAGER)) {
      final Menu usersMenu = new UsersMenu().buildUsersMenu();
      mainMenu.addSubMenu(USERS_OPTION, usersMenu);
      final Menu coursesMenu = new CoursesMenu().buildCoursesMenu();
      mainMenu.addSubMenu(COURSES_OPTION, coursesMenu);
      final Menu enrollmentsMenu = new EnrollmentsMenu().buildEnrolmentsMenu();
      mainMenu.addSubMenu(ENROLLMENTS_OPTION, enrollmentsMenu);
      final Menu settingsMenu = buildAdminSettingsMenu();
      mainMenu.addSubMenu(SETTINGS_OPTION, settingsMenu);
    }

    if (!Application.settings().isMenuLayoutHorizontal())
      mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));

    mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

    return mainMenu;
  }

  private Menu buildAdminSettingsMenu() {
    final Menu menu = new Menu("Settings >");

    menu.addItem(SOMETHING_OPTION, "Test", new ShowMessageAction("Not implemented yet"));
    menu.addItem(CREATE_BOARD_OPTION, "Create Board", new ShowMessageAction("Not implemented yet"));
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
