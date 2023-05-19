package eapli.ecourse.coursemanagement.domain;

import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;

public class CourseTitleTest {
  @Test
  public void ensureItIsNotPossibleToCreateTitleWithNull() {
    assertThrows(IllegalArgumentException.class, () -> new CourseTitle(null));
  }

  @Test
  public void ensureItIsNotPossibleToCreateTitleWithEmpty() {
    assertThrows(IllegalArgumentException.class, () -> new CourseTitle(""));
  }

  @Test
  public void ensureToStringWorks() {
    assertEquals("Sample title", new CourseTitle("Sample title").toString());
  }

  @Test
  public void ensureCompareToWorks() {
    assertEquals(0, new CourseTitle("Sample title").compareTo(new CourseTitle("Sample title")));
    assertTrue(new CourseTitle("Sample title").compareTo(new CourseTitle("Sample title 2")) < 0);
    assertTrue(new CourseTitle("Sample title 2").compareTo(new CourseTitle("Sample title")) > 0);
  }
}
