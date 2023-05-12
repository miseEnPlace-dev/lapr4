grammar Question;

start: question+;

question:
  START_QUESTION TYPE (numerical_question | multiple_choice_question | short_answer_question | true_false_question | matching_question | missing_words_question) END_QUESTION EOI;

numerical_question:
  'numerical' EOI body feedback? CORRECT_ANSWER REAL_NUMBER EOI ACCEPTED_ERROR REAL_NUMBER EOI;

multiple_choice_question:
  'multiple-choice' EOI body feedback? START_CORRECT_ANSWERS_SECTION multiple_choice_correct_answer+ END_CORRECT_ANSWERS_SECTION EOI START_OPTIONS_SECTION multiple_choice_option+ END_OPTIONS_SECTION EOI;

short_answer_question:
  'short-answer' EOI body feedback? START_CORRECT_ANSWERS_SECTION short_answer_correct_answer+ END_CORRECT_ANSWERS_SECTION EOI;

true_false_question:
  'true-false' EOI body feedback? CORRECT_ANSWER ('true' | 'false') EOI;

matching_question:
  'matching' EOI body feedback? EOI;

missing_words_question:
  'missing-words' EOI body feedback? EOI;

body: QUESTION_BODY ' ' STRING EOI;

feedback: FEEDBACK STRING EOI;

short_answer_correct_answer:
  CORRECT_ANSWER STRING ' ' REAL_NUMBER EOI;

multiple_choice_correct_answer:
  CORRECT_ANSWER ' ' ID ' ' STRING ' ' WEIGTH EOI;

multiple_choice_option:
  ID ' ' STRING ' ' STRING? EOI;

// End of instruction
EOI: ';';

// Chars wrapped in double quotes, allowing escaped quotes and backslash
STRING: '"' ( '\\' [\\"] | ~[\\"])* '"';

REAL_NUMBER: [0-9]+ ('.' [0-9]+)?;
ID: [0-9];

// number between 0 and 1
WEIGTH: '0' | '1' | '0.' [0-9]+;

START_QUESTION: '@start-question';
END_QUESTION: '@end-question';
TYPE: '@type ';
QUESTION_BODY: '@question-body';
START_CORRECT_ANSWERS_SECTION: '@correct-answers';
END_CORRECT_ANSWERS_SECTION: '@end-correct-answers';
CORRECT_ANSWER: '@correct-answer ';
ACCEPTED_ERROR: '@accepted-error ';
FEEDBACK: '@feedback ';
START_OPTIONS_SECTION: '@options';
END_OPTIONS_SECTION: '@end-options';
OPTION: '@option';
START_MATCHING_SECTION: '@start-matching';
END_MATCHING_SECTION: '@end-matching';
MATCH: '@match';

// Skip spaces, tabs and newlines
WS: [ \t\n\r]+ -> skip;

// Skip comments
COMMENT: '//' ~[\r\n]* -> skip;
