package eapli.ecourse.postitmanagement.domain;

import org.junit.Test;

public class PostItTitleTest {

  @Test
  public void assertValueOfWorks() {
    PostItTitle t = PostItTitle.valueOf("Title");
    assert (t != null);
    assert (t.toString() == "Title");
  }

  @Test(expected = IllegalArgumentException.class)
  public void assertValueOfThrowsExceptionWhenTitleIsNull() {
    PostItTitle.valueOf(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void assertValueOfThrowsExceptionWhenTitleIsEmpty() {
    PostItTitle.valueOf("");
  }

  @Test
  public void assertEqualsWorks() {
    PostItTitle t1 = PostItTitle.valueOf("Title");
    PostItTitle t2 = PostItTitle.valueOf("Title");
    assert (t1.equals(t2));
  }

  @Test
  public void assertEqualsWorksWhenSameObject() {
    PostItTitle t1 = PostItTitle.valueOf("Title");
    assert (t1.equals(t1));
  }

  @Test
  public void assertEqualsWorksWhenDifferentObjects() {
    PostItTitle t1 = PostItTitle.valueOf("Title");
    PostItTitle t2 = PostItTitle.valueOf("Title2");
    assert (!t1.equals(t2));
  }

  @Test
  public void assertToStringWorks() {
    PostItTitle t1 = PostItTitle.valueOf("Title");
    assert (t1.toString().equals("Title"));
  }

  @Test
  public void assertCompareToWorks() {
    PostItTitle t1 = PostItTitle.valueOf("Title");
    PostItTitle t2 = PostItTitle.valueOf("Title2");
    assert (t1.compareTo(t2) == -1);
  }

}
