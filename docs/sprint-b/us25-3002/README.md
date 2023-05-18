# User Story 3002 - Create a Board

> As User, I want to create a board.

|             |                   |
| ----------- | ----------------- |
| ID          | 25                |
| Sprint      | B                 |
| Application | 4 - Shared Boards |
| Priority    | 1                 |

---

## 1. Context

This is the first time this task is assigned to be developed. This is a new functionality that is needed to create a board.

## 2. Requirements

### "Create Board - A user creates a board"

## 2.1. Client Specifications

- "The project aims to implement the concept of shared board, as a board that can [...] used for managing projects."

- "All users can create and use boards"

- "It has a unique title."

- "It is divided into a certain number of columns and rows. Therefore it has a certain number of cells. For the moment it makes sense to define the maximum number of rows to 20 and of columns to 10. But this should be a setting in a property file. Columns and rows may have titles. They may also be identified by an integer number from 1 to the maximum number."

- "The user that creates the board is its owner. The owner can share the board with other users. Users may have read or write access to the board."

- "The owner of a board can archive the board when it will no longer be used."

## 2.2. Client Clarifications

> [**Question:** Can the owner of a board set custom permissions for each user?](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=21945)
>
> **Answer**: "Yes"
>
> [**Question:** Should it be possible to have a column with no title and no identifier in a board?](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=22120)
>
> **Answer**: "In order for user to post content into a cell they must identify the cell. Therefore, I think at least, it should be possible to identify the cell by the number of its column and the number of its row. If the cells have titles, these titles can be used to identify the cells. However, it should always be possible to identify a cell by the column number and row number."
>
> [**Question:** Can a user own more than one board?](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=22171)
>
> **Answer**: "Yes"

## 2.3. Functional Requirements

> **FRB01** Create Board - A user creates a board.

## 2.4. Acceptance Criteria

- N/a

---

## 3. Analysis

### 3.1. Conditions

- The user must be authenticated, although it is not necessary to have any specific role.

### 3.2. System Sequence Diagram

![US3002_SSD](out/US3002_SSD.svg)

### 3.3. Partial Domain Model

**Note:** The domain model was made in a way to avoid the synchronization problems, as several clients will try to concurrently update boards.

![US1003_DM](out/US1003_DM.svg)

## 4. Design

### 4.1. Functionality Realization

![US1003_SD](out/US1003_SD.svg)

### 4.2. Class Diagram

![US1003_CD](out/US1003_CD.svg)

### 4.3. Applied Patterns

- **Builder:** The builder pattern is used to provide a flexible way to create a board. This is done by using the `BoardBuilder` class. This allows the creation of a board with different ways to set some of its attributes and also allows the creation of a board without setting non mandatory attributes. This will also be useful to develop the tests.
- **Dependency Injection:** This is used in the controller and in the service. This is done to enable the use of a mock repository in the tests and to reduce coupling.
- **Repository:** This is used to store the boards. This is done to allow the persistence of the boards and to allow the use of the boards in other parts of the application.
- **Service:** This is used to provide a list of System Users to the controller. This is done to reduce coupling and to allow the use of the service in other parts of the application.

### 4.4. Tests

_Note: This are some simplified versions of the tests for readability purposes._

**Test 1:** Ensure the course is in the correct state after the operation

```java
  @Test
  public void ensureCourseIsInCorrectStateAfterToggle() {
    final Course course = getDummyOpenCourse();

    assertTrue(course.enrolmentState().isClosed());
    course.toggleEnrolmentState();
    assertTrue(course.enrolmentState().isOpen());
  }
```

**Test 2:** Ensure that double toggle does not change the state (the state is reversible)

```java
  @Test
  public void ensureDoubleToggleDoesNotChangeState() {
    final Course course = getDummyOpenCourse();

    assertTrue(course.enrolmentState().isClosed());
    course.toggleEnrolmentState();
    course.toggleEnrolmentState();
    assertTrue(course.enrolmentState().isClosed());
  }
```

**Test 3:** Ensure that is not possible to open enrolments in a closed course

```java
  @Test
  public void ensureCannotOpenEnrolmentsInClosedCourse() {
    final Course course = getDummyClosedCourse();

    assertTrue(course.state().isClosed());
    assertTrue(course.enrolmentState().isClosed());
    assertThrows(IllegalStateException.class, () -> course.toggleEnrolmentState());
  }
```

**Test 4:** Ensure that is not possible to toggle enrolments in a course that is finished

```java
  @Test
  public void ensureCannotOpenEnrolmentsInFinishedCourse() {
    final Course course = getDummyFinishedCourse();

    assertTrue(course.state().isFinished());
    assertThrows(IllegalStateException.class, () -> course.toggleEnrolmentState());
  }
```

**Test 5:** Ensure that is possible to toggle enrolments in a course that is in progress

```java
  @Test
  public void ensureIsPossibleToToggleEnrolmentsInProgressCourse() {
    final Course course = getDummyInProgressCourse();

    assertTrue(course.state().isInProgress());
    assertTrue(course.enrolmentState().isClosed());
    course.toggleEnrolmentState();
    assertTrue(course.enrolmentState().isOpen());
  }
```

**Test 6:** Ensure that is possible to toggle enrolemtns in a course that is open

```java
  @Test
  public void ensureIsPossibleToToggleEnrolmentsInOpenCourse() {
    final Course course = getDummyOpenCourse();

    assertTrue(course.state().isOpen());
    assertTrue(course.enrolmentState().isClosed());
    course.toggleEnrolmentState();
    assertTrue(course.enrolmentState().isOpen());
  }
```

## 5. Implementation

### 5.1. Controller

- Relevant implementation details

```java
  public CourseDTO toggleEnrolmentState(CourseDTO courseDTO) {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.MANAGER);

    Course course = courseRepository.findByCode(courseDTO.getCode()).orElseThrow();

    course.toggleEnrolmentState();

    return courseRepository.save(course).toDto();
  }
```

## 6. Integration & Demonstration

### 6.1. Success scenario

![US1003_DEMO](US1003_DEMO.png)

### 6.2. Failure scenario

![US1003_DEMO_FAIL](US1003_DEMO_FAIL.png)

## 7. Observations

- The history of the states of a course is not relevant.
