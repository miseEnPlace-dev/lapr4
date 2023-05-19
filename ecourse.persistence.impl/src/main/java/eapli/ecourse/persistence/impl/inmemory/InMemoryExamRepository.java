package eapli.ecourse.persistence.impl.inmemory;

import java.util.Calendar;
import java.util.Optional;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.eventsmanagement.domain.Time;
import eapli.ecourse.exammanagement.domain.EvaluationExam;
import eapli.ecourse.exammanagement.domain.ExamCode;
import eapli.ecourse.exammanagement.domain.repositories.ExamRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryExamRepository extends InMemoryDomainRepository<EvaluationExam, ExamCode>
    implements ExamRepository {

  static {
    InMemoryInitializer.init();
  }

  public Optional<EvaluationExam> findByCode(final ExamCode examCode) {
    return Optional.of(data().get(examCode));
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
}
