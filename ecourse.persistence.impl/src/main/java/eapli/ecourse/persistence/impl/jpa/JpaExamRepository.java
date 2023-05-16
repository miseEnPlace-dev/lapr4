package eapli.ecourse.persistence.impl.jpa;

import eapli.ecourse.Application;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.exammanagement.domain.Exam;
import eapli.ecourse.exammanagement.domain.repositories.ExamRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class JpaExamRepository extends JpaAutoTxRepository<Exam, Long, Long> implements ExamRepository {
  public JpaExamRepository(final TransactionalContext autoTx) {
    super(autoTx, "id");
  }

  public JpaExamRepository(final String puname) {
    super(puname, Application.settings().extendedPersistenceProperties(), "id");
  }

  public Optional<Exam> findById(final Long id) {
    final Map<String, Object> params = new HashMap<>();
    params.put("id", id);
    return matchOne("e.id=:id", params);
  }

  @Override
  public Iterable<Exam> findAllCourseExams(Course course) {
    return match("e.course = :course", "course", course);
  }

}
