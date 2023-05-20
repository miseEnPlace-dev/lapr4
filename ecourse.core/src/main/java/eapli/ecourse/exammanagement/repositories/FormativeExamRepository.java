package eapli.ecourse.exammanagement.repositories;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.exammanagement.domain.ExamCode;
import eapli.ecourse.exammanagement.domain.formative.FormativeExam;
import eapli.framework.domain.repositories.DomainRepository;

public interface FormativeExamRepository extends DomainRepository<ExamCode, FormativeExam> {
  /**
   * Returns all exams of the given course.
   *
   * @param course
   * @return
   */
  Iterable<FormativeExam> findAllCourseExams(Course course);
}
