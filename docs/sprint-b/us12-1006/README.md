# User Story 1006 - List Available Courses

|             |             |
| ----------- | ----------- |
| ID          | 12          |
| Sprint      | B           |
| Application | 2 - Courses |
| Priority    | 1           |

---

## 1. Requirements

### "As User, I want to list all the courses that are available to me."

## 1.1. Client Specifications

- "The platform should support the activities of the major actors of a course, mainly teachers, students and managers."
- "The Admin app is used by managers to manage courses, users and enrollment of students."
- "A course may be open or closed."
- "A course has also a small textual descriptions of its contents."
- "Courses may have a minimum and a maximum number of enrolled students."
- "The usual workflow related to the course can be illustrated as follows:
  event create (-> state close) -> event open (-> state open) -> event open enrollments (-> state
  enroll) -> event close enrollments (-> state in progress) -> event close (-> state closed")

## 1.2. Client Clarifications

> [**Question 46**](../../client-clarifications.md#question-46): In G1006, what are the available courses for each role?
>
> **Answer**: "Teachers: courses that they are in charge of; Students: courses that they are enrolled in, and courses that have opened enrolment status; Managers: all courses."

## 1.3. Functional Requirements

> **FRC05** List Courses - All users are able to execute this functionality.

## 1.4. Acceptance Criteria

> N/A.

---

## 2. Analysis

### 2.1. Main success scenario

- A list of available courses is displayed to the user.

### 2.2. Conditions

- The user must be authenticated and authorized to perform the operation.
- The course must have the state enroll to be listed to the user.

### 2.3. System Sequence Diagram

![US1006_SSD](out/US1006_SSD.svg)

### 2.4. Sequence Diagram (Simplified)

![US1006_SD](out/US1006_SD.svg)

### 2.5. Partial Domain Model

![US1006_DM](out/US1006_DM.svg)

## 3. Design

### 3.1. Functionality Realization

![US1006_SD](out/US1006_SD.svg)

### 3.2. Class Diagram

![US1006_CD](out/US1006_CD.svg)

### 3.3. Applied Patterns

- **Dependency Injection:** This is used in the controller and in the services. This is done to enable the use of a mock repository in the tests and to reduce coupling.
- **Repository:** This is used to store the scheduled extraordinary classes. This is done to reduce coupling and to allow the use of the repository in other parts of the application.
- **Service:** This is used to provide a list of System Users and courses to the controller. This is done to reduce coupling and to allow the use of the service in other parts of the application.

### 3.4. Tests

_Note: This are some simplified versions of the tests for readability purposes._

**Test 1:** xxx

```java
  @Test
  private void test1() {
    assetTrue(true);
  }
```

## 4. Implementation

### 4.1. Controller

```java
  public ListCoursesController(AuthorizationService authz, CourseRepository courseRepository,
      TeacherRepository teacherRepository, StudentRepository studentRepository,
      EnrolmentRepository enrolmentRepository) {
    this.authz = authz;
    this.teacherRepository = teacherRepository;
    this.studentRepository = studentRepository;

    this.listCourseService = new ListCourseService(courseRepository);
    this.enrolmentListService = new ListEnrolmentService(enrolmentRepository);
  }

  public Iterable<CourseDTO> getCoursesForLoggedUser() {
    if (authz.isAuthenticatedUserAuthorizedTo(ClientRoles.MANAGER, ClientRoles.POWER_USER))
      return listCourseService.listAll();

    if (authz.isAuthenticatedUserAuthorizedTo(ClientRoles.TEACHER)) {
      final SystemUser user = authz.loggedinUserWithPermissions(ClientRoles.TEACHER).orElseThrow();
      final Teacher teacher = teacherRepository.findByUsername(user.username()).orElseThrow();
      return listCourseService.listInProgressCoursesThatTeacherLectures(teacher);
    }

    if (authz.isAuthenticatedUserAuthorizedTo(ClientRoles.STUDENT)) {
      final SystemUser user = authz.loggedinUserWithPermissions(ClientRoles.STUDENT).orElseThrow();
      final Student student = studentRepository.findByUsername(user.username()).orElseThrow();
      return enrolmentListService
          .listStudentsCourses(student.identity());
    }

    return null;
  }
```

## 5. Integration & Demonstration

![US1006_DEMO](US1006_DEMO.png)

## 6. Observations

- N/a.
