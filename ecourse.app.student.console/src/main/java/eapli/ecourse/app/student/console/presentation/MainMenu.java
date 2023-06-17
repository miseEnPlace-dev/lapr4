package eapli.ecourse.app.student.console.presentation;

import eapli.ecourse.Application;
import eapli.ecourse.app.common.console.presentation.authz.MyUserMenu;
import eapli.ecourse.app.common.console.presentation.board.CreateBoardUI;
import eapli.ecourse.app.common.console.presentation.meeting.MeetingsMenu;
import eapli.ecourse.app.student.console.presentation.courses.CoursesMenu;
import eapli.ecourse.app.student.console.presentation.exams.ExamsMenu;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.ShowMessageAction;
import eapli.framework.presentation.console.menu.HorizontalMenuRenderer;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

class MainMenu extends StudentBaseUI {
  private static final String SEPARATOR_LABEL = "--------------";
  private static final String RETURN = "Return ";
  private static final String NOT_IMPLEMENTED_YET = "Not implemented yet";

  private static final int EXIT_OPTION = 0;

  // MAIN MENU
  private static final int CREATE_BOARD_OPTION = 1;
  private static final int EXAMS_MENU_OPTION = 2;
  private static final int MEETINGS_MENU_OPTION = 3;
  private static final int COURSES_OPTION = 4;
  private static final int MY_USER_OPTION = 5;
  private static final int SETTINGS_OPTION = 6;

  // SETTINGS
  private static final int SET_USER_ALERT_LIMIT_OPTION = 1;

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
    final MenuRenderer renderer = getRenderer(menu);

    return renderer.render();
  }

  @Override
  public String headline() {
    return authz.session().map(s -> "Student [ @" + s.authenticatedUser().identity() + " ]")
        .orElse("Student App [ ==Anonymous== ]");
  }

  private MenuRenderer getRenderer(final Menu menu) {
    final MenuRenderer theRenderer;
    if (Application.settings().isMenuLayoutHorizontal())
      theRenderer = new HorizontalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
    else
      theRenderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);

    return theRenderer;
  }

  private Menu buildMainMenu() {
    final Menu mainMenu = new Menu();

    final Menu myUserMenu = new MyUserMenu();
    mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);

    mainMenu.addSubMenu(COURSES_OPTION, new CoursesMenu().buildMenu());
    mainMenu.addSubMenu(MEETINGS_MENU_OPTION, new MeetingsMenu().buildMenu());
    mainMenu.addSubMenu(EXAMS_MENU_OPTION, new ExamsMenu().buildMenu());
    mainMenu.addItem(CREATE_BOARD_OPTION, "Create board", new CreateBoardUI()::show);

    final Menu settingsMenu = buildAdminSettingsMenu();
    mainMenu.addSubMenu(SETTINGS_OPTION, settingsMenu);

    if (!Application.settings().isMenuLayoutHorizontal())
      mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));

    mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

    return mainMenu;
  }

  private Menu buildAdminSettingsMenu() {
    final Menu menu = new Menu("Settings >");

    menu.addItem(SET_USER_ALERT_LIMIT_OPTION, "Set users' alert limit",
        new ShowMessageAction(NOT_IMPLEMENTED_YET));

    if (!Application.settings().isMenuLayoutHorizontal())
      menu.addItem(MenuItem.separator(SEPARATOR_LABEL));

    menu.addItem(EXIT_OPTION, RETURN, Actions.SUCCESS);

    return menu;
  }
}
