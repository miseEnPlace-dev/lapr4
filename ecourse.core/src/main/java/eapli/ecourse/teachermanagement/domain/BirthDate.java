package eapli.ecourse.teachermanagement.domain;

import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class BirthDate implements ValueObject {
  private static final long serialVersionUID = 1L;

  private Calendar birthDate;

  private BirthDate(Calendar birthDate) {
    if (birthDate == null)
      throw new IllegalArgumentException("Birth Date should not be null");
    if (birthDate.after(Calendar.getInstance()))
      throw new IllegalArgumentException("Birth Date should not be after today");

    this.birthDate = birthDate;
  }

  protected BirthDate() {
    // for ORM only
  }

  public static BirthDate valueOf(Calendar birthDate) {
    return new BirthDate(birthDate);
  }

  @Override
  public String toString() {
    SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
    return sdf.format(birthDate.getTime());
  }
}
