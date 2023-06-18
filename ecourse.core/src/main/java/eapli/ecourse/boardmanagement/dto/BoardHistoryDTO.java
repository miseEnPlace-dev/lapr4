package eapli.ecourse.boardmanagement.dto;

import java.io.Serializable;

import eapli.framework.representations.dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@DTO
@Data
@AllArgsConstructor
@NoArgsConstructor
public class BoardHistoryDTO implements Serializable {
  private String previousTitle;
  private String currentTitle;

  private String previousDescription;
  private String currentDescription;

  private String previousState;
  private String currentState;

  private String previousCoordinates;
  private String currentCoordinates;

  private String previousImage;
  private String currentImage;

  private String owner;
  private String date;
}
