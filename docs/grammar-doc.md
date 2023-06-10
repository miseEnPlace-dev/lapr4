# LPROG - eCourse - Grammar Documentation

## Table of Contents

- [LPROG - eCourse - Grammar Documentation](#lprog---ecourse---grammar-documentation)
  - [Table of Contents](#table-of-contents)
  - [Introduction](#introduction)
  - [Design](#design)
    - [Grammar](#grammar)
      - [1.1 Question Grammar](#11-question-grammar)
        - [1.1.1. Numerical](#111-numerical)
        - [1.1.2. Short Answer](#112-short-answer)
        - [1.1.3. True/False](#113-truefalse)
        - [1.1.4. Multiple Choice (Multiple Answers)](#114-multiple-choice-multiple-answers)
        - [1.1.5. Multiple Choice (Single Answer)](#115-multiple-choice-single-answer)
        - [1.1.6. Matching](#116-matching)
        - [1.1.7 Select Missing Words](#117-select-missing-words)
        - [1.2. Example](#12-example)
      - [2. Exam Grammar](#2-exam-grammar)

## Introduction

This document describes the grammar used to define the questions to be used in exams. As required, there are 6 types of questions:

- Numerical
- Short Answer
- True/False
- Multiple Choice (Multiple Answers)
- Multiple Choice (Single Answer)
- Matching
- Select Missing Words

The grammar was defined using the [ANTLR](https://www.antlr.org/) tool. ANTLR is a powerful parser generator for reading, processing, executing, or translating structured text or binary files. It's widely used to build languages, tools, and frameworks. From a grammar, ANTLR generates a parser that can build and walk parse trees.

## Design

### Grammar

We defined some custom tokens to keep the input files simple and readable.

All the files defining questions to be used in exams must follow the following structure:

```txt
@start-question
  @type numerical|multiple-choice|short-answer|true-false|matching|select-missing-words;
  @question-body <question-body>
  @correct-answer <correct-answer>;
@end-question;
```

This is the base structure. As there are many question types and each one has its own structure, we will define the structure of each one of them.

To make the understanding easier we will use the following notation:

- [] - optional
- <> - replace with the value (required)
- | - or
- {} - repeatable.

### 1.1 Question Grammar

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

#### 1.2. Example

You can check a pratical example with all the question types [here](../../../ecourse.core/src/main/java/eapli/ecourse/exammanagement/domain/grammars/Question/Question.txt).

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

The exam grammar is defined as follows:

```g4
grammar Exam;

start: exam;

exam: start_exam header sections end_exam;

sections:	section+;
section:	start_section header questions end_section;

questions: question+;

header:			properties+;
properties:	title | description | feedback_header | grade;

title:						TITLE STRING EOI;
description:			DESCRIPTION STRING EOI;
feedback_header:	FEEDBACK FDB_GRD_TYPE EOI;
grade:						GRADE FDB_GRD_TYPE EOI;

start_exam:	START_EXAM IDENTIFIER EOI;
end_exam:		END_EXAM EOI;

start_section:	START_SECTION IDENTIFIER EOI;
end_section:		END_SECTION EOI;

// ----- QUESTIONS -----

question:
	START_QUESTION TYPE (
		numericalQuestion
		| multipleChoiceQuestion
		| shortAnswerQuestion
		| trueFalseQuestion
		| matchingQuestion
		| missingWordsQuestion
	) END_QUESTION EOI;

numericalQuestion:
	'numerical' EOI score? body feedback? numericalCorrectAnswer numericalAcceptedError;

multipleChoiceQuestion:
	'multiple-choice' EOI score? body feedback? (
		START_CORRECT_ANSWERS_SECTION multipleChoiceCorrectAnswer+ END_CORRECT_ANSWERS_SECTION EOI
		| multipleChoiceCorrectAnswer
	) START_OPTIONS_SECTION option+ END_OPTIONS_SECTION EOI;

shortAnswerQuestion:
	'short-answer' EOI score? body feedback? START_CORRECT_ANSWERS_SECTION shortAnswerCorrectAnswer+
		END_CORRECT_ANSWERS_SECTION EOI;

trueFalseQuestion:
	'true-false' EOI score? body feedback? trueFalseCorrectAnswer;

matchingQuestion:
	'matching' EOI score? body feedback? START_CORRECT_ANSWERS_SECTION matchingCorrectAnswer+
		END_CORRECT_ANSWERS_SECTION EOI START_OPTIONS_SECTION option+ END_OPTIONS_SECTION EOI
		START_MATCHING_SECTION match+ END_MATCHING_SECTION EOI;

missingWordsQuestion:
	'missing-words' EOI score? body feedback? START_CORRECT_ANSWERS_SECTION missingWordsCorrectAnswer+
		END_CORRECT_ANSWERS_SECTION EOI START_OPTIONS_SECTION option+ END_OPTIONS_SECTION EOI;

body: QUESTION_BODY STRING EOI;

feedback: FEEDBACK STRING EOI;

score: SCORE REAL_NUMBER EOI;

shortAnswerCorrectAnswer: CORRECT_ANSWER STRING REAL_NUMBER EOI;

multipleChoiceCorrectAnswer:
	CORRECT_ANSWER NUMBER (REAL_NUMBER)? EOI
	| CORRECT_ANSWER NUMBER EOI;

numericalCorrectAnswer: CORRECT_ANSWER REAL_NUMBER EOI;

numericalAcceptedError: ACCEPTED_ERROR REAL_NUMBER EOI;

option: OPTION NUMBER STRING (STRING)? EOI;

match: MATCH NUMBER STRING EOI;

matchingCorrectAnswer: CORRECT_ANSWER NUMBER NUMBER EOI;

missingWordsCorrectAnswer: CORRECT_ANSWER STRING EOI;

trueFalseCorrectAnswer: CORRECT_ANSWER (TRUE | FALSE) EOI;

// ----- TOKENS -----

// End of instruction
EOI: ';';

// Chars wrapped in double quotes, allowing escaped quotes and backslash
STRING: '"' ( '\\' [\\"] | ~[\\"])* '"';

START_EXAM:											'@start-exam';
END_EXAM:												'@end-exam';
TITLE:													'@title';
DESCRIPTION:										'@description';
FEEDBACK:												'@feedback';
GRADE:													'@grade';
START_SECTION:									'@start-section';
END_SECTION:										'@end-section';
SCORE:													'@score';
START_QUESTION:									'@start-question';
END_QUESTION:										'@end-question';
TYPE:														'@type';
QUESTION_BODY:									'@question-body';
START_CORRECT_ANSWERS_SECTION:	'@start-correct-answers';
CORRECT_ANSWER:									'@correct-answer';
END_CORRECT_ANSWERS_SECTION:		'@end-correct-answers';
ACCEPTED_ERROR:									'@accepted-error';
START_OPTIONS_SECTION:					'@start-options';
END_OPTIONS_SECTION:						'@end-options';
OPTION:													'@option';
START_MATCHING_SECTION:					'@start-matching';
END_MATCHING_SECTION:						'@end-matching';
MATCH:													'@match';
TRUE:														'true';
FALSE:													'false';

// Feedback/Grade type
FDB_GRD_TYPE: 'none' | 'on-submit' | 'after-closing';

NUMBER:				[0-9]+;
REAL_NUMBER:	[0-9]+ ('.' [0-9]+)?;
IDENTIFIER:		[a-zA-Z][a-zA-Z0-9_]*;

// Skip spaces, tabs and newlines
WS: [ \t\n\r]+ -> skip;

// Skip comments
COMMENT: '//' ~[\r\n]* -> skip;
```
