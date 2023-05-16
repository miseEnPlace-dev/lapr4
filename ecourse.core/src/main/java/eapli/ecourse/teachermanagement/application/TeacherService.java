package eapli.ecourse.teachermanagement.application;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.ecourse.teachermanagement.dto.TeacherDTO;
import eapli.ecourse.teachermanagement.repositories.TeacherRepository;

public class TeacherService {
  private TeacherRepository teacherRepository;

  public TeacherService(TeacherRepository teacherRepository) {
    this.teacherRepository = teacherRepository;
  }

  public Iterable<TeacherDTO> allTeachers() {
    final Iterable<Teacher> types = teacherRepository.findAll();
    return convertToDto(types);
  }

  private Iterable<TeacherDTO> convertToDto(Iterable<Teacher> teachers) {
    return StreamSupport.stream(teachers.spliterator(), true)
        .map(Teacher::toDto)
        .collect(Collectors.toUnmodifiableList());

  }
}
