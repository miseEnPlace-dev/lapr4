package eapli.ecourse.coursemanagement.domain;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import org.junit.Test;

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
}
