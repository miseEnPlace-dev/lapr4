package eapli.ecourse.eventsmanagement.courseclassmanagement.application;

import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.CourseClass;
import eapli.ecourse.eventsmanagement.courseclassmanagement.repositories.CourseClassRepository;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;

public class ListClassesController {
  private final CourseClassRepository repo = PersistenceContext.repositories().classes();

  public Iterable<CourseClass> findClasses() {
    return repo.findAll();
  }
}
