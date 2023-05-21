package eapli.ecourse.eventsmanagement.courseclassmanagement.domain;

import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DayInWeekTest {

  @Test
  public void ensureIsSameDayWorks() {
    DayInWeek d = DayInWeek.valueOf(WeekDay.MONDAY);

    assertTrue(d.isSameDay(DayInWeek.valueOf(WeekDay.MONDAY)));
    assertTrue(!d.isSameDay(DayInWeek.valueOf(WeekDay.TUESDAY)));
  }

  @Test
  public void ensureToStringWorks() {
    DayInWeek d = DayInWeek.valueOf(WeekDay.MONDAY);

    assertTrue(d.toString().equals("MONDAY"));
  }

}
