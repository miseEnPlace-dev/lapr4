package eapli.ecourse.app.backoffice.console.presentation.course;

import eapli.ecourse.Application;
import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.menu.MenuItem;

public class CoursesMenu {
  private static final int CREATE_COURSES_OPTION = 1;
  private static final int LIST_COURSES_OPTION = 2;
  private static final int TOGGLE_COURSE_STATE_OPTION = 3;
  private static final int TOGGLE_COURSE_ENROLMENT_STATE_OPTION = 4;
  private static final int ADD_TEACHERS_OPTION = 5;
  private static final int EXIT_OPTION = 0;

  private static final String RETURN_LABEL = "Return ";

  private static final String SEPARATOR_LABEL = "--------------";

  /**
   * UI for managing courses in the application.
   *
   * @author Tomás Lopes <1211289>
   */
  public Menu buildCoursesMenu() {
    final Menu menu = new Menu("Courses >");

    menu.addItem(CREATE_COURSES_OPTION, "Create Courses", new CreateCourseUI()::show);
    menu.addItem(LIST_COURSES_OPTION, "List All Courses", new ListCoursesUI()::show);
    menu.addItem(TOGGLE_COURSE_STATE_OPTION, "Toggle Course State",
        new ToggleCourseStatusUI()::show);
    menu.addItem(TOGGLE_COURSE_ENROLMENT_STATE_OPTION, "Toggle Course Enrolment State",
        new ToggleCourseEnrolmentStateUI()::show);
    menu.addItem(ADD_TEACHERS_OPTION, "Add Teachers", new AssignNewTeachersToCourseUI()::show);

    if (!Application.settings().isMenuLayoutHorizontal())
      menu.addItem(MenuItem.separator(SEPARATOR_LABEL));

    menu.addItem(EXIT_OPTION, RETURN_LABEL, Actions.SUCCESS);

    return menu;
  }
}
