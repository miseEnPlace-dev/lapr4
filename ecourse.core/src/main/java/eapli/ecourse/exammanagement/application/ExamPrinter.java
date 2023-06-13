package eapli.ecourse.exammanagement.application;

import java.util.List;
import java.util.Map;
import java.util.Set;

import eapli.ecourse.questionmanagement.domain.QuestionBody;
import eapli.ecourse.questionmanagement.domain.QuestionIdentifier;

public interface ExamPrinter {
  public void printExamHeader(final String title, final String description);

  public void printSectionHeader(final String title, final String description);

  public void printFeedback(final String feedback);

  public void printFinalScore(final Double studentScore, final Double examScore);

  public Double getNumericalQuestionAnswer(final QuestionBody body, final Double score);

  public String getShortAnswerQuestionAnswer(final QuestionBody body, final Double score);

  public boolean getTrueFalseQuestionAnswer(final QuestionBody body, final Double score);

  public Set<QuestionIdentifier> getMultipleChoiceMultipleQuestionAnswer(final QuestionBody body,
      final Map<QuestionIdentifier, String> options, final Double score);

  public QuestionIdentifier getMultipleChoiceSingleQuestionAnswer(final QuestionBody body,
      final Map<QuestionIdentifier, String> options, final Double score);

  public Map<QuestionIdentifier, QuestionIdentifier> getMatchingQuestionAnswer(final QuestionBody body,
      final Map<QuestionIdentifier, String> options, final Map<QuestionIdentifier, String> matches, final Double score);

  public List<String> getMissingWordsQuestionAnswer(final QuestionBody body, final Double score);
}
