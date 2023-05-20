package eapli.ecourse.exammanagement.repositories;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.exammanagement.domain.ExamCode;
import eapli.ecourse.exammanagement.domain.evaluation.EvaluationExam;
import eapli.framework.domain.repositories.DomainRepository;

public interface EvaluationExamRepository extends DomainRepository<ExamCode, EvaluationExam> {
  /**
   * Returns all exams of the given course.
   *
   * @param course
   * @return
   */
  Iterable<EvaluationExam> findAllCourseExams(Course course);

  /**
   * Returns all future exams of the given course.
   *
   * @param course
   * @return
   */
  Iterable<EvaluationExam> findAllFutureCourseExams(Course course);

}
