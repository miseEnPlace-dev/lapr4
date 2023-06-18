package eapli.ecourse.boardmanagement.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Test;

public class ArchivedTest {

  @Test
  public void ensureItIsNotPossibleToCreateArchivedWithFutureDate() {
    final Calendar futureDate = Calendar.getInstance();
    futureDate.add(Calendar.DAY_OF_MONTH, 1);

    assertThrows(IllegalArgumentException.class, () -> new Archived(futureDate));
  }

  @Test
  public void ensureToStringWorks() {
    Calendar date = Calendar.getInstance();

    date.set(Calendar.MINUTE, 0);
    date.set(Calendar.HOUR_OF_DAY, 14);
    date.set(Calendar.DAY_OF_MONTH, 2);
    date.set(Calendar.MONTH, 0);
    date.set(Calendar.YEAR, 2020);

    assertEquals("02/01/2020 14:00", new Archived(date).toString());
  }

  @Test
  public void ensureCompareToWorks() {
    Calendar date1 = Calendar.getInstance();
    Calendar date2 = Calendar.getInstance();

    date2.add(Calendar.DAY_OF_MONTH, -1);

    assertEquals(0, new Archived(date1).compareTo(new Archived(date1)));
    assertTrue(new Archived(date1).compareTo(new Archived(date2)) > 0);
    assertTrue(new Archived(date2).compareTo(new Archived(date1)) < 0);
  }

  @Test
  public void ensureEqualsWorks() {
    Calendar date1 = Calendar.getInstance();
    Calendar date2 = Calendar.getInstance();

    date2.add(Calendar.DAY_OF_MONTH, -1);

    assertEquals(new Archived(date1), new Archived(date1));
    assertTrue(!new Archived(date1).equals(new Archived(date2)));
    assertTrue(!new Archived(date1).equals(new Object()));
  }

  @Test
  public void testHashCode() {
    Calendar date1 = Calendar.getInstance();
    Calendar date2 = Calendar.getInstance();

    date2.add(Calendar.DAY_OF_MONTH, -1);

    assertEquals(new Archived(date1).hashCode(), new Archived(date1).hashCode());
    assertTrue(new Archived(date1).hashCode() != new Archived(date2).hashCode());
  }
}
