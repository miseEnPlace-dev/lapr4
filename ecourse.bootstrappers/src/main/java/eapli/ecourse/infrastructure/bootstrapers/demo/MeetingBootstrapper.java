package eapli.ecourse.infrastructure.bootstrapers.demo;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

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
      PersistenceContext.repositories().invites());

  @Override
  public boolean execute() {

    final Set<SystemUser> users = new HashSet<>();
    users.add(PersistenceContext.repositories().users().ofIdentity(Username.valueOf("user1")).orElse(null));

    controller.scheduleMeeting(Time.valueOf(Calendar.getInstance()), Duration.valueOf(120), users);

    return true;

  }

}
