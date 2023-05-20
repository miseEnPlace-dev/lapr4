package eapli.ecourse.questionmanagement.domain;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class QuestionIdentifier implements ValueObject {
  private static final long serialVersionUID = 1L;

  private String identifier;

  private QuestionIdentifier(final String identifier) {
    Preconditions.nonEmpty(identifier, "Identifier should neither be null nor empty.");

    if (identifier.length() > 255)
      throw new IllegalArgumentException("Identifier should not exceed 255 characters");

    this.identifier = identifier;
  }

  protected QuestionIdentifier() {
    // for ORM
  }

  public static QuestionIdentifier valueOf(final String identifier) {
    return new QuestionIdentifier(identifier);
  }

  @Override
  public String toString() {
    return this.identifier;
  }
}
