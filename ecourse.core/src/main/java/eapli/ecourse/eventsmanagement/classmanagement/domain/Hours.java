package eapli.ecourse.eventsmanagement.classmanagement.domain;

import javax.persistence.Embeddable;
import eapli.ecourse.eventsmanagement.domain.Time;
import eapli.framework.domain.model.ValueObject;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class Hours implements ValueObject {
  private static final long serialVersionUID = 1L;

  private Time hour;

  public Hours(Time hour) {
    this.hour = hour;
  }

  protected Hours() {
    // for ORM
  }

  public static Hours valueOf(final Time hour) {
    return new Hours(hour);
  }

  @Override
  public String toString() {
    return this.hour.toString();
  }

  public boolean isSameDay(final Time other) {
    return this.hour == other;
  }
}
