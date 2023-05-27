package eapli.ecourse.eventsmanagement.meetingmanagement.application;

import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import eapli.ecourse.eventsmanagement.meetingmanagement.domain.Invite;
import eapli.ecourse.eventsmanagement.meetingmanagement.domain.Meeting;
import eapli.ecourse.eventsmanagement.meetingmanagement.dto.InviteDTO;
import eapli.ecourse.eventsmanagement.meetingmanagement.dto.MeetingDTO;
import eapli.ecourse.eventsmanagement.meetingmanagement.repositories.InviteRepository;
import eapli.ecourse.eventsmanagement.meetingmanagement.repositories.MeetingRepository;
import eapli.framework.infrastructure.authz.domain.model.SystemUser;
import eapli.framework.infrastructure.authz.domain.model.Username;

public class MeetingService {
  private MeetingRepository meetingRepository;
  private InviteRepository inviteRepository;

  public MeetingService(MeetingRepository meetingRepository, InviteRepository inviteRepository) {
    this.meetingRepository = meetingRepository;
    this.inviteRepository = inviteRepository;
  }

  public Iterable<InviteDTO> getInvites(Username username) {
    final Iterable<Invite> types = inviteRepository.findAllPendingForUsername(username);

    return convertToDtoInvites(types);
  }

  public Iterable<MeetingDTO> allTeachers() {
    final Iterable<Meeting> types = meetingRepository.findAll();
    return convertToDtoMeetings(types);
  }

  public Iterable<MeetingDTO> meetingsScheduledBy(SystemUser user) {
    final Iterable<Meeting> types = meetingRepository.findMeetingsByOwner(user);

    return convertToDtoMeetings(types);
  }

  private Iterable<MeetingDTO> convertToDtoMeetings(Iterable<Meeting> meetings) {
    return StreamSupport.stream(meetings.spliterator(), true)
        .map(Meeting::toDto)
        .collect(Collectors.toUnmodifiableList());
  }

  private Iterable<InviteDTO> convertToDtoInvites(Iterable<Invite> invites) {
    return StreamSupport.stream(invites.spliterator(), true)
        .map(Invite::toDto)
        .collect(Collectors.toUnmodifiableSet());
  }
}
