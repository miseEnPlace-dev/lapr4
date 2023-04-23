package eapli.ecourse.coursemanagement.domain;

public enum CourseEnrolmentState {
  OPEN, CLOSED;

  public static CourseEnrolmentState getType(String type) {
    if (type.equals("Open"))
      return CourseEnrolmentState.OPEN;
    if (type.equals("Closed"))
      return CourseEnrolmentState.CLOSED;

    return null;
  }
}
