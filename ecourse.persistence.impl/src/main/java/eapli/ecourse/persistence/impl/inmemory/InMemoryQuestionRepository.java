package eapli.ecourse.persistence.impl.inmemory;

import eapli.ecourse.questionmanagement.domain.Question;
import eapli.ecourse.questionmanagement.domain.QuestionCode;
import eapli.ecourse.questionmanagement.repositories.QuestionRepository;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryQuestionRepository extends
    InMemoryDomainRepository<Question, QuestionCode> implements QuestionRepository {

  static {
    InMemoryInitializer.init();
  }
}
