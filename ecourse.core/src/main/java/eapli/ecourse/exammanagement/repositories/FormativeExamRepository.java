package eapli.ecourse.exammanagement.repositories;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.exammanagement.domain.ExamIdentifier;
import eapli.ecourse.exammanagement.domain.formative.FormativeExam;
import eapli.ecourse.studentmanagement.domain.MecanographicNumber;
import eapli.framework.domain.repositories.DomainRepository;

public interface FormativeExamRepository extends DomainRepository<ExamIdentifier, FormativeExam> {
  /**
   * Returns all exams of the given course.
   *
   * @param course
   * @return
   */
  Iterable<FormativeExam> findAllCourseExams(Course course);

  /**
   * Returns all exams of the given course that have no answers for a given
   * student.
   */
  Iterable<FormativeExam> findAllCourseExamsWithNoAnswersFromStudent(CourseCode courseCode,
      MecanographicNumber mecanographicNumber);
}
