package eapli.ecourse.coursemanagement.domain;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class EnrolmentLimits implements ValueObject {
  private static final long serialVersionUID = 1L;

  private Integer max;
  private Integer min;

  protected EnrolmentLimits(final Integer max, final Integer min) {
    if (min < 0)
      throw new IllegalArgumentException("Course minimum limit cannot be negative");
    if (min > max)
      throw new IllegalArgumentException("Minimum limit cannot be greater than max");

    this.max = max;
    this.min = min;
  }

  protected EnrolmentLimits() {
    // for ORM
  }

  @Override
  public String toString() {
    return String.format("[ min=%d max=%d ]", min, max);
  }
}
