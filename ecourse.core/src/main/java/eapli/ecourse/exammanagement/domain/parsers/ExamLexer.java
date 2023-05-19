// Generated from /home/kappa-laptop/faculdade/22/lapr4/sem4pi-22-23-19/ecourse.core/src/main/java/eapli/ecourse/exammanagement/domain/grammars/FormativeExam/FormativeExam.g4 by ANTLR 4.9.2
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
		END_EXAM=10, TITLE=11, DESCRIPTION=12, FEEDBACK=13, START_SECTION=14, 
		END_SECTION=15, NUMBER_OF_QUESTIONS=16, QUESTIONS_TYPE=17, FDB_GRD_TYPE=18, 
		NUMBER=19, IDENTIFIER=20, WS=21, COMMENT=22;
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
			"NUMBER_OF_QUESTIONS", "QUESTIONS_TYPE", "FDB_GRD_TYPE", "NUMBER", "IDENTIFIER", 
			"WS", "COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'numerical'", "'short-answer'", "'multiple-choice'", "'true-false'", 
			"'matching'", "'missing-words'", "';'", null, "'@start-exam'", "'@end-exam'", 
			"'@title'", "'@description'", "'@feedback'", "'@start-section'", "'@end-section'", 
			"'@number-of-questions'", "'@questions-type'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, "EOI", "STRING", "START_EXAM", 
			"END_EXAM", "TITLE", "DESCRIPTION", "FEEDBACK", "START_SECTION", "END_SECTION", 
			"NUMBER_OF_QUESTIONS", "QUESTIONS_TYPE", "FDB_GRD_TYPE", "NUMBER", "IDENTIFIER", 
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


	public ExamLexer(CharStream input) {
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\30\u0134\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6"+
		"\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3"+
		"\b\3\b\3\t\3\t\3\t\3\t\7\t\177\n\t\f\t\16\t\u0082\13\t\3\t\3\t\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13"+
		"\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3"+
		"\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\5\23\u0115\n\23\3\24\6\24"+
		"\u0118\n\24\r\24\16\24\u0119\3\25\3\25\7\25\u011e\n\25\f\25\16\25\u0121"+
		"\13\25\3\26\6\26\u0124\n\26\r\26\16\26\u0125\3\26\3\26\3\27\3\27\3\27"+
		"\3\27\7\27\u012e\n\27\f\27\16\27\u0131\13\27\3\27\3\27\2\2\30\3\3\5\4"+
		"\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22"+
		"#\23%\24\'\25)\26+\27-\30\3\2\b\4\2$$^^\3\2\62;\4\2C\\c|\6\2\62;C\\aa"+
		"c|\5\2\13\f\17\17\"\"\4\2\f\f\17\17\2\u013b\2\3\3\2\2\2\2\5\3\2\2\2\2"+
		"\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2"+
		"\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2"+
		"\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2"+
		"\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\3/\3\2\2\2\59\3\2\2\2\7F\3\2\2\2\t"+
		"V\3\2\2\2\13a\3\2\2\2\rj\3\2\2\2\17x\3\2\2\2\21z\3\2\2\2\23\u0085\3\2"+
		"\2\2\25\u0091\3\2\2\2\27\u009b\3\2\2\2\31\u00a2\3\2\2\2\33\u00af\3\2\2"+
		"\2\35\u00b9\3\2\2\2\37\u00c8\3\2\2\2!\u00d5\3\2\2\2#\u00ea\3\2\2\2%\u0114"+
		"\3\2\2\2\'\u0117\3\2\2\2)\u011b\3\2\2\2+\u0123\3\2\2\2-\u0129\3\2\2\2"+
		"/\60\7p\2\2\60\61\7w\2\2\61\62\7o\2\2\62\63\7g\2\2\63\64\7t\2\2\64\65"+
		"\7k\2\2\65\66\7e\2\2\66\67\7c\2\2\678\7n\2\28\4\3\2\2\29:\7u\2\2:;\7j"+
		"\2\2;<\7q\2\2<=\7t\2\2=>\7v\2\2>?\7/\2\2?@\7c\2\2@A\7p\2\2AB\7u\2\2BC"+
		"\7y\2\2CD\7g\2\2DE\7t\2\2E\6\3\2\2\2FG\7o\2\2GH\7w\2\2HI\7n\2\2IJ\7v\2"+
		"\2JK\7k\2\2KL\7r\2\2LM\7n\2\2MN\7g\2\2NO\7/\2\2OP\7e\2\2PQ\7j\2\2QR\7"+
		"q\2\2RS\7k\2\2ST\7e\2\2TU\7g\2\2U\b\3\2\2\2VW\7v\2\2WX\7t\2\2XY\7w\2\2"+
		"YZ\7g\2\2Z[\7/\2\2[\\\7h\2\2\\]\7c\2\2]^\7n\2\2^_\7u\2\2_`\7g\2\2`\n\3"+
		"\2\2\2ab\7o\2\2bc\7c\2\2cd\7v\2\2de\7e\2\2ef\7j\2\2fg\7k\2\2gh\7p\2\2"+
		"hi\7i\2\2i\f\3\2\2\2jk\7o\2\2kl\7k\2\2lm\7u\2\2mn\7u\2\2no\7k\2\2op\7"+
		"p\2\2pq\7i\2\2qr\7/\2\2rs\7y\2\2st\7q\2\2tu\7t\2\2uv\7f\2\2vw\7u\2\2w"+
		"\16\3\2\2\2xy\7=\2\2y\20\3\2\2\2z\u0080\7$\2\2{|\7^\2\2|\177\t\2\2\2}"+
		"\177\n\2\2\2~{\3\2\2\2~}\3\2\2\2\177\u0082\3\2\2\2\u0080~\3\2\2\2\u0080"+
		"\u0081\3\2\2\2\u0081\u0083\3\2\2\2\u0082\u0080\3\2\2\2\u0083\u0084\7$"+
		"\2\2\u0084\22\3\2\2\2\u0085\u0086\7B\2\2\u0086\u0087\7u\2\2\u0087\u0088"+
		"\7v\2\2\u0088\u0089\7c\2\2\u0089\u008a\7t\2\2\u008a\u008b\7v\2\2\u008b"+
		"\u008c\7/\2\2\u008c\u008d\7g\2\2\u008d\u008e\7z\2\2\u008e\u008f\7c\2\2"+
		"\u008f\u0090\7o\2\2\u0090\24\3\2\2\2\u0091\u0092\7B\2\2\u0092\u0093\7"+
		"g\2\2\u0093\u0094\7p\2\2\u0094\u0095\7f\2\2\u0095\u0096\7/\2\2\u0096\u0097"+
		"\7g\2\2\u0097\u0098\7z\2\2\u0098\u0099\7c\2\2\u0099\u009a\7o\2\2\u009a"+
		"\26\3\2\2\2\u009b\u009c\7B\2\2\u009c\u009d\7v\2\2\u009d\u009e\7k\2\2\u009e"+
		"\u009f\7v\2\2\u009f\u00a0\7n\2\2\u00a0\u00a1\7g\2\2\u00a1\30\3\2\2\2\u00a2"+
		"\u00a3\7B\2\2\u00a3\u00a4\7f\2\2\u00a4\u00a5\7g\2\2\u00a5\u00a6\7u\2\2"+
		"\u00a6\u00a7\7e\2\2\u00a7\u00a8\7t\2\2\u00a8\u00a9\7k\2\2\u00a9\u00aa"+
		"\7r\2\2\u00aa\u00ab\7v\2\2\u00ab\u00ac\7k\2\2\u00ac\u00ad\7q\2\2\u00ad"+
		"\u00ae\7p\2\2\u00ae\32\3\2\2\2\u00af\u00b0\7B\2\2\u00b0\u00b1\7h\2\2\u00b1"+
		"\u00b2\7g\2\2\u00b2\u00b3\7g\2\2\u00b3\u00b4\7f\2\2\u00b4\u00b5\7d\2\2"+
		"\u00b5\u00b6\7c\2\2\u00b6\u00b7\7e\2\2\u00b7\u00b8\7m\2\2\u00b8\34\3\2"+
		"\2\2\u00b9\u00ba\7B\2\2\u00ba\u00bb\7u\2\2\u00bb\u00bc\7v\2\2\u00bc\u00bd"+
		"\7c\2\2\u00bd\u00be\7t\2\2\u00be\u00bf\7v\2\2\u00bf\u00c0\7/\2\2\u00c0"+
		"\u00c1\7u\2\2\u00c1\u00c2\7g\2\2\u00c2\u00c3\7e\2\2\u00c3\u00c4\7v\2\2"+
		"\u00c4\u00c5\7k\2\2\u00c5\u00c6\7q\2\2\u00c6\u00c7\7p\2\2\u00c7\36\3\2"+
		"\2\2\u00c8\u00c9\7B\2\2\u00c9\u00ca\7g\2\2\u00ca\u00cb\7p\2\2\u00cb\u00cc"+
		"\7f\2\2\u00cc\u00cd\7/\2\2\u00cd\u00ce\7u\2\2\u00ce\u00cf\7g\2\2\u00cf"+
		"\u00d0\7e\2\2\u00d0\u00d1\7v\2\2\u00d1\u00d2\7k\2\2\u00d2\u00d3\7q\2\2"+
		"\u00d3\u00d4\7p\2\2\u00d4 \3\2\2\2\u00d5\u00d6\7B\2\2\u00d6\u00d7\7p\2"+
		"\2\u00d7\u00d8\7w\2\2\u00d8\u00d9\7o\2\2\u00d9\u00da\7d\2\2\u00da\u00db"+
		"\7g\2\2\u00db\u00dc\7t\2\2\u00dc\u00dd\7/\2\2\u00dd\u00de\7q\2\2\u00de"+
		"\u00df\7h\2\2\u00df\u00e0\7/\2\2\u00e0\u00e1\7s\2\2\u00e1\u00e2\7w\2\2"+
		"\u00e2\u00e3\7g\2\2\u00e3\u00e4\7u\2\2\u00e4\u00e5\7v\2\2\u00e5\u00e6"+
		"\7k\2\2\u00e6\u00e7\7q\2\2\u00e7\u00e8\7p\2\2\u00e8\u00e9\7u\2\2\u00e9"+
		"\"\3\2\2\2\u00ea\u00eb\7B\2\2\u00eb\u00ec\7s\2\2\u00ec\u00ed\7w\2\2\u00ed"+
		"\u00ee\7g\2\2\u00ee\u00ef\7u\2\2\u00ef\u00f0\7v\2\2\u00f0\u00f1\7k\2\2"+
		"\u00f1\u00f2\7q\2\2\u00f2\u00f3\7p\2\2\u00f3\u00f4\7u\2\2\u00f4\u00f5"+
		"\7/\2\2\u00f5\u00f6\7v\2\2\u00f6\u00f7\7{\2\2\u00f7\u00f8\7r\2\2\u00f8"+
		"\u00f9\7g\2\2\u00f9$\3\2\2\2\u00fa\u00fb\7p\2\2\u00fb\u00fc\7q\2\2\u00fc"+
		"\u00fd\7p\2\2\u00fd\u0115\7g\2\2\u00fe\u00ff\7q\2\2\u00ff\u0100\7p\2\2"+
		"\u0100\u0101\7/\2\2\u0101\u0102\7u\2\2\u0102\u0103\7w\2\2\u0103\u0104"+
		"\7d\2\2\u0104\u0105\7o\2\2\u0105\u0106\7k\2\2\u0106\u0115\7v\2\2\u0107"+
		"\u0108\7c\2\2\u0108\u0109\7h\2\2\u0109\u010a\7v\2\2\u010a\u010b\7g\2\2"+
		"\u010b\u010c\7t\2\2\u010c\u010d\7/\2\2\u010d\u010e\7e\2\2\u010e\u010f"+
		"\7n\2\2\u010f\u0110\7q\2\2\u0110\u0111\7u\2\2\u0111\u0112\7k\2\2\u0112"+
		"\u0113\7p\2\2\u0113\u0115\7i\2\2\u0114\u00fa\3\2\2\2\u0114\u00fe\3\2\2"+
		"\2\u0114\u0107\3\2\2\2\u0115&\3\2\2\2\u0116\u0118\t\3\2\2\u0117\u0116"+
		"\3\2\2\2\u0118\u0119\3\2\2\2\u0119\u0117\3\2\2\2\u0119\u011a\3\2\2\2\u011a"+
		"(\3\2\2\2\u011b\u011f\t\4\2\2\u011c\u011e\t\5\2\2\u011d\u011c\3\2\2\2"+
		"\u011e\u0121\3\2\2\2\u011f\u011d\3\2\2\2\u011f\u0120\3\2\2\2\u0120*\3"+
		"\2\2\2\u0121\u011f\3\2\2\2\u0122\u0124\t\6\2\2\u0123\u0122\3\2\2\2\u0124"+
		"\u0125\3\2\2\2\u0125\u0123\3\2\2\2\u0125\u0126\3\2\2\2\u0126\u0127\3\2"+
		"\2\2\u0127\u0128\b\26\2\2\u0128,\3\2\2\2\u0129\u012a\7\61\2\2\u012a\u012b"+
		"\7\61\2\2\u012b\u012f\3\2\2\2\u012c\u012e\n\7\2\2\u012d\u012c\3\2\2\2"+
		"\u012e\u0131\3\2\2\2\u012f\u012d\3\2\2\2\u012f\u0130\3\2\2\2\u0130\u0132"+
		"\3\2\2\2\u0131\u012f\3\2\2\2\u0132\u0133\b\27\2\2\u0133.\3\2\2\2\n\2~"+
		"\u0080\u0114\u0119\u011f\u0125\u012f\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}