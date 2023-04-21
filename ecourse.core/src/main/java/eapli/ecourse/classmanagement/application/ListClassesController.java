package eapli.ecourse.classmanagement.application;

import eapli.ecourse.classmanagement.domain.Class;
import eapli.ecourse.classmanagement.repositories.ClassRepository;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;

public class ListClassesController {
  private final ClassRepository repo = PersistenceContext.repositories().classes();

  public Iterable<Class> findClasses() {
    return repo.findAll();
  }
}
