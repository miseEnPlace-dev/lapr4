package eapli.ecourse.teachermanagement.application;

import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.teachermanagement.dto.TeacherDTO;
import eapli.ecourse.teachermanagement.repositories.TeacherRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

public class ListTeachersController {
  private final AuthorizationService authz = AuthzRegistry.authorizationService();

  private final TeacherRepository repo = PersistenceContext.repositories().teachers();
  private final TeacherService teacherService = new TeacherService(repo);

  public Iterable<TeacherDTO> allTeachers() {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.MANAGER);

    return this.teacherService.allTeachers();
  }
}
