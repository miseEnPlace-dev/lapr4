package eapli.ecourse.answermanagement.domain;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class Score implements ValueObject {
  private static final long serialVersionUID = 1L;

  private Double score;

  private Score(Double score) {
    this.score = score;
  }

  protected Score() {
    // for ORM
  }

  public static Score valueOf(Double score) {
    return new Score(score);
  }

  @Override
  public String toString() {
    return String.format(".2f", score);
  }
}
