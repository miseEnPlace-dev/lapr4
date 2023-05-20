package eapli.ecourse.persistence.impl.jpa;

import eapli.ecourse.Application;
import eapli.ecourse.eventsmanagement.meetingmanagement.domain.Meeting;
import eapli.ecourse.eventsmanagement.meetingmanagement.domain.MeetingID;
import eapli.ecourse.eventsmanagement.meetingmanagement.repositories.MeetingRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaMeetingRepository extends JpaAutoTxRepository<Meeting, MeetingID, MeetingID>
    implements MeetingRepository {

  public JpaMeetingRepository(final TransactionalContext autoTx) {
    super(autoTx, "id");
  }

  public JpaMeetingRepository(final String puname) {
    super(puname, Application.settings().extendedPersistenceProperties(), "id");
  }
}
