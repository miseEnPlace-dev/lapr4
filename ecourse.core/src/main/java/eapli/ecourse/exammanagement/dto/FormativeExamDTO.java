package eapli.ecourse.exammanagement.dto;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.exammanagement.domain.*;
import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.framework.representations.dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@DTO
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormativeExamDTO {
  private ExamIdentifier identifier;
  private ExamTitle title;
  private Course course;
  private Teacher teacher;
  private ExamDescription description;
  private ExamState state;
}
