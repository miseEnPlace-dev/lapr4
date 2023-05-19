package eapli.ecourse.app.common.console.presentation.enrolment;

public class EnrolmentHeader {

  public String header() {
    return String.format("%-10s%-20s%-30s%-5s%-5s%-8s%-16s%-20s", "Course Code", "Student Number", "Student Name",
        "Created At", "State");
  }

  public void printHeader() {
    System.out.println(header());
  }

}
