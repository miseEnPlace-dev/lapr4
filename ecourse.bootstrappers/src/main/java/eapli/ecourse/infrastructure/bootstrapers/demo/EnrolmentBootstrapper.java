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

    CourseDTO other = PersistenceContext.repositories().courses()
        .ofIdentity(CourseCode.valueOf("1234")).get().toDto();
    requestCtrl.requestEnrolment(other, user);

    return true;
  }

}
