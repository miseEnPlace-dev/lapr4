package eapli.ecourse.persistence.impl.inmemory;

import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.questionmanagement.domain.Question;
import eapli.ecourse.questionmanagement.domain.QuestionCode;
import eapli.ecourse.questionmanagement.repositories.QuestionRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryQuestionRepository extends
    InMemoryDomainRepository<Question, QuestionCode> implements QuestionRepository {

  static {
    InMemoryInitializer.init();
  }

  @Override
  public Iterable<Question> findWithTypeFromCourse(String type, CourseCode code) {
    return match(e -> e.type().equals(type) && e.course().code().equals(code));
  }
}
