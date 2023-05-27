package eapli.ecourse.coursemanagement.domain;

import java.util.UUID;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

@Embeddable
public class CourseCode implements ValueObject, Comparable<CourseCode> {
  private static final long serialVersionUID = 1L;

  private String code;

  public CourseCode(final String courseCode) {
    Preconditions.nonEmpty(courseCode);

    this.code = courseCode;
  }

  protected CourseCode() {
    // for ORM
    this.code = null;
  }

  public static CourseCode valueOf(final String courseCode) {
    return new CourseCode(courseCode);
  }

  public static CourseCode newID() {
    return valueOf(UUID.randomUUID().toString());
  }

  @Override
  public String toString() {
    return this.code;
  }

  @Override
  public int compareTo(final CourseCode arg0) {
    return code.compareTo(arg0.code);
  }
}
