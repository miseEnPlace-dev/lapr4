grammar FormativeExam;

start: exam;

exam: startExam header sections endExam;

sections: section+;
section:
	startSection header numberOfQuestions questionsType endSection;

header:							properties+;
properties:					title | description | feedback | grade | score;
numberOfQuestions:	NUMBER_OF_QUESTIONS NUMBER EOI;
questionsType:
	QUESTIONS_TYPE (
		'numerical'
		| 'short-answer'
		| 'multiple-choice'
		| 'true-false'
		| 'matching'
		| 'missing-words'
	) EOI;

title:				TITLE STRING EOI;
description:	DESCRIPTION STRING EOI;
feedback:			FEEDBACK FDB_GRD_TYPE EOI;
grade:				GRADE FDB_GRD_TYPE EOI;
score:				SCORE NUMBER EOI;

startExam:	START_EXAM IDENTIFIER EOI;
endExam:		END_EXAM EOI;

startSection:	START_SECTION IDENTIFIER EOI;
endSection:		END_SECTION EOI;

// ----- TOKENS -----

// End of instruction
EOI: ';';

// Chars wrapped in double quotes, allowing escaped quotes and backslash
STRING: '"' ( '\\' [\\"] | ~[\\"])* '"';

START_EXAM:						'@start-exam';
END_EXAM:							'@end-exam';
TITLE:								'@title';
DESCRIPTION:					'@description';
FEEDBACK:							'@feedback';
START_SECTION:				'@start-section';
END_SECTION:					'@end-section';
NUMBER_OF_QUESTIONS:	'@number-of-questions';
QUESTIONS_TYPE:				'@questions-type';
SCORE:								'@score';
GRADE:								'@grade';

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
