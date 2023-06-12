package eapli.ecourse.exammanagement.dto;

import eapli.ecourse.coursemanagement.domain.Course;
import eapli.ecourse.eventsmanagement.domain.Time;
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
public class EvaluationExamDTO {
  private ExamIdentifier identifier;
  private ExamTitle title;
  private Course course;
  private Teacher teacher;
  private Time startTime;
  private Time endTime;
  private ExamDescription description;
  private ExamState state;
  private String fileContent;
}
