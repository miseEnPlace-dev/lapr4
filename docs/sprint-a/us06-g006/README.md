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

## Related Tasks

**Task 02** - Make sign up go back to main menu instead of existing the app bug

This was a known bug that was fixed.

**Task 03** - Implement password hashing

The base program used a PlainTextEncoder that was not secure.
We implemented 2 different encoders.
[SimplePasswordEncoder](../../../ecourse.infrastructure.application/src/main/java/eapli/ecourse/infrastructure/authz/SimplePasswordHashEncoder.java) and [PasswordHashEncoder](../../../ecourse.infrastructure.application/src/main/java/eapli/ecourse/infrastructure/authz/PasswordHashEncoder.java).

The simple one is used for testing purposes and all of the downsides are listed in the class file.

## Sequence diagram (simplified)

[Sequence diagram](assets/out/SD.svg)

## Tests

Write about the tests that were done to validate the implementation.

## Implementation

Write about the useful details of the implementation of the user story.
