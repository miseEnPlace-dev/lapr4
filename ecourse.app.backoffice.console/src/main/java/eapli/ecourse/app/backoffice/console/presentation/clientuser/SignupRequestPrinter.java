package eapli.ecourse.app.backoffice.console.presentation.clientuser;

import eapli.ecourse.studentmanagement.domain.SignupRequest;
import eapli.framework.visitor.Visitor;

/**
 * Created by AJS on 08/04/2016.
 *
 */
@SuppressWarnings("squid:S106")
class SignupRequestPrinter implements Visitor<SignupRequest> {

  @Override
  public void visit(final SignupRequest visitee) {
    System.out.printf("%-10s%-20s%-10s%n", visitee.identity(), visitee.name(),
        visitee.mecanographicNumber());
  }
}
