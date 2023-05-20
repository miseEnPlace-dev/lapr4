package eapli.ecourse.infrastructure.bootstrapers.demo;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.questionmanagement.application.AddQuestionsController;
import eapli.ecourse.questionmanagement.domain.QuestionIdentifier;
import eapli.ecourse.questionmanagement.domain.MatchingQuestion;
import eapli.ecourse.questionmanagement.domain.MissingWordsQuestion;
import eapli.ecourse.questionmanagement.domain.MultipleChoiceQuestion;
import eapli.ecourse.questionmanagement.domain.NumericalQuestion;
import eapli.ecourse.questionmanagement.domain.QuestionBody;
import eapli.ecourse.questionmanagement.domain.QuestionType;
import eapli.ecourse.questionmanagement.domain.ShortAnswerQuestion;
import eapli.ecourse.questionmanagement.domain.TrueFalseQuestion;
import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class QuestionsBootstrapper implements Action {
  private static final Logger LOGGER = LogManager.getLogger(StudentBootstrapper.class);

  private final AddQuestionsController controller = new AddQuestionsController(
      PersistenceContext.repositories().questions(), PersistenceContext.repositories().courses(),
      AuthzRegistry.authorizationService());

  private Course course;

  private void addMultipleChoiceQuestion() {
    MultipleChoiceQuestion multipleChoiceQuestion = new MultipleChoiceQuestion(
        QuestionBody.valueOf("Qual a melhor coisa?"),
        QuestionType.FORMATIVE);
    multipleChoiceQuestion.addCorrectAnswer(QuestionIdentifier.valueOf("123"), 0.2);
    multipleChoiceQuestion.addCorrectAnswer(QuestionIdentifier.valueOf("456"), 0.4);
    multipleChoiceQuestion.addOption(QuestionIdentifier.valueOf("123"), "coisa");
    multipleChoiceQuestion.addOption(QuestionIdentifier.valueOf("456"), "coiso");
    multipleChoiceQuestion.addOption(QuestionIdentifier.valueOf("789"), "outra coisa");
    multipleChoiceQuestion.changeCourse(course);
    controller.addQuestion(multipleChoiceQuestion);
  }

  private void addShortAnswerQuestion() {
    ShortAnswerQuestion shortAnswerQuestion = new ShortAnswerQuestion(QuestionBody.valueOf("Qual a melhor coisa?"),
        QuestionType.FORMATIVE);
    shortAnswerQuestion.addCorrectAnswer("coisa", 0.2);
    shortAnswerQuestion.addCorrectAnswer("coiso", 0.4);
    shortAnswerQuestion.changeCourse(course);
    controller.addQuestion(shortAnswerQuestion);
  }

  private void addTrueOrFalseQuestion() {
    TrueFalseQuestion trueFalseQuestion = new TrueFalseQuestion(QuestionBody.valueOf("O céu é azul?"),
        QuestionType.FORMATIVE, true);

    trueFalseQuestion.changeCourse(course);
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
    missingWordsQuestion.changeCourse(course);
    controller.addQuestion(missingWordsQuestion);
  }

  private void addMatchingQuestion() {
    MatchingQuestion matchingQuestion = new MatchingQuestion(QuestionBody.valueOf("Associe as colunas"),
        QuestionType.FORMATIVE);

    matchingQuestion.addCorrectMatch("123", "A");
    matchingQuestion.addCorrectMatch("456", "B");
    matchingQuestion.addCorrectMatch("769", "C");

    matchingQuestion.addOption(QuestionIdentifier.valueOf("123"), "qualquer coisa");
    matchingQuestion.addOption(QuestionIdentifier.valueOf("456"), "outra situação");
    matchingQuestion.addOption(QuestionIdentifier.valueOf("789"), "mais situações");

    matchingQuestion.addOption(QuestionIdentifier.valueOf("A"), "associação 1");
    matchingQuestion.addOption(QuestionIdentifier.valueOf("B"), "associação 2");
    matchingQuestion.addOption(QuestionIdentifier.valueOf("C"), "associação 3");

    matchingQuestion.changeCourse(course);
    controller.addQuestion(matchingQuestion);
  }

  private void addNumericalQuestion() {
    NumericalQuestion numericalQuestion = new NumericalQuestion(
        QuestionBody.valueOf("Qual o raio de uma circunferência de raio 1.2cm?"),
        QuestionType.FORMATIVE, 1.2, 0.1);
    numericalQuestion.changeCourse(course);
    controller.addQuestion(numericalQuestion);
  }

  @Override
  public boolean execute() {
    course = PersistenceContext.repositories().courses().ofIdentity(CourseCode.valueOf("1234")).get();

    addShortAnswerQuestion();
    addTrueOrFalseQuestion();
    addMissingWordsQuestion();
    addMultipleChoiceQuestion();
    addMatchingQuestion();
    addNumericalQuestion();

    return true;
  }
}
