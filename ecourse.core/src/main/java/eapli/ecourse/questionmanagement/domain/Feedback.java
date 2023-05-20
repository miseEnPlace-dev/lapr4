package eapli.ecourse.questionmanagement.domain;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class Feedback implements ValueObject {
  private static final long serialVersionUID = 1L;

  private String feedback;

  private Feedback(String feedback) {
    Preconditions.nonEmpty(feedback);
    this.feedback = feedback;
  }

  protected Feedback() {
    // for ORM
  }

  public static Feedback valueOf(final String feedback) {
    return new Feedback(feedback);
  }

  @Override
  public String toString() {
    return this.feedback;
  }
}
