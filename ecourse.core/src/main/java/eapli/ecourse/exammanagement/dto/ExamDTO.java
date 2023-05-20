package eapli.ecourse.exammanagement.dto;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.exammanagement.domain.ExamCode;
import eapli.ecourse.exammanagement.domain.ExamDescription;
import eapli.ecourse.exammanagement.domain.ExamIdentifier;
import eapli.ecourse.exammanagement.domain.ExamState;
import eapli.ecourse.exammanagement.domain.ExamTitle;
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
  private ExamIdentifier identifier;
  private ExamTitle title;
  private ExamDescription description;
  private ExamState state;
}
