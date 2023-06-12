package eapli.ecourse.persistence.impl.jpa;

import java.util.Map;
import java.util.Optional;

import eapli.ecourse.eventsmanagement.meetingmanagement.domain.Invite;
import eapli.ecourse.eventsmanagement.meetingmanagement.domain.InviteID;
import eapli.ecourse.exammanagement.domain.ExamIdentifier;
import eapli.ecourse.exammanagement.domain.formative.FormativeExam;
import eapli.ecourse.exammanagement.domain.formative.FormativeExamRequest;
import eapli.ecourse.exammanagement.repositories.FormativeExamRequestRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaFormativeExamRequestRepository
    extends JpaAutoTxRepository<FormativeExamRequest, ExamIdentifier, ExamIdentifier>
    implements FormativeExamRequestRepository {

  public JpaFormativeExamRequestRepository(final TransactionalContext autoTx) {
    super(autoTx, "id");
  }

  public JpaFormativeExamRequestRepository(final String puname) {
    super(puname, "id");
  }
}
