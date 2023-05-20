package eapli.ecourse.eventsmanagement.courseclassmanagement.repositories;

import eapli.framework.domain.repositories.DomainRepository;
import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.ExtraordinaryClassID;
import eapli.ecourse.studentmanagement.domain.MecanographicNumber;
import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.ClassID;
import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.ExtraordinaryClass;

public interface ExtraordinaryClassRepository extends DomainRepository<ExtraordinaryClassID, ExtraordinaryClass> {
  Iterable<ExtraordinaryClass> findAllByStudentMecanographicNumber(MecanographicNumber mecanographicNumber);
}
