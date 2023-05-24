# User Story 4003 - Accept/Reject Meeting

> As User, I want to accept or reject a meeting request

|             |              |
| ----------- | ------------ |
| ID          | 41           |
| Sprint      | C            |
| Application | 5 - Meetings |
| Priority    | 5            |

## 1. Context

This is a new feature that allows users to accept or reject a meeting request.

## 2. Requirements

### "As User, I want to accept or reject a meeting request."

## 2.1. Client Specifications

- XXX

## 2.2. Client Clarifications

- XXX

## 2.3. Functional Requirements

- **FRM03** Accept/Reject Meeting A user accepts or rejects an invitation to a meeting.

## 2.4. Acceptance Criteria

- N/a

---

## 3. Analysis

### 3.1. Main success scenario

1. Checks if theres an invite to a meeting
2. System shows list of invites
3. Selects an invite
4. Asks if the user wants to accept or reject the invite
5. Accepts or reject the meeting invitation
6. Reports success of the operation

### 3.2. Conditions

- The user must be authenticated and authorized to perform the operation.

- The user must have received an invitation to at least one meeting.

### 3.3. System Sequence Diagram

![US4003_SSD](out/US4003_SSD.svg)

### 3.4. Sequence Diagram (Simplified)

![US4003_SD](out/US4003_SD.svg)

### 3.5. Partial Domain Model

![US4003_DM](out/US4003_DM.svg)

## 4. Design

### 4.1. Functionality Realization

![US4003_SD](out/US4003_SD.svg)

### 4.2. Class Diagram

![US4003_CD](out/US4003_CD.svg)

### 4.3. Applied Patterns

- XXX

### 4.4. Tests

_Note: This are some simplified versions of the tests for readability purposes._

**Test 1:** XXX

```java

```

## 5. Implementation

### 5.1. Controller

- Relevant implementation details

```java

```

## 6. Integration & Demonstration

![US4003_DEMO](out/US4003_DEMO.svg)

## 7. Observations

- N/a
