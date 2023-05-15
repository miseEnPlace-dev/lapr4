package eapli.ecourse.eventsmanagement.classmanagement.domain;

import java.util.Calendar;
import javax.persistence.Embeddable;
import eapli.framework.domain.model.ValueObject;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class SpecialClass implements ValueObject, Comparable<SpecialClass> {
  private static final long serialVersionUID = 1L;

  private Calendar time;

  protected SpecialClass(final Calendar time) {
    if (time == null)
      throw new IllegalArgumentException("Time should not be null");

    this.time = time;
  }

  protected SpecialClass() {
    // for ORM
  }

  public static SpecialClass valueOf(final Calendar time) {
    return new SpecialClass(time);
  }

  @Override
  public String toString() {
    return this.time.toString();
  }

  @Override
  public int compareTo(final SpecialClass arg0) {
    // should a special class be the same if it is at the same time?
    return time.compareTo(arg0.time);
  }
}
