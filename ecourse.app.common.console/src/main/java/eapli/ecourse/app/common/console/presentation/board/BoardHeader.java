package eapli.ecourse.app.common.console.presentation.board;

public class BoardHeader {
  public String header() {
    return String.format("#  %-38s%-10s%-17s%-12s%-6s%-9s%-17s", "ID", "Title", "Archived?",
        "Owner", "Rows", "Columns", "Num. Permissions");
  }

  public void printHeader() {
    System.out.print(header());
  }
}
