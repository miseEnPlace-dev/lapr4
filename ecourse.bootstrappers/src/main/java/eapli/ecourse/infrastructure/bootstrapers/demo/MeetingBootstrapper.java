package eapli.ecourse.infrastructure.bootstrapers.demo;

import java.util.ArrayList;
import java.util.Calendar;

import eapli.ecourse.eventsmanagement.domain.Duration;
import eapli.ecourse.eventsmanagement.domain.Time;
import eapli.ecourse.eventsmanagement.meetingmanagement.application.ScheduleMeetingController;
import eapli.ecourse.infrastructure.bootstrapers.UsersBootstrapperBase;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;

public class MeetingBootstrapper extends UsersBootstrapperBase implements Action {
  private final AuthorizationService authz = AuthzRegistry.authorizationService();
  private ScheduleMeetingController controller = new ScheduleMeetingController(
      PersistenceContext.repositories().meetings(), authz,
      PersistenceContext.repositories().invites(), PersistenceContext.repositories().classes(),
      PersistenceContext.repositories().extraordinaryClasses(), PersistenceContext.repositories().enrollments(),
      PersistenceContext.repositories().students(), PersistenceContext.repositories().teachers(),
      AuthzRegistry.userService());

  @Override
  public boolean execute() {

    final ArrayList<SystemUser> users = new ArrayList<>();
    users.add(PersistenceContext.repositories().users().ofIdentity(Username.valueOf("isep959")).orElse(null));

    Username username = Username.valueOf("user1");
    SystemUser user = AuthzRegistry.userService().userOfIdentity(username).orElseThrow(IllegalStateException::new);

    controller.scheduleMeeting(user, Time.valueOf(Calendar.getInstance()), Duration.valueOf(120), users);

    return true;

  }

}
