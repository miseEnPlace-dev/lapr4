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

public class BoardBootstrapper extends UsersBootstrapperBase implements Action {
  private CreateBoardController ctrl = new CreateBoardController(PersistenceContext.repositories().boards(),
      AuthzRegistry.userService(), AuthzRegistry.authorizationService());

  private Map<SystemUser, PermissionType> getPermissions() {
    SystemUser u1 = registerTeacher("user3", "Password1", "firstName", "lastName", "email@ddd.com", "abc", "12345678",
        "01/01/2000");
    SystemUser u2 = registerTeacher("user4", "Password1", "firstName", "lastName", "email@ddd.com", "cbo", "12345679",
        "01/01/2000");

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
    Set<Role> roles = new HashSet<>();
    roles.add(ClientRoles.TEACHER);

    ctrl.createBoard("example", getPermissions(), getColumns(), getRows());

    return true;
  }
}
