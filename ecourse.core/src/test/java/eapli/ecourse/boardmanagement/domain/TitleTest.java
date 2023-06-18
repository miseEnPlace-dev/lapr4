package eapli.ecourse.boardmanagement.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class TitleTest {

  @Test
  public void ensureItIsNotPossibleToCreateTitleWithNull() {
    assertThrows(IllegalArgumentException.class, () -> new BoardTitle(null));
  }

  @Test
  public void ensureItIsNotPossibleToCreateTitleWithEmpty() {
    assertThrows(IllegalArgumentException.class, () -> new BoardTitle(""));
  }

  @Test
  public void ensureToStringWorks() {
    assertEquals("Sample title", new BoardTitle("Sample title").toString());
  }

  @Test
  public void ensureCompareToWorks() {
    assertEquals(0, new BoardTitle("Sample title").compareTo(new BoardTitle("Sample title")));
    assertTrue(new BoardTitle("Sample title").compareTo(new BoardTitle("Sample title 2")) < 0);
    assertTrue(new BoardTitle("Sample title 2").compareTo(new BoardTitle("Sample title")) > 0);
  }

  @Test
  public void testHashCode() {
    assertEquals(new BoardTitle("Sample title").hashCode(), new BoardTitle("Sample title").hashCode());
  }
}
