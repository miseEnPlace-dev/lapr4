package eapli.ecourse.app.common.console.presentation.answer;

public class AnswerHeader {
  public String header() {
    return String.format("#  %-14s%-15s%-15s%-10s%-10s%-16s", "Student No.", "Name", "Exam", "Course", "Score", "Date");
  }

  public void printHeader() {
    System.out.println(header());
  }
}
