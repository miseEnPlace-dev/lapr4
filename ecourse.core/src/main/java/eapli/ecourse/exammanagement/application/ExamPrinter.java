package eapli.ecourse.exammanagement.application;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface ExamPrinter {
  public void printExamHeader(final String title, final String description);

  public void printSectionHeader(final String title, final String description);

  public void printCorrectAnswer();

  public void printIncorrectAnswer();

  public void printIncorrectAnswer(final String feedback);

  public void printFinalScore(final Double studentScore, final Double examScore);

  public Double getNumericalQuestionAnswer(final String body, final Double score);

  public String getShortAnswerQuestionAnswer(final String body, final Double score);

  public boolean getTrueFalseQuestionAnswer(final String body, final Double score);

  public Set<String> getMultipleChoiceMultipleQuestionAnswer(final String body,
      final Map<String, String> options, final Double score);

  public String getMultipleChoiceSingleQuestionAnswer(final String body,
      final Map<String, String> options, final Double score);

  public Map<String, String> getMatchingQuestionAnswer(final String body,
      final Map<String, String> options, final Map<String, String> matches, final Double score);

  public List<String> getMissingWordsQuestionAnswer(final String body, final List<String> options, final Double score);
}
