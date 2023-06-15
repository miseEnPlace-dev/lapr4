package eapli.ecourse.questionmanagement.domain;

import javax.persistence.Embeddable;
import javax.persistence.Lob;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class QuestionBody implements ValueObject {
  @Lob
  private String body;

  public QuestionBody(final String questionBody) {
    Preconditions.nonEmpty(questionBody);

    this.body = questionBody;
  }

  protected QuestionBody() {
    // for ORM
    this.body = null;
  }

  public static QuestionBody valueOf(final String questionBody) {
    return new QuestionBody(questionBody);
  }

  @Override
  public String toString() {
    return this.body;
  }
}
