package eapli.ecourse.persistence.impl.jpa;

import java.util.HashMap;
import java.util.Map;

import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.ClassID;
import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.CourseClass;
import eapli.ecourse.eventsmanagement.courseclassmanagement.repositories.CourseClassRepository;
import eapli.ecourse.teachermanagement.domain.TaxPayerNumber;
import eapli.framework.domain.repositories.TransactionalContext;
import eapli.framework.infrastructure.repositories.impl.jpa.JpaAutoTxRepository;

public class JpaClassRepository extends JpaAutoTxRepository<CourseClass, ClassID, ClassID>
    implements CourseClassRepository {

  JpaClassRepository(final TransactionalContext autoTx) {
    super(autoTx, "id");
  }

  JpaClassRepository(final String puName) {
    super(puName, "id");
  }

  @Override
  public Iterable<CourseClass> findAllByCourseCode(CourseCode code) {
    final Map<String, Object> params = new HashMap<>();
    params.put("code", code);
    return match("e.course.code=:code", params);
  }

  @Override
  public Iterable<CourseClass> findAllScheduledByTeacherTaxPayerNumber(TaxPayerNumber number) {
    final Map<String, Object> params = new HashMap<>();
    params.put("number", number);
    return match("e.scheduledBy.taxPayerNumber=:number", params);
  }
}
