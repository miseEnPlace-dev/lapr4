package eapli.ecourse.persistence.impl.jpa;

import java.util.HashMap;
import java.util.Map;

import eapli.ecourse.answermanagement.domain.Answer;
import eapli.ecourse.answermanagement.domain.AnswerId;
import eapli.ecourse.answermanagement.repositories.AnswerRepository;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.exammanagement.domain.Exam;
import eapli.ecourse.exammanagement.domain.ExamIdentifier;
import eapli.ecourse.studentmanagement.domain.MecanographicNumber;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaAnswerRepository
    extends JpaAutoTxRepository<Answer, AnswerId, AnswerId>
    implements AnswerRepository {

  JpaAnswerRepository(final TransactionalContext autoTx) {
    super(autoTx, "id");
  }

  JpaAnswerRepository(final String puName) {
    super(puName, "id");
  }

  @Override
  public Iterable<Answer> findAllWithStudentMecanographicNumberAndCourseCode(
      final MecanographicNumber number, final CourseCode code) {
    Map<String, Object> params = new HashMap<>();
    params.put("number", number);
    params.put("code", code);
    return match("e.student.mecanographicNumber = :number AND e.exam.course.code = :code", params);
  }

  public Iterable<Answer> findAllWithExam(Exam exam) {
    return match("e.exam = :exam", "exam", exam);
  }

  public Iterable<Answer> findAllWithStudentAndExam(MecanographicNumber number, ExamIdentifier identifier) {
    Map<String, Object> params = new HashMap<>();
    params.put("number", number);
    params.put("identifier", identifier);
    return match("e.student.mecanographicNumber = :number AND e.exam.identifier = :identifier", params);
  }
}
