package eapli.ecourse.answermanagement.domain;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class Grade implements ValueObject {
  private static final long serialVersionUID = 1L;

  private Double grade;

  private Grade(Double grade) {
    this.grade = grade;
  }

  protected Grade() {
    // for ORM
  }

  public static Grade valueOf(Double grade) {
    return new Grade(grade);
  }

  @Override
  public String toString() {
    return String.format(".2f", grade);
  }
}
