package eapli.ecourse.eventsmanagement.courseclassmanagement.domain;

import java.util.Calendar;

import javax.persistence.Embeddable;

import eapli.ecourse.eventsmanagement.domain.Duration;
import eapli.framework.domain.model.ValueObject;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class Hours implements ValueObject, Comparable<Hours> {
  private static final long serialVersionUID = 1L;

  private Integer hours;
  private Integer minutes;

  private Hours(int hour, int minute) {
  }

  protected Hours() {
    // for ORM
  }

  public static Hours valueOf(final Calendar calendar) {
    return new Hours(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
  }

  public Hours addDuration(Duration duration) {
    return new Hours(this.hours + duration.hour(), this.minutes + duration.minute());
  }

  @Override
  public String toString() {
    return this.hours.toString();
  }

  @Override
  public int compareTo(final Hours other) {
    Integer thisToInt = this.hours * 60 + this.minutes;
    Integer thatToInt = other.hours * 60 + other.minutes;

    return Integer.compare(thisToInt, thatToInt);
  }
}
