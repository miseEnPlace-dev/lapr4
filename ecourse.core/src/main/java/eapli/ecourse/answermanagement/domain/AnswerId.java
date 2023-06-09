package eapli.ecourse.answermanagement.domain;

import java.util.UUID;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class AnswerId implements ValueObject, Comparable<AnswerId> {
  private static final long serialVersionUID = 1L;

  private String code;

  public AnswerId(final String courseCode) {
    Preconditions.nonEmpty(courseCode);

    this.code = courseCode;
  }

  protected AnswerId() {
    // for ORM
    this.code = null;
  }

  public static AnswerId valueOf(final String courseCode) {
    return new AnswerId(courseCode);
  }

  public static AnswerId newID() {
    return valueOf(UUID.randomUUID().toString());
  }

  @Override
  public String toString() {
    return this.code;
  }

  @Override
  public int compareTo(final AnswerId arg0) {
    return code.compareTo(arg0.code);
  }
}
