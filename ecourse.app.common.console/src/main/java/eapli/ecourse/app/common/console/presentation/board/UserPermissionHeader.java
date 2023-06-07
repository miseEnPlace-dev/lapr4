package eapli.ecourse.app.common.console.presentation.board;

public class UserPermissionHeader {
  public String header() {
    return String.format("#  %-38s%-12s%-12s%-18s%-18s", "ID", "User", "Permission", "Created At",
        "Updated At");
  }

  public void printHeader() {
    System.out.print(header());
  }
}
