package eapli.ecourse.persistence.impl.jpa;

import java.util.HashMap;
import java.util.Map;

import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.CourseClass;
import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.ClassID;
import eapli.ecourse.eventsmanagement.courseclassmanagement.repositories.CourseClassRepository;
import eapli.ecourse.teachermanagement.domain.Teacher;
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
    return match("e.code=:code", params);
  }

  @Override
  public Iterable<CourseClass> findAllByTeacher(Teacher teacher) {
    final Map<String, Object> params = new HashMap<>();
    params.put("teacher", teacher);
    return match("e.scheduledBy=:teacher", params);
  }
}
