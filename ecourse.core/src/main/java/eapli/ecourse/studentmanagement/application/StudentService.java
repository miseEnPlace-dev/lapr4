package eapli.ecourse.studentmanagement.application;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.studentmanagement.domain.MecanographicNumber;
import eapli.ecourse.studentmanagement.domain.Student;
import eapli.ecourse.studentmanagement.dto.StudentDTO;
import eapli.ecourse.studentmanagement.repositories.StudentRepository;
import eapli.ecourse.usermanagement.domain.ClientRoles;
import eapli.framework.application.ApplicationService;
import eapli.framework.infrastructure.authz.application.AuthorizationService;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;
import eapli.framework.infrastructure.authz.domain.model.Username;

/**
 * @author mcn
 */
@ApplicationService
public class StudentService {
  private final AuthorizationService authz = AuthzRegistry.authorizationService();
  private final StudentRepository repo = PersistenceContext.repositories().students();

  public Optional<Student> findStudentUserByMecNumber(final String mecNumber) {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.MANAGER);
    return repo.ofIdentity(MecanographicNumber.valueOf(mecNumber));
  }

  public Optional<Student> findStudentUserByUsername(final Username user) {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.MANAGER);
    return repo.findByUsername(user);
  }

  public Iterable<StudentDTO> findAll() {
    final Iterable<Student> students = repo.findAll();
    return convertToDto(students);

  }

  private Iterable<StudentDTO> convertToDto(Iterable<Student> students) {
    return StreamSupport.stream(students.spliterator(), true)
        .map(Student::toDto)
        .collect(Collectors.toUnmodifiableList());

  }
}
