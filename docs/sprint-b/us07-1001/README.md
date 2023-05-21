# User Story 1001 - User Management

> As Manager, I want to be able to register, disable/enable, and list users of the system (Teachers and Students, as well as Managers).

|             |           |
| ----------- | --------- |
| ID          | 07        |
| Sprint      | B         |
| Application | 1 - Users |
| Priority    | 1         |

---

## 1. Context

This is the first time this task is assigned to be developed. This is a new functionality that allows managers to register every type of user, as well as disable/enable and list them.

## 2. Requirements

### "Management of Users - Create, disable/enable, and list users of the system (Teachers and Students, as well as Managers)"

## 2.1. Client Specifications

- "A student is characterized by his/her name, date of birth, tax payer number and a mechanographic number assigned automatically by the system based on the year of registration and a sequential number, e.g., "202300001"."

- "A Teacher is characterized by his/her name, date of birth, tax payer number and an acronym
  inputed by the administrator (e.g., "AALB")."

## 2.2. Client Clarifications

- N/a

## 2.3. Functional Requirements

> **FRU01** - Management of Users Create, disable/enable, and list users of the system (Teachers and Students, as well as Managers)

## 2.4. Acceptance Criteria

- N/a

---

## 3. Analysis

### 3.1. Conditions

- The manager must be authenticated and authorized to perform the operations.

### 3.2. Partial Domain Model

![US1001_DM](out/US1001_DM.svg)

## 4. Design

### 4.1. Applied Patterns

- **Dependency Injection:** This is used in the controller and in the services. This is done to enable the use of a mock repository in the tests and to reduce coupling.
- **Repository:** This is used to store the users. This is done to allow the persistence of the enrollments and to allow the use of the enrollments in other parts of the application.
- **Service:** This is used to register the user in the system user repository. This is done to reduce coupling and to allow the use of the services in other parts of the application.

### 4.2. Tests

**Test 1:** Ensure user is deactivated

```java
@Test
public void ensureUserIsDeactivated() {
  SystemUser s = getDummyUser();

  deactivateUserController.deactivate(s);

  assertFalse(deactivateUserController.activeUsers().contains(s));
}
```

## 5. Implementation

### 5.1. Deactivate User Controller

```java
public class DeactivateUserController {

  private final AuthorizationService authz = AuthzRegistry.authorizationService();
  private final UserManagementService userSvc = AuthzRegistry.userService();

  public Iterable<SystemUser> activeUsers() {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.MANAGER);

    return userSvc.activeUsers();
  }

  public SystemUser deactivateUser(final SystemUser user) {
    authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.MANAGER);

    return userSvc.deactivateUser(user);
  }
}
```

### 5.2. Add user Controller methods

```java
public Student addStudent(final String username, final String password, final String firstName,
    final String lastName, final String email, final String mecanographicNumber) {
  authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.MANAGER);
  Set<Role> roles = new HashSet<Role>();
  roles.add(ClientRoles.STUDENT);

  SystemUser u = addUser(username, password, firstName, lastName, email, roles, CurrentTimeCalendars.now());
  Student s = new StudentBuilder().withSystemUser(u).withMecanographicNumber(mecanographicNumber).build();

  return studentRepository.save(s);
}

public Teacher addTeacher(final String username, final String password, final String firstName,
    final String lastName, final String email, final String taxPayerNumber, final String birthDate,
    final String acronym) {
  authz.ensureAuthenticatedUserHasAnyOf(ClientRoles.POWER_USER, ClientRoles.MANAGER);
  Set<Role> roles = new HashSet<Role>();
  roles.add(ClientRoles.TEACHER);

  SystemUser u = addUser(username, password, firstName, lastName, email, roles, CurrentTimeCalendars.now());
  Teacher s = new TeacherBuilder().withSystemUser(u).withTaxPayerNumber(taxPayerNumber).withBirthDate(birthDate)
      .withAcronym(acronym).build();

  return teacherRepository.save(s);

}
```

## 6. Integration & Demonstration

### 6.1. List students scenario

![LIST_STUDENTS_DEMO](LIST_STUDENTS_DEMO.png)

### 6.2. Add student scenario

![ADD_USER_DEMO](ADD_USER_DEMO.png)

### 6.3. Deactivate user scenario

![DEACTIVATE_USER_DEMO](DEACTIVATE_USER_DEMO.png)

## 7. Observations

- N/a
