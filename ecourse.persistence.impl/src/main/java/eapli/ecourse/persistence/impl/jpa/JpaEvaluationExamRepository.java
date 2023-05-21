package eapli.ecourse.persistence.impl.jpa;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import eapli.ecourse.Application;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.eventsmanagement.domain.Time;
import eapli.ecourse.exammanagement.domain.ExamCode;
import eapli.ecourse.exammanagement.domain.evaluation.EvaluationExam;
import eapli.ecourse.exammanagement.repositories.EvaluationExamRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaEvaluationExamRepository extends JpaAutoTxRepository<EvaluationExam, ExamCode, ExamCode>
    implements EvaluationExamRepository {
  public JpaEvaluationExamRepository(final TransactionalContext autoTx) {
    super(autoTx, "code");
  }

  public JpaEvaluationExamRepository(final String puname) {
    super(puname, Application.settings().extendedPersistenceProperties(), "code");
  }

  public Optional<EvaluationExam> findByCode(final ExamCode code) {
    final Map<String, Object> params = new HashMap<>();
    params.put("code", code);
    return matchOne("e.code=:code", params);
  }

  @Override
  public Iterable<EvaluationExam> findAllCourseExams(Course course) {
    return match("e.course = :course", "course", course);
  }

  @Override
  public Iterable<EvaluationExam> findAllFutureCourseExams(Course course) {
    final Calendar currentDate = Calendar.getInstance();

    return match("e.course = :course AND e.startTime > :startTime", "course", course, "startTime",
        Time.valueOf(currentDate));
  }

}
