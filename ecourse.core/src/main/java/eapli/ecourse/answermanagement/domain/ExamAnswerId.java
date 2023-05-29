package eapli.ecourse.answermanagement.domain;

import java.util.UUID;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class ExamAnswerId implements ValueObject, Comparable<ExamAnswerId> {
  private static final long serialVersionUID = 1L;

  private String code;

  public ExamAnswerId(final String courseCode) {
    Preconditions.nonEmpty(courseCode);

    this.code = courseCode;
  }

  protected ExamAnswerId() {
    // for ORM
    this.code = null;
  }

  public static ExamAnswerId valueOf(final String courseCode) {
    return new ExamAnswerId(courseCode);
  }

  public static ExamAnswerId newID() {
    return valueOf(UUID.randomUUID().toString());
  }

  @Override
  public String toString() {
    return this.code;
  }

  @Override
  public int compareTo(final ExamAnswerId arg0) {
    return code.compareTo(arg0.code);
  }
}
