package eapli.ecourse.app.common.console.presentation.meeting;

public class MeetingHeader {


  public String header() {
    return String.format("#  %-16s%-14s%-14s%14s", "Time", "Duration (m)", "Scheduled By", "Canceled At");
  }

  public void printHeader() {
    System.out.println(header());
  }

}
