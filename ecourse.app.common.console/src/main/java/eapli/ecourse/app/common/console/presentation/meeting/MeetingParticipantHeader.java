package eapli.ecourse.app.common.console.presentation.meeting;

public class MeetingParticipantHeader {

  public String header() {
    return String.format("%-30s%-14s", "Participants", "Status");
  }

  public void printHeader() {
    System.out.println(header());
  }

}
