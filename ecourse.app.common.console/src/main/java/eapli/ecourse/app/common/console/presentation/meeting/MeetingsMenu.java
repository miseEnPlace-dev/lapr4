package eapli.ecourse.app.common.console.presentation.meeting;

import eapli.framework.actions.Actions;
import eapli.framework.actions.menu.Menu;

public class MeetingsMenu {
  private static final int SCHEDULE_MEETING = 1;
  private static final int LIST_MEETINGS = 2;
  private static final int CHECK_INVITES = 3;
  private static final int CANCEL_MEETING = 4;
  private static final int EXIT_OPTION = 0;

  private static final String RETURN = "Return ";

  public Menu buildMenu() {
    final Menu menu = new Menu("Meetings >");

    menu.addItem(SCHEDULE_MEETING, "Schedule Meeting", new ScheduleMeetingUI()::show);
    menu.addItem(LIST_MEETINGS, "List Meetings", new ListMeetingUI()::show);
    menu.addItem(CHECK_INVITES, "Accept or Reject invite", new MeetingResponseUI()::show);
    menu.addItem(CANCEL_MEETING, "Cancel a Meeting", new CancelMeetingUI()::show);
    menu.addItem(EXIT_OPTION, RETURN, Actions.SUCCESS);

    return menu;
  }
}
