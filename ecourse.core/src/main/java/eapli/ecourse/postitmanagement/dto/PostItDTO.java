package eapli.ecourse.postitmanagement.dto;

import java.io.Serializable;
import eapli.ecourse.boardmanagement.dto.BoardDTO;
import eapli.ecourse.postitmanagement.domain.Coordinates;
import eapli.ecourse.postitmanagement.domain.PostItID;
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
}
