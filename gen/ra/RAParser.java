// Generated from /Users/jordanly/Documents/workspace/ra3/src/main/java/ra/RAParser.g4 by ANTLR 4.5.1
package ra;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RAParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		STATEMENT_TERMINATOR=1, TABLE_NAME=2, LEFT_PAREN=3, RIGHT=4, PAREN=5, 
		SELECT=6, OPERATOR_OPTION=7, PROJECT=8, RENAME=9, JOIN=10, CROSS=11, UNION=12, 
		DIFF=13, INTERSECT=14;
	public static final int
		RULE_exp0 = 0, RULE_exp_unit = 1, RULE_exp_unary = 2, RULE_exp = 3;
	public static final String[] ruleNames = {
		"exp0", "exp_unit", "exp_unary", "exp"
	};

	private static final String[] _LITERAL_NAMES = {
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, "STATEMENT_TERMINATOR", "TABLE_NAME", "LEFT_PAREN", "RIGHT", "PAREN", 
		"SELECT", "OPERATOR_OPTION", "PROJECT", "RENAME", "JOIN", "CROSS", "UNION", 
		"DIFF", "INTERSECT"
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

	@Override
	public String getGrammarFileName() { return "RAParser.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public RAParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class Exp0Context extends ParserRuleContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode STATEMENT_TERMINATOR() { return getToken(RAParser.STATEMENT_TERMINATOR, 0); }
		public TerminalNode EOF() { return getToken(RAParser.EOF, 0); }
		public Exp0Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp0; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RAParserListener ) ((RAParserListener)listener).enterExp0(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RAParserListener ) ((RAParserListener)listener).exitExp0(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RAParserVisitor ) return ((RAParserVisitor<? extends T>)visitor).visitExp0(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Exp0Context exp0() throws RecognitionException {
		Exp0Context _localctx = new Exp0Context(_ctx, getState());
		enterRule(_localctx, 0, RULE_exp0);
		try {
			setState(12);
			switch (_input.LA(1)) {
			case SELECT:
			case PROJECT:
			case RENAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(8);
				exp();
				setState(9);
				match(STATEMENT_TERMINATOR);
				}
				break;
			case EOF:
				enterOuterAlt(_localctx, 2);
				{
				setState(11);
				match(EOF);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Exp_unitContext extends ParserRuleContext {
		public TerminalNode TABLE_NAME() { return getToken(RAParser.TABLE_NAME, 0); }
		public TerminalNode LEFT_PAREN() { return getToken(RAParser.LEFT_PAREN, 0); }
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode RIGHT() { return getToken(RAParser.RIGHT, 0); }
		public TerminalNode PAREN() { return getToken(RAParser.PAREN, 0); }
		public Exp_unitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp_unit; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RAParserListener ) ((RAParserListener)listener).enterExp_unit(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RAParserListener ) ((RAParserListener)listener).exitExp_unit(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RAParserVisitor ) return ((RAParserVisitor<? extends T>)visitor).visitExp_unit(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Exp_unitContext exp_unit() throws RecognitionException {
		Exp_unitContext _localctx = new Exp_unitContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_exp_unit);
		try {
			setState(20);
			switch (_input.LA(1)) {
			case TABLE_NAME:
				enterOuterAlt(_localctx, 1);
				{
				setState(14);
				match(TABLE_NAME);
				}
				break;
			case LEFT_PAREN:
				enterOuterAlt(_localctx, 2);
				{
				setState(15);
				match(LEFT_PAREN);
				setState(16);
				exp();
				setState(17);
				match(RIGHT);
				setState(18);
				match(PAREN);
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class Exp_unaryContext extends ParserRuleContext {
		public TerminalNode SELECT() { return getToken(RAParser.SELECT, 0); }
		public TerminalNode OPERATOR_OPTION() { return getToken(RAParser.OPERATOR_OPTION, 0); }
		public Exp_unaryContext exp_unary() {
			return getRuleContext(Exp_unaryContext.class,0);
		}
		public TerminalNode PROJECT() { return getToken(RAParser.PROJECT, 0); }
		public TerminalNode RENAME() { return getToken(RAParser.RENAME, 0); }
		public Exp_unaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp_unary; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RAParserListener ) ((RAParserListener)listener).enterExp_unary(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RAParserListener ) ((RAParserListener)listener).exitExp_unary(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RAParserVisitor ) return ((RAParserVisitor<? extends T>)visitor).visitExp_unary(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Exp_unaryContext exp_unary() throws RecognitionException {
		Exp_unaryContext _localctx = new Exp_unaryContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_exp_unary);
		try {
			setState(31);
			switch (_input.LA(1)) {
			case SELECT:
				enterOuterAlt(_localctx, 1);
				{
				setState(22);
				match(SELECT);
				setState(23);
				match(OPERATOR_OPTION);
				setState(24);
				exp_unary();
				}
				break;
			case PROJECT:
				enterOuterAlt(_localctx, 2);
				{
				setState(25);
				match(PROJECT);
				setState(26);
				match(OPERATOR_OPTION);
				setState(27);
				exp_unary();
				}
				break;
			case RENAME:
				enterOuterAlt(_localctx, 3);
				{
				setState(28);
				match(RENAME);
				setState(29);
				match(OPERATOR_OPTION);
				setState(30);
				exp_unary();
				}
				break;
			default:
				throw new NoViableAltException(this);
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static class ExpContext extends ParserRuleContext {
		public List<Exp_unaryContext> exp_unary() {
			return getRuleContexts(Exp_unaryContext.class);
		}
		public Exp_unaryContext exp_unary(int i) {
			return getRuleContext(Exp_unaryContext.class,i);
		}
		public TerminalNode JOIN() { return getToken(RAParser.JOIN, 0); }
		public TerminalNode OPERATOR_OPTION() { return getToken(RAParser.OPERATOR_OPTION, 0); }
		public TerminalNode CROSS() { return getToken(RAParser.CROSS, 0); }
		public TerminalNode UNION() { return getToken(RAParser.UNION, 0); }
		public TerminalNode DIFF() { return getToken(RAParser.DIFF, 0); }
		public TerminalNode INTERSECT() { return getToken(RAParser.INTERSECT, 0); }
		public ExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RAParserListener ) ((RAParserListener)listener).enterExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RAParserListener ) ((RAParserListener)listener).exitExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RAParserVisitor ) return ((RAParserVisitor<? extends T>)visitor).visitExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpContext exp() throws RecognitionException {
		ExpContext _localctx = new ExpContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_exp);
		try {
			setState(55);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(33);
				exp_unary();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(34);
				exp_unary();
				setState(35);
				match(JOIN);
				setState(36);
				match(OPERATOR_OPTION);
				setState(37);
				exp_unary();
				}
				break;
			case 3:
				enterOuterAlt(_localctx, 3);
				{
				setState(39);
				exp_unary();
				setState(40);
				match(CROSS);
				setState(41);
				exp_unary();
				}
				break;
			case 4:
				enterOuterAlt(_localctx, 4);
				{
				setState(43);
				exp_unary();
				setState(44);
				match(UNION);
				setState(45);
				exp_unary();
				}
				break;
			case 5:
				enterOuterAlt(_localctx, 5);
				{
				setState(47);
				exp_unary();
				setState(48);
				match(DIFF);
				setState(49);
				exp_unary();
				}
				break;
			case 6:
				enterOuterAlt(_localctx, 6);
				{
				setState(51);
				exp_unary();
				setState(52);
				match(INTERSECT);
				setState(53);
				exp_unary();
				}
				break;
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			exitRule();
		}
		return _localctx;
	}

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\20<\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\3\2\3\2\3\2\3\2\5\2\17\n\2\3\3\3\3\3\3\3\3\3\3\3\3"+
		"\5\3\27\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\"\n\4\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\5\5:\n\5\3\5\2\2\6\2\4\6\b\2\2@\2\16\3\2\2\2\4\26\3\2\2\2\6!\3"+
		"\2\2\2\b9\3\2\2\2\n\13\5\b\5\2\13\f\7\3\2\2\f\17\3\2\2\2\r\17\7\2\2\3"+
		"\16\n\3\2\2\2\16\r\3\2\2\2\17\3\3\2\2\2\20\27\7\4\2\2\21\22\7\5\2\2\22"+
		"\23\5\b\5\2\23\24\7\6\2\2\24\25\7\7\2\2\25\27\3\2\2\2\26\20\3\2\2\2\26"+
		"\21\3\2\2\2\27\5\3\2\2\2\30\31\7\b\2\2\31\32\7\t\2\2\32\"\5\6\4\2\33\34"+
		"\7\n\2\2\34\35\7\t\2\2\35\"\5\6\4\2\36\37\7\13\2\2\37 \7\t\2\2 \"\5\6"+
		"\4\2!\30\3\2\2\2!\33\3\2\2\2!\36\3\2\2\2\"\7\3\2\2\2#:\5\6\4\2$%\5\6\4"+
		"\2%&\7\f\2\2&\'\7\t\2\2\'(\5\6\4\2(:\3\2\2\2)*\5\6\4\2*+\7\r\2\2+,\5\6"+
		"\4\2,:\3\2\2\2-.\5\6\4\2./\7\16\2\2/\60\5\6\4\2\60:\3\2\2\2\61\62\5\6"+
		"\4\2\62\63\7\17\2\2\63\64\5\6\4\2\64:\3\2\2\2\65\66\5\6\4\2\66\67\7\20"+
		"\2\2\678\5\6\4\28:\3\2\2\29#\3\2\2\29$\3\2\2\29)\3\2\2\29-\3\2\2\29\61"+
		"\3\2\2\29\65\3\2\2\2:\t\3\2\2\2\6\16\26!9";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}