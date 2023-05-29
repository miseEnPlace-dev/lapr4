package eapli.ecourse.persistence.impl.jpa;

import java.util.HashMap;
import java.util.Map;

import eapli.ecourse.answermanagement.domain.ExamAnswer;
import eapli.ecourse.answermanagement.domain.ExamAnswerId;
import eapli.ecourse.answermanagement.repositories.ExamAnswerRepository;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.studentmanagement.domain.MecanographicNumber;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaExamAnswerRepository
    extends JpaAutoTxRepository<ExamAnswer, ExamAnswerId, ExamAnswerId>
    implements ExamAnswerRepository {

  JpaExamAnswerRepository(final TransactionalContext autoTx) {
    super(autoTx, "id");
  }

  JpaExamAnswerRepository(final String puName) {
    super(puName, "id");
  }

  @Override
  public Iterable<ExamAnswer> findAllWithStudentMecanographicNumberAndCourseCode(
      final MecanographicNumber number, final CourseCode code) {
    Map<String, Object> params = new HashMap<>();
    params.put("number", number);
    params.put("code", code);
    return match("e.student.identity = :number and e.exam.course.code = :code", params);
  }

}
