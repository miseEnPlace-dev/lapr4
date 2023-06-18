package eapli.ecourse.postitmanagement.domain;

import org.junit.Test;

public class PostItIDTest {

  @Test
  public void assertValueOfWorks() {
    PostItID d = PostItID.valueOf("5fc03087-d265-11e7-b8c6-83e29cd24f4c");
    assert (d != null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void assertValueOfThrowsExceptionWhenDescriptionIsNull() {
    PostItID.valueOf(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void assertValueOfThrowsExceptionWhenDescriptionIsEmpty() {
    PostItID.valueOf("");
  }

  @Test
  public void assertEqualsWorks() {
    PostItID d1 = PostItID.valueOf("5fc03087-d265-11e7-b8c6-83e29cd24f4c");
    PostItID d2 = PostItID.valueOf("5fc03087-d265-11e7-b8c6-83e29cd24f4c");
    assert (d1.equals(d2));
  }

  @Test
  public void assertEqualsWorksWhenSameObject() {
    PostItID d1 = PostItID.valueOf("5fc03087-d265-11e7-b8c6-83e29cd24f4c");
    assert (d1.equals(d1));
  }

  @Test
  public void assertEqualsWorksWhenDifferentObjects() {
    PostItID d1 = PostItID.valueOf("5fc03087-d265-11e7-b8c6-83e29cd24f4c");
    PostItID d2 = PostItID.valueOf("5fc03087-d265-11e7-b8c6-83e29cd24f4d");
    assert (!d1.equals(d2));
  }

  @Test
  public void assertNewIdWorks() {
    PostItID d1 = PostItID.newID();
    PostItID d2 = PostItID.newID();
    assert (!d1.equals(d2));
  }

  @Test
  public void assertToStringWorks() {
    PostItID d1 = PostItID.valueOf("5fc03087-d265-11e7-b8c6-83e29cd24f4c");
    assert (d1.toString().equals("5fc03087-d265-11e7-b8c6-83e29cd24f4c"));
  }

  @Test
  public void assertCompareToWorks() {
    PostItID d1 = PostItID.valueOf("5fc03087-d265-11e7-b8c6-83e29cd24f4c");
    PostItID d2 = PostItID.valueOf("5fc03087-d265-11e7-b8c6-83e29cd24f4d");
    assert (d1.compareTo(d2) == -1);
  }

}
