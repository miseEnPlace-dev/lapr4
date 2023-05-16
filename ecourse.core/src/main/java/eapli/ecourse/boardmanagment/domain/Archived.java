package eapli.ecourse.boardmanagment.domain;

import java.util.Calendar;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class Archived implements ValueObject {
  private static final long serialVersionUID = 1L;

  private Calendar archivedAt;

  public Archived(final Calendar archivedAt) {
    validate(archivedAt);

    this.archivedAt = archivedAt;
  }

  protected Archived() {
    // for ORM
  }

  public String archivedAt() {
    return this.archivedAt.toString();
  }

  private void validate(final Calendar archivedAt) {
    if (archivedAt.after(Calendar.getInstance())) {
      throw new IllegalArgumentException("Archived date should be in the past");
    }
  }
}
