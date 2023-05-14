grammar Question;

start: question+;

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
	'numerical' EOI body feedback? numericalCorrectAnswer numericalAcceptedError;

multipleChoiceQuestion:
	'multiple-choice' EOI body feedback? (
		START_CORRECT_ANSWERS_SECTION multipleChoiceCorrectAnswer+ END_CORRECT_ANSWERS_SECTION EOI
		| multipleChoiceCorrectAnswer
	) START_OPTIONS_SECTION option+ END_OPTIONS_SECTION EOI;

shortAnswerQuestion:
	'short-answer' EOI body feedback? START_CORRECT_ANSWERS_SECTION shortAnswerCorrectAnswer+
		END_CORRECT_ANSWERS_SECTION EOI;

trueFalseQuestion:
	'true-false' EOI body feedback? trueFalseCorrectAnswer;

matchingQuestion:
	'matching' EOI body feedback? START_CORRECT_ANSWERS_SECTION matchingCorrectAnswer+
		END_CORRECT_ANSWERS_SECTION EOI START_OPTIONS_SECTION option+ END_OPTIONS_SECTION EOI
		START_MATCHING_SECTION match+ END_MATCHING_SECTION EOI;

missingWordsQuestion:
	'missing-words' EOI body feedback? missingWordsCorrectAnswer EOI;

body: QUESTION_BODY ' ' STRING EOI;

feedback: FEEDBACK STRING EOI;

shortAnswerCorrectAnswer:
	CORRECT_ANSWER STRING ' ' REAL_NUMBER EOI;

multipleChoiceCorrectAnswer:
	CORRECT_ANSWER NUMBER (' ' REAL_NUMBER)? EOI
	| CORRECT_ANSWER NUMBER EOI;

numericalCorrectAnswer: CORRECT_ANSWER REAL_NUMBER EOI;

numericalAcceptedError: ACCEPTED_ERROR REAL_NUMBER EOI;

option: OPTION ' ' NUMBER ' ' STRING (' ' STRING)? EOI;

match: MATCH ' ' NUMBER ' ' STRING EOI;

matchingCorrectAnswer: CORRECT_ANSWER NUMBER ' ' NUMBER EOI;

missingWordsCorrectAnswer: CORRECT_ANSWER STRING (' ' STRING)*;

trueFalseCorrectAnswer: CORRECT_ANSWER (TRUE | FALSE) EOI;

// End of instruction
EOI: ';';

// Chars wrapped in double quotes, allowing escaped quotes and backslash
STRING: '"' ( '\\' [\\"] | ~[\\"])* '"';

NUMBER:				[0-9]+;
REAL_NUMBER:	[0-9]+ ('.' [0-9]+)?;

START_QUESTION:									'@start-question';
END_QUESTION:										'@end-question';
TYPE:														'@type ';
QUESTION_BODY:									'@question-body';
START_CORRECT_ANSWERS_SECTION:	'@correct-answers';
CORRECT_ANSWER:									'@correct-answer ';
END_CORRECT_ANSWERS_SECTION:		'@end-correct-answers';
ACCEPTED_ERROR:									'@accepted-error ';
FEEDBACK:												'@feedback ';
START_OPTIONS_SECTION:					'@start-options';
END_OPTIONS_SECTION:						'@end-options';
OPTION:													'@option';
START_MATCHING_SECTION:					'@start-matching';
END_MATCHING_SECTION:						'@end-matching';
MATCH:													'@match';
TRUE:														'true';
FALSE:													'false';

// Skip spaces, tabs and newlines
WS: [ \t\n\r]+ -> skip;

// Skip comments
COMMENT: '//' ~[\r\n]* -> skip;
