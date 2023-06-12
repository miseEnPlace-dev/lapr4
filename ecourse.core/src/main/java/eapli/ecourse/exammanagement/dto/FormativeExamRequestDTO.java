package eapli.ecourse.exammanagement.dto;

import eapli.ecourse.exammanagement.domain.*;
import eapli.framework.representations.dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@DTO
@Data
@AllArgsConstructor
@NoArgsConstructor
public class FormativeExamRequestDTO {
  private ExamIdentifier identifier;
  private ExamTitle title;
  private ExamDescription description;
}
