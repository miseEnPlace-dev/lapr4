package eapli.ecourse.app.backoffice.console.presentation.users;

import eapli.ecourse.app.backoffice.console.presentation.authz.AddUserUI;
import eapli.ecourse.app.backoffice.console.presentation.authz.DeactivateUserAction;
import eapli.ecourse.app.backoffice.console.presentation.authz.ListUsersAction;
import eapli.ecourse.app.backoffice.console.presentation.student.AcceptRefuseSignupRequestAction;
import eapli.ecourse.app.backoffice.console.presentation.student.ListStudentsUI;
import eapli.ecourse.app.backoffice.console.presentation.teacher.ListTeachersUI;
import eapli.ecourse.app.common.console.presentation.meeting.ScheduleMeetingUI;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;

public class UsersMenu {
  private static final int ADD_USER_OPTION = 1;
  private static final int LIST_USERS_OPTION = 2;
  private static final int DEACTIVATE_USER_OPTION = 3;
  private static final int ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION = 4;
  private static final int SCHEDULE_MEETING = 5;
  private static final int EXIT_OPTION = 0;

  // LIST USERS SUBMENU
  private static final int LIST_STUDENTS_OPTION = 1;
  private static final int LIST_TEACHERS_OPTION = 2;
  private static final int LIST_MANAGERS_OPTION = 3;
  private static final int LIST_ALL_OPTION = 4;

  private static final String RETURN_LABEL = "Return ";

  public Menu buildUsersMenu() {
    final Menu menu = new Menu("Users >");

    final Menu listUsersMenu = new Menu("List Users >");

    listUsersMenu.addItem(LIST_STUDENTS_OPTION, "List Students", new ListStudentsUI()::show);
    listUsersMenu.addItem(LIST_TEACHERS_OPTION, "List Teachers", new ListTeachersUI()::show);
    listUsersMenu.addItem(LIST_MANAGERS_OPTION, "List Managers", new ListManagersUI()::show);

    listUsersMenu.addItem(LIST_ALL_OPTION, "List All", new ListUsersAction());

    menu.addItem(ADD_USER_OPTION, "Add User", new AddUserUI()::show);
    menu.addSubMenu(LIST_USERS_OPTION, listUsersMenu);
    menu.addItem(DEACTIVATE_USER_OPTION, "Deactivate User", new DeactivateUserAction());
    menu.addItem(ACCEPT_REFUSE_SIGNUP_REQUEST_OPTION, "Accept/Refuse Signup Request",
        new AcceptRefuseSignupRequestAction());
    menu.addItem(SCHEDULE_MEETING, "Schedule Meeting", new ScheduleMeetingUI()::show);
    menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

    return menu;
  }
}
