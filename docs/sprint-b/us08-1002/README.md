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

- A course is characterized by its title and a unique code inputed by the administrator, e.g.,
  "JAVA-1".

- Each course should have a unique identifier (automatically generated) and a unique name. A course may be open or closed. A closed course can not have any activity.

- Each course must have a teacher in charge, but may also have other teachers. Courses are not divided into sections (i.e., "turmas").

- A course has also a small textual descriptions of its contents.

- Courses may have a minimum and a maximum number of enrolled students. This may limit the possibility of opening (i.e. starting) courses that do not satisfy the limits.

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

- Course code must be unique.
- Course code must be alphanumeric.
- Limits must be positive.

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
- Must exist at least one teacher in the system.

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

- - **Builder:** The builder pattern is used to provide a flexible way to create a course. This is done by using the `CourseBuilder` class. This allows the creation of a course with different ways to set some of its attributes and also allows the creation of a course without setting non mandatory attributes. This will also be useful to develop the tests.
- **Dependency Injection:** This is used in the controller and in the service. This is done to enable the use of a mock repository in the tests and to reduce coupling.
- **Repository:** This is used to store the courses. This is done to allow the persistence of the courses and to allow the use of the courses in other parts of the application.
- **Service:** This is used to provide a list of Teachers to the controller. This is done to reduce coupling and to allow the use of the service in other parts of the application.

### 4.4. Tests

_Note: This are some simplified versions of the tests for readability purposes._

**Test 1:** Ensure its possible to create a course.

```java
  @Test
  private Course ensureIsPossibleToCreateCourse() {
    return new Course(CourseCode.valueOf("1234"), CourseTitle.valueOf("dummy"),
        CourseDescription.valueOf("dummy"), EnrolmentLimits.valueOf(10, 20), new CourseState(),
        new CourseEnrolmentState());
  }
```

**Test 2:** Ensure its not possible to create a course with null fields.

```java
  @Test
  public void ensureItsNotPossibleToCreateCourseWithNullFields() {
    assertThrows(IllegalArgumentException.class, () -> controller.createCourse(null, null, null, 0, 0, null));
  }
```

**Test 3:** Ensure its not possible to create a course with empty fields.

```java
  @Test
  public void ensureItsNotPossibleToCreateCourseWithEmptyFields() {
    assertThrows(IllegalArgumentException.class, () -> controller.createCourse("", "", "", 0, 0, null));
  }
```

**Test 4:** Ensure its not possible to create a course with negative limits.

```java
  @Test
  public void ensureItsNotPossibleToCreateCourseWithNegativeLimits() {
    assertThrows(IllegalArgumentException.class, () -> controller.createCourse("1234", "dummy", "dummy", -1, -1, null));
  }
```

## 5. Implementation

### 5.1. Controller

- Relevant implementation details

```java
  public Course createCourse(String code, String title, String description,
      int min, int max, TeacherDTO teacherDTO) {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.MANAGER);

    Preconditions.noneNull(code, title, description, min, max, teacherDTO);

    Teacher teacher = teacherRepository.findByTaxPayerNumber(teacherDTO.getNumber()).orElseThrow();

    Course course = new CourseBuilder().withCode(code).withTitle(title).withDescription(description)
        .withEnrolmentLimits(min, max).withResponsibleTeacher(teacher).build();

    if (courseRepository.containsOfIdentity(course.code()))
      throw new IllegalStateException("There is already a course with that code.");

    return saveCourse(course);
  }
```

## 6. Integration & Demonstration

![US1002_DEMO](./assets/Demo.png)

## 7. Observations

- N/a
