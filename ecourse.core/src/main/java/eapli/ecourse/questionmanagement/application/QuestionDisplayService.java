package eapli.ecourse.questionmanagement.application;

import java.util.Map;
import java.util.Set;

import eapli.ecourse.questionmanagement.domain.QuestionBody;
import eapli.ecourse.questionmanagement.domain.QuestionIdentifier;

public interface QuestionDisplayService {
  public Double showNumericalQuestion(final QuestionBody body);

  public String showShortAnswerQuestion(final QuestionBody body);

  public boolean showTrueFalseQuestion(final QuestionBody body);

  public Set<QuestionIdentifier> showMultipleChoiceQuestion(final QuestionBody body,
      final Map<QuestionIdentifier, String> options);

  public Map<QuestionIdentifier, QuestionIdentifier> showMatchingQuestion(final QuestionBody body,
      final Map<QuestionIdentifier, String> options, final Map<QuestionIdentifier, String> matches);

  public String showMissingWordsQuestion(final QuestionBody body);
}
