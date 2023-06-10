package eapli.ecourse.app.board.console.presentation;

import eapli.ecourse.Application;
import eapli.ecourse.app.board.lib.BoardBackend;
import eapli.ecourse.usermanagement.dto.UserDTO;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.menu.HorizontalMenuRenderer;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

public class MainMenu extends AbstractUI {

  private static final String SEPARATOR_LABEL = "--------------";

  private static final int EXIT_OPTION = 0;

  private static final int COMMTEST_OPTION = 1;
  private static final int SHARE_BOARD_OPTION = 2;
  private static final int SESSION_INFO_OPTION = 9;

  private final Menu menu;
  private final MenuRenderer renderer;

  public MainMenu() {
    menu = buildMainMenu();
    renderer = getRenderer(menu);
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

  @Override
  public boolean doShow() {
    return renderer.render();
  }

  @Override
  public boolean show() {
    drawFormTitle();
    return doShow();
  }

  @Override
  public String headline() {
    UserDTO user = BoardBackend.getInstance().getCredentialStore().getUser().get();
    return "Welcome, " + user.getFullName();
  }

  private Menu buildMainMenu() {
    final Menu mainMenu = new Menu();

    mainMenu.addItem(COMMTEST_OPTION, "Send COMMTEST", new CommTestUI()::show);
    mainMenu.addItem(SHARE_BOARD_OPTION, "Share Board", new ShareBoardUI()::show);
    mainMenu.addItem(SESSION_INFO_OPTION, "Session Info", new SessionInfoUI()::show);

    if (!Application.settings().isMenuLayoutHorizontal()) {
      mainMenu.addItem(MenuItem.separator(SEPARATOR_LABEL));
    }

    mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye!"));

    return mainMenu;
  }
}
