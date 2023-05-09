# User Story 1003 - Manage Course Enrollments

|             |             |
| ----------- | ----------- |
| ID          | 09          |
| Sprint      | B           |
| Application | 2 - Courses |
| Priority    | 1           |

---

## 1. Context

This is the first time this task is assigned to be developed. This is a new functionality that is needed to know the state of the course relative to its enrollments.

## 2. Requirements

### "As Manager, I want to open and close enrollments in courses"

## 2.1. Client Specifications

- "The Admin app is used by managers to manage courses, users and enrollment of students."

- "Managers [...] manage all the users of the system as well as [...] students enrollment."

- "Courses may have a minimum and a maximum number of enrolled students."

- "Students enroll in courses"

- "The usual workflow related to the course can be illustrated as follows:
  event create (-> state close) -> event open (->state open) -> event open enrollments (-> state
  enroll) -> event close enrollments (-> state in progress) -> event close (-> state closed"

## 2.2. Client Clarifications

> [**Question:** A course can open if the limits are exceeded or not? Who can setup the limits? Are the limits mandatory?](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=21913)
>
> **Answer**: "Managers should be able to specify the limits. In my opinion the system should notify the manager if a course is out of the enrolment limits (before this course is changed to "in progress"). But, in the end, it is up to the manager to follow/enforce or not, the limits."

> [**Question:** Can one student be enrolled in different courses?](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=21922)
>
> **Answer**: "Yes"

> [**Question:** In US1009, What are the criteria for a student to be enrolled in a course?](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=22498)
>
> **Answer**: "This is not a system requirement. You should only register the decision of the manager."]

## 2.3. Functional Requirements

> **FRC02** Open/Close Enrollments in Course - Only managers are able to execute this functionality.
> **FRC07** - Request Enrollment in Course - The student requests to be enrolled in a course

## 2.4. Acceptance Criteria

- N/a

---

## 3. Analysis

### 3.1. Conditions

- The manager must be authenticated and authorized to perform the operation.
- The course must be in a state that allows the operation to be performed. (e.g. a course cannot open enrolments if it is closed)

### 3.2. System Sequence Diagram

![US1003_SSD](out/US1003_SSD.svg)

### 3.3. Partial Domain Model

![US1003_DM](out/US1003_DM.svg)

## 4. Design

### 4.1. Functionality Realization

![US1003_SD](out/US1003_SD.svg)

### 4.2. Class Diagram

![US1003_CD](out/US1003_CD.svg)

### 4.3. Applied Patterns

- **Factory:** The factory pattern is used in the instantiation of the CourseRepository. This is done to enable the use of a mock repository in the tests and to allow swapping of the repository using a property file.
- **Dependency Injection:** This is used in the CourseService to inject the CourseRepository. This is done to enable the use of a mock repository in the tests and to reduce coupling.
- **State:** The state pattern is used to represent the state of the course. This is done to keep the course enrolment state logic encapsulated in the state class and to reduce coupling.

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
  public void sample() {
    return true;
  }
```

## 6. Integration & Demonstration

![US1003_DEMO](US1003_DEMO.png)

## 7. Observations

- The history of the states of a course is not relevant.
