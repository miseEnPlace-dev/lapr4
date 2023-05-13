package eapli.ecourse.questionmanagement.domain;

import java.util.UUID;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class QuestionCode implements ValueObject, Comparable<QuestionCode> {
  private static final long serialVersionUID = 1L;

  private String code;

  public QuestionCode(final String questionCode) {
    Preconditions.nonEmpty(questionCode);

    this.code = questionCode;
  }

  protected QuestionCode() {
    // for ORM
  }

  public static QuestionCode valueOf(final String questionCode) {
    return new QuestionCode(questionCode);
  }

  public static QuestionCode newID() {
    return valueOf(UUID.randomUUID().toString());
  }

  @Override
  public String toString() {
    return this.code;
  }

  @Override
  public int compareTo(final QuestionCode arg0) {
    return code.compareTo(arg0.code);
  }
}
