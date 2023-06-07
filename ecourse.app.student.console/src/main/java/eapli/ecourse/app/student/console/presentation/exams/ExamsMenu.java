package eapli.ecourse.app.student.console.presentation.exams;

import eapli.framework.actions.menu.Menu;
import eapli.framework.actions.Actions;

public class ExamsMenu {
  private static final int VIEW_FUTURE_EXAMS_OPTION = 1;
  private static final int LIST_GRADES_OPTION = 2;
  private static final int EXIT_OPTION = 0;

  private static final String RETURN = "Return ";

  public Menu buildMenu() {
    final Menu menu = new Menu("Exams >");

    menu.addItem(VIEW_FUTURE_EXAMS_OPTION, "View future exams", new ListFutureExamsUI()::show);
    menu.addItem(LIST_GRADES_OPTION, "List Grades", new ListStudentGradesUI()::show);
    menu.addItem(EXIT_OPTION, RETURN, Actions.SUCCESS);

    return menu;
  }
}
