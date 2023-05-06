grammar Exam;
start: exam;

exam:
	START_EXAM IDENTIFIER EOI exam_header sections END_EXAM EOI;

exam_header:
	title EOI (description EOI)? feedback EOI grade EOI;

title: TITLE STRING;
name: NAME STRING;
description: DESCRIPTION STRING;
feedback: FEEDBACK FDB_GRD_TYPE;
grade: GRADE FDB_GRD_TYPE;

sections: section+;

section:
	START_SECTION IDENTIFIER EOI section_header questions END_SECTION EOI;

section_header: name EOI (description EOI)?;

questions:;

// ----- TOKENS ----- Don't change the order of the tokens!!!

// End of instruction
EOI: ';';

// Chars wrapped in double quotes, allowing escaped quotes and backslash
STRING: '"' ( '\\' [\\"] | ~[\\"])* '"';

START_EXAM: '@start-exam';
END_EXAM: '@end-exam';
TITLE: '@title';
DESCRIPTION: '@description';
FEEDBACK: '@feedback';
GRADE: '@grade';
START_SECTION: '@start-section';
END_SECTION: '@end-section';
NAME: '@name';

// Feedback/Grade type
FDB_GRD_TYPE: 'none' | 'on-submit' | 'after-closing';

NUMBER: [0-9]+;
IDENTIFIER: [a-zA-Z][a-zA-Z0-9_]*;

// Skip spaces, tabs and newlines 
WS: [ \t\n\r]+ -> skip;

// Skip comments 
COMMENT: '//' ~[\r\n]* -> skip;

// WORD: [\p{L}]+;