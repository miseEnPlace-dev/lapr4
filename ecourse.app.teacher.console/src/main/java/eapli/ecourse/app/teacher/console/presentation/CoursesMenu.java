package eapli.ecourse.app.teacher.console.presentation;

import eapli.ecourse.Application;
import eapli.ecourse.app.teacher.console.presentation.courses.ListCoursesUI;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;

public class CoursesMenu {
  private static final int LIST_COURSES_OPTION = 1;

  private static final int RETURN_OPTION = 0;
  private static final String RETURN = "Return ";

  private static final String SEPARATOR_LABEL = "--------------";

  public Menu buildMenu() {
    final Menu menu = new Menu("Courses >");

    menu.addItem(LIST_COURSES_OPTION, "List courses schedule", new ListCoursesUI()::show);

    if (!Application.settings().isMenuLayoutHorizontal())
      menu.addItem(MenuItem.separator(SEPARATOR_LABEL));

    menu.addItem(RETURN_OPTION, RETURN, Actions.SUCCESS);

    return menu;
  }
}
