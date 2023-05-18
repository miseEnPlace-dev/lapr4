package eapli.ecourse.teachermanagement.application;

import java.util.HashSet;
import java.util.Set;
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
    final Iterable<Teacher> teachers = teacherRepository.findAll();
    return convertToDto(teachers);
  }

  public Iterable<TeacherDTO> allTeachersExceptFromCourse(Set<Teacher> teachersFromCourse) {
    Set<TeacherDTO> teachersExceptFromCourse = new HashSet<>();
    final Iterable<TeacherDTO> allTeachers = allTeachers();

    for (TeacherDTO teacher : allTeachers)
      for (Teacher t : teachersFromCourse)
        if (!teacher.getNumber().equals(t.taxPayerNumber()))
          teachersExceptFromCourse.add(teacher);

    return teachersExceptFromCourse;
  }

  public Iterable<Teacher> toDomain(Iterable<TeacherDTO> teachersDTO) {
    Set<Teacher> teachers = new HashSet<>();
    for (TeacherDTO teacher : teachersDTO) {
      Teacher t = teacherRepository.ofIdentity(teacher.getNumber()).orElseThrow();
      teachers.add(t);
    }
    return teachers;
  }

  private Iterable<TeacherDTO> convertToDto(Iterable<Teacher> teachers) {
    return StreamSupport.stream(teachers.spliterator(), true)
        .map(Teacher::toDto)
        .collect(Collectors.toUnmodifiableList());
  }
}
