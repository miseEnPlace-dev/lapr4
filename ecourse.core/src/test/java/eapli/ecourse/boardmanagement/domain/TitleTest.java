package eapli.ecourse.boardmanagement.domain;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

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

}
