package eapli.ecourse.persistence.impl.inmemory;

import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.CourseClass;
import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.ClassID;
import eapli.ecourse.eventsmanagement.courseclassmanagement.repositories.CourseClassRepository;
import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryClassRepository extends InMemoryDomainRepository<CourseClass, ClassID>
    implements CourseClassRepository {

  static {
    InMemoryInitializer.init();
  }

  @Override
  public Iterable<CourseClass> findAllByCourseCode(CourseCode code) {
    return match(e -> e.course().code().equals(code));
  }

  @Override
  public Iterable<CourseClass> findAllByTeacher(Teacher teacher) {
    return match(e -> e.scheduledBy().equals(teacher));
  }

  // @Override
  // public Iterable<Class> findAllActive() {
  // // TODO Auto-generated method stub
  // throw new UnsupportedOperationException("Unimplemented method
  // 'findAllActive'");
  // }
}
