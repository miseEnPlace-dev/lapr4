package eapli.ecourse.app.student.console.presentation.courses;

import eapli.framework.actions.menu.Menu;

public class CoursesMenu {
  private static final int LIST_COURSES_OPTION = 1;
  private static final int REQUEST_ENROLMENT_OPTION = 2;

  public Menu buildMenu() {
    final Menu menu = new Menu("Courses >");

    menu.addItem(LIST_COURSES_OPTION, "List courses schedule", new ListCoursesUI()::show);
    menu.addItem(REQUEST_ENROLMENT_OPTION, "Request enrolment", new RequestEnrolmentUI()::show);

    return menu;
  }
}
