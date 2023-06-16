package eapli.ecourse.app.common.console.presentation.meeting;

import eapli.ecourse.app.common.console.util.TableFormatPrinter;
import eapli.ecourse.eventsmanagement.meetingmanagement.dto.InviteDTO;
import eapli.framework.visitor.Visitor;

@SuppressWarnings({ "squid:S106" })
public class InvitePrinter implements Visitor<InviteDTO> {
  public String header() {
    return String.format("#  %-10s%-10s%-10s%-5s", "Owner", "Status", "Time", "Day");
  }

  public void printHeader() {
    System.out.println(header());
  }

  @Override
  public void visit(final InviteDTO visitee) {
    String timeString = String.format("%02d:%02d", visitee.getTime().hour(), visitee.getTime().minute());

    TableFormatPrinter printer = new TableFormatPrinter();

    printer.addColumn(visitee.getMeeting().scheduledBy().username().toString(), 10);
    printer.addColumn(visitee.getStatus().toString(), 10);
    printer.addColumn(timeString, 10);
    printer.addColumn(visitee.getTime().dayInWeek().toString(), 5);

    System.out.print(printer.format());
  }
}
