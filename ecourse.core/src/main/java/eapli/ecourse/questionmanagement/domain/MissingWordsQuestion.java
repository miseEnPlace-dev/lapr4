package eapli.ecourse.questionmanagement.domain;

import java.util.ArrayList;
import java.util.List;

public class MissingWordsQuestion extends Question {
  private static final long serialVersionUID = 1L;

  private List<String> missingWords;

  public MissingWordsQuestion(final QuestionBody body, QuestionType type) {
    super(body, type);
    this.missingWords = new ArrayList<>();
  }

  public void addMissingWord(final String missingWord) {
    missingWords.add(missingWord);
  }

  public List<String> missingWords() {
    return this.missingWords;
  }

  @Override
  public boolean sameAs(Object other) {
    if (!(other instanceof MissingWordsQuestion))
      return false;

    final MissingWordsQuestion that = (MissingWordsQuestion) other;

    if (this == that)
      return true;

    return this.body().equals(that.body()) && this.type().equals(that.type())
        && this.missingWords.equals(that.missingWords);
  }
}
