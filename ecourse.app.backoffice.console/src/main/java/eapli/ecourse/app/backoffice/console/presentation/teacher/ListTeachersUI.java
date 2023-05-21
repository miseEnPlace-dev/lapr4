package eapli.ecourse.app.backoffice.console.presentation.teacher;

import eapli.ecourse.app.common.console.presentation.teacher.TeacherPrinter;
import eapli.ecourse.teachermanagement.application.ListTeachersController;
import eapli.ecourse.teachermanagement.dto.TeacherDTO;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

public class ListTeachersUI extends AbstractListUI<TeacherDTO> {
  private final ListTeachersController controller = new ListTeachersController();

  @Override
  public String headline() {
    return "List Teachers";
  }

  @Override
  protected String emptyMessage() {
    return "No data.";
  }

  @Override
  protected Iterable<TeacherDTO> elements() {
    return controller.allTeachers();
  }

  @Override
  protected Visitor<TeacherDTO> elementPrinter() {
    return new TeacherPrinter();
  }

  @Override
  protected String elementName() {
    return "Teacher";
  }

  @Override
  protected String listHeader() {
    return String.format("#  %-20s%-20s%-30s%-20s", "Tax Payer No.", "Username", "Acronym", "Birth Date");
  }
}
