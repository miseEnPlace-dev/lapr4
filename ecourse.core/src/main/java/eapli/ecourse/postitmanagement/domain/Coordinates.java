package eapli.ecourse.postitmanagement.domain;

import java.util.Objects;
import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;

@Embeddable
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

  public Integer getX() {
    return x;
  }

  public Integer getY() {
    return y;
  }

  @Override
  public String toString() {
    return "(" + x + ", " + y + ")";
  }

  @Override
  public boolean equals(Object o) {
    if (o == this)
      return true;
    if (!(o instanceof Coordinates)) {
      return false;
    }
    Coordinates coordinates = (Coordinates) o;
    return x == coordinates.x && y == coordinates.y;
  }

  @Override
  public int hashCode() {
    return Objects.hash(x, y);
  }
}
