# User Story 1007 - Enroll Students in Bulk

|             |             |
| ----------- | ----------- |
| ID          | 13          |
| Sprint      | B           |
| Application | 2 - Courses |
| Priority    | 5           |

---

## 1. Context

This is the first time this task is assigned to be developed. This is a new functionality that is needed to add a large number of students to a course.

---

## 2. Requirements

#### "As Manager, I want to enroll students in bulk by importing their data using a .csv file."

### 2.1. Client Specifications

- Courses may have a **minimum** and a **maximum** number of enrolled students.

### 2.2. Client Clarifications

## [Question 1](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=21913)

> A course can open if the limits are exceeded or not? Who can setup the limits? Are the limits mandatory?

"Managers should be able to specify the limits. In my opinion the system should notify the manager if a course is out of the enrolment limits (before this course is changed to "in progress"). But, in the end, it is up to the manager to follow/enforce or not, the limits."

## [Question 2](https://moodle.isep.ipp.pt/mod/forum/discuss.php?d=21922)

> Can one student be enrolled in different courses?

"Yes"

### 2.3. Functional Requirements

- **FRC06** Bulk Enroll Students in Course - This can be made by importing a .csv file with students. Only managers are able to execute this functionality.

### 2.4. Acceptance Criteria

- N/A.

---

## 3. Analysis

### 3.1. Input and Output Data

#### Input

- Course (Selected from a list of courses)
- List of students (CSV file)

#### Output

- Success or error messages (from parser)

### 3.2. System Sequence Diagram

![US1007_SSD](out/US1007_SSD.svg)

### 3.3. Partial Domain Model

![US1007_DM](out/US1007_DM.svg)

---

## 4. Design

### 4.1. Functionality Realization

![US1007_SD](out/US1007_SD.svg)

### 4.2. Class Diagram

![US1007_CD](out/US1007_CD.svg)

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

---

## 5. Implementation

### 5.1. Controller

- Relevant implementation details

```java
  private void sample() {
    return true;
  }
```

---

## 6. Integration & Demonstration

![US1007_DEMO](US1007_DEMO.png)

---

## 7. Observations

- N/A
