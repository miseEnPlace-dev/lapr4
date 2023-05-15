package eapli.ecourse.eventsmanagement.meetingmanagement.repositories;

import eapli.ecourse.eventsmanagement.meetingmanagement.domain.Meeting;
import eapli.ecourse.eventsmanagement.meetingmanagement.domain.MeetingID;
import eapli.framework.domain.repositories.DomainRepository;

public interface MeetingRepository extends DomainRepository<MeetingID, Meeting> {

}
