package eapli.ecourse.boardmanagement.domain;

import java.util.UUID;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class UserPermissionID implements ValueObject, Comparable<UserPermissionID> {
  private static final long serialVersionUID = 1L;

  private String id;

  private UserPermissionID(final String userPermissionId) {
    Preconditions.nonEmpty(userPermissionId);

    this.id = userPermissionId;
  }

  protected UserPermissionID() {
    // for ORM
  }

  public static UserPermissionID valueOf(final String userPermissionID) {
    return new UserPermissionID(userPermissionID);
  }

  public static UserPermissionID newID() {
    return valueOf(UUID.randomUUID().toString());
  }

  @Override
  public String toString() {
    return this.id;
  }

  @Override
  public int compareTo(final UserPermissionID arg0) {
    return id.compareTo(arg0.id);
  }
}
