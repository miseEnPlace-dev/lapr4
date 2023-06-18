package eapli.ecourse.app.common.console.presentation.meeting;

import eapli.ecourse.app.common.console.util.TableFormatPrinter;
import eapli.ecourse.eventsmanagement.meetingmanagement.dto.MeetingDTO;
import eapli.framework.visitor.Visitor;

public class MeetingPrinter implements Visitor<MeetingDTO> {
  public String header() {
    return String.format("#  %-20s%-16s%-14s%-22s", "Time", "Duration (m)", "Scheduled By", "Canceled At");
  }

  public void printHeader() {
    System.out.println(header());
  }

  @Override
  public void visit(MeetingDTO visitee) {
    TableFormatPrinter printer = new TableFormatPrinter();

    printer.addColumn(visitee.getTime().toString(), 20);
    printer.addColumn(visitee.getDuration().toString(), 16);
    printer.addColumn(visitee.getScheduledBy().username().toString(), 14);
    printer.addColumn(visitee.getCanceledAt() == null ? "N/a" : visitee.getCanceledAt().toString(), 22);

    System.out.print(printer.format());
  }
}
