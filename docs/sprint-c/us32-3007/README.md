# User Story 3004 - Change Post-It

> As User, I want to change a post-it

|             |                   |
| ----------- | ----------------- |
| ID          | 32                |
| Sprint      | C                 |
| Application | 4 - Shared Boards |
| Priority    | 1                 |

## 1. Context

- This is the first time this task is assigned to be developed. This is a new functionality that allows user to update post-it in a board.

## 2. Requirements

### "Update Post-it - A user update a post-it on a board"

## 2.1. Client Specifications

- When the server commits a post it also should notify all clients with access to the board of the update.
- The user who created a post in a cell can change that post. It may change its contents or
  move the post into a free cell. The user can also undo the last change.
- The owner of a post can delete it.

## 2.2. Client Clarifications

> [**Question:** Can a cell have more than one post?](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=22202)
>
> **Answer**: "No"
>
> [**Question:** Where should a notification regarding the update of a board post appear? In the user app, the shared board app or both?](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=22041)
>
> **Answer**:"it should be in the Shared Board App."

## 2.3. Functional Requirements

- **FRB04** Change Post-it - A user change a post-it on a board

## 2.4. Acceptance Criteria

- This functional part of the system has very specific technical requirements, particularly some concerns about synchronization problems.
- In fact, several clients will try to concurrently update boards.
- As such, the solution design and implementation must be based on threads, condition variables and mutexes. Specific requirements will be provided in SCOMP.

---

## 3. Analysis

As there is no user story for deleting the post-it, that implementation will be done in this user story.

### 3.1. Conditions

- User must have write permissions to update a post-it.
- If the post-it is moved, it should be moved to a cell that is not occupied by another post-it.

### 3.2. System Sequence Diagram

![US3007_SSD](out/US3007_SSD.svg)

### 3.3. Partial Domain Model

![US3007_DM](out/US3007_DM.svg)

## 4. Design

### 4.1. Functionality Realization

#### 4.1.1. Sequence Diagram - Update Post-it

![US3007_SD](out/US3007_SD.svg)

#### 4.1.2. Sequence Diagram - Delete Post-it

![US3007_SD](out/US3007_SD_delete.svg)

### 4.2. Class Diagram

![US3007_CD](out/US3007_CD.svg)

### 4.3. Applied Patterns

- **Dependency Injection:** This is used in the controller and in the service. This is done to enable the use of a mock repository in the tests and to reduce coupling.
- **Repository:** This is used to store the post-its. This is done to allow the persistence of the post-its and to allow the use of the post-its in other parts of the application.
- **Service:** This is used to provide a list of boards/post-its and to do some validations. This is done to reduce coupling and to allow the use of the service in other parts of the application.

### 4.4. Tests

_Note: This are some simplified versions of the tests for readability purposes._

**Test 1:** Ensure update method toggles old post-it isLatest attribute.

```java
@Test
public void ensureUpdateToggleIsLatest() { ... }
```

**Test 2:** Ensure update method returns the updated post-it.

```java
@Test
public void ensureUpdateReturnsUpdatedPostIt() { ... }
```

**Test 3:** Ensure delete method toggles old post-it isLatest attribute.

```java
@Test
public void ensureDeleteToggleIsLatest() { ... }
```

**Test 4:** Ensure delete method returns the deleted post-it.

```java
@Test
public void ensureDeleteReturnsDeletedPostIt() { ... }
```

## 5. Implementation

### 5.1. Controller

[Click here](/ecourse.core/src/main/java/eapli/ecourse/postitmanagement/application/ChangePostItController.java) to see the full code.

## 6. Integration & Demonstration

![US3007_DEMO](US3007_DEMO.png)

## 7. Observations

- The scomp implementation is documented in the [scomp report](/docs/scomp-concurrency.md).
