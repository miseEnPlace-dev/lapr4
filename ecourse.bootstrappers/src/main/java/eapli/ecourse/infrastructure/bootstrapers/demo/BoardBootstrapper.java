package eapli.ecourse.infrastructure.bootstrapers.demo;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import eapli.ecourse.boardmanagement.application.CreateBoardController;
import eapli.ecourse.boardmanagement.domain.PermissionType;
import eapli.ecourse.infrastructure.bootstrapers.UsersBootstrapperBase;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;

public class BoardBootstrapper extends UsersBootstrapperBase implements Action {
  private CreateBoardController ctrl = new CreateBoardController(PersistenceContext.repositories().boards(),
      AuthzRegistry.userService());

  private Map<SystemUser, PermissionType> getPermissions() {
    final Set<Role> roles = new HashSet<>();
    roles.add(ClientRoles.TEACHER);

    SystemUser u1 = PersistenceContext.repositories().users().ofIdentity(Username.valueOf("user1")).orElse(null);
    SystemUser u2 = PersistenceContext.repositories().users().ofIdentity(Username.valueOf("poweruser")).orElse(null);

    Map<SystemUser, PermissionType> map = new HashMap<>();
    map.put(u1, new PermissionType(PermissionType.Type.READ));
    map.put(u2, new PermissionType(PermissionType.Type.WRITE));

    return map;
  }

  private Map<String, Integer> getColumns() {
    Map<String, Integer> map = new HashMap<>();
    map.put("To Do", 1);
    map.put("Doing", 2);
    map.put("Done", 3);

    return map;
  }

  private Map<String, Integer> getRows() {
    Map<String, Integer> map = new HashMap<>();
    map.put("High", 1);
    map.put("Medium", 2);
    map.put("Low", 3);

    return map;
  }

  @Override
  public boolean execute() {
    final Set<Role> roles = new HashSet<>();
    roles.add(ClientRoles.TEACHER);

    SystemUser user = PersistenceContext.repositories().users().ofIdentity(Username.valueOf("user1")).orElse(null);
    ctrl.createBoard("example", getPermissions(), getColumns(), getRows(), "boardID", user);

    return true;
  }

}
