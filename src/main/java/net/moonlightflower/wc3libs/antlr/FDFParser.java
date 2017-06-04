// Generated from FDF.g4 by ANTLR 4.5.3

	package net.moonlightflower.wc3libs.antlr;

import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.misc.*;
import org.antlr.v4.runtime.tree.*;
import java.util.List;
import java.util.Iterator;
import java.util.ArrayList;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FDFParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, CURLY_L=2, CURLY_R=3, COMMA=4, WS=5, ID=6, STRING_LITERAL=7;
	public static final int
		RULE_root = 0, RULE_stringList = 1, RULE_stringListContent = 2, RULE_stringAssign = 3;
	public static final String[] ruleNames = {
		"root", "stringList", "stringListContent", "stringAssign"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'StringList'", "'{'", "'}'", "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, "CURLY_L", "CURLY_R", "COMMA", "WS", "ID", "STRING_LITERAL"
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
	public String getGrammarFileName() { return "FDF.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public FDFParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class RootContext extends ParserRuleContext {
		public List<TerminalNode> WS() { return getTokens(FDFParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(FDFParser.WS, i);
		}
		public List<StringListContext> stringList() {
			return getRuleContexts(StringListContext.class);
		}
		public StringListContext stringList(int i) {
			return getRuleContext(StringListContext.class,i);
		}
		public RootContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_root; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FDFListener ) ((FDFListener)listener).enterRoot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FDFListener ) ((FDFListener)listener).exitRoot(this);
		}
	}

	public final RootContext root() throws RecognitionException {
		RootContext _localctx = new RootContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_root);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(12);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__0 || _la==WS) {
				{
				setState(10);
				switch (_input.LA(1)) {
				case WS:
					{
					setState(8);
					match(WS);
					}
					break;
				case T__0:
					{
					setState(9);
					stringList();
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				setState(14);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
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

	public static class StringListContext extends ParserRuleContext {
		public TerminalNode CURLY_L() { return getToken(FDFParser.CURLY_L, 0); }
		public StringListContentContext stringListContent() {
			return getRuleContext(StringListContentContext.class,0);
		}
		public TerminalNode CURLY_R() { return getToken(FDFParser.CURLY_R, 0); }
		public List<TerminalNode> WS() { return getTokens(FDFParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(FDFParser.WS, i);
		}
		public StringListContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringList; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FDFListener ) ((FDFListener)listener).enterStringList(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FDFListener ) ((FDFListener)listener).exitStringList(this);
		}
	}

	public final StringListContext stringList() throws RecognitionException {
		StringListContext _localctx = new StringListContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_stringList);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(15);
			match(T__0);
			setState(19);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WS) {
				{
				{
				setState(16);
				match(WS);
				}
				}
				setState(21);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(22);
			match(CURLY_L);
			setState(26);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WS) {
				{
				{
				setState(23);
				match(WS);
				}
				}
				setState(28);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(29);
			stringListContent();
			setState(33);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==WS) {
				{
				{
				setState(30);
				match(WS);
				}
				}
				setState(35);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(36);
			match(CURLY_R);
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

	public static class StringListContentContext extends ParserRuleContext {
		public List<StringAssignContext> stringAssign() {
			return getRuleContexts(StringAssignContext.class);
		}
		public StringAssignContext stringAssign(int i) {
			return getRuleContext(StringAssignContext.class,i);
		}
		public List<TerminalNode> COMMA() { return getTokens(FDFParser.COMMA); }
		public TerminalNode COMMA(int i) {
			return getToken(FDFParser.COMMA, i);
		}
		public List<TerminalNode> WS() { return getTokens(FDFParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(FDFParser.WS, i);
		}
		public StringListContentContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringListContent; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FDFListener ) ((FDFListener)listener).enterStringListContent(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FDFListener ) ((FDFListener)listener).exitStringListContent(this);
		}
	}

	public final StringListContentContext stringListContent() throws RecognitionException {
		StringListContentContext _localctx = new StringListContentContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_stringListContent);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			{
			setState(38);
			stringAssign();
			setState(42);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(39);
					match(WS);
					}
					} 
				}
				setState(44);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,5,_ctx);
			}
			}
			setState(61);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(45);
					match(COMMA);
					setState(49);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==WS) {
						{
						{
						setState(46);
						match(WS);
						}
						}
						setState(51);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(52);
					stringAssign();
					setState(56);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(53);
							match(WS);
							}
							} 
						}
						setState(58);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,7,_ctx);
					}
					}
					} 
				}
				setState(63);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,8,_ctx);
			}
			setState(65);
			_la = _input.LA(1);
			if (_la==COMMA) {
				{
				setState(64);
				match(COMMA);
				}
			}

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

	public static class StringAssignContext extends ParserRuleContext {
		public Token id;
		public Token val;
		public TerminalNode ID() { return getToken(FDFParser.ID, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(FDFParser.STRING_LITERAL, 0); }
		public List<TerminalNode> WS() { return getTokens(FDFParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(FDFParser.WS, i);
		}
		public StringAssignContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_stringAssign; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof FDFListener ) ((FDFListener)listener).enterStringAssign(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof FDFListener ) ((FDFListener)listener).exitStringAssign(this);
		}
	}

	public final StringAssignContext stringAssign() throws RecognitionException {
		StringAssignContext _localctx = new StringAssignContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_stringAssign);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(67);
			((StringAssignContext)_localctx).id = match(ID);
			setState(69); 
			_errHandler.sync(this);
			_la = _input.LA(1);
			do {
				{
				{
				setState(68);
				match(WS);
				}
				}
				setState(71); 
				_errHandler.sync(this);
				_la = _input.LA(1);
			} while ( _la==WS );
			setState(73);
			((StringAssignContext)_localctx).val = match(STRING_LITERAL);
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
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\3\tN\4\2\t\2\4\3\t"+
		"\3\4\4\t\4\4\5\t\5\3\2\3\2\7\2\r\n\2\f\2\16\2\20\13\2\3\3\3\3\7\3\24\n"+
		"\3\f\3\16\3\27\13\3\3\3\3\3\7\3\33\n\3\f\3\16\3\36\13\3\3\3\3\3\7\3\""+
		"\n\3\f\3\16\3%\13\3\3\3\3\3\3\4\3\4\7\4+\n\4\f\4\16\4.\13\4\3\4\3\4\7"+
		"\4\62\n\4\f\4\16\4\65\13\4\3\4\3\4\7\49\n\4\f\4\16\4<\13\4\7\4>\n\4\f"+
		"\4\16\4A\13\4\3\4\5\4D\n\4\3\5\3\5\6\5H\n\5\r\5\16\5I\3\5\3\5\3\5\2\2"+
		"\6\2\4\6\b\2\2T\2\16\3\2\2\2\4\21\3\2\2\2\6(\3\2\2\2\bE\3\2\2\2\n\r\7"+
		"\7\2\2\13\r\5\4\3\2\f\n\3\2\2\2\f\13\3\2\2\2\r\20\3\2\2\2\16\f\3\2\2\2"+
		"\16\17\3\2\2\2\17\3\3\2\2\2\20\16\3\2\2\2\21\25\7\3\2\2\22\24\7\7\2\2"+
		"\23\22\3\2\2\2\24\27\3\2\2\2\25\23\3\2\2\2\25\26\3\2\2\2\26\30\3\2\2\2"+
		"\27\25\3\2\2\2\30\34\7\4\2\2\31\33\7\7\2\2\32\31\3\2\2\2\33\36\3\2\2\2"+
		"\34\32\3\2\2\2\34\35\3\2\2\2\35\37\3\2\2\2\36\34\3\2\2\2\37#\5\6\4\2 "+
		"\"\7\7\2\2! \3\2\2\2\"%\3\2\2\2#!\3\2\2\2#$\3\2\2\2$&\3\2\2\2%#\3\2\2"+
		"\2&\'\7\5\2\2\'\5\3\2\2\2(,\5\b\5\2)+\7\7\2\2*)\3\2\2\2+.\3\2\2\2,*\3"+
		"\2\2\2,-\3\2\2\2-?\3\2\2\2.,\3\2\2\2/\63\7\6\2\2\60\62\7\7\2\2\61\60\3"+
		"\2\2\2\62\65\3\2\2\2\63\61\3\2\2\2\63\64\3\2\2\2\64\66\3\2\2\2\65\63\3"+
		"\2\2\2\66:\5\b\5\2\679\7\7\2\28\67\3\2\2\29<\3\2\2\2:8\3\2\2\2:;\3\2\2"+
		"\2;>\3\2\2\2<:\3\2\2\2=/\3\2\2\2>A\3\2\2\2?=\3\2\2\2?@\3\2\2\2@C\3\2\2"+
		"\2A?\3\2\2\2BD\7\6\2\2CB\3\2\2\2CD\3\2\2\2D\7\3\2\2\2EG\7\b\2\2FH\7\7"+
		"\2\2GF\3\2\2\2HI\3\2\2\2IG\3\2\2\2IJ\3\2\2\2JK\3\2\2\2KL\7\t\2\2L\t\3"+
		"\2\2\2\r\f\16\25\34#,\63:?CI";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}