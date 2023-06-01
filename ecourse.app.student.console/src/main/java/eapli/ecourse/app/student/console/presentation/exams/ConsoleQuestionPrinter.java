package eapli.ecourse.app.student.console.presentation.exams;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Set;

import eapli.ecourse.questionmanagement.application.QuestionPrinter;
import eapli.ecourse.questionmanagement.domain.QuestionBody;
import eapli.ecourse.questionmanagement.domain.QuestionIdentifier;
import eapli.framework.io.util.Console;

public class ConsoleQuestionPrinter implements QuestionPrinter {
  public Double getNumericalQuestionAnswer(final QuestionBody body) {
    System.out.println(body);

    return Console.readDouble("Enter your answer: ");
  }

  public String getShortAnswerQuestionAnswer(final QuestionBody body) {
    System.out.println(body);

    return Console.readLine("Enter your answer: ");
  }

  public boolean getTrueFalseQuestionAnswer(final QuestionBody body) {
    System.out.println(body);

    System.out.print("\n1 - True\n2 - False\nSelect an option: ");
    int option = Console.readOption(1, 2, 0);

    return option == 1;
  }

  public Set<QuestionIdentifier> getMultipleChoiceQuestionAnswer(final QuestionBody body,
      final Map<QuestionIdentifier, String> options) {
    System.out.println(body);

    for (QuestionIdentifier id : options.keySet())
      System.out.printf("%s: %s", id.toString(), options.get(id));

    // TODO
    return null;

  }

  public Map<QuestionIdentifier, QuestionIdentifier> getMatchingQuestionAnswer(final QuestionBody body,
      final Map<QuestionIdentifier, String> options, final Map<QuestionIdentifier, String> matches) {
    return null;
  }

  public List<String> getMissingWordsQuestionAnswer(final QuestionBody body) {
    System.out.println(body);

    String words = Console.readLine("Enter the missing words, separated by a semi-column: ");

    return Arrays.asList(words.split(";"));
  }
}
