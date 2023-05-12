// Generated from /home/kappa-laptop/faculdade/22/lapr4/sem4pi-22-23-19/ecourse.core/src/main/java/eapli/ecourse/exammanagement/domain/grammars/Question/Question.g4 by ANTLR 4.9.2
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class QuestionLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.9.2", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		EOI=10, STRING=11, REAL_NUMBER=12, START_QUESTION=13, END_QUESTION=14, 
		TYPE=15, QUESTION_BODY=16, START_CORRECT_ANSWERS_SECTION=17, END_CORRECT_ANSWERS_SECTION=18, 
		CORRECT_ANSWER=19, ACCEPTED_ERROR=20, FEEDBACK=21, START_OPTIONS_SECTION=22, 
		END_OPTIONS_SECTION=23, OPTION=24, START_MATCHING_SECTION=25, END_MATCHING_SECTION=26, 
		MATCH=27, WS=28, COMMENT=29;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "T__7", "T__8", 
			"EOI", "STRING", "REAL_NUMBER", "START_QUESTION", "END_QUESTION", "TYPE", 
			"QUESTION_BODY", "START_CORRECT_ANSWERS_SECTION", "END_CORRECT_ANSWERS_SECTION", 
			"CORRECT_ANSWER", "ACCEPTED_ERROR", "FEEDBACK", "START_OPTIONS_SECTION", 
			"END_OPTIONS_SECTION", "OPTION", "START_MATCHING_SECTION", "END_MATCHING_SECTION", 
			"MATCH", "WS", "COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "'numerical'", "'multiple-choice'", "'short-answer'", "'true-false'", 
			"'true'", "'false'", "'matching'", "'missing-words'", "' '", "';'", null, 
			null, "'@start-question'", "'@end-question'", "'@type '", "'@question-body'", 
			"'@correct-answers'", "'@end-correct-answers'", "'@correct-answer '", 
			"'@accepted-error '", "'@feedback '", "'@options'", "'@end-options'", 
			"'@option'", "'@start-matching'", "'@end-matching'", "'@match'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, null, null, "EOI", "STRING", 
			"REAL_NUMBER", "START_QUESTION", "END_QUESTION", "TYPE", "QUESTION_BODY", 
			"START_CORRECT_ANSWERS_SECTION", "END_CORRECT_ANSWERS_SECTION", "CORRECT_ANSWER", 
			"ACCEPTED_ERROR", "FEEDBACK", "START_OPTIONS_SECTION", "END_OPTIONS_SECTION", 
			"OPTION", "START_MATCHING_SECTION", "END_MATCHING_SECTION", "MATCH", 
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


	public QuestionLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "Question.g4"; }

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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\37\u0189\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\3\2\3\2\3\2\3"+
		"\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3"+
		"\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\7"+
		"\3\7\3\7\3\7\3\7\3\7\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\t\3"+
		"\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\13\3\13\3\f\3\f\3"+
		"\f\3\f\7\f\u009a\n\f\f\f\16\f\u009d\13\f\3\f\3\f\3\r\6\r\u00a2\n\r\r\r"+
		"\16\r\u00a3\3\r\3\r\6\r\u00a8\n\r\r\r\16\r\u00a9\5\r\u00ac\n\r\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3"+
		"\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3\22\3\22\3\22\3\22\3"+
		"\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\23\3\23\3"+
		"\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3"+
		"\23\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3"+
		"\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\3\25\3\25\3"+
		"\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3"+
		"\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27\3"+
		"\27\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3"+
		"\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3"+
		"\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\3\33\3"+
		"\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\33\3\34\3\34\3"+
		"\34\3\34\3\34\3\34\3\34\3\35\6\35\u0179\n\35\r\35\16\35\u017a\3\35\3\35"+
		"\3\36\3\36\3\36\3\36\7\36\u0183\n\36\f\36\16\36\u0186\13\36\3\36\3\36"+
		"\2\2\37\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21\n\23\13\25\f\27\r\31\16\33\17"+
		"\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30/\31\61\32\63\33\65\34\67\35"+
		"9\36;\37\3\2\6\4\2$$^^\3\2\62;\5\2\13\f\17\17\"\"\4\2\f\f\17\17\2\u018f"+
		"\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2"+
		"\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2"+
		"\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2"+
		"\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3\2\2\2\2/\3\2\2"+
		"\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2\29\3\2\2\2\2;\3"+
		"\2\2\2\3=\3\2\2\2\5G\3\2\2\2\7W\3\2\2\2\td\3\2\2\2\13o\3\2\2\2\rt\3\2"+
		"\2\2\17z\3\2\2\2\21\u0083\3\2\2\2\23\u0091\3\2\2\2\25\u0093\3\2\2\2\27"+
		"\u0095\3\2\2\2\31\u00a1\3\2\2\2\33\u00ad\3\2\2\2\35\u00bd\3\2\2\2\37\u00cb"+
		"\3\2\2\2!\u00d2\3\2\2\2#\u00e1\3\2\2\2%\u00f2\3\2\2\2\'\u0107\3\2\2\2"+
		")\u0118\3\2\2\2+\u0129\3\2\2\2-\u0134\3\2\2\2/\u013d\3\2\2\2\61\u014a"+
		"\3\2\2\2\63\u0152\3\2\2\2\65\u0162\3\2\2\2\67\u0170\3\2\2\29\u0178\3\2"+
		"\2\2;\u017e\3\2\2\2=>\7p\2\2>?\7w\2\2?@\7o\2\2@A\7g\2\2AB\7t\2\2BC\7k"+
		"\2\2CD\7e\2\2DE\7c\2\2EF\7n\2\2F\4\3\2\2\2GH\7o\2\2HI\7w\2\2IJ\7n\2\2"+
		"JK\7v\2\2KL\7k\2\2LM\7r\2\2MN\7n\2\2NO\7g\2\2OP\7/\2\2PQ\7e\2\2QR\7j\2"+
		"\2RS\7q\2\2ST\7k\2\2TU\7e\2\2UV\7g\2\2V\6\3\2\2\2WX\7u\2\2XY\7j\2\2YZ"+
		"\7q\2\2Z[\7t\2\2[\\\7v\2\2\\]\7/\2\2]^\7c\2\2^_\7p\2\2_`\7u\2\2`a\7y\2"+
		"\2ab\7g\2\2bc\7t\2\2c\b\3\2\2\2de\7v\2\2ef\7t\2\2fg\7w\2\2gh\7g\2\2hi"+
		"\7/\2\2ij\7h\2\2jk\7c\2\2kl\7n\2\2lm\7u\2\2mn\7g\2\2n\n\3\2\2\2op\7v\2"+
		"\2pq\7t\2\2qr\7w\2\2rs\7g\2\2s\f\3\2\2\2tu\7h\2\2uv\7c\2\2vw\7n\2\2wx"+
		"\7u\2\2xy\7g\2\2y\16\3\2\2\2z{\7o\2\2{|\7c\2\2|}\7v\2\2}~\7e\2\2~\177"+
		"\7j\2\2\177\u0080\7k\2\2\u0080\u0081\7p\2\2\u0081\u0082\7i\2\2\u0082\20"+
		"\3\2\2\2\u0083\u0084\7o\2\2\u0084\u0085\7k\2\2\u0085\u0086\7u\2\2\u0086"+
		"\u0087\7u\2\2\u0087\u0088\7k\2\2\u0088\u0089\7p\2\2\u0089\u008a\7i\2\2"+
		"\u008a\u008b\7/\2\2\u008b\u008c\7y\2\2\u008c\u008d\7q\2\2\u008d\u008e"+
		"\7t\2\2\u008e\u008f\7f\2\2\u008f\u0090\7u\2\2\u0090\22\3\2\2\2\u0091\u0092"+
		"\7\"\2\2\u0092\24\3\2\2\2\u0093\u0094\7=\2\2\u0094\26\3\2\2\2\u0095\u009b"+
		"\7$\2\2\u0096\u0097\7^\2\2\u0097\u009a\t\2\2\2\u0098\u009a\n\2\2\2\u0099"+
		"\u0096\3\2\2\2\u0099\u0098\3\2\2\2\u009a\u009d\3\2\2\2\u009b\u0099\3\2"+
		"\2\2\u009b\u009c\3\2\2\2\u009c\u009e\3\2\2\2\u009d\u009b\3\2\2\2\u009e"+
		"\u009f\7$\2\2\u009f\30\3\2\2\2\u00a0\u00a2\t\3\2\2\u00a1\u00a0\3\2\2\2"+
		"\u00a2\u00a3\3\2\2\2\u00a3\u00a1\3\2\2\2\u00a3\u00a4\3\2\2\2\u00a4\u00ab"+
		"\3\2\2\2\u00a5\u00a7\7\60\2\2\u00a6\u00a8\t\3\2\2\u00a7\u00a6\3\2\2\2"+
		"\u00a8\u00a9\3\2\2\2\u00a9\u00a7\3\2\2\2\u00a9\u00aa\3\2\2\2\u00aa\u00ac"+
		"\3\2\2\2\u00ab\u00a5\3\2\2\2\u00ab\u00ac\3\2\2\2\u00ac\32\3\2\2\2\u00ad"+
		"\u00ae\7B\2\2\u00ae\u00af\7u\2\2\u00af\u00b0\7v\2\2\u00b0\u00b1\7c\2\2"+
		"\u00b1\u00b2\7t\2\2\u00b2\u00b3\7v\2\2\u00b3\u00b4\7/\2\2\u00b4\u00b5"+
		"\7s\2\2\u00b5\u00b6\7w\2\2\u00b6\u00b7\7g\2\2\u00b7\u00b8\7u\2\2\u00b8"+
		"\u00b9\7v\2\2\u00b9\u00ba\7k\2\2\u00ba\u00bb\7q\2\2\u00bb\u00bc\7p\2\2"+
		"\u00bc\34\3\2\2\2\u00bd\u00be\7B\2\2\u00be\u00bf\7g\2\2\u00bf\u00c0\7"+
		"p\2\2\u00c0\u00c1\7f\2\2\u00c1\u00c2\7/\2\2\u00c2\u00c3\7s\2\2\u00c3\u00c4"+
		"\7w\2\2\u00c4\u00c5\7g\2\2\u00c5\u00c6\7u\2\2\u00c6\u00c7\7v\2\2\u00c7"+
		"\u00c8\7k\2\2\u00c8\u00c9\7q\2\2\u00c9\u00ca\7p\2\2\u00ca\36\3\2\2\2\u00cb"+
		"\u00cc\7B\2\2\u00cc\u00cd\7v\2\2\u00cd\u00ce\7{\2\2\u00ce\u00cf\7r\2\2"+
		"\u00cf\u00d0\7g\2\2\u00d0\u00d1\7\"\2\2\u00d1 \3\2\2\2\u00d2\u00d3\7B"+
		"\2\2\u00d3\u00d4\7s\2\2\u00d4\u00d5\7w\2\2\u00d5\u00d6\7g\2\2\u00d6\u00d7"+
		"\7u\2\2\u00d7\u00d8\7v\2\2\u00d8\u00d9\7k\2\2\u00d9\u00da\7q\2\2\u00da"+
		"\u00db\7p\2\2\u00db\u00dc\7/\2\2\u00dc\u00dd\7d\2\2\u00dd\u00de\7q\2\2"+
		"\u00de\u00df\7f\2\2\u00df\u00e0\7{\2\2\u00e0\"\3\2\2\2\u00e1\u00e2\7B"+
		"\2\2\u00e2\u00e3\7e\2\2\u00e3\u00e4\7q\2\2\u00e4\u00e5\7t\2\2\u00e5\u00e6"+
		"\7t\2\2\u00e6\u00e7\7g\2\2\u00e7\u00e8\7e\2\2\u00e8\u00e9\7v\2\2\u00e9"+
		"\u00ea\7/\2\2\u00ea\u00eb\7c\2\2\u00eb\u00ec\7p\2\2\u00ec\u00ed\7u\2\2"+
		"\u00ed\u00ee\7y\2\2\u00ee\u00ef\7g\2\2\u00ef\u00f0\7t\2\2\u00f0\u00f1"+
		"\7u\2\2\u00f1$\3\2\2\2\u00f2\u00f3\7B\2\2\u00f3\u00f4\7g\2\2\u00f4\u00f5"+
		"\7p\2\2\u00f5\u00f6\7f\2\2\u00f6\u00f7\7/\2\2\u00f7\u00f8\7e\2\2\u00f8"+
		"\u00f9\7q\2\2\u00f9\u00fa\7t\2\2\u00fa\u00fb\7t\2\2\u00fb\u00fc\7g\2\2"+
		"\u00fc\u00fd\7e\2\2\u00fd\u00fe\7v\2\2\u00fe\u00ff\7/\2\2\u00ff\u0100"+
		"\7c\2\2\u0100\u0101\7p\2\2\u0101\u0102\7u\2\2\u0102\u0103\7y\2\2\u0103"+
		"\u0104\7g\2\2\u0104\u0105\7t\2\2\u0105\u0106\7u\2\2\u0106&\3\2\2\2\u0107"+
		"\u0108\7B\2\2\u0108\u0109\7e\2\2\u0109\u010a\7q\2\2\u010a\u010b\7t\2\2"+
		"\u010b\u010c\7t\2\2\u010c\u010d\7g\2\2\u010d\u010e\7e\2\2\u010e\u010f"+
		"\7v\2\2\u010f\u0110\7/\2\2\u0110\u0111\7c\2\2\u0111\u0112\7p\2\2\u0112"+
		"\u0113\7u\2\2\u0113\u0114\7y\2\2\u0114\u0115\7g\2\2\u0115\u0116\7t\2\2"+
		"\u0116\u0117\7\"\2\2\u0117(\3\2\2\2\u0118\u0119\7B\2\2\u0119\u011a\7c"+
		"\2\2\u011a\u011b\7e\2\2\u011b\u011c\7e\2\2\u011c\u011d\7g\2\2\u011d\u011e"+
		"\7r\2\2\u011e\u011f\7v\2\2\u011f\u0120\7g\2\2\u0120\u0121\7f\2\2\u0121"+
		"\u0122\7/\2\2\u0122\u0123\7g\2\2\u0123\u0124\7t\2\2\u0124\u0125\7t\2\2"+
		"\u0125\u0126\7q\2\2\u0126\u0127\7t\2\2\u0127\u0128\7\"\2\2\u0128*\3\2"+
		"\2\2\u0129\u012a\7B\2\2\u012a\u012b\7h\2\2\u012b\u012c\7g\2\2\u012c\u012d"+
		"\7g\2\2\u012d\u012e\7f\2\2\u012e\u012f\7d\2\2\u012f\u0130\7c\2\2\u0130"+
		"\u0131\7e\2\2\u0131\u0132\7m\2\2\u0132\u0133\7\"\2\2\u0133,\3\2\2\2\u0134"+
		"\u0135\7B\2\2\u0135\u0136\7q\2\2\u0136\u0137\7r\2\2\u0137\u0138\7v\2\2"+
		"\u0138\u0139\7k\2\2\u0139\u013a\7q\2\2\u013a\u013b\7p\2\2\u013b\u013c"+
		"\7u\2\2\u013c.\3\2\2\2\u013d\u013e\7B\2\2\u013e\u013f\7g\2\2\u013f\u0140"+
		"\7p\2\2\u0140\u0141\7f\2\2\u0141\u0142\7/\2\2\u0142\u0143\7q\2\2\u0143"+
		"\u0144\7r\2\2\u0144\u0145\7v\2\2\u0145\u0146\7k\2\2\u0146\u0147\7q\2\2"+
		"\u0147\u0148\7p\2\2\u0148\u0149\7u\2\2\u0149\60\3\2\2\2\u014a\u014b\7"+
		"B\2\2\u014b\u014c\7q\2\2\u014c\u014d\7r\2\2\u014d\u014e\7v\2\2\u014e\u014f"+
		"\7k\2\2\u014f\u0150\7q\2\2\u0150\u0151\7p\2\2\u0151\62\3\2\2\2\u0152\u0153"+
		"\7B\2\2\u0153\u0154\7u\2\2\u0154\u0155\7v\2\2\u0155\u0156\7c\2\2\u0156"+
		"\u0157\7t\2\2\u0157\u0158\7v\2\2\u0158\u0159\7/\2\2\u0159\u015a\7o\2\2"+
		"\u015a\u015b\7c\2\2\u015b\u015c\7v\2\2\u015c\u015d\7e\2\2\u015d\u015e"+
		"\7j\2\2\u015e\u015f\7k\2\2\u015f\u0160\7p\2\2\u0160\u0161\7i\2\2\u0161"+
		"\64\3\2\2\2\u0162\u0163\7B\2\2\u0163\u0164\7g\2\2\u0164\u0165\7p\2\2\u0165"+
		"\u0166\7f\2\2\u0166\u0167\7/\2\2\u0167\u0168\7o\2\2\u0168\u0169\7c\2\2"+
		"\u0169\u016a\7v\2\2\u016a\u016b\7e\2\2\u016b\u016c\7j\2\2\u016c\u016d"+
		"\7k\2\2\u016d\u016e\7p\2\2\u016e\u016f\7i\2\2\u016f\66\3\2\2\2\u0170\u0171"+
		"\7B\2\2\u0171\u0172\7o\2\2\u0172\u0173\7c\2\2\u0173\u0174\7v\2\2\u0174"+
		"\u0175\7e\2\2\u0175\u0176\7j\2\2\u01768\3\2\2\2\u0177\u0179\t\4\2\2\u0178"+
		"\u0177\3\2\2\2\u0179\u017a\3\2\2\2\u017a\u0178\3\2\2\2\u017a\u017b\3\2"+
		"\2\2\u017b\u017c\3\2\2\2\u017c\u017d\b\35\2\2\u017d:\3\2\2\2\u017e\u017f"+
		"\7\61\2\2\u017f\u0180\7\61\2\2\u0180\u0184\3\2\2\2\u0181\u0183\n\5\2\2"+
		"\u0182\u0181\3\2\2\2\u0183\u0186\3\2\2\2\u0184\u0182\3\2\2\2\u0184\u0185"+
		"\3\2\2\2\u0185\u0187\3\2\2\2\u0186\u0184\3\2\2\2\u0187\u0188\b\36\2\2"+
		"\u0188<\3\2\2\2\n\2\u0099\u009b\u00a3\u00a9\u00ab\u017a\u0184\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}