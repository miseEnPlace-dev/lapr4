package eapli.ecourse.exammanagement.application.exceptions;

public class ParseException extends RuntimeException {
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

  /**
   * Initializes a new ParseException, without exception info.
   */
  public ParseException() {
    this.line = -1;
    this.message = "Error(s) detected in file.";
  }

  @Override
  public String getMessage() {
    StringBuilder sb = new StringBuilder();

    if (line == -1) {
      sb.append("Error(s) detected: please fix all errors listed above.");
    } else {
      sb.append("Error detected in line ");
      sb.append(line);
      sb.append(": ");
      sb.append(message);
    }

    return sb.toString();
  }
}
