package eapli.ecourse.boardmanagement.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Version;

import eapli.framework.domain.model.DomainEntity;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.validations.Preconditions;

@Entity
public class UserPermission implements DomainEntity<UserPermissionID> {

  private static final long serialVersionUID = 1L;

  @Version
  private Long version;

  @Column(nullable = false, updatable = false)
  private Calendar createdAt;

  @Column
  private Calendar updatedAt;

  @Column(nullable = false, updatable = false)
  private SystemUser user;

  @Column(nullable = false, updatable = false)
  private PermissionType permissionType;

  @EmbeddedId
  private UserPermissionID id;

  protected UserPermission() {
    // for ORM
  }

  public UserPermission(final UserPermissionID id, final Calendar createdAt, final Calendar updatedAt,
      final PermissionType permissionType, final SystemUser user) {
    Preconditions.noneNull(id, createdAt, permissionType, user);

    this.id = id;
    this.createdAt = createdAt;
    this.updatedAt = updatedAt;
    this.permissionType = permissionType;
    this.user = user;
  }

  public Calendar createdAt() {
    return this.createdAt;
  }

  public Calendar updatedAt() {
    return this.updatedAt;
  }

  public PermissionType permissionType() {
    return this.permissionType;
  }

  public SystemUser user() {
    return this.user;
  }

  @Override
  public UserPermissionID identity() {
    return this.id;
  }

  @Override
  public boolean sameAs(Object other) {

    if (!(other instanceof UserPermission)) {
      return false;
    }

    final UserPermission otherUserPermission = (UserPermission) other;
    if (this == otherUserPermission) {
      return true;
    }

    return identity().equals(otherUserPermission.identity()) && createdAt().equals(otherUserPermission.createdAt())
        && updatedAt().equals(otherUserPermission.updatedAt()) && user().equals(otherUserPermission.user())
        && permissionType().equals(otherUserPermission.permissionType());

  }

}
