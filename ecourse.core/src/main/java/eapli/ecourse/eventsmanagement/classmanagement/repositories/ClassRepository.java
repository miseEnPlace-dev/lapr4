package eapli.ecourse.eventsmanagement.classmanagement.repositories;

import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.eventsmanagement.classmanagement.domain.Class;
import eapli.ecourse.eventsmanagement.classmanagement.domain.ClassID;
import eapli.framework.domain.repositories.DomainRepository;

public interface ClassRepository extends DomainRepository<ClassID, Class> {
  // Iterable<Class> findAllActive();

  Iterable<Class> findAllByCourseCode(CourseCode code);

}
