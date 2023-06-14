// Generated from /home/drew/Faculdade/LAPR4/sem4pi-22-23-19/ecourse.core/src/main/java/eapli/ecourse/exammanagement/domain/grammars/FormativeExam/FormativeExam.g4 by ANTLR 4.9.2
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
public class FormativeExamParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, EOI=7, STRING=8, START_EXAM=9, 
		END_EXAM=10, TITLE=11, DESCRIPTION=12, FEEDBACK=13, START_SECTION=14, 
		END_SECTION=15, NUMBER_OF_QUESTIONS=16, QUESTIONS_TYPE=17, SCORE=18, GRADE=19, 
		FDB_GRD_TYPE=20, NUMBER=21, REAL_NUMBER=22, INTEGER=23, IDENTIFIER=24, 
		WS=25, COMMENT=26;
	public static final int
		RULE_start = 0, RULE_exam = 1, RULE_sections = 2, RULE_section = 3, RULE_header = 4, 
		RULE_properties = 5, RULE_numberOfQuestions = 6, RULE_questionsType = 7, 
		RULE_title = 8, RULE_description = 9, RULE_feedback = 10, RULE_grade = 11, 
		RULE_score = 12, RULE_startExam = 13, RULE_endExam = 14, RULE_startSection = 15, 
		RULE_endSection = 16;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "exam", "sections", "section", "header", "properties", "numberOfQuestions", 
			"questionsType", "title", "description", "feedback", "grade", "score", 
			"startExam", "endExam", "startSection", "endSection"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'numerical'", "'short-answer'", "'multiple-choice'", "'true-false'", 
			"'matching'", "'missing-words'", "';'", null, "'@start-exam'", "'@end-exam'", 
			"'@title'", "'@description'", "'@feedback'", "'@start-section'", "'@end-section'", 
			"'@number-of-questions'", "'@questions-type'", "'@score'", "'@grade'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, "EOI", "STRING", "START_EXAM", 
			"END_EXAM", "TITLE", "DESCRIPTION", "FEEDBACK", "START_SECTION", "END_SECTION", 
			"NUMBER_OF_QUESTIONS", "QUESTIONS_TYPE", "SCORE", "GRADE", "FDB_GRD_TYPE", 
			"NUMBER", "REAL_NUMBER", "INTEGER", "IDENTIFIER", "WS", "COMMENT"
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
	public String getGrammarFileName() { return "FormativeExam.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public FormativeExamParser(TokenStream input) {
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
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).enterStart(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).exitStart(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormativeExamVisitor ) return ((FormativeExamVisitor<? extends T>)visitor).visitStart(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartContext start() throws RecognitionException {
		StartContext _localctx = new StartContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_start);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(34);
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
		public StartExamContext startExam() {
			return getRuleContext(StartExamContext.class,0);
		}
		public HeaderContext header() {
			return getRuleContext(HeaderContext.class,0);
		}
		public SectionsContext sections() {
			return getRuleContext(SectionsContext.class,0);
		}
		public EndExamContext endExam() {
			return getRuleContext(EndExamContext.class,0);
		}
		public ExamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exam; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).enterExam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).exitExam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormativeExamVisitor ) return ((FormativeExamVisitor<? extends T>)visitor).visitExam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExamContext exam() throws RecognitionException {
		ExamContext _localctx = new ExamContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_exam);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(36);
			startExam();
			setState(37);
			header();
			setState(38);
			sections();
			setState(39);
			endExam();
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
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).enterSections(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).exitSections(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormativeExamVisitor ) return ((FormativeExamVisitor<? extends T>)visitor).visitSections(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SectionsContext sections() throws RecognitionException {
		SectionsContext _localctx = new SectionsContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_sections);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(42); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(41);
				section();
				}
				}
				setState(44); 
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
		public StartSectionContext startSection() {
			return getRuleContext(StartSectionContext.class,0);
		}
		public HeaderContext header() {
			return getRuleContext(HeaderContext.class,0);
		}
		public NumberOfQuestionsContext numberOfQuestions() {
			return getRuleContext(NumberOfQuestionsContext.class,0);
		}
		public QuestionsTypeContext questionsType() {
			return getRuleContext(QuestionsTypeContext.class,0);
		}
		public EndSectionContext endSection() {
			return getRuleContext(EndSectionContext.class,0);
		}
		public SectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_section; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).enterSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).exitSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormativeExamVisitor ) return ((FormativeExamVisitor<? extends T>)visitor).visitSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SectionContext section() throws RecognitionException {
		SectionContext _localctx = new SectionContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_section);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(46);
			startSection();
			setState(47);
			header();
			setState(48);
			numberOfQuestions();
			setState(49);
			questionsType();
			setState(50);
			endSection();
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

	public static class HeaderContext extends ParserRuleContext {
		public List<PropertiesContext> properties() {
			return getRuleContexts(PropertiesContext.class);
		}
		public PropertiesContext properties(int i) {
			return getRuleContext(PropertiesContext.class,i);
		}
		public HeaderContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_header; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).enterHeader(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).exitHeader(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormativeExamVisitor ) return ((FormativeExamVisitor<? extends T>)visitor).visitHeader(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HeaderContext header() throws RecognitionException {
		HeaderContext _localctx = new HeaderContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_header);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(53); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(52);
				properties();
				}
				}
				setState(55); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TITLE) | (1L << DESCRIPTION) | (1L << FEEDBACK) | (1L << SCORE) | (1L << GRADE))) != 0) );
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

	public static class PropertiesContext extends ParserRuleContext {
		public TitleContext title() {
			return getRuleContext(TitleContext.class,0);
		}
		public DescriptionContext description() {
			return getRuleContext(DescriptionContext.class,0);
		}
		public FeedbackContext feedback() {
			return getRuleContext(FeedbackContext.class,0);
		}
		public GradeContext grade() {
			return getRuleContext(GradeContext.class,0);
		}
		public ScoreContext score() {
			return getRuleContext(ScoreContext.class,0);
		}
		public PropertiesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_properties; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).enterProperties(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).exitProperties(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormativeExamVisitor ) return ((FormativeExamVisitor<? extends T>)visitor).visitProperties(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertiesContext properties() throws RecognitionException {
		PropertiesContext _localctx = new PropertiesContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_properties);
		try {
			setState(62);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TITLE:
				enterOuterAlt(_localctx, 1);
				{
				setState(57);
				title();
				}
				break;
			case DESCRIPTION:
				enterOuterAlt(_localctx, 2);
				{
				setState(58);
				description();
				}
				break;
			case FEEDBACK:
				enterOuterAlt(_localctx, 3);
				{
				setState(59);
				feedback();
				}
				break;
			case GRADE:
				enterOuterAlt(_localctx, 4);
				{
				setState(60);
				grade();
				}
				break;
			case SCORE:
				enterOuterAlt(_localctx, 5);
				{
				setState(61);
				score();
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class NumberOfQuestionsContext extends ParserRuleContext {
		public TerminalNode NUMBER_OF_QUESTIONS() { return getToken(FormativeExamParser.NUMBER_OF_QUESTIONS, 0); }
		public TerminalNode NUMBER() { return getToken(FormativeExamParser.NUMBER, 0); }
		public TerminalNode EOI() { return getToken(FormativeExamParser.EOI, 0); }
		public NumberOfQuestionsContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numberOfQuestions; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).enterNumberOfQuestions(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).exitNumberOfQuestions(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormativeExamVisitor ) return ((FormativeExamVisitor<? extends T>)visitor).visitNumberOfQuestions(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumberOfQuestionsContext numberOfQuestions() throws RecognitionException {
		NumberOfQuestionsContext _localctx = new NumberOfQuestionsContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_numberOfQuestions);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(64);
			match(NUMBER_OF_QUESTIONS);
			setState(65);
			match(NUMBER);
			setState(66);
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

	public static class QuestionsTypeContext extends ParserRuleContext {
		public TerminalNode QUESTIONS_TYPE() { return getToken(FormativeExamParser.QUESTIONS_TYPE, 0); }
		public TerminalNode EOI() { return getToken(FormativeExamParser.EOI, 0); }
		public QuestionsTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_questionsType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).enterQuestionsType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).exitQuestionsType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormativeExamVisitor ) return ((FormativeExamVisitor<? extends T>)visitor).visitQuestionsType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionsTypeContext questionsType() throws RecognitionException {
		QuestionsTypeContext _localctx = new QuestionsTypeContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_questionsType);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(68);
			match(QUESTIONS_TYPE);
			setState(69);
			_la = _input.LA(1);
			if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__0) | (1L << T__1) | (1L << T__2) | (1L << T__3) | (1L << T__4) | (1L << T__5))) != 0)) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(70);
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
		public TerminalNode TITLE() { return getToken(FormativeExamParser.TITLE, 0); }
		public TerminalNode STRING() { return getToken(FormativeExamParser.STRING, 0); }
		public TerminalNode EOI() { return getToken(FormativeExamParser.EOI, 0); }
		public TitleContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_title; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).enterTitle(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).exitTitle(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormativeExamVisitor ) return ((FormativeExamVisitor<? extends T>)visitor).visitTitle(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TitleContext title() throws RecognitionException {
		TitleContext _localctx = new TitleContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_title);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(72);
			match(TITLE);
			setState(73);
			match(STRING);
			setState(74);
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

	public static class DescriptionContext extends ParserRuleContext {
		public TerminalNode DESCRIPTION() { return getToken(FormativeExamParser.DESCRIPTION, 0); }
		public TerminalNode STRING() { return getToken(FormativeExamParser.STRING, 0); }
		public TerminalNode EOI() { return getToken(FormativeExamParser.EOI, 0); }
		public DescriptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_description; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).enterDescription(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).exitDescription(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormativeExamVisitor ) return ((FormativeExamVisitor<? extends T>)visitor).visitDescription(this);
			else return visitor.visitChildren(this);
		}
	}

	public final DescriptionContext description() throws RecognitionException {
		DescriptionContext _localctx = new DescriptionContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_description);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76);
			match(DESCRIPTION);
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

	public static class FeedbackContext extends ParserRuleContext {
		public TerminalNode FEEDBACK() { return getToken(FormativeExamParser.FEEDBACK, 0); }
		public TerminalNode FDB_GRD_TYPE() { return getToken(FormativeExamParser.FDB_GRD_TYPE, 0); }
		public TerminalNode EOI() { return getToken(FormativeExamParser.EOI, 0); }
		public FeedbackContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_feedback; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).enterFeedback(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).exitFeedback(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormativeExamVisitor ) return ((FormativeExamVisitor<? extends T>)visitor).visitFeedback(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FeedbackContext feedback() throws RecognitionException {
		FeedbackContext _localctx = new FeedbackContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_feedback);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			match(FEEDBACK);
			setState(81);
			match(FDB_GRD_TYPE);
			setState(82);
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

	public static class GradeContext extends ParserRuleContext {
		public TerminalNode GRADE() { return getToken(FormativeExamParser.GRADE, 0); }
		public TerminalNode FDB_GRD_TYPE() { return getToken(FormativeExamParser.FDB_GRD_TYPE, 0); }
		public TerminalNode EOI() { return getToken(FormativeExamParser.EOI, 0); }
		public GradeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_grade; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).enterGrade(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).exitGrade(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormativeExamVisitor ) return ((FormativeExamVisitor<? extends T>)visitor).visitGrade(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GradeContext grade() throws RecognitionException {
		GradeContext _localctx = new GradeContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_grade);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(84);
			match(GRADE);
			setState(85);
			match(FDB_GRD_TYPE);
			setState(86);
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
		public TerminalNode SCORE() { return getToken(FormativeExamParser.SCORE, 0); }
		public TerminalNode NUMBER() { return getToken(FormativeExamParser.NUMBER, 0); }
		public TerminalNode EOI() { return getToken(FormativeExamParser.EOI, 0); }
		public ScoreContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_score; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).enterScore(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).exitScore(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormativeExamVisitor ) return ((FormativeExamVisitor<? extends T>)visitor).visitScore(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScoreContext score() throws RecognitionException {
		ScoreContext _localctx = new ScoreContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_score);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(88);
			match(SCORE);
			setState(89);
			match(NUMBER);
			setState(90);
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

	public static class StartExamContext extends ParserRuleContext {
		public TerminalNode START_EXAM() { return getToken(FormativeExamParser.START_EXAM, 0); }
		public TerminalNode IDENTIFIER() { return getToken(FormativeExamParser.IDENTIFIER, 0); }
		public TerminalNode EOI() { return getToken(FormativeExamParser.EOI, 0); }
		public StartExamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_startExam; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).enterStartExam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).exitStartExam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormativeExamVisitor ) return ((FormativeExamVisitor<? extends T>)visitor).visitStartExam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartExamContext startExam() throws RecognitionException {
		StartExamContext _localctx = new StartExamContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_startExam);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(92);
			match(START_EXAM);
			setState(93);
			match(IDENTIFIER);
			setState(94);
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

	public static class EndExamContext extends ParserRuleContext {
		public TerminalNode END_EXAM() { return getToken(FormativeExamParser.END_EXAM, 0); }
		public TerminalNode EOI() { return getToken(FormativeExamParser.EOI, 0); }
		public EndExamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_endExam; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).enterEndExam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).exitEndExam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormativeExamVisitor ) return ((FormativeExamVisitor<? extends T>)visitor).visitEndExam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EndExamContext endExam() throws RecognitionException {
		EndExamContext _localctx = new EndExamContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_endExam);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(96);
			match(END_EXAM);
			setState(97);
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

	public static class StartSectionContext extends ParserRuleContext {
		public TerminalNode START_SECTION() { return getToken(FormativeExamParser.START_SECTION, 0); }
		public TerminalNode IDENTIFIER() { return getToken(FormativeExamParser.IDENTIFIER, 0); }
		public TerminalNode EOI() { return getToken(FormativeExamParser.EOI, 0); }
		public StartSectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_startSection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).enterStartSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).exitStartSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormativeExamVisitor ) return ((FormativeExamVisitor<? extends T>)visitor).visitStartSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StartSectionContext startSection() throws RecognitionException {
		StartSectionContext _localctx = new StartSectionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_startSection);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(99);
			match(START_SECTION);
			setState(100);
			match(IDENTIFIER);
			setState(101);
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

	public static class EndSectionContext extends ParserRuleContext {
		public TerminalNode END_SECTION() { return getToken(FormativeExamParser.END_SECTION, 0); }
		public TerminalNode EOI() { return getToken(FormativeExamParser.EOI, 0); }
		public EndSectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_endSection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).enterEndSection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FormativeExamListener ) ((FormativeExamListener)listener).exitEndSection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof FormativeExamVisitor ) return ((FormativeExamVisitor<? extends T>)visitor).visitEndSection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final EndSectionContext endSection() throws RecognitionException {
		EndSectionContext _localctx = new EndSectionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_endSection);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(103);
			match(END_SECTION);
			setState(104);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3\34m\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t\13\4"+
		"\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22\3\2\3"+
		"\2\3\3\3\3\3\3\3\3\3\3\3\4\6\4-\n\4\r\4\16\4.\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\6\6\68\n\6\r\6\16\69\3\7\3\7\3\7\3\7\3\7\5\7A\n\7\3\b\3\b\3\b\3\b\3"+
		"\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r"+
		"\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3"+
		"\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\2\2\23\2\4\6\b\n\f\16\20\22\24"+
		"\26\30\32\34\36 \"\2\3\3\2\3\b\2a\2$\3\2\2\2\4&\3\2\2\2\6,\3\2\2\2\b\60"+
		"\3\2\2\2\n\67\3\2\2\2\f@\3\2\2\2\16B\3\2\2\2\20F\3\2\2\2\22J\3\2\2\2\24"+
		"N\3\2\2\2\26R\3\2\2\2\30V\3\2\2\2\32Z\3\2\2\2\34^\3\2\2\2\36b\3\2\2\2"+
		" e\3\2\2\2\"i\3\2\2\2$%\5\4\3\2%\3\3\2\2\2&\'\5\34\17\2\'(\5\n\6\2()\5"+
		"\6\4\2)*\5\36\20\2*\5\3\2\2\2+-\5\b\5\2,+\3\2\2\2-.\3\2\2\2.,\3\2\2\2"+
		"./\3\2\2\2/\7\3\2\2\2\60\61\5 \21\2\61\62\5\n\6\2\62\63\5\16\b\2\63\64"+
		"\5\20\t\2\64\65\5\"\22\2\65\t\3\2\2\2\668\5\f\7\2\67\66\3\2\2\289\3\2"+
		"\2\29\67\3\2\2\29:\3\2\2\2:\13\3\2\2\2;A\5\22\n\2<A\5\24\13\2=A\5\26\f"+
		"\2>A\5\30\r\2?A\5\32\16\2@;\3\2\2\2@<\3\2\2\2@=\3\2\2\2@>\3\2\2\2@?\3"+
		"\2\2\2A\r\3\2\2\2BC\7\22\2\2CD\7\27\2\2DE\7\t\2\2E\17\3\2\2\2FG\7\23\2"+
		"\2GH\t\2\2\2HI\7\t\2\2I\21\3\2\2\2JK\7\r\2\2KL\7\n\2\2LM\7\t\2\2M\23\3"+
		"\2\2\2NO\7\16\2\2OP\7\n\2\2PQ\7\t\2\2Q\25\3\2\2\2RS\7\17\2\2ST\7\26\2"+
		"\2TU\7\t\2\2U\27\3\2\2\2VW\7\25\2\2WX\7\26\2\2XY\7\t\2\2Y\31\3\2\2\2Z"+
		"[\7\24\2\2[\\\7\27\2\2\\]\7\t\2\2]\33\3\2\2\2^_\7\13\2\2_`\7\32\2\2`a"+
		"\7\t\2\2a\35\3\2\2\2bc\7\f\2\2cd\7\t\2\2d\37\3\2\2\2ef\7\20\2\2fg\7\32"+
		"\2\2gh\7\t\2\2h!\3\2\2\2ij\7\21\2\2jk\7\t\2\2k#\3\2\2\2\5.9@";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}