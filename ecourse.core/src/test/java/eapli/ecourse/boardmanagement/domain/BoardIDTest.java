package eapli.ecourse.boardmanagement.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class BoardIDTest {

  @Test
  public void ensureBoardIDHasID() {
    BoardID boardID = BoardID.newID();
    assert (boardID.toString() != null);
  }

  @Test
  public void ensureToStringWorks() {
    BoardID boardID = BoardID.valueOf("boardID");
    assertEquals("boardID", boardID.toString());
  }

  @Test
  public void ensureNewIDWorks() {
    BoardID boardID = BoardID.newID();
    assert (boardID.toString() != null);
  }

  @Test
  public void ensureCompareToWorks() {
    BoardID boardID1 = BoardID.valueOf("boardID1");
    BoardID boardID2 = BoardID.valueOf("boardID2");
    assert (boardID1.compareTo(boardID2) != 0);
  }

  @Test
  public void ensureEqualsWorks() {
    BoardID boardID1 = BoardID.valueOf("boardID1");
    BoardID boardID2 = BoardID.valueOf("boardID2");
    assert (boardID1.equals(boardID2) == false);
  }

}
