package eapli.ecourse.infrastructure.bootstrapers.demo;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.eventsmanagement.domain.Time;
import eapli.ecourse.exammanagement.application.CreateEvaluationExamController;
import eapli.ecourse.exammanagement.domain.evaluation.EvaluationExam;
import eapli.ecourse.exammanagement.domain.evaluation.EvaluationExamBuilder;
import eapli.ecourse.exammanagement.domain.parsers.ExamsParser;
import eapli.ecourse.exammanagement.repositories.EvaluationExamRepository;
import eapli.ecourse.infrastructure.persistence.PersistenceContext;
import eapli.ecourse.teachermanagement.domain.TaxPayerNumber;
import eapli.ecourse.teachermanagement.repositories.TeacherRepository;
import eapli.framework.actions.Action;
import eapli.framework.infrastructure.authz.application.AuthzRegistry;

import java.io.IOException;
import java.util.Calendar;

public class ExamBootstrapper implements Action {
  private Course course;

  private EvaluationExamBuilder builder;

  private TeacherRepository teacherRepository = PersistenceContext.repositories().teachers();

  private EvaluationExamRepository evaluationRepository = PersistenceContext.repositories().evaluationExams();
  @Override
  public boolean execute() {
    course = PersistenceContext.repositories().courses().ofIdentity(CourseCode.valueOf("2222")).get();
    try {
      builder = ExamsParser.parseWithVisitor("exam.txt");
    } catch (IOException e) {
      throw new RuntimeException(e);
    }

    addEvaluationExam();
    return false;
  }


  private void addEvaluationExam() {
    Calendar startTime = Calendar.getInstance();
    startTime.set(2023, Calendar.JANUARY, 23, 14, 30);

    Calendar endTime = Calendar.getInstance();
    endTime.set(2023, Calendar.JANUARY, 23, 15, 30);

    builder.withCourse(course).withTeacher(teacherRepository.ofIdentity(TaxPayerNumber.valueOf("212345678"))
      .orElseThrow()).withStartTime(Time.valueOf(startTime)).withEndTime(Time.valueOf(endTime));

    EvaluationExam exam = builder.build();

    evaluationRepository.save(exam);
  }
}
