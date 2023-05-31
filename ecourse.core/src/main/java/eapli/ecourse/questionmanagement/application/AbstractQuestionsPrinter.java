package eapli.ecourse.questionmanagement.application;

import java.util.List;
import java.util.Map;
import java.util.Set;

import eapli.ecourse.questionmanagement.domain.QuestionBody;
import eapli.ecourse.questionmanagement.domain.QuestionIdentifier;

public interface AbstractQuestionsPrinter {
  public Double showNumericalQuestion(final QuestionBody body);

  public String showShortAnswerQuestion(final QuestionBody body);

  public boolean showTrueFalseQuestion(final QuestionBody body);

  public QuestionIdentifier showMultipleChoiceSingleAnswerQuestion(final QuestionBody body,
      final Map<QuestionIdentifier, String> options);

  public Set<QuestionIdentifier> showMultipleChoiceMultipleAnswersQuestion(final QuestionBody body,
      final Map<QuestionIdentifier, String> options);

  public Map<QuestionIdentifier, QuestionIdentifier> showMatchingQuestion(final QuestionBody body,
      final Map<QuestionIdentifier, String> options, final Map<QuestionIdentifier, String> matches);

  public List<String> showMissingWordsQuestion(final QuestionBody body);
}
