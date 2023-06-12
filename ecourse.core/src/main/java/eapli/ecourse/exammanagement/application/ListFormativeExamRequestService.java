package eapli.ecourse.exammanagement.application;

import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.coursemanagement.repositories.CourseRepository;
import eapli.ecourse.exammanagement.domain.formative.FormativeExamRequest;
import eapli.ecourse.exammanagement.dto.FormativeExamRequestDTO;
import eapli.ecourse.exammanagement.repositories.FormativeExamRequestRepository;

public class ListFormativeExamRequestService {
  private CourseRepository courseRepository;
  private FormativeExamRequestRepository formativeExamRequestRepository;

  public ListFormativeExamRequestService(CourseRepository courseRepository,
      FormativeExamRequestRepository fRepository) {
    this.courseRepository = courseRepository;
    this.formativeExamRequestRepository = fRepository;
  }

  public Iterable<FormativeExamRequestDTO> findAllRequestByCourse(CourseCode code) {
    Optional<Course> course = courseRepository.findByCode(code);

    Iterable<FormativeExamRequest> list = formativeExamRequestRepository.findAllFormativeRequestByCourse(course);

    return convertToDto(list);
  }

  private Iterable<FormativeExamRequestDTO> convertToDto(Iterable<FormativeExamRequest> request) {
    return StreamSupport.stream(request.spliterator(), true)
        .map(FormativeExamRequest::toDto)
        .collect(Collectors.toUnmodifiableSet());
  }
}
