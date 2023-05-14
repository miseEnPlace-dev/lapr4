package eapli.ecourse.questionmanagement.application;

import java.util.List;

import eapli.ecourse.exammanagement.domain.parsers.QuestionsMain;
import eapli.ecourse.questionmanagement.domain.Question;
import eapli.ecourse.questionmanagement.repositories.QuestionRepository;

public class AddQuestionsController {
  private final QuestionRepository questionRepository;

  public AddQuestionsController(QuestionRepository questionRepository) {
    this.questionRepository = questionRepository;
  }

  public void addQuestionsFromFile(String filename) {
    List<Question> questions = QuestionsMain.parseWithVisitor(filename);
    questions.forEach(question -> addQuestion(question));
  }

  public Question addQuestion(Question question) {
    return questionRepository.save(question);
  }
}
