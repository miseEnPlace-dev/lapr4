package eapli.ecourse.persistence.impl.jpa;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import eapli.ecourse.Application;
import eapli.ecourse.eventsmanagement.meetingmanagement.domain.Invite;
import eapli.ecourse.eventsmanagement.meetingmanagement.domain.InviteID;
import eapli.ecourse.eventsmanagement.meetingmanagement.domain.InviteStatus;
import eapli.ecourse.eventsmanagement.meetingmanagement.domain.MeetingID;
import eapli.ecourse.eventsmanagement.meetingmanagement.repositories.InviteRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaInviteRepository extends JpaAutoTxRepository<Invite, InviteID, InviteID>
    implements InviteRepository {
  public JpaInviteRepository(final TransactionalContext autoTx) {
    super(autoTx, "id");
  }

  public JpaInviteRepository(final String puname) {
    super(puname, Application.settings().extendedPersistenceProperties(), "id");
  }

  /**
   *
   * Returns the invites associated with the user
   */
  public Iterable<Invite> findByUsername(Username username) {
    return match("e.username = :username", "username", username);
  }

  /**
   * Returns the invites that are open
   *
   * @return
   */
  public Iterable<Invite> findAllPendingForUsername(Username username) {
    Map<String, Object> params = new HashMap<>();
    params.put("username", username);
    params.put("status", new InviteStatus(InviteStatus.Status.PENDING));
    return match("e.user.username = :username AND e.status = :status", params);
  }

  /**
   * Returns the invites that are not finished
   *
   * @return
   */
  public Iterable<Invite> findAllNotClosedForUsername(Username username) {
    return null;
  }

  /**
   * Returns the invites that are closed
   *
   * @return
   */
  public Iterable<Invite> findAllClosedForUsername(Username username) {
    return null;
  }

  /**
   * Returns the invites associated with the meeting
   *
   * @return
   */

  public Iterable<Invite> findByMeetingId(MeetingID meetingID) {
    return match("e.meeting.meetingID = :meetingID", "meetingID", meetingID);
  }

  /**
   * Returns the invites that are accepted for a meeting
   *
   * @return
   */

  public Iterable<Invite> findAllAcceptedForMeetingId(MeetingID meetingId) {
    return null;
  }

  /**
   * Returns the invites that are rejected for a meeting
   *
   * @return
   */
  public Iterable<Invite> findAllRejectedForMeetingId(MeetingID meetingId) {
    return null;
  }

  /**
   * Returns the invites that are pending for a meeting
   */
  public Iterable<Invite> findAllPendingForMeetingId(MeetingID meetingId) {
    return null;
  }

  @Override
  public Iterable<Invite> findAllAcceptedForUsername(Username username) {
    Map<String, Object> params = new HashMap<>();
    params.put("username", username);
    params.put("status", new InviteStatus(InviteStatus.Status.ACCEPTED));
    return match("e.user.username = :username AND e.status = :status", params);
  }

  @Override
  public Iterable<Invite> findByMeetingID(MeetingID meetingID) {
    return match("e.meeting.meetingID = :meetingID", "meetingID", meetingID);
  }

  @Override
  public Optional<Invite> findInviteById(InviteID id) {
    final Map<String, Object> params = new HashMap<>();
    params.put("id", id);
    return matchOne("e.id=:id", params);
  }
}
