package isep.ui;

import eapli.framework.actions.menu.Menu;
import eapli.framework.presentation.console.AbstractUI;
import eapli.framework.presentation.console.ExitWithMessageAction;
import eapli.framework.presentation.console.menu.MenuItemRenderer;
import eapli.framework.presentation.console.menu.MenuRenderer;
import eapli.framework.presentation.console.menu.VerticalMenuRenderer;

public class MainUI extends AbstractUI {
  private final int EXIT_OPTION = 0;
  private final int LOGIN_OPTION = 1;
  private final int SIGNUP_OPTION = 2;
  private final int KNOW_THE_DEVELOPMENT_TEAM_OPTION = 3;

  @Override
  public boolean show() {
    super.drawFormTitle();
    return doShow();
  }

  @Override
  public boolean doShow() {
    final Menu menu = new Menu();
    /*
     * menu.addItem(LOGIN_OPTION, "Login", LoginRequestAction());
     * menu.addItem(SIGNUP_OPTION, "Sign Up", SignUpRequestAction());
     */
    menu.addItem(KNOW_THE_DEVELOPMENT_TEAM_OPTION, "Know the development team",
        new KnowTheDevelopmentTeamAction());
    menu.addItem(EXIT_OPTION, "Exit", new ExitWithMessageAction("Bye!"));

    final MenuRenderer renderer = new VerticalMenuRenderer(menu, MenuItemRenderer.DEFAULT);
    return renderer.render();
  }

  @Override
  public String headline() {
    return "eCourse";
  }
}
