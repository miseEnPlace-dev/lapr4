package eapli.ecourse.app.board.console.presentation;

import eapli.ecourse.Application;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.menu.HorizontalMenuRenderer;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

public class MainMenu extends AbstractUI {

  private static final String SEPARATOR_LABEL = "--------------";

  private static final int EXIT_OPTION = 0;

  // MAIN MENU
  // private static final int SETTINGS_OPTION = 1;

  private final AuthorizationService authz = AuthzRegistry.authorizationService();

  private final Menu menu;
  private final MenuRenderer renderer;

  public MainMenu() {
    menu = buildMainMenu();
    renderer = getRenderer(menu);
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
    return authz.session().map(s -> "Client [ @" + s.authenticatedUser().identity() + " ]")
        .orElse("Client [ ==Anonymous== ]");
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

    mainMenu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

    return mainMenu;
  }
}
