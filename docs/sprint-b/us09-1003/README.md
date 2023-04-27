# User Story 1003 - Manage Course Enrollments

|             |             |
| ----------- | ----------- |
| ID          | 09          |
| Sprint      | B           |
| Application | 2 - Courses |
| Priority    | 1           |

---

## 1. Requirements

### "As Manager, I want to open and close enrollments in courses"

## 1.1. Client Specifications

- "The Admin app is used by managers to manage courses, users and enrollment of students."

- "Managers [...] manage all the users of the system as well as [...] students enrollment."

- "Courses may have a minimum and a maximum number of enrolled students."

- "Students enroll in courses"

- "The usual workflow related to the course can be illustrated as follows:
  event create (-> state close) -> event open (->state open) -> event open enrollments (-> state
  enroll) -> event close enrollments (-> state in progress) -> event close (-> state closed"

## 1.2. Client Clarifications

> [**Question**: A course can open if the limits are exceeded or not? Who can setup the limits? Are the limits mandatory?](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=21913)
>
> **Answer**: "Managers should be able to specify the limits. In my opinion the system should notify the manager if a course is out of the enrolment limits (before this course is changed to "in progress"). But, in the end, it is up to the manager to follow/enforce or not, the limits."

> [**Question**: Can one student be enrolled in different courses?](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=21922)
>
> **Answer**: "Yes"

> [**Question**: In US1009, What are the criteria for a student to be enrolled in a course?](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=22498)
>
> **Answer**: "This is not a system requirement. You should only register the decision of the manager."]

## 1.3. Functional Requirements

> **FRC02** Open/Close Enrollments in Course - Only managers are able to execute this functionality.
> **FRC07** - Request Enrollment in Course The student requests to be enrolled in a course

## 1.4. Acceptance Criteria

- N/a

---

## 2. Analysis

### 2.1. Main success scenario

1. Manager asks to list all the courses and their states
2. The System lists all the non finished courses
3. Manager selects the desired course
4. The system asks the manager to select the desired action
5. Manager selects the desired action
6. The system reports the success of the operation

### 2.2. Conditions

- The manager must be authenticated and authorized to perform the operation.
- The course must be in a state that allows the operation to be performed.

### 2.3. System Sequence Diagram

![US1003_SSD](out/US1003_SSD.svg)

### 2.4. Sequence Diagram (Simplified)

![US1003_SD](out/US1003_SD.svg)

### 2.5. Partial Domain Model

![US1003_DM](out/US1003_DM.svg)

## 3. Design

### 3.1. Functionality Realization

![US1003_SD](out/US1003_SD.svg)

### 3.2. Class Diagram

![US1003_CD](out/US1003_CD.svg)

### 3.3. Applied Patterns

- xxx

### 3.4. Tests

**Test 1:** xxx

```java
  @Test
  private void test1() {
    assetTrue(true);
  }
```

## 4. Implementation

### 4.1. Controller

- Relevant implementation details

```java
  private void sample() {
    return true;
  }
```

## 5. Integration & Demonstration

![US1003_DEMO](US1003_DEMO.png)

## 6. Observations

N/a
