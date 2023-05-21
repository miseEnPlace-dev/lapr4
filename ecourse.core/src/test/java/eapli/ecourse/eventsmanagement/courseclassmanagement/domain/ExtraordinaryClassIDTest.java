package eapli.ecourse.eventsmanagement.courseclassmanagement.domain;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class ExtraordinaryClassIDTest {

  @Test
  public void ensureExtraordinaryClassIDHasID() {
    ExtraordinaryClassID extraordinaryClassID = ExtraordinaryClassID.newID();
    assert (extraordinaryClassID.toString() != null);
  }

  @Test
  public void ensureToStringWorks() {
    ExtraordinaryClassID extraordinaryClassID = ExtraordinaryClassID.valueOf("extraordinaryClassID");
    assertEquals("extraordinaryClassID", extraordinaryClassID.toString());
  }

  @Test
  public void ensureNewIDWorks() {
    ExtraordinaryClassID extraordinaryClassID = ExtraordinaryClassID.newID();
    assert (extraordinaryClassID.toString() != null);
  }

  @Test
  public void ensureCompareToWorks() {
    ExtraordinaryClassID extraordinaryClassID1 = ExtraordinaryClassID.valueOf("extraordinaryClassID1");
    ExtraordinaryClassID extraordinaryClassID2 = ExtraordinaryClassID.valueOf("extraordinaryClassID2");
    assert (extraordinaryClassID1.compareTo(extraordinaryClassID2) != 0);
  }

  @Test
  public void ensureEqualsWorks() {
    ExtraordinaryClassID extraordinaryClassID1 = ExtraordinaryClassID.valueOf("extraordinaryClassID1");
    ExtraordinaryClassID extraordinaryClassID2 = ExtraordinaryClassID.valueOf("extraordinaryClassID2");
    assert (extraordinaryClassID1.equals(extraordinaryClassID2) == false);
  }

}
