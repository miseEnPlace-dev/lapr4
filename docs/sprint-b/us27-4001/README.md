# User Story 4001 - Schedule a Meeting

> As User, I want to schedule a meeting.

|             |              |
| ----------- | ------------ |
| ID          | 27           |
| Sprint      | B            |
| Application | 5 - Meetings |
| Priority    | 5            |

## 1. Context

This is the first time this task is assigned to be developed. This is a new functionality that is needed to schedule meetings.

## 2. Requirements

### "As User, I want to schedule a meeting."

## 2.1. Client Specifications

- Classes and meetings are events that happen in some time and have a duration. They also have participants. However there is no concept of location related to classes and meetings. They do not take place in a specific location and "nothing" happens at the time of the event.

- All users can create and use boards as well as meetings.

## 2.2. Client Clarifications

> [**Question 17**: Can a meeting and class overlap?](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=21994)
>
> **Answer**: Regarding Meetings: FRM01 - Schedule a Meeting A user schedules a meeting. The system must check if all participants are available and send invitations to participants. In the case of Meetings they should not be created if the participants are not available.

> [**Question 23**: Can any user of the system invite any other user? For example, can a student invite another student who is in a different course, or can a manager can create a meeting with any group of teachers.](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=22064)
>
> **Answer**: "When in the document specification the term "User" is used it usually means "any user" of the system. Therefore, any user of the system can schedule a meeting and be a participant in a meeting."

> [**Question 24**: When a user does not respond to a meeting invitation, should his status be changed to "not responded" or "rejected"?](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=22080)
>
> **Answer**: "The status of someone that did not answer should be "no answer" or "unknown""

## 2.3 Functional Requirements

> **FRC01** - Schedule a Meeting - A user schedules a meeting. The system must check if all participants are available and send invitations to participants.

## 2.4. Acceptance Criteria

- The system must check if all participants are available and send invitations to participants.

## 3. Analysis

### 3.1 Main success scenario

1. User asks to schedule a meeting
2. The System asks the user to introduce the data needed to schedule a meeting
3. User types the requested data
4. The system shows the user the data typed and asks for confirmation
5. User confirms the data
6. The system reports the success of the operation

### 3.2. Conditions

- The user must be authenticated and authorized to perform the operation.

- The system must check if all participants are available (no classes or meetings in that time) and send invitations to participants.

### 3.3. System Sequence Diagram

![US4001_SSD](out/US4001_SSD.svg)

### 3.4. Partial Domain Model

![US4001_DM](out/US4001_DM.svg)

## 4. Design

### 4.1. Functionality Realization (Without observer pattern)

![US4001_SD](out/US4001_SD.svg)

### 4.1. Functionality Realization (observer pattern)

![US4001_SD2](out/US4001_SD2.svg)

### 4.2. Class Diagram

![US4001_CD](out/US4001_CD.svg)

### 4.3. Applied Patterns

- **Dependency Injection:** This is used in the controller and in the service. This is done to enable the use of a mock repository in the tests and to reduce coupling.
- **State:** The state pattern is used to represent the state of the invite. This is done to keep the invite state logic encapsulated in the invite status class and to reduce coupling.
- **Observer Pattern:** This is used to notify the user when he receives a invite to a meeting. This is done to reduce coupling and to make the system more extensible.

### 4.4. Tests

_Note: This are some simplified versions of the tests for readability purposes._

**Test 1:** Ensure user can receive an invite to a meeting

```java
  @Test
  public void testUserHasInvite() {
    Invite invite = getDummyInvite();
    assertEquals(invite.user(), getDummyUser());
  }
```

**Test 2:** Ensure user can accept an invite to a meeting

```java
  @Test
  public void testUserCanAcceptInvite() {
    Invite invite = getDummyInvite();
    invite.accept();
    assertEquals(invite.status(), InviteStatus.Status.ACCEPTED);
  }
```

**Test 3:** Ensure user can reject an invite to a meeting

```java
  @Test
  public void testUserCanRejectInvite() {
    Invite invite = getDummyInvite();
    invite.reject();
    assertEquals(invite.status(), InviteStatus.Status.REJECTED);
  }
```

**Test 4:** Ensure user can ignore an invite to a meeting

```java
  @Test
  public void testUserCanIgnoreInvite() {
    Invite invite = getDummyInvite();
    invite.ignore();
    assertEquals(invite.status(), InviteStatus.Status.NO_ANSWER);
  }
```

**Test 5:** Ensure its possible to create a meeting

```java
  @Test
  public void ensureItsPossibleToCreateMeeting() {
    Meeting meeting = getDummyMeeting();
    assertEquals(meeting.time(), getDummyMeeting().time());
    assertEquals(meeting.duration(), getDummyMeeting().duration());
  }
```

## 5. Implementation

### 5.1. Controller

- Relevant implementation details

```java
 public Meeting scheduleMeeting(Time time, Duration duration, Iterable<SystemUser> users) {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.STUDENT, ClientRoles.POWER_USER, ClientRoles.MANAGER,
        ClientRoles.TEACHER);

    Preconditions.noneNull(time, duration);

    Meeting meeting = new Meeting(time, duration);

    if (meetingRepository.containsOfIdentity(meeting.identity()))
      throw new IllegalStateException("There is already a meeting with that id.");
    Meeting m = saveMeeting(meeting);

    sendInvites(users, m);

    return m;
  }
```

## 6. Integration & Demonstration

![US4001_DEMO](US4001_DEMO.png)

## 7. Observations

It was decided to not use the observer pattern because it would be too complex for the problem at hand. Since the system is not very complex, it was decided to use a simpler solution. The solution used was to have a list of participants in the meeting and to have a list of invites for each participant. This way, when a meeting is created, the system creates an invite for each participant and adds it to the list of invites of each participant. This way, when a participant accepts or rejects an invite, the system can check if all participants have accepted the invite and if so, the meeting is scheduled. If not, the meeting is not scheduled. To use the observer pattern, the system should have a interface to when the user receives a invite, it would appear on the screen of the user.
