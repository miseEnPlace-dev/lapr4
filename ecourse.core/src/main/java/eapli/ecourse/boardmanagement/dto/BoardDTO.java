package eapli.ecourse.boardmanagement.dto;

import java.io.Serializable;
import java.util.List;
import org.apache.commons.lang3.builder.HashCodeBuilder;
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
public class BoardDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private BoardID id;
  private BoardTitle title;
  private Archived archived;
  private SystemUser owner;
  private List<UserPermission> permissions;
  private List<BoardRow> rows;
  private List<BoardColumn> columns;

  @Override
  public int hashCode() {
    HashCodeBuilder hashBuilder =
        new HashCodeBuilder().append(id).append(title).append(archived).append(owner);

    permissions.forEach(p -> hashBuilder.appendSuper(p.getHash()));
    rows.forEach(r -> hashBuilder.appendSuper(r.getHash()));
    columns.forEach(c -> hashBuilder.appendSuper(c.getHash()));

    return hashBuilder.toHashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null)
      return false;

    if (this == obj)
      return true;

    if (!(obj instanceof BoardDTO))
      return false;

    BoardDTO other = (BoardDTO) obj;
    return other.hashCode() == this.hashCode();
  }
}
