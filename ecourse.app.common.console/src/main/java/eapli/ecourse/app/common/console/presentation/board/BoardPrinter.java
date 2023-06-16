package eapli.ecourse.app.common.console.presentation.board;

import eapli.ecourse.app.common.console.util.TableFormatPrinter;
import eapli.ecourse.boardmanagement.dto.BoardDTO;
import eapli.framework.visitor.Visitor;

public class BoardPrinter implements Visitor<BoardDTO> {
  private String format = "%-38s%-10s%-17s%-12s%-6s%-9s%-17s";

  public String header() {
    return String.format("#  " + format, "ID", "Title", "Archived?", "Owner", "Rows", "Columns",
        "Num. Permissions");
  }

  public void printHeader() {
    System.out.print(header());
  }

  @Override
  public void visit(final BoardDTO visitee) {
    TableFormatPrinter printer = new TableFormatPrinter();

    printer.addColumn(visitee.getId().toString(), 38);
    printer.addColumn(visitee.getTitle().toString(), 10);
    printer.addColumn(visitee.getArchived() == null ? "No" : "Yes", 17);
    printer.addColumn(visitee.getOwner().username().toString(), 12);
    printer.addColumn(String.valueOf(visitee.getRows().size()), 6);
    printer.addColumn(String.valueOf(visitee.getColumns().size()), 9);
    printer.addColumn(String.valueOf(visitee.getPermissions().size()), 17);

    System.out.print(printer.format());
  }
}
