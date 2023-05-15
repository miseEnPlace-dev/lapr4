package eapli.ecourse.teachermanagement.dto;

import eapli.ecourse.teachermanagement.domain.Acronym;
import eapli.ecourse.teachermanagement.domain.BirthDate;
import eapli.ecourse.teachermanagement.domain.TaxPayerNumber;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.representations.dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@DTO
@Data
@AllArgsConstructor
@NoArgsConstructor
public class TeacherDTO {
  private TaxPayerNumber number;
  private Acronym acronym;
  private BirthDate birthDate;
  private Username username;
}
