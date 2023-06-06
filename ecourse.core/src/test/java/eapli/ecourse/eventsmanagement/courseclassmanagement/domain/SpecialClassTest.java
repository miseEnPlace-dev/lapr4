package eapli.ecourse.eventsmanagement.courseclassmanagement.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Before;
import org.junit.Test;

import eapli.ecourse.eventsmanagement.domain.Time;

public class SpecialClassTest {

  private SpecialClass specialClass;
  private Time time;

  @Before
  public void setUp() {
    Calendar calendar = Calendar.getInstance();

    calendar.set(Calendar.DAY_OF_MONTH, 10);
    calendar.set(Calendar.MONTH, 9);
    calendar.set(Calendar.YEAR, 2020);
    calendar.set(Calendar.HOUR_OF_DAY, 10);
    calendar.set(Calendar.MINUTE, 30);
    time = Time.valueOf(calendar);
    specialClass = new SpecialClass(time);
  }

  @Test
  public void testTime() {
    assertEquals(time, specialClass.time());
  }

  @Test
  public void testToString() {
    assertEquals("10/10/2020 10:30", specialClass.toString());
  }

  @Test
  public void testSameAs() {
    Calendar calendar = Calendar.getInstance();
    calendar.set(Calendar.HOUR_OF_DAY, 10);
    calendar.set(Calendar.MINUTE, 30);

    SpecialClass other = new SpecialClass(Time.valueOf(calendar));
    assertTrue(specialClass.sameAs(other));
  }
}
