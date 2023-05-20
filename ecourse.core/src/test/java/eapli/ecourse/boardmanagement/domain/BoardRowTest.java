package eapli.ecourse.boardmanagement.domain;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BoardRowTest {

  @Test
  public void ensureBoardRowHasTitle() {
    assertThrows(IllegalArgumentException.class, () -> new BoardRow(null, 1));
  }

  @Test
  public void ensureBoardRowHasNumber() {
    assertThrows(IllegalArgumentException.class, () -> new BoardRow(new Title("title"), null));
  }

  @Test
  public void ensureSameAsWorks() {
    BoardRow boardRow1 = new BoardRow(new Title("title"), 1);
    BoardRow boardRow2 = new BoardRow(new Title("differentTitle"), 1);
    assertTrue(!boardRow1.sameAs(boardRow2));
    assertTrue(boardRow1.sameAs(boardRow1));
  }

}
