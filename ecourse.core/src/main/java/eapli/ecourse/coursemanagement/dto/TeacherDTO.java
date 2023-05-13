package eapli.ecourse.coursemanagement.dto;

import eapli.ecourse.studentmanagement.domain.TaxPayerNumber;
import eapli.framework.representations.dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * TODO: incomplete
 */
@DTO
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO {
  private TaxPayerNumber number;
}
