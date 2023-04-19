# User Story 2001 - Create/Update Exam

> As Teacher, I want to create/update an exam.

|             |           |
| ----------- | --------- |
| ID          | 19        |
| Sprint      | B         |
| Application | 3 - Exams |
| Priority    | 1         |

## Acceptance Criteria

- This includes the specification of the exam (i.e., its structure, in accordance with a grammar for exams that is used to validate the specification of the exam).
- The support for exams (its design, feedback and grading) must follow specific technical requirements, specified in LPROG.
- The ANTLR tool should be used (<https://www.antlr.org/>).

## Requirements

- **FRE01** Create Exam - A Teacher creates a new exam. This includes the specification of the exam (i.e., its structure, in accordance with a grammar for exams that is used to validate the specification of the exam).

- **NFR09** Exam Language - The support for exams (its design, feedback and grading) must follow specific technical requirements, specified in LPROG. The ANTLR tool should be used (<https://www.antlr.org/>).

## Client Clarifications

- **Teachers** can create exams.
- An exam is related to a specific **course**.
- It has a unique **title** and a small **description**.
- Exams have also a **open** and a **close date**. The open date is the time when students can start to take the exam. The close date is the deadline for students to submit the exam.
- The system must provide a **language** to support the specification and "execution" of exams. The language must support the **design** of the exam layout and its questions as well as **solutions**, **feedback** and **grading**.

## Input and Output Data

### Input

- Course (Selected from a list of courses)
- Exam Structure (Text file)

### Output

- Success or error messages (from parser)

## Exam Structure

The exam structure is defined with a specific language, defined in the context of LPROG.
