# User Story 3004 - Create Post-It

> A user creates a post-it on a board

|             |                   |
| ----------- | ----------------- |
| ID          | 31                |
| Sprint      | C                 |
| Application | 4 - Shared Boards |
| Priority    | 1                 |

## 1. Context

- This is the first time this task is assigned to be developed. This is a new functionality that allows user to create post-it in a board.

## 2. Requirements

### "Create Post-it - A user creates a post-it on a board"

## 2.1. Client Specifications

- When the server commits a post it also should notify all clients with access to the board of the update.
- The user who created a post in a cell can change that post. It may change its contents or
  move the post into a free cell. The user can also undo the last change.
- The owner of a post can delete it.

## 2.2. Client Clarifications

> [**Question:** Can a cell have more than one post?](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=22202)
>
> **Answer**: "No"

## 2.3. Functional Requirements

- **FRB04** Create Post-it - A user creates a post-it on a board

## 2.4. Acceptance Criteria

- This functional part of the system has very specific technical requirements, particularly some concerns about synchronization problems.
- In fact, several clients will try to concurrently update boards.
- As such, the solution design and implementation must be based on threads, condition variables and mutexes. Specific requirements will be provided in SCOMP.

---

## 3. Analysis

- User must have write permissions to create a post-it.
- The post-it must be created in a cell that is not occupied by another post-it.

### 3.3. System Sequence Diagram

![US3006_SSD](out/US3006_SSD.svg)

### 3.4. Partial Domain Model

![US3006_DM](out/US3006_DM.svg)

## 4. Design

### 4.1. Functionality Realization

![US3006_SD](out/US3006_SD.svg)

### 4.2. Class Diagram

![US3006_CD](out/US3006_CD.svg)

### 4.3. Applied Patterns

- 3006

### 4.4. Tests

_Note: This are some simplified versions of the tests for readability purposes._

**Test 1:** 3006

```java

```

## 5. Implementation

### 5.1. Controller

- Relevant implementation details

```java

```

## 6. Integration & Demonstration

![US3006_DEMO](out/US3006_DEMO.svg)

## 7. Observations

- N/a
