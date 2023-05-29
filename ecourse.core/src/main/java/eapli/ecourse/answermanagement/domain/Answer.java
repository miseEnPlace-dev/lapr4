package eapli.ecourse.answermanagement.domain;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class Answer implements ValueObject {
  private static final long serialVersionUID = 1L;

  private String answer;

  private Answer(String answer) {
    this.answer = answer;
  }

  protected Answer() {
    // for ORM
  }

  public static Answer valueOf(String answer) {
    return new Answer(answer);
  }

  @Override
  public String toString() {
    return answer;
  }
}
