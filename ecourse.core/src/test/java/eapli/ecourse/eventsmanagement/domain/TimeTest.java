package eapli.ecourse.eventsmanagement.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

import java.util.Calendar;

import org.junit.Test;

import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.DayInWeek;
import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.Hours;

public class TimeTest {

  private Calendar getDummyCalendar() {
    Calendar calendar = Calendar.getInstance();
    calendar.set(2021, 1, 1, 10, 1, 1);
    return calendar;
  }

  @Test
  public void ensureTimeIsComparable() {
    Time time1 = new Time();
    Time time2 = new Time();
    assertEquals(time1, time2);
  }

  @Test
  public void ensureTimeIsComparableWithDifferentTime() {
    Time time1 = new Time();
    Time time2 = Time.valueOf(getDummyCalendar());

    Duration duration = new Duration(1);

    time2.addDuration(duration);
    assertNotEquals(time1, time2);
  }

  @Test
  public void ensureTimeDayInWeekWorks() {
    Time time = Time.valueOf(getDummyCalendar());

    Duration duration = new Duration(1);

    DayInWeek dayInWeek = time.dayInWeek();
    assertTrue(dayInWeek.equals(time.addDuration(duration).dayInWeek()));

  }

  @Test
  public void ensureTimeHourWorks() {
    Time time = Time.valueOf(getDummyCalendar());

    Duration duration = new Duration(1);

    int hour = time.hour();
    assertEquals(hour, time.addDuration(duration).hour());
  }

  @Test
  public void ensureTimeMinuteWorks() {
    Time time = Time.valueOf(getDummyCalendar());

    Duration duration = new Duration(1);

    int minute = time.minute();
    assertEquals(minute + 1, time.addDuration(duration).minute());
  }

  @Test
  public void ensureCompareHoursWorks() {
    Time time1 = Time.valueOf(getDummyCalendar());

    Hours hour1 = Hours.valueOf(time1.hour(), time1.minute());

    assertTrue(time1.compareHours(hour1) == 0);
  }

  @Test
  public void ensureCompareHoursWorks2() {
    Time time1 = Time.valueOf(getDummyCalendar());

    Hours hour1 = Hours.valueOf(time1.hour(), time1.minute());

    Duration duration = new Duration(1);

    assertTrue(time1.addDuration(duration).compareHours(hour1) == 1);
  }
}
