package eapli.ecourse.eventsmanagement.courseclassmanagement.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Test;

import eapli.ecourse.eventsmanagement.domain.Duration;

public class HoursTest {

  @Test
  public void ensureCompareToWorks() {
    Calendar c = Calendar.getInstance();
    Calendar c2 = Calendar.getInstance();
    c.add(Calendar.HOUR_OF_DAY, 8);

    Hours h = Hours.valueOf(c);
    Hours h2 = Hours.valueOf(c2);

    assertTrue(h.compareTo(h2) > 0);
    assertTrue(h2.compareTo(h) < 0);
    assertEquals(0, h.compareTo(h));
  }

  @Test
  public void ensureAddDurationWorks() {
    Calendar c = Calendar.getInstance();
    c.set(Calendar.HOUR_OF_DAY, 8);
    c.set(Calendar.MINUTE, 30);

    Hours h = Hours.valueOf(c);
    Hours h2 = h.addDuration(Duration.valueOf(30));

    assertEquals("09:00", h2.toString());
  }

  @Test
  public void ensureToStringWorks() {
    Calendar c = Calendar.getInstance();
    c.set(Calendar.HOUR_OF_DAY, 8);
    c.set(Calendar.MINUTE, 30);

    Hours h = Hours.valueOf(c);

    assertEquals("08:30", h.toString());
  }

}
