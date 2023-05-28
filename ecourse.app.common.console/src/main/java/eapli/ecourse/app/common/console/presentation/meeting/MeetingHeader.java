package eapli.ecourse.app.common.console.presentation.meeting;

public class MeetingHeader {


  public String header() {
    return String.format("#  %-7s%-10s%-10s%-14s%12s", "Id", "Time", "Duration", "Scheduled By", "Canceled At");
  }

  public void printHeader() {
    System.out.println(header());
  }

}
