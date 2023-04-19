package eapli.ecourse.coursemanagement.domain;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;
import lombok.EqualsAndHashCode;

/**
 * @author Jorge Santos ajs@isep.ipp.pt
 */
@Embeddable
@EqualsAndHashCode
public class CourseCode implements ValueObject, Comparable<CourseCode> {
  private static final long serialVersionUID = 1L;

  private String code;

  protected CourseCode(final String courseCode) {
    if (StringPredicates.isNullOrEmpty(courseCode))
      throw new IllegalArgumentException("Course Code should neither be null nor empty");

    this.code = courseCode;
  }

  protected CourseCode() {
    // for ORM
  }

  public static CourseCode valueOf(final String mecanographicNumber) {
    return new CourseCode(mecanographicNumber);
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
