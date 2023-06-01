package eapli.ecourse.boardmanagement.dto;

import java.util.List;
import eapli.ecourse.boardmanagement.domain.Archived;
import eapli.ecourse.boardmanagement.domain.BoardColumn;
import eapli.ecourse.boardmanagement.domain.BoardID;
import eapli.ecourse.boardmanagement.domain.BoardRow;
import eapli.ecourse.boardmanagement.domain.BoardTitle;
import eapli.ecourse.boardmanagement.domain.UserPermission;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.representations.dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@DTO
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardDTO {
  private BoardID id;
  private BoardTitle title;
  private Archived archived;
  private SystemUser owner;
  private List<UserPermission> permissions;
  private List<BoardRow> rows;
  private List<BoardColumn> columns;
}
