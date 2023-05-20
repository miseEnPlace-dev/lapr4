package eapli.ecourse.boardmanagement.domain;

import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import eapli.framework.domain.model.ValueObject;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class PermissionType implements ValueObject {
  public enum Type {
    READ, WRITE
  }

  public static PermissionType read() {
    return new PermissionType(Type.READ);
  }

  public static PermissionType write() {
    return new PermissionType(Type.WRITE);
  }

  public static Type[] options() {
    return new Type[] { Type.READ, Type.WRITE };
  }

  @Enumerated(EnumType.STRING)
  private Type type;

  public PermissionType(Type type) {
    if (type == null)
      throw new IllegalArgumentException("Permission type should not be null");
    this.type = type;
  }

  protected PermissionType() {
    // for ORM
  }

  public void changeToRead() {
    type = Type.READ;
  }

  public void changeToWrite() {
    type = Type.WRITE;
  }

  public boolean isRead() {
    return type == Type.READ;
  }

  public boolean isWrite() {
    return type == Type.WRITE;
  }

}
