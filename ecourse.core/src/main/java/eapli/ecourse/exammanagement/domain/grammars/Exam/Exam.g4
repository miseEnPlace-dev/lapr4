grammar Exam;
start: exam;

exam: start_exam header sections end_exam;

sections:	section+;
section:	start_section header questions end_section;

questions: ;

header:			properties+;
properties:	title | description | feedback | grade | score;

title:				TITLE STRING EOI;
description:	DESCRIPTION STRING EOI;
feedback:			FEEDBACK FDB_GRD_TYPE EOI;
grade:				GRADE FDB_GRD_TYPE EOI;
score:				SCORE NUMBER EOI;

start_exam:	START_EXAM IDENTIFIER EOI;
end_exam:		END_EXAM EOI;

start_section:	START_SECTION IDENTIFIER EOI;
end_section:		END_SECTION EOI;

// ----- TOKENS -----

// End of instruction
EOI: ';';

// Chars wrapped in double quotes, allowing escaped quotes and backslash
STRING: '"' ( '\\' [\\"] | ~[\\"])* '"';

START_EXAM:			'@start-exam';
END_EXAM:				'@end-exam';
TITLE:					'@title';
DESCRIPTION:		'@description';
FEEDBACK:				'@feedback';
GRADE:					'@grade';
START_SECTION:	'@start-section';
END_SECTION:		'@end-section';
SCORE:					'@score';

// Feedback/Grade type
FDB_GRD_TYPE: 'none' | 'on-submit' | 'after-closing';

NUMBER:			[0-9]+;
IDENTIFIER:	[a-zA-Z][a-zA-Z0-9_]*;

// Skip spaces, tabs and newlines
WS: [ \t\n\r]+ -> skip;

// Skip comments
COMMENT: '//' ~[\r\n]* -> skip;
