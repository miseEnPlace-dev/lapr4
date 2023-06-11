// Generated from /home/drew/Faculdade/LAPR4/sem4pi-22-23-19/ecourse.core/src/main/java/eapli/ecourse/exammanagement/domain/grammars/Question/Question.g4 by ANTLR 4.9.2
package eapli.ecourse.exammanagement.domain.parsers;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QuestionParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, EOI=7, STRING=8, NUMBER=9, 
		REAL_NUMBER=10, INTEGER=11, START_QUESTION=12, END_QUESTION=13, TYPE=14, 
		QUESTION_BODY=15, START_CORRECT_ANSWERS_SECTION=16, CORRECT_ANSWER=17, 
		END_CORRECT_ANSWERS_SECTION=18, ACCEPTED_ERROR=19, FEEDBACK=20, START_OPTIONS_SECTION=21, 
		END_OPTIONS_SECTION=22, OPTION=23, SCORE=24, START_MATCHING_SECTION=25, 
		END_MATCHING_SECTION=26, MATCH=27, TRUE=28, FALSE=29, WS=30, COMMENT=31;
	public static final int
		RULE_start = 0, RULE_question = 1, RULE_numericalQuestion = 2, RULE_multipleChoiceQuestion = 3, 
		RULE_shortAnswerQuestion = 4, RULE_trueFalseQuestion = 5, RULE_matchingQuestion = 6, 
		RULE_missingWordsQuestion = 7, RULE_body = 8, RULE_feedback = 9, RULE_score = 10, 
		RULE_shortAnswerCorrectAnswer = 11, RULE_multipleChoiceCorrectAnswer = 12, 
		RULE_numericalCorrectAnswer = 13, RULE_numericalAcceptedError = 14, RULE_option = 15, 
		RULE_missingWordsOption = 16, RULE_match = 17, RULE_matchingCorrectAnswer = 18, 
		RULE_missingWordsCorrectAnswer = 19, RULE_trueFalseCorrectAnswer = 20;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "question", "numericalQuestion", "multipleChoiceQuestion", "shortAnswerQuestion", 
			"trueFalseQuestion", "matchingQuestion", "missingWordsQuestion", "body", 
			"feedback", "score", "shortAnswerCorrectAnswer", "multipleChoiceCorrectAnswer", 
			"numericalCorrectAnswer", "numericalAcceptedError", "option", "missingWordsOption", 
			"match", "matchingCorrectAnswer", "missingWordsCorrectAnswer", "trueFalseCorrectAnswer"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'numerical'", "'multiple-choice'", "'short-answer'", "'true-false'", 
			"'matching'", "'missing-words'", "';'", null, null, null, null, "'@start-question'", 
			"'@end-question'", "'@type'", "'@question-body'", "'@start-correct-answers'", 
			"'@correct-answer'", "'@end-correct-answers'", "'@accepted-error'", "'@feedback'", 
			"'@start-options'", "'@end-options'", "'@option'", "'@score'", "'@start-matching'", 
			"'@end-matching'", "'@match'", "'true'", "'false'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, "EOI", "STRING", "NUMBER", 
			"REAL_NUMBER", "INTEGER", "START_QUESTION", "END_QUESTION", "TYPE", "QUESTION_BODY", 
			"START_CORRECT_ANSWERS_SECTION", "CORRECT_ANSWER", "END_CORRECT_ANSWERS_SECTION", 
			"ACCEPTED_ERROR", "FEEDBACK", "START_OPTIONS_SECTION", "END_OPTIONS_SECTION", 
			"OPTION", "SCORE", "START_MATCHING_SECTION", "END_MATCHING_SECTION", 
			"MATCH", "TRUE", "FALSE", "WS", "COMMENT"
		};
	}
	private static final String[] _SYMBOLIC_NAMES = makeSymbolicNames();
	public static final Vocabulary VOCABULARY = new VocabularyImpl(_LITERAL_NAMES, _SYMBOLIC_NAMES);

	/**
	 * @deprecated Use {@link #VOCABULARY} instead.
	 */
	@Deprecated
	public static final String[] tokenNames;
	static {
		tokenNames = new String[_SYMBOLIC_NAMES.length];
		for (int i = 0; i < tokenNames.length; i++) {
			tokenNames[i] = VOCABULARY.getLiteralName(i);
			if (tokenNames[i] == null) {
				tokenNames[i] = VOCABULARY.getSymbolicName(i);
			}

			if (tokenNames[i] == null) {
				tokenNames[i] = "<INVALID>";
			}
		}
	}

	@Override
	@Deprecated
	public String[] getTokenNames() {
		return tokenNames;
	}

	@Override

	public Vocabulary getVocabulary() {
		return VOCABULARY;
	}

	@Override
	public String getGrammarFileName() { return "Question.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public QuestionParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class StartContext extends ParserRuleContext {
		public List<QuestionContext> question() {
			return getRuleContexts(QuestionContext.class);
		}
		public QuestionContext question(int i) {
			return getRuleContext(QuestionContext.class,i);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitStart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(43); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(42);
				question();
				}
				}
				setState(45); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==START_QUESTION );
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class QuestionContext extends ParserRuleContext {
		public TerminalNode START_QUESTION() { return getToken(QuestionParser.START_QUESTION, 0); }
		public TerminalNode TYPE() { return getToken(QuestionParser.TYPE, 0); }
		public TerminalNode END_QUESTION() { return getToken(QuestionParser.END_QUESTION, 0); }
		public TerminalNode EOI() { return getToken(QuestionParser.EOI, 0); }
		public NumericalQuestionContext numericalQuestion() {
			return getRuleContext(NumericalQuestionContext.class,0);
		}
		public MultipleChoiceQuestionContext multipleChoiceQuestion() {
			return getRuleContext(MultipleChoiceQuestionContext.class,0);
		}
		public ShortAnswerQuestionContext shortAnswerQuestion() {
			return getRuleContext(ShortAnswerQuestionContext.class,0);
		}
		public TrueFalseQuestionContext trueFalseQuestion() {
			return getRuleContext(TrueFalseQuestionContext.class,0);
		}
		public MatchingQuestionContext matchingQuestion() {
			return getRuleContext(MatchingQuestionContext.class,0);
		}
		public MissingWordsQuestionContext missingWordsQuestion() {
			return getRuleContext(MissingWordsQuestionContext.class,0);
		}
		public QuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_question);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			match(START_QUESTION);
			setState(48);
			match(TYPE);
			setState(55);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				{
				setState(49);
				numericalQuestion();
				}
				break;
			case T__1:
				{
				setState(50);
				multipleChoiceQuestion();
				}
				break;
			case T__2:
				{
				setState(51);
				shortAnswerQuestion();
				}
				break;
			case T__3:
				{
				setState(52);
				trueFalseQuestion();
				}
				break;
			case T__4:
				{
				setState(53);
				matchingQuestion();
				}
				break;
			case T__5:
				{
				setState(54);
				missingWordsQuestion();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(57);
			match(END_QUESTION);
			setState(58);
			match(EOI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumericalQuestionContext extends ParserRuleContext {
		public TerminalNode EOI() { return getToken(QuestionParser.EOI, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public NumericalCorrectAnswerContext numericalCorrectAnswer() {
			return getRuleContext(NumericalCorrectAnswerContext.class,0);
		}
		public NumericalAcceptedErrorContext numericalAcceptedError() {
			return getRuleContext(NumericalAcceptedErrorContext.class,0);
		}
		public ScoreContext score() {
			return getRuleContext(ScoreContext.class,0);
		}
		public FeedbackContext feedback() {
			return getRuleContext(FeedbackContext.class,0);
		}
		public NumericalQuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numericalQuestion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterNumericalQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitNumericalQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitNumericalQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumericalQuestionContext numericalQuestion() throws RecognitionException {
		NumericalQuestionContext _localctx = new NumericalQuestionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_numericalQuestion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(60);
			match(T__0);
			setState(61);
			match(EOI);
			setState(63);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SCORE) {
				{
				setState(62);
				score();
				}
			}

			setState(65);
			body();
			setState(67);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FEEDBACK) {
				{
				setState(66);
				feedback();
				}
			}

			setState(69);
			numericalCorrectAnswer();
			setState(70);
			numericalAcceptedError();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultipleChoiceQuestionContext extends ParserRuleContext {
		public List<TerminalNode> EOI() { return getTokens(QuestionParser.EOI); }
		public TerminalNode EOI(int i) {
			return getToken(QuestionParser.EOI, i);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public TerminalNode START_OPTIONS_SECTION() { return getToken(QuestionParser.START_OPTIONS_SECTION, 0); }
		public TerminalNode END_OPTIONS_SECTION() { return getToken(QuestionParser.END_OPTIONS_SECTION, 0); }
		public TerminalNode START_CORRECT_ANSWERS_SECTION() { return getToken(QuestionParser.START_CORRECT_ANSWERS_SECTION, 0); }
		public TerminalNode END_CORRECT_ANSWERS_SECTION() { return getToken(QuestionParser.END_CORRECT_ANSWERS_SECTION, 0); }
		public List<MultipleChoiceCorrectAnswerContext> multipleChoiceCorrectAnswer() {
			return getRuleContexts(MultipleChoiceCorrectAnswerContext.class);
		}
		public MultipleChoiceCorrectAnswerContext multipleChoiceCorrectAnswer(int i) {
			return getRuleContext(MultipleChoiceCorrectAnswerContext.class,i);
		}
		public ScoreContext score() {
			return getRuleContext(ScoreContext.class,0);
		}
		public FeedbackContext feedback() {
			return getRuleContext(FeedbackContext.class,0);
		}
		public List<OptionContext> option() {
			return getRuleContexts(OptionContext.class);
		}
		public OptionContext option(int i) {
			return getRuleContext(OptionContext.class,i);
		}
		public MultipleChoiceQuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multipleChoiceQuestion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterMultipleChoiceQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitMultipleChoiceQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitMultipleChoiceQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultipleChoiceQuestionContext multipleChoiceQuestion() throws RecognitionException {
		MultipleChoiceQuestionContext _localctx = new MultipleChoiceQuestionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_multipleChoiceQuestion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(T__1);
			setState(73);
			match(EOI);
			setState(75);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SCORE) {
				{
				setState(74);
				score();
				}
			}

			setState(77);
			body();
			setState(79);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FEEDBACK) {
				{
				setState(78);
				feedback();
				}
			}

			setState(91);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case START_CORRECT_ANSWERS_SECTION:
				{
				setState(81);
				match(START_CORRECT_ANSWERS_SECTION);
				setState(83); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(82);
					multipleChoiceCorrectAnswer();
					}
					}
					setState(85); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==CORRECT_ANSWER );
				setState(87);
				match(END_CORRECT_ANSWERS_SECTION);
				setState(88);
				match(EOI);
				}
				break;
			case CORRECT_ANSWER:
				{
				setState(90);
				multipleChoiceCorrectAnswer();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(93);
			match(START_OPTIONS_SECTION);
			setState(95); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(94);
				option();
				}
				}
				setState(97); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==OPTION );
			setState(99);
			match(END_OPTIONS_SECTION);
			setState(100);
			match(EOI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ShortAnswerQuestionContext extends ParserRuleContext {
		public List<TerminalNode> EOI() { return getTokens(QuestionParser.EOI); }
		public TerminalNode EOI(int i) {
			return getToken(QuestionParser.EOI, i);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public TerminalNode START_CORRECT_ANSWERS_SECTION() { return getToken(QuestionParser.START_CORRECT_ANSWERS_SECTION, 0); }
		public TerminalNode END_CORRECT_ANSWERS_SECTION() { return getToken(QuestionParser.END_CORRECT_ANSWERS_SECTION, 0); }
		public ScoreContext score() {
			return getRuleContext(ScoreContext.class,0);
		}
		public FeedbackContext feedback() {
			return getRuleContext(FeedbackContext.class,0);
		}
		public List<ShortAnswerCorrectAnswerContext> shortAnswerCorrectAnswer() {
			return getRuleContexts(ShortAnswerCorrectAnswerContext.class);
		}
		public ShortAnswerCorrectAnswerContext shortAnswerCorrectAnswer(int i) {
			return getRuleContext(ShortAnswerCorrectAnswerContext.class,i);
		}
		public ShortAnswerQuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shortAnswerQuestion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterShortAnswerQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitShortAnswerQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitShortAnswerQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ShortAnswerQuestionContext shortAnswerQuestion() throws RecognitionException {
		ShortAnswerQuestionContext _localctx = new ShortAnswerQuestionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_shortAnswerQuestion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(102);
			match(T__2);
			setState(103);
			match(EOI);
			setState(105);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SCORE) {
				{
				setState(104);
				score();
				}
			}

			setState(107);
			body();
			setState(109);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FEEDBACK) {
				{
				setState(108);
				feedback();
				}
			}

			setState(111);
			match(START_CORRECT_ANSWERS_SECTION);
			setState(113); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(112);
				shortAnswerCorrectAnswer();
				}
				}
				setState(115); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CORRECT_ANSWER );
			setState(117);
			match(END_CORRECT_ANSWERS_SECTION);
			setState(118);
			match(EOI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TrueFalseQuestionContext extends ParserRuleContext {
		public TerminalNode EOI() { return getToken(QuestionParser.EOI, 0); }
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public TrueFalseCorrectAnswerContext trueFalseCorrectAnswer() {
			return getRuleContext(TrueFalseCorrectAnswerContext.class,0);
		}
		public ScoreContext score() {
			return getRuleContext(ScoreContext.class,0);
		}
		public FeedbackContext feedback() {
			return getRuleContext(FeedbackContext.class,0);
		}
		public TrueFalseQuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_trueFalseQuestion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterTrueFalseQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitTrueFalseQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitTrueFalseQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TrueFalseQuestionContext trueFalseQuestion() throws RecognitionException {
		TrueFalseQuestionContext _localctx = new TrueFalseQuestionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_trueFalseQuestion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(120);
			match(T__3);
			setState(121);
			match(EOI);
			setState(123);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SCORE) {
				{
				setState(122);
				score();
				}
			}

			setState(125);
			body();
			setState(127);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FEEDBACK) {
				{
				setState(126);
				feedback();
				}
			}

			setState(129);
			trueFalseCorrectAnswer();
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MatchingQuestionContext extends ParserRuleContext {
		public List<TerminalNode> EOI() { return getTokens(QuestionParser.EOI); }
		public TerminalNode EOI(int i) {
			return getToken(QuestionParser.EOI, i);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public TerminalNode START_CORRECT_ANSWERS_SECTION() { return getToken(QuestionParser.START_CORRECT_ANSWERS_SECTION, 0); }
		public TerminalNode END_CORRECT_ANSWERS_SECTION() { return getToken(QuestionParser.END_CORRECT_ANSWERS_SECTION, 0); }
		public TerminalNode START_OPTIONS_SECTION() { return getToken(QuestionParser.START_OPTIONS_SECTION, 0); }
		public TerminalNode END_OPTIONS_SECTION() { return getToken(QuestionParser.END_OPTIONS_SECTION, 0); }
		public TerminalNode START_MATCHING_SECTION() { return getToken(QuestionParser.START_MATCHING_SECTION, 0); }
		public TerminalNode END_MATCHING_SECTION() { return getToken(QuestionParser.END_MATCHING_SECTION, 0); }
		public ScoreContext score() {
			return getRuleContext(ScoreContext.class,0);
		}
		public FeedbackContext feedback() {
			return getRuleContext(FeedbackContext.class,0);
		}
		public List<MatchingCorrectAnswerContext> matchingCorrectAnswer() {
			return getRuleContexts(MatchingCorrectAnswerContext.class);
		}
		public MatchingCorrectAnswerContext matchingCorrectAnswer(int i) {
			return getRuleContext(MatchingCorrectAnswerContext.class,i);
		}
		public List<OptionContext> option() {
			return getRuleContexts(OptionContext.class);
		}
		public OptionContext option(int i) {
			return getRuleContext(OptionContext.class,i);
		}
		public List<MatchContext> match() {
			return getRuleContexts(MatchContext.class);
		}
		public MatchContext match(int i) {
			return getRuleContext(MatchContext.class,i);
		}
		public MatchingQuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matchingQuestion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterMatchingQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitMatchingQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitMatchingQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchingQuestionContext matchingQuestion() throws RecognitionException {
		MatchingQuestionContext _localctx = new MatchingQuestionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_matchingQuestion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			match(T__4);
			setState(132);
			match(EOI);
			setState(134);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SCORE) {
				{
				setState(133);
				score();
				}
			}

			setState(136);
			body();
			setState(138);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FEEDBACK) {
				{
				setState(137);
				feedback();
				}
			}

			setState(140);
			match(START_CORRECT_ANSWERS_SECTION);
			setState(142); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(141);
				matchingCorrectAnswer();
				}
				}
				setState(144); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CORRECT_ANSWER );
			setState(146);
			match(END_CORRECT_ANSWERS_SECTION);
			setState(147);
			match(EOI);
			setState(148);
			match(START_OPTIONS_SECTION);
			setState(150); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(149);
				option();
				}
				}
				setState(152); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==OPTION );
			setState(154);
			match(END_OPTIONS_SECTION);
			setState(155);
			match(EOI);
			setState(156);
			match(START_MATCHING_SECTION);
			setState(158); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(157);
				match();
				}
				}
				setState(160); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==MATCH );
			setState(162);
			match(END_MATCHING_SECTION);
			setState(163);
			match(EOI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MissingWordsQuestionContext extends ParserRuleContext {
		public List<TerminalNode> EOI() { return getTokens(QuestionParser.EOI); }
		public TerminalNode EOI(int i) {
			return getToken(QuestionParser.EOI, i);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public TerminalNode START_CORRECT_ANSWERS_SECTION() { return getToken(QuestionParser.START_CORRECT_ANSWERS_SECTION, 0); }
		public TerminalNode END_CORRECT_ANSWERS_SECTION() { return getToken(QuestionParser.END_CORRECT_ANSWERS_SECTION, 0); }
		public TerminalNode START_OPTIONS_SECTION() { return getToken(QuestionParser.START_OPTIONS_SECTION, 0); }
		public TerminalNode END_OPTIONS_SECTION() { return getToken(QuestionParser.END_OPTIONS_SECTION, 0); }
		public ScoreContext score() {
			return getRuleContext(ScoreContext.class,0);
		}
		public FeedbackContext feedback() {
			return getRuleContext(FeedbackContext.class,0);
		}
		public List<MissingWordsCorrectAnswerContext> missingWordsCorrectAnswer() {
			return getRuleContexts(MissingWordsCorrectAnswerContext.class);
		}
		public MissingWordsCorrectAnswerContext missingWordsCorrectAnswer(int i) {
			return getRuleContext(MissingWordsCorrectAnswerContext.class,i);
		}
		public List<MissingWordsOptionContext> missingWordsOption() {
			return getRuleContexts(MissingWordsOptionContext.class);
		}
		public MissingWordsOptionContext missingWordsOption(int i) {
			return getRuleContext(MissingWordsOptionContext.class,i);
		}
		public MissingWordsQuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_missingWordsQuestion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterMissingWordsQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitMissingWordsQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitMissingWordsQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MissingWordsQuestionContext missingWordsQuestion() throws RecognitionException {
		MissingWordsQuestionContext _localctx = new MissingWordsQuestionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_missingWordsQuestion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(165);
			match(T__5);
			setState(166);
			match(EOI);
			setState(168);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==SCORE) {
				{
				setState(167);
				score();
				}
			}

			setState(170);
			body();
			setState(172);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FEEDBACK) {
				{
				setState(171);
				feedback();
				}
			}

			setState(174);
			match(START_CORRECT_ANSWERS_SECTION);
			setState(176); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(175);
				missingWordsCorrectAnswer();
				}
				}
				setState(178); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CORRECT_ANSWER );
			setState(180);
			match(END_CORRECT_ANSWERS_SECTION);
			setState(181);
			match(EOI);
			setState(182);
			match(START_OPTIONS_SECTION);
			setState(184); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(183);
				missingWordsOption();
				}
				}
				setState(186); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==OPTION );
			setState(188);
			match(END_OPTIONS_SECTION);
			setState(189);
			match(EOI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class BodyContext extends ParserRuleContext {
		public TerminalNode QUESTION_BODY() { return getToken(QuestionParser.QUESTION_BODY, 0); }
		public TerminalNode STRING() { return getToken(QuestionParser.STRING, 0); }
		public TerminalNode EOI() { return getToken(QuestionParser.EOI, 0); }
		public BodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BodyContext body() throws RecognitionException {
		BodyContext _localctx = new BodyContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_body);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			match(QUESTION_BODY);
			setState(192);
			match(STRING);
			setState(193);
			match(EOI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class FeedbackContext extends ParserRuleContext {
		public TerminalNode FEEDBACK() { return getToken(QuestionParser.FEEDBACK, 0); }
		public TerminalNode STRING() { return getToken(QuestionParser.STRING, 0); }
		public TerminalNode EOI() { return getToken(QuestionParser.EOI, 0); }
		public FeedbackContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_feedback; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterFeedback(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitFeedback(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitFeedback(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FeedbackContext feedback() throws RecognitionException {
		FeedbackContext _localctx = new FeedbackContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_feedback);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(195);
			match(FEEDBACK);
			setState(196);
			match(STRING);
			setState(197);
			match(EOI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ScoreContext extends ParserRuleContext {
		public TerminalNode SCORE() { return getToken(QuestionParser.SCORE, 0); }
		public TerminalNode NUMBER() { return getToken(QuestionParser.NUMBER, 0); }
		public TerminalNode EOI() { return getToken(QuestionParser.EOI, 0); }
		public ScoreContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_score; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterScore(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitScore(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitScore(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScoreContext score() throws RecognitionException {
		ScoreContext _localctx = new ScoreContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_score);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(199);
			match(SCORE);
			setState(200);
			match(NUMBER);
			setState(201);
			match(EOI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ShortAnswerCorrectAnswerContext extends ParserRuleContext {
		public TerminalNode CORRECT_ANSWER() { return getToken(QuestionParser.CORRECT_ANSWER, 0); }
		public TerminalNode STRING() { return getToken(QuestionParser.STRING, 0); }
		public TerminalNode NUMBER() { return getToken(QuestionParser.NUMBER, 0); }
		public TerminalNode EOI() { return getToken(QuestionParser.EOI, 0); }
		public ShortAnswerCorrectAnswerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shortAnswerCorrectAnswer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterShortAnswerCorrectAnswer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitShortAnswerCorrectAnswer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitShortAnswerCorrectAnswer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ShortAnswerCorrectAnswerContext shortAnswerCorrectAnswer() throws RecognitionException {
		ShortAnswerCorrectAnswerContext _localctx = new ShortAnswerCorrectAnswerContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_shortAnswerCorrectAnswer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(203);
			match(CORRECT_ANSWER);
			setState(204);
			match(STRING);
			setState(205);
			match(NUMBER);
			setState(206);
			match(EOI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MultipleChoiceCorrectAnswerContext extends ParserRuleContext {
		public TerminalNode CORRECT_ANSWER() { return getToken(QuestionParser.CORRECT_ANSWER, 0); }
		public List<TerminalNode> NUMBER() { return getTokens(QuestionParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(QuestionParser.NUMBER, i);
		}
		public TerminalNode EOI() { return getToken(QuestionParser.EOI, 0); }
		public MultipleChoiceCorrectAnswerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multipleChoiceCorrectAnswer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterMultipleChoiceCorrectAnswer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitMultipleChoiceCorrectAnswer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitMultipleChoiceCorrectAnswer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultipleChoiceCorrectAnswerContext multipleChoiceCorrectAnswer() throws RecognitionException {
		MultipleChoiceCorrectAnswerContext _localctx = new MultipleChoiceCorrectAnswerContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_multipleChoiceCorrectAnswer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(208);
			match(CORRECT_ANSWER);
			setState(209);
			match(NUMBER);
			setState(211);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==NUMBER) {
				{
				setState(210);
				match(NUMBER);
				}
			}

			setState(213);
			match(EOI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumericalCorrectAnswerContext extends ParserRuleContext {
		public TerminalNode CORRECT_ANSWER() { return getToken(QuestionParser.CORRECT_ANSWER, 0); }
		public TerminalNode NUMBER() { return getToken(QuestionParser.NUMBER, 0); }
		public TerminalNode EOI() { return getToken(QuestionParser.EOI, 0); }
		public NumericalCorrectAnswerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numericalCorrectAnswer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterNumericalCorrectAnswer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitNumericalCorrectAnswer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitNumericalCorrectAnswer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumericalCorrectAnswerContext numericalCorrectAnswer() throws RecognitionException {
		NumericalCorrectAnswerContext _localctx = new NumericalCorrectAnswerContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_numericalCorrectAnswer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(215);
			match(CORRECT_ANSWER);
			setState(216);
			match(NUMBER);
			setState(217);
			match(EOI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class NumericalAcceptedErrorContext extends ParserRuleContext {
		public TerminalNode ACCEPTED_ERROR() { return getToken(QuestionParser.ACCEPTED_ERROR, 0); }
		public TerminalNode NUMBER() { return getToken(QuestionParser.NUMBER, 0); }
		public TerminalNode EOI() { return getToken(QuestionParser.EOI, 0); }
		public NumericalAcceptedErrorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numericalAcceptedError; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterNumericalAcceptedError(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitNumericalAcceptedError(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitNumericalAcceptedError(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumericalAcceptedErrorContext numericalAcceptedError() throws RecognitionException {
		NumericalAcceptedErrorContext _localctx = new NumericalAcceptedErrorContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_numericalAcceptedError);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(219);
			match(ACCEPTED_ERROR);
			setState(220);
			match(NUMBER);
			setState(221);
			match(EOI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class OptionContext extends ParserRuleContext {
		public TerminalNode OPTION() { return getToken(QuestionParser.OPTION, 0); }
		public TerminalNode NUMBER() { return getToken(QuestionParser.NUMBER, 0); }
		public List<TerminalNode> STRING() { return getTokens(QuestionParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(QuestionParser.STRING, i);
		}
		public TerminalNode EOI() { return getToken(QuestionParser.EOI, 0); }
		public OptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OptionContext option() throws RecognitionException {
		OptionContext _localctx = new OptionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_option);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(223);
			match(OPTION);
			setState(224);
			match(NUMBER);
			setState(225);
			match(STRING);
			setState(227);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STRING) {
				{
				setState(226);
				match(STRING);
				}
			}

			setState(229);
			match(EOI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MissingWordsOptionContext extends ParserRuleContext {
		public TerminalNode OPTION() { return getToken(QuestionParser.OPTION, 0); }
		public TerminalNode STRING() { return getToken(QuestionParser.STRING, 0); }
		public TerminalNode EOI() { return getToken(QuestionParser.EOI, 0); }
		public MissingWordsOptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_missingWordsOption; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterMissingWordsOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitMissingWordsOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitMissingWordsOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MissingWordsOptionContext missingWordsOption() throws RecognitionException {
		MissingWordsOptionContext _localctx = new MissingWordsOptionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_missingWordsOption);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(231);
			match(OPTION);
			setState(232);
			match(STRING);
			setState(233);
			match(EOI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MatchContext extends ParserRuleContext {
		public TerminalNode MATCH() { return getToken(QuestionParser.MATCH, 0); }
		public TerminalNode NUMBER() { return getToken(QuestionParser.NUMBER, 0); }
		public TerminalNode STRING() { return getToken(QuestionParser.STRING, 0); }
		public TerminalNode EOI() { return getToken(QuestionParser.EOI, 0); }
		public MatchContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_match; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterMatch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitMatch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitMatch(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchContext match() throws RecognitionException {
		MatchContext _localctx = new MatchContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_match);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(235);
			match(MATCH);
			setState(236);
			match(NUMBER);
			setState(237);
			match(STRING);
			setState(238);
			match(EOI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MatchingCorrectAnswerContext extends ParserRuleContext {
		public TerminalNode CORRECT_ANSWER() { return getToken(QuestionParser.CORRECT_ANSWER, 0); }
		public List<TerminalNode> NUMBER() { return getTokens(QuestionParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(QuestionParser.NUMBER, i);
		}
		public TerminalNode EOI() { return getToken(QuestionParser.EOI, 0); }
		public MatchingCorrectAnswerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matchingCorrectAnswer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterMatchingCorrectAnswer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitMatchingCorrectAnswer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitMatchingCorrectAnswer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchingCorrectAnswerContext matchingCorrectAnswer() throws RecognitionException {
		MatchingCorrectAnswerContext _localctx = new MatchingCorrectAnswerContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_matchingCorrectAnswer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(240);
			match(CORRECT_ANSWER);
			setState(241);
			match(NUMBER);
			setState(242);
			match(NUMBER);
			setState(243);
			match(EOI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class MissingWordsCorrectAnswerContext extends ParserRuleContext {
		public TerminalNode CORRECT_ANSWER() { return getToken(QuestionParser.CORRECT_ANSWER, 0); }
		public TerminalNode STRING() { return getToken(QuestionParser.STRING, 0); }
		public TerminalNode EOI() { return getToken(QuestionParser.EOI, 0); }
		public MissingWordsCorrectAnswerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_missingWordsCorrectAnswer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterMissingWordsCorrectAnswer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitMissingWordsCorrectAnswer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitMissingWordsCorrectAnswer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MissingWordsCorrectAnswerContext missingWordsCorrectAnswer() throws RecognitionException {
		MissingWordsCorrectAnswerContext _localctx = new MissingWordsCorrectAnswerContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_missingWordsCorrectAnswer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(245);
			match(CORRECT_ANSWER);
			setState(246);
			match(STRING);
			setState(247);
			match(EOI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class TrueFalseCorrectAnswerContext extends ParserRuleContext {
		public TerminalNode CORRECT_ANSWER() { return getToken(QuestionParser.CORRECT_ANSWER, 0); }
		public TerminalNode EOI() { return getToken(QuestionParser.EOI, 0); }
		public TerminalNode TRUE() { return getToken(QuestionParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(QuestionParser.FALSE, 0); }
		public TrueFalseCorrectAnswerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_trueFalseCorrectAnswer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).enterTrueFalseCorrectAnswer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof QuestionListener ) ((QuestionListener)listener).exitTrueFalseCorrectAnswer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof QuestionVisitor ) return ((QuestionVisitor<? extends T>)visitor).visitTrueFalseCorrectAnswer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TrueFalseCorrectAnswerContext trueFalseCorrectAnswer() throws RecognitionException {
		TrueFalseCorrectAnswerContext _localctx = new TrueFalseCorrectAnswerContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_trueFalseCorrectAnswer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(249);
			match(CORRECT_ANSWER);
			setState(250);
			_la = _input.LA(1);
			if ( !(_la==TRUE || _la==FALSE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(251);
			match(EOI);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3!\u0100\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\3\2\6\2.\n\2\r\2\16\2/\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3:\n\3\3\3\3\3\3\3\3\4\3\4\3\4\5\4B\n\4\3"+
		"\4\3\4\5\4F\n\4\3\4\3\4\3\4\3\5\3\5\3\5\5\5N\n\5\3\5\3\5\5\5R\n\5\3\5"+
		"\3\5\6\5V\n\5\r\5\16\5W\3\5\3\5\3\5\3\5\5\5^\n\5\3\5\3\5\6\5b\n\5\r\5"+
		"\16\5c\3\5\3\5\3\5\3\6\3\6\3\6\5\6l\n\6\3\6\3\6\5\6p\n\6\3\6\3\6\6\6t"+
		"\n\6\r\6\16\6u\3\6\3\6\3\6\3\7\3\7\3\7\5\7~\n\7\3\7\3\7\5\7\u0082\n\7"+
		"\3\7\3\7\3\b\3\b\3\b\5\b\u0089\n\b\3\b\3\b\5\b\u008d\n\b\3\b\3\b\6\b\u0091"+
		"\n\b\r\b\16\b\u0092\3\b\3\b\3\b\3\b\6\b\u0099\n\b\r\b\16\b\u009a\3\b\3"+
		"\b\3\b\3\b\6\b\u00a1\n\b\r\b\16\b\u00a2\3\b\3\b\3\b\3\t\3\t\3\t\5\t\u00ab"+
		"\n\t\3\t\3\t\5\t\u00af\n\t\3\t\3\t\6\t\u00b3\n\t\r\t\16\t\u00b4\3\t\3"+
		"\t\3\t\3\t\6\t\u00bb\n\t\r\t\16\t\u00bc\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3"+
		"\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16"+
		"\5\16\u00d6\n\16\3\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\21"+
		"\3\21\3\21\3\21\5\21\u00e6\n\21\3\21\3\21\3\22\3\22\3\22\3\22\3\23\3\23"+
		"\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\26\3\26"+
		"\3\26\3\26\3\26\2\2\27\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*\2"+
		"\3\3\2\36\37\2\u0107\2-\3\2\2\2\4\61\3\2\2\2\6>\3\2\2\2\bJ\3\2\2\2\nh"+
		"\3\2\2\2\fz\3\2\2\2\16\u0085\3\2\2\2\20\u00a7\3\2\2\2\22\u00c1\3\2\2\2"+
		"\24\u00c5\3\2\2\2\26\u00c9\3\2\2\2\30\u00cd\3\2\2\2\32\u00d2\3\2\2\2\34"+
		"\u00d9\3\2\2\2\36\u00dd\3\2\2\2 \u00e1\3\2\2\2\"\u00e9\3\2\2\2$\u00ed"+
		"\3\2\2\2&\u00f2\3\2\2\2(\u00f7\3\2\2\2*\u00fb\3\2\2\2,.\5\4\3\2-,\3\2"+
		"\2\2./\3\2\2\2/-\3\2\2\2/\60\3\2\2\2\60\3\3\2\2\2\61\62\7\16\2\2\629\7"+
		"\20\2\2\63:\5\6\4\2\64:\5\b\5\2\65:\5\n\6\2\66:\5\f\7\2\67:\5\16\b\28"+
		":\5\20\t\29\63\3\2\2\29\64\3\2\2\29\65\3\2\2\29\66\3\2\2\29\67\3\2\2\2"+
		"98\3\2\2\2:;\3\2\2\2;<\7\17\2\2<=\7\t\2\2=\5\3\2\2\2>?\7\3\2\2?A\7\t\2"+
		"\2@B\5\26\f\2A@\3\2\2\2AB\3\2\2\2BC\3\2\2\2CE\5\22\n\2DF\5\24\13\2ED\3"+
		"\2\2\2EF\3\2\2\2FG\3\2\2\2GH\5\34\17\2HI\5\36\20\2I\7\3\2\2\2JK\7\4\2"+
		"\2KM\7\t\2\2LN\5\26\f\2ML\3\2\2\2MN\3\2\2\2NO\3\2\2\2OQ\5\22\n\2PR\5\24"+
		"\13\2QP\3\2\2\2QR\3\2\2\2R]\3\2\2\2SU\7\22\2\2TV\5\32\16\2UT\3\2\2\2V"+
		"W\3\2\2\2WU\3\2\2\2WX\3\2\2\2XY\3\2\2\2YZ\7\24\2\2Z[\7\t\2\2[^\3\2\2\2"+
		"\\^\5\32\16\2]S\3\2\2\2]\\\3\2\2\2^_\3\2\2\2_a\7\27\2\2`b\5 \21\2a`\3"+
		"\2\2\2bc\3\2\2\2ca\3\2\2\2cd\3\2\2\2de\3\2\2\2ef\7\30\2\2fg\7\t\2\2g\t"+
		"\3\2\2\2hi\7\5\2\2ik\7\t\2\2jl\5\26\f\2kj\3\2\2\2kl\3\2\2\2lm\3\2\2\2"+
		"mo\5\22\n\2np\5\24\13\2on\3\2\2\2op\3\2\2\2pq\3\2\2\2qs\7\22\2\2rt\5\30"+
		"\r\2sr\3\2\2\2tu\3\2\2\2us\3\2\2\2uv\3\2\2\2vw\3\2\2\2wx\7\24\2\2xy\7"+
		"\t\2\2y\13\3\2\2\2z{\7\6\2\2{}\7\t\2\2|~\5\26\f\2}|\3\2\2\2}~\3\2\2\2"+
		"~\177\3\2\2\2\177\u0081\5\22\n\2\u0080\u0082\5\24\13\2\u0081\u0080\3\2"+
		"\2\2\u0081\u0082\3\2\2\2\u0082\u0083\3\2\2\2\u0083\u0084\5*\26\2\u0084"+
		"\r\3\2\2\2\u0085\u0086\7\7\2\2\u0086\u0088\7\t\2\2\u0087\u0089\5\26\f"+
		"\2\u0088\u0087\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008a\3\2\2\2\u008a\u008c"+
		"\5\22\n\2\u008b\u008d\5\24\13\2\u008c\u008b\3\2\2\2\u008c\u008d\3\2\2"+
		"\2\u008d\u008e\3\2\2\2\u008e\u0090\7\22\2\2\u008f\u0091\5&\24\2\u0090"+
		"\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0090\3\2\2\2\u0092\u0093\3\2"+
		"\2\2\u0093\u0094\3\2\2\2\u0094\u0095\7\24\2\2\u0095\u0096\7\t\2\2\u0096"+
		"\u0098\7\27\2\2\u0097\u0099\5 \21\2\u0098\u0097\3\2\2\2\u0099\u009a\3"+
		"\2\2\2\u009a\u0098\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u009c\3\2\2\2\u009c"+
		"\u009d\7\30\2\2\u009d\u009e\7\t\2\2\u009e\u00a0\7\33\2\2\u009f\u00a1\5"+
		"$\23\2\u00a0\u009f\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a2"+
		"\u00a3\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00a5\7\34\2\2\u00a5\u00a6\7"+
		"\t\2\2\u00a6\17\3\2\2\2\u00a7\u00a8\7\b\2\2\u00a8\u00aa\7\t\2\2\u00a9"+
		"\u00ab\5\26\f\2\u00aa\u00a9\3\2\2\2\u00aa\u00ab\3\2\2\2\u00ab\u00ac\3"+
		"\2\2\2\u00ac\u00ae\5\22\n\2\u00ad\u00af\5\24\13\2\u00ae\u00ad\3\2\2\2"+
		"\u00ae\u00af\3\2\2\2\u00af\u00b0\3\2\2\2\u00b0\u00b2\7\22\2\2\u00b1\u00b3"+
		"\5(\25\2\u00b2\u00b1\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b2\3\2\2\2\u00b4"+
		"\u00b5\3\2\2\2\u00b5\u00b6\3\2\2\2\u00b6\u00b7\7\24\2\2\u00b7\u00b8\7"+
		"\t\2\2\u00b8\u00ba\7\27\2\2\u00b9\u00bb\5\"\22\2\u00ba\u00b9\3\2\2\2\u00bb"+
		"\u00bc\3\2\2\2\u00bc\u00ba\3\2\2\2\u00bc\u00bd\3\2\2\2\u00bd\u00be\3\2"+
		"\2\2\u00be\u00bf\7\30\2\2\u00bf\u00c0\7\t\2\2\u00c0\21\3\2\2\2\u00c1\u00c2"+
		"\7\21\2\2\u00c2\u00c3\7\n\2\2\u00c3\u00c4\7\t\2\2\u00c4\23\3\2\2\2\u00c5"+
		"\u00c6\7\26\2\2\u00c6\u00c7\7\n\2\2\u00c7\u00c8\7\t\2\2\u00c8\25\3\2\2"+
		"\2\u00c9\u00ca\7\32\2\2\u00ca\u00cb\7\13\2\2\u00cb\u00cc\7\t\2\2\u00cc"+
		"\27\3\2\2\2\u00cd\u00ce\7\23\2\2\u00ce\u00cf\7\n\2\2\u00cf\u00d0\7\13"+
		"\2\2\u00d0\u00d1\7\t\2\2\u00d1\31\3\2\2\2\u00d2\u00d3\7\23\2\2\u00d3\u00d5"+
		"\7\13\2\2\u00d4\u00d6\7\13\2\2\u00d5\u00d4\3\2\2\2\u00d5\u00d6\3\2\2\2"+
		"\u00d6\u00d7\3\2\2\2\u00d7\u00d8\7\t\2\2\u00d8\33\3\2\2\2\u00d9\u00da"+
		"\7\23\2\2\u00da\u00db\7\13\2\2\u00db\u00dc\7\t\2\2\u00dc\35\3\2\2\2\u00dd"+
		"\u00de\7\25\2\2\u00de\u00df\7\13\2\2\u00df\u00e0\7\t\2\2\u00e0\37\3\2"+
		"\2\2\u00e1\u00e2\7\31\2\2\u00e2\u00e3\7\13\2\2\u00e3\u00e5\7\n\2\2\u00e4"+
		"\u00e6\7\n\2\2\u00e5\u00e4\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6\u00e7\3\2"+
		"\2\2\u00e7\u00e8\7\t\2\2\u00e8!\3\2\2\2\u00e9\u00ea\7\31\2\2\u00ea\u00eb"+
		"\7\n\2\2\u00eb\u00ec\7\t\2\2\u00ec#\3\2\2\2\u00ed\u00ee\7\35\2\2\u00ee"+
		"\u00ef\7\13\2\2\u00ef\u00f0\7\n\2\2\u00f0\u00f1\7\t\2\2\u00f1%\3\2\2\2"+
		"\u00f2\u00f3\7\23\2\2\u00f3\u00f4\7\13\2\2\u00f4\u00f5\7\13\2\2\u00f5"+
		"\u00f6\7\t\2\2\u00f6\'\3\2\2\2\u00f7\u00f8\7\23\2\2\u00f8\u00f9\7\n\2"+
		"\2\u00f9\u00fa\7\t\2\2\u00fa)\3\2\2\2\u00fb\u00fc\7\23\2\2\u00fc\u00fd"+
		"\t\2\2\2\u00fd\u00fe\7\t\2\2\u00fe+\3\2\2\2\33/9AEMQW]ckou}\u0081\u0088"+
		"\u008c\u0092\u009a\u00a2\u00aa\u00ae\u00b4\u00bc\u00d5\u00e5";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}