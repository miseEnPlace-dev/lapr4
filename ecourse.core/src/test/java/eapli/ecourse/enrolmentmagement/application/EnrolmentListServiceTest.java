package eapli.ecourse.enrolmentmagement.application;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

import org.junit.Before;
import org.junit.Test;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.enrolmentmagement.EnrolmentBaseTest;
import eapli.ecourse.enrolmentmanagement.application.EnrolmentListService;
import eapli.ecourse.enrolmentmanagement.repositories.EnrolmentRepository;
import eapli.ecourse.studentmanagement.domain.MecanographicNumber;

public class EnrolmentListServiceTest extends EnrolmentBaseTest {
  private EnrolmentRepository enrolmentRepository;
  private EnrolmentListService service;

  @Before
  public void setUp() {
    enrolmentRepository = mock(EnrolmentRepository.class);
    service = new EnrolmentListService(enrolmentRepository);
  }

  @Test
  public void testListPendingCourseApplications() {
    Course c = getNewDummyCourse();
    service.listPendingCourseApplications(c.code());
    verify(enrolmentRepository, times(1)).findPendingByCourseCode(c.code());
  }

  @Test
  public void testFindByStudentMecanographicNumber() {
    String mecanographicNumber = "12345";
    service.findByStudentMecanographicNumber(MecanographicNumber.valueOf(mecanographicNumber));
    verify(enrolmentRepository, times(1))
        .findByStudentMecanographicNumber(MecanographicNumber.valueOf(mecanographicNumber));
  }
}
