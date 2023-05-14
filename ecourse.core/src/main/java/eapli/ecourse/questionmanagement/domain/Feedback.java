package eapli.ecourse.questionmanagement.domain;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class Feedback implements ValueObject {
  private static final long serialVersionUID = 1L;

  private String feedback;

  public Feedback(String feedback) {
    this.feedback = feedback;
  }

  protected Feedback() {
    // for ORM
  }

  @Override
  public String toString() {
    return this.feedback;
  }
}
