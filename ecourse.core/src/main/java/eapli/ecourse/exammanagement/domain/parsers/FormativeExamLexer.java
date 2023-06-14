// Generated from /home/drew/Faculdade/LAPR4/sem4pi-22-23-19/ecourse.core/src/main/java/eapli/ecourse/exammanagement/domain/grammars/FormativeExam/FormativeExam.g4 by ANTLR 4.9.2
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
public class FormativeExamLexer extends Lexer {
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
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "EOI", "STRING", "START_EXAM", 
			"END_EXAM", "TITLE", "DESCRIPTION", "FEEDBACK", "START_SECTION", "END_SECTION", 
			"NUMBER_OF_QUESTIONS", "QUESTIONS_TYPE", "SCORE", "GRADE", "FDB_GRD_TYPE", 
			"NUMBER", "REAL_NUMBER", "INTEGER", "IDENTIFIER", "WS", "COMMENT"
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


	public FormativeExamLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "FormativeExam.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\34\u0159\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3"+
		"\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\t\3\t\3\t\3\t\7\t\u0087"+
		"\n\t\f\t\16\t\u008a\13\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3"+
		"\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\24\3\24"+
		"\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25"+
		"\3\25\3\25\3\25\5\25\u012b\n\25\3\26\3\26\5\26\u012f\n\26\3\27\6\27\u0132"+
		"\n\27\r\27\16\27\u0133\3\27\3\27\6\27\u0138\n\27\r\27\16\27\u0139\3\30"+
		"\6\30\u013d\n\30\r\30\16\30\u013e\3\31\3\31\7\31\u0143\n\31\f\31\16\31"+
		"\u0146\13\31\3\32\6\32\u0149\n\32\r\32\16\32\u014a\3\32\3\32\3\33\3\33"+
		"\3\33\3\33\7\33\u0153\n\33\f\33\16\33\u0156\13\33\3\33\3\33\2\2\34\3\3"+
		"\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\3\2\b\4\2$$^^\3\2"+
		"\62;\4\2C\\c|\6\2\62;C\\aac|\5\2\13\f\17\17\"\"\4\2\f\f\17\17\2\u0163"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\3\67\3\2\2\2\5A\3\2\2\2\7N\3"+
		"\2\2\2\t^\3\2\2\2\13i\3\2\2\2\rr\3\2\2\2\17\u0080\3\2\2\2\21\u0082\3\2"+
		"\2\2\23\u008d\3\2\2\2\25\u0099\3\2\2\2\27\u00a3\3\2\2\2\31\u00aa\3\2\2"+
		"\2\33\u00b7\3\2\2\2\35\u00c1\3\2\2\2\37\u00d0\3\2\2\2!\u00dd\3\2\2\2#"+
		"\u00f2\3\2\2\2%\u0102\3\2\2\2\'\u0109\3\2\2\2)\u012a\3\2\2\2+\u012e\3"+
		"\2\2\2-\u0131\3\2\2\2/\u013c\3\2\2\2\61\u0140\3\2\2\2\63\u0148\3\2\2\2"+
		"\65\u014e\3\2\2\2\678\7p\2\289\7w\2\29:\7o\2\2:;\7g\2\2;<\7t\2\2<=\7k"+
		"\2\2=>\7e\2\2>?\7c\2\2?@\7n\2\2@\4\3\2\2\2AB\7u\2\2BC\7j\2\2CD\7q\2\2"+
		"DE\7t\2\2EF\7v\2\2FG\7/\2\2GH\7c\2\2HI\7p\2\2IJ\7u\2\2JK\7y\2\2KL\7g\2"+
		"\2LM\7t\2\2M\6\3\2\2\2NO\7o\2\2OP\7w\2\2PQ\7n\2\2QR\7v\2\2RS\7k\2\2ST"+
		"\7r\2\2TU\7n\2\2UV\7g\2\2VW\7/\2\2WX\7e\2\2XY\7j\2\2YZ\7q\2\2Z[\7k\2\2"+
		"[\\\7e\2\2\\]\7g\2\2]\b\3\2\2\2^_\7v\2\2_`\7t\2\2`a\7w\2\2ab\7g\2\2bc"+
		"\7/\2\2cd\7h\2\2de\7c\2\2ef\7n\2\2fg\7u\2\2gh\7g\2\2h\n\3\2\2\2ij\7o\2"+
		"\2jk\7c\2\2kl\7v\2\2lm\7e\2\2mn\7j\2\2no\7k\2\2op\7p\2\2pq\7i\2\2q\f\3"+
		"\2\2\2rs\7o\2\2st\7k\2\2tu\7u\2\2uv\7u\2\2vw\7k\2\2wx\7p\2\2xy\7i\2\2"+
		"yz\7/\2\2z{\7y\2\2{|\7q\2\2|}\7t\2\2}~\7f\2\2~\177\7u\2\2\177\16\3\2\2"+
		"\2\u0080\u0081\7=\2\2\u0081\20\3\2\2\2\u0082\u0088\7$\2\2\u0083\u0084"+
		"\7^\2\2\u0084\u0087\t\2\2\2\u0085\u0087\n\2\2\2\u0086\u0083\3\2\2\2\u0086"+
		"\u0085\3\2\2\2\u0087\u008a\3\2\2\2\u0088\u0086\3\2\2\2\u0088\u0089\3\2"+
		"\2\2\u0089\u008b\3\2\2\2\u008a\u0088\3\2\2\2\u008b\u008c\7$\2\2\u008c"+
		"\22\3\2\2\2\u008d\u008e\7B\2\2\u008e\u008f\7u\2\2\u008f\u0090\7v\2\2\u0090"+
		"\u0091\7c\2\2\u0091\u0092\7t\2\2\u0092\u0093\7v\2\2\u0093\u0094\7/\2\2"+
		"\u0094\u0095\7g\2\2\u0095\u0096\7z\2\2\u0096\u0097\7c\2\2\u0097\u0098"+
		"\7o\2\2\u0098\24\3\2\2\2\u0099\u009a\7B\2\2\u009a\u009b\7g\2\2\u009b\u009c"+
		"\7p\2\2\u009c\u009d\7f\2\2\u009d\u009e\7/\2\2\u009e\u009f\7g\2\2\u009f"+
		"\u00a0\7z\2\2\u00a0\u00a1\7c\2\2\u00a1\u00a2\7o\2\2\u00a2\26\3\2\2\2\u00a3"+
		"\u00a4\7B\2\2\u00a4\u00a5\7v\2\2\u00a5\u00a6\7k\2\2\u00a6\u00a7\7v\2\2"+
		"\u00a7\u00a8\7n\2\2\u00a8\u00a9\7g\2\2\u00a9\30\3\2\2\2\u00aa\u00ab\7"+
		"B\2\2\u00ab\u00ac\7f\2\2\u00ac\u00ad\7g\2\2\u00ad\u00ae\7u\2\2\u00ae\u00af"+
		"\7e\2\2\u00af\u00b0\7t\2\2\u00b0\u00b1\7k\2\2\u00b1\u00b2\7r\2\2\u00b2"+
		"\u00b3\7v\2\2\u00b3\u00b4\7k\2\2\u00b4\u00b5\7q\2\2\u00b5\u00b6\7p\2\2"+
		"\u00b6\32\3\2\2\2\u00b7\u00b8\7B\2\2\u00b8\u00b9\7h\2\2\u00b9\u00ba\7"+
		"g\2\2\u00ba\u00bb\7g\2\2\u00bb\u00bc\7f\2\2\u00bc\u00bd\7d\2\2\u00bd\u00be"+
		"\7c\2\2\u00be\u00bf\7e\2\2\u00bf\u00c0\7m\2\2\u00c0\34\3\2\2\2\u00c1\u00c2"+
		"\7B\2\2\u00c2\u00c3\7u\2\2\u00c3\u00c4\7v\2\2\u00c4\u00c5\7c\2\2\u00c5"+
		"\u00c6\7t\2\2\u00c6\u00c7\7v\2\2\u00c7\u00c8\7/\2\2\u00c8\u00c9\7u\2\2"+
		"\u00c9\u00ca\7g\2\2\u00ca\u00cb\7e\2\2\u00cb\u00cc\7v\2\2\u00cc\u00cd"+
		"\7k\2\2\u00cd\u00ce\7q\2\2\u00ce\u00cf\7p\2\2\u00cf\36\3\2\2\2\u00d0\u00d1"+
		"\7B\2\2\u00d1\u00d2\7g\2\2\u00d2\u00d3\7p\2\2\u00d3\u00d4\7f\2\2\u00d4"+
		"\u00d5\7/\2\2\u00d5\u00d6\7u\2\2\u00d6\u00d7\7g\2\2\u00d7\u00d8\7e\2\2"+
		"\u00d8\u00d9\7v\2\2\u00d9\u00da\7k\2\2\u00da\u00db\7q\2\2\u00db\u00dc"+
		"\7p\2\2\u00dc \3\2\2\2\u00dd\u00de\7B\2\2\u00de\u00df\7p\2\2\u00df\u00e0"+
		"\7w\2\2\u00e0\u00e1\7o\2\2\u00e1\u00e2\7d\2\2\u00e2\u00e3\7g\2\2\u00e3"+
		"\u00e4\7t\2\2\u00e4\u00e5\7/\2\2\u00e5\u00e6\7q\2\2\u00e6\u00e7\7h\2\2"+
		"\u00e7\u00e8\7/\2\2\u00e8\u00e9\7s\2\2\u00e9\u00ea\7w\2\2\u00ea\u00eb"+
		"\7g\2\2\u00eb\u00ec\7u\2\2\u00ec\u00ed\7v\2\2\u00ed\u00ee\7k\2\2\u00ee"+
		"\u00ef\7q\2\2\u00ef\u00f0\7p\2\2\u00f0\u00f1\7u\2\2\u00f1\"\3\2\2\2\u00f2"+
		"\u00f3\7B\2\2\u00f3\u00f4\7s\2\2\u00f4\u00f5\7w\2\2\u00f5\u00f6\7g\2\2"+
		"\u00f6\u00f7\7u\2\2\u00f7\u00f8\7v\2\2\u00f8\u00f9\7k\2\2\u00f9\u00fa"+
		"\7q\2\2\u00fa\u00fb\7p\2\2\u00fb\u00fc\7u\2\2\u00fc\u00fd\7/\2\2\u00fd"+
		"\u00fe\7v\2\2\u00fe\u00ff\7{\2\2\u00ff\u0100\7r\2\2\u0100\u0101\7g\2\2"+
		"\u0101$\3\2\2\2\u0102\u0103\7B\2\2\u0103\u0104\7u\2\2\u0104\u0105\7e\2"+
		"\2\u0105\u0106\7q\2\2\u0106\u0107\7t\2\2\u0107\u0108\7g\2\2\u0108&\3\2"+
		"\2\2\u0109\u010a\7B\2\2\u010a\u010b\7i\2\2\u010b\u010c\7t\2\2\u010c\u010d"+
		"\7c\2\2\u010d\u010e\7f\2\2\u010e\u010f\7g\2\2\u010f(\3\2\2\2\u0110\u0111"+
		"\7p\2\2\u0111\u0112\7q\2\2\u0112\u0113\7p\2\2\u0113\u012b\7g\2\2\u0114"+
		"\u0115\7q\2\2\u0115\u0116\7p\2\2\u0116\u0117\7/\2\2\u0117\u0118\7u\2\2"+
		"\u0118\u0119\7w\2\2\u0119\u011a\7d\2\2\u011a\u011b\7o\2\2\u011b\u011c"+
		"\7k\2\2\u011c\u012b\7v\2\2\u011d\u011e\7c\2\2\u011e\u011f\7h\2\2\u011f"+
		"\u0120\7v\2\2\u0120\u0121\7g\2\2\u0121\u0122\7t\2\2\u0122\u0123\7/\2\2"+
		"\u0123\u0124\7e\2\2\u0124\u0125\7n\2\2\u0125\u0126\7q\2\2\u0126\u0127"+
		"\7u\2\2\u0127\u0128\7k\2\2\u0128\u0129\7p\2\2\u0129\u012b\7i\2\2\u012a"+
		"\u0110\3\2\2\2\u012a\u0114\3\2\2\2\u012a\u011d\3\2\2\2\u012b*\3\2\2\2"+
		"\u012c\u012f\5-\27\2\u012d\u012f\5/\30\2\u012e\u012c\3\2\2\2\u012e\u012d"+
		"\3\2\2\2\u012f,\3\2\2\2\u0130\u0132\t\3\2\2\u0131\u0130\3\2\2\2\u0132"+
		"\u0133\3\2\2\2\u0133\u0131\3\2\2\2\u0133\u0134\3\2\2\2\u0134\u0135\3\2"+
		"\2\2\u0135\u0137\7\60\2\2\u0136\u0138\t\3\2\2\u0137\u0136\3\2\2\2\u0138"+
		"\u0139\3\2\2\2\u0139\u0137\3\2\2\2\u0139\u013a\3\2\2\2\u013a.\3\2\2\2"+
		"\u013b\u013d\t\3\2\2\u013c\u013b\3\2\2\2\u013d\u013e\3\2\2\2\u013e\u013c"+
		"\3\2\2\2\u013e\u013f\3\2\2\2\u013f\60\3\2\2\2\u0140\u0144\t\4\2\2\u0141"+
		"\u0143\t\5\2\2\u0142\u0141\3\2\2\2\u0143\u0146\3\2\2\2\u0144\u0142\3\2"+
		"\2\2\u0144\u0145\3\2\2\2\u0145\62\3\2\2\2\u0146\u0144\3\2\2\2\u0147\u0149"+
		"\t\6\2\2\u0148\u0147\3\2\2\2\u0149\u014a\3\2\2\2\u014a\u0148\3\2\2\2\u014a"+
		"\u014b\3\2\2\2\u014b\u014c\3\2\2\2\u014c\u014d\b\32\2\2\u014d\64\3\2\2"+
		"\2\u014e\u014f\7\61\2\2\u014f\u0150\7\61\2\2\u0150\u0154\3\2\2\2\u0151"+
		"\u0153\n\7\2\2\u0152\u0151\3\2\2\2\u0153\u0156\3\2\2\2\u0154\u0152\3\2"+
		"\2\2\u0154\u0155\3\2\2\2\u0155\u0157\3\2\2\2\u0156\u0154\3\2\2\2\u0157"+
		"\u0158\b\33\2\2\u0158\66\3\2\2\2\r\2\u0086\u0088\u012a\u012e\u0133\u0139"+
		"\u013e\u0144\u014a\u0154\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}