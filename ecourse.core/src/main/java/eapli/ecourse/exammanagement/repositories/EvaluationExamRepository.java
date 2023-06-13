package eapli.ecourse.exammanagement.repositories;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.exammanagement.domain.ExamIdentifier;
import eapli.ecourse.exammanagement.domain.evaluation.EvaluationExam;
import eapli.ecourse.studentmanagement.domain.MecanographicNumber;
import eapli.framework.domain.repositories.DomainRepository;

public interface EvaluationExamRepository extends DomainRepository<ExamIdentifier, EvaluationExam> {
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

  /**
   * Returns all future exams of the given course.
   *
   * @param course
   * @return
   */
  Iterable<EvaluationExam> findAllOpenCourseExams(Course course);

  /**
   * Returns all past exams of the given course.
   *
   * @param course
   * @return
   */
  Iterable<EvaluationExam> findAllPastCourseExams(Course course);

  /**
   * Returns all exams of the given course that have no answers for a given
   * student.
   */

  Iterable<EvaluationExam> findAllCourseExamsWithNoAnswersFromStudent(CourseCode courseCode,
      MecanographicNumber mecanographicNumber);
}
