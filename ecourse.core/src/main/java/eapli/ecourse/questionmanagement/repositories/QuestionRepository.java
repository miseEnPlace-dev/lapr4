package eapli.ecourse.questionmanagement.repositories;

import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.questionmanagement.domain.Question;
import eapli.ecourse.questionmanagement.domain.QuestionCode;
import eapli.framework.domain.repositories.DomainRepository;

public interface QuestionRepository extends DomainRepository<QuestionCode, Question> {
  /**
   * Returns all questions with the given type
   *
   * @param type question type
   * @return
   */
  public Iterable<Question> findWithTypeFromCourse(String type, CourseCode code);
}
