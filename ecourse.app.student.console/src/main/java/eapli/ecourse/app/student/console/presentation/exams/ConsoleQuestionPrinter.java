package eapli.ecourse.app.student.console.presentation.exams;

import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
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

  public Set<QuestionIdentifier> getMultipleChoiceMultipleQuestionAnswer(final QuestionBody body,
      final Map<QuestionIdentifier, String> options) {
    System.out.println(body);

    Set<QuestionIdentifier> selected = new HashSet<>();
    Set<Entry<QuestionIdentifier, String>> opts = options.entrySet();
    int selectedIndex = -1;

    while (options.size() > 0 && selectedIndex != 0) {
      System.out.println("\nSelect the correct options:\n");

      int i = 0;
      for (Entry<QuestionIdentifier, String> option : opts) {
        System.out.println((i + 1) + ". " + option.getValue());
        i++;
      }
      System.out.println("\n0. Submit answer");

      selectedIndex = Console.readOption(1, options.size(), 0);

      Entry<QuestionIdentifier, String> selectedOption = fromIndex(opts, selectedIndex - 1);
      opts.remove(selectedOption);
      selected.add(selectedOption.getKey());
    }

    return selected;
  }

  public QuestionIdentifier getMultipleChoiceSingleQuestionAnswer(final QuestionBody body,
      final Map<QuestionIdentifier, String> options) {
    System.out.println(body);

    Set<Entry<QuestionIdentifier, String>> opts = options.entrySet();

    System.out.println("\nSelect the correct option:\n");
    int i = 0;
    for (Entry<QuestionIdentifier, String> option : opts) {
      System.out.println((i + 1) + ". " + option.getValue());
      i++;
    }

    int selectedIndex = Console.readOption(1, options.size(), 0);

    Entry<QuestionIdentifier, String> selectedOption = fromIndex(opts, selectedIndex - 1);

    return selectedOption.getKey();
  }

  private <T> T fromIndex(Iterable<T> source, int index) {
    int idx = 0;
    T elem = null;
    final Iterator<T> it = source.iterator();
    while (idx < index) {
      elem = it.next();
      idx++;
    }
    return elem;
  }

  public Map<QuestionIdentifier, QuestionIdentifier> getMatchingQuestionAnswer(final QuestionBody body,
      final Map<QuestionIdentifier, String> options, final Map<QuestionIdentifier, String> matches) {
    System.out.println(body);

    Map<QuestionIdentifier, QuestionIdentifier> selected = new HashMap<>();
    Collection<Entry<QuestionIdentifier, String>> optionsEntries = options.entrySet();
    Collection<Entry<QuestionIdentifier, String>> matchesEntries = matches.entrySet();
    int maxSize = Math.max(options.size(), matches.size());
    int selectedIndex = -1;

    while (selectedIndex != 0 && optionsEntries.size() > 0 && matchesEntries.size() > 0) {
      Iterator<Entry<QuestionIdentifier, String>> opts = optionsEntries.iterator();
      Iterator<Entry<QuestionIdentifier, String>> mts = matchesEntries.iterator();

      for (int i = 0; i < maxSize; i++) {
        System.out.println("\nSelect the correct match:\n");
        System.out.printf("%-25s%-25s%n", "Option", "Match");

        String optionString = String.format("%d. %s", i + 1, opts.next().getValue());
        String matchString = String.format("%d. %s", i + 1, mts.next().getValue());
        System.out.printf("%-25s%-25s%n", optionString, matchString);
      }
      System.out.println("\n0. Submit answer");

      int option = Console.readOption(1, optionsEntries.size(), 0);
      int match = Console.readOption(1, matchesEntries.size(), 0);

      Entry<QuestionIdentifier, String> selectedOption = fromIndex(optionsEntries, option - 1);
      Entry<QuestionIdentifier, String> selectedMatch = fromIndex(matchesEntries, match - 1);

      selected.put(selectedOption.getKey(), selectedMatch.getKey());

      optionsEntries.remove(selectedOption);
      matchesEntries.remove(selectedMatch);
    }

    return selected;
  }

  public List<String> getMissingWordsQuestionAnswer(final QuestionBody body) {
    System.out.println(body);

    String words = Console.readLine("Enter the missing words, separated by a semi-column: ");

    return Arrays.asList(words.split(";"));
  }
}
