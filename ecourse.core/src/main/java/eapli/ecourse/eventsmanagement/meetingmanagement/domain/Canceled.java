package eapli.ecourse.eventsmanagement.meetingmanagement.domain;

import java.util.Calendar;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class Canceled implements ValueObject, Comparable<Canceled> {
  private static final long serialVersionUID = 1L;

  private Calendar canceledAt;

  protected Canceled() {
    // for ORM
  }

  public Canceled(Calendar canceledAt) {
    this.canceledAt = canceledAt;
  }

  @Override
  public int compareTo(Canceled arg0) {
    return canceledAt.compareTo(arg0.canceledAt);
  }

  @Override
  public String toString() {
    return canceledAt.toString();
  }

  public static Canceled valueOf(Calendar canceledAt) {
    return new Canceled(canceledAt);
  }

  @Override
  public boolean equals(Object other) {
    if (!(other instanceof Canceled))
      return false;

    final Canceled that = (Canceled) other;

    if (this == that)
      return true;

    return canceledAt.equals(that.canceledAt);
  }

  @Override
  public int hashCode() {
    return canceledAt.hashCode();
  }

  public Calendar canceledAt() {
    return this.canceledAt;
  }

}
