package eapli.ecourse.eventsmanagement.meetingmanagement.repositories;

import java.util.Optional;

import eapli.ecourse.eventsmanagement.meetingmanagement.domain.Meeting;
import eapli.ecourse.eventsmanagement.meetingmanagement.domain.MeetingID;
import eapli.framework.domain.repositories.DomainRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;

public interface MeetingRepository extends DomainRepository<MeetingID, Meeting> {

  /**
   * Returns the meeting with the given id.
   *
   * @param id
   * @return
   */
  default Optional<Meeting> findById(final MeetingID id) {
    return ofIdentity(id);
  }

  public Iterable<Meeting> findNotCanceledMeetingsByOwner(SystemUser owner);

  public Iterable<Meeting> findMeetingsByOwner(SystemUser owner);

  public Iterable<Meeting> findMeetingsForUsername(Username username);
}
