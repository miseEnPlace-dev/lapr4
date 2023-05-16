package eapli.ecourse.exammanagement.domain.dto;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.exammanagement.domain.Description;
import eapli.ecourse.exammanagement.domain.ExamCode;
import eapli.ecourse.exammanagement.domain.ExamState;
import eapli.ecourse.exammanagement.domain.Title;
import eapli.ecourse.questionmanagement.domain.Identifier;
import eapli.ecourse.teachermanagement.domain.Teacher;
import eapli.framework.representations.dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@DTO
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExamDTO {
  private ExamCode code;
  private Course course;
  private Teacher teacher;
  private Identifier identifier;
  private Title title;
  private Description description;
  private ExamState state;
}
