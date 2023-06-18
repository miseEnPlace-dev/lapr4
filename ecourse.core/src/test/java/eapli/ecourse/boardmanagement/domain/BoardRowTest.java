package eapli.ecourse.boardmanagement.domain;

import static org.junit.Assert.assertFalse;
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
    assertThrows(IllegalArgumentException.class, () -> new BoardRow(new BoardTitle("title"), null));
  }

  @Test
  public void ensureSameAsWorks() {
    BoardRow boardRow1 = new BoardRow(new BoardTitle("title"), 1);
    BoardRow boardRow2 = new BoardRow(new BoardTitle("differentTitle"), 1);
    assertFalse(boardRow1.sameAs(boardRow2));
    assertTrue(boardRow1.sameAs(boardRow1));
  }

  @Test
  public void ensureSameAsWorksDifferentObject() {
    BoardRow boardRow1 = new BoardRow(new BoardTitle("title"), 1);
    assertFalse(boardRow1.sameAs(new Object()));
  }

  @Test
  public void testRowNumber() {
    BoardRow boardRow1 = new BoardRow(new BoardTitle("title"), 1);
    BoardRow boardRow2 = new BoardRow(new BoardTitle("title"), 2);
    assertTrue(boardRow1.rowNumber().equals(1));
    assertFalse(boardRow1.rowNumber().equals(2));
  }

  @Test
  public void testGetHash() {
    BoardRow boardRow1 = new BoardRow(new BoardTitle("title"), 1);
    BoardRow boardRow2 = new BoardRow(new BoardTitle("title"), 2);
    assertTrue(boardRow1.hashCode() == boardRow1.hashCode());
    assertFalse(boardRow1.hashCode() == boardRow2.hashCode());
  }
}
