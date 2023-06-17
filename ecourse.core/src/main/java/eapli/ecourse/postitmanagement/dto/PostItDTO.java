package eapli.ecourse.postitmanagement.dto;

import java.io.Serializable;
import java.util.Calendar;
import org.apache.commons.lang3.builder.HashCodeBuilder;
import eapli.ecourse.boardmanagement.dto.BoardDTO;
import eapli.ecourse.postitmanagement.domain.Coordinates;
import eapli.ecourse.postitmanagement.domain.PostItDescription;
import eapli.ecourse.postitmanagement.domain.PostItID;
import eapli.ecourse.postitmanagement.domain.PostItImage;
import eapli.ecourse.postitmanagement.domain.PostItState;
import eapli.ecourse.postitmanagement.domain.PostItTitle;
import eapli.ecourse.usermanagement.dto.UserDTO;
import eapli.framework.representations.dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@DTO
@Data
@AllArgsConstructor
@NoArgsConstructor
public class PostItDTO implements Serializable {
  private static final long serialVersionUID = 1L;

  private PostItID id;
  private PostItTitle title;
  private Coordinates coordinates;
  private PostItState state;
  private BoardDTO board;
  private UserDTO owner;
  private PostItDTO previous;
  private Calendar createdAt;
  private boolean isLatest;
  private PostItDescription description;
  private PostItImage image;

  @Override
  public int hashCode() {
    HashCodeBuilder hashBuilder = new HashCodeBuilder().append(id).append(title).append(coordinates)
        .appendSuper(state.getHashCode()).append(owner).append(createdAt).append(isLatest)
        .append(description).append(image);

    if (previous != null)
      hashBuilder.appendSuper(previous.hashCode());

    return hashBuilder.toHashCode();
  }

  @Override
  public boolean equals(Object obj) {
    if (obj == null)
      return false;

    if (this == obj)
      return true;

    if (!(obj instanceof PostItDTO))
      return false;

    PostItDTO other = (PostItDTO) obj;
    return other.hashCode() == this.hashCode();
  }
}
