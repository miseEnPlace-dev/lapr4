package eapli.ecourse.exammanagement.domain;

import javax.persistence.Version;

public class FormativeExam extends Exam {
  private static final long serialVersionUID = 1L;

  @Version
  private Long version;

  @Override
  public boolean sameAs(Object other) {
    if (!(other instanceof FormativeExam))
      return false;

    final FormativeExam that = (FormativeExam) other;
    if (this == that)
      return true;

    // TODO: compare fields
    return super.identity().equals(that.identity());
  }
}
