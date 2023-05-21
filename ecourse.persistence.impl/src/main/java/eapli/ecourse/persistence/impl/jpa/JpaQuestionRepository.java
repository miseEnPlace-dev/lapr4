package eapli.ecourse.persistence.impl.jpa;

import java.util.HashMap;
import java.util.Map;

import eapli.ecourse.Application;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.questionmanagement.domain.Question;
import eapli.ecourse.questionmanagement.domain.QuestionCode;
import eapli.ecourse.questionmanagement.repositories.QuestionRepository;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaQuestionRepository extends JpaAutoTxRepository<Question, QuestionCode, QuestionCode>
    implements QuestionRepository {
  public JpaQuestionRepository(final TransactionalContext autoTx) {
    super(autoTx, "code");
  }

  public JpaQuestionRepository(final String puname) {
    super(puname, Application.settings().extendedPersistenceProperties(), "code");
  }

  @Override
  public Iterable<Question> findWithTypeFromCourse(String type, CourseCode code) {
    Map<String, Object> params = new HashMap<>();
    params.put("type", type);
    params.put("code", code);
    return match("e.disc = :type AND e.course.code = :code", params);
  }
}
