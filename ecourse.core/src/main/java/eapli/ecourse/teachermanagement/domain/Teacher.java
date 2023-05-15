package eapli.ecourse.teachermanagement.domain;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Version;

import eapli.ecourse.teachermanagement.dto.TeacherDTO;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

@Entity
public class Teacher implements AggregateRoot<TaxPayerNumber> {

  private static final long serialVersionUID = 1L;

  @Version
  private Long version;

  @EmbeddedId
  private TaxPayerNumber taxPayerNumber;

  @Column(nullable = false)
  private Acronym acronym;

  @Column(nullable = false)
  private BirthDate birthDate;

  /**
   * cascade = CascadeType.NONE as the systemUser is part of another aggregate
   */
  @OneToOne(optional = false)
  private SystemUser systemUser;

  public Teacher(final SystemUser user, final TaxPayerNumber taxPayerNumber, final Acronym acronym,
      final BirthDate birthDate) {
    Preconditions.noneNull(taxPayerNumber, user);

    this.systemUser = user;
    this.taxPayerNumber = taxPayerNumber;
    this.acronym = acronym;
    this.birthDate = birthDate;
  }

  protected Teacher() {
    // for ORM only
  }

  public SystemUser user() {
    return this.systemUser;
  }

  public Acronym acronym() {
    return this.acronym;
  }

  public BirthDate birthDate() {
    return this.birthDate;
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
    if (!(other instanceof Teacher))
      return false;

    if (this == other)
      return true;

    final Teacher that = (Teacher) other;

    return this.user().sameAs(other) && this.acronym().equals(that.acronym())
        && this.birthDate().equals(that.birthDate());
  }

  public TaxPayerNumber taxPayerNumber() {
    return identity();
  }

  @Override
  public TaxPayerNumber identity() {
    return this.taxPayerNumber;
  }

  public TeacherDTO toDto() {
    return new TeacherDTO(this.taxPayerNumber, this.acronym, this.birthDate, this.systemUser.username());
  }
}
