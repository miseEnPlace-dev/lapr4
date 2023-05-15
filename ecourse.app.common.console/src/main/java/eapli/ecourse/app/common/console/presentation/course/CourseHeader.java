package eapli.ecourse.app.common.console.presentation.course;

public class CourseHeader {
  public String header() {
    return String.format("%-10s%-20s%-30s%-5s%-5s%-8s%-16s%-20s", "Code", "Title", "Description", "Min", "Max",
        "State",
        "Enrolment State", "Created At");
  }

  public void printHeader() {
    System.out.println(header());
  }
}
