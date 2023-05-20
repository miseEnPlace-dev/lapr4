package eapli.ecourse.boardmanagement.domain;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class Archived implements ValueObject, Comparable<Archived> {
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

  @Override
  public String toString() {
    DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy HH:mm");
    return dateFormat.format(this.archivedAt.getTime());
  }

  @Override
  public int compareTo(Archived o) {
    return this.archivedAt.compareTo(o.archivedAt);
  }
}
