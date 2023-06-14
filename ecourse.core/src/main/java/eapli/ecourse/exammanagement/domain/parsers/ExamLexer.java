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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, EOI=8, STRING=9, 
		START_EXAM=10, END_EXAM=11, TITLE=12, DESCRIPTION=13, FEEDBACK=14, GRADE=15, 
		START_SECTION=16, END_SECTION=17, SCORE=18, START_QUESTION=19, END_QUESTION=20, 
		TYPE=21, QUESTION_BODY=22, START_CORRECT_ANSWERS_SECTION=23, CORRECT_ANSWER=24, 
		END_CORRECT_ANSWERS_SECTION=25, ACCEPTED_ERROR=26, START_OPTIONS_SECTION=27, 
		END_OPTIONS_SECTION=28, OPTION=29, START_MATCHING_SECTION=30, END_MATCHING_SECTION=31, 
		MATCH=32, TRUE=33, FALSE=34, FDB_GRD_TYPE=35, NUMBER=36, REAL_NUMBER=37, 
		INTEGER=38, IDENTIFIER=39, WS=40, COMMENT=41;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "EOI", "STRING", 
			"START_EXAM", "END_EXAM", "TITLE", "DESCRIPTION", "FEEDBACK", "GRADE", 
			"START_SECTION", "END_SECTION", "SCORE", "START_QUESTION", "END_QUESTION", 
			"TYPE", "QUESTION_BODY", "START_CORRECT_ANSWERS_SECTION", "CORRECT_ANSWER", 
			"END_CORRECT_ANSWERS_SECTION", "ACCEPTED_ERROR", "START_OPTIONS_SECTION", 
			"END_OPTIONS_SECTION", "OPTION", "START_MATCHING_SECTION", "END_MATCHING_SECTION", 
			"MATCH", "TRUE", "FALSE", "FDB_GRD_TYPE", "NUMBER", "REAL_NUMBER", "INTEGER", 
			"IDENTIFIER", "WS", "COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'numerical'", "'multiple-choice'", "'short-answer'", "'true-false'", 
			"'matching'", "'missing-words'", "'-'", "';'", null, "'@start-exam'", 
			"'@end-exam'", "'@title'", "'@description'", "'@feedback'", "'@grade'", 
			"'@start-section'", "'@end-section'", "'@score'", "'@start-question'", 
			"'@end-question'", "'@type'", "'@question-body'", "'@start-correct-answers'", 
			"'@correct-answer'", "'@end-correct-answers'", "'@accepted-error'", "'@start-options'", 
			"'@end-options'", "'@option'", "'@start-matching'", "'@end-matching'", 
			"'@match'", "'true'", "'false'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, "EOI", "STRING", "START_EXAM", 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2+\u0227\b\1\4\2\t"+
		"\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13"+
		"\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\4&\t&\4\'\t\'\4(\t(\4)\t)\4*\t*\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\b\3\b\3\t\3\t\3\n\3\n\3\n\3\n\7\n\u00a7\n\n\f\n\16\n\u00aa\13\n"+
		"\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3"+
		"\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32"+
		"\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3\33\3\33\3\33\3\33\3\33"+
		"\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3\34\3\34\3\34"+
		"\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\34\3\35\3\35\3\35\3\35"+
		"\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\35\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37\3\37"+
		"\3\37\3\37\3\37\3\37\3\37\3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3 \3"+
		"!\3!\3!\3!\3!\3!\3!\3\"\3\"\3\"\3\"\3\"\3#\3#\3#\3#\3#\3#\3$\3$\3$\3$"+
		"\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\3$\5$"+
		"\u01f9\n$\3%\3%\5%\u01fd\n%\3&\6&\u0200\n&\r&\16&\u0201\3&\3&\6&\u0206"+
		"\n&\r&\16&\u0207\3\'\6\'\u020b\n\'\r\'\16\'\u020c\3(\3(\7(\u0211\n(\f"+
		"(\16(\u0214\13(\3)\6)\u0217\n)\r)\16)\u0218\3)\3)\3*\3*\3*\3*\7*\u0221"+
		"\n*\f*\16*\u0224\13*\3*\3*\2\2+\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23"+
		"\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31"+
		"\61\32\63\33\65\34\67\359\36;\37= ?!A\"C#E$G%I&K\'M(O)Q*S+\3\2\b\4\2$"+
		"$^^\3\2\62;\4\2C\\c|\6\2\62;C\\aac|\5\2\13\f\17\17\"\"\4\2\f\f\17\17\2"+
		"\u0231\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2"+
		"\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3"+
		"\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2"+
		"\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2"+
		"/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2"+
		"\2\2;\3\2\2\2\2=\3\2\2\2\2?\3\2\2\2\2A\3\2\2\2\2C\3\2\2\2\2E\3\2\2\2\2"+
		"G\3\2\2\2\2I\3\2\2\2\2K\3\2\2\2\2M\3\2\2\2\2O\3\2\2\2\2Q\3\2\2\2\2S\3"+
		"\2\2\2\3U\3\2\2\2\5_\3\2\2\2\7o\3\2\2\2\t|\3\2\2\2\13\u0087\3\2\2\2\r"+
		"\u0090\3\2\2\2\17\u009e\3\2\2\2\21\u00a0\3\2\2\2\23\u00a2\3\2\2\2\25\u00ad"+
		"\3\2\2\2\27\u00b9\3\2\2\2\31\u00c3\3\2\2\2\33\u00ca\3\2\2\2\35\u00d7\3"+
		"\2\2\2\37\u00e1\3\2\2\2!\u00e8\3\2\2\2#\u00f7\3\2\2\2%\u0104\3\2\2\2\'"+
		"\u010b\3\2\2\2)\u011b\3\2\2\2+\u0129\3\2\2\2-\u012f\3\2\2\2/\u013e\3\2"+
		"\2\2\61\u0155\3\2\2\2\63\u0165\3\2\2\2\65\u017a\3\2\2\2\67\u018a\3\2\2"+
		"\29\u0199\3\2\2\2;\u01a6\3\2\2\2=\u01ae\3\2\2\2?\u01be\3\2\2\2A\u01cc"+
		"\3\2\2\2C\u01d3\3\2\2\2E\u01d8\3\2\2\2G\u01f8\3\2\2\2I\u01fc\3\2\2\2K"+
		"\u01ff\3\2\2\2M\u020a\3\2\2\2O\u020e\3\2\2\2Q\u0216\3\2\2\2S\u021c\3\2"+
		"\2\2UV\7p\2\2VW\7w\2\2WX\7o\2\2XY\7g\2\2YZ\7t\2\2Z[\7k\2\2[\\\7e\2\2\\"+
		"]\7c\2\2]^\7n\2\2^\4\3\2\2\2_`\7o\2\2`a\7w\2\2ab\7n\2\2bc\7v\2\2cd\7k"+
		"\2\2de\7r\2\2ef\7n\2\2fg\7g\2\2gh\7/\2\2hi\7e\2\2ij\7j\2\2jk\7q\2\2kl"+
		"\7k\2\2lm\7e\2\2mn\7g\2\2n\6\3\2\2\2op\7u\2\2pq\7j\2\2qr\7q\2\2rs\7t\2"+
		"\2st\7v\2\2tu\7/\2\2uv\7c\2\2vw\7p\2\2wx\7u\2\2xy\7y\2\2yz\7g\2\2z{\7"+
		"t\2\2{\b\3\2\2\2|}\7v\2\2}~\7t\2\2~\177\7w\2\2\177\u0080\7g\2\2\u0080"+
		"\u0081\7/\2\2\u0081\u0082\7h\2\2\u0082\u0083\7c\2\2\u0083\u0084\7n\2\2"+
		"\u0084\u0085\7u\2\2\u0085\u0086\7g\2\2\u0086\n\3\2\2\2\u0087\u0088\7o"+
		"\2\2\u0088\u0089\7c\2\2\u0089\u008a\7v\2\2\u008a\u008b\7e\2\2\u008b\u008c"+
		"\7j\2\2\u008c\u008d\7k\2\2\u008d\u008e\7p\2\2\u008e\u008f\7i\2\2\u008f"+
		"\f\3\2\2\2\u0090\u0091\7o\2\2\u0091\u0092\7k\2\2\u0092\u0093\7u\2\2\u0093"+
		"\u0094\7u\2\2\u0094\u0095\7k\2\2\u0095\u0096\7p\2\2\u0096\u0097\7i\2\2"+
		"\u0097\u0098\7/\2\2\u0098\u0099\7y\2\2\u0099\u009a\7q\2\2\u009a\u009b"+
		"\7t\2\2\u009b\u009c\7f\2\2\u009c\u009d\7u\2\2\u009d\16\3\2\2\2\u009e\u009f"+
		"\7/\2\2\u009f\20\3\2\2\2\u00a0\u00a1\7=\2\2\u00a1\22\3\2\2\2\u00a2\u00a8"+
		"\7$\2\2\u00a3\u00a4\7^\2\2\u00a4\u00a7\t\2\2\2\u00a5\u00a7\n\2\2\2\u00a6"+
		"\u00a3\3\2\2\2\u00a6\u00a5\3\2\2\2\u00a7\u00aa\3\2\2\2\u00a8\u00a6\3\2"+
		"\2\2\u00a8\u00a9\3\2\2\2\u00a9\u00ab\3\2\2\2\u00aa\u00a8\3\2\2\2\u00ab"+
		"\u00ac\7$\2\2\u00ac\24\3\2\2\2\u00ad\u00ae\7B\2\2\u00ae\u00af\7u\2\2\u00af"+
		"\u00b0\7v\2\2\u00b0\u00b1\7c\2\2\u00b1\u00b2\7t\2\2\u00b2\u00b3\7v\2\2"+
		"\u00b3\u00b4\7/\2\2\u00b4\u00b5\7g\2\2\u00b5\u00b6\7z\2\2\u00b6\u00b7"+
		"\7c\2\2\u00b7\u00b8\7o\2\2\u00b8\26\3\2\2\2\u00b9\u00ba\7B\2\2\u00ba\u00bb"+
		"\7g\2\2\u00bb\u00bc\7p\2\2\u00bc\u00bd\7f\2\2\u00bd\u00be\7/\2\2\u00be"+
		"\u00bf\7g\2\2\u00bf\u00c0\7z\2\2\u00c0\u00c1\7c\2\2\u00c1\u00c2\7o\2\2"+
		"\u00c2\30\3\2\2\2\u00c3\u00c4\7B\2\2\u00c4\u00c5\7v\2\2\u00c5\u00c6\7"+
		"k\2\2\u00c6\u00c7\7v\2\2\u00c7\u00c8\7n\2\2\u00c8\u00c9\7g\2\2\u00c9\32"+
		"\3\2\2\2\u00ca\u00cb\7B\2\2\u00cb\u00cc\7f\2\2\u00cc\u00cd\7g\2\2\u00cd"+
		"\u00ce\7u\2\2\u00ce\u00cf\7e\2\2\u00cf\u00d0\7t\2\2\u00d0\u00d1\7k\2\2"+
		"\u00d1\u00d2\7r\2\2\u00d2\u00d3\7v\2\2\u00d3\u00d4\7k\2\2\u00d4\u00d5"+
		"\7q\2\2\u00d5\u00d6\7p\2\2\u00d6\34\3\2\2\2\u00d7\u00d8\7B\2\2\u00d8\u00d9"+
		"\7h\2\2\u00d9\u00da\7g\2\2\u00da\u00db\7g\2\2\u00db\u00dc\7f\2\2\u00dc"+
		"\u00dd\7d\2\2\u00dd\u00de\7c\2\2\u00de\u00df\7e\2\2\u00df\u00e0\7m\2\2"+
		"\u00e0\36\3\2\2\2\u00e1\u00e2\7B\2\2\u00e2\u00e3\7i\2\2\u00e3\u00e4\7"+
		"t\2\2\u00e4\u00e5\7c\2\2\u00e5\u00e6\7f\2\2\u00e6\u00e7\7g\2\2\u00e7 "+
		"\3\2\2\2\u00e8\u00e9\7B\2\2\u00e9\u00ea\7u\2\2\u00ea\u00eb\7v\2\2\u00eb"+
		"\u00ec\7c\2\2\u00ec\u00ed\7t\2\2\u00ed\u00ee\7v\2\2\u00ee\u00ef\7/\2\2"+
		"\u00ef\u00f0\7u\2\2\u00f0\u00f1\7g\2\2\u00f1\u00f2\7e\2\2\u00f2\u00f3"+
		"\7v\2\2\u00f3\u00f4\7k\2\2\u00f4\u00f5\7q\2\2\u00f5\u00f6\7p\2\2\u00f6"+
		"\"\3\2\2\2\u00f7\u00f8\7B\2\2\u00f8\u00f9\7g\2\2\u00f9\u00fa\7p\2\2\u00fa"+
		"\u00fb\7f\2\2\u00fb\u00fc\7/\2\2\u00fc\u00fd\7u\2\2\u00fd\u00fe\7g\2\2"+
		"\u00fe\u00ff\7e\2\2\u00ff\u0100\7v\2\2\u0100\u0101\7k\2\2\u0101\u0102"+
		"\7q\2\2\u0102\u0103\7p\2\2\u0103$\3\2\2\2\u0104\u0105\7B\2\2\u0105\u0106"+
		"\7u\2\2\u0106\u0107\7e\2\2\u0107\u0108\7q\2\2\u0108\u0109\7t\2\2\u0109"+
		"\u010a\7g\2\2\u010a&\3\2\2\2\u010b\u010c\7B\2\2\u010c\u010d\7u\2\2\u010d"+
		"\u010e\7v\2\2\u010e\u010f\7c\2\2\u010f\u0110\7t\2\2\u0110\u0111\7v\2\2"+
		"\u0111\u0112\7/\2\2\u0112\u0113\7s\2\2\u0113\u0114\7w\2\2\u0114\u0115"+
		"\7g\2\2\u0115\u0116\7u\2\2\u0116\u0117\7v\2\2\u0117\u0118\7k\2\2\u0118"+
		"\u0119\7q\2\2\u0119\u011a\7p\2\2\u011a(\3\2\2\2\u011b\u011c\7B\2\2\u011c"+
		"\u011d\7g\2\2\u011d\u011e\7p\2\2\u011e\u011f\7f\2\2\u011f\u0120\7/\2\2"+
		"\u0120\u0121\7s\2\2\u0121\u0122\7w\2\2\u0122\u0123\7g\2\2\u0123\u0124"+
		"\7u\2\2\u0124\u0125\7v\2\2\u0125\u0126\7k\2\2\u0126\u0127\7q\2\2\u0127"+
		"\u0128\7p\2\2\u0128*\3\2\2\2\u0129\u012a\7B\2\2\u012a\u012b\7v\2\2\u012b"+
		"\u012c\7{\2\2\u012c\u012d\7r\2\2\u012d\u012e\7g\2\2\u012e,\3\2\2\2\u012f"+
		"\u0130\7B\2\2\u0130\u0131\7s\2\2\u0131\u0132\7w\2\2\u0132\u0133\7g\2\2"+
		"\u0133\u0134\7u\2\2\u0134\u0135\7v\2\2\u0135\u0136\7k\2\2\u0136\u0137"+
		"\7q\2\2\u0137\u0138\7p\2\2\u0138\u0139\7/\2\2\u0139\u013a\7d\2\2\u013a"+
		"\u013b\7q\2\2\u013b\u013c\7f\2\2\u013c\u013d\7{\2\2\u013d.\3\2\2\2\u013e"+
		"\u013f\7B\2\2\u013f\u0140\7u\2\2\u0140\u0141\7v\2\2\u0141\u0142\7c\2\2"+
		"\u0142\u0143\7t\2\2\u0143\u0144\7v\2\2\u0144\u0145\7/\2\2\u0145\u0146"+
		"\7e\2\2\u0146\u0147\7q\2\2\u0147\u0148\7t\2\2\u0148\u0149\7t\2\2\u0149"+
		"\u014a\7g\2\2\u014a\u014b\7e\2\2\u014b\u014c\7v\2\2\u014c\u014d\7/\2\2"+
		"\u014d\u014e\7c\2\2\u014e\u014f\7p\2\2\u014f\u0150\7u\2\2\u0150\u0151"+
		"\7y\2\2\u0151\u0152\7g\2\2\u0152\u0153\7t\2\2\u0153\u0154\7u\2\2\u0154"+
		"\60\3\2\2\2\u0155\u0156\7B\2\2\u0156\u0157\7e\2\2\u0157\u0158\7q\2\2\u0158"+
		"\u0159\7t\2\2\u0159\u015a\7t\2\2\u015a\u015b\7g\2\2\u015b\u015c\7e\2\2"+
		"\u015c\u015d\7v\2\2\u015d\u015e\7/\2\2\u015e\u015f\7c\2\2\u015f\u0160"+
		"\7p\2\2\u0160\u0161\7u\2\2\u0161\u0162\7y\2\2\u0162\u0163\7g\2\2\u0163"+
		"\u0164\7t\2\2\u0164\62\3\2\2\2\u0165\u0166\7B\2\2\u0166\u0167\7g\2\2\u0167"+
		"\u0168\7p\2\2\u0168\u0169\7f\2\2\u0169\u016a\7/\2\2\u016a\u016b\7e\2\2"+
		"\u016b\u016c\7q\2\2\u016c\u016d\7t\2\2\u016d\u016e\7t\2\2\u016e\u016f"+
		"\7g\2\2\u016f\u0170\7e\2\2\u0170\u0171\7v\2\2\u0171\u0172\7/\2\2\u0172"+
		"\u0173\7c\2\2\u0173\u0174\7p\2\2\u0174\u0175\7u\2\2\u0175\u0176\7y\2\2"+
		"\u0176\u0177\7g\2\2\u0177\u0178\7t\2\2\u0178\u0179\7u\2\2\u0179\64\3\2"+
		"\2\2\u017a\u017b\7B\2\2\u017b\u017c\7c\2\2\u017c\u017d\7e\2\2\u017d\u017e"+
		"\7e\2\2\u017e\u017f\7g\2\2\u017f\u0180\7r\2\2\u0180\u0181\7v\2\2\u0181"+
		"\u0182\7g\2\2\u0182\u0183\7f\2\2\u0183\u0184\7/\2\2\u0184\u0185\7g\2\2"+
		"\u0185\u0186\7t\2\2\u0186\u0187\7t\2\2\u0187\u0188\7q\2\2\u0188\u0189"+
		"\7t\2\2\u0189\66\3\2\2\2\u018a\u018b\7B\2\2\u018b\u018c\7u\2\2\u018c\u018d"+
		"\7v\2\2\u018d\u018e\7c\2\2\u018e\u018f\7t\2\2\u018f\u0190\7v\2\2\u0190"+
		"\u0191\7/\2\2\u0191\u0192\7q\2\2\u0192\u0193\7r\2\2\u0193\u0194\7v\2\2"+
		"\u0194\u0195\7k\2\2\u0195\u0196\7q\2\2\u0196\u0197\7p\2\2\u0197\u0198"+
		"\7u\2\2\u01988\3\2\2\2\u0199\u019a\7B\2\2\u019a\u019b\7g\2\2\u019b\u019c"+
		"\7p\2\2\u019c\u019d\7f\2\2\u019d\u019e\7/\2\2\u019e\u019f\7q\2\2\u019f"+
		"\u01a0\7r\2\2\u01a0\u01a1\7v\2\2\u01a1\u01a2\7k\2\2\u01a2\u01a3\7q\2\2"+
		"\u01a3\u01a4\7p\2\2\u01a4\u01a5\7u\2\2\u01a5:\3\2\2\2\u01a6\u01a7\7B\2"+
		"\2\u01a7\u01a8\7q\2\2\u01a8\u01a9\7r\2\2\u01a9\u01aa\7v\2\2\u01aa\u01ab"+
		"\7k\2\2\u01ab\u01ac\7q\2\2\u01ac\u01ad\7p\2\2\u01ad<\3\2\2\2\u01ae\u01af"+
		"\7B\2\2\u01af\u01b0\7u\2\2\u01b0\u01b1\7v\2\2\u01b1\u01b2\7c\2\2\u01b2"+
		"\u01b3\7t\2\2\u01b3\u01b4\7v\2\2\u01b4\u01b5\7/\2\2\u01b5\u01b6\7o\2\2"+
		"\u01b6\u01b7\7c\2\2\u01b7\u01b8\7v\2\2\u01b8\u01b9\7e\2\2\u01b9\u01ba"+
		"\7j\2\2\u01ba\u01bb\7k\2\2\u01bb\u01bc\7p\2\2\u01bc\u01bd\7i\2\2\u01bd"+
		">\3\2\2\2\u01be\u01bf\7B\2\2\u01bf\u01c0\7g\2\2\u01c0\u01c1\7p\2\2\u01c1"+
		"\u01c2\7f\2\2\u01c2\u01c3\7/\2\2\u01c3\u01c4\7o\2\2\u01c4\u01c5\7c\2\2"+
		"\u01c5\u01c6\7v\2\2\u01c6\u01c7\7e\2\2\u01c7\u01c8\7j\2\2\u01c8\u01c9"+
		"\7k\2\2\u01c9\u01ca\7p\2\2\u01ca\u01cb\7i\2\2\u01cb@\3\2\2\2\u01cc\u01cd"+
		"\7B\2\2\u01cd\u01ce\7o\2\2\u01ce\u01cf\7c\2\2\u01cf\u01d0\7v\2\2\u01d0"+
		"\u01d1\7e\2\2\u01d1\u01d2\7j\2\2\u01d2B\3\2\2\2\u01d3\u01d4\7v\2\2\u01d4"+
		"\u01d5\7t\2\2\u01d5\u01d6\7w\2\2\u01d6\u01d7\7g\2\2\u01d7D\3\2\2\2\u01d8"+
		"\u01d9\7h\2\2\u01d9\u01da\7c\2\2\u01da\u01db\7n\2\2\u01db\u01dc\7u\2\2"+
		"\u01dc\u01dd\7g\2\2\u01ddF\3\2\2\2\u01de\u01df\7p\2\2\u01df\u01e0\7q\2"+
		"\2\u01e0\u01e1\7p\2\2\u01e1\u01f9\7g\2\2\u01e2\u01e3\7q\2\2\u01e3\u01e4"+
		"\7p\2\2\u01e4\u01e5\7/\2\2\u01e5\u01e6\7u\2\2\u01e6\u01e7\7w\2\2\u01e7"+
		"\u01e8\7d\2\2\u01e8\u01e9\7o\2\2\u01e9\u01ea\7k\2\2\u01ea\u01f9\7v\2\2"+
		"\u01eb\u01ec\7c\2\2\u01ec\u01ed\7h\2\2\u01ed\u01ee\7v\2\2\u01ee\u01ef"+
		"\7g\2\2\u01ef\u01f0\7t\2\2\u01f0\u01f1\7/\2\2\u01f1\u01f2\7e\2\2\u01f2"+
		"\u01f3\7n\2\2\u01f3\u01f4\7q\2\2\u01f4\u01f5\7u\2\2\u01f5\u01f6\7k\2\2"+
		"\u01f6\u01f7\7p\2\2\u01f7\u01f9\7i\2\2\u01f8\u01de\3\2\2\2\u01f8\u01e2"+
		"\3\2\2\2\u01f8\u01eb\3\2\2\2\u01f9H\3\2\2\2\u01fa\u01fd\5K&\2\u01fb\u01fd"+
		"\5M\'\2\u01fc\u01fa\3\2\2\2\u01fc\u01fb\3\2\2\2\u01fdJ\3\2\2\2\u01fe\u0200"+
		"\t\3\2\2\u01ff\u01fe\3\2\2\2\u0200\u0201\3\2\2\2\u0201\u01ff\3\2\2\2\u0201"+
		"\u0202\3\2\2\2\u0202\u0203\3\2\2\2\u0203\u0205\7\60\2\2\u0204\u0206\t"+
		"\3\2\2\u0205\u0204\3\2\2\2\u0206\u0207\3\2\2\2\u0207\u0205\3\2\2\2\u0207"+
		"\u0208\3\2\2\2\u0208L\3\2\2\2\u0209\u020b\t\3\2\2\u020a\u0209\3\2\2\2"+
		"\u020b\u020c\3\2\2\2\u020c\u020a\3\2\2\2\u020c\u020d\3\2\2\2\u020dN\3"+
		"\2\2\2\u020e\u0212\t\4\2\2\u020f\u0211\t\5\2\2\u0210\u020f\3\2\2\2\u0211"+
		"\u0214\3\2\2\2\u0212\u0210\3\2\2\2\u0212\u0213\3\2\2\2\u0213P\3\2\2\2"+
		"\u0214\u0212\3\2\2\2\u0215\u0217\t\6\2\2\u0216\u0215\3\2\2\2\u0217\u0218"+
		"\3\2\2\2\u0218\u0216\3\2\2\2\u0218\u0219\3\2\2\2\u0219\u021a\3\2\2\2\u021a"+
		"\u021b\b)\2\2\u021bR\3\2\2\2\u021c\u021d\7\61\2\2\u021d\u021e\7\61\2\2"+
		"\u021e\u0222\3\2\2\2\u021f\u0221\n\7\2\2\u0220\u021f\3\2\2\2\u0221\u0224"+
		"\3\2\2\2\u0222\u0220\3\2\2\2\u0222\u0223\3\2\2\2\u0223\u0225\3\2\2\2\u0224"+
		"\u0222\3\2\2\2\u0225\u0226\b*\2\2\u0226T\3\2\2\2\r\2\u00a6\u00a8\u01f8"+
		"\u01fc\u0201\u0207\u020c\u0212\u0218\u0222\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}