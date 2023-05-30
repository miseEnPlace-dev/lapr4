# Exam Creation Language

## 1. Introduction

### 1.1. Purpose

The purpose of this document is to describe the Exam Creation Language and its syntax.
With this language, it is possible to create evaluation and formative exams for the eCourse platform, with automatic grading and feedback.

### 1.2. Technical Context

This language was created for the LPROG course to define the structure of the exams and their questions, as well as the correct answers, feedback and grading info.
The text file written by the teacher is then parsed and validated by the eCourse platform, which will then create the exam and its questions. To parse the exam, an ANTLR4 grammar was developed, which is available in the [this file](/ecourse.core/src/main/java/eapli/ecourse/exammanagement/domain/grammars/Exam/Exam.g4).

### 1.3. Exam Structure

An exam has a specific structure: it starts with a header, that describes it's title, description, total score, and feedback and grade information option.
Then, a set of sections are defined, each one containing a number of questions. A section has a total score, that is then equally distributed by all questions of that section.

## 2. Syntax

The syntax of the exam is based in a tag system: the attributes of the exam/section/question starts with a tag (ex.: @title), followed by the content of the attribute (varies from attribute to attribute), and **ends with a mandatory semi-column**.

### 2.1. Exam

The exam syntax follows the below structure:

```
@start-exam <exam_identifier>;
    <header>

    <sections>
@end-exam;
```

The exam starts with the `@start-exam` tag. It's then followed by an identifier.
Then, the header is defined, followed by the definition of all sections.
It ends with the  `@end-exam` tag.

- **exam_identifier**: (mandatory) the name that identifies the exam. Must start with a letter, followed by any sequence of letters, numbers, or underscores. Example: `exam_3_Math`;
- **header**: (mandatory) the header definition. Syntax defined in the [Header](#22-header) section;
- **sections**: (mandatory) the definition of all sections. Syntax defined in the [Sections](##23-sections) section.

### 2.2. Header

The header defines the proprieties of the exam: title, description, feedback and grade information option, and total score.
The proprieties can be defined in any order.

- **title**: (mandatory) the title of the exam. Starts with the `@title` tag, followed by a string between quotes. Example: `@title "Exam Nº3 of Mathematics";`;
- **description**: (optional) the description of the exam. Starts with the `@description` tag, followed by a string between quotes. Example: `@description "This is the third exam of the Mathematics course.";`;
- **score**: (mandatory) the total score of the exam. Starts with the `@score` tag, followed by a number. Example: `@score 100;`. Make sure that the sum of all sections' scores is the same as the exam score;
- **feedback**: (mandatory) describes whether the questions' feedback must appear on submission, on exam close, or not shown at all. Starts with the `@feedback` tag, followed by any of the follow options: `none`, `on-submit` or `on-close`. Example: `@feedback on-submit;`;
- **grade**: (mandatory) describes whether the exam's grade must appear on submission, on exam close, or not shown at all. Starts with the `@grade` tag, followed by any of the follow options: `none`, `on-submit` or `on-close`. Example: `@grade on-submit;`.

### 2.3. Sections

The sections of the exam contains a number of questions. A exam must contain, at least, one section.

The section syntax follows the below structure:

```
@start-section <section_identifier>;
    <header>

    <questions>
@end-section;
```

The section starts with the `@start-section` tag. It's then followed by an identifier.
Then, the header is defined, followed by the definition of all questions.
It ends with the  `@end-section` tag.

- **section_identifier**: (mandatory) the name that identifies the section. Must start with a letter, followed by any sequence of letters, numbers, or underscores. Example: `statistics_1`;
- **header**: (mandatory) defines all proprieties of the section, which are described below:
    - **title**: (mandatory) the title of the section. Starts with the `@title` tag, followed by a string between quotes. Example: `@title "Statistics 1";`;
    - **description**: (optional) the description of the section. Starts with the `@description` tag, followed by a string between quotes. Example: `@description "This section is about Statistics.";`;
    - **score**: (mandatory) the total score of the section. Starts with the `@score` tag, followed by a number. Example: `@score 25;`. Make sure that the sum of all sections' scores is the same as the exam score;
- **questions**: (mandatory) defines the questions of the section. The questions' syntax can be consulted in [this](/docs/sprint-b/us20-2007/README.md) document.

## 4. Exam Example

The following text represents an exam defined by this language.

```java
@start-exam exame_de_exemplo;
    @score 100;
    @title "Exame de Exemplo";
    @description "Exame de exemplo para testes.";
    @feedback none;
    @grade on-submit;

    @start-section section1;
        @title "Título da Primeira Secção";
        @description "Descrição da Secção";
        @score 80;

        @start-question
            @type short-answer;
            @question-body "Em que UC da LEI se aprende a programar em Java?";
            @correct-answers
                @correct-answer "APROG" 1.0;
                @correct-answer "Algoritmia e Programação" 1.0;
                @correct-answer "PPROG" 0.5;
                @correct-answer "Paradigmas da Programação" 0.5;
            @end-correct-answers;
        @end-question;
    @end-section;

    @start-section section2;
        // Para além de serem permitidos
        // comentários, as propriedades podem
        // ser definidas em qualquer ordem.
        @description "Descrição da Secção";
        @score 20;
        @title "Título da Segunda Secção";

        @start-question
            @type true-false;
            @question-body "Em ESINF programa-se em Python.";
            @correct-answer false;
        @end-question;
    @end-section;
@end-exam;
```
