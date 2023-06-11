package eapli.ecourse.persistence.impl.inmemory;

import java.util.Calendar;
import java.util.Optional;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.eventsmanagement.domain.Time;
import eapli.ecourse.exammanagement.domain.ExamIdentifier;
import eapli.ecourse.exammanagement.domain.evaluation.EvaluationExam;
import eapli.ecourse.exammanagement.repositories.EvaluationExamRepository;
import eapli.ecourse.studentmanagement.domain.MecanographicNumber;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryEvaluationExamRepository extends InMemoryDomainRepository<EvaluationExam, ExamIdentifier>
    implements EvaluationExamRepository {

  static {
    InMemoryInitializer.init();
  }

  public Optional<EvaluationExam> findByCode(final ExamIdentifier identifier) {
    return Optional.of(data().get(identifier));
  }

  @Override
  public Iterable<EvaluationExam> findAllCourseExams(Course course) {
    return match(e -> e.course().equals(course));
  }

  @Override
  public Iterable<EvaluationExam> findAllFutureCourseExams(Course course) {
    final Calendar currentDate = Calendar.getInstance();

    return match(e -> e.course().equals(course) && e.startTime().compareTo(Time.valueOf(currentDate)) > 0);
  }

  @Override
  public Iterable<EvaluationExam> findAllPastCourseExams(Course course) {
    final Calendar currentDate = Calendar.getInstance();

    return match(e -> e.course().equals(course) && e.startTime().compareTo(Time.valueOf(currentDate)) < 0);
  }

  public Iterable<EvaluationExam> findAllCourseExamsWithNoAnswersFromStudent(CourseCode code,
      MecanographicNumber number) {
    throw new UnsupportedOperationException();
  }
}
