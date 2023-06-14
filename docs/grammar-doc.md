# LPROG - eCourse - Grammar Documentation

## Table of Contents

- [LPROG - eCourse - Grammar Documentation](#lprog---ecourse---grammar-documentation)

  - [Table of Contents](#table-of-contents)
  - [Introduction](#introduction)
  - [Grammar](#grammar)

    - [1. Question Grammar](#11-question-grammar)

      - [1.1.1. Numerical](#111-numerical)
      - [1.1.2. Short Answer](#112-short-answer)
      - [1.1.3. True/False](#113-truefalse)
      - [1.1.4. Multiple Choice (Multiple Answers)](#114-multiple-choice-multiple-answers)
      - [1.1.5. Multiple Choice (Single Answer)](#115-multiple-choice-single-answer)
      - [1.1.6. Matching](#116-matching)
      - [1.1.7 Select Missing Words](#117-select-missing-words)

    - [1.2. Example](#12-example)

    - [2. Exam Grammar](#2-exam-grammar)

      - [2.1. Structure](#21-Structure)

        - [2.1.1. Exam Structure](#211-exam-structure)
        - [2.1.2. Header](#212-header)
        - [2.1.3. Sections](#213-sections)
        - [2.1.4. Questions](#214-questions)
        - [2.1.5. Correct Answers](#216-correct-answers)
        - [2.1.6. Options](#217-options)

      - [2.2. Example](#22-example)

    - [3. Formative Exam](#3-formative-exam)

      - [3.1. Structure](#31-Structure)

        - [3.1.1. Exam Structure](#311-exam-structure)
        - [3.1.2. Header](#312-header)
        - [3.1.3. Sections](#313-sections)

      - [3.2. Example](#32-example)

## Introduction

This document describes the grammar used to define the Questions to be used in Exams, as well as the grammar defined in the Exams. As required, there are 6 types of questions:

- Numerical
- Short Answer
- True/False
- Multiple Choice (Multiple Answers)
- Multiple Choice (Single Answer)
- Matching
- Select Missing Words

The grammar was defined using the [ANTLR](https://www.antlr.org/) tool. ANTLR is a powerful parser generator for reading, processing, executing, or translating structured text or binary files. It's widely used to build languages, tools, and frameworks. From a grammar, ANTLR generates a parser that can build and walk parse trees.

## Grammar

We defined some custom tokens to keep the input files simple and readable.

To make the understanding easier we will use the following notation:

- [] - optional
- <> - replace with the value (required)
- | - or
- {} - repeatable.

### 1.1 Question Grammar

All the files defining questions to be used in exams must follow the following structure:

```txt
@start-question
  @type numerical|multiple-choice|short-answer|true-false|matching|select-missing-words;
  @question-body <question-body>
  @correct-answer <correct-answer>;
@end-question;
```

This is the base structure. As there are many question types and each one has its own structure, we will define the structure of each one of them.

#### 1.1.1. Numerical

```txt
@start-question
  @type numerical;
  @question-body <body>;
  @correct-answer <correct-answer>;
  @accepted-error <accepted-error>;
@end-question;
```

**Note**: The score must be a real number with a maximum of 2 decimal places between 0 and 1.

#### 1.1.2. Short Answer

```txt
@start-question;
  @type short-answer;
  @question-body "<question-body>";

  @start-correct-answers
    {@correct-answer "<answer>" <score>;}
  @end-correct-answers;
@end-question;
```

#### 1.1.3. True/False

```txt
@start-question
  @type true-false;
  @question-body "<question-body>";
  @correct-answer true|false;
@end-question;
```

**Note**: The score must be a real number with a maximum of 2 decimal places between 0 and 1.
**Note 2**: The sum of the scores specified in the `@correct-answers` section must be 1.

#### 1.1.4. Multiple Choice (Multiple Answers)

```txt
@start-question
  @type multiple-choice;
  @question-body "<question-body>";
  [@feedback <feedback>;]

  @start-correct-answers
    {@correct-answer <id> <score>;}
  @end-correct-answers;

  @start-options
    {@option <id> "<answer>" ["feedback"];}
  @end-options;
@end-question;
```

#### 1.1.5. Multiple Choice (Single Answer)

```txt
@start-question
  @type multiple-choice;
  @question-body "<question-body>";
  [@feedback <feedback>;]
  @correct-answer <id>;

  @start-options
    {@option <id> ["feedback"];}
  @end-options;
@end-question;
```

#### 1.1.6. Matching

**Note:** We considered that there can be missing matches both in the options and in the matches.

```txt
@start-question
  @type matching;
  @question-body "<question-body>";
  [@feedback "<feedback>";]

  @start-correct-answers
    {@correct-answer <id> <id-match>;}
  @end-correct-answers;

  @start-options
    {@option <id> "<string>";}
  @end-options;

  @start-matching
    {@match <id-match> "<string>";}
  @end-matching;
@end-question;
```

#### 1.1.7 Select Missing Words

In this case the question body must have the placeholder for the missing words using '\_\_' (double underscore).
_E.g. "A \_\_ é um lugar onde se pode comer \_\_."_

In the correct answer section the words must be in the same order as they appear in the question body and must be placed inside double quotes separated by a space.
_E.g. "restaurante" "bem";_

**Note:** The number of correct answers must be equal to the number of placeholders.

```txt
@start-question
  @type missing-words
  @question-body "<question-body>";
  @correct-answer {"<word>" };

  @start-options
    {@option <value>;}
  @end-options;
@end-question;
```

### 1.2. Example

You can check a practical example with all the question types [here](../ecourse.core/src/main/java/eapli/ecourse/exammanagement/domain/grammars/Question/Question.txt).

## 2. Exam Grammar

All the files defining exams must follow the following structure:

```txt
@start-exam
  @title "<title>";
  @description "<description>";
  @feedback none|on-submit;
  @grade on-submit;

  @start-section
    {@title "<title>";}
    {@description "<description>";}

    @start-questions
      {@question <id>;}
    @end-questions;
  @end-section;
@end-exam;
```

**Note:** The score must be a real number with a maximum of 2 decimal places between 0 and 1.

**Note 2:** The sum of the scores specified in the `@correct-answers` section must be 1.

### 2.1. Structure

#### 2.1.1 Exam Structure:

The exam starts with the keyword **"@start-exam"** followed by an identifier and ends with the keyword **"@end-exam"**.
The exam consists of a header section and multiple sections.

#### 2.1.2 Header:

The header section provides information about the exam, such as the title, description, feedback type, and grading type.
It includes properties such as **"@title"**, **"@description"**, **"@feedback"**, and **"@grade"**.

#### 2.1.3 Sections:

Each section begins with the keyword **"@start-section"** followed by an identifier and ends with **"@end-section"**.
Sections contains a set of one or more questions.

#### 2.1.4 Questions:

The questions section is defined using the keyword **"@start-questions"** followed by a list of questions and ends with the keyword **"@end-questions"**. To see more in detail the structure of each question type, please check the [Question Grammar](#11-question-grammar).

#### 2.1.5 Correct Answers:

The correct answers section is defined using the keyword **"@start-correct-answers"** followed by a list of correct answers and ends with the keyword **"@end-correct-answers"**.
Each correct answer is defined using the keyword **"@correct-answer"** followed by the identifier of the correct answer and the score.

#### 2.1.6 Options:

The options section is defined using the keyword **"@start-options"** followed by a list of options and ends with the keyword **"@end-options"**.
Each option is defined using the keyword **"@option"** followed by the identifier of the option and the option text.

### 2.2. Example

You can check a practical example of an Exam [here](../ecourse.core/src/main/java/eapli/ecourse/exammanagement/domain/grammars/Exam/Exam.txt).

## 3. Formative Exam

All the files defining formative exams must follow the following structure:

```txt
@start-exam
  @title "<title>";
  @description "<description>";
  @feedback none|on-submit;
  @grade on-submit;
  @score <score>;

  @start-section
    {@title "<title>";}
    {@description "<description>";}
    {@number-of-questions <number>;}
    {@questions-type <type>;}
  @end-section;

@end-exam
```

**Note:** The score must be a real number with a maximum of 2 decimal places between 0 and 100.

### 3.1. Structure

#### 3.1.1 Exam Structure:

The exam starts with the keyword **"@start-exam"** followed by an identifier and ends with the keyword **"@end-exam"**.

#### 3.1.2 Header:

The header section provides information about the exam, such as the title, description, feedback type, grading type and score.

#### 3.1.3 Sections:

Each section begins with the keyword **"@start-section"** followed by an identifier and ends with **"@end-section"**.
Sections contain the number of questions and the type of questions.

### 3.2. Example

You can check a practical example of a Formative Exam [here](../ecourse.core/src/main/java/eapli/ecourse/exammanagement/domain/grammars/FormativeExam/FormativeExam.txt).
