package eapli.ecourse.teachermanagement.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Version;

import eapli.ecourse.coursemanagement.dto.TeacherDTO;
import eapli.ecourse.studentmanagement.domain.TaxPayerNumber;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

/**
 * TODO: missing acronym, birthDate, validations etc and probably other things.
 */
@Entity
public class Teacher implements AggregateRoot<TaxPayerNumber> {

  private static final long serialVersionUID = 1L;

  @Version
  private Long version;

  @EmbeddedId
  private TaxPayerNumber taxPayerNumber;

  /**
   * cascade = CascadeType.NONE as the systemUser is part of another aggregate
   */
  @OneToOne(optional = false)
  private SystemUser systemUser;

  public Teacher(final SystemUser user, final TaxPayerNumber taxPayerNumber) {
    Preconditions.noneNull(taxPayerNumber, user);

    this.systemUser = user;
    this.taxPayerNumber = taxPayerNumber;
  }

  protected Teacher() {
    // for ORM only
  }

  public SystemUser user() {
    return this.systemUser;
  }

  @Override
  public boolean equals(final Object o) {
    return DomainEntities.areEqual(this, o);
  }

  @Override
  public int hashCode() {
    return DomainEntities.hashCode(this);
  }

  @Override
  public boolean sameAs(final Object other) {
    return DomainEntities.areEqual(this, other);
  }

  public TaxPayerNumber taxPayerNumber() {
    return identity();
  }

  @Override
  public TaxPayerNumber identity() {
    return this.taxPayerNumber;
  }

  public TeacherDTO toDto() {
    return new TeacherDTO(this.taxPayerNumber);
  }
}
