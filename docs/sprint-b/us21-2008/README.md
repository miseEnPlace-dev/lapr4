# User Story 2008 - Create/Update Automatic Formative Exams

|             |           |
| ----------- | --------- |
| ID          | 21        |
| Sprint      | B         |
| Application | 3 - Exams |
| Priority    | 5         |

---

## 1. Requirements

### "As Teacher, I want to create/update automatic formative exams."

## 1.1. Client Specifications

<!-- TODO -->

- "The platform should support the activities of the major actors of a course, mainly teachers, students and managers."
- "The Admin app is used by managers to manage courses, users and enrollment of students."
- "A course may be open or closed."
- "A course has also a small textual descriptions of its contents."
- "Courses may have a minimum and a maximum number of enrolled students."
- "The usual workflow related to the course can be illustrated as follows:
  event create (-> state close) -> event open (-> state open) -> event open enrollments (-> state
  enroll) -> event close enrollments (-> state in progress) -> event close (-> state closed")
  v

## 1.2. Client Clarifications

<!-- TODO -->

> [**Question 2**](../../client-clarifications.md#question-2): As for the exam, is the title written in the exam the same as its unique title to identify?
> \
> **Answer**: "The title should be unique. Maybe it could be used to identify the exam."

> [**Question 14**](../../client-clarifications.md#question-14): Can the same question be used in different exams? Or are the questions made for a specific exam?
> \
> **Answer**: "There is no "database" of questions (no identity)"

## 1.3. Functional Requirements

> **FRE01** Create Exam - A Teacher creates a new exam. This includes the specification of the exam (i.e., its structure, in accordance with a grammar for exams that is used to validate the specification of the exam).

> **NFR09** Exam Language - The support for exams (its design, feedback and grading) must follow specific technical requirements, specified in LPROG. The ANTLR tool should be used (<https://www.antlr.org/>).

## 1.4. Acceptance Criteria

> **AC.1**: The specification of formative exams is similar to regular exams, but the user specifies the type of questions to be inserted in the sections instead of the specific questions.

> **AC.2**: When generating the automatic formative exam, the system should randomly create the questions (without repetition on a given exam).

> **AC.3**: Also, formative exams do not have open and close dates. Feedback and grades are only provided at the end of the exam.

> **AC.4**: The support for exams (its design, feedback and grading) must follow specific technical requirements, specified in LPROG.

> **AC.5**: The ANTLR tool should be used (<https://www.antlr.org/>).

---

## 2. Analysis

### 2.1. Main success scenario

<!-- TODO -->

1. Warehouse Employee requires the configuration of an AGV available in the Warehouse.
2. The System asks for information related to the AGV
3. Warehouse Employee enters the information.
4. The System reports the success of the operation.

### 2.2. Conditions

<!-- TODO -->

The registration information of the new category is persisted/saved in the system.

### 2.3. System Sequence Diagram

![US1006_SSD](out/US1006_SSD.svg)

### 2.4. Sequence Diagram (Simplified)

![US1006_SD](out/US1006_SD.svg)

### 2.5. Partial Domain Model

![US1006_DM](out/US1006_DM.svg)

---

## 3. Design

### 3.1. Functionality Realization

![US1006_SD](out/US1006_SD.svg)

### 3.2. Class Diagram

![US1006_CD](out/US1006_CD.svg)

### 3.3. Applied Patterns

<!-- TODO -->

- xxx

### 3.4. Tests

<!-- TODO -->

**Test 1:** xxx

```java
  @Test
  private void test1() {
    assetTrue(true);
  }
```

---

## 4. Implementation

### 4.1. Controller

<!-- TODO -->

- Relevant implementation details

```java
  private void sample() {
    return true;
  }
```

---

## 5. Integration & Demonstration

<!-- TODO -->

![US1006_DEMO](assets/US1006_DEMO.png)

## 6. Observations

<!-- TODO -->

N/a
