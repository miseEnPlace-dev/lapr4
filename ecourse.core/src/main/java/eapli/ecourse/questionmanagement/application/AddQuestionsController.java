package eapli.ecourse.questionmanagement.application;

import eapli.ecourse.questionmanagement.domain.Question;
import eapli.ecourse.questionmanagement.repositories.QuestionRepository;

public class AddQuestionsController {
  private final QuestionRepository questionRepository;

  public AddQuestionsController(QuestionRepository questionRepository) {
    this.questionRepository = questionRepository;
  }

  public void addQuestion(Question question) {
    questionRepository.save(question);
  }
}
