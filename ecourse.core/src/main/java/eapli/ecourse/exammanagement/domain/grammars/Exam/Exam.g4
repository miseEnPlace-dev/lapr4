grammar Exam;

start: exam;

exam: start_exam header sections end_exam;

sections:	section+;
section:	start_section header questions end_section;

questions: question+;

header: properties+;
properties:
	title
	| description
	| feedback_header
	| grade
	| course;

title:						TITLE STRING EOI;
description:			DESCRIPTION STRING EOI;
feedback_header:	FEEDBACK FDB_GRD_TYPE EOI;
grade:						GRADE FDB_GRD_TYPE EOI;
course:						COURSE STRING EOI;

start_exam:	START_EXAM IDENTIFIER EOI;
end_exam:		END_EXAM EOI;

start_section:	START_SECTION IDENTIFIER EOI;
end_section:		END_SECTION EOI;

// ----- QUESTIONS -----

question:
	START_QUESTION question_type score body feedback? (
		numericalQuestion
		| multipleChoiceQuestion
		| shortAnswerQuestion
		| trueFalseQuestion
		| matchingQuestion
		| missingWordsQuestion
	) END_QUESTION EOI;

body:			QUESTION_BODY STRING EOI;
feedback:	FEEDBACK STRING EOI;
score:		SCORE REAL_NUMBER EOI;

question_type:
	TYPE (
		'numerical'
		| 'multiple-choice'
		| 'short-answer'
		| 'true-false'
		| 'matching'
		| 'missing-words'
	) EOI;

numericalQuestion:
	numericalCorrectAnswer numericalAcceptedError;

multipleChoiceQuestion:
	(
		START_CORRECT_ANSWERS_SECTION multipleChoiceCorrectAnswer+ END_CORRECT_ANSWERS_SECTION EOI
		| multipleChoiceCorrectAnswer
	) START_OPTIONS_SECTION option+ END_OPTIONS_SECTION EOI;

shortAnswerQuestion:
	START_CORRECT_ANSWERS_SECTION shortAnswerCorrectAnswer+ END_CORRECT_ANSWERS_SECTION EOI;

trueFalseQuestion: trueFalseCorrectAnswer;

matchingQuestion:
	START_CORRECT_ANSWERS_SECTION matchingCorrectAnswer+ END_CORRECT_ANSWERS_SECTION EOI
		START_OPTIONS_SECTION option+ END_OPTIONS_SECTION EOI START_MATCHING_SECTION match+
		END_MATCHING_SECTION EOI;

missingWordsQuestion: missingWordsCorrectAnswer EOI;

shortAnswerCorrectAnswer: CORRECT_ANSWER STRING REAL_NUMBER EOI;

multipleChoiceCorrectAnswer:
	CORRECT_ANSWER NUMBER (REAL_NUMBER)? EOI
	| CORRECT_ANSWER NUMBER EOI;

numericalCorrectAnswer: CORRECT_ANSWER REAL_NUMBER EOI;

numericalAcceptedError: ACCEPTED_ERROR REAL_NUMBER EOI;

option: OPTION NUMBER STRING (STRING)? EOI;

match: MATCH NUMBER STRING EOI;

matchingCorrectAnswer: CORRECT_ANSWER NUMBER NUMBER EOI;

missingWordsCorrectAnswer: CORRECT_ANSWER STRING (STRING)*;

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
COURSE:													'@course-code';
START_SECTION:									'@start-section';
END_SECTION:										'@end-section';
SCORE:													'@score';
START_QUESTION:									'@start-question';
END_QUESTION:										'@end-question';
TYPE:														'@type';
QUESTION_BODY:									'@question-body';
START_CORRECT_ANSWERS_SECTION:	'@correct-answers';
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
