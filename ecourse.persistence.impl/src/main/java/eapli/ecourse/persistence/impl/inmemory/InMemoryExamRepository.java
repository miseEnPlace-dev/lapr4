package eapli.ecourse.persistence.impl.inmemory;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.eventsmanagement.domain.Time;
import eapli.ecourse.exammanagement.domain.Exam;
import eapli.ecourse.exammanagement.domain.ExamCode;
import eapli.ecourse.exammanagement.domain.repositories.ExamRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.Calendar;
import java.util.Optional;

public class InMemoryExamRepository extends InMemoryDomainRepository<Exam, ExamCode> implements ExamRepository {

  static {
    InMemoryInitializer.init();
  }

  public Optional<Exam> findById(ExamCode id) {
    return ofIdentity(id);
  }

  @Override
  public Iterable<Exam> findAllCourseExams(Course course) {
    return match(e -> e.course().equals(course));
  }

  @Override
  public Iterable<Exam> findAllFutureCourseExams(Course course) {
    final Calendar currentDate = Calendar.getInstance();

    return match(e -> e.course().equals(course) && e.startTime().compareTo(Time.valueOf(currentDate)) > 0);
  }
}
