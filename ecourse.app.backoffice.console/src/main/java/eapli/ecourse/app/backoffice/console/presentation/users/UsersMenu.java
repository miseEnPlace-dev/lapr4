package eapli.ecourse.app.backoffice.console.presentation.users;

import eapli.ecourse.app.backoffice.console.presentation.authz.AddUserUI;
import eapli.ecourse.app.backoffice.console.presentation.authz.DeactivateUserAction;
import eapli.ecourse.app.backoffice.console.presentation.authz.ListUsersAction;
import eapli.ecourse.app.backoffice.console.presentation.student.AcceptRefuseSignupRequestAction;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;

public class UsersMenu {
  private static final int ADD_USER_OPTION = 1;
  private static final int LIST_USERS_OPTION = 2;
  private static final int DEACTIVATE_USER_OPTION = 3;
  private static final int ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION = 4;
  private static final int EXIT_OPTION = 0;

  private static final String RETURN_LABEL = "Return ";

  public Menu buildUsersMenu() {
    final Menu menu = new Menu("Users >");

    menu.addItem(ADD_USER_OPTION, "Add User", new AddUserUI()::show);
    menu.addItem(LIST_USERS_OPTION, "List all Users", new ListUsersAction());
    menu.addItem(DEACTIVATE_USER_OPTION, "Deactivate User", new DeactivateUserAction());
    menu.addItem(ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION, "Accept/Refuse Signup Request",
        new AcceptRefuseSignupRequestAction());
    menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

    return menu;
  }
}
