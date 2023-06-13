package eapli.ecourse.exammanagement.dto;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.exammanagement.domain.*;
import eapli.ecourse.exammanagement.domain.evaluation.ExamScore;
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
  private ExamDescription description;
  private Course course;
  private ExamScore score;
  private Teacher teacher;
  private ExamState state;
}
