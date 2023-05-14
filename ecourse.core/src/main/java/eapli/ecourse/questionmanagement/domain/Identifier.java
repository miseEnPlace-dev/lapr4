package eapli.ecourse.questionmanagement.domain;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class Identifier implements ValueObject {
  private static final long serialVersionUID = 1L;

  private String identifier;

  private Identifier(final String identifier) {
    this.identifier = identifier;
  }

  protected Identifier() {
    // for ORM
  }

  public static Identifier valueOf(final String identifier) {
    return new Identifier(identifier);
  }

  @Override
  public String toString() {
    return this.identifier;
  }
}
