# User Story 2008 - Create/Update Automatic Formative Exams

|             |           |
| ----------- | --------- |
| ID          | 21        |
| Sprint      | B         |
| Application | 3 - Exams |
| Priority    | 5         |

---

## 1. Context

This is the first time this task is assigned to be developed. This is a new functionality that is needed to create new exams, using a specific language defined by the team.

---

## 2. Requirements

### "As Teacher, I want to create/update automatic formative exams."

## 2.1. Client Specifications

- "**Teachers** can create exams."
- "An exam is related to a specific **course**."
- "It has a unique **title** and a small **description**."
- "Exams have also a **open** and a **close date**. The open date is the time when students can start to take the exam. The close date is the deadline for students to submit the exam."
- "The system must provide a **language** to support the specification and "execution" of exams. The language must support the **design** of the exam layout and its questions as well as **solutions**, **feedback** and **grading**."

## 2.2. Client Clarifications

> [**Question 2**](../../client-clarifications.md#question-2): As for the exam, is the title written in the exam the same as its unique title to identify?
> \
> **Answer**: "The title should be unique. Maybe it could be used to identify the exam."

> [**Question 14**](../../client-clarifications.md#question-14): Can the same question be used in different exams? Or are the questions made for a specific exam?
> \
> **Answer**: "There is no "database" of questions (no identity)"

## 2.3. Functional Requirements

> **FRE01** Create Exam - A Teacher creates a new exam. This includes the specification of the exam (i.e., its structure, in accordance with a grammar for exams that is used to validate the specification of the exam).

## 2.4. Non-Functional Requirements

> **NFR09** Exam Language - The support for exams (its design, feedback and grading) must follow specific technical requirements, specified in LPROG. The ANTLR tool should be used (<https://www.antlr.org/>).

## 2.5. Acceptance Criteria

> **AC.1**: The specification of formative exams is similar to regular exams, but the user specifies the type of questions to be inserted in the sections instead of the specific questions.

> **AC.2**: When generating the automatic formative exam, the system should randomly create the questions (without repetition on a given exam).

> **AC.3**: Also, formative exams do not have open and close dates. Feedback and grades are only provided at the end of the exam.

> **AC.4**: The support for exams (its design, feedback and grading) must follow specific technical requirements, specified in LPROG.

> **AC.5**: The ANTLR tool should be used (<https://www.antlr.org/>).

---

## 3. Analysis

### 3.1. Input and Output Data

#### Input

- Exam Structure (Text file)

#### Output

- Success or error messages (from parser)

## 4. Implementation

- Parser from a file

```java
  public FormativeExamRequestBuilder parseFromFile(String filePath) throws IOException, ParseException {
    FormativeExamLexer lexer = new FormativeExamLexer(CharStreams.fromFileName(filePath));
    CommonTokenStream tokens = new CommonTokenStream(lexer);
    FormativeExamParser parser = new FormativeExamParser(tokens);
    ParseTree tree = parser.start();

    if (parser.getNumberOfSyntaxErrors() > 0)
      throw new ParseException();

    FormativeExamBuilderVisitor eval = new FormativeExamBuilderVisitor();
    return (FormativeExamRequestBuilder) eval.visit(tree);
  }
```

## 5. Observations

N/a
