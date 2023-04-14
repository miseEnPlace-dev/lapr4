package isep;

import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.PlainTextEncoder;
import isep.infrastructure.persistence.PersistenceContext;
import isep.model.user.BasePasswordPolicy;
import isep.ui.MainUI;

public class MainUserApp {
  public static void main(String[] args) {
    AuthzRegistry.configure(PersistenceContext.repositories().users(),
        new BasePasswordPolicy(), new PlainTextEncoder());

    new MainUI().show();

    System.exit(0);
  }
}
