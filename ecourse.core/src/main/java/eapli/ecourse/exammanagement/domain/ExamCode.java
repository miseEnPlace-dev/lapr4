package eapli.ecourse.exammanagement.domain;

import java.util.UUID;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class ExamCode implements ValueObject, Comparable<ExamCode> {
  private static final long serialVersionUID = 1L;

  private String code;

  private ExamCode(final String examCode) {
    Preconditions.nonEmpty(examCode);

    this.code = examCode;
  }

  protected ExamCode() {
    // for ORM
  }

  public static ExamCode valueOf(final String examCode) {
    return new ExamCode(examCode);
  }

  public static ExamCode newID() {
    return valueOf(UUID.randomUUID().toString());
  }

  @Override
  public String toString() {
    return this.code;
  }

  @Override
  public int compareTo(final ExamCode arg0) {
    return code.compareTo(arg0.code);
  }
}
