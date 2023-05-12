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
		EOI=10, STRING=11, NUMBER=12, REAL_NUMBER=13, START_QUESTION=14, END_QUESTION=15, 
		TYPE=16, QUESTION_BODY=17, START_CORRECT_ANSWERS_SECTION=18, CORRECT_ANSWER=19, 
		END_CORRECT_ANSWERS_SECTION=20, ACCEPTED_ERROR=21, FEEDBACK=22, START_OPTIONS_SECTION=23, 
		END_OPTIONS_SECTION=24, OPTION=25, START_MATCHING_SECTION=26, END_MATCHING_SECTION=27, 
		MATCH=28, WS=29, COMMENT=30;
	public static final int
		RULE_start = 0, RULE_question = 1, RULE_numerical_question = 2, RULE_multiple_choice_question = 3, 
		RULE_short_answer_question = 4, RULE_true_false_question = 5, RULE_matching_question = 6, 
		RULE_missing_words_question = 7, RULE_body = 8, RULE_feedback = 9, RULE_short_answer_correct_answer = 10, 
		RULE_multiple_choice_correct_answer = 11, RULE_option = 12, RULE_match = 13, 
		RULE_matching_correct_answer = 14, RULE_missing_words_correct_answer = 15;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "question", "numerical_question", "multiple_choice_question", 
			"short_answer_question", "true_false_question", "matching_question", 
			"missing_words_question", "body", "feedback", "short_answer_correct_answer", 
			"multiple_choice_correct_answer", "option", "match", "matching_correct_answer", 
			"missing_words_correct_answer"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'numerical'", "'multiple-choice'", "'short-answer'", "'true-false'", 
			"'true'", "'false'", "'matching'", "'missing-words'", "' '", "';'", null, 
			null, null, "'@start-question'", "'@end-question'", "'@type '", "'@question-body'", 
			"'@correct-answers'", "'@correct-answer '", "'@end-correct-answers'", 
			"'@accepted-error '", "'@feedback '", "'@start-options'", "'@end-options'", 
			"'@option'", "'@start-matching'", "'@end-matching'", "'@match'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, "EOI", "STRING", 
			"NUMBER", "REAL_NUMBER", "START_QUESTION", "END_QUESTION", "TYPE", "QUESTION_BODY", 
			"START_CORRECT_ANSWERS_SECTION", "CORRECT_ANSWER", "END_CORRECT_ANSWERS_SECTION", 
			"ACCEPTED_ERROR", "FEEDBACK", "START_OPTIONS_SECTION", "END_OPTIONS_SECTION", 
			"OPTION", "START_MATCHING_SECTION", "END_MATCHING_SECTION", "MATCH", 
			"WS", "COMMENT"
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
			setState(33); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(32);
				question();
				}
				}
				setState(35); 
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
			setState(37);
			match(START_QUESTION);
			setState(38);
			match(TYPE);
			setState(45);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				{
				setState(39);
				numerical_question();
				}
				break;
			case T__1:
				{
				setState(40);
				multiple_choice_question();
				}
				break;
			case T__2:
				{
				setState(41);
				short_answer_question();
				}
				break;
			case T__3:
				{
				setState(42);
				true_false_question();
				}
				break;
			case T__6:
				{
				setState(43);
				matching_question();
				}
				break;
			case T__7:
				{
				setState(44);
				missing_words_question();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(47);
			match(END_QUESTION);
			setState(48);
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
			setState(50);
			match(T__0);
			setState(51);
			match(EOI);
			setState(52);
			body();
			setState(54);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FEEDBACK) {
				{
				setState(53);
				feedback();
				}
			}

			setState(56);
			match(CORRECT_ANSWER);
			setState(57);
			match(REAL_NUMBER);
			setState(58);
			match(EOI);
			setState(59);
			match(ACCEPTED_ERROR);
			setState(60);
			match(REAL_NUMBER);
			setState(61);
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
		public TerminalNode START_OPTIONS_SECTION() { return getToken(QuestionParser.START_OPTIONS_SECTION, 0); }
		public TerminalNode END_OPTIONS_SECTION() { return getToken(QuestionParser.END_OPTIONS_SECTION, 0); }
		public TerminalNode START_CORRECT_ANSWERS_SECTION() { return getToken(QuestionParser.START_CORRECT_ANSWERS_SECTION, 0); }
		public TerminalNode END_CORRECT_ANSWERS_SECTION() { return getToken(QuestionParser.END_CORRECT_ANSWERS_SECTION, 0); }
		public List<Multiple_choice_correct_answerContext> multiple_choice_correct_answer() {
			return getRuleContexts(Multiple_choice_correct_answerContext.class);
		}
		public Multiple_choice_correct_answerContext multiple_choice_correct_answer(int i) {
			return getRuleContext(Multiple_choice_correct_answerContext.class,i);
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
			setState(63);
			match(T__1);
			setState(64);
			match(EOI);
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

			setState(79);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case START_CORRECT_ANSWERS_SECTION:
				{
				setState(69);
				match(START_CORRECT_ANSWERS_SECTION);
				setState(71); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(70);
					multiple_choice_correct_answer();
					}
					}
					setState(73); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==CORRECT_ANSWER );
				setState(75);
				match(END_CORRECT_ANSWERS_SECTION);
				setState(76);
				match(EOI);
				}
				break;
			case CORRECT_ANSWER:
				{
				setState(78);
				multiple_choice_correct_answer();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(81);
			match(START_OPTIONS_SECTION);
			setState(83); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(82);
				option();
				}
				}
				setState(85); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==OPTION );
			setState(87);
			match(END_OPTIONS_SECTION);
			setState(88);
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
			setState(90);
			match(T__2);
			setState(91);
			match(EOI);
			setState(92);
			body();
			setState(94);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FEEDBACK) {
				{
				setState(93);
				feedback();
				}
			}

			setState(96);
			match(START_CORRECT_ANSWERS_SECTION);
			setState(98); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(97);
				short_answer_correct_answer();
				}
				}
				setState(100); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CORRECT_ANSWER );
			setState(102);
			match(END_CORRECT_ANSWERS_SECTION);
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
			setState(105);
			match(T__3);
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
			match(CORRECT_ANSWER);
			setState(112);
			_la = _input.LA(1);
			if ( !(_la==T__4 || _la==T__5) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(113);
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
		public TerminalNode START_CORRECT_ANSWERS_SECTION() { return getToken(QuestionParser.START_CORRECT_ANSWERS_SECTION, 0); }
		public TerminalNode END_CORRECT_ANSWERS_SECTION() { return getToken(QuestionParser.END_CORRECT_ANSWERS_SECTION, 0); }
		public TerminalNode START_OPTIONS_SECTION() { return getToken(QuestionParser.START_OPTIONS_SECTION, 0); }
		public TerminalNode END_OPTIONS_SECTION() { return getToken(QuestionParser.END_OPTIONS_SECTION, 0); }
		public TerminalNode START_MATCHING_SECTION() { return getToken(QuestionParser.START_MATCHING_SECTION, 0); }
		public TerminalNode END_MATCHING_SECTION() { return getToken(QuestionParser.END_MATCHING_SECTION, 0); }
		public FeedbackContext feedback() {
			return getRuleContext(FeedbackContext.class,0);
		}
		public List<Matching_correct_answerContext> matching_correct_answer() {
			return getRuleContexts(Matching_correct_answerContext.class);
		}
		public Matching_correct_answerContext matching_correct_answer(int i) {
			return getRuleContext(Matching_correct_answerContext.class,i);
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
			setState(115);
			match(T__6);
			setState(116);
			match(EOI);
			setState(117);
			body();
			setState(119);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FEEDBACK) {
				{
				setState(118);
				feedback();
				}
			}

			setState(121);
			match(START_CORRECT_ANSWERS_SECTION);
			setState(123); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(122);
				matching_correct_answer();
				}
				}
				setState(125); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CORRECT_ANSWER );
			setState(127);
			match(END_CORRECT_ANSWERS_SECTION);
			setState(128);
			match(EOI);
			setState(129);
			match(START_OPTIONS_SECTION);
			setState(131); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(130);
				option();
				}
				}
				setState(133); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==OPTION );
			setState(135);
			match(END_OPTIONS_SECTION);
			setState(136);
			match(EOI);
			setState(137);
			match(START_MATCHING_SECTION);
			setState(139); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(138);
				match();
				}
				}
				setState(141); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==MATCH );
			setState(143);
			match(END_MATCHING_SECTION);
			setState(144);
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
		public Missing_words_correct_answerContext missing_words_correct_answer() {
			return getRuleContext(Missing_words_correct_answerContext.class,0);
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
			setState(146);
			match(T__7);
			setState(147);
			match(EOI);
			setState(148);
			body();
			setState(150);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FEEDBACK) {
				{
				setState(149);
				feedback();
				}
			}

			setState(152);
			missing_words_correct_answer();
			setState(153);
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
			setState(155);
			match(QUESTION_BODY);
			setState(156);
			match(T__8);
			setState(157);
			match(STRING);
			setState(158);
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
			setState(160);
			match(FEEDBACK);
			setState(161);
			match(STRING);
			setState(162);
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
			setState(164);
			match(CORRECT_ANSWER);
			setState(165);
			match(STRING);
			setState(166);
			match(T__8);
			setState(167);
			match(REAL_NUMBER);
			setState(168);
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
		public TerminalNode NUMBER() { return getToken(QuestionParser.NUMBER, 0); }
		public TerminalNode REAL_NUMBER() { return getToken(QuestionParser.REAL_NUMBER, 0); }
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
			setState(178);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,15,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(170);
				match(CORRECT_ANSWER);
				setState(171);
				match(NUMBER);
				setState(172);
				match(T__8);
				setState(173);
				match(REAL_NUMBER);
				setState(174);
				match(EOI);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(175);
				match(CORRECT_ANSWER);
				setState(176);
				match(NUMBER);
				setState(177);
				match(EOI);
				}
				break;
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
	}

	public final OptionContext option() throws RecognitionException {
		OptionContext _localctx = new OptionContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_option);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(180);
			match(OPTION);
			setState(181);
			match(T__8);
			setState(182);
			match(NUMBER);
			setState(183);
			match(T__8);
			setState(184);
			match(STRING);
			setState(187);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__8) {
				{
				setState(185);
				match(T__8);
				setState(186);
				match(STRING);
				}
			}

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

	public static class MatchContext extends ParserRuleContext {
		public TerminalNode MATCH() { return getToken(QuestionParser.MATCH, 0); }
		public TerminalNode NUMBER() { return getToken(QuestionParser.NUMBER, 0); }
		public TerminalNode STRING() { return getToken(QuestionParser.STRING, 0); }
		public TerminalNode EOI() { return getToken(QuestionParser.EOI, 0); }
		public MatchContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_match; }
	}

	public final MatchContext match() throws RecognitionException {
		MatchContext _localctx = new MatchContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_match);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(191);
			match(MATCH);
			setState(192);
			match(T__8);
			setState(193);
			match(NUMBER);
			setState(194);
			match(T__8);
			setState(195);
			match(STRING);
			setState(196);
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

	public static class Matching_correct_answerContext extends ParserRuleContext {
		public TerminalNode CORRECT_ANSWER() { return getToken(QuestionParser.CORRECT_ANSWER, 0); }
		public List<TerminalNode> NUMBER() { return getTokens(QuestionParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(QuestionParser.NUMBER, i);
		}
		public TerminalNode EOI() { return getToken(QuestionParser.EOI, 0); }
		public Matching_correct_answerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matching_correct_answer; }
	}

	public final Matching_correct_answerContext matching_correct_answer() throws RecognitionException {
		Matching_correct_answerContext _localctx = new Matching_correct_answerContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_matching_correct_answer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(198);
			match(CORRECT_ANSWER);
			setState(199);
			match(NUMBER);
			setState(200);
			match(T__8);
			setState(201);
			match(NUMBER);
			setState(202);
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

	public static class Missing_words_correct_answerContext extends ParserRuleContext {
		public TerminalNode CORRECT_ANSWER() { return getToken(QuestionParser.CORRECT_ANSWER, 0); }
		public List<TerminalNode> STRING() { return getTokens(QuestionParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(QuestionParser.STRING, i);
		}
		public Missing_words_correct_answerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_missing_words_correct_answer; }
	}

	public final Missing_words_correct_answerContext missing_words_correct_answer() throws RecognitionException {
		Missing_words_correct_answerContext _localctx = new Missing_words_correct_answerContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_missing_words_correct_answer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(204);
			match(CORRECT_ANSWER);
			setState(205);
			match(STRING);
			setState(210);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__8) {
				{
				{
				setState(206);
				match(T__8);
				setState(207);
				match(STRING);
				}
				}
				setState(212);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3 \u00d8\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\6\2$\n"+
		"\2\r\2\16\2%\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3\60\n\3\3\3\3\3\3\3\3"+
		"\4\3\4\3\4\3\4\5\49\n\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\5"+
		"\5F\n\5\3\5\3\5\6\5J\n\5\r\5\16\5K\3\5\3\5\3\5\3\5\5\5R\n\5\3\5\3\5\6"+
		"\5V\n\5\r\5\16\5W\3\5\3\5\3\5\3\6\3\6\3\6\3\6\5\6a\n\6\3\6\3\6\6\6e\n"+
		"\6\r\6\16\6f\3\6\3\6\3\6\3\7\3\7\3\7\3\7\5\7p\n\7\3\7\3\7\3\7\3\7\3\b"+
		"\3\b\3\b\3\b\5\bz\n\b\3\b\3\b\6\b~\n\b\r\b\16\b\177\3\b\3\b\3\b\3\b\6"+
		"\b\u0086\n\b\r\b\16\b\u0087\3\b\3\b\3\b\3\b\6\b\u008e\n\b\r\b\16\b\u008f"+
		"\3\b\3\b\3\b\3\t\3\t\3\t\3\t\5\t\u0099\n\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n"+
		"\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\5\r\u00b5\n\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u00be"+
		"\n\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\21\3\21\3\21\3\21\7\21\u00d3\n\21\f\21\16\21\u00d6\13\21"+
		"\3\21\2\2\22\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \2\3\3\2\7\b\2\u00dd"+
		"\2#\3\2\2\2\4\'\3\2\2\2\6\64\3\2\2\2\bA\3\2\2\2\n\\\3\2\2\2\fk\3\2\2\2"+
		"\16u\3\2\2\2\20\u0094\3\2\2\2\22\u009d\3\2\2\2\24\u00a2\3\2\2\2\26\u00a6"+
		"\3\2\2\2\30\u00b4\3\2\2\2\32\u00b6\3\2\2\2\34\u00c1\3\2\2\2\36\u00c8\3"+
		"\2\2\2 \u00ce\3\2\2\2\"$\5\4\3\2#\"\3\2\2\2$%\3\2\2\2%#\3\2\2\2%&\3\2"+
		"\2\2&\3\3\2\2\2\'(\7\20\2\2(/\7\22\2\2)\60\5\6\4\2*\60\5\b\5\2+\60\5\n"+
		"\6\2,\60\5\f\7\2-\60\5\16\b\2.\60\5\20\t\2/)\3\2\2\2/*\3\2\2\2/+\3\2\2"+
		"\2/,\3\2\2\2/-\3\2\2\2/.\3\2\2\2\60\61\3\2\2\2\61\62\7\21\2\2\62\63\7"+
		"\f\2\2\63\5\3\2\2\2\64\65\7\3\2\2\65\66\7\f\2\2\668\5\22\n\2\679\5\24"+
		"\13\28\67\3\2\2\289\3\2\2\29:\3\2\2\2:;\7\25\2\2;<\7\17\2\2<=\7\f\2\2"+
		"=>\7\27\2\2>?\7\17\2\2?@\7\f\2\2@\7\3\2\2\2AB\7\4\2\2BC\7\f\2\2CE\5\22"+
		"\n\2DF\5\24\13\2ED\3\2\2\2EF\3\2\2\2FQ\3\2\2\2GI\7\24\2\2HJ\5\30\r\2I"+
		"H\3\2\2\2JK\3\2\2\2KI\3\2\2\2KL\3\2\2\2LM\3\2\2\2MN\7\26\2\2NO\7\f\2\2"+
		"OR\3\2\2\2PR\5\30\r\2QG\3\2\2\2QP\3\2\2\2RS\3\2\2\2SU\7\31\2\2TV\5\32"+
		"\16\2UT\3\2\2\2VW\3\2\2\2WU\3\2\2\2WX\3\2\2\2XY\3\2\2\2YZ\7\32\2\2Z[\7"+
		"\f\2\2[\t\3\2\2\2\\]\7\5\2\2]^\7\f\2\2^`\5\22\n\2_a\5\24\13\2`_\3\2\2"+
		"\2`a\3\2\2\2ab\3\2\2\2bd\7\24\2\2ce\5\26\f\2dc\3\2\2\2ef\3\2\2\2fd\3\2"+
		"\2\2fg\3\2\2\2gh\3\2\2\2hi\7\26\2\2ij\7\f\2\2j\13\3\2\2\2kl\7\6\2\2lm"+
		"\7\f\2\2mo\5\22\n\2np\5\24\13\2on\3\2\2\2op\3\2\2\2pq\3\2\2\2qr\7\25\2"+
		"\2rs\t\2\2\2st\7\f\2\2t\r\3\2\2\2uv\7\t\2\2vw\7\f\2\2wy\5\22\n\2xz\5\24"+
		"\13\2yx\3\2\2\2yz\3\2\2\2z{\3\2\2\2{}\7\24\2\2|~\5\36\20\2}|\3\2\2\2~"+
		"\177\3\2\2\2\177}\3\2\2\2\177\u0080\3\2\2\2\u0080\u0081\3\2\2\2\u0081"+
		"\u0082\7\26\2\2\u0082\u0083\7\f\2\2\u0083\u0085\7\31\2\2\u0084\u0086\5"+
		"\32\16\2\u0085\u0084\3\2\2\2\u0086\u0087\3\2\2\2\u0087\u0085\3\2\2\2\u0087"+
		"\u0088\3\2\2\2\u0088\u0089\3\2\2\2\u0089\u008a\7\32\2\2\u008a\u008b\7"+
		"\f\2\2\u008b\u008d\7\34\2\2\u008c\u008e\5\34\17\2\u008d\u008c\3\2\2\2"+
		"\u008e\u008f\3\2\2\2\u008f\u008d\3\2\2\2\u008f\u0090\3\2\2\2\u0090\u0091"+
		"\3\2\2\2\u0091\u0092\7\35\2\2\u0092\u0093\7\f\2\2\u0093\17\3\2\2\2\u0094"+
		"\u0095\7\n\2\2\u0095\u0096\7\f\2\2\u0096\u0098\5\22\n\2\u0097\u0099\5"+
		"\24\13\2\u0098\u0097\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u009a\3\2\2\2\u009a"+
		"\u009b\5 \21\2\u009b\u009c\7\f\2\2\u009c\21\3\2\2\2\u009d\u009e\7\23\2"+
		"\2\u009e\u009f\7\13\2\2\u009f\u00a0\7\r\2\2\u00a0\u00a1\7\f\2\2\u00a1"+
		"\23\3\2\2\2\u00a2\u00a3\7\30\2\2\u00a3\u00a4\7\r\2\2\u00a4\u00a5\7\f\2"+
		"\2\u00a5\25\3\2\2\2\u00a6\u00a7\7\25\2\2\u00a7\u00a8\7\r\2\2\u00a8\u00a9"+
		"\7\13\2\2\u00a9\u00aa\7\17\2\2\u00aa\u00ab\7\f\2\2\u00ab\27\3\2\2\2\u00ac"+
		"\u00ad\7\25\2\2\u00ad\u00ae\7\16\2\2\u00ae\u00af\7\13\2\2\u00af\u00b0"+
		"\7\17\2\2\u00b0\u00b5\7\f\2\2\u00b1\u00b2\7\25\2\2\u00b2\u00b3\7\16\2"+
		"\2\u00b3\u00b5\7\f\2\2\u00b4\u00ac\3\2\2\2\u00b4\u00b1\3\2\2\2\u00b5\31"+
		"\3\2\2\2\u00b6\u00b7\7\33\2\2\u00b7\u00b8\7\13\2\2\u00b8\u00b9\7\16\2"+
		"\2\u00b9\u00ba\7\13\2\2\u00ba\u00bd\7\r\2\2\u00bb\u00bc\7\13\2\2\u00bc"+
		"\u00be\7\r\2\2\u00bd\u00bb\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\u00bf\3\2"+
		"\2\2\u00bf\u00c0\7\f\2\2\u00c0\33\3\2\2\2\u00c1\u00c2\7\36\2\2\u00c2\u00c3"+
		"\7\13\2\2\u00c3\u00c4\7\16\2\2\u00c4\u00c5\7\13\2\2\u00c5\u00c6\7\r\2"+
		"\2\u00c6\u00c7\7\f\2\2\u00c7\35\3\2\2\2\u00c8\u00c9\7\25\2\2\u00c9\u00ca"+
		"\7\16\2\2\u00ca\u00cb\7\13\2\2\u00cb\u00cc\7\16\2\2\u00cc\u00cd\7\f\2"+
		"\2\u00cd\37\3\2\2\2\u00ce\u00cf\7\25\2\2\u00cf\u00d4\7\r\2\2\u00d0\u00d1"+
		"\7\13\2\2\u00d1\u00d3\7\r\2\2\u00d2\u00d0\3\2\2\2\u00d3\u00d6\3\2\2\2"+
		"\u00d4\u00d2\3\2\2\2\u00d4\u00d5\3\2\2\2\u00d5!\3\2\2\2\u00d6\u00d4\3"+
		"\2\2\2\24%/8EKQW`foy\177\u0087\u008f\u0098\u00b4\u00bd\u00d4";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}