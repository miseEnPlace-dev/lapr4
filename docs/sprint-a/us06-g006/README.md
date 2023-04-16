# User Story G006 - Authentication Functionality

> As Project Managers, I want the system to support and apply authentication and authorization for all its users and functionalities.

|             |             |
| ----------- | ----------- |
| ID          | 06          |
| Sprint      | A           |
| Application | 0 - General |
| Priority    | 1           |

## Acceptance Criteria

- N/A.

## Requirements

- **NFR08** Authentication and Authorization - The system must support and apply authentication and authorization for all its users and functionalities.

## Analysis

There was already a base implementation of the authentication and authorization system in the framework.

### Related Tasks

**Task 02** - Make sign up go back to main menu instead of existing the app bug

This was a known bug that was fixed.

**Task 03** - Implement password hashing

The base program used a PlainTextEncoder that was not secure.
We implemented 2 different encoders.
[SimplePasswordEncoder](../../../ecourse.infrastructure.application/src/main/java/eapli/ecourse/infrastructure/authz/SimplePasswordHashEncoder.java) and [PasswordHashEncoder](../../../ecourse.infrastructure.application/src/main/java/eapli/ecourse/infrastructure/authz/PasswordHashEncoder.java).

The simple one is used for testing purposes and all of the downsides are listed in the class file.

**Task 04** - Sign up validations crash the application

This was a known bug that was fixed.

## Design

The framework already had a base implementation of the authentication and authorization system, so we did not implement it from scratch. There is a central component called `AuthenticationService`, which provides the authenticate method to verify a user identity. This service depends on other components such as the `AuthorizationService` which handles the user session, and the `PasswordPolicy`, which is responsible for checking if the password is according to the rules defined by the system.

The `SystemUser` entity is a user in the system.

The `Role` value object represents the user's role in the system, which determines its permissions.
To encode/decode passwords, the `AuthenticationService` relies on a `PasswordEncoder`.

The base implementation used a simple `PlainTextEncoder` that was not secure, so we decided to implement another 2 encoders:

- [SimplePasswordEncoder](../../../ecourse.infrastructure.application/src/main/java/eapli/ecourse/infrastructure/authz/SimplePasswordHashEncoder.java) - This encoder is used for testing purposes and all of the downsides are listed in the class file.
- [PasswordHashEncoder](../../../ecourse.infrastructure.application/src/main/java/eapli/ecourse/infrastructure/authz/PasswordHashEncoder.java) - This encoder is used in the application and is more secure.

### Applied patterns

#### Factory (GOF - Creational)

The factory pattern is used to create the encoders. This way we can easily change the encoder used in the application at any time using a simple configuration file.

### Tests

As we implemented 2 different encoders, we also implemented 2 different tests to ensure the differences between them.
The rest of the implementation was already done in the base project so we did not need to implement any more tests.

#### Password Hash Encoder

```java
 @Test
  public void ensureHashingIsOneWay() {
    final String password = "password";
    final String encodedPassword = encoder.encode(password);
    final String encodedPassword2 = encoder.encode(password);
    assertNotEquals(password, encodedPassword);
    assertNotEquals(encodedPassword, encodedPassword2);
    assertTrue(encoder.matches(password, encodedPassword));
    assertTrue(encoder.matches(password, encodedPassword2));
  }
```

```java
  @Test
  public void ensurePasswordIsEncodedAndMatches() {
    final String password = "password";
    final String encodedPassword = encoder.encode(password);
    assertNotEquals(password, encodedPassword);
    assertTrue(encoder.matches(password, encodedPassword));
  }
```

```java
  @Test
  public void ensurePasswordIsEncodedAndDoesNotMatch() {
    final String password = "password";
    final String encodedPassword = encoder.encode(password);
    assertNotEquals(password, encodedPassword);
    assertFalse(encoder.matches("wrongPassword", encodedPassword));
  }
```

#### Simple Password Hash Encoder

```java
  @Test
  public void ensurePasswordIsNotOneWay() {
    final String password = "password";
    final String encodedPassword = encoder.encode(password);
    final String encodedPassword2 = encoder.encode(password);
    assertEquals(encodedPassword, encodedPassword2);
    assertTrue(encoder.matches(password, encodedPassword));
    assertTrue(encoder.matches(password, encodedPassword2));
  }
```

## Implementation

We created the class `PasswordEncoderContext` to provide a way to change the encoder used in the application at any time using a simple configuration file.

We can get the current encoder using the `passwordHash()` method.

```java
PasswordEncoder passwordEncoder = PasswordEncoderContext.passwordHash();
```

Then, when setting up the application auth service:

```java
AuthzRegistry.configure(usersRepo, passwordPolicy, passwordEncoder);
```

## Observations

We also solved some known issues with the base application as mentioned before like:

- The sign up functionality that would cause the application to exit after registering a new user
- The sign up functionality that whenever a constraint was violated, the application would exit.
