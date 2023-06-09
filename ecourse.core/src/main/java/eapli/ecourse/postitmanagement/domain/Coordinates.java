package eapli.ecourse.postitmanagement.domain;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class Coordinates implements ValueObject {

  Integer x, y;

  private Coordinates(Integer x, Integer y) {
    this.x = x;
    this.y = y;
  }

  protected Coordinates() {
    // for ORM
  }

  public static Coordinates valueOf(Integer x, Integer y) {
    return new Coordinates(x, y);
  }

  @Override
  public String toString() {
    return "X = " + x + ", Y = " + y;
  }

}
