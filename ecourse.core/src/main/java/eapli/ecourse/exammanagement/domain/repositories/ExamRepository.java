package eapli.ecourse.exammanagement.domain.repositories;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.exammanagement.domain.Exam;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

public interface ExamRepository extends DomainRepository<Long, Exam> {
  /**
   * Returns the exam with the given id.
   *
   * @param id
   * @return
   */
  default Optional<Exam> findById(final Long id) {
    return ofIdentity(id);
  }

  /**
   * Returns the exams of the given course.
   *
   * @param course
   * @return
   */
  Iterable<Exam> findAllCourseExams(Course course);

}
