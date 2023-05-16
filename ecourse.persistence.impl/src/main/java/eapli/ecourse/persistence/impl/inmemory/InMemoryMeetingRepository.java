package eapli.ecourse.persistence.impl.inmemory;

import java.util.Optional;

import eapli.ecourse.eventsmanagement.meetingmanagement.domain.Meeting;
import eapli.ecourse.eventsmanagement.meetingmanagement.domain.MeetingID;
import eapli.ecourse.eventsmanagement.meetingmanagement.repositories.MeetingRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryMeetingRepository extends InMemoryDomainRepository<Meeting, MeetingID>
    implements MeetingRepository {

  static {
    InMemoryInitializer.init();
  }

  @Override
  public Optional<Meeting> findById(final MeetingID id) {
    return Optional.of(data().get(id));
  }

  @Override
  public Iterable<Meeting> findAllIterableByUserMeetings() {
    return null;
  }
}
