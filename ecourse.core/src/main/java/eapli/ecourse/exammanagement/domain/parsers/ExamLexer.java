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
		INTEGER=37, IDENTIFIER=38, WS=39, COMMENT=40;
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
			"FDB_GRD_TYPE", "NUMBER", "REAL_NUMBER", "INTEGER", "IDENTIFIER", "WS", 
			"COMMENT"
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
			"FDB_GRD_TYPE", "NUMBER", "REAL_NUMBER", "INTEGER", "IDENTIFIER", "WS", 
			"COMMENT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2*\u0223\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\b\3\b\3\t\3\t\3\t\3\t\7\t\u00a3\n\t\f\t\16\t\u00a6\13\t\3\t\3\t\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3"+
		"\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37"+
		"\3 \3 \3 \3 \3 \3 \3 \3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3\"\3#\3#\3#"+
		"\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#\3#"+
		"\5#\u01f5\n#\3$\3$\5$\u01f9\n$\3%\6%\u01fc\n%\r%\16%\u01fd\3%\3%\6%\u0202"+
		"\n%\r%\16%\u0203\3&\6&\u0207\n&\r&\16&\u0208\3\'\3\'\7\'\u020d\n\'\f\'"+
		"\16\'\u0210\13\'\3(\6(\u0213\n(\r(\16(\u0214\3(\3(\3)\3)\3)\3)\7)\u021d"+
		"\n)\f)\16)\u0220\13)\3)\3)\2\2*\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31"+
		"\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*\3\2\b\4\2$$^"+
		"^\3\2\62;\4\2C\\c|\6\2\62;C\\aac|\5\2\13\f\17\17\"\"\4\2\f\f\17\17\2\u022d"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3"+
		"\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2G\3\2\2"+
		"\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\3S\3\2\2\2\5"+
		"]\3\2\2\2\7m\3\2\2\2\tz\3\2\2\2\13\u0085\3\2\2\2\r\u008e\3\2\2\2\17\u009c"+
		"\3\2\2\2\21\u009e\3\2\2\2\23\u00a9\3\2\2\2\25\u00b5\3\2\2\2\27\u00bf\3"+
		"\2\2\2\31\u00c6\3\2\2\2\33\u00d3\3\2\2\2\35\u00dd\3\2\2\2\37\u00e4\3\2"+
		"\2\2!\u00f3\3\2\2\2#\u0100\3\2\2\2%\u0107\3\2\2\2\'\u0117\3\2\2\2)\u0125"+
		"\3\2\2\2+\u012b\3\2\2\2-\u013a\3\2\2\2/\u0151\3\2\2\2\61\u0161\3\2\2\2"+
		"\63\u0176\3\2\2\2\65\u0186\3\2\2\2\67\u0195\3\2\2\29\u01a2\3\2\2\2;\u01aa"+
		"\3\2\2\2=\u01ba\3\2\2\2?\u01c8\3\2\2\2A\u01cf\3\2\2\2C\u01d4\3\2\2\2E"+
		"\u01f4\3\2\2\2G\u01f8\3\2\2\2I\u01fb\3\2\2\2K\u0206\3\2\2\2M\u020a\3\2"+
		"\2\2O\u0212\3\2\2\2Q\u0218\3\2\2\2ST\7p\2\2TU\7w\2\2UV\7o\2\2VW\7g\2\2"+
		"WX\7t\2\2XY\7k\2\2YZ\7e\2\2Z[\7c\2\2[\\\7n\2\2\\\4\3\2\2\2]^\7o\2\2^_"+
		"\7w\2\2_`\7n\2\2`a\7v\2\2ab\7k\2\2bc\7r\2\2cd\7n\2\2de\7g\2\2ef\7/\2\2"+
		"fg\7e\2\2gh\7j\2\2hi\7q\2\2ij\7k\2\2jk\7e\2\2kl\7g\2\2l\6\3\2\2\2mn\7"+
		"u\2\2no\7j\2\2op\7q\2\2pq\7t\2\2qr\7v\2\2rs\7/\2\2st\7c\2\2tu\7p\2\2u"+
		"v\7u\2\2vw\7y\2\2wx\7g\2\2xy\7t\2\2y\b\3\2\2\2z{\7v\2\2{|\7t\2\2|}\7w"+
		"\2\2}~\7g\2\2~\177\7/\2\2\177\u0080\7h\2\2\u0080\u0081\7c\2\2\u0081\u0082"+
		"\7n\2\2\u0082\u0083\7u\2\2\u0083\u0084\7g\2\2\u0084\n\3\2\2\2\u0085\u0086"+
		"\7o\2\2\u0086\u0087\7c\2\2\u0087\u0088\7v\2\2\u0088\u0089\7e\2\2\u0089"+
		"\u008a\7j\2\2\u008a\u008b\7k\2\2\u008b\u008c\7p\2\2\u008c\u008d\7i\2\2"+
		"\u008d\f\3\2\2\2\u008e\u008f\7o\2\2\u008f\u0090\7k\2\2\u0090\u0091\7u"+
		"\2\2\u0091\u0092\7u\2\2\u0092\u0093\7k\2\2\u0093\u0094\7p\2\2\u0094\u0095"+
		"\7i\2\2\u0095\u0096\7/\2\2\u0096\u0097\7y\2\2\u0097\u0098\7q\2\2\u0098"+
		"\u0099\7t\2\2\u0099\u009a\7f\2\2\u009a\u009b\7u\2\2\u009b\16\3\2\2\2\u009c"+
		"\u009d\7=\2\2\u009d\20\3\2\2\2\u009e\u00a4\7$\2\2\u009f\u00a0\7^\2\2\u00a0"+
		"\u00a3\t\2\2\2\u00a1\u00a3\n\2\2\2\u00a2\u009f\3\2\2\2\u00a2\u00a1\3\2"+
		"\2\2\u00a3\u00a6\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a4\u00a5\3\2\2\2\u00a5"+
		"\u00a7\3\2\2\2\u00a6\u00a4\3\2\2\2\u00a7\u00a8\7$\2\2\u00a8\22\3\2\2\2"+
		"\u00a9\u00aa\7B\2\2\u00aa\u00ab\7u\2\2\u00ab\u00ac\7v\2\2\u00ac\u00ad"+
		"\7c\2\2\u00ad\u00ae\7t\2\2\u00ae\u00af\7v\2\2\u00af\u00b0\7/\2\2\u00b0"+
		"\u00b1\7g\2\2\u00b1\u00b2\7z\2\2\u00b2\u00b3\7c\2\2\u00b3\u00b4\7o\2\2"+
		"\u00b4\24\3\2\2\2\u00b5\u00b6\7B\2\2\u00b6\u00b7\7g\2\2\u00b7\u00b8\7"+
		"p\2\2\u00b8\u00b9\7f\2\2\u00b9\u00ba\7/\2\2\u00ba\u00bb\7g\2\2\u00bb\u00bc"+
		"\7z\2\2\u00bc\u00bd\7c\2\2\u00bd\u00be\7o\2\2\u00be\26\3\2\2\2\u00bf\u00c0"+
		"\7B\2\2\u00c0\u00c1\7v\2\2\u00c1\u00c2\7k\2\2\u00c2\u00c3\7v\2\2\u00c3"+
		"\u00c4\7n\2\2\u00c4\u00c5\7g\2\2\u00c5\30\3\2\2\2\u00c6\u00c7\7B\2\2\u00c7"+
		"\u00c8\7f\2\2\u00c8\u00c9\7g\2\2\u00c9\u00ca\7u\2\2\u00ca\u00cb\7e\2\2"+
		"\u00cb\u00cc\7t\2\2\u00cc\u00cd\7k\2\2\u00cd\u00ce\7r\2\2\u00ce\u00cf"+
		"\7v\2\2\u00cf\u00d0\7k\2\2\u00d0\u00d1\7q\2\2\u00d1\u00d2\7p\2\2\u00d2"+
		"\32\3\2\2\2\u00d3\u00d4\7B\2\2\u00d4\u00d5\7h\2\2\u00d5\u00d6\7g\2\2\u00d6"+
		"\u00d7\7g\2\2\u00d7\u00d8\7f\2\2\u00d8\u00d9\7d\2\2\u00d9\u00da\7c\2\2"+
		"\u00da\u00db\7e\2\2\u00db\u00dc\7m\2\2\u00dc\34\3\2\2\2\u00dd\u00de\7"+
		"B\2\2\u00de\u00df\7i\2\2\u00df\u00e0\7t\2\2\u00e0\u00e1\7c\2\2\u00e1\u00e2"+
		"\7f\2\2\u00e2\u00e3\7g\2\2\u00e3\36\3\2\2\2\u00e4\u00e5\7B\2\2\u00e5\u00e6"+
		"\7u\2\2\u00e6\u00e7\7v\2\2\u00e7\u00e8\7c\2\2\u00e8\u00e9\7t\2\2\u00e9"+
		"\u00ea\7v\2\2\u00ea\u00eb\7/\2\2\u00eb\u00ec\7u\2\2\u00ec\u00ed\7g\2\2"+
		"\u00ed\u00ee\7e\2\2\u00ee\u00ef\7v\2\2\u00ef\u00f0\7k\2\2\u00f0\u00f1"+
		"\7q\2\2\u00f1\u00f2\7p\2\2\u00f2 \3\2\2\2\u00f3\u00f4\7B\2\2\u00f4\u00f5"+
		"\7g\2\2\u00f5\u00f6\7p\2\2\u00f6\u00f7\7f\2\2\u00f7\u00f8\7/\2\2\u00f8"+
		"\u00f9\7u\2\2\u00f9\u00fa\7g\2\2\u00fa\u00fb\7e\2\2\u00fb\u00fc\7v\2\2"+
		"\u00fc\u00fd\7k\2\2\u00fd\u00fe\7q\2\2\u00fe\u00ff\7p\2\2\u00ff\"\3\2"+
		"\2\2\u0100\u0101\7B\2\2\u0101\u0102\7u\2\2\u0102\u0103\7e\2\2\u0103\u0104"+
		"\7q\2\2\u0104\u0105\7t\2\2\u0105\u0106\7g\2\2\u0106$\3\2\2\2\u0107\u0108"+
		"\7B\2\2\u0108\u0109\7u\2\2\u0109\u010a\7v\2\2\u010a\u010b\7c\2\2\u010b"+
		"\u010c\7t\2\2\u010c\u010d\7v\2\2\u010d\u010e\7/\2\2\u010e\u010f\7s\2\2"+
		"\u010f\u0110\7w\2\2\u0110\u0111\7g\2\2\u0111\u0112\7u\2\2\u0112\u0113"+
		"\7v\2\2\u0113\u0114\7k\2\2\u0114\u0115\7q\2\2\u0115\u0116\7p\2\2\u0116"+
		"&\3\2\2\2\u0117\u0118\7B\2\2\u0118\u0119\7g\2\2\u0119\u011a\7p\2\2\u011a"+
		"\u011b\7f\2\2\u011b\u011c\7/\2\2\u011c\u011d\7s\2\2\u011d\u011e\7w\2\2"+
		"\u011e\u011f\7g\2\2\u011f\u0120\7u\2\2\u0120\u0121\7v\2\2\u0121\u0122"+
		"\7k\2\2\u0122\u0123\7q\2\2\u0123\u0124\7p\2\2\u0124(\3\2\2\2\u0125\u0126"+
		"\7B\2\2\u0126\u0127\7v\2\2\u0127\u0128\7{\2\2\u0128\u0129\7r\2\2\u0129"+
		"\u012a\7g\2\2\u012a*\3\2\2\2\u012b\u012c\7B\2\2\u012c\u012d\7s\2\2\u012d"+
		"\u012e\7w\2\2\u012e\u012f\7g\2\2\u012f\u0130\7u\2\2\u0130\u0131\7v\2\2"+
		"\u0131\u0132\7k\2\2\u0132\u0133\7q\2\2\u0133\u0134\7p\2\2\u0134\u0135"+
		"\7/\2\2\u0135\u0136\7d\2\2\u0136\u0137\7q\2\2\u0137\u0138\7f\2\2\u0138"+
		"\u0139\7{\2\2\u0139,\3\2\2\2\u013a\u013b\7B\2\2\u013b\u013c\7u\2\2\u013c"+
		"\u013d\7v\2\2\u013d\u013e\7c\2\2\u013e\u013f\7t\2\2\u013f\u0140\7v\2\2"+
		"\u0140\u0141\7/\2\2\u0141\u0142\7e\2\2\u0142\u0143\7q\2\2\u0143\u0144"+
		"\7t\2\2\u0144\u0145\7t\2\2\u0145\u0146\7g\2\2\u0146\u0147\7e\2\2\u0147"+
		"\u0148\7v\2\2\u0148\u0149\7/\2\2\u0149\u014a\7c\2\2\u014a\u014b\7p\2\2"+
		"\u014b\u014c\7u\2\2\u014c\u014d\7y\2\2\u014d\u014e\7g\2\2\u014e\u014f"+
		"\7t\2\2\u014f\u0150\7u\2\2\u0150.\3\2\2\2\u0151\u0152\7B\2\2\u0152\u0153"+
		"\7e\2\2\u0153\u0154\7q\2\2\u0154\u0155\7t\2\2\u0155\u0156\7t\2\2\u0156"+
		"\u0157\7g\2\2\u0157\u0158\7e\2\2\u0158\u0159\7v\2\2\u0159\u015a\7/\2\2"+
		"\u015a\u015b\7c\2\2\u015b\u015c\7p\2\2\u015c\u015d\7u\2\2\u015d\u015e"+
		"\7y\2\2\u015e\u015f\7g\2\2\u015f\u0160\7t\2\2\u0160\60\3\2\2\2\u0161\u0162"+
		"\7B\2\2\u0162\u0163\7g\2\2\u0163\u0164\7p\2\2\u0164\u0165\7f\2\2\u0165"+
		"\u0166\7/\2\2\u0166\u0167\7e\2\2\u0167\u0168\7q\2\2\u0168\u0169\7t\2\2"+
		"\u0169\u016a\7t\2\2\u016a\u016b\7g\2\2\u016b\u016c\7e\2\2\u016c\u016d"+
		"\7v\2\2\u016d\u016e\7/\2\2\u016e\u016f\7c\2\2\u016f\u0170\7p\2\2\u0170"+
		"\u0171\7u\2\2\u0171\u0172\7y\2\2\u0172\u0173\7g\2\2\u0173\u0174\7t\2\2"+
		"\u0174\u0175\7u\2\2\u0175\62\3\2\2\2\u0176\u0177\7B\2\2\u0177\u0178\7"+
		"c\2\2\u0178\u0179\7e\2\2\u0179\u017a\7e\2\2\u017a\u017b\7g\2\2\u017b\u017c"+
		"\7r\2\2\u017c\u017d\7v\2\2\u017d\u017e\7g\2\2\u017e\u017f\7f\2\2\u017f"+
		"\u0180\7/\2\2\u0180\u0181\7g\2\2\u0181\u0182\7t\2\2\u0182\u0183\7t\2\2"+
		"\u0183\u0184\7q\2\2\u0184\u0185\7t\2\2\u0185\64\3\2\2\2\u0186\u0187\7"+
		"B\2\2\u0187\u0188\7u\2\2\u0188\u0189\7v\2\2\u0189\u018a\7c\2\2\u018a\u018b"+
		"\7t\2\2\u018b\u018c\7v\2\2\u018c\u018d\7/\2\2\u018d\u018e\7q\2\2\u018e"+
		"\u018f\7r\2\2\u018f\u0190\7v\2\2\u0190\u0191\7k\2\2\u0191\u0192\7q\2\2"+
		"\u0192\u0193\7p\2\2\u0193\u0194\7u\2\2\u0194\66\3\2\2\2\u0195\u0196\7"+
		"B\2\2\u0196\u0197\7g\2\2\u0197\u0198\7p\2\2\u0198\u0199\7f\2\2\u0199\u019a"+
		"\7/\2\2\u019a\u019b\7q\2\2\u019b\u019c\7r\2\2\u019c\u019d\7v\2\2\u019d"+
		"\u019e\7k\2\2\u019e\u019f\7q\2\2\u019f\u01a0\7p\2\2\u01a0\u01a1\7u\2\2"+
		"\u01a18\3\2\2\2\u01a2\u01a3\7B\2\2\u01a3\u01a4\7q\2\2\u01a4\u01a5\7r\2"+
		"\2\u01a5\u01a6\7v\2\2\u01a6\u01a7\7k\2\2\u01a7\u01a8\7q\2\2\u01a8\u01a9"+
		"\7p\2\2\u01a9:\3\2\2\2\u01aa\u01ab\7B\2\2\u01ab\u01ac\7u\2\2\u01ac\u01ad"+
		"\7v\2\2\u01ad\u01ae\7c\2\2\u01ae\u01af\7t\2\2\u01af\u01b0\7v\2\2\u01b0"+
		"\u01b1\7/\2\2\u01b1\u01b2\7o\2\2\u01b2\u01b3\7c\2\2\u01b3\u01b4\7v\2\2"+
		"\u01b4\u01b5\7e\2\2\u01b5\u01b6\7j\2\2\u01b6\u01b7\7k\2\2\u01b7\u01b8"+
		"\7p\2\2\u01b8\u01b9\7i\2\2\u01b9<\3\2\2\2\u01ba\u01bb\7B\2\2\u01bb\u01bc"+
		"\7g\2\2\u01bc\u01bd\7p\2\2\u01bd\u01be\7f\2\2\u01be\u01bf\7/\2\2\u01bf"+
		"\u01c0\7o\2\2\u01c0\u01c1\7c\2\2\u01c1\u01c2\7v\2\2\u01c2\u01c3\7e\2\2"+
		"\u01c3\u01c4\7j\2\2\u01c4\u01c5\7k\2\2\u01c5\u01c6\7p\2\2\u01c6\u01c7"+
		"\7i\2\2\u01c7>\3\2\2\2\u01c8\u01c9\7B\2\2\u01c9\u01ca\7o\2\2\u01ca\u01cb"+
		"\7c\2\2\u01cb\u01cc\7v\2\2\u01cc\u01cd\7e\2\2\u01cd\u01ce\7j\2\2\u01ce"+
		"@\3\2\2\2\u01cf\u01d0\7v\2\2\u01d0\u01d1\7t\2\2\u01d1\u01d2\7w\2\2\u01d2"+
		"\u01d3\7g\2\2\u01d3B\3\2\2\2\u01d4\u01d5\7h\2\2\u01d5\u01d6\7c\2\2\u01d6"+
		"\u01d7\7n\2\2\u01d7\u01d8\7u\2\2\u01d8\u01d9\7g\2\2\u01d9D\3\2\2\2\u01da"+
		"\u01db\7p\2\2\u01db\u01dc\7q\2\2\u01dc\u01dd\7p\2\2\u01dd\u01f5\7g\2\2"+
		"\u01de\u01df\7q\2\2\u01df\u01e0\7p\2\2\u01e0\u01e1\7/\2\2\u01e1\u01e2"+
		"\7u\2\2\u01e2\u01e3\7w\2\2\u01e3\u01e4\7d\2\2\u01e4\u01e5\7o\2\2\u01e5"+
		"\u01e6\7k\2\2\u01e6\u01f5\7v\2\2\u01e7\u01e8\7c\2\2\u01e8\u01e9\7h\2\2"+
		"\u01e9\u01ea\7v\2\2\u01ea\u01eb\7g\2\2\u01eb\u01ec\7t\2\2\u01ec\u01ed"+
		"\7/\2\2\u01ed\u01ee\7e\2\2\u01ee\u01ef\7n\2\2\u01ef\u01f0\7q\2\2\u01f0"+
		"\u01f1\7u\2\2\u01f1\u01f2\7k\2\2\u01f2\u01f3\7p\2\2\u01f3\u01f5\7i\2\2"+
		"\u01f4\u01da\3\2\2\2\u01f4\u01de\3\2\2\2\u01f4\u01e7\3\2\2\2\u01f5F\3"+
		"\2\2\2\u01f6\u01f9\5I%\2\u01f7\u01f9\5K&\2\u01f8\u01f6\3\2\2\2\u01f8\u01f7"+
		"\3\2\2\2\u01f9H\3\2\2\2\u01fa\u01fc\t\3\2\2\u01fb\u01fa\3\2\2\2\u01fc"+
		"\u01fd\3\2\2\2\u01fd\u01fb\3\2\2\2\u01fd\u01fe\3\2\2\2\u01fe\u01ff\3\2"+
		"\2\2\u01ff\u0201\7\60\2\2\u0200\u0202\t\3\2\2\u0201\u0200\3\2\2\2\u0202"+
		"\u0203\3\2\2\2\u0203\u0201\3\2\2\2\u0203\u0204\3\2\2\2\u0204J\3\2\2\2"+
		"\u0205\u0207\t\3\2\2\u0206\u0205\3\2\2\2\u0207\u0208\3\2\2\2\u0208\u0206"+
		"\3\2\2\2\u0208\u0209\3\2\2\2\u0209L\3\2\2\2\u020a\u020e\t\4\2\2\u020b"+
		"\u020d\t\5\2\2\u020c\u020b\3\2\2\2\u020d\u0210\3\2\2\2\u020e\u020c\3\2"+
		"\2\2\u020e\u020f\3\2\2\2\u020fN\3\2\2\2\u0210\u020e\3\2\2\2\u0211\u0213"+
		"\t\6\2\2\u0212\u0211\3\2\2\2\u0213\u0214\3\2\2\2\u0214\u0212\3\2\2\2\u0214"+
		"\u0215\3\2\2\2\u0215\u0216\3\2\2\2\u0216\u0217\b(\2\2\u0217P\3\2\2\2\u0218"+
		"\u0219\7\61\2\2\u0219\u021a\7\61\2\2\u021a\u021e\3\2\2\2\u021b\u021d\n"+
		"\7\2\2\u021c\u021b\3\2\2\2\u021d\u0220\3\2\2\2\u021e\u021c\3\2\2\2\u021e"+
		"\u021f\3\2\2\2\u021f\u0221\3\2\2\2\u0220\u021e\3\2\2\2\u0221\u0222\b)"+
		"\2\2\u0222R\3\2\2\2\r\2\u00a2\u00a4\u01f4\u01f8\u01fd\u0203\u0208\u020e"+
		"\u0214\u021e\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}