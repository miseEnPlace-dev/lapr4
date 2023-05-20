package eapli.ecourse.enrolmentmanagement.application;

import java.util.stream.StreamSupport;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.coursemanagement.dto.CourseDTO;
import eapli.ecourse.enrolmentmanagement.domain.Enrolment;
import eapli.ecourse.enrolmentmanagement.dto.EnrolmentDTO;
import eapli.ecourse.enrolmentmanagement.repositories.EnrolmentRepository;
import eapli.ecourse.studentmanagement.domain.MecanographicNumber;

public class ListEnrolmentService {

  private EnrolmentRepository enrolmentRepository;

  public ListEnrolmentService(EnrolmentRepository enrolmentRepository) {
    this.enrolmentRepository = enrolmentRepository;
  }

  public Iterable<EnrolmentDTO> listPendingCourseApplications(CourseCode code) {
    final Iterable<Enrolment> enrollments = enrolmentRepository.findPendingByCourseCode(code);

    return convertToDTO(enrollments);
  }

  public Iterable<EnrolmentDTO> findByStudentMecanographicNumber(MecanographicNumber studentID) {
    final Iterable<Enrolment> enrollments = enrolmentRepository.findByStudentMecanographicNumber(studentID);

    return convertToDTO(enrollments);
  }

  public Iterable<CourseDTO> listStudentsCourses(MecanographicNumber studentID) {
    final Iterable<Enrolment> enrollments = enrolmentRepository.findByStudentMecanographicNumber(studentID);

    return StreamSupport.stream(enrollments.spliterator(), true)
        .map(Enrolment::course)
        .map(Course::toDto)
        .collect(java.util.stream.Collectors.toUnmodifiableList());
  }

  public Iterable<EnrolmentDTO> listStudentsEnrolled(CourseCode code) {
    final Iterable<Enrolment> enrollments = enrolmentRepository.findCourseAccepted(code);

    return convertToDTO(enrollments);
  }

  private Iterable<EnrolmentDTO> convertToDTO(Iterable<Enrolment> enrollments) {
    return StreamSupport.stream(enrollments.spliterator(), true)
        .map(Enrolment::toDto)
        .collect(java.util.stream.Collectors.toUnmodifiableList());
  }

}
