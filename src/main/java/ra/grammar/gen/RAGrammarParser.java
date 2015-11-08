// Generated from /Users/jordanly/Documents/workspace/ra3/src/main/java/ra/grammar/RAGrammar.g4 by ANTLR 4.5.1
package ra.grammar.gen;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class RAGrammarParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.1", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		WS=1, LEFT_PAREN=2, RIGHT_PAREN=3, STATEMENT_TERMINATOR=4, TABLE_NAME=5, 
		SELECT=6, PROJECT=7, JOIN=8, CROSS=9, UNION=10, DIFF=11, INTERSECT=12, 
		RENAME=13, SQLEXEC=14, OPERATOR_OPTION=15, INSIDE_OPERATOR_OPTION=16, 
		COMMENT=17, SINGLELINE_COMMENT=18;
	public static final int
		RULE_exp0 = 0, RULE_exp_unit = 1, RULE_exp_unary = 2, RULE_exp = 3, RULE_exp1 = 4;
	public static final String[] ruleNames = {
		"exp0", "exp_unit", "exp_unary", "exp", "exp1"
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

	@Override
	public String getGrammarFileName() { return "RAGrammar.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public RAGrammarParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class Exp0Context extends ParserRuleContext {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode STATEMENT_TERMINATOR() { return getToken(RAGrammarParser.STATEMENT_TERMINATOR, 0); }
		public TerminalNode EOF() { return getToken(RAGrammarParser.EOF, 0); }
		public Exp0Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp0; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RAGrammarListener ) ((RAGrammarListener)listener).enterExp0(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RAGrammarListener ) ((RAGrammarListener)listener).exitExp0(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RAGrammarVisitor ) return ((RAGrammarVisitor<? extends T>)visitor).visitExp0(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Exp0Context exp0() throws RecognitionException {
		Exp0Context _localctx = new Exp0Context(_ctx, getState());
		enterRule(_localctx, 0, RULE_exp0);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(10);
			exp();
			setState(11);
			match(STATEMENT_TERMINATOR);
			setState(12);
			match(EOF);
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
		public Exp_unitContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp_unit; }
	 
		public Exp_unitContext() { }
		public void copyFrom(Exp_unitContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class ParenExpContext extends Exp_unitContext {
		public TerminalNode LEFT_PAREN() { return getToken(RAGrammarParser.LEFT_PAREN, 0); }
		public Exp1Context exp1() {
			return getRuleContext(Exp1Context.class,0);
		}
		public TerminalNode RIGHT_PAREN() { return getToken(RAGrammarParser.RIGHT_PAREN, 0); }
		public ParenExpContext(Exp_unitContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RAGrammarListener ) ((RAGrammarListener)listener).enterParenExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RAGrammarListener ) ((RAGrammarListener)listener).exitParenExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RAGrammarVisitor ) return ((RAGrammarVisitor<? extends T>)visitor).visitParenExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class TableExpContext extends Exp_unitContext {
		public TerminalNode TABLE_NAME() { return getToken(RAGrammarParser.TABLE_NAME, 0); }
		public TableExpContext(Exp_unitContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RAGrammarListener ) ((RAGrammarListener)listener).enterTableExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RAGrammarListener ) ((RAGrammarListener)listener).exitTableExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RAGrammarVisitor ) return ((RAGrammarVisitor<? extends T>)visitor).visitTableExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Exp_unitContext exp_unit() throws RecognitionException {
		Exp_unitContext _localctx = new Exp_unitContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_exp_unit);
		try {
			setState(19);
			switch (_input.LA(1)) {
			case TABLE_NAME:
				_localctx = new TableExpContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(14);
				match(TABLE_NAME);
				}
				break;
			case LEFT_PAREN:
				_localctx = new ParenExpContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(15);
				match(LEFT_PAREN);
				setState(16);
				exp1();
				setState(17);
				match(RIGHT_PAREN);
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
		public Exp_unaryContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp_unary; }
	 
		public Exp_unaryContext() { }
		public void copyFrom(Exp_unaryContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class UnitExpContext extends Exp_unaryContext {
		public Exp_unitContext exp_unit() {
			return getRuleContext(Exp_unitContext.class,0);
		}
		public UnitExpContext(Exp_unaryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RAGrammarListener ) ((RAGrammarListener)listener).enterUnitExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RAGrammarListener ) ((RAGrammarListener)listener).exitUnitExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RAGrammarVisitor ) return ((RAGrammarVisitor<? extends T>)visitor).visitUnitExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class UnaryExpContext extends Exp_unaryContext {
		public TerminalNode SELECT() { return getToken(RAGrammarParser.SELECT, 0); }
		public TerminalNode OPERATOR_OPTION() { return getToken(RAGrammarParser.OPERATOR_OPTION, 0); }
		public Exp_unaryContext exp_unary() {
			return getRuleContext(Exp_unaryContext.class,0);
		}
		public TerminalNode PROJECT() { return getToken(RAGrammarParser.PROJECT, 0); }
		public TerminalNode RENAME() { return getToken(RAGrammarParser.RENAME, 0); }
		public UnaryExpContext(Exp_unaryContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RAGrammarListener ) ((RAGrammarListener)listener).enterUnaryExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RAGrammarListener ) ((RAGrammarListener)listener).exitUnaryExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RAGrammarVisitor ) return ((RAGrammarVisitor<? extends T>)visitor).visitUnaryExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Exp_unaryContext exp_unary() throws RecognitionException {
		Exp_unaryContext _localctx = new Exp_unaryContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_exp_unary);
		try {
			setState(31);
			switch (_input.LA(1)) {
			case LEFT_PAREN:
			case TABLE_NAME:
				_localctx = new UnitExpContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(21);
				exp_unit();
				}
				break;
			case SELECT:
				_localctx = new UnaryExpContext(_localctx);
				enterOuterAlt(_localctx, 2);
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
				_localctx = new UnaryExpContext(_localctx);
				enterOuterAlt(_localctx, 3);
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
				_localctx = new UnaryExpContext(_localctx);
				enterOuterAlt(_localctx, 4);
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
		public ExpContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp; }
	 
		public ExpContext() { }
		public void copyFrom(ExpContext ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class SingleUnaryExpContext extends ExpContext {
		public Exp_unaryContext exp_unary() {
			return getRuleContext(Exp_unaryContext.class,0);
		}
		public SingleUnaryExpContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RAGrammarListener ) ((RAGrammarListener)listener).enterSingleUnaryExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RAGrammarListener ) ((RAGrammarListener)listener).exitSingleUnaryExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RAGrammarVisitor ) return ((RAGrammarVisitor<? extends T>)visitor).visitSingleUnaryExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class BinaryExpContext extends ExpContext {
		public List<Exp_unaryContext> exp_unary() {
			return getRuleContexts(Exp_unaryContext.class);
		}
		public Exp_unaryContext exp_unary(int i) {
			return getRuleContext(Exp_unaryContext.class,i);
		}
		public TerminalNode JOIN() { return getToken(RAGrammarParser.JOIN, 0); }
		public TerminalNode CROSS() { return getToken(RAGrammarParser.CROSS, 0); }
		public TerminalNode UNION() { return getToken(RAGrammarParser.UNION, 0); }
		public TerminalNode DIFF() { return getToken(RAGrammarParser.DIFF, 0); }
		public TerminalNode INTERSECT() { return getToken(RAGrammarParser.INTERSECT, 0); }
		public BinaryExpContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RAGrammarListener ) ((RAGrammarListener)listener).enterBinaryExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RAGrammarListener ) ((RAGrammarListener)listener).exitBinaryExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RAGrammarVisitor ) return ((RAGrammarVisitor<? extends T>)visitor).visitBinaryExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class JoinExpContext extends ExpContext {
		public List<Exp_unaryContext> exp_unary() {
			return getRuleContexts(Exp_unaryContext.class);
		}
		public Exp_unaryContext exp_unary(int i) {
			return getRuleContext(Exp_unaryContext.class,i);
		}
		public TerminalNode JOIN() { return getToken(RAGrammarParser.JOIN, 0); }
		public TerminalNode OPERATOR_OPTION() { return getToken(RAGrammarParser.OPERATOR_OPTION, 0); }
		public JoinExpContext(ExpContext ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RAGrammarListener ) ((RAGrammarListener)listener).enterJoinExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RAGrammarListener ) ((RAGrammarListener)listener).exitJoinExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RAGrammarVisitor ) return ((RAGrammarVisitor<? extends T>)visitor).visitJoinExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExpContext exp() throws RecognitionException {
		ExpContext _localctx = new ExpContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_exp);
		try {
			setState(59);
			switch ( getInterpreter().adaptivePredict(_input,2,_ctx) ) {
			case 1:
				_localctx = new SingleUnaryExpContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(33);
				exp_unary();
				}
				break;
			case 2:
				_localctx = new JoinExpContext(_localctx);
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
				_localctx = new BinaryExpContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(39);
				exp_unary();
				setState(40);
				match(JOIN);
				setState(41);
				exp_unary();
				}
				break;
			case 4:
				_localctx = new BinaryExpContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(43);
				exp_unary();
				setState(44);
				match(CROSS);
				setState(45);
				exp_unary();
				}
				break;
			case 5:
				_localctx = new BinaryExpContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(47);
				exp_unary();
				setState(48);
				match(UNION);
				setState(49);
				exp_unary();
				}
				break;
			case 6:
				_localctx = new BinaryExpContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(51);
				exp_unary();
				setState(52);
				match(DIFF);
				setState(53);
				exp_unary();
				}
				break;
			case 7:
				_localctx = new BinaryExpContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(55);
				exp_unary();
				setState(56);
				match(INTERSECT);
				setState(57);
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

	public static class Exp1Context extends ParserRuleContext {
		public Exp1Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exp1; }
	 
		public Exp1Context() { }
		public void copyFrom(Exp1Context ctx) {
			super.copyFrom(ctx);
		}
	}
	public static class BinaryTermExpContext extends Exp1Context {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode JOIN() { return getToken(RAGrammarParser.JOIN, 0); }
		public Exp_unaryContext exp_unary() {
			return getRuleContext(Exp_unaryContext.class,0);
		}
		public TerminalNode CROSS() { return getToken(RAGrammarParser.CROSS, 0); }
		public TerminalNode UNION() { return getToken(RAGrammarParser.UNION, 0); }
		public TerminalNode DIFF() { return getToken(RAGrammarParser.DIFF, 0); }
		public TerminalNode INTERSECT() { return getToken(RAGrammarParser.INTERSECT, 0); }
		public BinaryTermExpContext(Exp1Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RAGrammarListener ) ((RAGrammarListener)listener).enterBinaryTermExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RAGrammarListener ) ((RAGrammarListener)listener).exitBinaryTermExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RAGrammarVisitor ) return ((RAGrammarVisitor<? extends T>)visitor).visitBinaryTermExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class JoinTermExpContext extends Exp1Context {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public TerminalNode JOIN() { return getToken(RAGrammarParser.JOIN, 0); }
		public TerminalNode OPERATOR_OPTION() { return getToken(RAGrammarParser.OPERATOR_OPTION, 0); }
		public Exp_unaryContext exp_unary() {
			return getRuleContext(Exp_unaryContext.class,0);
		}
		public JoinTermExpContext(Exp1Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RAGrammarListener ) ((RAGrammarListener)listener).enterJoinTermExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RAGrammarListener ) ((RAGrammarListener)listener).exitJoinTermExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RAGrammarVisitor ) return ((RAGrammarVisitor<? extends T>)visitor).visitJoinTermExp(this);
			else return visitor.visitChildren(this);
		}
	}
	public static class SingleTermExpContext extends Exp1Context {
		public ExpContext exp() {
			return getRuleContext(ExpContext.class,0);
		}
		public SingleTermExpContext(Exp1Context ctx) { copyFrom(ctx); }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof RAGrammarListener ) ((RAGrammarListener)listener).enterSingleTermExp(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof RAGrammarListener ) ((RAGrammarListener)listener).exitSingleTermExp(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof RAGrammarVisitor ) return ((RAGrammarVisitor<? extends T>)visitor).visitSingleTermExp(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Exp1Context exp1() throws RecognitionException {
		Exp1Context _localctx = new Exp1Context(_ctx, getState());
		enterRule(_localctx, 8, RULE_exp1);
		try {
			setState(87);
			switch ( getInterpreter().adaptivePredict(_input,3,_ctx) ) {
			case 1:
				_localctx = new SingleTermExpContext(_localctx);
				enterOuterAlt(_localctx, 1);
				{
				setState(61);
				exp();
				}
				break;
			case 2:
				_localctx = new JoinTermExpContext(_localctx);
				enterOuterAlt(_localctx, 2);
				{
				setState(62);
				exp();
				setState(63);
				match(JOIN);
				setState(64);
				match(OPERATOR_OPTION);
				setState(65);
				exp_unary();
				}
				break;
			case 3:
				_localctx = new BinaryTermExpContext(_localctx);
				enterOuterAlt(_localctx, 3);
				{
				setState(67);
				exp();
				setState(68);
				match(JOIN);
				setState(69);
				exp_unary();
				}
				break;
			case 4:
				_localctx = new BinaryTermExpContext(_localctx);
				enterOuterAlt(_localctx, 4);
				{
				setState(71);
				exp();
				setState(72);
				match(CROSS);
				setState(73);
				exp_unary();
				}
				break;
			case 5:
				_localctx = new BinaryTermExpContext(_localctx);
				enterOuterAlt(_localctx, 5);
				{
				setState(75);
				exp();
				setState(76);
				match(UNION);
				setState(77);
				exp_unary();
				}
				break;
			case 6:
				_localctx = new BinaryTermExpContext(_localctx);
				enterOuterAlt(_localctx, 6);
				{
				setState(79);
				exp();
				setState(80);
				match(DIFF);
				setState(81);
				exp_unary();
				}
				break;
			case 7:
				_localctx = new BinaryTermExpContext(_localctx);
				enterOuterAlt(_localctx, 7);
				{
				setState(83);
				exp();
				setState(84);
				match(INTERSECT);
				setState(85);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\24\\\4\2\t\2\4\3"+
		"\t\3\4\4\t\4\4\5\t\5\4\6\t\6\3\2\3\2\3\2\3\2\3\3\3\3\3\3\3\3\3\3\5\3\26"+
		"\n\3\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\3\4\5\4\"\n\4\3\5\3\5\3\5\3\5"+
		"\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3\5\3"+
		"\5\3\5\3\5\3\5\3\5\5\5>\n\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3"+
		"\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6Z\n"+
		"\6\3\6\2\2\7\2\4\6\b\n\2\2f\2\f\3\2\2\2\4\25\3\2\2\2\6!\3\2\2\2\b=\3\2"+
		"\2\2\nY\3\2\2\2\f\r\5\b\5\2\r\16\7\6\2\2\16\17\7\2\2\3\17\3\3\2\2\2\20"+
		"\26\7\7\2\2\21\22\7\4\2\2\22\23\5\n\6\2\23\24\7\5\2\2\24\26\3\2\2\2\25"+
		"\20\3\2\2\2\25\21\3\2\2\2\26\5\3\2\2\2\27\"\5\4\3\2\30\31\7\b\2\2\31\32"+
		"\7\21\2\2\32\"\5\6\4\2\33\34\7\t\2\2\34\35\7\21\2\2\35\"\5\6\4\2\36\37"+
		"\7\17\2\2\37 \7\21\2\2 \"\5\6\4\2!\27\3\2\2\2!\30\3\2\2\2!\33\3\2\2\2"+
		"!\36\3\2\2\2\"\7\3\2\2\2#>\5\6\4\2$%\5\6\4\2%&\7\n\2\2&\'\7\21\2\2\'("+
		"\5\6\4\2(>\3\2\2\2)*\5\6\4\2*+\7\n\2\2+,\5\6\4\2,>\3\2\2\2-.\5\6\4\2."+
		"/\7\13\2\2/\60\5\6\4\2\60>\3\2\2\2\61\62\5\6\4\2\62\63\7\f\2\2\63\64\5"+
		"\6\4\2\64>\3\2\2\2\65\66\5\6\4\2\66\67\7\r\2\2\678\5\6\4\28>\3\2\2\29"+
		":\5\6\4\2:;\7\16\2\2;<\5\6\4\2<>\3\2\2\2=#\3\2\2\2=$\3\2\2\2=)\3\2\2\2"+
		"=-\3\2\2\2=\61\3\2\2\2=\65\3\2\2\2=9\3\2\2\2>\t\3\2\2\2?Z\5\b\5\2@A\5"+
		"\b\5\2AB\7\n\2\2BC\7\21\2\2CD\5\6\4\2DZ\3\2\2\2EF\5\b\5\2FG\7\n\2\2GH"+
		"\5\6\4\2HZ\3\2\2\2IJ\5\b\5\2JK\7\13\2\2KL\5\6\4\2LZ\3\2\2\2MN\5\b\5\2"+
		"NO\7\f\2\2OP\5\6\4\2PZ\3\2\2\2QR\5\b\5\2RS\7\r\2\2ST\5\6\4\2TZ\3\2\2\2"+
		"UV\5\b\5\2VW\7\16\2\2WX\5\6\4\2XZ\3\2\2\2Y?\3\2\2\2Y@\3\2\2\2YE\3\2\2"+
		"\2YI\3\2\2\2YM\3\2\2\2YQ\3\2\2\2YU\3\2\2\2Z\13\3\2\2\2\6\25!=Y";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}