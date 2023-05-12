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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, EOI=8, STRING=9, 
		REAL_NUMBER=10, START_QUESTION=11, END_QUESTION=12, TYPE=13, QUESTION_BODY=14, 
		START_CORRECT_ANSWERS_SECTION=15, END_CORRECT_ANSWERS_SECTION=16, CORRECT_ANSWER=17, 
		ACCEPTED_ERROR=18, FEEDBACK=19, START_OPTIONS_SECTION=20, END_OPTIONS_SECTION=21, 
		OPTION=22, START_MATCHING_SECTION=23, END_MATCHING_SECTION=24, MATCH=25, 
		WS=26, COMMENT=27;
	public static final int
		RULE_start = 0, RULE_question = 1, RULE_numerical_question = 2, RULE_multiple_choice_question = 3, 
		RULE_short_answer_question = 4, RULE_true_false_question = 5, RULE_matching_question = 6, 
		RULE_missing_words_question = 7, RULE_body = 8, RULE_short_answer_correct_answer = 9;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "question", "numerical_question", "multiple_choice_question", 
			"short_answer_question", "true_false_question", "matching_question", 
			"missing_words_question", "body", "short_answer_correct_answer"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "' '", "'numerical'", "'multiple-choice'", "'short-answer'", "'true-false'", 
			"'matching'", "'missing-words'", "';'", null, null, "'@start-question'", 
			"'@end-question'", "'@type'", "'@question-body'", "'@correct-answers'", 
			"'@end-correct-answers'", "'@correct-answer'", "'@accepted-error'", "'@feedback'", 
			"'@options'", "'@end-options'", "'@option'", "'@start-matching'", "'@end-matching'", 
			"'@match'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, "EOI", "STRING", "REAL_NUMBER", 
			"START_QUESTION", "END_QUESTION", "TYPE", "QUESTION_BODY", "START_CORRECT_ANSWERS_SECTION", 
			"END_CORRECT_ANSWERS_SECTION", "CORRECT_ANSWER", "ACCEPTED_ERROR", "FEEDBACK", 
			"START_OPTIONS_SECTION", "END_OPTIONS_SECTION", "OPTION", "START_MATCHING_SECTION", 
			"END_MATCHING_SECTION", "MATCH", "WS", "COMMENT"
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
			setState(21); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(20);
				question();
				}
				}
				setState(23); 
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
			setState(25);
			match(START_QUESTION);
			setState(26);
			match(TYPE);
			setState(27);
			match(T__0);
			setState(34);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__1:
				{
				setState(28);
				numerical_question();
				}
				break;
			case T__2:
				{
				setState(29);
				multiple_choice_question();
				}
				break;
			case T__3:
				{
				setState(30);
				short_answer_question();
				}
				break;
			case T__4:
				{
				setState(31);
				true_false_question();
				}
				break;
			case T__5:
				{
				setState(32);
				matching_question();
				}
				break;
			case T__6:
				{
				setState(33);
				missing_words_question();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(36);
			match(END_QUESTION);
			setState(37);
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
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public TerminalNode CORRECT_ANSWER() { return getToken(QuestionParser.CORRECT_ANSWER, 0); }
		public List<TerminalNode> REAL_NUMBER() { return getTokens(QuestionParser.REAL_NUMBER); }
		public TerminalNode REAL_NUMBER(int i) {
			return getToken(QuestionParser.REAL_NUMBER, i);
		}
		public List<TerminalNode> EOI() { return getTokens(QuestionParser.EOI); }
		public TerminalNode EOI(int i) {
			return getToken(QuestionParser.EOI, i);
		}
		public TerminalNode ACCEPTED_ERROR() { return getToken(QuestionParser.ACCEPTED_ERROR, 0); }
		public Numerical_questionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numerical_question; }
	}

	public final Numerical_questionContext numerical_question() throws RecognitionException {
		Numerical_questionContext _localctx = new Numerical_questionContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_numerical_question);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(39);
			match(T__1);
			setState(40);
			body();
			setState(41);
			match(CORRECT_ANSWER);
			setState(42);
			match(REAL_NUMBER);
			setState(43);
			match(EOI);
			setState(44);
			match(ACCEPTED_ERROR);
			setState(45);
			match(REAL_NUMBER);
			setState(46);
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
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public TerminalNode EOI() { return getToken(QuestionParser.EOI, 0); }
		public Multiple_choice_questionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multiple_choice_question; }
	}

	public final Multiple_choice_questionContext multiple_choice_question() throws RecognitionException {
		Multiple_choice_questionContext _localctx = new Multiple_choice_questionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_multiple_choice_question);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(48);
			match(T__2);
			setState(49);
			body();
			setState(50);
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
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public TerminalNode START_CORRECT_ANSWERS_SECTION() { return getToken(QuestionParser.START_CORRECT_ANSWERS_SECTION, 0); }
		public TerminalNode END_CORRECT_ANSWERS_SECTION() { return getToken(QuestionParser.END_CORRECT_ANSWERS_SECTION, 0); }
		public TerminalNode EOI() { return getToken(QuestionParser.EOI, 0); }
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
			setState(52);
			match(T__3);
			setState(53);
			body();
			setState(54);
			match(START_CORRECT_ANSWERS_SECTION);
			setState(56); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(55);
				short_answer_correct_answer();
				}
				}
				setState(58); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CORRECT_ANSWER );
			setState(60);
			match(END_CORRECT_ANSWERS_SECTION);
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

	public static class True_false_questionContext extends ParserRuleContext {
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public TerminalNode EOI() { return getToken(QuestionParser.EOI, 0); }
		public True_false_questionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_true_false_question; }
	}

	public final True_false_questionContext true_false_question() throws RecognitionException {
		True_false_questionContext _localctx = new True_false_questionContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_true_false_question);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(63);
			match(T__4);
			setState(64);
			body();
			setState(65);
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
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public TerminalNode EOI() { return getToken(QuestionParser.EOI, 0); }
		public Matching_questionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matching_question; }
	}

	public final Matching_questionContext matching_question() throws RecognitionException {
		Matching_questionContext _localctx = new Matching_questionContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_matching_question);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			match(T__5);
			setState(68);
			body();
			setState(69);
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
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public TerminalNode EOI() { return getToken(QuestionParser.EOI, 0); }
		public Missing_words_questionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_missing_words_question; }
	}

	public final Missing_words_questionContext missing_words_question() throws RecognitionException {
		Missing_words_questionContext _localctx = new Missing_words_questionContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_missing_words_question);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(71);
			match(T__6);
			setState(72);
			body();
			setState(73);
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
			setState(75);
			match(QUESTION_BODY);
			setState(76);
			match(T__0);
			setState(77);
			match(STRING);
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
		enterRule(_localctx, 18, RULE_short_answer_correct_answer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(CORRECT_ANSWER);
			setState(81);
			match(T__0);
			setState(82);
			match(STRING);
			setState(83);
			match(T__0);
			setState(84);
			match(REAL_NUMBER);
			setState(85);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\35Z\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\3"+
		"\2\6\2\30\n\2\r\2\16\2\31\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\5\3%\n\3"+
		"\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\6\3"+
		"\6\3\6\3\6\6\6;\n\6\r\6\16\6<\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\b\3\b\3\b"+
		"\3\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\2\2\f\2\4\6\b\n\f\16\20\22\24\2\2\2V\2\27\3\2\2\2\4\33\3\2"+
		"\2\2\6)\3\2\2\2\b\62\3\2\2\2\n\66\3\2\2\2\fA\3\2\2\2\16E\3\2\2\2\20I\3"+
		"\2\2\2\22M\3\2\2\2\24R\3\2\2\2\26\30\5\4\3\2\27\26\3\2\2\2\30\31\3\2\2"+
		"\2\31\27\3\2\2\2\31\32\3\2\2\2\32\3\3\2\2\2\33\34\7\r\2\2\34\35\7\17\2"+
		"\2\35$\7\3\2\2\36%\5\6\4\2\37%\5\b\5\2 %\5\n\6\2!%\5\f\7\2\"%\5\16\b\2"+
		"#%\5\20\t\2$\36\3\2\2\2$\37\3\2\2\2$ \3\2\2\2$!\3\2\2\2$\"\3\2\2\2$#\3"+
		"\2\2\2%&\3\2\2\2&\'\7\16\2\2\'(\7\n\2\2(\5\3\2\2\2)*\7\4\2\2*+\5\22\n"+
		"\2+,\7\23\2\2,-\7\f\2\2-.\7\n\2\2./\7\24\2\2/\60\7\f\2\2\60\61\7\n\2\2"+
		"\61\7\3\2\2\2\62\63\7\5\2\2\63\64\5\22\n\2\64\65\7\n\2\2\65\t\3\2\2\2"+
		"\66\67\7\6\2\2\678\5\22\n\28:\7\21\2\29;\5\24\13\2:9\3\2\2\2;<\3\2\2\2"+
		"<:\3\2\2\2<=\3\2\2\2=>\3\2\2\2>?\7\22\2\2?@\7\n\2\2@\13\3\2\2\2AB\7\7"+
		"\2\2BC\5\22\n\2CD\7\n\2\2D\r\3\2\2\2EF\7\b\2\2FG\5\22\n\2GH\7\n\2\2H\17"+
		"\3\2\2\2IJ\7\t\2\2JK\5\22\n\2KL\7\n\2\2L\21\3\2\2\2MN\7\20\2\2NO\7\3\2"+
		"\2OP\7\13\2\2PQ\7\n\2\2Q\23\3\2\2\2RS\7\23\2\2ST\7\3\2\2TU\7\13\2\2UV"+
		"\7\3\2\2VW\7\f\2\2WX\7\n\2\2X\25\3\2\2\2\5\31$<";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}