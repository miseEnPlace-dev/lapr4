package eapli.ecourse.enrolmentmanagement.dto;

import java.util.Calendar;

import eapli.ecourse.coursemanagement.domain.CourseCode;
import eapli.ecourse.studentmanagement.domain.MecanographicNumber;
import eapli.framework.representations.dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@DTO
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrolmentDTO {
  private CourseCode courseCode;
  private MecanographicNumber studentNumber;
  private String studentName;
  private Calendar createdAt;
  private String state;
}
