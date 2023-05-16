package eapli.ecourse.persistence.impl.jpa;

import eapli.ecourse.Application;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.exammanagement.domain.Exam;
import eapli.ecourse.exammanagement.domain.ExamCode;
import eapli.ecourse.exammanagement.domain.repositories.ExamRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

public class JpaExamRepository extends JpaAutoTxRepository<Exam, ExamCode, ExamCode> implements ExamRepository {
  public JpaExamRepository(final TransactionalContext autoTx) {
    super(autoTx, "code");
  }

  public JpaExamRepository(final String puname) {
    super(puname, Application.settings().extendedPersistenceProperties(), "code");
  }

  public Optional<Exam> findById(final ExamCode code) {
    final Map<String, Object> params = new HashMap<>();
    params.put("code", code);
    return matchOne("e.code=:code", params);
  }

  @Override
  public Iterable<Exam> findAllCourseExams(Course course) {
    return match("e.course = :course", "course", course);
  }

}
