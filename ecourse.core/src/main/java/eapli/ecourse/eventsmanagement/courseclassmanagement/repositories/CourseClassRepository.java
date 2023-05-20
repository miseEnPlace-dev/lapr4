package eapli.ecourse.eventsmanagement.courseclassmanagement.repositories;

import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.ClassID;
import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.CourseClass;
import eapli.ecourse.teachermanagement.domain.TaxPayerNumber;
import eapli.framework.domain.repositories.DomainRepository;

public interface CourseClassRepository extends DomainRepository<ClassID, CourseClass> {
  Iterable<CourseClass> findAllByCourseCode(CourseCode code);

  Iterable<CourseClass> findAllScheduledByTeacherTaxPayerNumber(TaxPayerNumber teacher);
}
