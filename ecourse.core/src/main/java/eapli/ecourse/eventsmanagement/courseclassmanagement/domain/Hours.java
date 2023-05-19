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

  private Integer hour;
  private Integer minute;

  private Hours(int hour, int minute) {
  }

  protected Hours() {
    // for ORM
  }

  public static Hours valueOf(final Calendar calendar) {
    return new Hours(calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE));
  }

  public Hours addDuration(Duration duration) {
    return new Hours(this.hour + duration.hour(), this.minute + duration.minute());
  }

  @Override
  public String toString() {
    return this.hour.toString();
  }

  @Override
  public int compareTo(final Hours other) {
    Integer thisToInt = this.hour * 60 + this.minute;
    Integer thatToInt = other.hour * 60 + other.minute;

    return Integer.compare(thisToInt, thatToInt);
  }
}
