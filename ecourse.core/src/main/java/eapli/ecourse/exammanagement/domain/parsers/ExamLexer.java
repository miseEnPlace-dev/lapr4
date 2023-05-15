// Generated from /home/russo/isep/lapr4/sem4pi-22-23-19/ecourse.core/src/main/java/eapli/ecourse/exammanagement/domain/grammars/Exam/Exam.g4 by ANTLR 4.9.2
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
		EOI=1, STRING=2, START_EXAM=3, END_EXAM=4, TITLE=5, DESCRIPTION=6, FEEDBACK=7, 
		GRADE=8, START_SECTION=9, END_SECTION=10, SCORE=11, FDB_GRD_TYPE=12, NUMBER=13, 
		IDENTIFIER=14, WS=15, COMMENT=16;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"EOI", "STRING", "START_EXAM", "END_EXAM", "TITLE", "DESCRIPTION", "FEEDBACK", 
			"GRADE", "START_SECTION", "END_SECTION", "SCORE", "FDB_GRD_TYPE", "NUMBER", 
			"IDENTIFIER", "WS", "COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "';'", null, "'@start-exam'", "'@end-exam'", "'@title'", "'@description'", 
			"'@feedback'", "'@grade'", "'@start-section'", "'@end-section'", "'@score'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, "EOI", "STRING", "START_EXAM", "END_EXAM", "TITLE", "DESCRIPTION", 
			"FEEDBACK", "GRADE", "START_SECTION", "END_SECTION", "SCORE", "FDB_GRD_TYPE", 
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\22\u00c8\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\3\2\3"+
		"\2\3\3\3\3\3\3\3\3\7\3*\n\3\f\3\16\3-\13\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3\t\3\t\3"+
		"\t\3\t\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13"+
		"\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\5\r\u00a9\n\r\3\16"+
		"\6\16\u00ac\n\16\r\16\16\16\u00ad\3\17\3\17\7\17\u00b2\n\17\f\17\16\17"+
		"\u00b5\13\17\3\20\6\20\u00b8\n\20\r\20\16\20\u00b9\3\20\3\20\3\21\3\21"+
		"\3\21\3\21\7\21\u00c2\n\21\f\21\16\21\u00c5\13\21\3\21\3\21\2\2\22\3\3"+
		"\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21"+
		"!\22\3\2\b\4\2$$^^\3\2\62;\4\2C\\c|\6\2\62;C\\aac|\5\2\13\f\17\17\"\""+
		"\4\2\f\f\17\17\2\u00cf\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2"+
		"\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25"+
		"\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2"+
		"\2\2\2!\3\2\2\2\3#\3\2\2\2\5%\3\2\2\2\7\60\3\2\2\2\t<\3\2\2\2\13F\3\2"+
		"\2\2\rM\3\2\2\2\17Z\3\2\2\2\21d\3\2\2\2\23k\3\2\2\2\25z\3\2\2\2\27\u0087"+
		"\3\2\2\2\31\u00a8\3\2\2\2\33\u00ab\3\2\2\2\35\u00af\3\2\2\2\37\u00b7\3"+
		"\2\2\2!\u00bd\3\2\2\2#$\7=\2\2$\4\3\2\2\2%+\7$\2\2&\'\7^\2\2\'*\t\2\2"+
		"\2(*\n\2\2\2)&\3\2\2\2)(\3\2\2\2*-\3\2\2\2+)\3\2\2\2+,\3\2\2\2,.\3\2\2"+
		"\2-+\3\2\2\2./\7$\2\2/\6\3\2\2\2\60\61\7B\2\2\61\62\7u\2\2\62\63\7v\2"+
		"\2\63\64\7c\2\2\64\65\7t\2\2\65\66\7v\2\2\66\67\7/\2\2\678\7g\2\289\7"+
		"z\2\29:\7c\2\2:;\7o\2\2;\b\3\2\2\2<=\7B\2\2=>\7g\2\2>?\7p\2\2?@\7f\2\2"+
		"@A\7/\2\2AB\7g\2\2BC\7z\2\2CD\7c\2\2DE\7o\2\2E\n\3\2\2\2FG\7B\2\2GH\7"+
		"v\2\2HI\7k\2\2IJ\7v\2\2JK\7n\2\2KL\7g\2\2L\f\3\2\2\2MN\7B\2\2NO\7f\2\2"+
		"OP\7g\2\2PQ\7u\2\2QR\7e\2\2RS\7t\2\2ST\7k\2\2TU\7r\2\2UV\7v\2\2VW\7k\2"+
		"\2WX\7q\2\2XY\7p\2\2Y\16\3\2\2\2Z[\7B\2\2[\\\7h\2\2\\]\7g\2\2]^\7g\2\2"+
		"^_\7f\2\2_`\7d\2\2`a\7c\2\2ab\7e\2\2bc\7m\2\2c\20\3\2\2\2de\7B\2\2ef\7"+
		"i\2\2fg\7t\2\2gh\7c\2\2hi\7f\2\2ij\7g\2\2j\22\3\2\2\2kl\7B\2\2lm\7u\2"+
		"\2mn\7v\2\2no\7c\2\2op\7t\2\2pq\7v\2\2qr\7/\2\2rs\7u\2\2st\7g\2\2tu\7"+
		"e\2\2uv\7v\2\2vw\7k\2\2wx\7q\2\2xy\7p\2\2y\24\3\2\2\2z{\7B\2\2{|\7g\2"+
		"\2|}\7p\2\2}~\7f\2\2~\177\7/\2\2\177\u0080\7u\2\2\u0080\u0081\7g\2\2\u0081"+
		"\u0082\7e\2\2\u0082\u0083\7v\2\2\u0083\u0084\7k\2\2\u0084\u0085\7q\2\2"+
		"\u0085\u0086\7p\2\2\u0086\26\3\2\2\2\u0087\u0088\7B\2\2\u0088\u0089\7"+
		"u\2\2\u0089\u008a\7e\2\2\u008a\u008b\7q\2\2\u008b\u008c\7t\2\2\u008c\u008d"+
		"\7g\2\2\u008d\30\3\2\2\2\u008e\u008f\7p\2\2\u008f\u0090\7q\2\2\u0090\u0091"+
		"\7p\2\2\u0091\u00a9\7g\2\2\u0092\u0093\7q\2\2\u0093\u0094\7p\2\2\u0094"+
		"\u0095\7/\2\2\u0095\u0096\7u\2\2\u0096\u0097\7w\2\2\u0097\u0098\7d\2\2"+
		"\u0098\u0099\7o\2\2\u0099\u009a\7k\2\2\u009a\u00a9\7v\2\2\u009b\u009c"+
		"\7c\2\2\u009c\u009d\7h\2\2\u009d\u009e\7v\2\2\u009e\u009f\7g\2\2\u009f"+
		"\u00a0\7t\2\2\u00a0\u00a1\7/\2\2\u00a1\u00a2\7e\2\2\u00a2\u00a3\7n\2\2"+
		"\u00a3\u00a4\7q\2\2\u00a4\u00a5\7u\2\2\u00a5\u00a6\7k\2\2\u00a6\u00a7"+
		"\7p\2\2\u00a7\u00a9\7i\2\2\u00a8\u008e\3\2\2\2\u00a8\u0092\3\2\2\2\u00a8"+
		"\u009b\3\2\2\2\u00a9\32\3\2\2\2\u00aa\u00ac\t\3\2\2\u00ab\u00aa\3\2\2"+
		"\2\u00ac\u00ad\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae\34"+
		"\3\2\2\2\u00af\u00b3\t\4\2\2\u00b0\u00b2\t\5\2\2\u00b1\u00b0\3\2\2\2\u00b2"+
		"\u00b5\3\2\2\2\u00b3\u00b1\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4\36\3\2\2"+
		"\2\u00b5\u00b3\3\2\2\2\u00b6\u00b8\t\6\2\2\u00b7\u00b6\3\2\2\2\u00b8\u00b9"+
		"\3\2\2\2\u00b9\u00b7\3\2\2\2\u00b9\u00ba\3\2\2\2\u00ba\u00bb\3\2\2\2\u00bb"+
		"\u00bc\b\20\2\2\u00bc \3\2\2\2\u00bd\u00be\7\61\2\2\u00be\u00bf\7\61\2"+
		"\2\u00bf\u00c3\3\2\2\2\u00c0\u00c2\n\7\2\2\u00c1\u00c0\3\2\2\2\u00c2\u00c5"+
		"\3\2\2\2\u00c3\u00c1\3\2\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c6\3\2\2\2\u00c5"+
		"\u00c3\3\2\2\2\u00c6\u00c7\b\21\2\2\u00c7\"\3\2\2\2\n\2)+\u00a8\u00ad"+
		"\u00b3\u00b9\u00c3\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}