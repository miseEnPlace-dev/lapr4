package eapli.ecourse.exammanagement.application.exceptions;

public class ParseException extends Exception {
  private int line;
  private String message;

  /**
   * Initializes a new ParseException.
   *
   * @param line the line number on the file where the error occurred
   * @param msg  a descriptive message of the error
   */
  public ParseException(int line, String msg) {
    this.line = line;
    this.message = msg;
  }

  @Override
  public String getMessage() {
    StringBuilder sb = new StringBuilder();
    sb.append("Parse error in line ");
    sb.append(line);
    sb.append(": ");
    sb.append(message);
    return sb.toString();
  }
}
