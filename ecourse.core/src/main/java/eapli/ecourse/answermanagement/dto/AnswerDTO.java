package eapli.ecourse.answermanagement.dto;

import eapli.framework.representations.dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@DTO
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AnswerDTO {
  private String studentNumber;
  private String studentName;
  private String examTitle;
  private String examType;
  private String score;
}
