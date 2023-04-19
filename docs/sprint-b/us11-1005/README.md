# User Story 1005 - Set Course Teachers

> As Manager, I want to set the teachers of a course.

|             |             |
| ----------- | ----------- |
| ID          | 11          |
| Sprint      | B           |
| Application | 2 - Courses |
| Priority    | 1           |

## Initial Remarks

This US is divided in two parts/features:

- Set all teachers;
- Set teacher in charge.

In order to select the teacher in charge, the manager must first define the teachers of the course.

## Acceptance Criteria

- N/A.

## Requirements

- **FRC04** Set Course Teachers - Each course may have several teachers and must have only one Teacher in Charge. Only managers are able to execute this functionality.

## Client Clarifications

- **Managers** can set the teachers of a course.
- There is only one **Teacher in Charge** per course.

## Input and Output Data

### Input

- Course (Selected from a list of courses)
- Teacher (Selected from a list of teachers - if defining the teacher in charge, the teachers listed are the ones that were already defined for the course)

### Output

- Success or error
