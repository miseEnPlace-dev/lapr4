package eapli.ecourse.eventsmanagement.courseclassmanagement.domain;

import java.util.Calendar;
import javax.persistence.Entity;
import javax.persistence.Version;

import eapli.ecourse.eventsmanagement.domain.Time;
import eapli.framework.validations.Preconditions;

@Entity
public class SpecialClass implements Comparable<SpecialClass> {
  private static final long serialVersionUID = 1L;

  @Version
  private Long version;

  private Time time;

  public SpecialClass(final Time time) {
    Preconditions.nonNull(time);

    this.time = time;
  }

  protected SpecialClass() {
    // for ORM
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
