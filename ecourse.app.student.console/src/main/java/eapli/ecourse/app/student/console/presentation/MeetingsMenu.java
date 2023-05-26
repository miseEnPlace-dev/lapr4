package eapli.ecourse.app.student.console.presentation;

import eapli.ecourse.app.common.console.presentation.meeting.MeetingResponseUI;
import eapli.ecourse.app.common.console.presentation.meeting.ScheduleMeetingUI;
import eapli.framework.actions.menu.Menu;

public class MeetingsMenu {
  private static final int SCHEDULE_MEETING = 1;
  private static final int LIST_MEETINGS = 2;
  private static final int CHECK_INVITES = 3;

  public Menu buildMenu() {
    final Menu menu = new Menu("Meetings >");

    menu.addItem(SCHEDULE_MEETING, "Schedule Meeting", new ScheduleMeetingUI()::show);
    // menu.addItem(LIST_MEETINGS, "List Meetings", new ()::show);
    menu.addItem(CHECK_INVITES, "Accept or Reject invite", new MeetingResponseUI()::show);

    return menu;
  }
}