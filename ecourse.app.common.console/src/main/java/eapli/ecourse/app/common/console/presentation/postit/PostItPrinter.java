package eapli.ecourse.app.common.console.presentation.postit;

import java.text.SimpleDateFormat;

import eapli.ecourse.app.common.console.util.TableFormatPrinter;
import eapli.ecourse.postitmanagement.dto.PostItDTO;
import eapli.framework.visitor.Visitor;

public class PostItPrinter implements Visitor<PostItDTO> {
  public String header() {
    return String.format("#  %-15s%-15s%-8s%-9s%-9s%-11s%-18s%-15s%-7s", "Title",
        "Owner", "State", "Pos.",
        "Latest?", "Has Prev?", "Created At", "Description", "Image");
  }

  public void printHeader() {
    System.out.print(header());
  }

  @Override
  public void visit(final PostItDTO visitee) {
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    TableFormatPrinter printer = new TableFormatPrinter();

    printer.addColumn(visitee.getTitle().toString(), 15);
    printer.addColumn(visitee.getOwner().getUsername().toString(), 15);
    printer.addColumn(visitee.getState().toString(), 8);
    printer.addColumn(visitee.getCoordinates().toString(), 9);
    printer.addColumn(visitee.isLatest() ? "Yes" : "No", 9);
    printer.addColumn(visitee.getPrevious() == null ? "No" : "Yes", 11);
    printer.addColumn(formatter.format(visitee.getCreatedAt().getTime()), 18);
    printer.addColumn(visitee.getDescription() == null ? "N/a" : visitee.getDescription().toString(), 15);
    printer.addColumn(visitee.getImage() == null ? "No" : "Yes", 7);

    System.out.print(printer.format());
  }
}
