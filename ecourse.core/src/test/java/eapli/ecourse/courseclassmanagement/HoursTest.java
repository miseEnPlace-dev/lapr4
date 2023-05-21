package eapli.ecourse.courseclassmanagement;

import static org.junit.Assert.assertEquals;

import java.util.Calendar;

import org.junit.Test;

import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.Hours;

public class HoursTest {

  @Test
  public void ensureToStringWorks() {
    Hours hours = Hours.valueOf(10, 30);
    assertEquals("10:30", hours.toString());
  }

  @Test
  public void ensureValueOfWithCalendarWorks() {
    final Calendar calendar = Calendar.getInstance();

    calendar.set(Calendar.HOUR_OF_DAY, 10);
    calendar.set(Calendar.MINUTE, 30);

    Hours hours = Hours.valueOf(calendar);

    assertEquals("10:30", hours.toString());
  }

  @Test
  public void ensureValueOfWithHourAndMinuteWorks() {
    Hours hours = Hours.valueOf(10, 30);
    assertEquals("10:30", hours.toString());
  }
}
