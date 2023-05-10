package eapli.ecourse.coursemanagement.domain;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class CourseDescription implements ValueObject, Comparable<CourseDescription> {
  private static final long serialVersionUID = 1L;

  private String description;

  public CourseDescription(final String courseDescription) {
    if (StringPredicates.isNullOrEmpty(courseDescription))
      throw new IllegalArgumentException("Course Description should neither be null nor empty");

    // TODO: course description validation
    this.description = courseDescription;
  }

  protected CourseDescription() {
    // for ORM
  }

  public static CourseDescription valueOf(final String courseDescription) {
    return new CourseDescription(courseDescription);
  }

  @Override
  public String toString() {
    return this.description;
  }

  @Override
  public int compareTo(final CourseDescription arg0) {
    return description.compareTo(arg0.description);
  }
}
