package eapli.ecourse.persistence.impl.inmemory;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.exammanagement.domain.Exam;
import eapli.ecourse.exammanagement.domain.repositories.ExamRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

import java.util.Optional;

public class InMemoryExamRepository extends InMemoryDomainRepository<Exam, Long> implements ExamRepository {

  static {
    InMemoryInitializer.init();
  }

  public Optional<Exam> findById(Long id) {
    return ofIdentity(id);
  }

  @Override
  public Iterable<Exam> findAllCourseExams(Course course) {
    return match(e -> e.course().equals(course));
  }
}
