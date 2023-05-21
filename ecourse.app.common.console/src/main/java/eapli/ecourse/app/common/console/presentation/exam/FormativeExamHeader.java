package eapli.ecourse.app.common.console.presentation.exam;

public class FormativeExamHeader {

  public String header() {
    return String.format("#  %-7s%-20s%-30s%-5s%-5s%-16s%-16s", "Code", "Title", "Course", "Teacher",
      "Identifier", "Description", "State");
  }

  public void printHeader() {
    System.out.println(header());
  }
}
