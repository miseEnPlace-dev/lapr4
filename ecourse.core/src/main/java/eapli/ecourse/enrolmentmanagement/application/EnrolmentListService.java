package eapli.ecourse.enrolmentmanagement.application;

import java.util.stream.StreamSupport;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.enrolmentmanagement.domain.Enrolment;
import eapli.ecourse.enrolmentmanagement.dto.EnrolmentDTO;
import eapli.ecourse.enrolmentmanagement.repositories.EnrolmentRepository;

public class EnrolmentListService {

  private EnrolmentRepository enrolmentRepository;

  public EnrolmentListService(EnrolmentRepository enrolmentRepository) {
    this.enrolmentRepository = enrolmentRepository;
  }

  public Iterable<EnrolmentDTO> listCourseEnrolment(Course course) {
    final Iterable<Enrolment> enrolments = enrolmentRepository.findByCourseCode(course.code());

    return convertToDTO(enrolments);
  }

  private Iterable<EnrolmentDTO> convertToDTO(Iterable<Enrolment> enrolments) {
    return StreamSupport.stream(enrolments.spliterator(), true)
        .map(Enrolment::toDto)
        .collect(java.util.stream.Collectors.toUnmodifiableList());
  }

}
