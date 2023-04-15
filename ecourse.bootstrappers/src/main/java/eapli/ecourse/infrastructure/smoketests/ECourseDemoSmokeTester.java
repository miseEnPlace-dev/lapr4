package eapli.ecourse.infrastructure.smoketests;

import eapli.ecourse.infrastructure.smoketests.backoffice.ConnectionPoolingSmokeTester;
import eapli.framework.actions.Action;
import eapli.framework.actions.ChainedAction;

/**
 * Execute simple smoke tests on the application layer. We are assuming that the bootstrappers
 * mainly test the "register" use cases and some of the "finders" to support those "register", so
 * these smoke tests will focus mainly on executing the other application methods.
 * <p>
 * "smoke testers" make a walkthru of the main use cases of the system so that the system is "smoke
 * tested" in an end-to-end manner.
 *
 * @author Paulo Gandra de Sousa
 */
@SuppressWarnings({"squid:S1126", "java:S106"})
public class ECafeteriaDemoSmokeTester implements Action {

  @Override
  public boolean execute() {
    System.out.println("\n\n------- SPECIFIC FEATURES -------");

    return ChainedAction.first(new ConnectionPoolingSmokeTester()).execute();
    // .then(...)
  }
}
