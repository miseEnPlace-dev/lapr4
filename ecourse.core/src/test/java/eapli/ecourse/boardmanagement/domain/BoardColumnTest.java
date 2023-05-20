package eapli.ecourse.boardmanagement.domain;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class BoardColumnTest {

  @Test
  public void ensureBoardColumnHasTitle() {
    assertThrows(IllegalArgumentException.class, () -> new BoardColumn(null, 1));
  }

  @Test
  public void ensureBoardColumnHasNumber() {
    assertThrows(IllegalArgumentException.class, () -> new BoardColumn(new BoardTitle("title"), null));
  }

  @Test
  public void ensureSameAsWorks() {
    BoardColumn boardColumn1 = new BoardColumn(new BoardTitle("title"), 1);
    BoardColumn boardColumn2 = new BoardColumn(new BoardTitle("differentTitle"), 1);
    assertTrue(!boardColumn1.sameAs(boardColumn2));
    assertTrue(boardColumn1.sameAs(boardColumn1));
  }

  @Test
  public void ensureSameAsWorksDifferentObject() {
    BoardColumn boardColumn1 = new BoardColumn(new BoardTitle("title"), 1);
    assertTrue(!boardColumn1.sameAs(new Object()));
  }

}
