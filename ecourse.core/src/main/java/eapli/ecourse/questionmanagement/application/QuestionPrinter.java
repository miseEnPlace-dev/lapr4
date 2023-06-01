package eapli.ecourse.questionmanagement.application;

import java.util.List;
import java.util.Map;
import java.util.Set;

import eapli.ecourse.questionmanagement.domain.QuestionBody;
import eapli.ecourse.questionmanagement.domain.QuestionIdentifier;

public interface QuestionPrinter {
  public Double getNumericalQuestionAnswer(final QuestionBody body);

  public String getShortAnswerQuestionAnswer(final QuestionBody body);

  public boolean getTrueFalseQuestionAnswer(final QuestionBody body);

  public Set<QuestionIdentifier> getMultipleChoiceQuestionAnswer(final QuestionBody body,
      final Map<QuestionIdentifier, String> options);

  public Map<QuestionIdentifier, QuestionIdentifier> getMatchingQuestionAnswer(final QuestionBody body,
      final Map<QuestionIdentifier, String> options, final Map<QuestionIdentifier, String> matches);

  public List<String> getMissingWordsQuestionAnswer(final QuestionBody body);
}
