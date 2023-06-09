package eapli.ecourse.boardmanagement.domain;

import java.util.Calendar;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.PreUpdate;
import javax.persistence.Version;
import eapli.ecourse.boardmanagement.dto.UserPermissionDTO;
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

  @ManyToOne
  private SystemUser user;

  @Column(nullable = false, updatable = false)
  private PermissionType permissionType;

  @EmbeddedId
  private UserPermissionID id;

  protected UserPermission() {
    // for ORM
  }

  public UserPermission(final Calendar createdAt, final Calendar updatedAt,
      final PermissionType permissionType, final SystemUser user) {
    Preconditions.noneNull(createdAt, permissionType, user);

    this.id = UserPermissionID.newID();
    if (createdAt.after(updatedAt))
      throw new IllegalArgumentException("The createdAt date must be before the updatedAt date");

    if (createdAt.after(Calendar.getInstance()))
      throw new IllegalArgumentException("The createdAt date must be before the current date");

    this.createdAt = createdAt;

    // if the updatedAt is null, then it is the same as createdAt
    this.updatedAt = (updatedAt == null) ? createdAt : updatedAt;
    this.permissionType = permissionType;
    this.user = user;
  }

  public UserPermission(final UserPermissionID id, final Calendar createdAt,
      final Calendar updatedAt, final PermissionType permissionType, final SystemUser user) {
    Preconditions.noneNull(id, createdAt, permissionType, user);

    this.id = id;
    if (createdAt.after(updatedAt))
      throw new IllegalArgumentException("The createdAt date must be before the updatedAt date");

    if (createdAt.after(Calendar.getInstance()))
      throw new IllegalArgumentException("The createdAt date must be before the current date");

    this.createdAt = createdAt;

    // if the updatedAt is null, then it is the same as createdAt
    this.updatedAt = (updatedAt == null) ? createdAt : updatedAt;
    this.permissionType = permissionType;
    this.user = user;
  }

  @PreUpdate
  public void preUpdateFunction() {
    this.updatedAt = Calendar.getInstance();
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

    return identity().equals(otherUserPermission.identity())
        && createdAt().equals(otherUserPermission.createdAt())
        && updatedAt().equals(otherUserPermission.updatedAt())
        && user().equals(otherUserPermission.user())
        && permissionType().equals(otherUserPermission.permissionType());

  }

  public UserPermissionDTO toDto() {
    return new UserPermissionDTO(createdAt, updatedAt, user, permissionType, id);
  }

  public boolean canWrite(SystemUser user) {
    return this.user.equals(user) || this.permissionType.equals(PermissionType.read());
  }
}
