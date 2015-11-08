// Generated from /Users/jordanly/Documents/workspace/ra3/src/main/java/ra/grammar/RAGrammar.g4 by ANTLR 4.5.1
package ra.grammar.gen;
import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

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
		COMMENT=17, SINGLELINE_COMMENT=18;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"DIGIT", "ALPHA", "WS", "LEFT_PAREN", "RIGHT_PAREN", "STATEMENT_TERMINATOR", 
		"TABLE_NAME", "SELECT", "PROJECT", "JOIN", "CROSS", "UNION", "DIFF", "INTERSECT", 
		"RENAME", "SQLEXEC", "OPERATOR_OPTION", "INSIDE_OPERATOR_OPTION", "COMMENT", 
		"SINGLELINE_COMMENT"
	};

	private static final String[] _LITERAL_NAMES = {
		null, null, "'('", "')'", "';'", null, "'\\select'", "'\\project'", "'\\join'", 
		"'\\cross'", "'\\union'", "'\\diff'", "'\\intersect'", "'\\rename'", "'\\sqlexec'"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "WS", "LEFT_PAREN", "RIGHT_PAREN", "STATEMENT_TERMINATOR", "TABLE_NAME", 
		"SELECT", "PROJECT", "JOIN", "CROSS", "UNION", "DIFF", "INTERSECT", "RENAME", 
		"SQLEXEC", "OPERATOR_OPTION", "INSIDE_OPERATOR_OPTION", "COMMENT", "SINGLELINE_COMMENT"
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\24\u00b5\b\1\4\2"+
		"\t\2\4\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4"+
		"\13\t\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22"+
		"\t\22\4\23\t\23\4\24\t\24\4\25\t\25\3\2\3\2\3\3\6\3/\n\3\r\3\16\3\60\3"+
		"\4\6\4\64\n\4\r\4\16\4\65\3\4\3\4\3\5\3\5\3\6\3\6\3\7\3\7\3\b\3\b\3\b"+
		"\3\b\7\bD\n\b\f\b\16\bG\13\b\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\t\3\n\3\n\3"+
		"\n\3\n\3\n\3\n\3\n\3\n\3\n\3\13\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3"+
		"\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\3\r\3\r\3\16\3\16\3\16\3\16\3\16\3"+
		"\16\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\17\3\20\3\20\3"+
		"\20\3\20\3\20\3\20\3\20\3\20\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3\21\3"+
		"\21\3\22\3\22\3\22\3\22\7\22\u0094\n\22\f\22\16\22\u0097\13\22\3\22\3"+
		"\22\3\23\3\23\3\24\3\24\3\24\3\24\7\24\u00a1\n\24\f\24\16\24\u00a4\13"+
		"\24\3\24\3\24\3\24\3\24\3\24\3\25\3\25\3\25\3\25\7\25\u00af\n\25\f\25"+
		"\16\25\u00b2\13\25\3\25\3\25\3\u00a2\2\26\3\2\5\2\7\3\t\4\13\5\r\6\17"+
		"\7\21\b\23\t\25\n\27\13\31\f\33\r\35\16\37\17!\20#\21%\22\'\23)\24\3\2"+
		"\7\3\2\62;\4\2C\\c|\5\2\13\f\17\17\"\"\5\2\f\f\17\17\177\177\4\2\f\f\17"+
		"\17\u00ba\2\7\3\2\2\2\2\t\3\2\2\2\2\13\3\2\2\2\2\r\3\2\2\2\2\17\3\2\2"+
		"\2\2\21\3\2\2\2\2\23\3\2\2\2\2\25\3\2\2\2\2\27\3\2\2\2\2\31\3\2\2\2\2"+
		"\33\3\2\2\2\2\35\3\2\2\2\2\37\3\2\2\2\2!\3\2\2\2\2#\3\2\2\2\2%\3\2\2\2"+
		"\2\'\3\2\2\2\2)\3\2\2\2\3+\3\2\2\2\5.\3\2\2\2\7\63\3\2\2\2\t9\3\2\2\2"+
		"\13;\3\2\2\2\r=\3\2\2\2\17?\3\2\2\2\21H\3\2\2\2\23P\3\2\2\2\25Y\3\2\2"+
		"\2\27_\3\2\2\2\31f\3\2\2\2\33m\3\2\2\2\35s\3\2\2\2\37~\3\2\2\2!\u0086"+
		"\3\2\2\2#\u008f\3\2\2\2%\u009a\3\2\2\2\'\u009c\3\2\2\2)\u00aa\3\2\2\2"+
		"+,\t\2\2\2,\4\3\2\2\2-/\t\3\2\2.-\3\2\2\2/\60\3\2\2\2\60.\3\2\2\2\60\61"+
		"\3\2\2\2\61\6\3\2\2\2\62\64\t\4\2\2\63\62\3\2\2\2\64\65\3\2\2\2\65\63"+
		"\3\2\2\2\65\66\3\2\2\2\66\67\3\2\2\2\678\b\4\2\28\b\3\2\2\29:\7*\2\2:"+
		"\n\3\2\2\2;<\7+\2\2<\f\3\2\2\2=>\7=\2\2>\16\3\2\2\2?E\5\5\3\2@D\5\5\3"+
		"\2AD\5\3\2\2BD\7a\2\2C@\3\2\2\2CA\3\2\2\2CB\3\2\2\2DG\3\2\2\2EC\3\2\2"+
		"\2EF\3\2\2\2F\20\3\2\2\2GE\3\2\2\2HI\7^\2\2IJ\7u\2\2JK\7g\2\2KL\7n\2\2"+
		"LM\7g\2\2MN\7e\2\2NO\7v\2\2O\22\3\2\2\2PQ\7^\2\2QR\7r\2\2RS\7t\2\2ST\7"+
		"q\2\2TU\7l\2\2UV\7g\2\2VW\7e\2\2WX\7v\2\2X\24\3\2\2\2YZ\7^\2\2Z[\7l\2"+
		"\2[\\\7q\2\2\\]\7k\2\2]^\7p\2\2^\26\3\2\2\2_`\7^\2\2`a\7e\2\2ab\7t\2\2"+
		"bc\7q\2\2cd\7u\2\2de\7u\2\2e\30\3\2\2\2fg\7^\2\2gh\7w\2\2hi\7p\2\2ij\7"+
		"k\2\2jk\7q\2\2kl\7p\2\2l\32\3\2\2\2mn\7^\2\2no\7f\2\2op\7k\2\2pq\7h\2"+
		"\2qr\7h\2\2r\34\3\2\2\2st\7^\2\2tu\7k\2\2uv\7p\2\2vw\7v\2\2wx\7g\2\2x"+
		"y\7t\2\2yz\7u\2\2z{\7g\2\2{|\7e\2\2|}\7v\2\2}\36\3\2\2\2~\177\7^\2\2\177"+
		"\u0080\7t\2\2\u0080\u0081\7g\2\2\u0081\u0082\7p\2\2\u0082\u0083\7c\2\2"+
		"\u0083\u0084\7o\2\2\u0084\u0085\7g\2\2\u0085 \3\2\2\2\u0086\u0087\7^\2"+
		"\2\u0087\u0088\7u\2\2\u0088\u0089\7s\2\2\u0089\u008a\7n\2\2\u008a\u008b"+
		"\7g\2\2\u008b\u008c\7z\2\2\u008c\u008d\7g\2\2\u008d\u008e\7e\2\2\u008e"+
		"\"\3\2\2\2\u008f\u0090\7a\2\2\u0090\u0091\7}\2\2\u0091\u0095\3\2\2\2\u0092"+
		"\u0094\5%\23\2\u0093\u0092\3\2\2\2\u0094\u0097\3\2\2\2\u0095\u0093\3\2"+
		"\2\2\u0095\u0096\3\2\2\2\u0096\u0098\3\2\2\2\u0097\u0095\3\2\2\2\u0098"+
		"\u0099\7\177\2\2\u0099$\3\2\2\2\u009a\u009b\n\5\2\2\u009b&\3\2\2\2\u009c"+
		"\u009d\7\61\2\2\u009d\u009e\7,\2\2\u009e\u00a2\3\2\2\2\u009f\u00a1\13"+
		"\2\2\2\u00a0\u009f\3\2\2\2\u00a1\u00a4\3\2\2\2\u00a2\u00a3\3\2\2\2\u00a2"+
		"\u00a0\3\2\2\2\u00a3\u00a5\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a5\u00a6\7,"+
		"\2\2\u00a6\u00a7\7\61\2\2\u00a7\u00a8\3\2\2\2\u00a8\u00a9\b\24\3\2\u00a9"+
		"(\3\2\2\2\u00aa\u00ab\7\61\2\2\u00ab\u00ac\7\61\2\2\u00ac\u00b0\3\2\2"+
		"\2\u00ad\u00af\n\6\2\2\u00ae\u00ad\3\2\2\2\u00af\u00b2\3\2\2\2\u00b0\u00ae"+
		"\3\2\2\2\u00b0\u00b1\3\2\2\2\u00b1\u00b3\3\2\2\2\u00b2\u00b0\3\2\2\2\u00b3"+
		"\u00b4\b\25\3\2\u00b4*\3\2\2\2\n\2\60\65CE\u0095\u00a2\u00b0\4\b\2\2\2"+
		"\3\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}