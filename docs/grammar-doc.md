## 4. Design

### 4.1. Grammar

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

#### 4.1.1. Numerical

```txt
@start-question
  @type numerical;
  @question-body <body>;
  @correct-answer <correct-answer>;
  @accepted-error <accepted-error>;
@end-question;
```

**Note**: The score must be a real number with a maximum of 2 decimal places between 0 and 1.

#### 4.1.2. Short Answer

```txt
@start-question;
  @type short-answer;
  @question-body "<question-body>";

  @start-correct-answers
    {@correct-answer "<answer>" <score>;}
  @end-correct-answers;
@end-question;
```

#### 4.1.3. True/False

```txt
@start-question
  @type true-false;
  @question-body "<question-body>";
  @correct-answer true|false;
@end-question;
```

**Note**: The score must be a real number with a maximum of 2 decimal places between 0 and 1.
**Note 2**: The sum of the scores specified in the `@correct-answers` section must be 1.

#### 4.1.4. Multiple Choice (Multiple Answers)

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

#### 4.1.5. Multiple Choice (Single Answer)

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

#### 4.1.6. Matching

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

##### 4.1.7 Select Missing Words

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

#### 4.2. Example

You can check a pratical example with all the question types [here](../../../ecourse.core/src/main/java/eapli/ecourse/exammanagement/domain/grammars/Question/Question.txt).
