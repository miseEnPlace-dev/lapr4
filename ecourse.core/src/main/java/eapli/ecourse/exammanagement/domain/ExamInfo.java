package eapli.ecourse.exammanagement.domain;

public enum ExamInfo {
  NONE, ON_SUBMIT, AFTER_CLOSING;

  public static ExamInfo convert(String str) {
    switch (str) {
      case "none":
        return NONE;
      case "on-submit":
        return ON_SUBMIT;
      case "after-closing":
        return AFTER_CLOSING;
      default:
        return null;
    }
  }
}
