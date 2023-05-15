// Generated from /home/russo/isep/lapr4/sem4pi-22-23-19/ecourse.core/src/main/java/eapli/ecourse/exammanagement/domain/grammars/Exam/Exam.g4 by ANTLR 4.9.2
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
public class ExamParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		EOI=1, STRING=2, START_EXAM=3, END_EXAM=4, TITLE=5, DESCRIPTION=6, FEEDBACK=7, 
		GRADE=8, START_SECTION=9, END_SECTION=10, FDB_GRD_TYPE=11, NUMBER=12, 
		IDENTIFIER=13, WS=14, COMMENT=15;
	public static final int
		RULE_start = 0, RULE_exam = 1, RULE_exam_header = 2, RULE_title = 3, RULE_description = 4, 
		RULE_feedback = 5, RULE_grade = 6, RULE_sections = 7, RULE_section = 8, 
		RULE_section_header = 9, RULE_questions = 10;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "exam", "exam_header", "title", "description", "feedback", "grade", 
			"sections", "section", "section_header", "questions"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", null, "'@start-exam'", "'@end-exam'", "'@title'", "'@description'", 
			"'@feedback'", "'@grade'", "'@start-section'", "'@end-section'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "EOI", "STRING", "START_EXAM", "END_EXAM", "TITLE", "DESCRIPTION", 
			"FEEDBACK", "GRADE", "START_SECTION", "END_SECTION", "FDB_GRD_TYPE", 
			"NUMBER", "IDENTIFIER", "WS", "COMMENT"
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
	public String getGrammarFileName() { return "Exam.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public ExamParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	public static class StartContext extends ParserRuleContext {
		public ExamContext exam() {
			return getRuleContext(ExamContext.class,0);
		}
		public StartContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitStart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(22);
			exam();
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

	public static class ExamContext extends ParserRuleContext {
		public TerminalNode START_EXAM() { return getToken(ExamParser.START_EXAM, 0); }
		public TerminalNode IDENTIFIER() { return getToken(ExamParser.IDENTIFIER, 0); }
		public List<TerminalNode> EOI() { return getTokens(ExamParser.EOI); }
		public TerminalNode EOI(int i) {
			return getToken(ExamParser.EOI, i);
		}
		public Exam_headerContext exam_header() {
			return getRuleContext(Exam_headerContext.class,0);
		}
		public SectionsContext sections() {
			return getRuleContext(SectionsContext.class,0);
		}
		public TerminalNode END_EXAM() { return getToken(ExamParser.END_EXAM, 0); }
		public ExamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exam; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterExam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitExam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitExam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExamContext exam() throws RecognitionException {
		ExamContext _localctx = new ExamContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_exam);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(24);
			match(START_EXAM);
			setState(25);
			match(IDENTIFIER);
			setState(26);
			match(EOI);
			setState(27);
			exam_header();
			setState(28);
			sections();
			setState(29);
			match(END_EXAM);
			setState(30);
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

	public static class Exam_headerContext extends ParserRuleContext {
		public TitleContext title() {
			return getRuleContext(TitleContext.class,0);
		}
		public List<TerminalNode> EOI() { return getTokens(ExamParser.EOI); }
		public TerminalNode EOI(int i) {
			return getToken(ExamParser.EOI, i);
		}
		public FeedbackContext feedback() {
			return getRuleContext(FeedbackContext.class,0);
		}
		public GradeContext grade() {
			return getRuleContext(GradeContext.class,0);
		}
		public DescriptionContext description() {
			return getRuleContext(DescriptionContext.class,0);
		}
		public Exam_headerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exam_header; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterExam_header(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitExam_header(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitExam_header(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Exam_headerContext exam_header() throws RecognitionException {
		Exam_headerContext _localctx = new Exam_headerContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_exam_header);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(32);
			title();
			setState(33);
			match(EOI);
			setState(37);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DESCRIPTION) {
				{
				setState(34);
				description();
				setState(35);
				match(EOI);
				}
			}

			setState(39);
			feedback();
			setState(40);
			match(EOI);
			setState(41);
			grade();
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

	public static class TitleContext extends ParserRuleContext {
		public TerminalNode TITLE() { return getToken(ExamParser.TITLE, 0); }
		public TerminalNode STRING() { return getToken(ExamParser.STRING, 0); }
		public TitleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_title; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterTitle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitTitle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitTitle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TitleContext title() throws RecognitionException {
		TitleContext _localctx = new TitleContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_title);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(44);
			match(TITLE);
			setState(45);
			match(STRING);
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

	public static class DescriptionContext extends ParserRuleContext {
		public TerminalNode DESCRIPTION() { return getToken(ExamParser.DESCRIPTION, 0); }
		public TerminalNode STRING() { return getToken(ExamParser.STRING, 0); }
		public DescriptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_description; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterDescription(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitDescription(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitDescription(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DescriptionContext description() throws RecognitionException {
		DescriptionContext _localctx = new DescriptionContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_description);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(47);
			match(DESCRIPTION);
			setState(48);
			match(STRING);
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
		public TerminalNode FEEDBACK() { return getToken(ExamParser.FEEDBACK, 0); }
		public TerminalNode FDB_GRD_TYPE() { return getToken(ExamParser.FDB_GRD_TYPE, 0); }
		public FeedbackContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_feedback; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterFeedback(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitFeedback(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitFeedback(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FeedbackContext feedback() throws RecognitionException {
		FeedbackContext _localctx = new FeedbackContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_feedback);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(50);
			match(FEEDBACK);
			setState(51);
			match(FDB_GRD_TYPE);
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

	public static class GradeContext extends ParserRuleContext {
		public TerminalNode GRADE() { return getToken(ExamParser.GRADE, 0); }
		public TerminalNode FDB_GRD_TYPE() { return getToken(ExamParser.FDB_GRD_TYPE, 0); }
		public GradeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_grade; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterGrade(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitGrade(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitGrade(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GradeContext grade() throws RecognitionException {
		GradeContext _localctx = new GradeContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_grade);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53);
			match(GRADE);
			setState(54);
			match(FDB_GRD_TYPE);
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

	public static class SectionsContext extends ParserRuleContext {
		public List<SectionContext> section() {
			return getRuleContexts(SectionContext.class);
		}
		public SectionContext section(int i) {
			return getRuleContext(SectionContext.class,i);
		}
		public SectionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_sections; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterSections(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitSections(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitSections(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SectionsContext sections() throws RecognitionException {
		SectionsContext _localctx = new SectionsContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_sections);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(57); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(56);
				section();
				}
				}
				setState(59); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==START_SECTION );
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

	public static class SectionContext extends ParserRuleContext {
		public TerminalNode START_SECTION() { return getToken(ExamParser.START_SECTION, 0); }
		public TerminalNode IDENTIFIER() { return getToken(ExamParser.IDENTIFIER, 0); }
		public List<TerminalNode> EOI() { return getTokens(ExamParser.EOI); }
		public TerminalNode EOI(int i) {
			return getToken(ExamParser.EOI, i);
		}
		public Section_headerContext section_header() {
			return getRuleContext(Section_headerContext.class,0);
		}
		public QuestionsContext questions() {
			return getRuleContext(QuestionsContext.class,0);
		}
		public TerminalNode END_SECTION() { return getToken(ExamParser.END_SECTION, 0); }
		public SectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_section; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SectionContext section() throws RecognitionException {
		SectionContext _localctx = new SectionContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_section);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(61);
			match(START_SECTION);
			setState(62);
			match(IDENTIFIER);
			setState(63);
			match(EOI);
			setState(64);
			section_header();
			setState(65);
			questions();
			setState(66);
			match(END_SECTION);
			setState(67);
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

	public static class Section_headerContext extends ParserRuleContext {
		public TitleContext title() {
			return getRuleContext(TitleContext.class,0);
		}
		public List<TerminalNode> EOI() { return getTokens(ExamParser.EOI); }
		public TerminalNode EOI(int i) {
			return getToken(ExamParser.EOI, i);
		}
		public DescriptionContext description() {
			return getRuleContext(DescriptionContext.class,0);
		}
		public Section_headerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_section_header; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterSection_header(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitSection_header(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitSection_header(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Section_headerContext section_header() throws RecognitionException {
		Section_headerContext _localctx = new Section_headerContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_section_header);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(69);
			title();
			setState(70);
			match(EOI);
			setState(74);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==DESCRIPTION) {
				{
				setState(71);
				description();
				setState(72);
				match(EOI);
				}
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

	public static class QuestionsContext extends ParserRuleContext {
		public QuestionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterQuestions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitQuestions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitQuestions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionsContext questions() throws RecognitionException {
		QuestionsContext _localctx = new QuestionsContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_questions);
		try {
			enterOuterAlt(_localctx, 1);
			{
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\21Q\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\5\4"+
		"(\n\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\6\3\6\3\6\3\7\3\7\3\7\3\b\3\b"+
		"\3\b\3\t\6\t<\n\t\r\t\16\t=\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13"+
		"\3\13\3\13\3\13\5\13M\n\13\3\f\3\f\3\f\2\2\r\2\4\6\b\n\f\16\20\22\24\26"+
		"\2\2\2H\2\30\3\2\2\2\4\32\3\2\2\2\6\"\3\2\2\2\b.\3\2\2\2\n\61\3\2\2\2"+
		"\f\64\3\2\2\2\16\67\3\2\2\2\20;\3\2\2\2\22?\3\2\2\2\24G\3\2\2\2\26N\3"+
		"\2\2\2\30\31\5\4\3\2\31\3\3\2\2\2\32\33\7\5\2\2\33\34\7\17\2\2\34\35\7"+
		"\3\2\2\35\36\5\6\4\2\36\37\5\20\t\2\37 \7\6\2\2 !\7\3\2\2!\5\3\2\2\2\""+
		"#\5\b\5\2#\'\7\3\2\2$%\5\n\6\2%&\7\3\2\2&(\3\2\2\2\'$\3\2\2\2\'(\3\2\2"+
		"\2()\3\2\2\2)*\5\f\7\2*+\7\3\2\2+,\5\16\b\2,-\7\3\2\2-\7\3\2\2\2./\7\7"+
		"\2\2/\60\7\4\2\2\60\t\3\2\2\2\61\62\7\b\2\2\62\63\7\4\2\2\63\13\3\2\2"+
		"\2\64\65\7\t\2\2\65\66\7\r\2\2\66\r\3\2\2\2\678\7\n\2\289\7\r\2\29\17"+
		"\3\2\2\2:<\5\22\n\2;:\3\2\2\2<=\3\2\2\2=;\3\2\2\2=>\3\2\2\2>\21\3\2\2"+
		"\2?@\7\13\2\2@A\7\17\2\2AB\7\3\2\2BC\5\24\13\2CD\5\26\f\2DE\7\f\2\2EF"+
		"\7\3\2\2F\23\3\2\2\2GH\5\b\5\2HL\7\3\2\2IJ\5\n\6\2JK\7\3\2\2KM\3\2\2\2"+
		"LI\3\2\2\2LM\3\2\2\2M\25\3\2\2\2NO\3\2\2\2O\27\3\2\2\2\5\'=L";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}