package eapli.ecourse.persistence.impl.inmemory;

import eapli.ecourse.eventsmanagement.meetingmanagement.domain.Meeting;
import eapli.ecourse.eventsmanagement.meetingmanagement.domain.MeetingID;
import eapli.ecourse.eventsmanagement.meetingmanagement.repositories.MeetingRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryMeetingRepository extends InMemoryDomainRepository<Meeting, MeetingID>
    implements MeetingRepository {

}
