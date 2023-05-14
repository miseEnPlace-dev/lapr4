package eapli.ecourse.coursemanagement.domain;

import java.util.Set;

import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.framework.domain.model.DomainFactory;
import eapli.framework.validations.Preconditions;

public class CourseBuilder implements DomainFactory<Course> {
  private Course course;

  private CourseCode code;
  private CourseTitle title;
  private CourseDescription description;
  private EnrolmentLimits enrolmentLimits;
  private Teacher teacher;
  private Set<Teacher> teachers;

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

  public CourseBuilder withTeacher(Teacher teacher) {
    this.teacher = teacher;
    return this;
  }

  private Course buildOrThrow() {
    if (course != null)
      return course;

    Preconditions.noneNull(code, title, description, enrolmentLimits, teacher);

    course = new Course(code, title, description, enrolmentLimits, teacher);
    return course;
  }

  @Override
  public Course build() {
    final Course ret = buildOrThrow();
    course = null;
    return ret;
  }
}
