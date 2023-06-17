package eapli.ecourse.answermanagement.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class AnswerIDTest {
  @Test
  public void testCreateAnswerId() {
    AnswerId answerId = new AnswerId("A12345");
    assertNotNull(answerId);
    assertEquals("A12345", answerId.toString());
  }

  @Test
  public void testCreateAnswerIdWithEmptyCode() {
    assertThrows(IllegalArgumentException.class, () -> new AnswerId(""));
  }

  @Test
  public void testValueOf() {
    AnswerId answerId = AnswerId.valueOf("A12345");
    assertNotNull(answerId);
    assertEquals("A12345", answerId.toString());
  }

  @Test
  public void testNewID() {
    AnswerId answerId1 = AnswerId.newID();
    AnswerId answerId2 = AnswerId.newID();
    assertNotNull(answerId1);
    assertNotNull(answerId2);
    assertNotEquals(answerId1, answerId2);
  }

  @Test
  public void testCompareTo() {
    AnswerId answerId1 = new AnswerId("A12345");
    AnswerId answerId2 = new AnswerId("B12345");
    assertEquals(-1, answerId1.compareTo(answerId2));
    assertEquals(1, answerId2.compareTo(answerId1));
    assertEquals(0, answerId1.compareTo(answerId1));
  }

  @Test
  public void testEquals() {
    AnswerId answerId1 = new AnswerId("A12345");
    AnswerId answerId2 = new AnswerId("A12345");
    AnswerId answerId3 = new AnswerId("B12345");
    assertEquals(answerId1, answerId2);
    assertNotEquals(answerId1, answerId3);
    assertNotEquals(answerId1, null);
    assertNotEquals(answerId1, "A12345");
  }

  @Test
  public void testHashCode() {
    AnswerId answerId1 = new AnswerId("A12345");
    AnswerId answerId2 = new AnswerId("A12345");
    AnswerId answerId3 = new AnswerId("B12345");
    assertEquals(answerId1.hashCode(), answerId2.hashCode());
    assertNotEquals(answerId1.hashCode(), answerId3.hashCode());
  }

  @Test
  public void testToString() {
    AnswerId answerId = new AnswerId("A12345");
    assertEquals("A12345", answerId.toString());
  }

  @Test
  public void testCanEqual() {
    AnswerId answerId = new AnswerId("A12345");
    AnswerId answerId2 = new AnswerId("A12345");
    assertTrue(answerId.canEqual(answerId2));
  }
}
