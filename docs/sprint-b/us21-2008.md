# User Story 2008 - Create/Update Automatic Formative Exams

> As Teacher, I want to create/update automatic formative exams.

|             |           |
| ----------- | --------- |
| ID          | 21        |
| Sprint      | B         |
| Application | 3 - Exams |
| Priority    | 5         |

## Acceptance Criteria

- The specification of formative exams is similar to regular exams, but the user specifies the type of questions to be inserted in the sections instead of the specific questions.
- When generating the automatic formative exam, the system should randomly create the questions (without repetition on a given exam).
- Also, formative exams do not have open and close dates. Feedback and grades are only provided at the end of the exam.
- The support for exams (its design, feedback and grading) must follow specific technical requirements, specified in LPROG.
- The ANTLR tool should be used (<https://www.antlr.org/>).

## Requirements

- **FRE01** Create Exam - A Teacher creates a new exam. This includes the specification of the exam (i.e., its structure, in accordance with a grammar for exams that is used to validate the specification of the exam).

- **NFR09** Exam Language - The support for exams (its design, feedback and grading) must follow specific technical requirements, specified in LPROG. The ANTLR tool should be used (<https://www.antlr.org/>).
