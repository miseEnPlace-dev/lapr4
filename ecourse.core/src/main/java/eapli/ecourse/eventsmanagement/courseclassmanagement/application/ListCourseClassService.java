package eapli.ecourse.eventsmanagement.courseclassmanagement.application;

import java.util.stream.StreamSupport;

import eapli.ecourse.eventsmanagement.courseclassmanagement.domain.CourseClass;
import eapli.ecourse.eventsmanagement.courseclassmanagement.dto.ClassDTO;
import eapli.ecourse.eventsmanagement.courseclassmanagement.repositories.CourseClassRepository;
import eapli.ecourse.teachermanagement.domain.TaxPayerNumber;

public class ListCourseClassService {
  private CourseClassRepository classRepository;

  public ListCourseClassService(CourseClassRepository classRepository) {
    this.classRepository = classRepository;
  }

  public Iterable<ClassDTO> findAllByTeacherTaxPayerNumber(final TaxPayerNumber teacherTaxPayerNumber) {
    final Iterable<CourseClass> classes = classRepository.findAllByTeacherTaxPayerNumber(teacherTaxPayerNumber);
    return toDto(classes);
  }

  private Iterable<ClassDTO> toDto(Iterable<CourseClass> classes) {
    return StreamSupport.stream(classes.spliterator(), true)
        .map(CourseClass::toDto)
        .collect(java.util.stream.Collectors.toUnmodifiableList());
  }
}
