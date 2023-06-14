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

import eapli.ecourse.exammanagement.application.ExamPrinter;
import eapli.framework.io.util.Console;
import eapli.framework.presentation.console.AbstractUI;

public class ConsoleExamPrinter extends AbstractUI implements ExamPrinter {
  private static final String QUESTION_SEPARATOR = "................................................................................";

  public void printExamHeader(final String title, final String description) {
    drawFormTitle(title);
    printString(description);
    System.out.println("");
  }

  public void printSectionHeader(final String title, final String description) {
    drawFormSeparator(title);
    printString(description);
  }

  public void printCorrectAnswer() {
    System.out.println("\nCorrect :) Well done!\n");
    Console.readLine("Press Enter to continue...");
  }

  public void printIncorrectAnswer() {
    System.out.println("\nSorry, your answer is incorrect :(\n");
    Console.readLine("Press Enter to continue...");
  }

  public void printIncorrectAnswer(final String feedback) {
    System.out.println("\nSorry, your answer is incorrect :(");
    System.out.print("Feedback: ");
    printString(feedback);
    System.out.println("");
    Console.readLine("Press Enter to continue...");
  }

  public void printFinalScore(final Double studentScore, final Double examScore) {
    drawFormTitle("Exam Finished!");
    System.out.println("\nWell done!\nYour score: " + String.format("%.2f", studentScore) + " out of "
        + String.format("%.2f", examScore) + ".");
  }

  public Double getNumericalQuestionAnswer(final String body, final Double score) {
    drawQuestionSeparator();
    System.out.print("\n[" + String.format("%.2f", score) + " points] ");
    printString(body);

    System.out.println("");
    return Console.readDouble("Your answer: ");
  }

  public String getShortAnswerQuestionAnswer(final String body, final Double score) {
    drawQuestionSeparator();
    System.out.print("\n[" + String.format("%.2f", score) + " points] ");
    printString(body);

    System.out.println("");
    return Console.readLine("Your answer: ");
  }

  public boolean getTrueFalseQuestionAnswer(final String body, final Double score) {
    drawQuestionSeparator();
    System.out.print("\n[" + String.format("%.2f", score) + " points] ");
    printString(body);

    System.out.println("1 - True\n2 - False");

    System.out.println("");
    int option = Console.readOption(1, 2, 0);

    return option == 1;
  }

  public Set<String> getMultipleChoiceMultipleQuestionAnswer(final String body,
      final Map<String, String> options, final Double score) {
    drawQuestionSeparator();
    System.out.print("\n[" + String.format("%.2f", score) + " points] ");
    printString(body);

    Set<String> selected = new HashSet<>();
    Set<Entry<String, String>> opts = options.entrySet();
    int selectedIndex = -1;

    while (opts.size() > 0 && selectedIndex != 0) {
      System.out.println("\nSelect the correct options, one at a time:");
      int i = 0;
      for (Entry<String, String> option : opts) {
        System.out.println((i + 1) + ". " + option.getValue());
        i++;
      }
      System.out.println("\n0. Submit answer\n");

      selectedIndex = Console.readOption(1, options.size(), 0);

      Entry<String, String> selectedOption = fromIndex(opts, selectedIndex);
      if (selectedOption == null)
        break;
      opts.remove(selectedOption);
      selected.add(selectedOption.getKey());
    }

    return selected;
  }

  public String getMultipleChoiceSingleQuestionAnswer(final String body,
      final Map<String, String> options, final Double score) {
    drawQuestionSeparator();
    System.out.print("\n[" + String.format("%.2f", score) + " points] ");
    printString(body);

    Set<Entry<String, String>> opts = options.entrySet();

    int i = 0;
    for (Entry<String, String> option : opts) {
      System.out.println((i + 1) + ". " + option.getValue());
      i++;
    }

    System.out.println("");
    int selectedIndex = Console.readOption(1, options.size(), 0);

    Entry<String, String> selectedOption = fromIndex(opts, selectedIndex);

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

  public Map<String, String> getMatchingQuestionAnswer(final String body,
      final Map<String, String> options, final Map<String, String> matches,
      final Double score) {
    drawQuestionSeparator();
    System.out.print("\n[" + String.format("%.2f", score) + " points] ");
    printString(body);

    Map<String, String> selected = new HashMap<>();
    Collection<Entry<String, String>> optionsEntries = options.entrySet();
    Collection<Entry<String, String>> matchesEntries = matches.entrySet();
    int maxSize = Math.max(options.size(), matches.size());
    int selectedIndex = -1;

    while (selectedIndex != 0 && optionsEntries.size() > 0 && matchesEntries.size() > 0) {
      Iterator<Entry<String, String>> opts = optionsEntries.iterator();
      Iterator<Entry<String, String>> mts = matchesEntries.iterator();

      // System.out.println("\nSelect the correct match:\n");
      System.out.printf("\n%-28s%-28s%n", "Options", "Matches");
      for (int i = 0; i < maxSize; i++) {
        String optionString;
        String matchString;
        if (i < options.size())
          optionString = String.format("%d. %s", i + 1, opts.next().getValue());
        else
          optionString = "";
        if (i < matches.size())
          matchString = String.format("%d. %s", i + 1, mts.next().getValue());
        else
          matchString = "";
        System.out.printf("%-28s%-28s%n", optionString, matchString);
      }

      System.out.println("\nSelect one of the options.");
      int option = Console.readOption(1, optionsEntries.size(), 0);
      System.out
          .println("Now, select the match for " + fromIndex(optionsEntries, option).getValue() + ".");
      int match = Console.readOption(1, matchesEntries.size(), 0);

      Entry<String, String> selectedOption = fromIndex(optionsEntries, option);
      Entry<String, String> selectedMatch = fromIndex(matchesEntries, match);

      selected.put(selectedOption.getKey(), selectedMatch.getKey());

      optionsEntries.remove(selectedOption);
      matchesEntries.remove(selectedMatch);
    }

    return selected;
  }

  public List<String> getMissingWordsQuestionAnswer(final String body, final List<String> options, final Double score) {
    drawQuestionSeparator();
    System.out.print("\n[" + String.format("%.2f", score) + " points] ");
    printString(body);

    System.out.println("\nOptions:");
    for (int i = 0; i < options.size(); i++)
      System.out.println("> " + options.get(i));
    String words = Console.readLine("\nEnter the missing words in the correct order, separated by a semi-column: ");

    List<String> list = Arrays.asList(words.split(";"));
    for (int i = 0; i < list.size(); i++)
      list.set(i, list.get(i).trim());

    return list;
  }

  public boolean doShow() {
    return false;
  }

  public String headline() {
    return null;
  }

  private void drawFormSeparator(final String title) {
    final String titleBorder = SEPARATOR.substring(0, 2) + " " + title + " "
        + SEPARATOR.substring(4 + title.length());
    System.out.println(titleBorder);
  }

  private void drawQuestionSeparator() {
    System.out.println("");
    System.out.println(QUESTION_SEPARATOR);
  }

  private void printString(final String str) {
    if (str == null)
      return;
    for (String substr : str.split("\\\\n"))
      System.out.println(substr);
  }
}
