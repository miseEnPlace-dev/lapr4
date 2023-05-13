package eapli.ecourse.questionmanagement.repositories;

import eapli.ecourse.questionmanagement.domain.Question;
import eapli.ecourse.questionmanagement.domain.QuestionCode;
import eapli.framework.domain.repositories.DomainRepository;

public interface QuestionRepository extends DomainRepository<QuestionCode, Question> {

}
