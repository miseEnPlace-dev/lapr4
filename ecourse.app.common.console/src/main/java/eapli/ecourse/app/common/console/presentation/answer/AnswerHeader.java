package eapli.ecourse.app.common.console.presentation.answer;

public class AnswerHeader {
  public String header() {
    return String.format("%-12s%-20s%-20s%-20s%-6s%-16s", "Student No.", "Name", "Exam", "Class", "Score", "Date");
  }

  public void printHeader() {
    System.out.println(header());
  }
}
