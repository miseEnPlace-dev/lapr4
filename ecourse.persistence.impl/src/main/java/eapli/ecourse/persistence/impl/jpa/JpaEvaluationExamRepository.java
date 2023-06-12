package eapli.ecourse.persistence.impl.jpa;

import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

import javax.persistence.TypedQuery;

import eapli.ecourse.Application;
import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.eventsmanagement.domain.Time;
import eapli.ecourse.exammanagement.domain.ExamIdentifier;
import eapli.ecourse.exammanagement.domain.evaluation.EvaluationExam;
import eapli.ecourse.exammanagement.repositories.EvaluationExamRepository;
import eapli.ecourse.studentmanagement.domain.MecanographicNumber;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaEvaluationExamRepository extends JpaAutoTxRepository<EvaluationExam, ExamIdentifier, ExamIdentifier>
    implements EvaluationExamRepository {
  public JpaEvaluationExamRepository(final TransactionalContext autoTx) {
    super(autoTx, "identifier");
  }

  public JpaEvaluationExamRepository(final String puname) {
    super(puname, Application.settings().extendedPersistenceProperties(), "identifier");
  }

  public Optional<EvaluationExam> findByCode(final ExamIdentifier identifier) {
    final Map<String, Object> params = new HashMap<>();
    params.put("identifier", identifier);
    return matchOne("e.identifier=:identifier", params);
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

  @Override
  public Iterable<EvaluationExam> findAllPastCourseExams(Course course) {
    final Calendar currentDate = Calendar.getInstance();

    return match("e.course = :course AND e.startTime < :startTime", "course", course, "startTime",
        Time.valueOf(currentDate));
  }

  public Iterable<EvaluationExam> findAllCourseExamsWithNoAnswersFromStudent(CourseCode code,
      MecanographicNumber number) {
    final TypedQuery<EvaluationExam> query = entityManager().createQuery(
        "SELECT e FROM EvaluationExam e WHERE e NOT IN (SELECT a.exam FROM Answer a WHERE a.student = :number) AND e.course.code = :code",
        EvaluationExam.class);
    query.setParameter("code", code);
    query.setParameter("number", number);
    return query.getResultList();
  }
}
