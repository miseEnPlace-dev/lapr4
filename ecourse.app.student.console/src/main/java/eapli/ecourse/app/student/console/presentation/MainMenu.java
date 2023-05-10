package eapli.ecourse.app.student.console.presentation;

import eapli.ecourse.app.common.console.presentation.authz.MyUserMenu;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.ShowMessageAction;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
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
  private static final int SETTINGS_OPTION = 3;

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
    final var renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
    return renderer.render();
  }

  private Menu buildMainMenu() {
    final Menu mainMenu = new Menu();

    final Menu myUserMenu = new MyUserMenu();
    mainMenu.addSubMenu(MY_USER_OPTION, myUserMenu);

    mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));

    final Menu accountMenu = buildAccountMenu();
    mainMenu.addSubMenu(ACCOUNT_OPTION, accountMenu);

    mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));

    final Menu settingsMenu = buildAdminSettingsMenu();
    mainMenu.addSubMenu(SETTINGS_OPTION, settingsMenu);

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
