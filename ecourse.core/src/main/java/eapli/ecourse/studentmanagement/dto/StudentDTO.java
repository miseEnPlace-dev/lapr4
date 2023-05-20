package eapli.ecourse.studentmanagement.dto;

import eapli.ecourse.studentmanagement.domain.MecanographicNumber;
import eapli.framework.infrastructure.authz.domain.model.Name;
import eapli.framework.infrastructure.authz.domain.model.Username;
import eapli.framework.representations.dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@DTO
@Data
@AllArgsConstructor
@NoArgsConstructor
public class StudentDTO {
  private MecanographicNumber mecanographicNumber;
  private Username username;
  private Name name;
}
