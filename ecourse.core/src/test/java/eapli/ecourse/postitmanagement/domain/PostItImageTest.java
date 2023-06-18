package eapli.ecourse.postitmanagement.domain;

import org.junit.Test;

public class PostItImageTest {

  @Test
  public void assertValueOfWorks() {
    PostItImage i = PostItImage.valueOf("Image");
    assert (i != null);
    assert (i.toString() == "Image");
  }

  @Test(expected = IllegalArgumentException.class)
  public void assertValueOfThrowsExceptionWhenImageIsNull() {
    PostItImage.valueOf(null);
  }

  @Test(expected = IllegalArgumentException.class)
  public void assertValueOfThrowsExceptionWhenImageIsEmpty() {
    PostItImage.valueOf("");
  }

  @Test
  public void assertEqualsWorks() {
    PostItImage i1 = PostItImage.valueOf("Image");
    PostItImage i2 = PostItImage.valueOf("Image");
    assert (i1.equals(i2));
  }

  @Test
  public void assertEqualsWorksWhenSameObject() {
    PostItImage i1 = PostItImage.valueOf("Image");
    assert (i1.equals(i1));
  }

  @Test
  public void assertEqualsWorksWhenDifferentObjects() {
    PostItImage i1 = PostItImage.valueOf("Image");
    PostItImage i2 = PostItImage.valueOf("Image2");
    assert (!i1.equals(i2));
  }

  @Test
  public void assertGetHashWorks() {
    PostItImage i1 = PostItImage.valueOf("Image");
    PostItImage i2 = PostItImage.valueOf("Image");
    assert (i1.getHash() == i2.getHash());
  }

}
