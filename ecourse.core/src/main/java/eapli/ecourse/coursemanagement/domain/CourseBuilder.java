package eapli.ecourse.coursemanagement.domain;

import eapli.framework.domain.model.DomainFactory;
import eapli.framework.validations.Preconditions;

public class CourseBuilder implements DomainFactory<Course> {
  private Course course;

  private CourseCode code;
  private CourseTitle title;
  private CourseDescription description;
  private EnrolmentLimits enrolmentLimits;

  public CourseBuilder withCode(String code) {
    this.code = new CourseCode(code);
    return this;
  }

  public CourseBuilder withCode(CourseCode code) {
    this.code = code;
    return this;
  }

  public CourseBuilder withTitle(String title) {
    this.title = new CourseTitle(title);
    return this;
  }

  public CourseBuilder withTitle(CourseTitle title) {
    this.title = title;
    return this;
  }

  public CourseBuilder withDescription(String description) {
    this.description = new CourseDescription(description);
    return this;
  }

  public CourseBuilder withDescription(CourseDescription description) {
    this.description = description;
    return this;
  }

  public CourseBuilder withEnrolmentLimits(int min, int max) {
    this.enrolmentLimits = new EnrolmentLimits(min, max);
    return this;
  }

  public CourseBuilder withEnrolmentLimits(EnrolmentLimits enrolmentLimits) {
    this.enrolmentLimits = enrolmentLimits;
    return this;
  }

  private Course buildOrThrow() {
    if (course != null)
      return course;

    Preconditions.noneNull(code, title, description, enrolmentLimits);

    course = new Course(code, title, description, enrolmentLimits);
    return course;
  }

  @Override
  public Course build() {
    final Course ret = buildOrThrow();
    course = null;
    return ret;
  }
}
