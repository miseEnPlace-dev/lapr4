package eapli.ecourse.eventsmanagement.courseclassmanagement.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.Test;

public class DayInWeekTest {
  @Test
  public void testValueOfWeekDay() {
    DayInWeek day = DayInWeek.valueOf(WeekDay.MONDAY);
    assertTrue(day.isSameDay(DayInWeek.valueOf(WeekDay.MONDAY)));
  }

  @Test
  public void testValueOfInt() {
    DayInWeek day = DayInWeek.valueOf(1);
    assertTrue(day.isSameDay(DayInWeek.valueOf(WeekDay.MONDAY)));
  }

  @Test
  public void testToString() {
    DayInWeek day = DayInWeek.valueOf(WeekDay.MONDAY);
    assertEquals("MONDAY", day.toString());
  }

  @Test
  public void testIsSameDay() {
    DayInWeek day1 = DayInWeek.valueOf(WeekDay.MONDAY);
    DayInWeek day2 = DayInWeek.valueOf(WeekDay.TUESDAY);
    assertFalse(day1.isSameDay(day2));
    assertTrue(day1.isSameDay(DayInWeek.valueOf(WeekDay.MONDAY)));
  }

  @Test
  public void testInvalidValueOfInt() {
    assertThrows(IllegalArgumentException.class, () -> {
      DayInWeek.valueOf(7);
    });
  }
}
