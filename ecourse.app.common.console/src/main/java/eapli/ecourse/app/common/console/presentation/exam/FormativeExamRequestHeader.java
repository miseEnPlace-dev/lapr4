package eapli.ecourse.app.common.console.presentation.exam;

public class FormativeExamRequestHeader {

  public String header() {
    return String.format("+= Formative Exams =======================================================+\n" +
        "#   %-22s%-8s%-10s%-10s", "Identifier", "Title", "Description");
  }

  public void printHeader() {
    System.out.println(header());
  }
}
