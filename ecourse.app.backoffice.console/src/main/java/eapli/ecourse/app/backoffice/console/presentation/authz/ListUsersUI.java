package eapli.ecourse.app.backoffice.console.presentation.authz;

import eapli.ecourse.app.common.console.presentation.authz.SystemUserPrinter;
import eapli.ecourse.usermanagement.application.ListUsersController;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.presentation.console.AbstractListUI;
import eapli.framework.visitor.Visitor;

/**
 *
 * @author losa
 */
@SuppressWarnings({ "squid:S106" })
public class ListUsersUI extends AbstractListUI<SystemUser> {

  @Override
  public String headline() {
    return "List Users";
  }

  @Override
  protected String emptyMessage() {
    return "No data.";
  }

  @Override
  protected Iterable<SystemUser> elements() {
    ListUsersController theController = new ListUsersController();
    return theController.allUsers();
  }

  @Override
  protected Visitor<SystemUser> elementPrinter() {
    return new SystemUserPrinter();
  }

  @Override
  protected String elementName() {
    return "User";
  }

  @Override
  protected String listHeader() {
    return String.format("#  %-20s%-30s%-30s%-15s", "Username", "F. Name", "L. Name", "Is Active");
  }
}
