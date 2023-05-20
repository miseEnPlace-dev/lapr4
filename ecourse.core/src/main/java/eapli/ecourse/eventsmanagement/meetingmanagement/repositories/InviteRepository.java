package eapli.ecourse.eventsmanagement.meetingmanagement.repositories;

import eapli.ecourse.eventsmanagement.meetingmanagement.domain.Invite;
import eapli.ecourse.eventsmanagement.meetingmanagement.domain.InviteID;
import eapli.ecourse.eventsmanagement.meetingmanagement.domain.MeetingID;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.Username;

public interface InviteRepository extends DomainRepository<InviteID, Invite> {
  /**
   *
   * Returns the invites associated with the user
   */
  Iterable<Invite> findByUsername(Username username);

  /**
   * Returns the invites that are pending for a user
   *
   * @return
   */
  Iterable<Invite> findAllPendingForUsername(Username username);

  /**
   * Returns the invites that are accepted for a user
   *
   * @return
   */
  Iterable<Invite> findAllAcceptedForUsername(Username username);

  /**
   * Returns the invites that are accepted for a meeting
   *
   * @return
   */
  Iterable<Invite> findAllAcceptedForMeetingId(MeetingID meetingId);

  /**
   * Returns the invites that are rejected for a meeting
   *
   * @return
   */
  Iterable<Invite> findAllRejectedForMeetingId(MeetingID meetingId);

  /**
   * Returns the invites that are pending for a meeting
   */
  Iterable<Invite> findAllPendingForMeetingId(MeetingID meetingId);
}
