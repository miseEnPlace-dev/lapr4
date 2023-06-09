package eapli.ecourse.answermanagement.repositories;

import eapli.ecourse.answermanagement.domain.Answer;
import eapli.ecourse.answermanagement.domain.AnswerId;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.exammanagement.domain.Exam;
import eapli.ecourse.studentmanagement.domain.MecanographicNumber;
import eapli.framework.domain.repositories.DomainRepository;

public interface ExamAnswerRepository extends DomainRepository<AnswerId, Answer> {
  /**
   * Finds all exam answers with the given student mecanographic number and course
   * code.
   *
   * @param number student mecanographic number
   * @param code   course code
   * @return
   */
  Iterable<Answer> findAllWithStudentMecanographicNumberAndCourseCode(MecanographicNumber number, CourseCode code);

  Iterable<Answer> findAllWithExam(Exam exam);
}
