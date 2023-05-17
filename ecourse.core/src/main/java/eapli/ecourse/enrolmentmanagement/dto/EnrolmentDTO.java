package eapli.ecourse.enrolmentmanagement.dto;

import java.util.Calendar;

import eapli.framework.representations.dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@DTO
@Data
@AllArgsConstructor
@NoArgsConstructor
public class EnrolmentDTO {
  private String courseCode;
  private String studentNumber;
  private String studentName;
  private Calendar createdAt;
  private String state;
}
