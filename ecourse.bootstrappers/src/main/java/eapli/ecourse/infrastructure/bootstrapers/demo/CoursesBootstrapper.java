package eapli.ecourse.infrastructure.bootstrapers.demo;

import java.util.Calendar;
import java.util.HashSet;
import java.util.Set;

import eapli.ecourse.coursemanagement.application.ToggleCourseEnrolmentStateController;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.infrastructure.bootstrapers.CourseBootstrapperBase;
import eapli.ecourse.infrastructure.bootstrapers.UsersBootstrapperBase;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.teachermanagement.domain.Acronym;
import eapli.ecourse.teachermanagement.domain.BirthDate;
import eapli.ecourse.teachermanagement.domain.TaxPayerNumber;
import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.ecourse.teachermanagement.dto.TeacherDTO;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Role;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;

public class CoursesBootstrapper extends UsersBootstrapperBase implements Action {
  private CourseBootstrapperBase courseBootstrapperBase = new CourseBootstrapperBase();
  private ToggleCourseEnrolmentStateController toggleCourseEnrolmentStateController = new ToggleCourseEnrolmentStateController(
      PersistenceContext.repositories().courses(), AuthzRegistry.authorizationService());

  private TeacherDTO registerTeacher(final String username, final String password, final String firstName,
      final String lastName, final String email, final String taxPayerNumber, final String acronym) {
    final Set<Role> roles = new HashSet<>();
    roles.add(ClientRoles.TEACHER);

    SystemUser u = registerUser(username, password, firstName, lastName, email, roles);

    final Teacher teacher = new Teacher(u, TaxPayerNumber.valueOf(taxPayerNumber), Acronym.valueOf(acronym),
        BirthDate.valueOf(Calendar.getInstance()));
    PersistenceContext.repositories().teachers().save(teacher);

    return teacher.toDto();
  }

  @Override
  public boolean execute() {
    TeacherDTO t = registerTeacher("teacher1", "Password1", "John", "Doe", "john@doe.com", "109123456", "JOD");
    TeacherDTO oms = registerTeacher("oms", "Password1", "Orlando", "Sousa", "oms@isep.ipp.pt", "212345678",
        "OMS");
    TeacherDTO amm = registerTeacher("amm", "Password1", "Ângelo", "Martins", "amm@isep.ipp.pt", "312345678",
        "AMM");
    CourseDTO lapr4 = courseBootstrapperBase.createOpenCourse("1234", "LAPR4", "Laboratório/Projeto IV", 1, 20, t);
    courseBootstrapperBase.createClosedCourse("4321", "ESINF", "Estruturas de Informação ", 5, 80, t);
    CourseDTO eapli = courseBootstrapperBase.createOpenCourse("2222", "EAPLI", "Engenharia de Aplicações", 10, 200, t);
    CourseDTO lprog = courseBootstrapperBase.createInProgressCourse("4444", "LPROG", "Linguagens e Programação", 1,
        190, t);
    CourseDTO scomp = courseBootstrapperBase.createInProgressCourse("5555", "SCOMP", "Sistemas de Computadores", 0, 100,
        t);
    courseBootstrapperBase.createInProgressCourse("8787", "BDDAD", "Bases de Dados", 0, 100,
        amm);
    courseBootstrapperBase.createInProgressCourse("9876", "ARQCP", "Arquitetura de Computadores", 0, 100, t);
    courseBootstrapperBase.assignTeacherToCourse(lprog, t);
    courseBootstrapperBase.assignTeacherToCourse(scomp, oms);
    courseBootstrapperBase.assignTeacherToCourse(lapr4, amm);
    courseBootstrapperBase.assignTeacherToCourse(eapli, amm);

    toggleCourseEnrolmentStateController.toggleEnrolmentState(lapr4);
    toggleCourseEnrolmentStateController.toggleEnrolmentState(eapli);
    return true;
  }

}
