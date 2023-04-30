# User Story 1003 - Create Courses

|             |             |
| ----------- | ----------- |
| ID          | 08          |
| Sprint      | B           |
| Application | 2 - Courses |
| Priority    | 1           |

---

## 1. Context

This is the first time this task is assigned to be developed. This is a new functionality that is needed to create courses.

## 2. Requirements

### "As Manager, I want create courses"

## 2.1. Client Specifications

-

## 2.2. Client Clarifications

> [**Question**: Course id is unique and generated o\*r inputed? Course title and name are the same?](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=21835)
>
> **Answer**: "Unique code inputted by the administrator, e.g., "JAVA-1, that is used to identify it. It has a name (name and title may refer to the same property of courses, and I would say that there is no need for it to be unique)"

> [**Question**:A course can open if the limits are exceeded or not? Who can setup the limits? Are the limits mandatory?](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=21913)
>
> **Answer**:"Managers should be able to specify the limits. In my opinion the system should notify the manager if a course is out of the enrolment limits (before this course is changed to "in progress"). But, in the end, it is up to the manager to follow/enforce or not, the limits."

> [**Question**: Course code verifications?](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=21971)
>
> **Answer**:"Course code should be unique and there are not restrictions on the format of the code."

## 2.3. Functional Requirements

> **FRC01** - Create Course Different editions of a course are to be considered different courses (e.g., Intro-Math-Sem01, Intro-Math-Sem02). Only managers are able to execute this functionality

## 2.4. Acceptance Criteria

- N/a

---

## 3. Analysis

### 3.1. Main success scenario

1. Manager asks to create a new course
2. The System asks the manager to introduce the data needed to create a course
3. Manager types the requested data
4. The system shows the manager the data typed and asks for confirmation
5. Manager confirms the data
6. The system reports the success of the operation

### 3.2. Conditions

- The manager must be authenticated and authorized to perform the operation.

### 3.3. System Sequence Diagram

![US1002_SSD](out/US1002_SSD.svg)

### 3.4. Sequence Diagram (Simplified)

![US1002_SD](out/US1002_SD.svg)

### 3.5. Partial Domain Model

![US1002_DM](out/US1002_DM.svg)

## 4. Design

### 4.1. Functionality Realization

![US1002_SD](out/US1002_SD.svg)

### 4.2. Class Diagram

![US1002_CD](out/US1002_CD.svg)

### 4.3. Applied Patterns

- xxx

### 4.4. Tests

**Test 1:** xxx

```java
  @Test
  private void test1() {
    assetTrue(true);
  }
```

## 5. Implementation

### 5.1. Controller

- Relevant implementation details

```java
  private void sample() {
    return true;
  }
```

## 6. Integration & Demonstration

![US1002_DEMO](US1002_DEMO.png)

## 7. Observations

- The history of the states of a course is not relevant.
