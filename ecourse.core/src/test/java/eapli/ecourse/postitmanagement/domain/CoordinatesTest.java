package eapli.ecourse.postitmanagement.domain;

import org.junit.Test;

public class CoordinatesTest {

  @Test
  public void assertValueOfWorks() {
    Coordinates c = Coordinates.valueOf(1, 2);
    assert (c != null);
    assert (c.getX() == 1);
    assert (c.getY() == 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void assertValueOfThrowsExceptionWhenXIsNull() {
    Coordinates.valueOf(null, 2);
  }

  @Test(expected = IllegalArgumentException.class)
  public void assertValueOfThrowsExceptionWhenYIsNull() {
    Coordinates.valueOf(1, null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void assertValueOfThrowsExceptionWhenXAndYAreNull() {
    Coordinates.valueOf(null, null);
  }

  @Test
  public void assertEqualsWorks() {
    Coordinates c1 = Coordinates.valueOf(1, 2);
    Coordinates c2 = Coordinates.valueOf(1, 2);
    assert (c1.equals(c2));
  }

  @Test
  public void assertEqualsWorksWhenSameObject() {
    Coordinates c1 = Coordinates.valueOf(1, 2);
    assert (c1.equals(c1));
  }

  @Test
  public void assertEqualsWorksWhenDifferentObjects() {
    Coordinates c1 = Coordinates.valueOf(1, 2);
    Coordinates c2 = Coordinates.valueOf(2, 1);
    assert (!c1.equals(c2));
  }

  @Test
  public void assertHashCodeWorks() {
    Coordinates c1 = Coordinates.valueOf(1, 2);
    Coordinates c2 = Coordinates.valueOf(1, 2);
    assert (c1.hashCode() == c2.hashCode());
  }

  @Test
  public void assertHashCodeWorksWhenDifferentObjects() {
    Coordinates c1 = Coordinates.valueOf(1, 2);
    Coordinates c2 = Coordinates.valueOf(2, 1);
    assert (c1.hashCode() != c2.hashCode());
  }

  @Test
  public void assertToStringWorks() {
    Coordinates c = Coordinates.valueOf(1, 2);
    assert (c.toString().equals("(1, 2)"));
  }

}
