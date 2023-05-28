package eapli.ecourse.eventsmanagement.meetingmanagement.dto;

import eapli.ecourse.eventsmanagement.domain.Time;
import eapli.ecourse.eventsmanagement.meetingmanagement.domain.InviteID;
import eapli.ecourse.eventsmanagement.meetingmanagement.domain.InviteStatus;
import eapli.ecourse.eventsmanagement.meetingmanagement.domain.Meeting;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.representations.dto.DTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@DTO
@Data
@AllArgsConstructor
@NoArgsConstructor
public class InviteDTO {
  private InviteID id;
  private Meeting meeting;
  private InviteStatus status;
  private Time time;
  private SystemUser user;
}
