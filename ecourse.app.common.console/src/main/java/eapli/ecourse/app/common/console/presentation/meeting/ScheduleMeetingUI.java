package eapli.ecourse.app.common.console.presentation.meeting;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import eapli.ecourse.app.common.console.presentation.authz.SystemUserPrinter;
import eapli.ecourse.app.common.console.util.MultipleSelector;
import eapli.ecourse.eventsmanagement.domain.Duration;
import eapli.ecourse.eventsmanagement.domain.Time;
import eapli.ecourse.eventsmanagement.meetingmanagement.application.ScheduleMeetingController;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.application.UserManagementService;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

public class ScheduleMeetingUI extends AbstractUI {

  private UserManagementService userManagementService = AuthzRegistry.userService();

  private final ScheduleMeetingController ctrl = new ScheduleMeetingController(
      PersistenceContext.repositories().meetings(),
      AuthzRegistry.authorizationService(), PersistenceContext.repositories().invites());

  @Override
  protected boolean doShow() {
    Calendar time = Console.readCalendar("Time (HH:MM): ", "HH:mm");
    Time meetingTime = Time.valueOf(time);

    Integer duration = 0;

    while (duration <= 0) {
      duration = Console.readInteger("Duration (minutes): ");
    }

    Duration meetingDuration = Duration.valueOf(duration);

    List<SystemUser> allUsers = (ArrayList<SystemUser>) userManagementService.allUsers();

    if (!allUsers.iterator().hasNext()) {
      System.out.println("There are no registered users.");
      return false;
    }

    MultipleSelector<SystemUser> selector = new MultipleSelector<>("Users:", allUsers,
        new SystemUserPrinter());
    Iterable<SystemUser> selectedUsers = selector.selectElements();

    System.out.println("\nDo you want to submit the data? [Y/N]");

    String confirm = "";
    while (!confirm.equals("Y") && !confirm.equals("N")) {
      confirm = Console.readLine("Option: ").toUpperCase();
    }

    if (confirm.equals("N")) {
      System.out.println("Operation Cancelled!");
      return false;
    }

    try {
      ctrl.scheduleMeeting(meetingTime, meetingDuration, selectedUsers);
    } catch (IllegalStateException e) {
      System.out.println(e.getMessage());
      System.out.println("Error creating meeting.");
      return false;
    }

    System.out.println("\nMeeting successfully created");
    Console.readLine("Press Enter to continue...");

    return false;
  }

  @Override
  public String headline() {
    return "Schedule Meeting";
  }

}
