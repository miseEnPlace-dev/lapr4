package eapli.ecourse.coursemanagement.domain;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class EnrolmentLimits implements ValueObject {
  private static final long serialVersionUID = 1L;

  private Integer min;
  private Integer max;

  protected EnrolmentLimits(final Integer min, final Integer max) {
    if (min < 0)
      throw new IllegalArgumentException("Course minimum limit cannot be negative");
    if (min > max)
      throw new IllegalArgumentException("Minimum limit cannot be greater than max");

    this.min = min;
    this.max = max;
  }

  protected EnrolmentLimits() {
    // for ORM
  }

  public static EnrolmentLimits valueOf(final Integer min, final Integer max) {
    return new EnrolmentLimits(min, max);
  }

  @Override
  public String toString() {
    return String.format("[ min=%d max=%d ]", min, max);
  }
}
