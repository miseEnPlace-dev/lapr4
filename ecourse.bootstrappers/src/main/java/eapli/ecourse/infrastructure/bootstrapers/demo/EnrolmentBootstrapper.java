package eapli.ecourse.infrastructure.bootstrapers.demo;

import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.enrolmentmanagement.application.RequestEnrolmentController;
import eapli.ecourse.enrolmentmanagement.application.RespondCourseApplicationController;
import eapli.ecourse.enrolmentmanagement.dto.EnrolmentDTO;
import eapli.ecourse.infrastructure.bootstrapers.UsersBootstrapperBase;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;

public class EnrolmentBootstrapper extends UsersBootstrapperBase implements Action {

  private final RespondCourseApplicationController respondCtrl = new RespondCourseApplicationController(
      PersistenceContext.repositories().courses(), PersistenceContext.repositories().enrollments(),
      AuthzRegistry.authorizationService());

  private final RequestEnrolmentController requestCtrl = new RequestEnrolmentController(
      PersistenceContext.repositories().courses(), PersistenceContext.repositories().enrollments(),
      PersistenceContext.repositories().students(),
      AuthzRegistry.authorizationService());

  @Override
  public boolean execute() {
    CourseDTO course = PersistenceContext.repositories().courses()
        .ofIdentity(CourseCode.valueOf("2222")).get().toDto();

    Username username = Username.valueOf("user1");
    SystemUser user = AuthzRegistry.userService().userOfIdentity(username).orElseThrow(IllegalStateException::new);

    requestCtrl.requestEnrolment(course, user);

    EnrolmentDTO enrolmentDTO = respondCtrl.listPendingCourseApplications(course).iterator().next();
    respondCtrl.accept(enrolmentDTO);

    CourseDTO lprog = PersistenceContext.repositories().courses()
        .ofIdentity(CourseCode.valueOf("4444")).get().toDto();
    CourseDTO bddad = PersistenceContext.repositories().courses()
        .ofIdentity(CourseCode.valueOf("8787")).get().toDto();

    CourseDTO scomp = PersistenceContext.repositories().courses()
        .ofIdentity(CourseCode.valueOf("5555")).get().toDto();

    Username russo = Username.valueOf("tomas.russo");
    SystemUser userRusso = AuthzRegistry.userService().userOfIdentity(russo).orElseThrow(IllegalStateException::new);

    Username barros = Username.valueOf("andre.barros");
    SystemUser userBarros = AuthzRegistry.userService().userOfIdentity(barros).orElseThrow(IllegalStateException::new);

    Username user1 = Username.valueOf("user1");
    SystemUser userUser1 = AuthzRegistry.userService().userOfIdentity(user1).orElseThrow(IllegalStateException::new);

    requestCtrl.requestEnrolment(lprog, userRusso);
    requestCtrl.requestEnrolment(bddad, userRusso);
    requestCtrl.requestEnrolment(scomp, userUser1);

    CourseDTO lapr4 = PersistenceContext.repositories().courses()
        .ofIdentity(CourseCode.valueOf("1234")).get().toDto();
    requestCtrl.requestEnrolment(lapr4, userBarros);

    EnrolmentDTO lprogEnr = respondCtrl.listPendingCourseApplications(lprog).iterator().next();
    respondCtrl.accept(lprogEnr);

    EnrolmentDTO bddadEnr = respondCtrl.listPendingCourseApplications(bddad).iterator().next();
    respondCtrl.accept(bddadEnr);

    EnrolmentDTO scompEnr = respondCtrl.listPendingCourseApplications(scomp).iterator().next();
    respondCtrl.accept(scompEnr);

    return true;
  }

}
