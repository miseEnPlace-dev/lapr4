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
		END_CORRECT_ANSWERS_SECTION EOI START_OPTIONS_SECTION missingWordsOption+ END_OPTIONS_SECTION
		EOI;

body: QUESTION_BODY STRING EOI;

feedback: FEEDBACK STRING EOI;

score: SCORE NUMBER EOI;

shortAnswerCorrectAnswer: CORRECT_ANSWER STRING NUMBER EOI;

multipleChoiceCorrectAnswer:
	CORRECT_ANSWER NUMBER (NUMBER)? EOI;

numericalCorrectAnswer: CORRECT_ANSWER NUMBER EOI;

numericalAcceptedError: ACCEPTED_ERROR NUMBER EOI;

option: OPTION NUMBER STRING (STRING)? EOI;

missingWordsOption: OPTION STRING EOI;

match: MATCH NUMBER STRING EOI;

matchingCorrectAnswer: CORRECT_ANSWER NUMBER '-' NUMBER EOI;

missingWordsCorrectAnswer: CORRECT_ANSWER STRING EOI;

trueFalseCorrectAnswer: CORRECT_ANSWER (TRUE | FALSE) EOI;

// ----- TOKENS -----

// End of instruction
EOI: ';';

// Chars wrapped in double quotes, allowing escaped double quotes, backslashes and newlines
STRING:
	'"' ('\\' [\\"] | ~[\\"])* '"'
	| '"' ( '\\' [\\n] | ~[\\"])* '"';

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

NUMBER:				REAL_NUMBER | INTEGER;
REAL_NUMBER:	[0-9]+ '.' [0-9]+;
INTEGER:			[0-9]+;
IDENTIFIER:		[a-zA-Z][a-zA-Z0-9_]*;

// Skip spaces, tabs and newlines
WS: [ \t\n\r]+ -> skip;

// Skip comments
COMMENT: '//' ~[\r\n]* -> skip;
