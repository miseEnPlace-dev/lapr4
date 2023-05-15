package eapli.ecourse.eventsmanagement.domain;

import java.util.Calendar;
import javax.persistence.Embeddable;
import eapli.framework.domain.model.ValueObject;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class Time implements ValueObject, Comparable<Time> {
  private static final long serialVersionUID = 1L;

  private Calendar time;

  protected Time(final Calendar time) {
    if (time == null)
      throw new IllegalArgumentException("Time should not be null");

    this.time = time;
  }

  protected Time() {
    // for ORM
  }

  public static Time valueOf(final Calendar time) {
    return new Time(time);
  }

  @Override
  public String toString() {
    return this.time.toString();
  }

  @Override
  public int compareTo(final Time arg0) {
    return time.compareTo(arg0.time);
  }
}
