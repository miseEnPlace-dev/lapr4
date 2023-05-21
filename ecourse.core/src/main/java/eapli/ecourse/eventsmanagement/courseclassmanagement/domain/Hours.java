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
    this.hours = hour;
    this.minutes = minute;
  }

  protected Hours() {
    // for ORM
  }

  public static Hours valueOf(final Calendar calendar) {
    return new Hours(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
  }

  public static Hours valueOf(int hour, int minute) {
    return new Hours(hour, minute);
  }

  public Hours addDuration(Duration duration) {
    Calendar c = Calendar.getInstance();
    c.set(Calendar.HOUR_OF_DAY, this.hours);
    c.set(Calendar.MINUTE, this.minutes);
    c.add(Calendar.MINUTE, duration.minute());

    return Hours.valueOf(c);
  }

  @Override
  public String toString() {
    return String.format("%02d:%02d", this.hours, this.minutes);
  }

  @Override
  public int compareTo(final Hours other) {
    Integer thisToInt = this.hours * 60 + this.minutes;
    Integer thatToInt = other.hours * 60 + other.minutes;

    return Integer.compare(thisToInt, thatToInt);
  }
}
