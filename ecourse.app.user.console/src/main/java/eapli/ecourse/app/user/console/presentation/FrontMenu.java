package eapli.ecourse.app.user.console.presentation;

import eapli.ecourse.app.common.console.presentation.authz.LoginUI;
import eapli.ecourse.app.user.console.presentation.myuser.SignupRequestAction;
import eapli.ecourse.infrastructure.authz.AuthenticationCredentialHandler;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.actions.ChainedAction;
import eapli.framework.actions.menu.Menu;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

/**
 * @author Paulo Gandra Sousa
 */
public class FrontMenu extends AbstractUI {

  private static final int EXIT_OPTION = 0;

  private static final int LOGIN_OPTION = 1;
  private static final int SIGNUP_OPTION = 2;

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
    final Menu menu = new Menu();
    menu.addItem(LOGIN_OPTION, "Login", new ChainedAction(
        new LoginUI(new AuthenticationCredentialHandler(), ClientRoles.CLIENT_USER)::show, () -> {
          new MainMenu().mainLoop();
          return true;
        }));
    menu.addItem(SIGNUP_OPTION, "Sign up", new ChainedAction(
        new SignupRequestAction(), () -> {
          new FrontMenu().mainLoop();
          return true;
        }));
    menu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye, Bye"));

    final MenuRenderer renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
    return renderer.render();
  }

  @Override
  public String headline() {
    return "eCOURSE";
  }
}
