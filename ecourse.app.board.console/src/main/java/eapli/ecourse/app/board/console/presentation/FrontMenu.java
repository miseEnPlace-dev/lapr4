package eapli.ecourse.app.board.console.presentation;

import eapli.ecourse.AppSettings;
import eapli.ecourse.app.board.lib.BoardBackend;
import eapli.ecourse.app.board.lib.BoardHttpServer;
import eapli.ecourse.app.common.console.presentation.authz.LoginUI;
import eapli.framework.actions.ChainedAction;
import eapli.framework.actions.menu.Menu;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

public class FrontMenu extends AbstractUI {
  private static final int EXIT_OPTION = 0;
  private static final int LOGIN_OPTION = 1;

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
    BoardBackend boardBackend = BoardBackend.getInstance();
    AppSettings appSettings = new AppSettings();

    boolean isSecure = appSettings.isSSLEnabled();

    final Menu menu = new Menu();

    menu.addItem(LOGIN_OPTION, "Login",
        new ChainedAction(new LoginUI(boardBackend.getCredentialStore().AUTHENTICATE)::show, () -> {
          // start the board http server
          BoardHttpServer.run(isSecure);

          // next ui
          new MainMenu().mainLoop();

          return false;
        }));

    menu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye!"));

    final MenuRenderer renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
    return renderer.render();
  }

  @Override
  public String headline() {
    return "Shared Board App Login Menu";
  }
}
