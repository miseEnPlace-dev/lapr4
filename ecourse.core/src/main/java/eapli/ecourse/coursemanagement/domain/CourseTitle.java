package eapli.ecourse.coursemanagement.domain;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class CourseTitle implements ValueObject, Comparable<CourseTitle> {
  private static final long serialVersionUID = 1L;

  private String title;

  protected CourseTitle(final String courseTitle) {
    if (StringPredicates.isNullOrEmpty(courseTitle))
      throw new IllegalArgumentException("Course Title should neither be null nor empty");

    // TODO: course title validation
    this.title = courseTitle;
  }

  protected CourseTitle() {
    // for ORM
  }

  public static CourseTitle valueOf(final String courseTitle) {
    return new CourseTitle(courseTitle);
  }

  @Override
  public String toString() {
    return this.title;
  }

  @Override
  public int compareTo(final CourseTitle arg0) {
    return title.compareTo(arg0.title);
  }
}
