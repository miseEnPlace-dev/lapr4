package eapli.ecourse.eventsmanagement.courseclassmanagement.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ClassIDTest {

  @Test
  public void ensureClassIDHasID() {
    ClassID classID = ClassID.newID();
    assert (classID.toString() != null);
  }

  @Test
  public void ensureToStringWorks() {
    ClassID classID = ClassID.valueOf("classID");
    assertEquals("classID", classID.toString());
  }

  @Test
  public void ensureNewIDWorks() {
    ClassID classID = ClassID.newID();
    assert (classID.toString() != null);
  }

  @Test
  public void ensureCompareToWorks() {
    ClassID classID1 = ClassID.valueOf("classID1");
    ClassID classID2 = ClassID.valueOf("classID2");
    assert (classID1.compareTo(classID2) != 0);
  }

  @Test
  public void ensureEqualsWorks() {
    ClassID classID1 = ClassID.valueOf("classID1");
    ClassID classID2 = ClassID.valueOf("classID2");
    assert (classID1.equals(classID2) == false);
  }

}
