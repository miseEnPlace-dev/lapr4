package eapli.ecourse.coursemanagement.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

public class CourseEnrolmentLimitsTest {
  @Test
  public void ensureItIsNotPossibleToCreateACourseEnrolmentLimitsWithNegativeMin() {
    assertThrows(IllegalArgumentException.class, () -> new EnrolmentLimits(-1, 10));
  }

  @Test
  public void ensureItIsNotPossibleToCreateEnrolmentLimitsWithMinGreaterThanMax() {
    assertThrows(IllegalArgumentException.class, () -> new EnrolmentLimits(10, 1));
  }

  @Test
  public void ensureToStringPrintsCorrectFormat() {
    EnrolmentLimits limits = new EnrolmentLimits(10, 20);
    String expected = "[ min=10 max=20 ]";
    String result = limits.toString();
    assertEquals(expected, result);
  }

  @Test
  public void ensureHashCodeWorks() {
    Integer min = 10;
    Integer max = 20;
    EnrolmentLimits limits = new EnrolmentLimits(min, max);
    int expected = min.hashCode() + max.hashCode();
    int result = limits.hashCode();
    assertEquals(expected, result);
  }
}
