// Generated from /home/drew/Faculdade/LAPR4/sem4pi-22-23-19/ecourse.core/src/main/java/eapli/ecourse/exammanagement/domain/grammars/Exam/Exam.g4 by ANTLR 4.9.2
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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, EOI=7, STRING=8, START_EXAM=9, 
		END_EXAM=10, TITLE=11, DESCRIPTION=12, FEEDBACK=13, GRADE=14, START_SECTION=15, 
		END_SECTION=16, SCORE=17, START_QUESTION=18, END_QUESTION=19, TYPE=20, 
		QUESTION_BODY=21, START_CORRECT_ANSWERS_SECTION=22, CORRECT_ANSWER=23, 
		END_CORRECT_ANSWERS_SECTION=24, ACCEPTED_ERROR=25, START_OPTIONS_SECTION=26, 
		END_OPTIONS_SECTION=27, OPTION=28, START_MATCHING_SECTION=29, END_MATCHING_SECTION=30, 
		MATCH=31, TRUE=32, FALSE=33, FDB_GRD_TYPE=34, NUMBER=35, REAL_NUMBER=36, 
		IDENTIFIER=37, WS=38, COMMENT=39;
	public static final int
		RULE_start = 0, RULE_exam = 1, RULE_sections = 2, RULE_section = 3, RULE_questions = 4, 
		RULE_header = 5, RULE_properties = 6, RULE_title = 7, RULE_description = 8, 
		RULE_feedback_header = 9, RULE_grade = 10, RULE_start_exam = 11, RULE_end_exam = 12, 
		RULE_start_section = 13, RULE_end_section = 14, RULE_question = 15, RULE_numericalQuestion = 16, 
		RULE_multipleChoiceQuestion = 17, RULE_shortAnswerQuestion = 18, RULE_trueFalseQuestion = 19, 
		RULE_matchingQuestion = 20, RULE_missingWordsQuestion = 21, RULE_body = 22, 
		RULE_feedback = 23, RULE_score = 24, RULE_shortAnswerCorrectAnswer = 25, 
		RULE_multipleChoiceCorrectAnswer = 26, RULE_numericalCorrectAnswer = 27, 
		RULE_numericalAcceptedError = 28, RULE_option = 29, RULE_match = 30, RULE_matchingCorrectAnswer = 31, 
		RULE_missingWordsCorrectAnswer = 32, RULE_trueFalseCorrectAnswer = 33;
	private static String[] makeRuleNames() {
		return new String[] {
			"start", "exam", "sections", "section", "questions", "header", "properties", 
			"title", "description", "feedback_header", "grade", "start_exam", "end_exam", 
			"start_section", "end_section", "question", "numericalQuestion", "multipleChoiceQuestion", 
			"shortAnswerQuestion", "trueFalseQuestion", "matchingQuestion", "missingWordsQuestion", 
			"body", "feedback", "score", "shortAnswerCorrectAnswer", "multipleChoiceCorrectAnswer", 
			"numericalCorrectAnswer", "numericalAcceptedError", "option", "match", 
			"matchingCorrectAnswer", "missingWordsCorrectAnswer", "trueFalseCorrectAnswer"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'numerical'", "'multiple-choice'", "'short-answer'", "'true-false'", 
			"'matching'", "'missing-words'", "';'", null, "'@start-exam'", "'@end-exam'", 
			"'@title'", "'@description'", "'@feedback'", "'@grade'", "'@start-section'", 
			"'@end-section'", "'@score'", "'@start-question'", "'@end-question'", 
			"'@type'", "'@question-body'", "'@start-correct-answers'", "'@correct-answer'", 
			"'@end-correct-answers'", "'@accepted-error'", "'@start-options'", "'@end-options'", 
			"'@option'", "'@start-matching'", "'@end-matching'", "'@match'", "'true'", 
			"'false'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, "EOI", "STRING", "START_EXAM", 
			"END_EXAM", "TITLE", "DESCRIPTION", "FEEDBACK", "GRADE", "START_SECTION", 
			"END_SECTION", "SCORE", "START_QUESTION", "END_QUESTION", "TYPE", "QUESTION_BODY", 
			"START_CORRECT_ANSWERS_SECTION", "CORRECT_ANSWER", "END_CORRECT_ANSWERS_SECTION", 
			"ACCEPTED_ERROR", "START_OPTIONS_SECTION", "END_OPTIONS_SECTION", "OPTION", 
			"START_MATCHING_SECTION", "END_MATCHING_SECTION", "MATCH", "TRUE", "FALSE", 
			"FDB_GRD_TYPE", "NUMBER", "REAL_NUMBER", "IDENTIFIER", "WS", "COMMENT"
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
			setState(68);
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
		public Start_examContext start_exam() {
			return getRuleContext(Start_examContext.class,0);
		}
		public HeaderContext header() {
			return getRuleContext(HeaderContext.class,0);
		}
		public SectionsContext sections() {
			return getRuleContext(SectionsContext.class,0);
		}
		public End_examContext end_exam() {
			return getRuleContext(End_examContext.class,0);
		}
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
			setState(70);
			start_exam();
			setState(71);
			header();
			setState(72);
			sections();
			setState(73);
			end_exam();
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
		enterRule(_localctx, 4, RULE_sections);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(76); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(75);
				section();
				}
				}
				setState(78); 
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
		public Start_sectionContext start_section() {
			return getRuleContext(Start_sectionContext.class,0);
		}
		public HeaderContext header() {
			return getRuleContext(HeaderContext.class,0);
		}
		public QuestionsContext questions() {
			return getRuleContext(QuestionsContext.class,0);
		}
		public End_sectionContext end_section() {
			return getRuleContext(End_sectionContext.class,0);
		}
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
		enterRule(_localctx, 6, RULE_section);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(80);
			start_section();
			setState(81);
			header();
			setState(82);
			questions();
			setState(83);
			end_section();
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
		public List<QuestionContext> question() {
			return getRuleContexts(QuestionContext.class);
		}
		public QuestionContext question(int i) {
			return getRuleContext(QuestionContext.class,i);
		}
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
		enterRule(_localctx, 8, RULE_questions);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(86); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(85);
				question();
				}
				}
				setState(88); 
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
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterHeader(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitHeader(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitHeader(this);
			else return visitor.visitChildren(this);
		}
	}

	public final HeaderContext header() throws RecognitionException {
		HeaderContext _localctx = new HeaderContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_header);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(91); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(90);
				properties();
				}
				}
				setState(93); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( (((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << TITLE) | (1L << DESCRIPTION) | (1L << FEEDBACK) | (1L << GRADE))) != 0) );
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
		public Feedback_headerContext feedback_header() {
			return getRuleContext(Feedback_headerContext.class,0);
		}
		public GradeContext grade() {
			return getRuleContext(GradeContext.class,0);
		}
		public PropertiesContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_properties; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterProperties(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitProperties(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitProperties(this);
			else return visitor.visitChildren(this);
		}
	}

	public final PropertiesContext properties() throws RecognitionException {
		PropertiesContext _localctx = new PropertiesContext(_ctx, getState());
		enterRule(_localctx, 12, RULE_properties);
		try {
			setState(99);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case TITLE:
				enterOuterAlt(_localctx, 1);
				{
				setState(95);
				title();
				}
				break;
			case DESCRIPTION:
				enterOuterAlt(_localctx, 2);
				{
				setState(96);
				description();
				}
				break;
			case FEEDBACK:
				enterOuterAlt(_localctx, 3);
				{
				setState(97);
				feedback_header();
				}
				break;
			case GRADE:
				enterOuterAlt(_localctx, 4);
				{
				setState(98);
				grade();
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

	public static class TitleContext extends ParserRuleContext {
		public TerminalNode TITLE() { return getToken(ExamParser.TITLE, 0); }
		public TerminalNode STRING() { return getToken(ExamParser.STRING, 0); }
		public TerminalNode EOI() { return getToken(ExamParser.EOI, 0); }
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
		enterRule(_localctx, 14, RULE_title);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(101);
			match(TITLE);
			setState(102);
			match(STRING);
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

	public static class DescriptionContext extends ParserRuleContext {
		public TerminalNode DESCRIPTION() { return getToken(ExamParser.DESCRIPTION, 0); }
		public TerminalNode STRING() { return getToken(ExamParser.STRING, 0); }
		public TerminalNode EOI() { return getToken(ExamParser.EOI, 0); }
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
		enterRule(_localctx, 16, RULE_description);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(105);
			match(DESCRIPTION);
			setState(106);
			match(STRING);
			setState(107);
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

	public static class Feedback_headerContext extends ParserRuleContext {
		public TerminalNode FEEDBACK() { return getToken(ExamParser.FEEDBACK, 0); }
		public TerminalNode FDB_GRD_TYPE() { return getToken(ExamParser.FDB_GRD_TYPE, 0); }
		public TerminalNode EOI() { return getToken(ExamParser.EOI, 0); }
		public Feedback_headerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_feedback_header; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterFeedback_header(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitFeedback_header(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitFeedback_header(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Feedback_headerContext feedback_header() throws RecognitionException {
		Feedback_headerContext _localctx = new Feedback_headerContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_feedback_header);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(109);
			match(FEEDBACK);
			setState(110);
			match(FDB_GRD_TYPE);
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

	public static class GradeContext extends ParserRuleContext {
		public TerminalNode GRADE() { return getToken(ExamParser.GRADE, 0); }
		public TerminalNode FDB_GRD_TYPE() { return getToken(ExamParser.FDB_GRD_TYPE, 0); }
		public TerminalNode EOI() { return getToken(ExamParser.EOI, 0); }
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
		enterRule(_localctx, 20, RULE_grade);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(113);
			match(GRADE);
			setState(114);
			match(FDB_GRD_TYPE);
			setState(115);
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

	public static class Start_examContext extends ParserRuleContext {
		public TerminalNode START_EXAM() { return getToken(ExamParser.START_EXAM, 0); }
		public TerminalNode IDENTIFIER() { return getToken(ExamParser.IDENTIFIER, 0); }
		public TerminalNode EOI() { return getToken(ExamParser.EOI, 0); }
		public Start_examContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start_exam; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterStart_exam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitStart_exam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitStart_exam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Start_examContext start_exam() throws RecognitionException {
		Start_examContext _localctx = new Start_examContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_start_exam);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(117);
			match(START_EXAM);
			setState(118);
			match(IDENTIFIER);
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

	public static class End_examContext extends ParserRuleContext {
		public TerminalNode END_EXAM() { return getToken(ExamParser.END_EXAM, 0); }
		public TerminalNode EOI() { return getToken(ExamParser.EOI, 0); }
		public End_examContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_end_exam; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterEnd_exam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitEnd_exam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitEnd_exam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final End_examContext end_exam() throws RecognitionException {
		End_examContext _localctx = new End_examContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_end_exam);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(121);
			match(END_EXAM);
			setState(122);
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

	public static class Start_sectionContext extends ParserRuleContext {
		public TerminalNode START_SECTION() { return getToken(ExamParser.START_SECTION, 0); }
		public TerminalNode IDENTIFIER() { return getToken(ExamParser.IDENTIFIER, 0); }
		public TerminalNode EOI() { return getToken(ExamParser.EOI, 0); }
		public Start_sectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_start_section; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterStart_section(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitStart_section(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitStart_section(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Start_sectionContext start_section() throws RecognitionException {
		Start_sectionContext _localctx = new Start_sectionContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_start_section);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(124);
			match(START_SECTION);
			setState(125);
			match(IDENTIFIER);
			setState(126);
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

	public static class End_sectionContext extends ParserRuleContext {
		public TerminalNode END_SECTION() { return getToken(ExamParser.END_SECTION, 0); }
		public TerminalNode EOI() { return getToken(ExamParser.EOI, 0); }
		public End_sectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_end_section; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterEnd_section(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitEnd_section(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitEnd_section(this);
			else return visitor.visitChildren(this);
		}
	}

	public final End_sectionContext end_section() throws RecognitionException {
		End_sectionContext _localctx = new End_sectionContext(_ctx, getState());
		enterRule(_localctx, 28, RULE_end_section);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(128);
			match(END_SECTION);
			setState(129);
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

	public static class QuestionContext extends ParserRuleContext {
		public TerminalNode START_QUESTION() { return getToken(ExamParser.START_QUESTION, 0); }
		public TerminalNode TYPE() { return getToken(ExamParser.TYPE, 0); }
		public TerminalNode END_QUESTION() { return getToken(ExamParser.END_QUESTION, 0); }
		public TerminalNode EOI() { return getToken(ExamParser.EOI, 0); }
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
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final QuestionContext question() throws RecognitionException {
		QuestionContext _localctx = new QuestionContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_question);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(131);
			match(START_QUESTION);
			setState(132);
			match(TYPE);
			setState(139);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__0:
				{
				setState(133);
				numericalQuestion();
				}
				break;
			case T__1:
				{
				setState(134);
				multipleChoiceQuestion();
				}
				break;
			case T__2:
				{
				setState(135);
				shortAnswerQuestion();
				}
				break;
			case T__3:
				{
				setState(136);
				trueFalseQuestion();
				}
				break;
			case T__4:
				{
				setState(137);
				matchingQuestion();
				}
				break;
			case T__5:
				{
				setState(138);
				missingWordsQuestion();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(141);
			match(END_QUESTION);
			setState(142);
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
		public TerminalNode EOI() { return getToken(ExamParser.EOI, 0); }
		public ScoreContext score() {
			return getRuleContext(ScoreContext.class,0);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public NumericalCorrectAnswerContext numericalCorrectAnswer() {
			return getRuleContext(NumericalCorrectAnswerContext.class,0);
		}
		public NumericalAcceptedErrorContext numericalAcceptedError() {
			return getRuleContext(NumericalAcceptedErrorContext.class,0);
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
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterNumericalQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitNumericalQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitNumericalQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumericalQuestionContext numericalQuestion() throws RecognitionException {
		NumericalQuestionContext _localctx = new NumericalQuestionContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_numericalQuestion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(144);
			match(T__0);
			setState(145);
			match(EOI);
			setState(146);
			score();
			setState(147);
			body();
			setState(149);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FEEDBACK) {
				{
				setState(148);
				feedback();
				}
			}

			setState(151);
			numericalCorrectAnswer();
			setState(152);
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
		public List<TerminalNode> EOI() { return getTokens(ExamParser.EOI); }
		public TerminalNode EOI(int i) {
			return getToken(ExamParser.EOI, i);
		}
		public ScoreContext score() {
			return getRuleContext(ScoreContext.class,0);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public TerminalNode START_OPTIONS_SECTION() { return getToken(ExamParser.START_OPTIONS_SECTION, 0); }
		public TerminalNode END_OPTIONS_SECTION() { return getToken(ExamParser.END_OPTIONS_SECTION, 0); }
		public TerminalNode START_CORRECT_ANSWERS_SECTION() { return getToken(ExamParser.START_CORRECT_ANSWERS_SECTION, 0); }
		public TerminalNode END_CORRECT_ANSWERS_SECTION() { return getToken(ExamParser.END_CORRECT_ANSWERS_SECTION, 0); }
		public List<MultipleChoiceCorrectAnswerContext> multipleChoiceCorrectAnswer() {
			return getRuleContexts(MultipleChoiceCorrectAnswerContext.class);
		}
		public MultipleChoiceCorrectAnswerContext multipleChoiceCorrectAnswer(int i) {
			return getRuleContext(MultipleChoiceCorrectAnswerContext.class,i);
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
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterMultipleChoiceQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitMultipleChoiceQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitMultipleChoiceQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultipleChoiceQuestionContext multipleChoiceQuestion() throws RecognitionException {
		MultipleChoiceQuestionContext _localctx = new MultipleChoiceQuestionContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_multipleChoiceQuestion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(154);
			match(T__1);
			setState(155);
			match(EOI);
			setState(156);
			score();
			setState(157);
			body();
			setState(159);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FEEDBACK) {
				{
				setState(158);
				feedback();
				}
			}

			setState(171);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case START_CORRECT_ANSWERS_SECTION:
				{
				setState(161);
				match(START_CORRECT_ANSWERS_SECTION);
				setState(163); 
				_errHandler.sync(this);
				_la = _input.LA(1);
				do {
					{
					{
					setState(162);
					multipleChoiceCorrectAnswer();
					}
					}
					setState(165); 
					_errHandler.sync(this);
					_la = _input.LA(1);
				} while ( _la==CORRECT_ANSWER );
				setState(167);
				match(END_CORRECT_ANSWERS_SECTION);
				setState(168);
				match(EOI);
				}
				break;
			case CORRECT_ANSWER:
				{
				setState(170);
				multipleChoiceCorrectAnswer();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
			setState(173);
			match(START_OPTIONS_SECTION);
			setState(175); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(174);
				option();
				}
				}
				setState(177); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==OPTION );
			setState(179);
			match(END_OPTIONS_SECTION);
			setState(180);
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
		public List<TerminalNode> EOI() { return getTokens(ExamParser.EOI); }
		public TerminalNode EOI(int i) {
			return getToken(ExamParser.EOI, i);
		}
		public ScoreContext score() {
			return getRuleContext(ScoreContext.class,0);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public TerminalNode START_CORRECT_ANSWERS_SECTION() { return getToken(ExamParser.START_CORRECT_ANSWERS_SECTION, 0); }
		public TerminalNode END_CORRECT_ANSWERS_SECTION() { return getToken(ExamParser.END_CORRECT_ANSWERS_SECTION, 0); }
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
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterShortAnswerQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitShortAnswerQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitShortAnswerQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ShortAnswerQuestionContext shortAnswerQuestion() throws RecognitionException {
		ShortAnswerQuestionContext _localctx = new ShortAnswerQuestionContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_shortAnswerQuestion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(182);
			match(T__2);
			setState(183);
			match(EOI);
			setState(184);
			score();
			setState(185);
			body();
			setState(187);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FEEDBACK) {
				{
				setState(186);
				feedback();
				}
			}

			setState(189);
			match(START_CORRECT_ANSWERS_SECTION);
			setState(191); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(190);
				shortAnswerCorrectAnswer();
				}
				}
				setState(193); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CORRECT_ANSWER );
			setState(195);
			match(END_CORRECT_ANSWERS_SECTION);
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

	public static class TrueFalseQuestionContext extends ParserRuleContext {
		public TerminalNode EOI() { return getToken(ExamParser.EOI, 0); }
		public ScoreContext score() {
			return getRuleContext(ScoreContext.class,0);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public TrueFalseCorrectAnswerContext trueFalseCorrectAnswer() {
			return getRuleContext(TrueFalseCorrectAnswerContext.class,0);
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
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterTrueFalseQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitTrueFalseQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitTrueFalseQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TrueFalseQuestionContext trueFalseQuestion() throws RecognitionException {
		TrueFalseQuestionContext _localctx = new TrueFalseQuestionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_trueFalseQuestion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(198);
			match(T__3);
			setState(199);
			match(EOI);
			setState(200);
			score();
			setState(201);
			body();
			setState(203);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FEEDBACK) {
				{
				setState(202);
				feedback();
				}
			}

			setState(205);
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
		public List<TerminalNode> EOI() { return getTokens(ExamParser.EOI); }
		public TerminalNode EOI(int i) {
			return getToken(ExamParser.EOI, i);
		}
		public ScoreContext score() {
			return getRuleContext(ScoreContext.class,0);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public TerminalNode START_CORRECT_ANSWERS_SECTION() { return getToken(ExamParser.START_CORRECT_ANSWERS_SECTION, 0); }
		public TerminalNode END_CORRECT_ANSWERS_SECTION() { return getToken(ExamParser.END_CORRECT_ANSWERS_SECTION, 0); }
		public TerminalNode START_OPTIONS_SECTION() { return getToken(ExamParser.START_OPTIONS_SECTION, 0); }
		public TerminalNode END_OPTIONS_SECTION() { return getToken(ExamParser.END_OPTIONS_SECTION, 0); }
		public TerminalNode START_MATCHING_SECTION() { return getToken(ExamParser.START_MATCHING_SECTION, 0); }
		public TerminalNode END_MATCHING_SECTION() { return getToken(ExamParser.END_MATCHING_SECTION, 0); }
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
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterMatchingQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitMatchingQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitMatchingQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchingQuestionContext matchingQuestion() throws RecognitionException {
		MatchingQuestionContext _localctx = new MatchingQuestionContext(_ctx, getState());
		enterRule(_localctx, 40, RULE_matchingQuestion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(207);
			match(T__4);
			setState(208);
			match(EOI);
			setState(209);
			score();
			setState(210);
			body();
			setState(212);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FEEDBACK) {
				{
				setState(211);
				feedback();
				}
			}

			setState(214);
			match(START_CORRECT_ANSWERS_SECTION);
			setState(216); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(215);
				matchingCorrectAnswer();
				}
				}
				setState(218); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==CORRECT_ANSWER );
			setState(220);
			match(END_CORRECT_ANSWERS_SECTION);
			setState(221);
			match(EOI);
			setState(222);
			match(START_OPTIONS_SECTION);
			setState(224); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(223);
				option();
				}
				}
				setState(226); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==OPTION );
			setState(228);
			match(END_OPTIONS_SECTION);
			setState(229);
			match(EOI);
			setState(230);
			match(START_MATCHING_SECTION);
			setState(232); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(231);
				match();
				}
				}
				setState(234); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==MATCH );
			setState(236);
			match(END_MATCHING_SECTION);
			setState(237);
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
		public List<TerminalNode> EOI() { return getTokens(ExamParser.EOI); }
		public TerminalNode EOI(int i) {
			return getToken(ExamParser.EOI, i);
		}
		public ScoreContext score() {
			return getRuleContext(ScoreContext.class,0);
		}
		public BodyContext body() {
			return getRuleContext(BodyContext.class,0);
		}
		public MissingWordsCorrectAnswerContext missingWordsCorrectAnswer() {
			return getRuleContext(MissingWordsCorrectAnswerContext.class,0);
		}
		public FeedbackContext feedback() {
			return getRuleContext(FeedbackContext.class,0);
		}
		public MissingWordsQuestionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_missingWordsQuestion; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterMissingWordsQuestion(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitMissingWordsQuestion(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitMissingWordsQuestion(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MissingWordsQuestionContext missingWordsQuestion() throws RecognitionException {
		MissingWordsQuestionContext _localctx = new MissingWordsQuestionContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_missingWordsQuestion);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(239);
			match(T__5);
			setState(240);
			match(EOI);
			setState(241);
			score();
			setState(242);
			body();
			setState(244);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==FEEDBACK) {
				{
				setState(243);
				feedback();
				}
			}

			setState(246);
			missingWordsCorrectAnswer();
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

	public static class BodyContext extends ParserRuleContext {
		public TerminalNode QUESTION_BODY() { return getToken(ExamParser.QUESTION_BODY, 0); }
		public TerminalNode STRING() { return getToken(ExamParser.STRING, 0); }
		public TerminalNode EOI() { return getToken(ExamParser.EOI, 0); }
		public BodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_body; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final BodyContext body() throws RecognitionException {
		BodyContext _localctx = new BodyContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_body);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(249);
			match(QUESTION_BODY);
			setState(250);
			match(STRING);
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

	public static class FeedbackContext extends ParserRuleContext {
		public TerminalNode FEEDBACK() { return getToken(ExamParser.FEEDBACK, 0); }
		public TerminalNode STRING() { return getToken(ExamParser.STRING, 0); }
		public TerminalNode EOI() { return getToken(ExamParser.EOI, 0); }
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
		enterRule(_localctx, 46, RULE_feedback);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(253);
			match(FEEDBACK);
			setState(254);
			match(STRING);
			setState(255);
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
		public TerminalNode SCORE() { return getToken(ExamParser.SCORE, 0); }
		public TerminalNode REAL_NUMBER() { return getToken(ExamParser.REAL_NUMBER, 0); }
		public TerminalNode EOI() { return getToken(ExamParser.EOI, 0); }
		public ScoreContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_score; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterScore(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitScore(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitScore(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ScoreContext score() throws RecognitionException {
		ScoreContext _localctx = new ScoreContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_score);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(257);
			match(SCORE);
			setState(258);
			match(REAL_NUMBER);
			setState(259);
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
		public TerminalNode CORRECT_ANSWER() { return getToken(ExamParser.CORRECT_ANSWER, 0); }
		public TerminalNode STRING() { return getToken(ExamParser.STRING, 0); }
		public TerminalNode REAL_NUMBER() { return getToken(ExamParser.REAL_NUMBER, 0); }
		public TerminalNode EOI() { return getToken(ExamParser.EOI, 0); }
		public ShortAnswerCorrectAnswerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_shortAnswerCorrectAnswer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterShortAnswerCorrectAnswer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitShortAnswerCorrectAnswer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitShortAnswerCorrectAnswer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ShortAnswerCorrectAnswerContext shortAnswerCorrectAnswer() throws RecognitionException {
		ShortAnswerCorrectAnswerContext _localctx = new ShortAnswerCorrectAnswerContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_shortAnswerCorrectAnswer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(261);
			match(CORRECT_ANSWER);
			setState(262);
			match(STRING);
			setState(263);
			match(REAL_NUMBER);
			setState(264);
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
		public TerminalNode CORRECT_ANSWER() { return getToken(ExamParser.CORRECT_ANSWER, 0); }
		public TerminalNode NUMBER() { return getToken(ExamParser.NUMBER, 0); }
		public TerminalNode EOI() { return getToken(ExamParser.EOI, 0); }
		public TerminalNode REAL_NUMBER() { return getToken(ExamParser.REAL_NUMBER, 0); }
		public MultipleChoiceCorrectAnswerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_multipleChoiceCorrectAnswer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterMultipleChoiceCorrectAnswer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitMultipleChoiceCorrectAnswer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitMultipleChoiceCorrectAnswer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MultipleChoiceCorrectAnswerContext multipleChoiceCorrectAnswer() throws RecognitionException {
		MultipleChoiceCorrectAnswerContext _localctx = new MultipleChoiceCorrectAnswerContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_multipleChoiceCorrectAnswer);
		int _la;
		try {
			setState(275);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,19,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(266);
				match(CORRECT_ANSWER);
				setState(267);
				match(NUMBER);
				setState(269);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==REAL_NUMBER) {
					{
					setState(268);
					match(REAL_NUMBER);
					}
				}

				setState(271);
				match(EOI);
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(272);
				match(CORRECT_ANSWER);
				setState(273);
				match(NUMBER);
				setState(274);
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

	public static class NumericalCorrectAnswerContext extends ParserRuleContext {
		public TerminalNode CORRECT_ANSWER() { return getToken(ExamParser.CORRECT_ANSWER, 0); }
		public TerminalNode REAL_NUMBER() { return getToken(ExamParser.REAL_NUMBER, 0); }
		public TerminalNode EOI() { return getToken(ExamParser.EOI, 0); }
		public NumericalCorrectAnswerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numericalCorrectAnswer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterNumericalCorrectAnswer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitNumericalCorrectAnswer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitNumericalCorrectAnswer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumericalCorrectAnswerContext numericalCorrectAnswer() throws RecognitionException {
		NumericalCorrectAnswerContext _localctx = new NumericalCorrectAnswerContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_numericalCorrectAnswer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(277);
			match(CORRECT_ANSWER);
			setState(278);
			match(REAL_NUMBER);
			setState(279);
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
		public TerminalNode ACCEPTED_ERROR() { return getToken(ExamParser.ACCEPTED_ERROR, 0); }
		public TerminalNode REAL_NUMBER() { return getToken(ExamParser.REAL_NUMBER, 0); }
		public TerminalNode EOI() { return getToken(ExamParser.EOI, 0); }
		public NumericalAcceptedErrorContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_numericalAcceptedError; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterNumericalAcceptedError(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitNumericalAcceptedError(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitNumericalAcceptedError(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NumericalAcceptedErrorContext numericalAcceptedError() throws RecognitionException {
		NumericalAcceptedErrorContext _localctx = new NumericalAcceptedErrorContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_numericalAcceptedError);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(281);
			match(ACCEPTED_ERROR);
			setState(282);
			match(REAL_NUMBER);
			setState(283);
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
		public TerminalNode OPTION() { return getToken(ExamParser.OPTION, 0); }
		public TerminalNode NUMBER() { return getToken(ExamParser.NUMBER, 0); }
		public List<TerminalNode> STRING() { return getTokens(ExamParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(ExamParser.STRING, i);
		}
		public TerminalNode EOI() { return getToken(ExamParser.EOI, 0); }
		public OptionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_option; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterOption(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitOption(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitOption(this);
			else return visitor.visitChildren(this);
		}
	}

	public final OptionContext option() throws RecognitionException {
		OptionContext _localctx = new OptionContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_option);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(285);
			match(OPTION);
			setState(286);
			match(NUMBER);
			setState(287);
			match(STRING);
			setState(289);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==STRING) {
				{
				setState(288);
				match(STRING);
				}
			}

			setState(291);
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
		public TerminalNode MATCH() { return getToken(ExamParser.MATCH, 0); }
		public TerminalNode NUMBER() { return getToken(ExamParser.NUMBER, 0); }
		public TerminalNode STRING() { return getToken(ExamParser.STRING, 0); }
		public TerminalNode EOI() { return getToken(ExamParser.EOI, 0); }
		public MatchContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_match; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterMatch(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitMatch(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitMatch(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchContext match() throws RecognitionException {
		MatchContext _localctx = new MatchContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_match);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(293);
			match(MATCH);
			setState(294);
			match(NUMBER);
			setState(295);
			match(STRING);
			setState(296);
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
		public TerminalNode CORRECT_ANSWER() { return getToken(ExamParser.CORRECT_ANSWER, 0); }
		public List<TerminalNode> NUMBER() { return getTokens(ExamParser.NUMBER); }
		public TerminalNode NUMBER(int i) {
			return getToken(ExamParser.NUMBER, i);
		}
		public TerminalNode EOI() { return getToken(ExamParser.EOI, 0); }
		public MatchingCorrectAnswerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_matchingCorrectAnswer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterMatchingCorrectAnswer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitMatchingCorrectAnswer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitMatchingCorrectAnswer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MatchingCorrectAnswerContext matchingCorrectAnswer() throws RecognitionException {
		MatchingCorrectAnswerContext _localctx = new MatchingCorrectAnswerContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_matchingCorrectAnswer);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(298);
			match(CORRECT_ANSWER);
			setState(299);
			match(NUMBER);
			setState(300);
			match(NUMBER);
			setState(301);
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
		public TerminalNode CORRECT_ANSWER() { return getToken(ExamParser.CORRECT_ANSWER, 0); }
		public List<TerminalNode> STRING() { return getTokens(ExamParser.STRING); }
		public TerminalNode STRING(int i) {
			return getToken(ExamParser.STRING, i);
		}
		public MissingWordsCorrectAnswerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_missingWordsCorrectAnswer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterMissingWordsCorrectAnswer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitMissingWordsCorrectAnswer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitMissingWordsCorrectAnswer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final MissingWordsCorrectAnswerContext missingWordsCorrectAnswer() throws RecognitionException {
		MissingWordsCorrectAnswerContext _localctx = new MissingWordsCorrectAnswerContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_missingWordsCorrectAnswer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(303);
			match(CORRECT_ANSWER);
			setState(304);
			match(STRING);
			setState(308);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==STRING) {
				{
				{
				setState(305);
				match(STRING);
				}
				}
				setState(310);
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

	public static class TrueFalseCorrectAnswerContext extends ParserRuleContext {
		public TerminalNode CORRECT_ANSWER() { return getToken(ExamParser.CORRECT_ANSWER, 0); }
		public TerminalNode EOI() { return getToken(ExamParser.EOI, 0); }
		public TerminalNode TRUE() { return getToken(ExamParser.TRUE, 0); }
		public TerminalNode FALSE() { return getToken(ExamParser.FALSE, 0); }
		public TrueFalseCorrectAnswerContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_trueFalseCorrectAnswer; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).enterTrueFalseCorrectAnswer(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof ExamListener ) ((ExamListener)listener).exitTrueFalseCorrectAnswer(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof ExamVisitor ) return ((ExamVisitor<? extends T>)visitor).visitTrueFalseCorrectAnswer(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TrueFalseCorrectAnswerContext trueFalseCorrectAnswer() throws RecognitionException {
		TrueFalseCorrectAnswerContext _localctx = new TrueFalseCorrectAnswerContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_trueFalseCorrectAnswer);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(311);
			match(CORRECT_ANSWER);
			setState(312);
			_la = _input.LA(1);
			if ( !(_la==TRUE || _la==FALSE) ) {
			_errHandler.recoverInline(this);
			}
			else {
				if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
				_errHandler.reportMatch(this);
				consume();
			}
			setState(313);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3)\u013e\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\4\6\4O\n\4\r\4\16\4P\3"+
		"\5\3\5\3\5\3\5\3\5\3\6\6\6Y\n\6\r\6\16\6Z\3\7\6\7^\n\7\r\7\16\7_\3\b\3"+
		"\b\3\b\3\b\5\bf\n\b\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13"+
		"\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3"+
		"\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u008e\n\21"+
		"\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\5\22\u0098\n\22\3\22\3\22\3\22"+
		"\3\23\3\23\3\23\3\23\3\23\5\23\u00a2\n\23\3\23\3\23\6\23\u00a6\n\23\r"+
		"\23\16\23\u00a7\3\23\3\23\3\23\3\23\5\23\u00ae\n\23\3\23\3\23\6\23\u00b2"+
		"\n\23\r\23\16\23\u00b3\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\5\24\u00be"+
		"\n\24\3\24\3\24\6\24\u00c2\n\24\r\24\16\24\u00c3\3\24\3\24\3\24\3\25\3"+
		"\25\3\25\3\25\3\25\5\25\u00ce\n\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26"+
		"\5\26\u00d7\n\26\3\26\3\26\6\26\u00db\n\26\r\26\16\26\u00dc\3\26\3\26"+
		"\3\26\3\26\6\26\u00e3\n\26\r\26\16\26\u00e4\3\26\3\26\3\26\3\26\6\26\u00eb"+
		"\n\26\r\26\16\26\u00ec\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\5\27\u00f7"+
		"\n\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\32\3\32"+
		"\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\5\34\u0110\n\34\3\34"+
		"\3\34\3\34\3\34\5\34\u0116\n\34\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36"+
		"\3\37\3\37\3\37\3\37\5\37\u0124\n\37\3\37\3\37\3 \3 \3 \3 \3 \3!\3!\3"+
		"!\3!\3!\3\"\3\"\3\"\7\"\u0135\n\"\f\"\16\"\u0138\13\"\3#\3#\3#\3#\3#\2"+
		"\2$\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60\62\64\668:<>@B"+
		"D\2\3\3\2\"#\2\u0137\2F\3\2\2\2\4H\3\2\2\2\6N\3\2\2\2\bR\3\2\2\2\nX\3"+
		"\2\2\2\f]\3\2\2\2\16e\3\2\2\2\20g\3\2\2\2\22k\3\2\2\2\24o\3\2\2\2\26s"+
		"\3\2\2\2\30w\3\2\2\2\32{\3\2\2\2\34~\3\2\2\2\36\u0082\3\2\2\2 \u0085\3"+
		"\2\2\2\"\u0092\3\2\2\2$\u009c\3\2\2\2&\u00b8\3\2\2\2(\u00c8\3\2\2\2*\u00d1"+
		"\3\2\2\2,\u00f1\3\2\2\2.\u00fb\3\2\2\2\60\u00ff\3\2\2\2\62\u0103\3\2\2"+
		"\2\64\u0107\3\2\2\2\66\u0115\3\2\2\28\u0117\3\2\2\2:\u011b\3\2\2\2<\u011f"+
		"\3\2\2\2>\u0127\3\2\2\2@\u012c\3\2\2\2B\u0131\3\2\2\2D\u0139\3\2\2\2F"+
		"G\5\4\3\2G\3\3\2\2\2HI\5\30\r\2IJ\5\f\7\2JK\5\6\4\2KL\5\32\16\2L\5\3\2"+
		"\2\2MO\5\b\5\2NM\3\2\2\2OP\3\2\2\2PN\3\2\2\2PQ\3\2\2\2Q\7\3\2\2\2RS\5"+
		"\34\17\2ST\5\f\7\2TU\5\n\6\2UV\5\36\20\2V\t\3\2\2\2WY\5 \21\2XW\3\2\2"+
		"\2YZ\3\2\2\2ZX\3\2\2\2Z[\3\2\2\2[\13\3\2\2\2\\^\5\16\b\2]\\\3\2\2\2^_"+
		"\3\2\2\2_]\3\2\2\2_`\3\2\2\2`\r\3\2\2\2af\5\20\t\2bf\5\22\n\2cf\5\24\13"+
		"\2df\5\26\f\2ea\3\2\2\2eb\3\2\2\2ec\3\2\2\2ed\3\2\2\2f\17\3\2\2\2gh\7"+
		"\r\2\2hi\7\n\2\2ij\7\t\2\2j\21\3\2\2\2kl\7\16\2\2lm\7\n\2\2mn\7\t\2\2"+
		"n\23\3\2\2\2op\7\17\2\2pq\7$\2\2qr\7\t\2\2r\25\3\2\2\2st\7\20\2\2tu\7"+
		"$\2\2uv\7\t\2\2v\27\3\2\2\2wx\7\13\2\2xy\7\'\2\2yz\7\t\2\2z\31\3\2\2\2"+
		"{|\7\f\2\2|}\7\t\2\2}\33\3\2\2\2~\177\7\21\2\2\177\u0080\7\'\2\2\u0080"+
		"\u0081\7\t\2\2\u0081\35\3\2\2\2\u0082\u0083\7\22\2\2\u0083\u0084\7\t\2"+
		"\2\u0084\37\3\2\2\2\u0085\u0086\7\24\2\2\u0086\u008d\7\26\2\2\u0087\u008e"+
		"\5\"\22\2\u0088\u008e\5$\23\2\u0089\u008e\5&\24\2\u008a\u008e\5(\25\2"+
		"\u008b\u008e\5*\26\2\u008c\u008e\5,\27\2\u008d\u0087\3\2\2\2\u008d\u0088"+
		"\3\2\2\2\u008d\u0089\3\2\2\2\u008d\u008a\3\2\2\2\u008d\u008b\3\2\2\2\u008d"+
		"\u008c\3\2\2\2\u008e\u008f\3\2\2\2\u008f\u0090\7\25\2\2\u0090\u0091\7"+
		"\t\2\2\u0091!\3\2\2\2\u0092\u0093\7\3\2\2\u0093\u0094\7\t\2\2\u0094\u0095"+
		"\5\62\32\2\u0095\u0097\5.\30\2\u0096\u0098\5\60\31\2\u0097\u0096\3\2\2"+
		"\2\u0097\u0098\3\2\2\2\u0098\u0099\3\2\2\2\u0099\u009a\58\35\2\u009a\u009b"+
		"\5:\36\2\u009b#\3\2\2\2\u009c\u009d\7\4\2\2\u009d\u009e\7\t\2\2\u009e"+
		"\u009f\5\62\32\2\u009f\u00a1\5.\30\2\u00a0\u00a2\5\60\31\2\u00a1\u00a0"+
		"\3\2\2\2\u00a1\u00a2\3\2\2\2\u00a2\u00ad\3\2\2\2\u00a3\u00a5\7\30\2\2"+
		"\u00a4\u00a6\5\66\34\2\u00a5\u00a4\3\2\2\2\u00a6\u00a7\3\2\2\2\u00a7\u00a5"+
		"\3\2\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00aa\7\32\2\2"+
		"\u00aa\u00ab\7\t\2\2\u00ab\u00ae\3\2\2\2\u00ac\u00ae\5\66\34\2\u00ad\u00a3"+
		"\3\2\2\2\u00ad\u00ac\3\2\2\2\u00ae\u00af\3\2\2\2\u00af\u00b1\7\34\2\2"+
		"\u00b0\u00b2\5<\37\2\u00b1\u00b0\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b1"+
		"\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\u00b5\3\2\2\2\u00b5\u00b6\7\35\2\2"+
		"\u00b6\u00b7\7\t\2\2\u00b7%\3\2\2\2\u00b8\u00b9\7\5\2\2\u00b9\u00ba\7"+
		"\t\2\2\u00ba\u00bb\5\62\32\2\u00bb\u00bd\5.\30\2\u00bc\u00be\5\60\31\2"+
		"\u00bd\u00bc\3\2\2\2\u00bd\u00be\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c1"+
		"\7\30\2\2\u00c0\u00c2\5\64\33\2\u00c1\u00c0\3\2\2\2\u00c2\u00c3\3\2\2"+
		"\2\u00c3\u00c1\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c5\3\2\2\2\u00c5\u00c6"+
		"\7\32\2\2\u00c6\u00c7\7\t\2\2\u00c7\'\3\2\2\2\u00c8\u00c9\7\6\2\2\u00c9"+
		"\u00ca\7\t\2\2\u00ca\u00cb\5\62\32\2\u00cb\u00cd\5.\30\2\u00cc\u00ce\5"+
		"\60\31\2\u00cd\u00cc\3\2\2\2\u00cd\u00ce\3\2\2\2\u00ce\u00cf\3\2\2\2\u00cf"+
		"\u00d0\5D#\2\u00d0)\3\2\2\2\u00d1\u00d2\7\7\2\2\u00d2\u00d3\7\t\2\2\u00d3"+
		"\u00d4\5\62\32\2\u00d4\u00d6\5.\30\2\u00d5\u00d7\5\60\31\2\u00d6\u00d5"+
		"\3\2\2\2\u00d6\u00d7\3\2\2\2\u00d7\u00d8\3\2\2\2\u00d8\u00da\7\30\2\2"+
		"\u00d9\u00db\5@!\2\u00da\u00d9\3\2\2\2\u00db\u00dc\3\2\2\2\u00dc\u00da"+
		"\3\2\2\2\u00dc\u00dd\3\2\2\2\u00dd\u00de\3\2\2\2\u00de\u00df\7\32\2\2"+
		"\u00df\u00e0\7\t\2\2\u00e0\u00e2\7\34\2\2\u00e1\u00e3\5<\37\2\u00e2\u00e1"+
		"\3\2\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e2\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5"+
		"\u00e6\3\2\2\2\u00e6\u00e7\7\35\2\2\u00e7\u00e8\7\t\2\2\u00e8\u00ea\7"+
		"\37\2\2\u00e9\u00eb\5> \2\u00ea\u00e9\3\2\2\2\u00eb\u00ec\3\2\2\2\u00ec"+
		"\u00ea\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee\u00ef\7 "+
		"\2\2\u00ef\u00f0\7\t\2\2\u00f0+\3\2\2\2\u00f1\u00f2\7\b\2\2\u00f2\u00f3"+
		"\7\t\2\2\u00f3\u00f4\5\62\32\2\u00f4\u00f6\5.\30\2\u00f5\u00f7\5\60\31"+
		"\2\u00f6\u00f5\3\2\2\2\u00f6\u00f7\3\2\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00f9"+
		"\5B\"\2\u00f9\u00fa\7\t\2\2\u00fa-\3\2\2\2\u00fb\u00fc\7\27\2\2\u00fc"+
		"\u00fd\7\n\2\2\u00fd\u00fe\7\t\2\2\u00fe/\3\2\2\2\u00ff\u0100\7\17\2\2"+
		"\u0100\u0101\7\n\2\2\u0101\u0102\7\t\2\2\u0102\61\3\2\2\2\u0103\u0104"+
		"\7\23\2\2\u0104\u0105\7&\2\2\u0105\u0106\7\t\2\2\u0106\63\3\2\2\2\u0107"+
		"\u0108\7\31\2\2\u0108\u0109\7\n\2\2\u0109\u010a\7&\2\2\u010a\u010b\7\t"+
		"\2\2\u010b\65\3\2\2\2\u010c\u010d\7\31\2\2\u010d\u010f\7%\2\2\u010e\u0110"+
		"\7&\2\2\u010f\u010e\3\2\2\2\u010f\u0110\3\2\2\2\u0110\u0111\3\2\2\2\u0111"+
		"\u0116\7\t\2\2\u0112\u0113\7\31\2\2\u0113\u0114\7%\2\2\u0114\u0116\7\t"+
		"\2\2\u0115\u010c\3\2\2\2\u0115\u0112\3\2\2\2\u0116\67\3\2\2\2\u0117\u0118"+
		"\7\31\2\2\u0118\u0119\7&\2\2\u0119\u011a\7\t\2\2\u011a9\3\2\2\2\u011b"+
		"\u011c\7\33\2\2\u011c\u011d\7&\2\2\u011d\u011e\7\t\2\2\u011e;\3\2\2\2"+
		"\u011f\u0120\7\36\2\2\u0120\u0121\7%\2\2\u0121\u0123\7\n\2\2\u0122\u0124"+
		"\7\n\2\2\u0123\u0122\3\2\2\2\u0123\u0124\3\2\2\2\u0124\u0125\3\2\2\2\u0125"+
		"\u0126\7\t\2\2\u0126=\3\2\2\2\u0127\u0128\7!\2\2\u0128\u0129\7%\2\2\u0129"+
		"\u012a\7\n\2\2\u012a\u012b\7\t\2\2\u012b?\3\2\2\2\u012c\u012d\7\31\2\2"+
		"\u012d\u012e\7%\2\2\u012e\u012f\7%\2\2\u012f\u0130\7\t\2\2\u0130A\3\2"+
		"\2\2\u0131\u0132\7\31\2\2\u0132\u0136\7\n\2\2\u0133\u0135\7\n\2\2\u0134"+
		"\u0133\3\2\2\2\u0135\u0138\3\2\2\2\u0136\u0134\3\2\2\2\u0136\u0137\3\2"+
		"\2\2\u0137C\3\2\2\2\u0138\u0136\3\2\2\2\u0139\u013a\7\31\2\2\u013a\u013b"+
		"\t\2\2\2\u013b\u013c\7\t\2\2\u013cE\3\2\2\2\30PZ_e\u008d\u0097\u00a1\u00a7"+
		"\u00ad\u00b3\u00bd\u00c3\u00cd\u00d6\u00dc\u00e4\u00ec\u00f6\u010f\u0115"+
		"\u0123\u0136";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}