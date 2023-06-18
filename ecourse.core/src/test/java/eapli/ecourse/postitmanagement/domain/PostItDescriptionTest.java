package eapli.ecourse.postitmanagement.domain;

import org.junit.Test;

public class PostItDescriptionTest {

  @Test
  public void assertValueOfWorks() {
    PostItDescription d = PostItDescription.valueOf("Description");
    assert (d != null);
    assert (d.toString() == "Description");
  }

  @Test(expected = IllegalArgumentException.class)
  public void assertValueOfThrowsExceptionWhenDescriptionIsNull() {
    PostItDescription.valueOf(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void assertValueOfThrowsExceptionWhenDescriptionIsEmpty() {
    PostItDescription.valueOf("");
  }

  @Test
  public void assertEqualsWorks() {
    PostItDescription d1 = PostItDescription.valueOf("Description");
    PostItDescription d2 = PostItDescription.valueOf("Description");
    assert (d1.equals(d2));
  }

  @Test
  public void assertEqualsWorksWhenSameObject() {
    PostItDescription d1 = PostItDescription.valueOf("Description");
    assert (d1.equals(d1));
  }

  @Test
  public void assertEqualsWorksWhenDifferentObjects() {
    PostItDescription d1 = PostItDescription.valueOf("Description");
    PostItDescription d2 = PostItDescription.valueOf("Description2");
    assert (!d1.equals(d2));
  }

  @Test
  public void assertGetHashWorks() {
    PostItDescription d1 = PostItDescription.valueOf("Description");
    PostItDescription d2 = PostItDescription.valueOf("Description");
    assert (d1.getHash() == d2.getHash());
  }

}
