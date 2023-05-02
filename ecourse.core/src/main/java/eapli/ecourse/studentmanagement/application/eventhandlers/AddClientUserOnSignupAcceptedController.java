package eapli.ecourse.studentmanagement.application.eventhandlers;

import java.util.Optional;

import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.studentmanagement.domain.Student;
import eapli.ecourse.studentmanagement.domain.StudentBuilder;
import eapli.ecourse.studentmanagement.domain.events.NewUserRegisteredFromSignupEvent;
import eapli.ecourse.studentmanagement.repositories.StudentRepository;
import eapli.framework.functional.Functions;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.repositories.UserRepository;

/**
 *
 * @author Paulo Gandra de Sousa
 */
/* package */ class AddClientUserOnSignupAcceptedController {

  private final UserRepository repo = PersistenceContext.repositories().users();
  private final StudentRepository clientUserRepository = PersistenceContext.repositories().students();

  public Student addStudent(final NewUserRegisteredFromSignupEvent event) {
    final Optional<SystemUser> newUser = findStudent(event);
    return newUser.map(u -> createStudent(event, u)).orElseThrow(IllegalStateException::new);
  }

  private Student createStudent(final NewUserRegisteredFromSignupEvent event, SystemUser u) {
    final var student = new StudentBuilder()
        .withMecanographicNumber(event.mecanographicNumber()).withSystemUser(u).build();
    return clientUserRepository.save(student);
  }

  @SuppressWarnings("squid:S1488")
  private Optional<SystemUser> findStudent(final NewUserRegisteredFromSignupEvent event) {
    // since we are using events, the actual user may not yet be
    // created, so lets give it a time and wait
    final Optional<SystemUser> newUser = Functions.retry(() -> repo.ofIdentity(event.username()), 500, 30);
    return newUser;
  }
}
