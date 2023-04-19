package eapli.ecourse.coursemanagement.domain;

public enum EnrolmentState {
  OPEN, CLOSED;

  public static EnrolmentState getType(String type) {
    if (type.equals("Open"))
      return EnrolmentState.OPEN;
    if (type.equals("Closed"))
      return EnrolmentState.CLOSED;

    return null;
  }
}
