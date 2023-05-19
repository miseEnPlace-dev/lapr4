package eapli.ecourse.eventsmanagement.courseclassmanagement.repositories;

import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.ClassID;
import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.CourseClass;
import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.framework.domain.repositories.DomainRepository;

public interface CourseClassRepository extends DomainRepository<ClassID, CourseClass> {
  // Iterable<CourseClass> findAllActive();

  Iterable<CourseClass> findAllByCourseCode(CourseCode code);

  Iterable<CourseClass> findAllByTeacher(Teacher teacher);

}
