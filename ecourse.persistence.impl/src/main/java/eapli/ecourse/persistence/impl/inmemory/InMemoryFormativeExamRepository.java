package eapli.ecourse.persistence.impl.inmemory;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.exammanagement.domain.ExamIdentifier;
import eapli.ecourse.exammanagement.domain.formative.FormativeExam;
import eapli.ecourse.exammanagement.repositories.FormativeExamRepository;
import eapli.ecourse.studentmanagement.domain.MecanographicNumber;
import eapli.framework.infrastructure.repositories.impl.inmemory.InMemoryDomainRepository;

public class InMemoryFormativeExamRepository extends
    InMemoryDomainRepository<FormativeExam, ExamIdentifier> implements FormativeExamRepository {

  static {
    InMemoryInitializer.init();
  }

  @Override
  public Iterable<FormativeExam> findAllCourseExams(Course course) {
    return match(e -> e.course().equals(course));
  }

  @Override
  public Iterable<FormativeExam> findAllCourseExamsWithNoAnswersFromStudent(CourseCode code,
      MecanographicNumber number) {
    throw new UnsupportedOperationException();
  }

  @Override
  public FormativeExam findByIdentifier(ExamIdentifier examIdentifier) {
    throw new UnsupportedOperationException();
  }
}
