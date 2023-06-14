package eapli.ecourse.app.common.console.presentation.exam;

public class FormativeExamHeader {

  public String header() {
    return String.format("+= Formative Exams =======================================================+\n" +
        "#   %-22s%-8s%-10s%-35s%-10s", "Title", "Course", "Teacher", "Description", "State");
  }

  public void printHeader() {
    System.out.println(header());
  }
}
