package eapli.ecourse.teachermanagement.domain;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.strings.util.StringPredicates;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class Acronym implements ValueObject {
  private static final long serialVersionUID = 1L;

  private String acronym;

  private Acronym(String acronym) {
    if (StringPredicates.isNullOrEmpty(acronym))
      throw new IllegalArgumentException("Acronym should neither be null nor empty");

    this.acronym = acronym;
  }

  protected Acronym() {
    // for ORM only
  }

  public static Acronym valueOf(String acronym) {
    return new Acronym(acronym);
  }

  @Override
  public String toString() {
    return acronym;
  }
}
