package eapli.ecourse.eventsmanagement.courseclassmanagement.domain;

import java.util.UUID;

import eapli.framework.domain.model.ValueObject;
import eapli.framework.validations.Preconditions;

public class ClassID implements ValueObject, Comparable<ClassID> {
  private static final long serialVersionUID = 1L;

  private String classId;

  private ClassID(final String classId) {
    Preconditions.nonEmpty(classId);

    this.classId = UUID.fromString(classId).toString();
  }

  protected ClassID() {
    // for ORM
    this.classId = null;
  }

  public static ClassID valueOf(final String ClassID) {
    return new ClassID(ClassID);
  }

  public static ClassID newID() {
    return valueOf(UUID.randomUUID().toString());
  }

  @Override
  public String toString() {
    return this.classId;
  }

  @Override
  public int compareTo(final ClassID arg0) {
    return classId.compareTo(arg0.classId);
  }

}
