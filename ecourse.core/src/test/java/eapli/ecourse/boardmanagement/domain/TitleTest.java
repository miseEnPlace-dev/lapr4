package eapli.ecourse.boardmanagement.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

public class TitleTest {

  @Test
  public void ensureItIsNotPossibleToCreateTitleWithNull() {
    assertThrows(IllegalArgumentException.class, () -> new Title(null));
  }

  @Test
  public void ensureItIsNotPossibleToCreateTitleWithEmpty() {
    assertThrows(IllegalArgumentException.class, () -> new Title(""));
  }

  @Test
  public void ensureToStringWorks() {
    assertEquals("Sample title", new Title("Sample title").toString());
  }

  @Test
  public void ensureCompareToWorks() {
    assertEquals(0, new Title("Sample title").compareTo(new Title("Sample title")));
    assertTrue(new Title("Sample title").compareTo(new Title("Sample title 2")) < 0);
    assertTrue(new Title("Sample title 2").compareTo(new Title("Sample title")) > 0);
  }

}
