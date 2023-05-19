package eapli.ecourse.app.backoffice.console.presentation.enrolment;

import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;

public class EnrolmentsMenu {

  private static final int RESPOND_COURSE_APPLICATIONS_OPTION = 1;
  private static final int EXIT_OPTION = 0;

  private static final String RETURN_LABEL = "Return ";

  public Menu buildEnrolmentsMenu() {
    final Menu menu = new Menu("Enrolments >");

    menu.addItem(RESPOND_COURSE_APPLICATIONS_OPTION, "Respond Course Applications",
        new RespondCourseApplicationsUI()::show);
    menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

    return menu;

  }
}