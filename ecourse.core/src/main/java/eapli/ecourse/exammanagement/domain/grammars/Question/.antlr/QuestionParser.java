// Generated from /home/kappa-laptop/faculdade/22/lapr4/sem4pi-22-23-19/ecourse.core/src/main/java/eapli/ecourse/exammanagement/domain/grammars/Question/Question.g4 by ANTLR 4.9.2
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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		EOI=10, STRING=11, REAL_NUMBER=12, ID=13, WEIGTH=14, START_QUESTION=15, 
		END_QUESTION=16, TYPE=17, QUESTION_BODY=18, START_CORRECT_ANSWERS_SECTION=19, 
		END_CORRECT_ANSWERS_SECTION=20, CORRECT_ANSWER=21, ACCEPTED_ERROR=22, 
		FEEDBACK=23, START_OPTIONS_SECTION=24, END_OPTIONS_SECTION=25, OPTION=26, 
		START_MATCHING_SECTION=27, END_MATCHING_SECTION=28, MATCH=29, WS=30, COMMENT=31;
	public static final int
		RULE_start = 0, RULE_question = 1, RULE_numerical_question = 2, RULE_multiple_choice_question = 3, 
		RULE_short_answer_question = 4, RULE_true_false_question = 5, RULE_matching_question = 6, 
		RULE_missing_words_question = 7, RULE_body = 8, RULE_feedback = 9, RULE_short_answer_correct_answer = 10, 
		RULE_multiple_choice_correct_answer = 11, RULE_multiple_choice_option = 12;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "question", "numerical_question", "multiple_choice_question", 
			"short_answer_question", "true_false_question", "matching_question", 
			"missing_words_question", "body", "feedback", "short_answer_correct_answer", 
			"multiple_choice_correct_answer", "multiple_choice_option"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'numerical'", "'multiple-choice'", "'short-answer'", "'true-false'", 
			"'true'", "'false'", "'matching'", "'missing-words'", "' '", "';'", null, 
			null, null, null, "'@start-question'", "'@end-question'", "'@type '", 
			"'@question-body'", "'@correct-answers'", "'@end-correct-answers'", "'@correct-answer '", 
			"'@accepted-error '", "'@feedback '", "'@options'", "'@end-options'", 
			"'@option'", "'@start-matching'", "'@end-matching'", "'@match'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, "EOI", "STRING", 
			"REAL_NUMBER", "ID", "WEIGTH", "START_QUESTION", "END_QUESTION", "TYPE", 
			"QUESTION_BODY", "START_CORRECT_ANSWERS_SECTION", "END_CORRECT_ANSWERS_SECTION", 
			"CORRECT_ANSWER", "ACCEPTED_ERROR", "FEEDBACK", "START_OPTIONS_SECTION", 
			"END_OPTIONS_SECTION", "OPTION", "START_MATCHING_SECTION", "END_MATCHING_SECTION", 
			"MATCH", "WS", "COMMENT"
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
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(27); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(26);
				question();
				}
				}
				setState(29); 
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
		public Numerical_questionContext numerical_question() {
			return getRuleContext(Numerical_questionContext.class,0);
		}
		public Multiple_choice_questionContext multiple_choice_question() {
			return getRuleContext(Multiple_choice_questionContext.class,0);
		}
		public Short_answer_questionContext short_answer_question() {
			return getRuleContext(Short_answer_questionContext.class,0);
		}
		public True_false_questionContext true_false_question() {
			return getRuleContext(True_false_questionContext.class,0);
		}
		public Matching_questionContext matching_question() {
			return getRuleContext(Matching_questionContext.class,0);
		}
		public Missing_words_questionContext missing_words_question() {
			return getRuleContext(Missing_words_questionContext.class,0);
		}
		public QuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_question; }
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_question);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(31);
			match(START_QUESTION);
			setState(32);
			match(TYPE);
			setState(39);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				{
				setState(33);
				numerical_question();
				}
				break;
			case T__1:
				{
				setState(34);
				multiple_choice_question();
				}
				break;
			case T__2:
				{
				setState(35);
				short_answer_question();
				}
				break;
			case T__3:
				{
				setState(36);
				true_false_question();
				}
				break;
			case T__6:
				{
				setState(37);
				matching_question();
				}
				break;
			case T__7:
				{
				setState(38);
				missing_words_question();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(41);
			match(END_QUESTION);
			setState(42);
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

	public static class Numerical_questionContext extends ParserRuleContext {
		public List<TerminalNode> EOI() { return getTokens(QuestionParser.EOI); }
		public TerminalNode EOI(int i) {
			return getToken(QuestionParser.EOI, i);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public TerminalNode CORRECT_ANSWER() { return getToken(QuestionParser.CORRECT_ANSWER, 0); }
		public List<TerminalNode> REAL_NUMBER() { return getTokens(QuestionParser.REAL_NUMBER); }
		public TerminalNode REAL_NUMBER(int i) {
			return getToken(QuestionParser.REAL_NUMBER, i);
		}
		public TerminalNode ACCEPTED_ERROR() { return getToken(QuestionParser.ACCEPTED_ERROR, 0); }
		public FeedbackContext feedback() {
			return getRuleContext(FeedbackContext.class,0);
		}
		public Numerical_questionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numerical_question; }
	}

	public final Numerical_questionContext numerical_question() throws RecognitionException {
		Numerical_questionContext _localctx = new Numerical_questionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_numerical_question);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			match(T__0);
			setState(45);
			match(EOI);
			setState(46);
			body();
			setState(48);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FEEDBACK) {
				{
				setState(47);
				feedback();
				}
			}

			setState(50);
			match(CORRECT_ANSWER);
			setState(51);
			match(REAL_NUMBER);
			setState(52);
			match(EOI);
			setState(53);
			match(ACCEPTED_ERROR);
			setState(54);
			match(REAL_NUMBER);
			setState(55);
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

	public static class Multiple_choice_questionContext extends ParserRuleContext {
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
		public FeedbackContext feedback() {
			return getRuleContext(FeedbackContext.class,0);
		}
		public List<Multiple_choice_correct_answerContext> multiple_choice_correct_answer() {
			return getRuleContexts(Multiple_choice_correct_answerContext.class);
		}
		public Multiple_choice_correct_answerContext multiple_choice_correct_answer(int i) {
			return getRuleContext(Multiple_choice_correct_answerContext.class,i);
		}
		public List<Multiple_choice_optionContext> multiple_choice_option() {
			return getRuleContexts(Multiple_choice_optionContext.class);
		}
		public Multiple_choice_optionContext multiple_choice_option(int i) {
			return getRuleContext(Multiple_choice_optionContext.class,i);
		}
		public Multiple_choice_questionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiple_choice_question; }
	}

	public final Multiple_choice_questionContext multiple_choice_question() throws RecognitionException {
		Multiple_choice_questionContext _localctx = new Multiple_choice_questionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_multiple_choice_question);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57);
			match(T__1);
			setState(58);
			match(EOI);
			setState(59);
			body();
			setState(61);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FEEDBACK) {
				{
				setState(60);
				feedback();
				}
			}

			setState(63);
			match(START_CORRECT_ANSWERS_SECTION);
			setState(65); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(64);
				multiple_choice_correct_answer();
				}
				}
				setState(67); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CORRECT_ANSWER );
			setState(69);
			match(END_CORRECT_ANSWERS_SECTION);
			setState(70);
			match(EOI);
			setState(71);
			match(START_OPTIONS_SECTION);
			setState(73); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(72);
				multiple_choice_option();
				}
				}
				setState(75); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==ID );
			setState(77);
			match(END_OPTIONS_SECTION);
			setState(78);
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

	public static class Short_answer_questionContext extends ParserRuleContext {
		public List<TerminalNode> EOI() { return getTokens(QuestionParser.EOI); }
		public TerminalNode EOI(int i) {
			return getToken(QuestionParser.EOI, i);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public TerminalNode START_CORRECT_ANSWERS_SECTION() { return getToken(QuestionParser.START_CORRECT_ANSWERS_SECTION, 0); }
		public TerminalNode END_CORRECT_ANSWERS_SECTION() { return getToken(QuestionParser.END_CORRECT_ANSWERS_SECTION, 0); }
		public FeedbackContext feedback() {
			return getRuleContext(FeedbackContext.class,0);
		}
		public List<Short_answer_correct_answerContext> short_answer_correct_answer() {
			return getRuleContexts(Short_answer_correct_answerContext.class);
		}
		public Short_answer_correct_answerContext short_answer_correct_answer(int i) {
			return getRuleContext(Short_answer_correct_answerContext.class,i);
		}
		public Short_answer_questionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_short_answer_question; }
	}

	public final Short_answer_questionContext short_answer_question() throws RecognitionException {
		Short_answer_questionContext _localctx = new Short_answer_questionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_short_answer_question);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(T__2);
			setState(81);
			match(EOI);
			setState(82);
			body();
			setState(84);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FEEDBACK) {
				{
				setState(83);
				feedback();
				}
			}

			setState(86);
			match(START_CORRECT_ANSWERS_SECTION);
			setState(88); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(87);
				short_answer_correct_answer();
				}
				}
				setState(90); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CORRECT_ANSWER );
			setState(92);
			match(END_CORRECT_ANSWERS_SECTION);
			setState(93);
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

	public static class True_false_questionContext extends ParserRuleContext {
		public List<TerminalNode> EOI() { return getTokens(QuestionParser.EOI); }
		public TerminalNode EOI(int i) {
			return getToken(QuestionParser.EOI, i);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public TerminalNode CORRECT_ANSWER() { return getToken(QuestionParser.CORRECT_ANSWER, 0); }
		public FeedbackContext feedback() {
			return getRuleContext(FeedbackContext.class,0);
		}
		public True_false_questionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_true_false_question; }
	}

	public final True_false_questionContext true_false_question() throws RecognitionException {
		True_false_questionContext _localctx = new True_false_questionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_true_false_question);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(95);
			match(T__3);
			setState(96);
			match(EOI);
			setState(97);
			body();
			setState(99);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FEEDBACK) {
				{
				setState(98);
				feedback();
				}
			}

			setState(101);
			match(CORRECT_ANSWER);
			setState(102);
			_la = _input.LA(1);
			if ( !(_la==T__4 || _la==T__5) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(103);
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

	public static class Matching_questionContext extends ParserRuleContext {
		public List<TerminalNode> EOI() { return getTokens(QuestionParser.EOI); }
		public TerminalNode EOI(int i) {
			return getToken(QuestionParser.EOI, i);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public FeedbackContext feedback() {
			return getRuleContext(FeedbackContext.class,0);
		}
		public Matching_questionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matching_question; }
	}

	public final Matching_questionContext matching_question() throws RecognitionException {
		Matching_questionContext _localctx = new Matching_questionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_matching_question);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			match(T__6);
			setState(106);
			match(EOI);
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

	public static class Missing_words_questionContext extends ParserRuleContext {
		public List<TerminalNode> EOI() { return getTokens(QuestionParser.EOI); }
		public TerminalNode EOI(int i) {
			return getToken(QuestionParser.EOI, i);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public FeedbackContext feedback() {
			return getRuleContext(FeedbackContext.class,0);
		}
		public Missing_words_questionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_missing_words_question; }
	}

	public final Missing_words_questionContext missing_words_question() throws RecognitionException {
		Missing_words_questionContext _localctx = new Missing_words_questionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_missing_words_question);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			match(T__7);
			setState(114);
			match(EOI);
			setState(115);
			body();
			setState(117);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FEEDBACK) {
				{
				setState(116);
				feedback();
				}
			}

			setState(119);
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
	}

	public final BodyContext body() throws RecognitionException {
		BodyContext _localctx = new BodyContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_body);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			match(QUESTION_BODY);
			setState(122);
			match(T__8);
			setState(123);
			match(STRING);
			setState(124);
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
	}

	public final FeedbackContext feedback() throws RecognitionException {
		FeedbackContext _localctx = new FeedbackContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_feedback);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(126);
			match(FEEDBACK);
			setState(127);
			match(STRING);
			setState(128);
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

	public static class Short_answer_correct_answerContext extends ParserRuleContext {
		public TerminalNode CORRECT_ANSWER() { return getToken(QuestionParser.CORRECT_ANSWER, 0); }
		public TerminalNode STRING() { return getToken(QuestionParser.STRING, 0); }
		public TerminalNode REAL_NUMBER() { return getToken(QuestionParser.REAL_NUMBER, 0); }
		public TerminalNode EOI() { return getToken(QuestionParser.EOI, 0); }
		public Short_answer_correct_answerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_short_answer_correct_answer; }
	}

	public final Short_answer_correct_answerContext short_answer_correct_answer() throws RecognitionException {
		Short_answer_correct_answerContext _localctx = new Short_answer_correct_answerContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_short_answer_correct_answer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(130);
			match(CORRECT_ANSWER);
			setState(131);
			match(STRING);
			setState(132);
			match(T__8);
			setState(133);
			match(REAL_NUMBER);
			setState(134);
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

	public static class Multiple_choice_correct_answerContext extends ParserRuleContext {
		public TerminalNode CORRECT_ANSWER() { return getToken(QuestionParser.CORRECT_ANSWER, 0); }
		public TerminalNode ID() { return getToken(QuestionParser.ID, 0); }
		public TerminalNode STRING() { return getToken(QuestionParser.STRING, 0); }
		public TerminalNode WEIGTH() { return getToken(QuestionParser.WEIGTH, 0); }
		public TerminalNode EOI() { return getToken(QuestionParser.EOI, 0); }
		public Multiple_choice_correct_answerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiple_choice_correct_answer; }
	}

	public final Multiple_choice_correct_answerContext multiple_choice_correct_answer() throws RecognitionException {
		Multiple_choice_correct_answerContext _localctx = new Multiple_choice_correct_answerContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_multiple_choice_correct_answer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(136);
			match(CORRECT_ANSWER);
			setState(137);
			match(T__8);
			setState(138);
			match(ID);
			setState(139);
			match(T__8);
			setState(140);
			match(STRING);
			setState(141);
			match(T__8);
			setState(142);
			match(WEIGTH);
			setState(143);
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

	public static class Multiple_choice_optionContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(QuestionParser.ID, 0); }
		public List<TerminalNode> STRING() { return getTokens(QuestionParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(QuestionParser.STRING, i);
		}
		public TerminalNode EOI() { return getToken(QuestionParser.EOI, 0); }
		public Multiple_choice_optionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiple_choice_option; }
	}

	public final Multiple_choice_optionContext multiple_choice_option() throws RecognitionException {
		Multiple_choice_optionContext _localctx = new Multiple_choice_optionContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_multiple_choice_option);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(145);
			match(ID);
			setState(146);
			match(T__8);
			setState(147);
			match(STRING);
			setState(148);
			match(T__8);
			setState(150);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STRING) {
				{
				setState(149);
				match(STRING);
				}
			}

			setState(152);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3!\u009d\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\3\2\6\2\36\n\2\r\2\16\2\37\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\5\3*\n\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\5\4\63\n\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\5\5@\n\5\3\5\3\5\6\5D\n\5\r\5"+
		"\16\5E\3\5\3\5\3\5\3\5\6\5L\n\5\r\5\16\5M\3\5\3\5\3\5\3\6\3\6\3\6\3\6"+
		"\5\6W\n\6\3\6\3\6\6\6[\n\6\r\6\16\6\\\3\6\3\6\3\6\3\7\3\7\3\7\3\7\5\7"+
		"f\n\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\5\bp\n\b\3\b\3\b\3\t\3\t\3\t\3\t"+
		"\5\tx\n\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f"+
		"\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3"+
		"\16\5\16\u0099\n\16\3\16\3\16\3\16\2\2\17\2\4\6\b\n\f\16\20\22\24\26\30"+
		"\32\2\3\3\2\7\b\2\u009f\2\35\3\2\2\2\4!\3\2\2\2\6.\3\2\2\2\b;\3\2\2\2"+
		"\nR\3\2\2\2\fa\3\2\2\2\16k\3\2\2\2\20s\3\2\2\2\22{\3\2\2\2\24\u0080\3"+
		"\2\2\2\26\u0084\3\2\2\2\30\u008a\3\2\2\2\32\u0093\3\2\2\2\34\36\5\4\3"+
		"\2\35\34\3\2\2\2\36\37\3\2\2\2\37\35\3\2\2\2\37 \3\2\2\2 \3\3\2\2\2!\""+
		"\7\21\2\2\")\7\23\2\2#*\5\6\4\2$*\5\b\5\2%*\5\n\6\2&*\5\f\7\2\'*\5\16"+
		"\b\2(*\5\20\t\2)#\3\2\2\2)$\3\2\2\2)%\3\2\2\2)&\3\2\2\2)\'\3\2\2\2)(\3"+
		"\2\2\2*+\3\2\2\2+,\7\22\2\2,-\7\f\2\2-\5\3\2\2\2./\7\3\2\2/\60\7\f\2\2"+
		"\60\62\5\22\n\2\61\63\5\24\13\2\62\61\3\2\2\2\62\63\3\2\2\2\63\64\3\2"+
		"\2\2\64\65\7\27\2\2\65\66\7\16\2\2\66\67\7\f\2\2\678\7\30\2\289\7\16\2"+
		"\29:\7\f\2\2:\7\3\2\2\2;<\7\4\2\2<=\7\f\2\2=?\5\22\n\2>@\5\24\13\2?>\3"+
		"\2\2\2?@\3\2\2\2@A\3\2\2\2AC\7\25\2\2BD\5\30\r\2CB\3\2\2\2DE\3\2\2\2E"+
		"C\3\2\2\2EF\3\2\2\2FG\3\2\2\2GH\7\26\2\2HI\7\f\2\2IK\7\32\2\2JL\5\32\16"+
		"\2KJ\3\2\2\2LM\3\2\2\2MK\3\2\2\2MN\3\2\2\2NO\3\2\2\2OP\7\33\2\2PQ\7\f"+
		"\2\2Q\t\3\2\2\2RS\7\5\2\2ST\7\f\2\2TV\5\22\n\2UW\5\24\13\2VU\3\2\2\2V"+
		"W\3\2\2\2WX\3\2\2\2XZ\7\25\2\2Y[\5\26\f\2ZY\3\2\2\2[\\\3\2\2\2\\Z\3\2"+
		"\2\2\\]\3\2\2\2]^\3\2\2\2^_\7\26\2\2_`\7\f\2\2`\13\3\2\2\2ab\7\6\2\2b"+
		"c\7\f\2\2ce\5\22\n\2df\5\24\13\2ed\3\2\2\2ef\3\2\2\2fg\3\2\2\2gh\7\27"+
		"\2\2hi\t\2\2\2ij\7\f\2\2j\r\3\2\2\2kl\7\t\2\2lm\7\f\2\2mo\5\22\n\2np\5"+
		"\24\13\2on\3\2\2\2op\3\2\2\2pq\3\2\2\2qr\7\f\2\2r\17\3\2\2\2st\7\n\2\2"+
		"tu\7\f\2\2uw\5\22\n\2vx\5\24\13\2wv\3\2\2\2wx\3\2\2\2xy\3\2\2\2yz\7\f"+
		"\2\2z\21\3\2\2\2{|\7\24\2\2|}\7\13\2\2}~\7\r\2\2~\177\7\f\2\2\177\23\3"+
		"\2\2\2\u0080\u0081\7\31\2\2\u0081\u0082\7\r\2\2\u0082\u0083\7\f\2\2\u0083"+
		"\25\3\2\2\2\u0084\u0085\7\27\2\2\u0085\u0086\7\r\2\2\u0086\u0087\7\13"+
		"\2\2\u0087\u0088\7\16\2\2\u0088\u0089\7\f\2\2\u0089\27\3\2\2\2\u008a\u008b"+
		"\7\27\2\2\u008b\u008c\7\13\2\2\u008c\u008d\7\17\2\2\u008d\u008e\7\13\2"+
		"\2\u008e\u008f\7\r\2\2\u008f\u0090\7\13\2\2\u0090\u0091\7\20\2\2\u0091"+
		"\u0092\7\f\2\2\u0092\31\3\2\2\2\u0093\u0094\7\17\2\2\u0094\u0095\7\13"+
		"\2\2\u0095\u0096\7\r\2\2\u0096\u0098\7\13\2\2\u0097\u0099\7\r\2\2\u0098"+
		"\u0097\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u009a\3\2\2\2\u009a\u009b\7\f"+
		"\2\2\u009b\33\3\2\2\2\16\37)\62?EMV\\eow\u0098";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}