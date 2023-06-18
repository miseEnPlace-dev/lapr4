package eapli.ecourse.app.common.console.presentation.meeting;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import eapli.ecourse.app.common.console.presentation.authz.SystemUserPrinter;
import eapli.ecourse.app.common.console.util.ConsoleConstrainedReader;
import eapli.ecourse.app.common.console.util.MultipleSelectorWidget;
import eapli.ecourse.eventsmanagement.domain.Duration;
import eapli.ecourse.eventsmanagement.domain.Time;
import eapli.ecourse.eventsmanagement.meetingmanagement.application.ScheduleMeetingController;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

public class ScheduleMeetingUI extends AbstractUI {
  private ScheduleMeetingController ctrl;

  @Override
  protected boolean doShow() {
    ctrl = new ScheduleMeetingController(
        PersistenceContext.repositories().meetings(),
        AuthzRegistry.authorizationService(), PersistenceContext.repositories().invites(),
        PersistenceContext.repositories().classes(), PersistenceContext.repositories().extraordinaryClasses(),
        PersistenceContext.repositories().enrollments(), PersistenceContext.repositories().students(),
        PersistenceContext.repositories().teachers(), AuthzRegistry.userService());

    Calendar time;

    time = ConsoleConstrainedReader.readNonPastCalendar("\nMeeting Time (dd/mm/yyyy) HH:mm):", "dd/MM/yyyy HH:mm");

    Time meetingTime = Time.valueOf(time);

    int durationInt = ConsoleConstrainedReader.readPositiveInteger("\nDuration (minutes):");

    Duration meetingDuration = Duration.valueOf(durationInt);

    List<SystemUser> allUsers = this.ctrl.getUsers();

    if (!allUsers.iterator().hasNext()) {
      System.out.println("There are no registered users.");
      return false;
    }

    MultipleSelectorWidget<SystemUser> selector = new MultipleSelectorWidget<>("\nUsers:", allUsers,
        new SystemUserPrinter());
    ArrayList<SystemUser> selectedUsers = (ArrayList<SystemUser>) selector.selectElements();

    if (selectedUsers.isEmpty()) {
      System.out.println("No users selected. Please select at least one user. Operation cancelled.");
      return false;
    }

    if (!ctrl.checkIfUsersAreAvailable(meetingTime, meetingDuration, selectedUsers)) {
      System.out.println("Some of the selected users are not available at the given time.");
      return false;
    }

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
