package eapli.ecourse.boardmanagement.dto;

import java.io.Serializable;
import java.util.Calendar;
import eapli.ecourse.boardmanagement.domain.PermissionType;
import eapli.ecourse.boardmanagement.domain.UserPermissionID;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.representations.dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@DTO
@Data
@AllArgsConstructor
@NoArgsConstructor
public class UserPermissionDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private Calendar createdAt;
  private Calendar updatedAt;
  private SystemUser user;
  private PermissionType permissionType;
  private UserPermissionID id;
}
