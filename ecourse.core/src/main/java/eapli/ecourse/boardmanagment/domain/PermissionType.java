package eapli.ecourse.boardmanagment.domain;

import javax.persistence.Embeddable;

import eapli.framework.domain.model.ValueObject;
import lombok.EqualsAndHashCode;

@Embeddable
@EqualsAndHashCode
public class PermissionType implements ValueObject {
  public enum Type {
    READ, WRITE
  }

  private Type type;

  public PermissionType(Type type) {
    this.type = type;
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
