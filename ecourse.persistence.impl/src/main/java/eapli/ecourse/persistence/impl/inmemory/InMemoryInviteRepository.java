package eapli.ecourse.persistence.impl.inmemory;

import eapli.ecourse.eventsmanagement.meetingmanagement.domain.Invite;
import eapli.ecourse.eventsmanagement.meetingmanagement.domain.InviteID;
import eapli.ecourse.eventsmanagement.meetingmanagement.domain.Meeting;
import eapli.ecourse.eventsmanagement.meetingmanagement.domain.MeetingID;
import eapli.ecourse.eventsmanagement.meetingmanagement.repositories.InviteRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryInviteRepository extends InMemoryDomainRepository<Invite, InviteID> implements InviteRepository {

  static {
    InMemoryInitializer.init();
  }

  /**
   *
   * Returns the invites associated with the user
   */
  public Iterable<Invite> findByUsername(Username username) {
    return match(e -> e.user().username().equals(username));
  }

  /**
   * Returns the invites that are open
   *
   * @return
   */
  public Iterable<Invite> findAllPendingForUsername(Username username) {
    return match(e -> e.user().username().equals(username) && e.status().isPending());
  }

  /**
   * Returns the invites that are not finished
   *
   * @return
   */
  public Iterable<Invite> findAllAcceptedForUsername(Username username) {
    return match(e -> e.user().username().equals(username) && e.status().isAccepted());
  }

  /**
   * Returns the invites that are closed
   *
   * @return
   */
  Iterable<Invite> findAllRejectedForUsername(Username username) {
    return match(e -> e.user().username().equals(username) && e.status().isRejected());

  }

  public Iterable<Invite> findByMeetingID(MeetingID meetingID) {
    return (match(e -> e.meeting().identity().equals(meetingID)));
  }


  /**
   * Returns the invites that are accepted for a meeting
   *
   * @return
   */
  public Iterable<Invite> findAllAcceptedForMeetingId(MeetingID meetingId) {
    return match(e -> e.meeting().identity().equals(meetingId) && e.status().isAccepted());
  }

  /**
   * Returns the invites that are rejected for a meeting
   *
   * @return
   */
  public Iterable<Invite> findAllRejectedForMeetingId(MeetingID meetingId) {
    return match(e -> e.meeting().identity().equals(meetingId) && e.status().isRejected());
  }

  /**
   * Returns the invites that are pending for a meeting
   */
  public Iterable<Invite> findAllPendingForMeetingId(MeetingID meetingId) {
    return match(e -> e.meeting().identity().equals(meetingId) && e.status().isPending());
  }
}
