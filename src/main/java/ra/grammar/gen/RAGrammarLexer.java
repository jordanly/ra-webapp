// Generated from /Users/jordanly/Documents/workspace/ra3/src/main/java/ra.grammar/RAGrammar.g4 by ANTLR 4.5.1
package ra.grammar.gen;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RAGrammarLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, LEFT_PAREN=2, RIGHT_PAREN=3, STATEMENT_TERMINATOR=4, TABLE_NAME=5, 
		SELECT=6, PROJECT=7, JOIN=8, CROSS=9, UNION=10, DIFF=11, INTERSECT=12, 
		RENAME=13, SQLEXEC=14, OPERATOR_OPTION=15, INSIDE_OPERATOR_OPTION=16, 
		COMMENT=17;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"DIGIT", "ALPHA", "WS", "LEFT_PAREN", "RIGHT_PAREN", "STATEMENT_TERMINATOR", 
		"TABLE_NAME", "SELECT", "PROJECT", "JOIN", "CROSS", "UNION", "DIFF", "INTERSECT", 
		"RENAME", "SQLEXEC", "OPERATOR_OPTION", "INSIDE_OPERATOR_OPTION", "COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, "'('", "')'", "';'", null, "'\\select'", "'\\project'", "'\\join'", 
		"'\\cross'", "'\\union'", "'\\diff'", "'\\intersect'", "'\\rename'", "'\\sqlexec'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "WS", "LEFT_PAREN", "RIGHT_PAREN", "STATEMENT_TERMINATOR", "TABLE_NAME", 
		"SELECT", "PROJECT", "JOIN", "CROSS", "UNION", "DIFF", "INTERSECT", "RENAME", 
		"SQLEXEC", "OPERATOR_OPTION", "INSIDE_OPERATOR_OPTION", "COMMENT"
	};
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


	public RAGrammarLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "RAGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\23\u00a8\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\3\2\3\2\3\3\6\3-\n\3\r\3\16\3.\3\4\6\4\62\n"+
		"\4\r\4\16\4\63\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b\3\b\7\bB\n"+
		"\b\f\b\16\bE\13\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3\n\3\n\3\n"+
		"\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\f\3\f"+
		"\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3\16\3\17\3"+
		"\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3\20\3\20\3"+
		"\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\22\3"+
		"\22\3\22\3\22\7\22\u0092\n\22\f\22\16\22\u0095\13\22\3\22\3\22\3\23\3"+
		"\23\3\24\3\24\3\24\3\24\7\24\u009f\n\24\f\24\16\24\u00a2\13\24\3\24\3"+
		"\24\3\24\3\24\3\24\3\u00a0\2\25\3\2\5\2\7\3\t\4\13\5\r\6\17\7\21\b\23"+
		"\t\25\n\27\13\31\f\33\r\35\16\37\17!\20#\21%\22\'\23\3\2\6\3\2\62;\4\2"+
		"C\\c|\5\2\13\f\17\17\"\"\5\2\f\f\17\17\177\177\u00ac\2\7\3\2\2\2\2\t\3"+
		"\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2\2\2\21\3\2\2\2\2\23\3\2\2\2"+
		"\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2\33\3\2\2\2\2\35\3\2\2\2\2\37"+
		"\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2\2\'\3\2\2\2\3)\3\2\2\2\5,\3"+
		"\2\2\2\7\61\3\2\2\2\t\67\3\2\2\2\139\3\2\2\2\r;\3\2\2\2\17=\3\2\2\2\21"+
		"F\3\2\2\2\23N\3\2\2\2\25W\3\2\2\2\27]\3\2\2\2\31d\3\2\2\2\33k\3\2\2\2"+
		"\35q\3\2\2\2\37|\3\2\2\2!\u0084\3\2\2\2#\u008d\3\2\2\2%\u0098\3\2\2\2"+
		"\'\u009a\3\2\2\2)*\t\2\2\2*\4\3\2\2\2+-\t\3\2\2,+\3\2\2\2-.\3\2\2\2.,"+
		"\3\2\2\2./\3\2\2\2/\6\3\2\2\2\60\62\t\4\2\2\61\60\3\2\2\2\62\63\3\2\2"+
		"\2\63\61\3\2\2\2\63\64\3\2\2\2\64\65\3\2\2\2\65\66\b\4\2\2\66\b\3\2\2"+
		"\2\678\7*\2\28\n\3\2\2\29:\7+\2\2:\f\3\2\2\2;<\7=\2\2<\16\3\2\2\2=C\5"+
		"\5\3\2>B\5\5\3\2?B\5\3\2\2@B\7a\2\2A>\3\2\2\2A?\3\2\2\2A@\3\2\2\2BE\3"+
		"\2\2\2CA\3\2\2\2CD\3\2\2\2D\20\3\2\2\2EC\3\2\2\2FG\7^\2\2GH\7u\2\2HI\7"+
		"g\2\2IJ\7n\2\2JK\7g\2\2KL\7e\2\2LM\7v\2\2M\22\3\2\2\2NO\7^\2\2OP\7r\2"+
		"\2PQ\7t\2\2QR\7q\2\2RS\7l\2\2ST\7g\2\2TU\7e\2\2UV\7v\2\2V\24\3\2\2\2W"+
		"X\7^\2\2XY\7l\2\2YZ\7q\2\2Z[\7k\2\2[\\\7p\2\2\\\26\3\2\2\2]^\7^\2\2^_"+
		"\7e\2\2_`\7t\2\2`a\7q\2\2ab\7u\2\2bc\7u\2\2c\30\3\2\2\2de\7^\2\2ef\7w"+
		"\2\2fg\7p\2\2gh\7k\2\2hi\7q\2\2ij\7p\2\2j\32\3\2\2\2kl\7^\2\2lm\7f\2\2"+
		"mn\7k\2\2no\7h\2\2op\7h\2\2p\34\3\2\2\2qr\7^\2\2rs\7k\2\2st\7p\2\2tu\7"+
		"v\2\2uv\7g\2\2vw\7t\2\2wx\7u\2\2xy\7g\2\2yz\7e\2\2z{\7v\2\2{\36\3\2\2"+
		"\2|}\7^\2\2}~\7t\2\2~\177\7g\2\2\177\u0080\7p\2\2\u0080\u0081\7c\2\2\u0081"+
		"\u0082\7o\2\2\u0082\u0083\7g\2\2\u0083 \3\2\2\2\u0084\u0085\7^\2\2\u0085"+
		"\u0086\7u\2\2\u0086\u0087\7s\2\2\u0087\u0088\7n\2\2\u0088\u0089\7g\2\2"+
		"\u0089\u008a\7z\2\2\u008a\u008b\7g\2\2\u008b\u008c\7e\2\2\u008c\"\3\2"+
		"\2\2\u008d\u008e\7a\2\2\u008e\u008f\7}\2\2\u008f\u0093\3\2\2\2\u0090\u0092"+
		"\5%\23\2\u0091\u0090\3\2\2\2\u0092\u0095\3\2\2\2\u0093\u0091\3\2\2\2\u0093"+
		"\u0094\3\2\2\2\u0094\u0096\3\2\2\2\u0095\u0093\3\2\2\2\u0096\u0097\7\177"+
		"\2\2\u0097$\3\2\2\2\u0098\u0099\n\5\2\2\u0099&\3\2\2\2\u009a\u009b\7\61"+
		"\2\2\u009b\u009c\7,\2\2\u009c\u00a0\3\2\2\2\u009d\u009f\13\2\2\2\u009e"+
		"\u009d\3\2\2\2\u009f\u00a2\3\2\2\2\u00a0\u00a1\3\2\2\2\u00a0\u009e\3\2"+
		"\2\2\u00a1\u00a3\3\2\2\2\u00a2\u00a0\3\2\2\2\u00a3\u00a4\7,\2\2\u00a4"+
		"\u00a5\7\61\2\2\u00a5\u00a6\3\2\2\2\u00a6\u00a7\b\24\3\2\u00a7(\3\2\2"+
		"\2\t\2.\63AC\u0093\u00a0\4\b\2\2\2\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}