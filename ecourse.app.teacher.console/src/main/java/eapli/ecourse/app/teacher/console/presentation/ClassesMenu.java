package eapli.ecourse.app.teacher.console.presentation;

import eapli.ecourse.app.teacher.console.presentation.courseclass.ScheduleClassUI;
import eapli.ecourse.app.teacher.console.presentation.courseclass.ScheduleExtraClassUI;
import eapli.ecourse.app.teacher.console.presentation.courseclass.UpdateClassScheduleUI;
import eapli.framework.actions.menu.Menu;

public class ClassesMenu {
  private static final int CHANGE_CLASS_SCHEDULE_OPTION = 1;
  private static final int SCHEDULE_CLASS_OPTION = 2;
  private static final int SCHEDULE_EXTRA_CLASS_OPTION = 3;

  public Menu buildMenu() {
    final Menu menu = new Menu("Classes >");

    menu.addItem(CHANGE_CLASS_SCHEDULE_OPTION, "Change class schedule",
        new UpdateClassScheduleUI()::show);
    menu.addItem(SCHEDULE_CLASS_OPTION, "Schedule class", new ScheduleClassUI()::show);
    menu.addItem(SCHEDULE_EXTRA_CLASS_OPTION, "Schedule extra class",
        new ScheduleExtraClassUI()::show);

    return menu;
  }
}
