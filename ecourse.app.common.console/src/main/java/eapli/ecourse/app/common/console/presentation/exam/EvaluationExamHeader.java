package eapli.ecourse.app.common.console.presentation.exam;

public class EvaluationExamHeader {
  public String header() {
    return String.format("+= Evaluation Exams =======================================================+\n"
        + "#   %-22s%-8s%-10s%-18s%-18s%-18s%-35s%-10s", "Title", "Course", "Teacher", "StartTime",
      "EndTime", "Identifier", "Description", "State");
  }

  public void printHeader() {
    System.out.println(header());
  }
}
