package eapli.ecourse.app.common.console.presentation.board;

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
    System.out.printf(format, visitee.getId(), visitee.getTitle(),
        visitee.getArchived() == null ? "No" : visitee.getArchived(), visitee.getOwner().username(),
        visitee.getRows().size(), visitee.getColumns().size(), visitee.getPermissions().size());
  }
}
