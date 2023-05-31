package eapli.ecourse.app.student.console.presentation;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import eapli.ecourse.questionmanagement.application.AbstractQuestionsPrinter;
import eapli.ecourse.questionmanagement.domain.QuestionBody;
import eapli.ecourse.questionmanagement.domain.QuestionIdentifier;
import eapli.framework.io.util.Console;

public class QuestionsPrinter implements AbstractQuestionsPrinter {
  public Double showNumericalQuestion(final QuestionBody body) {
    System.out.println(body);

    return Console.readDouble("Enter your answer: ");
  }

  public String showShortAnswerQuestion(final QuestionBody body) {
    System.out.println(body);

    return Console.readLine("Enter your answer: ");
  }

  public boolean showTrueFalseQuestion(final QuestionBody body) {
    System.out.println(body);

    System.out.print("\n1 - True\n2 - False\nSelect an option: ");

    switch (Console.readOption(1, 2, 0)) {
      case 1:
        return true;
      case 2:
        return false;
      default:
        return false;
    }
  }

  public QuestionIdentifier showMultipleChoiceSingleAnswerQuestion(final QuestionBody body,
      final Map<QuestionIdentifier, String> options) {
    return null;
  }

  public Set<QuestionIdentifier> showMultipleChoiceMultipleAnswersQuestion(final QuestionBody body,
      final Map<QuestionIdentifier, String> options) {
    return null;
  }

  public Map<QuestionIdentifier, QuestionIdentifier> showMatchingQuestion(final QuestionBody body,
      final Map<QuestionIdentifier, String> options, final Map<QuestionIdentifier, String> matches) {
    return null;
  }

  public List<String> showMissingWordsQuestion(final QuestionBody body) {
    System.out.println(body);

    String words = Console.readLine("Enter the missing words, separated by a semi-column: ");

    return Arrays.asList(words.split(";"));
  }
}
