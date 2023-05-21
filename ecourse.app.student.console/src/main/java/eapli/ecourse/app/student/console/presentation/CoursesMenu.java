package eapli.ecourse.app.student.console.presentation;

import eapli.ecourse.app.student.console.presentation.courses.ListCoursesUI;
import eapli.framework.actions.menu.Menu;

public class CoursesMenu {
  private static final int LIST_COURSES_OPTION = 1;

  public Menu buildMenu() {
    final Menu menu = new Menu("Courses >");

    menu.addItem(LIST_COURSES_OPTION, "List courses schedule", new ListCoursesUI()::show);

    return menu;
  }
}
