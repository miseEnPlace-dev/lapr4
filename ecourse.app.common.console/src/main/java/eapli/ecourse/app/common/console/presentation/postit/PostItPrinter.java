package eapli.ecourse.app.common.console.presentation.postit;

import java.text.SimpleDateFormat;
import eapli.ecourse.postitmanagement.dto.PostItDTO;
import eapli.framework.visitor.Visitor;

public class PostItPrinter implements Visitor<PostItDTO> {
  private String format = "%-38s%-15s%-15s%-15s%-8s%-9s%-9s%-18s";

  public String header() {
    return String.format("#  " + format, "ID", "Title", "Board", "Owner", "State", "Pos.",
        "Latest?", "Created At");
  }

  public void printHeader() {
    System.out.print(header());
  }

  @Override
  public void visit(final PostItDTO visitee) {
    SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm");

    System.out.printf(format, visitee.getId(), visitee.getTitle(), visitee.getBoard().getTitle(),
        visitee.getOwner().getUsername(), visitee.getState(), visitee.getCoordinates(),
        visitee.isLatest() ? "Yes" : "No", formatter.format(visitee.getCreatedAt().getTime()));
  }
}
