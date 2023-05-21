package eapli.ecourse.infrastructure.bootstrapers.demo;

import eapli.ecourse.coursemanagement.application.ToggleCourseEnrolmentStateController;
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

  private final RespondCourseApplicationController respondeCtrl = new RespondCourseApplicationController(
      PersistenceContext.repositories().courses(), PersistenceContext.repositories().enrollments(),
      AuthzRegistry.authorizationService());

  private final RequestEnrolmentController requestCtrl = new RequestEnrolmentController(
      PersistenceContext.repositories().courses(), PersistenceContext.repositories().enrollments(),
      PersistenceContext.repositories().students(),
      AuthzRegistry.authorizationService());

  private final ToggleCourseEnrolmentStateController toggleCtrl = new ToggleCourseEnrolmentStateController(
      PersistenceContext.repositories().courses(), AuthzRegistry.authorizationService());

  @Override
  public boolean execute() {
    CourseDTO closedForEnrolmentCourseDto = toggleCtrl.listNotClosedCourses().iterator().next();

    CourseDTO openForEnrolmentCourseDto = toggleCtrl.toggleEnrolmentState(closedForEnrolmentCourseDto);

    Username username = Username.valueOf("user1");
    SystemUser user = AuthzRegistry.userService().userOfIdentity(username).orElseThrow(IllegalStateException::new);

    requestCtrl.requestEnrolment(openForEnrolmentCourseDto, user);

    EnrolmentDTO enrolmentDTO = respondeCtrl.listPendingCourseApplications(openForEnrolmentCourseDto).iterator().next();

    respondeCtrl.accept(enrolmentDTO);

    return true;
  }

}
