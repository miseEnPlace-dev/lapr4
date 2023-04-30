package eapli.ecourse.exammanagement.domain;

/**
 * Enum that represents the state of an exam.
 */
public enum ExamState {
  DRAFT, PUBLISHED;

  /**
   * Get the enum type of a given type.
   *
   * @param type the type of the exam
   * @return the equivalent enum type
   */
  public static ExamState getType(String type) {
    if (type.equals("Draft"))
      return ExamState.DRAFT;
    if (type.equals("Published"))
      return ExamState.PUBLISHED;

    return null;
  }
}
