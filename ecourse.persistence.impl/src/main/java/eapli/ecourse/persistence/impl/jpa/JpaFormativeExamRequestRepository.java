package eapli.ecourse.persistence.impl.jpa;

import java.util.Optional;

import javax.persistence.TypedQuery;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.exammanagement.domain.ExamIdentifier;
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

  @Override
  public Iterable<FormativeExamRequest> findAllFormativeRequestByCourse(Optional<Course> course) {
    TypedQuery<FormativeExamRequest> query = createQuery(
        "SELECT f FROM FormativeExamRequest f WHERE f.course = :course", FormativeExamRequest.class);

    query.setParameter("course", course);

    return query.getResultList();
  }
}
