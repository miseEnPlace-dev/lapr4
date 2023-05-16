package eapli.ecourse.exammanagement.domain.repositories;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.exammanagement.domain.Exam;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

public interface ExamRepository extends DomainRepository<Long, Exam> {

  default Optional<Exam> findById(final Long id) {
    return ofIdentity(id);
  }

  Iterable<Exam> findAllCourseExams(Course course);

}
