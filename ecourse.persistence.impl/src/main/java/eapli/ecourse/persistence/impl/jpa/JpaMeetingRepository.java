package eapli.ecourse.persistence.impl.jpa;

import javax.persistence.TypedQuery;

import eapli.ecourse.Application;
import eapli.ecourse.eventsmanagement.meetingmanagement.domain.Meeting;
import eapli.ecourse.eventsmanagement.meetingmanagement.domain.MeetingID;
import eapli.ecourse.eventsmanagement.meetingmanagement.repositories.MeetingRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaMeetingRepository extends JpaAutoTxRepository<Meeting, MeetingID, MeetingID>
    implements MeetingRepository {

  public JpaMeetingRepository(final TransactionalContext autoTx) {
    super(autoTx, "id");
  }

  public JpaMeetingRepository(final String puname) {
    super(puname, Application.settings().extendedPersistenceProperties(), "id");
  }

  @Override
  public Iterable<Meeting> findNotCanceledMeetingsByOwner(SystemUser scheduledBy) {
    return match("e.scheduledBy = :scheduledBy AND e.canceledAt IS NULL", "scheduledBy", scheduledBy);
  }

  @Override
  public Iterable<Meeting> findMeetingsByOwner(SystemUser scheduledBy) {
    return match("e.scheduledBy = :scheduledBy", "scheduledBy", scheduledBy);
  }

  @Override
  public Iterable<Meeting> findMeetingsForUsername(Username username) {
    final TypedQuery<Meeting> query = entityManager().createQuery(
        "SELECT m FROM Meeting m JOIN Invite i ON i.meeting=m WHERE i.user.username = :username",
        Meeting.class);
    query.setParameter("username", username);
    return query.getResultList();
  }
}
