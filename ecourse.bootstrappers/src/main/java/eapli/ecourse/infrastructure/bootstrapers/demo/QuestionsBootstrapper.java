package eapli.ecourse.infrastructure.bootstrapers.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.questionmanagement.application.AddQuestionsController;
import eapli.ecourse.questionmanagement.domain.Identifier;
import eapli.ecourse.questionmanagement.domain.MatchingQuestion;
import eapli.ecourse.questionmanagement.domain.MissingWordsQuestion;
import eapli.ecourse.questionmanagement.domain.MultipleChoiceQuestion;
import eapli.ecourse.questionmanagement.domain.NumericalQuestion;
import eapli.ecourse.questionmanagement.domain.QuestionBody;
import eapli.ecourse.questionmanagement.domain.QuestionType;
import eapli.ecourse.questionmanagement.domain.ShortAnswerQuestion;
import eapli.ecourse.questionmanagement.domain.TrueFalseQuestion;
import eapli.framework.actions.Action;

public class QuestionsBootstrapper implements Action {
  private static final Logger LOGGER = LogManager.getLogger(StudentBootstrapper.class);

  private final AddQuestionsController controller = new AddQuestionsController(
      PersistenceContext.repositories().questions());

  private void addMultipleChoiceQuestion() {
    MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion(
        QuestionBody.valueOf("Qual a melhor coisa?"),
        QuestionType.FORMATIVE);
    multipleChoiceQuestion.addCorrectAnswer(Identifier.valueOf("123"), 0.2);
    multipleChoiceQuestion.addCorrectAnswer(Identifier.valueOf("456"), 0.4);
    multipleChoiceQuestion.addOption(Identifier.valueOf("123"), "coisa");
    multipleChoiceQuestion.addOption(Identifier.valueOf("456"), "coiso");
    multipleChoiceQuestion.addOption(Identifier.valueOf("789"), "outra coisa");
    controller.addQuestion(multipleChoiceQuestion);
  }

  private void addShortAnswerQuestion() {
    ShortAnswerQuestion shortAnswerQuestion = new ShortAnswerQuestion(QuestionBody.valueOf("Qual a melhor coisa?"),
        QuestionType.FORMATIVE);
    shortAnswerQuestion.addCorrectAnswer("coisa", 0.2);
    shortAnswerQuestion.addCorrectAnswer("coiso", 0.4);
    controller.addQuestion(shortAnswerQuestion);
  }

  private void addTrueOrFalseQuestion() {
    TrueFalseQuestion trueFalseQuestion = new TrueFalseQuestion(QuestionBody.valueOf("O céu é azul?"),
        QuestionType.FORMATIVE, true);

    controller.addQuestion(trueFalseQuestion);
  }

  private void addMissingWordsQuestion() {
    MissingWordsQuestion missingWordsQuestion = new MissingWordsQuestion(
        QuestionBody.valueOf("O céu é ___ e a relva é ___"), QuestionType.FORMATIVE);
    missingWordsQuestion.addMissingWord("azul");
    missingWordsQuestion.addMissingWord("verde");
    missingWordsQuestion.addOption("azul");
    missingWordsQuestion.addOption("verde");
    missingWordsQuestion.addOption("amarelo");
    missingWordsQuestion.addOption("vermelho");
    controller.addQuestion(missingWordsQuestion);
  }

  private void addMatchingQuestion() {
    MatchingQuestion matchingQuestion = new MatchingQuestion(QuestionBody.valueOf("Associe as colunas"),
        QuestionType.FORMATIVE);

    matchingQuestion.addCorrectMatch("123", "A");
    matchingQuestion.addCorrectMatch("456", "B");
    matchingQuestion.addCorrectMatch("769", "C");

    matchingQuestion.addOption(Identifier.valueOf("123"), "qualquer coisa");
    matchingQuestion.addOption(Identifier.valueOf("456"), "outra situação");
    matchingQuestion.addOption(Identifier.valueOf("789"), "mais situações");

    matchingQuestion.addOption(Identifier.valueOf("A"), "associação 1");
    matchingQuestion.addOption(Identifier.valueOf("B"), "associação 2");
    matchingQuestion.addOption(Identifier.valueOf("C"), "associação 3");

    controller.addQuestion(matchingQuestion);
  }

  private void addNumericalQuestion() {
    NumericalQuestion numericalQuestion = new NumericalQuestion(
        QuestionBody.valueOf("Qual o raio de uma circunferência de raio 1.2cm?"),
        QuestionType.FORMATIVE, 1.2, 0.1);
    controller.addQuestion(numericalQuestion);
  }

  @Override
  public boolean execute() {
    addMultipleChoiceQuestion();
    addShortAnswerQuestion();
    addTrueOrFalseQuestion();
    addMissingWordsQuestion();
    addMatchingQuestion();
    addNumericalQuestion();

    return true;
  }
}
