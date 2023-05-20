package eapli.ecourse.app.student.console.presentation;

import eapli.ecourse.Application;
import eapli.ecourse.app.common.console.presentation.authz.MyUserMenu;
import eapli.ecourse.app.common.console.presentation.board.CreateBoardUI;
import eapli.ecourse.app.common.console.presentation.meeting.ScheduleMeetingUI;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.ShowMessageAction;
import eapli.framework.presentation.console.menu.HorizontalMenuRenderer;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

/**
 * @author Paulo Gandra Sousa
 */
class MainMenu extends StudentBaseUI {

  private static final String SEPARATOR_LABEL = "--------------";

  private static final String RETURN = "Return ";

  private static final String NOT_IMPLEMENTED_YET = "Not implemented yet";

  private static final int EXIT_OPTION = 0;

  // MAIN MENU
  private static final int MY_USER_OPTION = 1;
  private static final int ACCOUNT_OPTION = 2;
  private static final int REQUEST_ENROLMENT_OPTION = 3;
  private static final int SCHEDULE_MEETING = 4;
  private static final int VIEW_FUTURE_EXAMS = 5;
  private static final int CREATE_BOARD_OPTION = 6;
  private static final int SETTINGS_OPTION = 7;

  // ACCOUNT MENU
  // private static final int <something> = 1;

  // SETTINGS
  private static final int SET_USER_ALERT_LIMIT_OPTION = 1;

  // private final AuthorizationService authz =
  // AuthzRegistry.authorizationService();

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

  private MenuRenderer getRenderer(final Menu menu) {
    final MenuRenderer theRenderer;
    if (Application.settings().isMenuLayoutHorizontal()) {
      theRenderer = new HorizontalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
    } else {
      theRenderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
    }
    return theRenderer;
  }

  private Menu buildMainMenu() {
    final Menu mainMenu = new Menu();

    if (!Application.settings().isMenuLayoutHorizontal())
      mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));

    final Menu myUserMenu = new MyUserMenu();
    mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);

    final Menu accountMenu = buildAccountMenu();
    mainMenu.addSubMenu(ACCOUNT_OPTION, accountMenu);

    mainMenu.addItem(REQUEST_ENROLMENT_OPTION, "Request enrolment", new RequestEnrolmentUI()::show);
    mainMenu.addItem(SCHEDULE_MEETING, "Schedule meeting", new ScheduleMeetingUI()::show);
    mainMenu.addItem(VIEW_FUTURE_EXAMS, "View future exams", new ListFutureExamsUI()::show);
    mainMenu.addItem(CREATE_BOARD_OPTION, "Create board", new CreateBoardUI()::show);

    final Menu settingsMenu = buildAdminSettingsMenu();
    mainMenu.addSubMenu(SETTINGS_OPTION, settingsMenu);

    if (!Application.settings().isMenuLayoutHorizontal())
      mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));

    mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

    return mainMenu;
  }

  private Menu buildAccountMenu() {
    final Menu accountMenu = new Menu("Account");
    // accountMenu.addItem(<example>, "<something>", () -> {
    // // ...
    // });
    accountMenu.addItem(EXIT_OPTION, RETURN, Actions.SUCCESS);
    return accountMenu;
  }

  private Menu buildAdminSettingsMenu() {
    final Menu menu = new Menu("Settings >");

    menu.addItem(SET_USER_ALERT_LIMIT_OPTION, "Set users' alert limit",
        new ShowMessageAction(NOT_IMPLEMENTED_YET));
    menu.addItem(EXIT_OPTION, RETURN, Actions.SUCCESS);

    return menu;
  }
}
