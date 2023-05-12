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
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, EOI=8, STRING=9, 
		REAL_NUMBER=10, START_QUESTION=11, END_QUESTION=12, TYPE=13, QUESTION_BODY=14, 
		START_CORRECT_ANSWERS_SECTION=15, END_CORRECT_ANSWERS_SECTION=16, CORRECT_ANSWER=17, 
		ACCEPTED_ERROR=18, FEEDBACK=19, START_OPTIONS_SECTION=20, END_OPTIONS_SECTION=21, 
		OPTION=22, START_MATCHING_SECTION=23, END_MATCHING_SECTION=24, MATCH=25, 
		WS=26, COMMENT=27;
	public static String[] channelNames = {
		"DEFAULT_TOKEN_CHANNEL", "HIDDEN"
	};

	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	private static String[] makeRuleNames() {
		return new String[] {
			"T__0", "T__1", "T__2", "T__3", "T__4", "T__5", "T__6", "EOI", "STRING", 
			"REAL_NUMBER", "START_QUESTION", "END_QUESTION", "TYPE", "QUESTION_BODY", 
			"START_CORRECT_ANSWERS_SECTION", "END_CORRECT_ANSWERS_SECTION", "CORRECT_ANSWER", 
			"ACCEPTED_ERROR", "FEEDBACK", "START_OPTIONS_SECTION", "END_OPTIONS_SECTION", 
			"OPTION", "START_MATCHING_SECTION", "END_MATCHING_SECTION", "MATCH", 
			"WS", "COMMENT"
		};
	}
	public static final String[] ruleNames = makeRuleNames();

	private static String[] makeLiteralNames() {
		return new String[] {
			null, "' '", "'numerical'", "'multiple-choice'", "'short-answer'", "'true-false'", 
			"'matching'", "'missing-words'", "';'", null, null, "'@start-question'", 
			"'@end-question'", "'@type'", "'@question-body'", "'@correct-answers'", 
			"'@end-correct-answers'", "'@correct-answer'", "'@accepted-error'", "'@feedback'", 
			"'@options'", "'@end-options'", "'@option'", "'@start-matching'", "'@end-matching'", 
			"'@match'"
		};
	}
	private static final String[] _LITERAL_NAMES = makeLiteralNames();
	private static String[] makeSymbolicNames() {
		return new String[] {
			null, null, null, null, null, null, null, null, "EOI", "STRING", "REAL_NUMBER", 
			"START_QUESTION", "END_QUESTION", "TYPE", "QUESTION_BODY", "START_CORRECT_ANSWERS_SECTION", 
			"END_CORRECT_ANSWERS_SECTION", "CORRECT_ANSWER", "ACCEPTED_ERROR", "FEEDBACK", 
			"START_OPTIONS_SECTION", "END_OPTIONS_SECTION", "OPTION", "START_MATCHING_SECTION", 
			"END_MATCHING_SECTION", "MATCH", "WS", "COMMENT"
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\2\35\u0176\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31"+
		"\t\31\4\32\t\32\4\33\t\33\4\34\t\34\3\2\3\2\3\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\3\3\3\3\3\3\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4"+
		"\3\4\3\4\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7\3\7"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\t\3\t\3\n\3"+
		"\n\3\n\3\n\7\n\u008b\n\n\f\n\16\n\u008e\13\n\3\n\3\n\3\13\6\13\u0093\n"+
		"\13\r\13\16\13\u0094\3\13\3\13\6\13\u0099\n\13\r\13\16\13\u009a\5\13\u009d"+
		"\n\13\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16"+
		"\3\16\3\16\3\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17"+
		"\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\20"+
		"\3\20\3\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21"+
		"\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22\3\22"+
		"\3\22\3\22\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23\3\23"+
		"\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24\3\24"+
		"\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\25\3\26\3\26\3\26\3\26\3\26"+
		"\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\26\3\27\3\27\3\27\3\27\3\27\3\27"+
		"\3\27\3\27\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30\3\30"+
		"\3\30\3\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31\3\31"+
		"\3\31\3\31\3\31\3\31\3\32\3\32\3\32\3\32\3\32\3\32\3\32\3\33\6\33\u0166"+
		"\n\33\r\33\16\33\u0167\3\33\3\33\3\34\3\34\3\34\3\34\7\34\u0170\n\34\f"+
		"\34\16\34\u0173\13\34\3\34\3\34\2\2\35\3\3\5\4\7\5\t\6\13\7\r\b\17\t\21"+
		"\n\23\13\25\f\27\r\31\16\33\17\35\20\37\21!\22#\23%\24\'\25)\26+\27-\30"+
		"/\31\61\32\63\33\65\34\67\35\3\2\6\4\2$$^^\3\2\62;\4\2\13\f\17\17\4\2"+
		"\f\f\17\17\2\u017c\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2"+
		"\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2"+
		"!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\2)\3\2\2\2\2+\3\2\2\2\2-\3"+
		"\2\2\2\2/\3\2\2\2\2\61\3\2\2\2\2\63\3\2\2\2\2\65\3\2\2\2\2\67\3\2\2\2"+
		"\39\3\2\2\2\5;\3\2\2\2\7E\3\2\2\2\tU\3\2\2\2\13b\3\2\2\2\rm\3\2\2\2\17"+
		"v\3\2\2\2\21\u0084\3\2\2\2\23\u0086\3\2\2\2\25\u0092\3\2\2\2\27\u009e"+
		"\3\2\2\2\31\u00ae\3\2\2\2\33\u00bc\3\2\2\2\35\u00c2\3\2\2\2\37\u00d1\3"+
		"\2\2\2!\u00e2\3\2\2\2#\u00f7\3\2\2\2%\u0107\3\2\2\2\'\u0117\3\2\2\2)\u0121"+
		"\3\2\2\2+\u012a\3\2\2\2-\u0137\3\2\2\2/\u013f\3\2\2\2\61\u014f\3\2\2\2"+
		"\63\u015d\3\2\2\2\65\u0165\3\2\2\2\67\u016b\3\2\2\29:\7\"\2\2:\4\3\2\2"+
		"\2;<\7p\2\2<=\7w\2\2=>\7o\2\2>?\7g\2\2?@\7t\2\2@A\7k\2\2AB\7e\2\2BC\7"+
		"c\2\2CD\7n\2\2D\6\3\2\2\2EF\7o\2\2FG\7w\2\2GH\7n\2\2HI\7v\2\2IJ\7k\2\2"+
		"JK\7r\2\2KL\7n\2\2LM\7g\2\2MN\7/\2\2NO\7e\2\2OP\7j\2\2PQ\7q\2\2QR\7k\2"+
		"\2RS\7e\2\2ST\7g\2\2T\b\3\2\2\2UV\7u\2\2VW\7j\2\2WX\7q\2\2XY\7t\2\2YZ"+
		"\7v\2\2Z[\7/\2\2[\\\7c\2\2\\]\7p\2\2]^\7u\2\2^_\7y\2\2_`\7g\2\2`a\7t\2"+
		"\2a\n\3\2\2\2bc\7v\2\2cd\7t\2\2de\7w\2\2ef\7g\2\2fg\7/\2\2gh\7h\2\2hi"+
		"\7c\2\2ij\7n\2\2jk\7u\2\2kl\7g\2\2l\f\3\2\2\2mn\7o\2\2no\7c\2\2op\7v\2"+
		"\2pq\7e\2\2qr\7j\2\2rs\7k\2\2st\7p\2\2tu\7i\2\2u\16\3\2\2\2vw\7o\2\2w"+
		"x\7k\2\2xy\7u\2\2yz\7u\2\2z{\7k\2\2{|\7p\2\2|}\7i\2\2}~\7/\2\2~\177\7"+
		"y\2\2\177\u0080\7q\2\2\u0080\u0081\7t\2\2\u0081\u0082\7f\2\2\u0082\u0083"+
		"\7u\2\2\u0083\20\3\2\2\2\u0084\u0085\7=\2\2\u0085\22\3\2\2\2\u0086\u008c"+
		"\7$\2\2\u0087\u0088\7^\2\2\u0088\u008b\t\2\2\2\u0089\u008b\n\2\2\2\u008a"+
		"\u0087\3\2\2\2\u008a\u0089\3\2\2\2\u008b\u008e\3\2\2\2\u008c\u008a\3\2"+
		"\2\2\u008c\u008d\3\2\2\2\u008d\u008f\3\2\2\2\u008e\u008c\3\2\2\2\u008f"+
		"\u0090\7$\2\2\u0090\24\3\2\2\2\u0091\u0093\t\3\2\2\u0092\u0091\3\2\2\2"+
		"\u0093\u0094\3\2\2\2\u0094\u0092\3\2\2\2\u0094\u0095\3\2\2\2\u0095\u009c"+
		"\3\2\2\2\u0096\u0098\7\60\2\2\u0097\u0099\t\3\2\2\u0098\u0097\3\2\2\2"+
		"\u0099\u009a\3\2\2\2\u009a\u0098\3\2\2\2\u009a\u009b\3\2\2\2\u009b\u009d"+
		"\3\2\2\2\u009c\u0096\3\2\2\2\u009c\u009d\3\2\2\2\u009d\26\3\2\2\2\u009e"+
		"\u009f\7B\2\2\u009f\u00a0\7u\2\2\u00a0\u00a1\7v\2\2\u00a1\u00a2\7c\2\2"+
		"\u00a2\u00a3\7t\2\2\u00a3\u00a4\7v\2\2\u00a4\u00a5\7/\2\2\u00a5\u00a6"+
		"\7s\2\2\u00a6\u00a7\7w\2\2\u00a7\u00a8\7g\2\2\u00a8\u00a9\7u\2\2\u00a9"+
		"\u00aa\7v\2\2\u00aa\u00ab\7k\2\2\u00ab\u00ac\7q\2\2\u00ac\u00ad\7p\2\2"+
		"\u00ad\30\3\2\2\2\u00ae\u00af\7B\2\2\u00af\u00b0\7g\2\2\u00b0\u00b1\7"+
		"p\2\2\u00b1\u00b2\7f\2\2\u00b2\u00b3\7/\2\2\u00b3\u00b4\7s\2\2\u00b4\u00b5"+
		"\7w\2\2\u00b5\u00b6\7g\2\2\u00b6\u00b7\7u\2\2\u00b7\u00b8\7v\2\2\u00b8"+
		"\u00b9\7k\2\2\u00b9\u00ba\7q\2\2\u00ba\u00bb\7p\2\2\u00bb\32\3\2\2\2\u00bc"+
		"\u00bd\7B\2\2\u00bd\u00be\7v\2\2\u00be\u00bf\7{\2\2\u00bf\u00c0\7r\2\2"+
		"\u00c0\u00c1\7g\2\2\u00c1\34\3\2\2\2\u00c2\u00c3\7B\2\2\u00c3\u00c4\7"+
		"s\2\2\u00c4\u00c5\7w\2\2\u00c5\u00c6\7g\2\2\u00c6\u00c7\7u\2\2\u00c7\u00c8"+
		"\7v\2\2\u00c8\u00c9\7k\2\2\u00c9\u00ca\7q\2\2\u00ca\u00cb\7p\2\2\u00cb"+
		"\u00cc\7/\2\2\u00cc\u00cd\7d\2\2\u00cd\u00ce\7q\2\2\u00ce\u00cf\7f\2\2"+
		"\u00cf\u00d0\7{\2\2\u00d0\36\3\2\2\2\u00d1\u00d2\7B\2\2\u00d2\u00d3\7"+
		"e\2\2\u00d3\u00d4\7q\2\2\u00d4\u00d5\7t\2\2\u00d5\u00d6\7t\2\2\u00d6\u00d7"+
		"\7g\2\2\u00d7\u00d8\7e\2\2\u00d8\u00d9\7v\2\2\u00d9\u00da\7/\2\2\u00da"+
		"\u00db\7c\2\2\u00db\u00dc\7p\2\2\u00dc\u00dd\7u\2\2\u00dd\u00de\7y\2\2"+
		"\u00de\u00df\7g\2\2\u00df\u00e0\7t\2\2\u00e0\u00e1\7u\2\2\u00e1 \3\2\2"+
		"\2\u00e2\u00e3\7B\2\2\u00e3\u00e4\7g\2\2\u00e4\u00e5\7p\2\2\u00e5\u00e6"+
		"\7f\2\2\u00e6\u00e7\7/\2\2\u00e7\u00e8\7e\2\2\u00e8\u00e9\7q\2\2\u00e9"+
		"\u00ea\7t\2\2\u00ea\u00eb\7t\2\2\u00eb\u00ec\7g\2\2\u00ec\u00ed\7e\2\2"+
		"\u00ed\u00ee\7v\2\2\u00ee\u00ef\7/\2\2\u00ef\u00f0\7c\2\2\u00f0\u00f1"+
		"\7p\2\2\u00f1\u00f2\7u\2\2\u00f2\u00f3\7y\2\2\u00f3\u00f4\7g\2\2\u00f4"+
		"\u00f5\7t\2\2\u00f5\u00f6\7u\2\2\u00f6\"\3\2\2\2\u00f7\u00f8\7B\2\2\u00f8"+
		"\u00f9\7e\2\2\u00f9\u00fa\7q\2\2\u00fa\u00fb\7t\2\2\u00fb\u00fc\7t\2\2"+
		"\u00fc\u00fd\7g\2\2\u00fd\u00fe\7e\2\2\u00fe\u00ff\7v\2\2\u00ff\u0100"+
		"\7/\2\2\u0100\u0101\7c\2\2\u0101\u0102\7p\2\2\u0102\u0103\7u\2\2\u0103"+
		"\u0104\7y\2\2\u0104\u0105\7g\2\2\u0105\u0106\7t\2\2\u0106$\3\2\2\2\u0107"+
		"\u0108\7B\2\2\u0108\u0109\7c\2\2\u0109\u010a\7e\2\2\u010a\u010b\7e\2\2"+
		"\u010b\u010c\7g\2\2\u010c\u010d\7r\2\2\u010d\u010e\7v\2\2\u010e\u010f"+
		"\7g\2\2\u010f\u0110\7f\2\2\u0110\u0111\7/\2\2\u0111\u0112\7g\2\2\u0112"+
		"\u0113\7t\2\2\u0113\u0114\7t\2\2\u0114\u0115\7q\2\2\u0115\u0116\7t\2\2"+
		"\u0116&\3\2\2\2\u0117\u0118\7B\2\2\u0118\u0119\7h\2\2\u0119\u011a\7g\2"+
		"\2\u011a\u011b\7g\2\2\u011b\u011c\7f\2\2\u011c\u011d\7d\2\2\u011d\u011e"+
		"\7c\2\2\u011e\u011f\7e\2\2\u011f\u0120\7m\2\2\u0120(\3\2\2\2\u0121\u0122"+
		"\7B\2\2\u0122\u0123\7q\2\2\u0123\u0124\7r\2\2\u0124\u0125\7v\2\2\u0125"+
		"\u0126\7k\2\2\u0126\u0127\7q\2\2\u0127\u0128\7p\2\2\u0128\u0129\7u\2\2"+
		"\u0129*\3\2\2\2\u012a\u012b\7B\2\2\u012b\u012c\7g\2\2\u012c\u012d\7p\2"+
		"\2\u012d\u012e\7f\2\2\u012e\u012f\7/\2\2\u012f\u0130\7q\2\2\u0130\u0131"+
		"\7r\2\2\u0131\u0132\7v\2\2\u0132\u0133\7k\2\2\u0133\u0134\7q\2\2\u0134"+
		"\u0135\7p\2\2\u0135\u0136\7u\2\2\u0136,\3\2\2\2\u0137\u0138\7B\2\2\u0138"+
		"\u0139\7q\2\2\u0139\u013a\7r\2\2\u013a\u013b\7v\2\2\u013b\u013c\7k\2\2"+
		"\u013c\u013d\7q\2\2\u013d\u013e\7p\2\2\u013e.\3\2\2\2\u013f\u0140\7B\2"+
		"\2\u0140\u0141\7u\2\2\u0141\u0142\7v\2\2\u0142\u0143\7c\2\2\u0143\u0144"+
		"\7t\2\2\u0144\u0145\7v\2\2\u0145\u0146\7/\2\2\u0146\u0147\7o\2\2\u0147"+
		"\u0148\7c\2\2\u0148\u0149\7v\2\2\u0149\u014a\7e\2\2\u014a\u014b\7j\2\2"+
		"\u014b\u014c\7k\2\2\u014c\u014d\7p\2\2\u014d\u014e\7i\2\2\u014e\60\3\2"+
		"\2\2\u014f\u0150\7B\2\2\u0150\u0151\7g\2\2\u0151\u0152\7p\2\2\u0152\u0153"+
		"\7f\2\2\u0153\u0154\7/\2\2\u0154\u0155\7o\2\2\u0155\u0156\7c\2\2\u0156"+
		"\u0157\7v\2\2\u0157\u0158\7e\2\2\u0158\u0159\7j\2\2\u0159\u015a\7k\2\2"+
		"\u015a\u015b\7p\2\2\u015b\u015c\7i\2\2\u015c\62\3\2\2\2\u015d\u015e\7"+
		"B\2\2\u015e\u015f\7o\2\2\u015f\u0160\7c\2\2\u0160\u0161\7v\2\2\u0161\u0162"+
		"\7e\2\2\u0162\u0163\7j\2\2\u0163\64\3\2\2\2\u0164\u0166\t\4\2\2\u0165"+
		"\u0164\3\2\2\2\u0166\u0167\3\2\2\2\u0167\u0165\3\2\2\2\u0167\u0168\3\2"+
		"\2\2\u0168\u0169\3\2\2\2\u0169\u016a\b\33\2\2\u016a\66\3\2\2\2\u016b\u016c"+
		"\7\61\2\2\u016c\u016d\7\61\2\2\u016d\u0171\3\2\2\2\u016e\u0170\n\5\2\2"+
		"\u016f\u016e\3\2\2\2\u0170\u0173\3\2\2\2\u0171\u016f\3\2\2\2\u0171\u0172"+
		"\3\2\2\2\u0172\u0174\3\2\2\2\u0173\u0171\3\2\2\2\u0174\u0175\b\34\2\2"+
		"\u01758\3\2\2\2\n\2\u008a\u008c\u0094\u009a\u009c\u0167\u0171\3\b\2\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}