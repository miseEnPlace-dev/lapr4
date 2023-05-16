package eapli.ecourse.exammanagement.domain.repositories;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.exammanagement.domain.Exam;
import eapli.ecourse.exammanagement.domain.ExamCode;
import eapli.framework.domain.repositories.DomainRepository;

import java.util.Optional;

public interface ExamRepository extends DomainRepository<ExamCode, Exam> {
  /**
   * Returns the exam with the given id.
   *
   * @param id
   * @return
   */
  default Optional<Exam> findById(final ExamCode id) {
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
