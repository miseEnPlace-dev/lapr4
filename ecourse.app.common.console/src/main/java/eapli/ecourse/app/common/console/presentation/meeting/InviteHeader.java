package eapli.ecourse.app.common.console.presentation.meeting;

public class InviteHeader {
  public String header() {
    return String.format("#  %-7s%-15s%-10s%-10s%-5s", "Owner", "Status", "Hour", "Minute", "Day");
  }

  public void printHeader() {
    System.out.println(header());
  }

}
