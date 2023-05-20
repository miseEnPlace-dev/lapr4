package eapli.ecourse.studentmanagement.domain;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.OneToOne;
import javax.persistence.Version;

import eapli.ecourse.studentmanagement.dto.StudentDTO;
import eapli.framework.domain.model.AggregateRoot;
import eapli.framework.domain.model.DomainEntities;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

/**
 * A Client User.
 * <p>
 * This class represents client users. It follows a DDD approach where User is
 * the root entity of
 * the Client User Aggregate and all of its properties are instances of value
 * objects. A Client User
 * references a
 * {@link eapli.framework.infrastructure.authz.domain.model.SystemUser
 * SystemUser}
 * <p>
 * This approach may seem a little more complex than just having String or
 * native type attributes
 * but provides for real semantic of the domain and follows the Single
 * Responsibility Pattern
 *
 * @author Jorge Santos ajs@isep.ipp.pt
 */
@Entity
public class Student implements AggregateRoot<MecanographicNumber> {
  private static final long serialVersionUID = 1L;

  @Version
  private Long version;

  @EmbeddedId
  private MecanographicNumber mecanographicNumber;

  /**
   * cascade = CascadeType.NONE as the systemUser is part of another aggregate
   */
  @OneToOne(optional = false)
  private SystemUser systemUser;

  public Student(final SystemUser user, final MecanographicNumber mecanographicNumber) {
    Preconditions.noneNull(mecanographicNumber, user);

    this.systemUser = user;
    this.mecanographicNumber = mecanographicNumber;
  }

  protected Student() {
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

  public MecanographicNumber mecanographicNumber() {
    return identity();
  }

  public StudentDTO toDto() {
    return new StudentDTO(this.mecanographicNumber, this.systemUser.username(),
        this.systemUser.name());
  }

  @Override
  public MecanographicNumber identity() {
    return this.mecanographicNumber;
  }
}
