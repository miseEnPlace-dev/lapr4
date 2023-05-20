package eapli.ecourse.app.teacher.console.presentation;

import eapli.ecourse.app.teacher.console.presentation.courseclass.UpdateClassScheduleUI;
import eapli.framework.actions.menu.Menu;

public class ClassesMenu {
  private static final int CHANGE_CLASS_SCHEDULE_OPTION = 1;

  public Menu buildMenu() {
    final Menu menu = new Menu("Classes");

    menu.addItem(CHANGE_CLASS_SCHEDULE_OPTION, "Change class schedule", new UpdateClassScheduleUI()::show);

    return menu;
  }
}
