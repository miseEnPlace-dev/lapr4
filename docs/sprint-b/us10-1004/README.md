# User Story 1004 - Toggle Course Status

|             |             |
| ----------- | ----------- |
| ID          | 10          |
| Sprint      | B           |
| Application | 2 - Courses |
| Priority    | 1           |

---

## 1. Requirements

### As Manager, I want to open and close courses.

## 1.1 Client Specifications

- "A course may be open or closed. A closed course can not have any activity."

- "The usual workflow related to the course can be illustrated as follows:
  event create (-> state close) -> event open (-> state open) -> event open enrollments (-> state
  enroll) -> event close enrollments (-> state in progress) -> event close (-> state closed)"

- "The Admin app is used by managers to manage courses, users and enrollment of students."

## 1.2. Client Clarifications

- N/A

## 1.3. Functional Requirements

- FRC03 Open/Close Course - Only managers are able to execute this functionality.

## 1.4. Acceptance Criteria

- N/A

## 2. Analysis

### 2.1. Main success scenario

1. Manager asks to change course status
2. The system lists all the available courses
3. Manager selects the desired course
4. System informs the success of the operation

### 2.2. Conditions

- The manager must be authenticated and authorized to perform the operation.

### 2.3. System Sequence Diagram

![US1004_SSD](out/US1004_SSD.svg)

### 2.4. Sequence Diagram (Simplified)

![US1004_SD](out/US1004_SD.svg)

### 2.5. Partial Domain Model

![US1004_DM](out/US1004_DM.svg)

## 3. Design

### 3.1. Functionality Realization

![US1004_SD](out/US1004_SD.svg)

### 3.2. Class Diagram

![US1004_CD](out/US1004_CD.svg)

### 3.3. Applied Patterns

- xxx

### 3.4. Tests

Test 1: xxx

@Test
private void test1() {
assetTrue(true);
}


## 4. Implementation

### 4.1. Controller

- Relevant implementation details

  private void sample() {
  return true;
  }


## 5. Integration & Demonstration

![US1003_DEMO](US1004_DEMO.png)

## 6. Observations

N/a
