// Generated from /home/drew/Faculdade/LAPR4/sem4pi-22-23-19/ecourse.core/src/main/java/eapli/ecourse/exammanagement/domain/grammars/Exam/Exam.g4 by ANTLR 4.9.2
package eapli.ecourse.exammanagement.domain.parsers;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class ExamLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "EOI", "STRING", "START_EXAM", 
			"END_EXAM", "TITLE", "DESCRIPTION", "FEEDBACK", "GRADE", "START_SECTION", 
			"END_SECTION", "SCORE", "START_QUESTION", "END_QUESTION", "TYPE", "QUESTION_BODY", 
			"START_CORRECT_ANSWERS_SECTION", "CORRECT_ANSWER", "END_CORRECT_ANSWERS_SECTION", 
			"ACCEPTED_ERROR", "START_OPTIONS_SECTION", "END_OPTIONS_SECTION", "OPTION", 
			"START_MATCHING_SECTION", "END_MATCHING_SECTION", "MATCH", "TRUE", "FALSE", 
			"FDB_GRD_TYPE", "NUMBER", "REAL_NUMBER", "IDENTIFIER", "WS", "COMMENT"
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


	public ExamLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Exam.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getChannelNames() { return channelNames; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2)\u021f\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\3\2\3\2\3\2\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b"+
		"\3\t\3\t\3\t\3\t\7\t\u00a1\n\t\f\t\16\t\u00a4\13\t\3\t\3\t\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\37\3\37"+
		"\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3 \3 \3 "+
		"\3 \3 \3 \3 \3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#"+
		"\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\5#\u01f3"+
		"\n#\3$\6$\u01f6\n$\r$\16$\u01f7\3%\6%\u01fb\n%\r%\16%\u01fc\3%\3%\6%\u0201"+
		"\n%\r%\16%\u0202\5%\u0205\n%\3&\3&\7&\u0209\n&\f&\16&\u020c\13&\3\'\6"+
		"\'\u020f\n\'\r\'\16\'\u0210\3\'\3\'\3(\3(\3(\3(\7(\u0219\n(\f(\16(\u021c"+
		"\13(\3(\3(\2\2)\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31"+
		"\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65"+
		"\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)\3\2\b\4\2$$^^\3\2\62;\4\2C\\c"+
		"|\6\2\62;C\\aac|\5\2\13\f\17\17\"\"\4\2\f\f\17\17\2\u0229\2\3\3\2\2\2"+
		"\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2"+
		"\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2"+
		"\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2"+
		"\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2\2\2\61\3\2\2"+
		"\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3\2\2\2\2=\3\2"+
		"\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2\2\2I\3\2\2\2"+
		"\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\3Q\3\2\2\2\5[\3\2\2\2\7k\3\2\2\2\tx"+
		"\3\2\2\2\13\u0083\3\2\2\2\r\u008c\3\2\2\2\17\u009a\3\2\2\2\21\u009c\3"+
		"\2\2\2\23\u00a7\3\2\2\2\25\u00b3\3\2\2\2\27\u00bd\3\2\2\2\31\u00c4\3\2"+
		"\2\2\33\u00d1\3\2\2\2\35\u00db\3\2\2\2\37\u00e2\3\2\2\2!\u00f1\3\2\2\2"+
		"#\u00fe\3\2\2\2%\u0105\3\2\2\2\'\u0115\3\2\2\2)\u0123\3\2\2\2+\u0129\3"+
		"\2\2\2-\u0138\3\2\2\2/\u014f\3\2\2\2\61\u015f\3\2\2\2\63\u0174\3\2\2\2"+
		"\65\u0184\3\2\2\2\67\u0193\3\2\2\29\u01a0\3\2\2\2;\u01a8\3\2\2\2=\u01b8"+
		"\3\2\2\2?\u01c6\3\2\2\2A\u01cd\3\2\2\2C\u01d2\3\2\2\2E\u01f2\3\2\2\2G"+
		"\u01f5\3\2\2\2I\u01fa\3\2\2\2K\u0206\3\2\2\2M\u020e\3\2\2\2O\u0214\3\2"+
		"\2\2QR\7p\2\2RS\7w\2\2ST\7o\2\2TU\7g\2\2UV\7t\2\2VW\7k\2\2WX\7e\2\2XY"+
		"\7c\2\2YZ\7n\2\2Z\4\3\2\2\2[\\\7o\2\2\\]\7w\2\2]^\7n\2\2^_\7v\2\2_`\7"+
		"k\2\2`a\7r\2\2ab\7n\2\2bc\7g\2\2cd\7/\2\2de\7e\2\2ef\7j\2\2fg\7q\2\2g"+
		"h\7k\2\2hi\7e\2\2ij\7g\2\2j\6\3\2\2\2kl\7u\2\2lm\7j\2\2mn\7q\2\2no\7t"+
		"\2\2op\7v\2\2pq\7/\2\2qr\7c\2\2rs\7p\2\2st\7u\2\2tu\7y\2\2uv\7g\2\2vw"+
		"\7t\2\2w\b\3\2\2\2xy\7v\2\2yz\7t\2\2z{\7w\2\2{|\7g\2\2|}\7/\2\2}~\7h\2"+
		"\2~\177\7c\2\2\177\u0080\7n\2\2\u0080\u0081\7u\2\2\u0081\u0082\7g\2\2"+
		"\u0082\n\3\2\2\2\u0083\u0084\7o\2\2\u0084\u0085\7c\2\2\u0085\u0086\7v"+
		"\2\2\u0086\u0087\7e\2\2\u0087\u0088\7j\2\2\u0088\u0089\7k\2\2\u0089\u008a"+
		"\7p\2\2\u008a\u008b\7i\2\2\u008b\f\3\2\2\2\u008c\u008d\7o\2\2\u008d\u008e"+
		"\7k\2\2\u008e\u008f\7u\2\2\u008f\u0090\7u\2\2\u0090\u0091\7k\2\2\u0091"+
		"\u0092\7p\2\2\u0092\u0093\7i\2\2\u0093\u0094\7/\2\2\u0094\u0095\7y\2\2"+
		"\u0095\u0096\7q\2\2\u0096\u0097\7t\2\2\u0097\u0098\7f\2\2\u0098\u0099"+
		"\7u\2\2\u0099\16\3\2\2\2\u009a\u009b\7=\2\2\u009b\20\3\2\2\2\u009c\u00a2"+
		"\7$\2\2\u009d\u009e\7^\2\2\u009e\u00a1\t\2\2\2\u009f\u00a1\n\2\2\2\u00a0"+
		"\u009d\3\2\2\2\u00a0\u009f\3\2\2\2\u00a1\u00a4\3\2\2\2\u00a2\u00a0\3\2"+
		"\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a5\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a5"+
		"\u00a6\7$\2\2\u00a6\22\3\2\2\2\u00a7\u00a8\7B\2\2\u00a8\u00a9\7u\2\2\u00a9"+
		"\u00aa\7v\2\2\u00aa\u00ab\7c\2\2\u00ab\u00ac\7t\2\2\u00ac\u00ad\7v\2\2"+
		"\u00ad\u00ae\7/\2\2\u00ae\u00af\7g\2\2\u00af\u00b0\7z\2\2\u00b0\u00b1"+
		"\7c\2\2\u00b1\u00b2\7o\2\2\u00b2\24\3\2\2\2\u00b3\u00b4\7B\2\2\u00b4\u00b5"+
		"\7g\2\2\u00b5\u00b6\7p\2\2\u00b6\u00b7\7f\2\2\u00b7\u00b8\7/\2\2\u00b8"+
		"\u00b9\7g\2\2\u00b9\u00ba\7z\2\2\u00ba\u00bb\7c\2\2\u00bb\u00bc\7o\2\2"+
		"\u00bc\26\3\2\2\2\u00bd\u00be\7B\2\2\u00be\u00bf\7v\2\2\u00bf\u00c0\7"+
		"k\2\2\u00c0\u00c1\7v\2\2\u00c1\u00c2\7n\2\2\u00c2\u00c3\7g\2\2\u00c3\30"+
		"\3\2\2\2\u00c4\u00c5\7B\2\2\u00c5\u00c6\7f\2\2\u00c6\u00c7\7g\2\2\u00c7"+
		"\u00c8\7u\2\2\u00c8\u00c9\7e\2\2\u00c9\u00ca\7t\2\2\u00ca\u00cb\7k\2\2"+
		"\u00cb\u00cc\7r\2\2\u00cc\u00cd\7v\2\2\u00cd\u00ce\7k\2\2\u00ce\u00cf"+
		"\7q\2\2\u00cf\u00d0\7p\2\2\u00d0\32\3\2\2\2\u00d1\u00d2\7B\2\2\u00d2\u00d3"+
		"\7h\2\2\u00d3\u00d4\7g\2\2\u00d4\u00d5\7g\2\2\u00d5\u00d6\7f\2\2\u00d6"+
		"\u00d7\7d\2\2\u00d7\u00d8\7c\2\2\u00d8\u00d9\7e\2\2\u00d9\u00da\7m\2\2"+
		"\u00da\34\3\2\2\2\u00db\u00dc\7B\2\2\u00dc\u00dd\7i\2\2\u00dd\u00de\7"+
		"t\2\2\u00de\u00df\7c\2\2\u00df\u00e0\7f\2\2\u00e0\u00e1\7g\2\2\u00e1\36"+
		"\3\2\2\2\u00e2\u00e3\7B\2\2\u00e3\u00e4\7u\2\2\u00e4\u00e5\7v\2\2\u00e5"+
		"\u00e6\7c\2\2\u00e6\u00e7\7t\2\2\u00e7\u00e8\7v\2\2\u00e8\u00e9\7/\2\2"+
		"\u00e9\u00ea\7u\2\2\u00ea\u00eb\7g\2\2\u00eb\u00ec\7e\2\2\u00ec\u00ed"+
		"\7v\2\2\u00ed\u00ee\7k\2\2\u00ee\u00ef\7q\2\2\u00ef\u00f0\7p\2\2\u00f0"+
		" \3\2\2\2\u00f1\u00f2\7B\2\2\u00f2\u00f3\7g\2\2\u00f3\u00f4\7p\2\2\u00f4"+
		"\u00f5\7f\2\2\u00f5\u00f6\7/\2\2\u00f6\u00f7\7u\2\2\u00f7\u00f8\7g\2\2"+
		"\u00f8\u00f9\7e\2\2\u00f9\u00fa\7v\2\2\u00fa\u00fb\7k\2\2\u00fb\u00fc"+
		"\7q\2\2\u00fc\u00fd\7p\2\2\u00fd\"\3\2\2\2\u00fe\u00ff\7B\2\2\u00ff\u0100"+
		"\7u\2\2\u0100\u0101\7e\2\2\u0101\u0102\7q\2\2\u0102\u0103\7t\2\2\u0103"+
		"\u0104\7g\2\2\u0104$\3\2\2\2\u0105\u0106\7B\2\2\u0106\u0107\7u\2\2\u0107"+
		"\u0108\7v\2\2\u0108\u0109\7c\2\2\u0109\u010a\7t\2\2\u010a\u010b\7v\2\2"+
		"\u010b\u010c\7/\2\2\u010c\u010d\7s\2\2\u010d\u010e\7w\2\2\u010e\u010f"+
		"\7g\2\2\u010f\u0110\7u\2\2\u0110\u0111\7v\2\2\u0111\u0112\7k\2\2\u0112"+
		"\u0113\7q\2\2\u0113\u0114\7p\2\2\u0114&\3\2\2\2\u0115\u0116\7B\2\2\u0116"+
		"\u0117\7g\2\2\u0117\u0118\7p\2\2\u0118\u0119\7f\2\2\u0119\u011a\7/\2\2"+
		"\u011a\u011b\7s\2\2\u011b\u011c\7w\2\2\u011c\u011d\7g\2\2\u011d\u011e"+
		"\7u\2\2\u011e\u011f\7v\2\2\u011f\u0120\7k\2\2\u0120\u0121\7q\2\2\u0121"+
		"\u0122\7p\2\2\u0122(\3\2\2\2\u0123\u0124\7B\2\2\u0124\u0125\7v\2\2\u0125"+
		"\u0126\7{\2\2\u0126\u0127\7r\2\2\u0127\u0128\7g\2\2\u0128*\3\2\2\2\u0129"+
		"\u012a\7B\2\2\u012a\u012b\7s\2\2\u012b\u012c\7w\2\2\u012c\u012d\7g\2\2"+
		"\u012d\u012e\7u\2\2\u012e\u012f\7v\2\2\u012f\u0130\7k\2\2\u0130\u0131"+
		"\7q\2\2\u0131\u0132\7p\2\2\u0132\u0133\7/\2\2\u0133\u0134\7d\2\2\u0134"+
		"\u0135\7q\2\2\u0135\u0136\7f\2\2\u0136\u0137\7{\2\2\u0137,\3\2\2\2\u0138"+
		"\u0139\7B\2\2\u0139\u013a\7u\2\2\u013a\u013b\7v\2\2\u013b\u013c\7c\2\2"+
		"\u013c\u013d\7t\2\2\u013d\u013e\7v\2\2\u013e\u013f\7/\2\2\u013f\u0140"+
		"\7e\2\2\u0140\u0141\7q\2\2\u0141\u0142\7t\2\2\u0142\u0143\7t\2\2\u0143"+
		"\u0144\7g\2\2\u0144\u0145\7e\2\2\u0145\u0146\7v\2\2\u0146\u0147\7/\2\2"+
		"\u0147\u0148\7c\2\2\u0148\u0149\7p\2\2\u0149\u014a\7u\2\2\u014a\u014b"+
		"\7y\2\2\u014b\u014c\7g\2\2\u014c\u014d\7t\2\2\u014d\u014e\7u\2\2\u014e"+
		".\3\2\2\2\u014f\u0150\7B\2\2\u0150\u0151\7e\2\2\u0151\u0152\7q\2\2\u0152"+
		"\u0153\7t\2\2\u0153\u0154\7t\2\2\u0154\u0155\7g\2\2\u0155\u0156\7e\2\2"+
		"\u0156\u0157\7v\2\2\u0157\u0158\7/\2\2\u0158\u0159\7c\2\2\u0159\u015a"+
		"\7p\2\2\u015a\u015b\7u\2\2\u015b\u015c\7y\2\2\u015c\u015d\7g\2\2\u015d"+
		"\u015e\7t\2\2\u015e\60\3\2\2\2\u015f\u0160\7B\2\2\u0160\u0161\7g\2\2\u0161"+
		"\u0162\7p\2\2\u0162\u0163\7f\2\2\u0163\u0164\7/\2\2\u0164\u0165\7e\2\2"+
		"\u0165\u0166\7q\2\2\u0166\u0167\7t\2\2\u0167\u0168\7t\2\2\u0168\u0169"+
		"\7g\2\2\u0169\u016a\7e\2\2\u016a\u016b\7v\2\2\u016b\u016c\7/\2\2\u016c"+
		"\u016d\7c\2\2\u016d\u016e\7p\2\2\u016e\u016f\7u\2\2\u016f\u0170\7y\2\2"+
		"\u0170\u0171\7g\2\2\u0171\u0172\7t\2\2\u0172\u0173\7u\2\2\u0173\62\3\2"+
		"\2\2\u0174\u0175\7B\2\2\u0175\u0176\7c\2\2\u0176\u0177\7e\2\2\u0177\u0178"+
		"\7e\2\2\u0178\u0179\7g\2\2\u0179\u017a\7r\2\2\u017a\u017b\7v\2\2\u017b"+
		"\u017c\7g\2\2\u017c\u017d\7f\2\2\u017d\u017e\7/\2\2\u017e\u017f\7g\2\2"+
		"\u017f\u0180\7t\2\2\u0180\u0181\7t\2\2\u0181\u0182\7q\2\2\u0182\u0183"+
		"\7t\2\2\u0183\64\3\2\2\2\u0184\u0185\7B\2\2\u0185\u0186\7u\2\2\u0186\u0187"+
		"\7v\2\2\u0187\u0188\7c\2\2\u0188\u0189\7t\2\2\u0189\u018a\7v\2\2\u018a"+
		"\u018b\7/\2\2\u018b\u018c\7q\2\2\u018c\u018d\7r\2\2\u018d\u018e\7v\2\2"+
		"\u018e\u018f\7k\2\2\u018f\u0190\7q\2\2\u0190\u0191\7p\2\2\u0191\u0192"+
		"\7u\2\2\u0192\66\3\2\2\2\u0193\u0194\7B\2\2\u0194\u0195\7g\2\2\u0195\u0196"+
		"\7p\2\2\u0196\u0197\7f\2\2\u0197\u0198\7/\2\2\u0198\u0199\7q\2\2\u0199"+
		"\u019a\7r\2\2\u019a\u019b\7v\2\2\u019b\u019c\7k\2\2\u019c\u019d\7q\2\2"+
		"\u019d\u019e\7p\2\2\u019e\u019f\7u\2\2\u019f8\3\2\2\2\u01a0\u01a1\7B\2"+
		"\2\u01a1\u01a2\7q\2\2\u01a2\u01a3\7r\2\2\u01a3\u01a4\7v\2\2\u01a4\u01a5"+
		"\7k\2\2\u01a5\u01a6\7q\2\2\u01a6\u01a7\7p\2\2\u01a7:\3\2\2\2\u01a8\u01a9"+
		"\7B\2\2\u01a9\u01aa\7u\2\2\u01aa\u01ab\7v\2\2\u01ab\u01ac\7c\2\2\u01ac"+
		"\u01ad\7t\2\2\u01ad\u01ae\7v\2\2\u01ae\u01af\7/\2\2\u01af\u01b0\7o\2\2"+
		"\u01b0\u01b1\7c\2\2\u01b1\u01b2\7v\2\2\u01b2\u01b3\7e\2\2\u01b3\u01b4"+
		"\7j\2\2\u01b4\u01b5\7k\2\2\u01b5\u01b6\7p\2\2\u01b6\u01b7\7i\2\2\u01b7"+
		"<\3\2\2\2\u01b8\u01b9\7B\2\2\u01b9\u01ba\7g\2\2\u01ba\u01bb\7p\2\2\u01bb"+
		"\u01bc\7f\2\2\u01bc\u01bd\7/\2\2\u01bd\u01be\7o\2\2\u01be\u01bf\7c\2\2"+
		"\u01bf\u01c0\7v\2\2\u01c0\u01c1\7e\2\2\u01c1\u01c2\7j\2\2\u01c2\u01c3"+
		"\7k\2\2\u01c3\u01c4\7p\2\2\u01c4\u01c5\7i\2\2\u01c5>\3\2\2\2\u01c6\u01c7"+
		"\7B\2\2\u01c7\u01c8\7o\2\2\u01c8\u01c9\7c\2\2\u01c9\u01ca\7v\2\2\u01ca"+
		"\u01cb\7e\2\2\u01cb\u01cc\7j\2\2\u01cc@\3\2\2\2\u01cd\u01ce\7v\2\2\u01ce"+
		"\u01cf\7t\2\2\u01cf\u01d0\7w\2\2\u01d0\u01d1\7g\2\2\u01d1B\3\2\2\2\u01d2"+
		"\u01d3\7h\2\2\u01d3\u01d4\7c\2\2\u01d4\u01d5\7n\2\2\u01d5\u01d6\7u\2\2"+
		"\u01d6\u01d7\7g\2\2\u01d7D\3\2\2\2\u01d8\u01d9\7p\2\2\u01d9\u01da\7q\2"+
		"\2\u01da\u01db\7p\2\2\u01db\u01f3\7g\2\2\u01dc\u01dd\7q\2\2\u01dd\u01de"+
		"\7p\2\2\u01de\u01df\7/\2\2\u01df\u01e0\7u\2\2\u01e0\u01e1\7w\2\2\u01e1"+
		"\u01e2\7d\2\2\u01e2\u01e3\7o\2\2\u01e3\u01e4\7k\2\2\u01e4\u01f3\7v\2\2"+
		"\u01e5\u01e6\7c\2\2\u01e6\u01e7\7h\2\2\u01e7\u01e8\7v\2\2\u01e8\u01e9"+
		"\7g\2\2\u01e9\u01ea\7t\2\2\u01ea\u01eb\7/\2\2\u01eb\u01ec\7e\2\2\u01ec"+
		"\u01ed\7n\2\2\u01ed\u01ee\7q\2\2\u01ee\u01ef\7u\2\2\u01ef\u01f0\7k\2\2"+
		"\u01f0\u01f1\7p\2\2\u01f1\u01f3\7i\2\2\u01f2\u01d8\3\2\2\2\u01f2\u01dc"+
		"\3\2\2\2\u01f2\u01e5\3\2\2\2\u01f3F\3\2\2\2\u01f4\u01f6\t\3\2\2\u01f5"+
		"\u01f4\3\2\2\2\u01f6\u01f7\3\2\2\2\u01f7\u01f5\3\2\2\2\u01f7\u01f8\3\2"+
		"\2\2\u01f8H\3\2\2\2\u01f9\u01fb\t\3\2\2\u01fa\u01f9\3\2\2\2\u01fb\u01fc"+
		"\3\2\2\2\u01fc\u01fa\3\2\2\2\u01fc\u01fd\3\2\2\2\u01fd\u0204\3\2\2\2\u01fe"+
		"\u0200\7\60\2\2\u01ff\u0201\t\3\2\2\u0200\u01ff\3\2\2\2\u0201\u0202\3"+
		"\2\2\2\u0202\u0200\3\2\2\2\u0202\u0203\3\2\2\2\u0203\u0205\3\2\2\2\u0204"+
		"\u01fe\3\2\2\2\u0204\u0205\3\2\2\2\u0205J\3\2\2\2\u0206\u020a\t\4\2\2"+
		"\u0207\u0209\t\5\2\2\u0208\u0207\3\2\2\2\u0209\u020c\3\2\2\2\u020a\u0208"+
		"\3\2\2\2\u020a\u020b\3\2\2\2\u020bL\3\2\2\2\u020c\u020a\3\2\2\2\u020d"+
		"\u020f\t\6\2\2\u020e\u020d\3\2\2\2\u020f\u0210\3\2\2\2\u0210\u020e\3\2"+
		"\2\2\u0210\u0211\3\2\2\2\u0211\u0212\3\2\2\2\u0212\u0213\b\'\2\2\u0213"+
		"N\3\2\2\2\u0214\u0215\7\61\2\2\u0215\u0216\7\61\2\2\u0216\u021a\3\2\2"+
		"\2\u0217\u0219\n\7\2\2\u0218\u0217\3\2\2\2\u0219\u021c\3\2\2\2\u021a\u0218"+
		"\3\2\2\2\u021a\u021b\3\2\2\2\u021b\u021d\3\2\2\2\u021c\u021a\3\2\2\2\u021d"+
		"\u021e\b(\2\2\u021eP\3\2\2\2\r\2\u00a0\u00a2\u01f2\u01f7\u01fc\u0202\u0204"+
		"\u020a\u0210\u021a\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}