package isep.ui;

import eapli.framework.actions.Action;

public class KnowTheDevelopmentTeamAction implements Action {
  @Override
  public boolean execute() {
    return new DevTeamUI().show();
  }
}
