// Generated from E:/work/wc3libs/src/main/antlr\Jass.g4 by ANTLR 4.7

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
public class JassParser extends Parser {
	static { RuntimeMetaData.checkVersion("4.7", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, T__1=2, T__2=3, T__3=4, T__4=5, T__5=6, T__6=7, T__7=8, T__8=9, 
		T__9=10, T__10=11, T__11=12, T__12=13, T__13=14, T__14=15, T__15=16, T__16=17, 
		T__17=18, T__18=19, T__19=20, T__20=21, T__21=22, T__22=23, T__23=24, 
		T__24=25, T__25=26, T__26=27, T__27=28, T__28=29, T__29=30, T__30=31, 
		T__31=32, T__32=33, T__33=34, T__34=35, T__35=36, T__36=37, T__37=38, 
		T__38=39, T__39=40, T__40=41, T__41=42, BOOL_LITERAL=43, INT_LITERAL=44, 
		DEC_INT_LITERAL=45, OCT_INT_LITERAL=46, HEX_INT_LITERAL=47, ID_INT_LITERAL=48, 
		REAL_LITERAL=49, STRING_LITERAL=50, ID=51, CURLY_L=52, CURLY_R=53, COMMA=54, 
		NEW_LINE=55, WS=56;
	public static final int
		RULE_root = 0, RULE_globalsBlock = 1, RULE_varName = 2, RULE_funcName = 3, 
		RULE_globalDec = 4, RULE_surroundedExpr = 5, RULE_expr = 6, RULE_funcExpr = 7, 
		RULE_arg_list = 8, RULE_arrayRead = 9, RULE_funcRef = 10, RULE_literal = 11, 
		RULE_localVarDec = 12, RULE_localVarDec_list = 13, RULE_statement2 = 14, 
		RULE_statement = 15, RULE_statement_list = 16, RULE_callFunc = 17, RULE_setVar = 18, 
		RULE_condition = 19, RULE_selection2 = 20, RULE_selection = 21, RULE_loop = 22, 
		RULE_exitwhen = 23, RULE_loopBody = 24, RULE_loopBodyLine = 25, RULE_rule_return = 26, 
		RULE_typeName = 27, RULE_funcDec = 28, RULE_func = 29, RULE_funcReturnType = 30, 
		RULE_funcParam_list = 31, RULE_funcParam = 32, RULE_funcBody = 33, RULE_typeDec = 34, 
		RULE_nativeDec = 35;
	public static final String[] ruleNames = {
		"root", "globalsBlock", "varName", "funcName", "globalDec", "surroundedExpr", 
		"expr", "funcExpr", "arg_list", "arrayRead", "funcRef", "literal", "localVarDec", 
		"localVarDec_list", "statement2", "statement", "statement_list", "callFunc", 
		"setVar", "condition", "selection2", "selection", "loop", "exitwhen", 
		"loopBody", "loopBodyLine", "rule_return", "typeName", "funcDec", "func", 
		"funcReturnType", "funcParam_list", "funcParam", "funcBody", "typeDec", 
		"nativeDec"
	};

	private static final String[] _LITERAL_NAMES = {
		null, "'globals'", "'endglobals'", "'array'", "'constant'", "'='", "'('", 
		"')'", "'and'", "'or'", "'not'", "'+'", "'-'", "'*'", "'/'", "'<'", "'<='", 
		"'=='", "'!='", "'>'", "'>='", "'['", "']'", "'function'", "'local'", 
		"'call'", "'set'", "'if'", "'then'", "'endif'", "'elseif'", "'else'", 
		"'loop'", "'endloop'", "'exitwhen'", "'return'", "'takes'", "'returns'", 
		"'endfunction'", "'nothing'", "'type'", "'extends'", "'native'", null, 
		null, null, null, null, null, null, null, null, "'{'", "'}'", "','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, "BOOL_LITERAL", "INT_LITERAL", 
		"DEC_INT_LITERAL", "OCT_INT_LITERAL", "HEX_INT_LITERAL", "ID_INT_LITERAL", 
		"REAL_LITERAL", "STRING_LITERAL", "ID", "CURLY_L", "CURLY_R", "COMMA", 
		"NEW_LINE", "WS"
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
	public String getGrammarFileName() { return "Jass.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public ATN getATN() { return _ATN; }

	public JassParser(TokenStream input) {
		super(input);
		_interp = new ParserATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}
	public static class RootContext extends ParserRuleContext {
		public GlobalsBlockContext globalsBlock() {
			return getRuleContext(GlobalsBlockContext.class,0);
		}
		public List<TerminalNode> NEW_LINE() { return getTokens(JassParser.NEW_LINE); }
		public TerminalNode NEW_LINE(int i) {
			return getToken(JassParser.NEW_LINE, i);
		}
		public List<TypeDecContext> typeDec() {
			return getRuleContexts(TypeDecContext.class);
		}
		public TypeDecContext typeDec(int i) {
			return getRuleContext(TypeDecContext.class,i);
		}
		public List<NativeDecContext> nativeDec() {
			return getRuleContexts(NativeDecContext.class);
		}
		public NativeDecContext nativeDec(int i) {
			return getRuleContext(NativeDecContext.class,i);
		}
		public List<TerminalNode> WS() { return getTokens(JassParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(JassParser.WS, i);
		}
		public List<FuncContext> func() {
			return getRuleContexts(FuncContext.class);
		}
		public FuncContext func(int i) {
			return getRuleContext(FuncContext.class,i);
		}
		public List<TerminalNode> EOF() { return getTokens(JassParser.EOF); }
		public TerminalNode EOF(int i) {
			return getToken(JassParser.EOF, i);
		}
		public RootContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_root; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).enterRoot(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).exitRoot(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JassVisitor ) return ((JassVisitor<? extends T>)visitor).visitRoot(this);
			else return visitor.visitChildren(this);
		}
	}

	public final RootContext root() throws RecognitionException {
		RootContext _localctx = new RootContext(_ctx, getState());
		enterRule(_localctx, 0, RULE_root);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(75);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(72);
					_la = _input.LA(1);
					if ( !(_la==NEW_LINE || _la==WS) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					} 
				}
				setState(77);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,0,_ctx);
			}
			setState(91);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__39) {
				{
				{
				setState(78);
				typeDec();
				setState(80);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(79);
					match(WS);
					}
				}

				setState(82);
				match(NEW_LINE);
				setState(86);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(83);
						_la = _input.LA(1);
						if ( !(_la==NEW_LINE || _la==WS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						} 
					}
					setState(88);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,2,_ctx);
				}
				}
				}
				setState(93);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(97);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(94);
					_la = _input.LA(1);
					if ( !(_la==NEW_LINE || _la==WS) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					} 
				}
				setState(99);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,4,_ctx);
			}
			setState(113);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3 || _la==T__41) {
				{
				{
				setState(100);
				nativeDec();
				setState(102);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(101);
					match(WS);
					}
				}

				setState(104);
				match(NEW_LINE);
				setState(108);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(105);
						_la = _input.LA(1);
						if ( !(_la==NEW_LINE || _la==WS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						} 
					}
					setState(110);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,6,_ctx);
				}
				}
				}
				setState(115);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(117);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(116);
				match(WS);
				}
			}

			setState(119);
			globalsBlock();
			setState(121);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(120);
				match(WS);
				}
			}

			setState(123);
			match(NEW_LINE);
			setState(127);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(124);
					_la = _input.LA(1);
					if ( !(_la==NEW_LINE || _la==WS) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					} 
				}
				setState(129);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,10,_ctx);
			}
			setState(143);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(130);
					nativeDec();
					setState(132);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==WS) {
						{
						setState(131);
						match(WS);
						}
					}

					setState(134);
					_la = _input.LA(1);
					if ( !(_la==EOF || _la==NEW_LINE) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					setState(138);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
					while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
						if ( _alt==1 ) {
							{
							{
							setState(135);
							_la = _input.LA(1);
							if ( !(_la==NEW_LINE || _la==WS) ) {
							_errHandler.recoverInline(this);
							}
							else {
								if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
								_errHandler.reportMatch(this);
								consume();
							}
							}
							} 
						}
						setState(140);
						_errHandler.sync(this);
						_alt = getInterpreter().adaptivePredict(_input,12,_ctx);
					}
					}
					} 
				}
				setState(145);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,13,_ctx);
			}
			setState(149);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEW_LINE || _la==WS) {
				{
				{
				setState(146);
				_la = _input.LA(1);
				if ( !(_la==NEW_LINE || _la==WS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(151);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(165);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3 || _la==T__22) {
				{
				{
				setState(152);
				func();
				setState(154);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(153);
					match(WS);
					}
				}

				setState(156);
				match(NEW_LINE);
				setState(160);
				_errHandler.sync(this);
				_la = _input.LA(1);
				while (_la==NEW_LINE || _la==WS) {
					{
					{
					setState(157);
					_la = _input.LA(1);
					if ( !(_la==NEW_LINE || _la==WS) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					}
					setState(162);
					_errHandler.sync(this);
					_la = _input.LA(1);
				}
				}
				}
				setState(167);
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

	public static class GlobalsBlockContext extends ParserRuleContext {
		public List<TerminalNode> NEW_LINE() { return getTokens(JassParser.NEW_LINE); }
		public TerminalNode NEW_LINE(int i) {
			return getToken(JassParser.NEW_LINE, i);
		}
		public List<TerminalNode> WS() { return getTokens(JassParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(JassParser.WS, i);
		}
		public List<GlobalDecContext> globalDec() {
			return getRuleContexts(GlobalDecContext.class);
		}
		public GlobalDecContext globalDec(int i) {
			return getRuleContext(GlobalDecContext.class,i);
		}
		public GlobalsBlockContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_globalsBlock; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).enterGlobalsBlock(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).exitGlobalsBlock(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JassVisitor ) return ((JassVisitor<? extends T>)visitor).visitGlobalsBlock(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GlobalsBlockContext globalsBlock() throws RecognitionException {
		GlobalsBlockContext _localctx = new GlobalsBlockContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_globalsBlock);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(168);
			match(T__0);
			setState(170);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(169);
				match(WS);
				}
			}

			setState(172);
			match(NEW_LINE);
			setState(176);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(173);
					_la = _input.LA(1);
					if ( !(_la==NEW_LINE || _la==WS) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					} 
				}
				setState(178);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,19,_ctx);
			}
			setState(192);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3 || _la==ID) {
				{
				{
				setState(179);
				globalDec();
				setState(181);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(180);
					match(WS);
					}
				}

				setState(183);
				match(NEW_LINE);
				setState(187);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(184);
						_la = _input.LA(1);
						if ( !(_la==NEW_LINE || _la==WS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						} 
					}
					setState(189);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,21,_ctx);
				}
				}
				}
				setState(194);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(198);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEW_LINE || _la==WS) {
				{
				{
				setState(195);
				_la = _input.LA(1);
				if ( !(_la==NEW_LINE || _la==WS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(200);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(201);
			match(T__1);
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

	public static class VarNameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(JassParser.ID, 0); }
		public VarNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_varName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).enterVarName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).exitVarName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JassVisitor ) return ((JassVisitor<? extends T>)visitor).visitVarName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final VarNameContext varName() throws RecognitionException {
		VarNameContext _localctx = new VarNameContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_varName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(203);
			match(ID);
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

	public static class FuncNameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(JassParser.ID, 0); }
		public FuncNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).enterFuncName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).exitFuncName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JassVisitor ) return ((JassVisitor<? extends T>)visitor).visitFuncName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncNameContext funcName() throws RecognitionException {
		FuncNameContext _localctx = new FuncNameContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_funcName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(205);
			match(ID);
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

	public static class GlobalDecContext extends ParserRuleContext {
		public TypeNameContext type;
		public VarNameContext name;
		public ExprContext val;
		public List<TerminalNode> WS() { return getTokens(JassParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(JassParser.WS, i);
		}
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public VarNameContext varName() {
			return getRuleContext(VarNameContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public GlobalDecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_globalDec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).enterGlobalDec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).exitGlobalDec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JassVisitor ) return ((JassVisitor<? extends T>)visitor).visitGlobalDec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final GlobalDecContext globalDec() throws RecognitionException {
		GlobalDecContext _localctx = new GlobalDecContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_globalDec);
		int _la;
		try {
			setState(230);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(207);
				((GlobalDecContext)_localctx).type = typeName();
				setState(208);
				match(WS);
				setState(209);
				match(T__2);
				setState(210);
				match(WS);
				setState(211);
				((GlobalDecContext)_localctx).name = varName();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(215);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__3) {
					{
					setState(213);
					match(T__3);
					setState(214);
					match(WS);
					}
				}

				setState(217);
				((GlobalDecContext)_localctx).type = typeName();
				setState(218);
				match(WS);
				setState(219);
				((GlobalDecContext)_localctx).name = varName();
				setState(228);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,27,_ctx) ) {
				case 1:
					{
					setState(221);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==WS) {
						{
						setState(220);
						match(WS);
						}
					}

					setState(223);
					match(T__4);
					setState(225);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==WS) {
						{
						setState(224);
						match(WS);
						}
					}

					setState(227);
					((GlobalDecContext)_localctx).val = expr(0);
					}
					break;
				}
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

	public static class SurroundedExprContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(JassParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(JassParser.WS, i);
		}
		public SurroundedExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_surroundedExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).enterSurroundedExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).exitSurroundedExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JassVisitor ) return ((JassVisitor<? extends T>)visitor).visitSurroundedExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SurroundedExprContext surroundedExpr() throws RecognitionException {
		SurroundedExprContext _localctx = new SurroundedExprContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_surroundedExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(232);
			match(T__5);
			setState(234);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(233);
				match(WS);
				}
			}

			setState(236);
			expr(0);
			setState(238);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(237);
				match(WS);
				}
			}

			setState(240);
			match(T__6);
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

	public static class ExprContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> WS() { return getTokens(JassParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(JassParser.WS, i);
		}
		public LiteralContext literal() {
			return getRuleContext(LiteralContext.class,0);
		}
		public FuncExprContext funcExpr() {
			return getRuleContext(FuncExprContext.class,0);
		}
		public VarNameContext varName() {
			return getRuleContext(VarNameContext.class,0);
		}
		public ArrayReadContext arrayRead() {
			return getRuleContext(ArrayReadContext.class,0);
		}
		public FuncRefContext funcRef() {
			return getRuleContext(FuncRefContext.class,0);
		}
		public ExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_expr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).enterExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).exitExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JassVisitor ) return ((JassVisitor<? extends T>)visitor).visitExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExprContext expr() throws RecognitionException {
		return expr(0);
	}

	private ExprContext expr(int _p) throws RecognitionException {
		ParserRuleContext _parentctx = _ctx;
		int _parentState = getState();
		ExprContext _localctx = new ExprContext(_ctx, _parentState);
		ExprContext _prevctx = _localctx;
		int _startState = 12;
		enterRecursionRule(_localctx, 12, RULE_expr, _p);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(320);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,46,_ctx) ) {
			case 1:
				{
				setState(243);
				match(T__5);
				setState(245);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(244);
					match(WS);
					}
				}

				setState(247);
				expr(0);
				setState(249);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(248);
					match(WS);
					}
				}

				setState(251);
				match(T__6);
				setState(253);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(252);
					match(WS);
					}
				}

				setState(255);
				_la = _input.LA(1);
				if ( !(_la==T__7 || _la==T__8) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(257);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(256);
					match(WS);
					}
				}

				setState(259);
				match(T__5);
				setState(261);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(260);
					match(WS);
					}
				}

				setState(263);
				expr(0);
				setState(265);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(264);
					match(WS);
					}
				}

				setState(267);
				match(T__6);
				}
				break;
			case 2:
				{
				setState(269);
				match(T__5);
				setState(271);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(270);
					match(WS);
					}
				}

				setState(273);
				expr(0);
				setState(275);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(274);
					match(WS);
					}
				}

				setState(277);
				match(T__6);
				setState(279);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(278);
					match(WS);
					}
				}

				setState(281);
				_la = _input.LA(1);
				if ( !(_la==T__7 || _la==T__8) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(282);
				match(WS);
				setState(283);
				expr(11);
				}
				break;
			case 3:
				{
				setState(285);
				match(T__9);
				setState(298);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case WS:
					{
					setState(286);
					match(WS);
					setState(287);
					expr(0);
					}
					break;
				case T__5:
					{
					setState(288);
					match(T__5);
					setState(290);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==WS) {
						{
						setState(289);
						match(WS);
						}
					}

					setState(292);
					expr(0);
					setState(294);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==WS) {
						{
						setState(293);
						match(WS);
						}
					}

					setState(296);
					match(T__6);
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				}
				break;
			case 4:
				{
				setState(300);
				literal();
				}
				break;
			case 5:
				{
				setState(301);
				_la = _input.LA(1);
				if ( !(_la==T__10 || _la==T__11) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(303);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(302);
					match(WS);
					}
				}

				setState(305);
				expr(7);
				}
				break;
			case 6:
				{
				setState(306);
				funcExpr();
				}
				break;
			case 7:
				{
				setState(307);
				varName();
				}
				break;
			case 8:
				{
				setState(308);
				arrayRead();
				}
				break;
			case 9:
				{
				setState(309);
				funcRef();
				}
				break;
			case 10:
				{
				setState(310);
				match(T__5);
				setState(312);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(311);
					match(WS);
					}
				}

				setState(314);
				expr(0);
				setState(316);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(315);
					match(WS);
					}
				}

				setState(318);
				match(T__6);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(363);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(361);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,54,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(322);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(323);
						match(WS);
						setState(324);
						_la = _input.LA(1);
						if ( !(_la==T__7 || _la==T__8) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(325);
						match(WS);
						setState(326);
						expr(15);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(327);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(329);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==WS) {
							{
							setState(328);
							match(WS);
							}
						}

						setState(331);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(333);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==WS) {
							{
							setState(332);
							match(WS);
							}
						}

						setState(335);
						expr(9);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(336);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(338);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==WS) {
							{
							setState(337);
							match(WS);
							}
						}

						setState(340);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(342);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==WS) {
							{
							setState(341);
							match(WS);
							}
						}

						setState(344);
						expr(7);
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(345);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(346);
						match(WS);
						setState(347);
						_la = _input.LA(1);
						if ( !(_la==T__7 || _la==T__8) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(349);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==WS) {
							{
							setState(348);
							match(WS);
							}
						}

						setState(351);
						match(T__5);
						setState(353);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==WS) {
							{
							setState(352);
							match(WS);
							}
						}

						setState(355);
						expr(0);
						setState(357);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==WS) {
							{
							setState(356);
							match(WS);
							}
						}

						setState(359);
						match(T__6);
						}
						break;
					}
					} 
				}
				setState(365);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,55,_ctx);
			}
			}
		}
		catch (RecognitionException re) {
			_localctx.exception = re;
			_errHandler.reportError(this, re);
			_errHandler.recover(this, re);
		}
		finally {
			unrollRecursionContexts(_parentctx);
		}
		return _localctx;
	}

	public static class FuncExprContext extends ParserRuleContext {
		public FuncNameContext funcName() {
			return getRuleContext(FuncNameContext.class,0);
		}
		public Arg_listContext arg_list() {
			return getRuleContext(Arg_listContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(JassParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(JassParser.WS, i);
		}
		public FuncExprContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcExpr; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).enterFuncExpr(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).exitFuncExpr(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JassVisitor ) return ((JassVisitor<? extends T>)visitor).visitFuncExpr(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncExprContext funcExpr() throws RecognitionException {
		FuncExprContext _localctx = new FuncExprContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_funcExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(366);
			funcName();
			setState(368);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(367);
				match(WS);
				}
			}

			setState(370);
			match(T__5);
			setState(372);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,57,_ctx) ) {
			case 1:
				{
				setState(371);
				match(WS);
				}
				break;
			}
			setState(374);
			arg_list();
			setState(376);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(375);
				match(WS);
				}
			}

			setState(378);
			match(T__6);
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

	public static class Arg_listContext extends ParserRuleContext {
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public List<TerminalNode> WS() { return getTokens(JassParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(JassParser.WS, i);
		}
		public Arg_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arg_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).enterArg_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).exitArg_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JassVisitor ) return ((JassVisitor<? extends T>)visitor).visitArg_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Arg_listContext arg_list() throws RecognitionException {
		Arg_listContext _localctx = new Arg_listContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_arg_list);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(394);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__22) | (1L << BOOL_LITERAL) | (1L << INT_LITERAL) | (1L << REAL_LITERAL) | (1L << STRING_LITERAL) | (1L << ID))) != 0)) {
				{
				setState(380);
				expr(0);
				setState(391);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,61,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(382);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==WS) {
							{
							setState(381);
							match(WS);
							}
						}

						setState(384);
						match(COMMA);
						setState(386);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==WS) {
							{
							setState(385);
							match(WS);
							}
						}

						setState(388);
						expr(0);
						}
						} 
					}
					setState(393);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,61,_ctx);
				}
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

	public static class ArrayReadContext extends ParserRuleContext {
		public VarNameContext varName() {
			return getRuleContext(VarNameContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ArrayReadContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_arrayRead; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).enterArrayRead(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).exitArrayRead(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JassVisitor ) return ((JassVisitor<? extends T>)visitor).visitArrayRead(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ArrayReadContext arrayRead() throws RecognitionException {
		ArrayReadContext _localctx = new ArrayReadContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_arrayRead);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(396);
			varName();
			setState(397);
			match(T__20);
			setState(398);
			expr(0);
			setState(399);
			match(T__21);
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

	public static class FuncRefContext extends ParserRuleContext {
		public TerminalNode WS() { return getToken(JassParser.WS, 0); }
		public FuncNameContext funcName() {
			return getRuleContext(FuncNameContext.class,0);
		}
		public FuncRefContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcRef; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).enterFuncRef(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).exitFuncRef(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JassVisitor ) return ((JassVisitor<? extends T>)visitor).visitFuncRef(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncRefContext funcRef() throws RecognitionException {
		FuncRefContext _localctx = new FuncRefContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_funcRef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(401);
			match(T__22);
			setState(402);
			match(WS);
			setState(403);
			funcName();
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

	public static class LiteralContext extends ParserRuleContext {
		public TerminalNode BOOL_LITERAL() { return getToken(JassParser.BOOL_LITERAL, 0); }
		public TerminalNode INT_LITERAL() { return getToken(JassParser.INT_LITERAL, 0); }
		public TerminalNode REAL_LITERAL() { return getToken(JassParser.REAL_LITERAL, 0); }
		public TerminalNode STRING_LITERAL() { return getToken(JassParser.STRING_LITERAL, 0); }
		public FuncRefContext funcRef() {
			return getRuleContext(FuncRefContext.class,0);
		}
		public LiteralContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_literal; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).enterLiteral(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).exitLiteral(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JassVisitor ) return ((JassVisitor<? extends T>)visitor).visitLiteral(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_literal);
		try {
			setState(410);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOL_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(405);
				match(BOOL_LITERAL);
				}
				break;
			case INT_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(406);
				match(INT_LITERAL);
				}
				break;
			case REAL_LITERAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(407);
				match(REAL_LITERAL);
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 4);
				{
				setState(408);
				match(STRING_LITERAL);
				}
				break;
			case T__22:
				enterOuterAlt(_localctx, 5);
				{
				setState(409);
				funcRef();
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

	public static class LocalVarDecContext extends ParserRuleContext {
		public VarNameContext name;
		public List<TerminalNode> WS() { return getTokens(JassParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(JassParser.WS, i);
		}
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public VarNameContext varName() {
			return getRuleContext(VarNameContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public LocalVarDecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_localVarDec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).enterLocalVarDec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).exitLocalVarDec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JassVisitor ) return ((JassVisitor<? extends T>)visitor).visitLocalVarDec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LocalVarDecContext localVarDec() throws RecognitionException {
		LocalVarDecContext _localctx = new LocalVarDecContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_localVarDec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(412);
			match(T__23);
			setState(434);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,67,_ctx) ) {
			case 1:
				{
				{
				setState(413);
				match(WS);
				setState(414);
				typeName();
				setState(415);
				match(WS);
				setState(416);
				match(T__2);
				setState(417);
				match(WS);
				setState(418);
				((LocalVarDecContext)_localctx).name = varName();
				}
				}
				break;
			case 2:
				{
				{
				setState(420);
				match(WS);
				setState(421);
				typeName();
				setState(422);
				match(WS);
				setState(423);
				((LocalVarDecContext)_localctx).name = varName();
				setState(432);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,66,_ctx) ) {
				case 1:
					{
					setState(425);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==WS) {
						{
						setState(424);
						match(WS);
						}
					}

					setState(427);
					match(T__4);
					setState(429);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==WS) {
						{
						setState(428);
						match(WS);
						}
					}

					setState(431);
					expr(0);
					}
					break;
				}
				}
				}
				break;
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

	public static class LocalVarDec_listContext extends ParserRuleContext {
		public List<LocalVarDecContext> localVarDec() {
			return getRuleContexts(LocalVarDecContext.class);
		}
		public LocalVarDecContext localVarDec(int i) {
			return getRuleContext(LocalVarDecContext.class,i);
		}
		public List<TerminalNode> NEW_LINE() { return getTokens(JassParser.NEW_LINE); }
		public TerminalNode NEW_LINE(int i) {
			return getToken(JassParser.NEW_LINE, i);
		}
		public List<TerminalNode> WS() { return getTokens(JassParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(JassParser.WS, i);
		}
		public LocalVarDec_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_localVarDec_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).enterLocalVarDec_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).exitLocalVarDec_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JassVisitor ) return ((JassVisitor<? extends T>)visitor).visitLocalVarDec_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LocalVarDec_listContext localVarDec_list() throws RecognitionException {
		LocalVarDec_listContext _localctx = new LocalVarDec_listContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_localVarDec_list);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(436);
			localVarDec();
			setState(450);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,70,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(438);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==WS) {
						{
						setState(437);
						match(WS);
						}
					}

					setState(440);
					match(NEW_LINE);
					setState(444);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==NEW_LINE || _la==WS) {
						{
						{
						setState(441);
						_la = _input.LA(1);
						if ( !(_la==NEW_LINE || _la==WS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						}
						setState(446);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(447);
					localVarDec();
					}
					} 
				}
				setState(452);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,70,_ctx);
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

	public static class Statement2Context extends ParserRuleContext {
		public CallFuncContext callFunc() {
			return getRuleContext(CallFuncContext.class,0);
		}
		public SelectionContext selection() {
			return getRuleContext(SelectionContext.class,0);
		}
		public Statement2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).enterStatement2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).exitStatement2(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JassVisitor ) return ((JassVisitor<? extends T>)visitor).visitStatement2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Statement2Context statement2() throws RecognitionException {
		Statement2Context _localctx = new Statement2Context(_ctx, getState());
		enterRule(_localctx, 28, RULE_statement2);
		try {
			setState(455);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__24:
				enterOuterAlt(_localctx, 1);
				{
				setState(453);
				callFunc();
				}
				break;
			case T__26:
				enterOuterAlt(_localctx, 2);
				{
				setState(454);
				selection();
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

	public static class StatementContext extends ParserRuleContext {
		public CallFuncContext callFunc() {
			return getRuleContext(CallFuncContext.class,0);
		}
		public SetVarContext setVar() {
			return getRuleContext(SetVarContext.class,0);
		}
		public SelectionContext selection() {
			return getRuleContext(SelectionContext.class,0);
		}
		public LoopContext loop() {
			return getRuleContext(LoopContext.class,0);
		}
		public ExitwhenContext exitwhen() {
			return getRuleContext(ExitwhenContext.class,0);
		}
		public Rule_returnContext rule_return() {
			return getRuleContext(Rule_returnContext.class,0);
		}
		public StatementContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).enterStatement(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).exitStatement(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JassVisitor ) return ((JassVisitor<? extends T>)visitor).visitStatement(this);
			else return visitor.visitChildren(this);
		}
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_statement);
		try {
			setState(463);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__24:
				enterOuterAlt(_localctx, 1);
				{
				setState(457);
				callFunc();
				}
				break;
			case T__25:
				enterOuterAlt(_localctx, 2);
				{
				setState(458);
				setVar();
				}
				break;
			case T__26:
				enterOuterAlt(_localctx, 3);
				{
				setState(459);
				selection();
				}
				break;
			case T__31:
				enterOuterAlt(_localctx, 4);
				{
				setState(460);
				loop();
				}
				break;
			case T__33:
				enterOuterAlt(_localctx, 5);
				{
				setState(461);
				exitwhen();
				}
				break;
			case T__34:
				enterOuterAlt(_localctx, 6);
				{
				setState(462);
				rule_return();
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

	public static class Statement_listContext extends ParserRuleContext {
		public List<StatementContext> statement() {
			return getRuleContexts(StatementContext.class);
		}
		public StatementContext statement(int i) {
			return getRuleContext(StatementContext.class,i);
		}
		public List<TerminalNode> NEW_LINE() { return getTokens(JassParser.NEW_LINE); }
		public TerminalNode NEW_LINE(int i) {
			return getToken(JassParser.NEW_LINE, i);
		}
		public List<TerminalNode> WS() { return getTokens(JassParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(JassParser.WS, i);
		}
		public Statement_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_statement_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).enterStatement_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).exitStatement_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JassVisitor ) return ((JassVisitor<? extends T>)visitor).visitStatement_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Statement_listContext statement_list() throws RecognitionException {
		Statement_listContext _localctx = new Statement_listContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_statement_list);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(465);
			statement();
			setState(479);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,75,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(467);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==WS) {
						{
						setState(466);
						match(WS);
						}
					}

					setState(469);
					match(NEW_LINE);
					setState(473);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==NEW_LINE || _la==WS) {
						{
						{
						setState(470);
						_la = _input.LA(1);
						if ( !(_la==NEW_LINE || _la==WS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						}
						setState(475);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(476);
					statement();
					}
					} 
				}
				setState(481);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,75,_ctx);
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

	public static class CallFuncContext extends ParserRuleContext {
		public TerminalNode WS() { return getToken(JassParser.WS, 0); }
		public FuncExprContext funcExpr() {
			return getRuleContext(FuncExprContext.class,0);
		}
		public CallFuncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_callFunc; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).enterCallFunc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).exitCallFunc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JassVisitor ) return ((JassVisitor<? extends T>)visitor).visitCallFunc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final CallFuncContext callFunc() throws RecognitionException {
		CallFuncContext _localctx = new CallFuncContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_callFunc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(482);
			match(T__24);
			setState(483);
			match(WS);
			setState(484);
			funcExpr();
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

	public static class SetVarContext extends ParserRuleContext {
		public VarNameContext name;
		public ExprContext index;
		public ExprContext val;
		public List<TerminalNode> WS() { return getTokens(JassParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(JassParser.WS, i);
		}
		public VarNameContext varName() {
			return getRuleContext(VarNameContext.class,0);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public SetVarContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_setVar; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).enterSetVar(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).exitSetVar(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JassVisitor ) return ((JassVisitor<? extends T>)visitor).visitSetVar(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SetVarContext setVar() throws RecognitionException {
		SetVarContext _localctx = new SetVarContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_setVar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(486);
			match(T__25);
			setState(487);
			match(WS);
			setState(488);
			((SetVarContext)_localctx).name = varName();
			setState(496);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,77,_ctx) ) {
			case 1:
				{
				setState(490);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(489);
					match(WS);
					}
				}

				setState(492);
				match(T__20);
				setState(493);
				((SetVarContext)_localctx).index = expr(0);
				setState(494);
				match(T__21);
				}
				break;
			}
			setState(499);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(498);
				match(WS);
				}
			}

			setState(501);
			match(T__4);
			setState(503);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(502);
				match(WS);
				}
			}

			setState(505);
			((SetVarContext)_localctx).val = expr(0);
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

	public static class ConditionContext extends ParserRuleContext {
		public SurroundedExprContext surroundedExpr() {
			return getRuleContext(SurroundedExprContext.class,0);
		}
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public ConditionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_condition; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).enterCondition(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).exitCondition(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JassVisitor ) return ((JassVisitor<? extends T>)visitor).visitCondition(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(509);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,80,_ctx) ) {
			case 1:
				{
				setState(507);
				surroundedExpr();
				}
				break;
			case 2:
				{
				setState(508);
				expr(0);
				}
				break;
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

	public static class Selection2Context extends ParserRuleContext {
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public List<TerminalNode> NEW_LINE() { return getTokens(JassParser.NEW_LINE); }
		public TerminalNode NEW_LINE(int i) {
			return getToken(JassParser.NEW_LINE, i);
		}
		public List<TerminalNode> WS() { return getTokens(JassParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(JassParser.WS, i);
		}
		public Selection2Context(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selection2; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).enterSelection2(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).exitSelection2(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JassVisitor ) return ((JassVisitor<? extends T>)visitor).visitSelection2(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Selection2Context selection2() throws RecognitionException {
		Selection2Context _localctx = new Selection2Context(_ctx, getState());
		enterRule(_localctx, 40, RULE_selection2);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(511);
			match(T__26);
			setState(513);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(512);
				match(WS);
				}
			}

			setState(515);
			condition();
			setState(517);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(516);
				match(WS);
				}
			}

			setState(519);
			match(T__27);
			setState(521);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(520);
				match(WS);
				}
			}

			setState(523);
			match(NEW_LINE);
			setState(527);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEW_LINE || _la==WS) {
				{
				{
				setState(524);
				_la = _input.LA(1);
				if ( !(_la==NEW_LINE || _la==WS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(529);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(530);
			match(T__28);
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

	public static class SelectionContext extends ParserRuleContext {
		public ConditionContext condition() {
			return getRuleContext(ConditionContext.class,0);
		}
		public List<TerminalNode> NEW_LINE() { return getTokens(JassParser.NEW_LINE); }
		public TerminalNode NEW_LINE(int i) {
			return getToken(JassParser.NEW_LINE, i);
		}
		public List<TerminalNode> WS() { return getTokens(JassParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(JassParser.WS, i);
		}
		public List<Statement_listContext> statement_list() {
			return getRuleContexts(Statement_listContext.class);
		}
		public Statement_listContext statement_list(int i) {
			return getRuleContext(Statement_listContext.class,i);
		}
		public List<SurroundedExprContext> surroundedExpr() {
			return getRuleContexts(SurroundedExprContext.class);
		}
		public SurroundedExprContext surroundedExpr(int i) {
			return getRuleContext(SurroundedExprContext.class,i);
		}
		public List<ExprContext> expr() {
			return getRuleContexts(ExprContext.class);
		}
		public ExprContext expr(int i) {
			return getRuleContext(ExprContext.class,i);
		}
		public SelectionContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_selection; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).enterSelection(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).exitSelection(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JassVisitor ) return ((JassVisitor<? extends T>)visitor).visitSelection(this);
			else return visitor.visitChildren(this);
		}
	}

	public final SelectionContext selection() throws RecognitionException {
		SelectionContext _localctx = new SelectionContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_selection);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(532);
			match(T__26);
			setState(534);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(533);
				match(WS);
				}
			}

			setState(536);
			condition();
			setState(538);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(537);
				match(WS);
				}
			}

			setState(540);
			match(T__27);
			setState(542);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(541);
				match(WS);
				}
			}

			setState(544);
			match(NEW_LINE);
			setState(548);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,88,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(545);
					_la = _input.LA(1);
					if ( !(_la==NEW_LINE || _la==WS) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					} 
				}
				setState(550);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,88,_ctx);
			}
			setState(552);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__31) | (1L << T__33) | (1L << T__34))) != 0)) {
				{
				setState(551);
				statement_list();
				}
			}

			setState(557);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,90,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(554);
					_la = _input.LA(1);
					if ( !(_la==NEW_LINE || _la==WS) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					} 
				}
				setState(559);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,90,_ctx);
			}
			setState(591);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__29) {
				{
				{
				setState(560);
				match(T__29);
				setState(564);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__5:
					{
					setState(561);
					surroundedExpr();
					}
					break;
				case WS:
					{
					{
					setState(562);
					match(WS);
					setState(563);
					expr(0);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(567);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(566);
					match(WS);
					}
				}

				setState(569);
				match(T__27);
				setState(571);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(570);
					match(WS);
					}
				}

				setState(573);
				match(NEW_LINE);
				setState(577);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,94,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(574);
						_la = _input.LA(1);
						if ( !(_la==NEW_LINE || _la==WS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						} 
					}
					setState(579);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,94,_ctx);
				}
				setState(581);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__31) | (1L << T__33) | (1L << T__34))) != 0)) {
					{
					setState(580);
					statement_list();
					}
				}

				setState(586);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,96,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(583);
						_la = _input.LA(1);
						if ( !(_la==NEW_LINE || _la==WS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						} 
					}
					setState(588);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,96,_ctx);
				}
				}
				}
				setState(593);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(614);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__30) {
				{
				setState(594);
				match(T__30);
				setState(596);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(595);
					match(WS);
					}
				}

				setState(598);
				match(NEW_LINE);
				setState(602);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,99,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(599);
						_la = _input.LA(1);
						if ( !(_la==NEW_LINE || _la==WS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						} 
					}
					setState(604);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,99,_ctx);
				}
				setState(606);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__31) | (1L << T__33) | (1L << T__34))) != 0)) {
					{
					setState(605);
					statement_list();
					}
				}

				setState(611);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,101,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(608);
						_la = _input.LA(1);
						if ( !(_la==NEW_LINE || _la==WS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						} 
					}
					setState(613);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,101,_ctx);
				}
				}
			}

			setState(619);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEW_LINE || _la==WS) {
				{
				{
				setState(616);
				_la = _input.LA(1);
				if ( !(_la==NEW_LINE || _la==WS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(621);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(622);
			match(T__28);
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

	public static class LoopContext extends ParserRuleContext {
		public List<TerminalNode> NEW_LINE() { return getTokens(JassParser.NEW_LINE); }
		public TerminalNode NEW_LINE(int i) {
			return getToken(JassParser.NEW_LINE, i);
		}
		public List<TerminalNode> WS() { return getTokens(JassParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(JassParser.WS, i);
		}
		public LoopBodyContext loopBody() {
			return getRuleContext(LoopBodyContext.class,0);
		}
		public LoopContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loop; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).enterLoop(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).exitLoop(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JassVisitor ) return ((JassVisitor<? extends T>)visitor).visitLoop(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LoopContext loop() throws RecognitionException {
		LoopContext _localctx = new LoopContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_loop);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(624);
			match(T__31);
			setState(626);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(625);
				match(WS);
				}
			}

			setState(628);
			match(NEW_LINE);
			setState(632);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,105,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(629);
					_la = _input.LA(1);
					if ( !(_la==NEW_LINE || _la==WS) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					} 
				}
				setState(634);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,105,_ctx);
			}
			setState(641);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__31) | (1L << T__33) | (1L << T__34))) != 0)) {
				{
				setState(635);
				loopBody();
				setState(637);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(636);
					match(WS);
					}
				}

				setState(639);
				match(NEW_LINE);
				}
			}

			setState(646);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEW_LINE || _la==WS) {
				{
				{
				setState(643);
				_la = _input.LA(1);
				if ( !(_la==NEW_LINE || _la==WS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(648);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(649);
			match(T__32);
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

	public static class ExitwhenContext extends ParserRuleContext {
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(JassParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(JassParser.WS, i);
		}
		public ExitwhenContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_exitwhen; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).enterExitwhen(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).exitExitwhen(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JassVisitor ) return ((JassVisitor<? extends T>)visitor).visitExitwhen(this);
			else return visitor.visitChildren(this);
		}
	}

	public final ExitwhenContext exitwhen() throws RecognitionException {
		ExitwhenContext _localctx = new ExitwhenContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_exitwhen);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(651);
			match(T__33);
			setState(664);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WS:
				{
				{
				setState(652);
				match(WS);
				setState(653);
				expr(0);
				}
				}
				break;
			case T__5:
				{
				setState(654);
				match(T__5);
				setState(656);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(655);
					match(WS);
					}
				}

				setState(658);
				expr(0);
				setState(660);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(659);
					match(WS);
					}
				}

				setState(662);
				match(T__6);
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class LoopBodyContext extends ParserRuleContext {
		public List<LoopBodyLineContext> loopBodyLine() {
			return getRuleContexts(LoopBodyLineContext.class);
		}
		public LoopBodyLineContext loopBodyLine(int i) {
			return getRuleContext(LoopBodyLineContext.class,i);
		}
		public List<TerminalNode> NEW_LINE() { return getTokens(JassParser.NEW_LINE); }
		public TerminalNode NEW_LINE(int i) {
			return getToken(JassParser.NEW_LINE, i);
		}
		public List<TerminalNode> WS() { return getTokens(JassParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(JassParser.WS, i);
		}
		public LoopBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loopBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).enterLoopBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).exitLoopBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JassVisitor ) return ((JassVisitor<? extends T>)visitor).visitLoopBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LoopBodyContext loopBody() throws RecognitionException {
		LoopBodyContext _localctx = new LoopBodyContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_loopBody);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(666);
			loopBodyLine();
			setState(677);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,113,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(667);
					match(NEW_LINE);
					setState(671);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==NEW_LINE || _la==WS) {
						{
						{
						setState(668);
						_la = _input.LA(1);
						if ( !(_la==NEW_LINE || _la==WS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						}
						setState(673);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(674);
					loopBodyLine();
					}
					} 
				}
				setState(679);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,113,_ctx);
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

	public static class LoopBodyLineContext extends ParserRuleContext {
		public Statement_listContext statement_list() {
			return getRuleContext(Statement_listContext.class,0);
		}
		public LoopBodyLineContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_loopBodyLine; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).enterLoopBodyLine(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).exitLoopBodyLine(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JassVisitor ) return ((JassVisitor<? extends T>)visitor).visitLoopBodyLine(this);
			else return visitor.visitChildren(this);
		}
	}

	public final LoopBodyLineContext loopBodyLine() throws RecognitionException {
		LoopBodyLineContext _localctx = new LoopBodyLineContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_loopBodyLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(680);
			statement_list();
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

	public static class Rule_returnContext extends ParserRuleContext {
		public ExprContext name;
		public SurroundedExprContext surroundedExpr() {
			return getRuleContext(SurroundedExprContext.class,0);
		}
		public TerminalNode WS() { return getToken(JassParser.WS, 0); }
		public ExprContext expr() {
			return getRuleContext(ExprContext.class,0);
		}
		public Rule_returnContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_rule_return; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).enterRule_return(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).exitRule_return(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JassVisitor ) return ((JassVisitor<? extends T>)visitor).visitRule_return(this);
			else return visitor.visitChildren(this);
		}
	}

	public final Rule_returnContext rule_return() throws RecognitionException {
		Rule_returnContext _localctx = new Rule_returnContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_rule_return);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(682);
			match(T__34);
			setState(688);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				{
				setState(683);
				surroundedExpr();
				}
				break;
			case T__28:
			case T__29:
			case T__30:
			case NEW_LINE:
			case WS:
				{
				setState(686);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,114,_ctx) ) {
				case 1:
					{
					setState(684);
					match(WS);
					setState(685);
					((Rule_returnContext)_localctx).name = expr(0);
					}
					break;
				}
				}
				break;
			default:
				throw new NoViableAltException(this);
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

	public static class TypeNameContext extends ParserRuleContext {
		public TerminalNode ID() { return getToken(JassParser.ID, 0); }
		public TypeNameContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeName; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).enterTypeName(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).exitTypeName(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JassVisitor ) return ((JassVisitor<? extends T>)visitor).visitTypeName(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeNameContext typeName() throws RecognitionException {
		TypeNameContext _localctx = new TypeNameContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_typeName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(690);
			match(ID);
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

	public static class FuncDecContext extends ParserRuleContext {
		public Token name;
		public FuncParam_listContext params;
		public FuncReturnTypeContext returnType;
		public List<TerminalNode> WS() { return getTokens(JassParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(JassParser.WS, i);
		}
		public TerminalNode ID() { return getToken(JassParser.ID, 0); }
		public FuncParam_listContext funcParam_list() {
			return getRuleContext(FuncParam_listContext.class,0);
		}
		public FuncReturnTypeContext funcReturnType() {
			return getRuleContext(FuncReturnTypeContext.class,0);
		}
		public FuncDecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcDec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).enterFuncDec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).exitFuncDec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JassVisitor ) return ((JassVisitor<? extends T>)visitor).visitFuncDec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncDecContext funcDec() throws RecognitionException {
		FuncDecContext _localctx = new FuncDecContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_funcDec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(693);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(692);
				match(T__3);
				}
			}

			setState(695);
			match(T__22);
			setState(696);
			match(WS);
			setState(697);
			((FuncDecContext)_localctx).name = match(ID);
			setState(698);
			match(WS);
			setState(699);
			match(T__35);
			setState(700);
			match(WS);
			setState(701);
			((FuncDecContext)_localctx).params = funcParam_list();
			setState(702);
			match(WS);
			setState(703);
			match(T__36);
			setState(704);
			match(WS);
			setState(705);
			((FuncDecContext)_localctx).returnType = funcReturnType();
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

	public static class FuncContext extends ParserRuleContext {
		public FuncBodyContext body;
		public FuncDecContext funcDec() {
			return getRuleContext(FuncDecContext.class,0);
		}
		public List<TerminalNode> NEW_LINE() { return getTokens(JassParser.NEW_LINE); }
		public TerminalNode NEW_LINE(int i) {
			return getToken(JassParser.NEW_LINE, i);
		}
		public List<TerminalNode> WS() { return getTokens(JassParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(JassParser.WS, i);
		}
		public FuncBodyContext funcBody() {
			return getRuleContext(FuncBodyContext.class,0);
		}
		public FuncContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_func; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).enterFunc(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).exitFunc(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JassVisitor ) return ((JassVisitor<? extends T>)visitor).visitFunc(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncContext func() throws RecognitionException {
		FuncContext _localctx = new FuncContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_func);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(707);
			funcDec();
			setState(709);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(708);
				match(WS);
				}
			}

			setState(711);
			match(NEW_LINE);
			setState(715);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,118,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(712);
					_la = _input.LA(1);
					if ( !(_la==NEW_LINE || _la==WS) ) {
					_errHandler.recoverInline(this);
					}
					else {
						if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
						_errHandler.reportMatch(this);
						consume();
					}
					}
					} 
				}
				setState(717);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,118,_ctx);
			}
			setState(724);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,120,_ctx) ) {
			case 1:
				{
				setState(718);
				((FuncContext)_localctx).body = funcBody();
				setState(720);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(719);
					match(WS);
					}
				}

				setState(722);
				match(NEW_LINE);
				}
				break;
			}
			setState(729);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEW_LINE || _la==WS) {
				{
				{
				setState(726);
				_la = _input.LA(1);
				if ( !(_la==NEW_LINE || _la==WS) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				}
				}
				setState(731);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(732);
			match(T__37);
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

	public static class FuncReturnTypeContext extends ParserRuleContext {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public FuncReturnTypeContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcReturnType; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).enterFuncReturnType(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).exitFuncReturnType(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JassVisitor ) return ((JassVisitor<? extends T>)visitor).visitFuncReturnType(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncReturnTypeContext funcReturnType() throws RecognitionException {
		FuncReturnTypeContext _localctx = new FuncReturnTypeContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_funcReturnType);
		try {
			setState(736);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__38:
				enterOuterAlt(_localctx, 1);
				{
				setState(734);
				match(T__38);
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(735);
				typeName();
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

	public static class FuncParam_listContext extends ParserRuleContext {
		public FuncParamContext params;
		public List<FuncParamContext> funcParam() {
			return getRuleContexts(FuncParamContext.class);
		}
		public FuncParamContext funcParam(int i) {
			return getRuleContext(FuncParamContext.class,i);
		}
		public List<TerminalNode> WS() { return getTokens(JassParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(JassParser.WS, i);
		}
		public FuncParam_listContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcParam_list; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).enterFuncParam_list(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).exitFuncParam_list(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JassVisitor ) return ((JassVisitor<? extends T>)visitor).visitFuncParam_list(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncParam_listContext funcParam_list() throws RecognitionException {
		FuncParam_listContext _localctx = new FuncParam_listContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_funcParam_list);
		int _la;
		try {
			int _alt;
			setState(753);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__38:
				enterOuterAlt(_localctx, 1);
				{
				setState(738);
				match(T__38);
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(739);
				((FuncParam_listContext)_localctx).params = funcParam();
				setState(750);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,125,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(741);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==WS) {
							{
							setState(740);
							match(WS);
							}
						}

						setState(743);
						match(COMMA);
						setState(745);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==WS) {
							{
							setState(744);
							match(WS);
							}
						}

						setState(747);
						funcParam();
						}
						} 
					}
					setState(752);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,125,_ctx);
				}
				}
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

	public static class FuncParamContext extends ParserRuleContext {
		public TypeNameContext typeName() {
			return getRuleContext(TypeNameContext.class,0);
		}
		public TerminalNode WS() { return getToken(JassParser.WS, 0); }
		public TerminalNode ID() { return getToken(JassParser.ID, 0); }
		public FuncParamContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcParam; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).enterFuncParam(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).exitFuncParam(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JassVisitor ) return ((JassVisitor<? extends T>)visitor).visitFuncParam(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncParamContext funcParam() throws RecognitionException {
		FuncParamContext _localctx = new FuncParamContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_funcParam);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(755);
			typeName();
			setState(756);
			match(WS);
			setState(757);
			match(ID);
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

	public static class FuncBodyContext extends ParserRuleContext {
		public LocalVarDec_listContext localVarDec_list() {
			return getRuleContext(LocalVarDec_listContext.class,0);
		}
		public List<TerminalNode> NEW_LINE() { return getTokens(JassParser.NEW_LINE); }
		public TerminalNode NEW_LINE(int i) {
			return getToken(JassParser.NEW_LINE, i);
		}
		public Statement_listContext statement_list() {
			return getRuleContext(Statement_listContext.class,0);
		}
		public List<TerminalNode> WS() { return getTokens(JassParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(JassParser.WS, i);
		}
		public FuncBodyContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_funcBody; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).enterFuncBody(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).exitFuncBody(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JassVisitor ) return ((JassVisitor<? extends T>)visitor).visitFuncBody(this);
			else return visitor.visitChildren(this);
		}
	}

	public final FuncBodyContext funcBody() throws RecognitionException {
		FuncBodyContext _localctx = new FuncBodyContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_funcBody);
		int _la;
		try {
			setState(778);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,132,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(760);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__23) {
					{
					setState(759);
					localVarDec_list();
					}
				}

				setState(773);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,130,_ctx) ) {
				case 1:
					{
					setState(763);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==WS) {
						{
						setState(762);
						match(WS);
						}
					}

					setState(765);
					match(NEW_LINE);
					setState(769);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==NEW_LINE || _la==WS) {
						{
						{
						setState(766);
						_la = _input.LA(1);
						if ( !(_la==NEW_LINE || _la==WS) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						}
						}
						setState(771);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(772);
					statement_list();
					}
					break;
				}
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(776);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__31) | (1L << T__33) | (1L << T__34))) != 0)) {
					{
					setState(775);
					statement_list();
					}
				}

				}
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

	public static class TypeDecContext extends ParserRuleContext {
		public Token name;
		public Token parent;
		public List<TerminalNode> WS() { return getTokens(JassParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(JassParser.WS, i);
		}
		public List<TerminalNode> ID() { return getTokens(JassParser.ID); }
		public TerminalNode ID(int i) {
			return getToken(JassParser.ID, i);
		}
		public TypeDecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_typeDec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).enterTypeDec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).exitTypeDec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JassVisitor ) return ((JassVisitor<? extends T>)visitor).visitTypeDec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final TypeDecContext typeDec() throws RecognitionException {
		TypeDecContext _localctx = new TypeDecContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_typeDec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(780);
			match(T__39);
			setState(781);
			match(WS);
			setState(782);
			((TypeDecContext)_localctx).name = match(ID);
			setState(783);
			match(WS);
			setState(784);
			match(T__40);
			setState(785);
			match(WS);
			setState(786);
			((TypeDecContext)_localctx).parent = match(ID);
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

	public static class NativeDecContext extends ParserRuleContext {
		public Token name;
		public FuncParam_listContext params;
		public FuncReturnTypeContext returnType;
		public List<TerminalNode> WS() { return getTokens(JassParser.WS); }
		public TerminalNode WS(int i) {
			return getToken(JassParser.WS, i);
		}
		public TerminalNode ID() { return getToken(JassParser.ID, 0); }
		public FuncParam_listContext funcParam_list() {
			return getRuleContext(FuncParam_listContext.class,0);
		}
		public FuncReturnTypeContext funcReturnType() {
			return getRuleContext(FuncReturnTypeContext.class,0);
		}
		public NativeDecContext(ParserRuleContext parent, int invokingState) {
			super(parent, invokingState);
		}
		@Override public int getRuleIndex() { return RULE_nativeDec; }
		@Override
		public void enterRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).enterNativeDec(this);
		}
		@Override
		public void exitRule(ParseTreeListener listener) {
			if ( listener instanceof JassListener ) ((JassListener)listener).exitNativeDec(this);
		}
		@Override
		public <T> T accept(ParseTreeVisitor<? extends T> visitor) {
			if ( visitor instanceof JassVisitor ) return ((JassVisitor<? extends T>)visitor).visitNativeDec(this);
			else return visitor.visitChildren(this);
		}
	}

	public final NativeDecContext nativeDec() throws RecognitionException {
		NativeDecContext _localctx = new NativeDecContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_nativeDec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(790);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(788);
				match(T__3);
				setState(789);
				match(WS);
				}
			}

			setState(792);
			match(T__41);
			setState(793);
			match(WS);
			setState(794);
			((NativeDecContext)_localctx).name = match(ID);
			setState(795);
			match(WS);
			setState(796);
			match(T__35);
			setState(797);
			match(WS);
			setState(798);
			((NativeDecContext)_localctx).params = funcParam_list();
			setState(799);
			match(WS);
			setState(800);
			match(T__36);
			setState(801);
			match(WS);
			setState(802);
			((NativeDecContext)_localctx).returnType = funcReturnType();
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

	public boolean sempred(RuleContext _localctx, int ruleIndex, int predIndex) {
		switch (ruleIndex) {
		case 6:
			return expr_sempred((ExprContext)_localctx, predIndex);
		}
		return true;
	}
	private boolean expr_sempred(ExprContext _localctx, int predIndex) {
		switch (predIndex) {
		case 0:
			return precpred(_ctx, 14);
		case 1:
			return precpred(_ctx, 8);
		case 2:
			return precpred(_ctx, 6);
		case 3:
			return precpred(_ctx, 12);
		}
		return true;
	}

	public static final String _serializedATN =
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3:\u0327\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\4\13\t"+
		"\13\4\f\t\f\4\r\t\r\4\16\t\16\4\17\t\17\4\20\t\20\4\21\t\21\4\22\t\22"+
		"\4\23\t\23\4\24\t\24\4\25\t\25\4\26\t\26\4\27\t\27\4\30\t\30\4\31\t\31"+
		"\4\32\t\32\4\33\t\33\4\34\t\34\4\35\t\35\4\36\t\36\4\37\t\37\4 \t \4!"+
		"\t!\4\"\t\"\4#\t#\4$\t$\4%\t%\3\2\7\2L\n\2\f\2\16\2O\13\2\3\2\3\2\5\2"+
		"S\n\2\3\2\3\2\7\2W\n\2\f\2\16\2Z\13\2\7\2\\\n\2\f\2\16\2_\13\2\3\2\7\2"+
		"b\n\2\f\2\16\2e\13\2\3\2\3\2\5\2i\n\2\3\2\3\2\7\2m\n\2\f\2\16\2p\13\2"+
		"\7\2r\n\2\f\2\16\2u\13\2\3\2\5\2x\n\2\3\2\3\2\5\2|\n\2\3\2\3\2\7\2\u0080"+
		"\n\2\f\2\16\2\u0083\13\2\3\2\3\2\5\2\u0087\n\2\3\2\3\2\7\2\u008b\n\2\f"+
		"\2\16\2\u008e\13\2\7\2\u0090\n\2\f\2\16\2\u0093\13\2\3\2\7\2\u0096\n\2"+
		"\f\2\16\2\u0099\13\2\3\2\3\2\5\2\u009d\n\2\3\2\3\2\7\2\u00a1\n\2\f\2\16"+
		"\2\u00a4\13\2\7\2\u00a6\n\2\f\2\16\2\u00a9\13\2\3\3\3\3\5\3\u00ad\n\3"+
		"\3\3\3\3\7\3\u00b1\n\3\f\3\16\3\u00b4\13\3\3\3\3\3\5\3\u00b8\n\3\3\3\3"+
		"\3\7\3\u00bc\n\3\f\3\16\3\u00bf\13\3\7\3\u00c1\n\3\f\3\16\3\u00c4\13\3"+
		"\3\3\7\3\u00c7\n\3\f\3\16\3\u00ca\13\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6"+
		"\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u00da\n\6\3\6\3\6\3\6\3\6\5\6\u00e0\n\6\3"+
		"\6\3\6\5\6\u00e4\n\6\3\6\5\6\u00e7\n\6\5\6\u00e9\n\6\3\7\3\7\5\7\u00ed"+
		"\n\7\3\7\3\7\5\7\u00f1\n\7\3\7\3\7\3\b\3\b\3\b\5\b\u00f8\n\b\3\b\3\b\5"+
		"\b\u00fc\n\b\3\b\3\b\5\b\u0100\n\b\3\b\3\b\5\b\u0104\n\b\3\b\3\b\5\b\u0108"+
		"\n\b\3\b\3\b\5\b\u010c\n\b\3\b\3\b\3\b\3\b\5\b\u0112\n\b\3\b\3\b\5\b\u0116"+
		"\n\b\3\b\3\b\5\b\u011a\n\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u0125"+
		"\n\b\3\b\3\b\5\b\u0129\n\b\3\b\3\b\5\b\u012d\n\b\3\b\3\b\3\b\5\b\u0132"+
		"\n\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u013b\n\b\3\b\3\b\5\b\u013f\n\b\3"+
		"\b\3\b\5\b\u0143\n\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u014c\n\b\3\b\3\b"+
		"\5\b\u0150\n\b\3\b\3\b\3\b\5\b\u0155\n\b\3\b\3\b\5\b\u0159\n\b\3\b\3\b"+
		"\3\b\3\b\3\b\5\b\u0160\n\b\3\b\3\b\5\b\u0164\n\b\3\b\3\b\5\b\u0168\n\b"+
		"\3\b\3\b\7\b\u016c\n\b\f\b\16\b\u016f\13\b\3\t\3\t\5\t\u0173\n\t\3\t\3"+
		"\t\5\t\u0177\n\t\3\t\3\t\5\t\u017b\n\t\3\t\3\t\3\n\3\n\5\n\u0181\n\n\3"+
		"\n\3\n\5\n\u0185\n\n\3\n\7\n\u0188\n\n\f\n\16\n\u018b\13\n\5\n\u018d\n"+
		"\n\3\13\3\13\3\13\3\13\3\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\5\r\u019d"+
		"\n\r\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16"+
		"\5\16\u01ac\n\16\3\16\3\16\5\16\u01b0\n\16\3\16\5\16\u01b3\n\16\5\16\u01b5"+
		"\n\16\3\17\3\17\5\17\u01b9\n\17\3\17\3\17\7\17\u01bd\n\17\f\17\16\17\u01c0"+
		"\13\17\3\17\7\17\u01c3\n\17\f\17\16\17\u01c6\13\17\3\20\3\20\5\20\u01ca"+
		"\n\20\3\21\3\21\3\21\3\21\3\21\3\21\5\21\u01d2\n\21\3\22\3\22\5\22\u01d6"+
		"\n\22\3\22\3\22\7\22\u01da\n\22\f\22\16\22\u01dd\13\22\3\22\7\22\u01e0"+
		"\n\22\f\22\16\22\u01e3\13\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\5"+
		"\24\u01ed\n\24\3\24\3\24\3\24\3\24\5\24\u01f3\n\24\3\24\5\24\u01f6\n\24"+
		"\3\24\3\24\5\24\u01fa\n\24\3\24\3\24\3\25\3\25\5\25\u0200\n\25\3\26\3"+
		"\26\5\26\u0204\n\26\3\26\3\26\5\26\u0208\n\26\3\26\3\26\5\26\u020c\n\26"+
		"\3\26\3\26\7\26\u0210\n\26\f\26\16\26\u0213\13\26\3\26\3\26\3\27\3\27"+
		"\5\27\u0219\n\27\3\27\3\27\5\27\u021d\n\27\3\27\3\27\5\27\u0221\n\27\3"+
		"\27\3\27\7\27\u0225\n\27\f\27\16\27\u0228\13\27\3\27\5\27\u022b\n\27\3"+
		"\27\7\27\u022e\n\27\f\27\16\27\u0231\13\27\3\27\3\27\3\27\3\27\5\27\u0237"+
		"\n\27\3\27\5\27\u023a\n\27\3\27\3\27\5\27\u023e\n\27\3\27\3\27\7\27\u0242"+
		"\n\27\f\27\16\27\u0245\13\27\3\27\5\27\u0248\n\27\3\27\7\27\u024b\n\27"+
		"\f\27\16\27\u024e\13\27\7\27\u0250\n\27\f\27\16\27\u0253\13\27\3\27\3"+
		"\27\5\27\u0257\n\27\3\27\3\27\7\27\u025b\n\27\f\27\16\27\u025e\13\27\3"+
		"\27\5\27\u0261\n\27\3\27\7\27\u0264\n\27\f\27\16\27\u0267\13\27\5\27\u0269"+
		"\n\27\3\27\7\27\u026c\n\27\f\27\16\27\u026f\13\27\3\27\3\27\3\30\3\30"+
		"\5\30\u0275\n\30\3\30\3\30\7\30\u0279\n\30\f\30\16\30\u027c\13\30\3\30"+
		"\3\30\5\30\u0280\n\30\3\30\3\30\5\30\u0284\n\30\3\30\7\30\u0287\n\30\f"+
		"\30\16\30\u028a\13\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\5\31\u0293\n"+
		"\31\3\31\3\31\5\31\u0297\n\31\3\31\3\31\5\31\u029b\n\31\3\32\3\32\3\32"+
		"\7\32\u02a0\n\32\f\32\16\32\u02a3\13\32\3\32\7\32\u02a6\n\32\f\32\16\32"+
		"\u02a9\13\32\3\33\3\33\3\34\3\34\3\34\3\34\5\34\u02b1\n\34\5\34\u02b3"+
		"\n\34\3\35\3\35\3\36\5\36\u02b8\n\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\36\3\36\3\37\3\37\5\37\u02c8\n\37\3\37\3\37\7\37\u02cc"+
		"\n\37\f\37\16\37\u02cf\13\37\3\37\3\37\5\37\u02d3\n\37\3\37\3\37\5\37"+
		"\u02d7\n\37\3\37\7\37\u02da\n\37\f\37\16\37\u02dd\13\37\3\37\3\37\3 \3"+
		" \5 \u02e3\n \3!\3!\3!\5!\u02e8\n!\3!\3!\5!\u02ec\n!\3!\7!\u02ef\n!\f"+
		"!\16!\u02f2\13!\5!\u02f4\n!\3\"\3\"\3\"\3\"\3#\5#\u02fb\n#\3#\5#\u02fe"+
		"\n#\3#\3#\7#\u0302\n#\f#\16#\u0305\13#\3#\5#\u0308\n#\3#\5#\u030b\n#\5"+
		"#\u030d\n#\3$\3$\3$\3$\3$\3$\3$\3$\3%\3%\5%\u0319\n%\3%\3%\3%\3%\3%\3"+
		"%\3%\3%\3%\3%\3%\3%\3%\2\3\16&\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36"+
		" \"$&(*,.\60\62\64\668:<>@BDFH\2\b\3\29:\3\399\3\2\n\13\3\2\r\16\3\2\r"+
		"\20\3\2\21\26\2\u0399\2M\3\2\2\2\4\u00aa\3\2\2\2\6\u00cd\3\2\2\2\b\u00cf"+
		"\3\2\2\2\n\u00e8\3\2\2\2\f\u00ea\3\2\2\2\16\u0142\3\2\2\2\20\u0170\3\2"+
		"\2\2\22\u018c\3\2\2\2\24\u018e\3\2\2\2\26\u0193\3\2\2\2\30\u019c\3\2\2"+
		"\2\32\u019e\3\2\2\2\34\u01b6\3\2\2\2\36\u01c9\3\2\2\2 \u01d1\3\2\2\2\""+
		"\u01d3\3\2\2\2$\u01e4\3\2\2\2&\u01e8\3\2\2\2(\u01ff\3\2\2\2*\u0201\3\2"+
		"\2\2,\u0216\3\2\2\2.\u0272\3\2\2\2\60\u028d\3\2\2\2\62\u029c\3\2\2\2\64"+
		"\u02aa\3\2\2\2\66\u02ac\3\2\2\28\u02b4\3\2\2\2:\u02b7\3\2\2\2<\u02c5\3"+
		"\2\2\2>\u02e2\3\2\2\2@\u02f3\3\2\2\2B\u02f5\3\2\2\2D\u030c\3\2\2\2F\u030e"+
		"\3\2\2\2H\u0318\3\2\2\2JL\t\2\2\2KJ\3\2\2\2LO\3\2\2\2MK\3\2\2\2MN\3\2"+
		"\2\2N]\3\2\2\2OM\3\2\2\2PR\5F$\2QS\7:\2\2RQ\3\2\2\2RS\3\2\2\2ST\3\2\2"+
		"\2TX\79\2\2UW\t\2\2\2VU\3\2\2\2WZ\3\2\2\2XV\3\2\2\2XY\3\2\2\2Y\\\3\2\2"+
		"\2ZX\3\2\2\2[P\3\2\2\2\\_\3\2\2\2][\3\2\2\2]^\3\2\2\2^c\3\2\2\2_]\3\2"+
		"\2\2`b\t\2\2\2a`\3\2\2\2be\3\2\2\2ca\3\2\2\2cd\3\2\2\2ds\3\2\2\2ec\3\2"+
		"\2\2fh\5H%\2gi\7:\2\2hg\3\2\2\2hi\3\2\2\2ij\3\2\2\2jn\79\2\2km\t\2\2\2"+
		"lk\3\2\2\2mp\3\2\2\2nl\3\2\2\2no\3\2\2\2or\3\2\2\2pn\3\2\2\2qf\3\2\2\2"+
		"ru\3\2\2\2sq\3\2\2\2st\3\2\2\2tw\3\2\2\2us\3\2\2\2vx\7:\2\2wv\3\2\2\2"+
		"wx\3\2\2\2xy\3\2\2\2y{\5\4\3\2z|\7:\2\2{z\3\2\2\2{|\3\2\2\2|}\3\2\2\2"+
		"}\u0081\79\2\2~\u0080\t\2\2\2\177~\3\2\2\2\u0080\u0083\3\2\2\2\u0081\177"+
		"\3\2\2\2\u0081\u0082\3\2\2\2\u0082\u0091\3\2\2\2\u0083\u0081\3\2\2\2\u0084"+
		"\u0086\5H%\2\u0085\u0087\7:\2\2\u0086\u0085\3\2\2\2\u0086\u0087\3\2\2"+
		"\2\u0087\u0088\3\2\2\2\u0088\u008c\t\3\2\2\u0089\u008b\t\2\2\2\u008a\u0089"+
		"\3\2\2\2\u008b\u008e\3\2\2\2\u008c\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d"+
		"\u0090\3\2\2\2\u008e\u008c\3\2\2\2\u008f\u0084\3\2\2\2\u0090\u0093\3\2"+
		"\2\2\u0091\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0097\3\2\2\2\u0093"+
		"\u0091\3\2\2\2\u0094\u0096\t\2\2\2\u0095\u0094\3\2\2\2\u0096\u0099\3\2"+
		"\2\2\u0097\u0095\3\2\2\2\u0097\u0098\3\2\2\2\u0098\u00a7\3\2\2\2\u0099"+
		"\u0097\3\2\2\2\u009a\u009c\5<\37\2\u009b\u009d\7:\2\2\u009c\u009b\3\2"+
		"\2\2\u009c\u009d\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u00a2\79\2\2\u009f"+
		"\u00a1\t\2\2\2\u00a0\u009f\3\2\2\2\u00a1\u00a4\3\2\2\2\u00a2\u00a0\3\2"+
		"\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a6\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a5"+
		"\u009a\3\2\2\2\u00a6\u00a9\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a7\u00a8\3\2"+
		"\2\2\u00a8\3\3\2\2\2\u00a9\u00a7\3\2\2\2\u00aa\u00ac\7\3\2\2\u00ab\u00ad"+
		"\7:\2\2\u00ac\u00ab\3\2\2\2\u00ac\u00ad\3\2\2\2\u00ad\u00ae\3\2\2\2\u00ae"+
		"\u00b2\79\2\2\u00af\u00b1\t\2\2\2\u00b0\u00af\3\2\2\2\u00b1\u00b4\3\2"+
		"\2\2\u00b2\u00b0\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00c2\3\2\2\2\u00b4"+
		"\u00b2\3\2\2\2\u00b5\u00b7\5\n\6\2\u00b6\u00b8\7:\2\2\u00b7\u00b6\3\2"+
		"\2\2\u00b7\u00b8\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00bd\79\2\2\u00ba"+
		"\u00bc\t\2\2\2\u00bb\u00ba\3\2\2\2\u00bc\u00bf\3\2\2\2\u00bd\u00bb\3\2"+
		"\2\2\u00bd\u00be\3\2\2\2\u00be\u00c1\3\2\2\2\u00bf\u00bd\3\2\2\2\u00c0"+
		"\u00b5\3\2\2\2\u00c1\u00c4\3\2\2\2\u00c2\u00c0\3\2\2\2\u00c2\u00c3\3\2"+
		"\2\2\u00c3\u00c8\3\2\2\2\u00c4\u00c2\3\2\2\2\u00c5\u00c7\t\2\2\2\u00c6"+
		"\u00c5\3\2\2\2\u00c7\u00ca\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c8\u00c9\3\2"+
		"\2\2\u00c9\u00cb\3\2\2\2\u00ca\u00c8\3\2\2\2\u00cb\u00cc\7\4\2\2\u00cc"+
		"\5\3\2\2\2\u00cd\u00ce\7\65\2\2\u00ce\7\3\2\2\2\u00cf\u00d0\7\65\2\2\u00d0"+
		"\t\3\2\2\2\u00d1\u00d2\58\35\2\u00d2\u00d3\7:\2\2\u00d3\u00d4\7\5\2\2"+
		"\u00d4\u00d5\7:\2\2\u00d5\u00d6\5\6\4\2\u00d6\u00e9\3\2\2\2\u00d7\u00d8"+
		"\7\6\2\2\u00d8\u00da\7:\2\2\u00d9\u00d7\3\2\2\2\u00d9\u00da\3\2\2\2\u00da"+
		"\u00db\3\2\2\2\u00db\u00dc\58\35\2\u00dc\u00dd\7:\2\2\u00dd\u00e6\5\6"+
		"\4\2\u00de\u00e0\7:\2\2\u00df\u00de\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0"+
		"\u00e1\3\2\2\2\u00e1\u00e3\7\7\2\2\u00e2\u00e4\7:\2\2\u00e3\u00e2\3\2"+
		"\2\2\u00e3\u00e4\3\2\2\2\u00e4\u00e5\3\2\2\2\u00e5\u00e7\5\16\b\2\u00e6"+
		"\u00df\3\2\2\2\u00e6\u00e7\3\2\2\2\u00e7\u00e9\3\2\2\2\u00e8\u00d1\3\2"+
		"\2\2\u00e8\u00d9\3\2\2\2\u00e9\13\3\2\2\2\u00ea\u00ec\7\b\2\2\u00eb\u00ed"+
		"\7:\2\2\u00ec\u00eb\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00ee\3\2\2\2\u00ee"+
		"\u00f0\5\16\b\2\u00ef\u00f1\7:\2\2\u00f0\u00ef\3\2\2\2\u00f0\u00f1\3\2"+
		"\2\2\u00f1\u00f2\3\2\2\2\u00f2\u00f3\7\t\2\2\u00f3\r\3\2\2\2\u00f4\u00f5"+
		"\b\b\1\2\u00f5\u00f7\7\b\2\2\u00f6\u00f8\7:\2\2\u00f7\u00f6\3\2\2\2\u00f7"+
		"\u00f8\3\2\2\2\u00f8\u00f9\3\2\2\2\u00f9\u00fb\5\16\b\2\u00fa\u00fc\7"+
		":\2\2\u00fb\u00fa\3\2\2\2\u00fb\u00fc\3\2\2\2\u00fc\u00fd\3\2\2\2\u00fd"+
		"\u00ff\7\t\2\2\u00fe\u0100\7:\2\2\u00ff\u00fe\3\2\2\2\u00ff\u0100\3\2"+
		"\2\2\u0100\u0101\3\2\2\2\u0101\u0103\t\4\2\2\u0102\u0104\7:\2\2\u0103"+
		"\u0102\3\2\2\2\u0103\u0104\3\2\2\2\u0104\u0105\3\2\2\2\u0105\u0107\7\b"+
		"\2\2\u0106\u0108\7:\2\2\u0107\u0106\3\2\2\2\u0107\u0108\3\2\2\2\u0108"+
		"\u0109\3\2\2\2\u0109\u010b\5\16\b\2\u010a\u010c\7:\2\2\u010b\u010a\3\2"+
		"\2\2\u010b\u010c\3\2\2\2\u010c\u010d\3\2\2\2\u010d\u010e\7\t\2\2\u010e"+
		"\u0143\3\2\2\2\u010f\u0111\7\b\2\2\u0110\u0112\7:\2\2\u0111\u0110\3\2"+
		"\2\2\u0111\u0112\3\2\2\2\u0112\u0113\3\2\2\2\u0113\u0115\5\16\b\2\u0114"+
		"\u0116\7:\2\2\u0115\u0114\3\2\2\2\u0115\u0116\3\2\2\2\u0116\u0117\3\2"+
		"\2\2\u0117\u0119\7\t\2\2\u0118\u011a\7:\2\2\u0119\u0118\3\2\2\2\u0119"+
		"\u011a\3\2\2\2\u011a\u011b\3\2\2\2\u011b\u011c\t\4\2\2\u011c\u011d\7:"+
		"\2\2\u011d\u011e\5\16\b\r\u011e\u0143\3\2\2\2\u011f\u012c\7\f\2\2\u0120"+
		"\u0121\7:\2\2\u0121\u012d\5\16\b\2\u0122\u0124\7\b\2\2\u0123\u0125\7:"+
		"\2\2\u0124\u0123\3\2\2\2\u0124\u0125\3\2\2\2\u0125\u0126\3\2\2\2\u0126"+
		"\u0128\5\16\b\2\u0127\u0129\7:\2\2\u0128\u0127\3\2\2\2\u0128\u0129\3\2"+
		"\2\2\u0129\u012a\3\2\2\2\u012a\u012b\7\t\2\2\u012b\u012d\3\2\2\2\u012c"+
		"\u0120\3\2\2\2\u012c\u0122\3\2\2\2\u012d\u0143\3\2\2\2\u012e\u0143\5\30"+
		"\r\2\u012f\u0131\t\5\2\2\u0130\u0132\7:\2\2\u0131\u0130\3\2\2\2\u0131"+
		"\u0132\3\2\2\2\u0132\u0133\3\2\2\2\u0133\u0143\5\16\b\t\u0134\u0143\5"+
		"\20\t\2\u0135\u0143\5\6\4\2\u0136\u0143\5\24\13\2\u0137\u0143\5\26\f\2"+
		"\u0138\u013a\7\b\2\2\u0139\u013b\7:\2\2\u013a\u0139\3\2\2\2\u013a\u013b"+
		"\3\2\2\2\u013b\u013c\3\2\2\2\u013c\u013e\5\16\b\2\u013d\u013f\7:\2\2\u013e"+
		"\u013d\3\2\2\2\u013e\u013f\3\2\2\2\u013f\u0140\3\2\2\2\u0140\u0141\7\t"+
		"\2\2\u0141\u0143\3\2\2\2\u0142\u00f4\3\2\2\2\u0142\u010f\3\2\2\2\u0142"+
		"\u011f\3\2\2\2\u0142\u012e\3\2\2\2\u0142\u012f\3\2\2\2\u0142\u0134\3\2"+
		"\2\2\u0142\u0135\3\2\2\2\u0142\u0136\3\2\2\2\u0142\u0137\3\2\2\2\u0142"+
		"\u0138\3\2\2\2\u0143\u016d\3\2\2\2\u0144\u0145\f\20\2\2\u0145\u0146\7"+
		":\2\2\u0146\u0147\t\4\2\2\u0147\u0148\7:\2\2\u0148\u016c\5\16\b\21\u0149"+
		"\u014b\f\n\2\2\u014a\u014c\7:\2\2\u014b\u014a\3\2\2\2\u014b\u014c\3\2"+
		"\2\2\u014c\u014d\3\2\2\2\u014d\u014f\t\6\2\2\u014e\u0150\7:\2\2\u014f"+
		"\u014e\3\2\2\2\u014f\u0150\3\2\2\2\u0150\u0151\3\2\2\2\u0151\u016c\5\16"+
		"\b\13\u0152\u0154\f\b\2\2\u0153\u0155\7:\2\2\u0154\u0153\3\2\2\2\u0154"+
		"\u0155\3\2\2\2\u0155\u0156\3\2\2\2\u0156\u0158\t\7\2\2\u0157\u0159\7:"+
		"\2\2\u0158\u0157\3\2\2\2\u0158\u0159\3\2\2\2\u0159\u015a\3\2\2\2\u015a"+
		"\u016c\5\16\b\t\u015b\u015c\f\16\2\2\u015c\u015d\7:\2\2\u015d\u015f\t"+
		"\4\2\2\u015e\u0160\7:\2\2\u015f\u015e\3\2\2\2\u015f\u0160\3\2\2\2\u0160"+
		"\u0161\3\2\2\2\u0161\u0163\7\b\2\2\u0162\u0164\7:\2\2\u0163\u0162\3\2"+
		"\2\2\u0163\u0164\3\2\2\2\u0164\u0165\3\2\2\2\u0165\u0167\5\16\b\2\u0166"+
		"\u0168\7:\2\2\u0167\u0166\3\2\2\2\u0167\u0168\3\2\2\2\u0168\u0169\3\2"+
		"\2\2\u0169\u016a\7\t\2\2\u016a\u016c\3\2\2\2\u016b\u0144\3\2\2\2\u016b"+
		"\u0149\3\2\2\2\u016b\u0152\3\2\2\2\u016b\u015b\3\2\2\2\u016c\u016f\3\2"+
		"\2\2\u016d\u016b\3\2\2\2\u016d\u016e\3\2\2\2\u016e\17\3\2\2\2\u016f\u016d"+
		"\3\2\2\2\u0170\u0172\5\b\5\2\u0171\u0173\7:\2\2\u0172\u0171\3\2\2\2\u0172"+
		"\u0173\3\2\2\2\u0173\u0174\3\2\2\2\u0174\u0176\7\b\2\2\u0175\u0177\7:"+
		"\2\2\u0176\u0175\3\2\2\2\u0176\u0177\3\2\2\2\u0177\u0178\3\2\2\2\u0178"+
		"\u017a\5\22\n\2\u0179\u017b\7:\2\2\u017a\u0179\3\2\2\2\u017a\u017b\3\2"+
		"\2\2\u017b\u017c\3\2\2\2\u017c\u017d\7\t\2\2\u017d\21\3\2\2\2\u017e\u0189"+
		"\5\16\b\2\u017f\u0181\7:\2\2\u0180\u017f\3\2\2\2\u0180\u0181\3\2\2\2\u0181"+
		"\u0182\3\2\2\2\u0182\u0184\78\2\2\u0183\u0185\7:\2\2\u0184\u0183\3\2\2"+
		"\2\u0184\u0185\3\2\2\2\u0185\u0186\3\2\2\2\u0186\u0188\5\16\b\2\u0187"+
		"\u0180\3\2\2\2\u0188\u018b\3\2\2\2\u0189\u0187\3\2\2\2\u0189\u018a\3\2"+
		"\2\2\u018a\u018d\3\2\2\2\u018b\u0189\3\2\2\2\u018c\u017e\3\2\2\2\u018c"+
		"\u018d\3\2\2\2\u018d\23\3\2\2\2\u018e\u018f\5\6\4\2\u018f\u0190\7\27\2"+
		"\2\u0190\u0191\5\16\b\2\u0191\u0192\7\30\2\2\u0192\25\3\2\2\2\u0193\u0194"+
		"\7\31\2\2\u0194\u0195\7:\2\2\u0195\u0196\5\b\5\2\u0196\27\3\2\2\2\u0197"+
		"\u019d\7-\2\2\u0198\u019d\7.\2\2\u0199\u019d\7\63\2\2\u019a\u019d\7\64"+
		"\2\2\u019b\u019d\5\26\f\2\u019c\u0197\3\2\2\2\u019c\u0198\3\2\2\2\u019c"+
		"\u0199\3\2\2\2\u019c\u019a\3\2\2\2\u019c\u019b\3\2\2\2\u019d\31\3\2\2"+
		"\2\u019e\u01b4\7\32\2\2\u019f\u01a0\7:\2\2\u01a0\u01a1\58\35\2\u01a1\u01a2"+
		"\7:\2\2\u01a2\u01a3\7\5\2\2\u01a3\u01a4\7:\2\2\u01a4\u01a5\5\6\4\2\u01a5"+
		"\u01b5\3\2\2\2\u01a6\u01a7\7:\2\2\u01a7\u01a8\58\35\2\u01a8\u01a9\7:\2"+
		"\2\u01a9\u01b2\5\6\4\2\u01aa\u01ac\7:\2\2\u01ab\u01aa\3\2\2\2\u01ab\u01ac"+
		"\3\2\2\2\u01ac\u01ad\3\2\2\2\u01ad\u01af\7\7\2\2\u01ae\u01b0\7:\2\2\u01af"+
		"\u01ae\3\2\2\2\u01af\u01b0\3\2\2\2\u01b0\u01b1\3\2\2\2\u01b1\u01b3\5\16"+
		"\b\2\u01b2\u01ab\3\2\2\2\u01b2\u01b3\3\2\2\2\u01b3\u01b5\3\2\2\2\u01b4"+
		"\u019f\3\2\2\2\u01b4\u01a6\3\2\2\2\u01b5\33\3\2\2\2\u01b6\u01c4\5\32\16"+
		"\2\u01b7\u01b9\7:\2\2\u01b8\u01b7\3\2\2\2\u01b8\u01b9\3\2\2\2\u01b9\u01ba"+
		"\3\2\2\2\u01ba\u01be\79\2\2\u01bb\u01bd\t\2\2\2\u01bc\u01bb\3\2\2\2\u01bd"+
		"\u01c0\3\2\2\2\u01be\u01bc\3\2\2\2\u01be\u01bf\3\2\2\2\u01bf\u01c1\3\2"+
		"\2\2\u01c0\u01be\3\2\2\2\u01c1\u01c3\5\32\16\2\u01c2\u01b8\3\2\2\2\u01c3"+
		"\u01c6\3\2\2\2\u01c4\u01c2\3\2\2\2\u01c4\u01c5\3\2\2\2\u01c5\35\3\2\2"+
		"\2\u01c6\u01c4\3\2\2\2\u01c7\u01ca\5$\23\2\u01c8\u01ca\5,\27\2\u01c9\u01c7"+
		"\3\2\2\2\u01c9\u01c8\3\2\2\2\u01ca\37\3\2\2\2\u01cb\u01d2\5$\23\2\u01cc"+
		"\u01d2\5&\24\2\u01cd\u01d2\5,\27\2\u01ce\u01d2\5.\30\2\u01cf\u01d2\5\60"+
		"\31\2\u01d0\u01d2\5\66\34\2\u01d1\u01cb\3\2\2\2\u01d1\u01cc\3\2\2\2\u01d1"+
		"\u01cd\3\2\2\2\u01d1\u01ce\3\2\2\2\u01d1\u01cf\3\2\2\2\u01d1\u01d0\3\2"+
		"\2\2\u01d2!\3\2\2\2\u01d3\u01e1\5 \21\2\u01d4\u01d6\7:\2\2\u01d5\u01d4"+
		"\3\2\2\2\u01d5\u01d6\3\2\2\2\u01d6\u01d7\3\2\2\2\u01d7\u01db\79\2\2\u01d8"+
		"\u01da\t\2\2\2\u01d9\u01d8\3\2\2\2\u01da\u01dd\3\2\2\2\u01db\u01d9\3\2"+
		"\2\2\u01db\u01dc\3\2\2\2\u01dc\u01de\3\2\2\2\u01dd\u01db\3\2\2\2\u01de"+
		"\u01e0\5 \21\2\u01df\u01d5\3\2\2\2\u01e0\u01e3\3\2\2\2\u01e1\u01df\3\2"+
		"\2\2\u01e1\u01e2\3\2\2\2\u01e2#\3\2\2\2\u01e3\u01e1\3\2\2\2\u01e4\u01e5"+
		"\7\33\2\2\u01e5\u01e6\7:\2\2\u01e6\u01e7\5\20\t\2\u01e7%\3\2\2\2\u01e8"+
		"\u01e9\7\34\2\2\u01e9\u01ea\7:\2\2\u01ea\u01f2\5\6\4\2\u01eb\u01ed\7:"+
		"\2\2\u01ec\u01eb\3\2\2\2\u01ec\u01ed\3\2\2\2\u01ed\u01ee\3\2\2\2\u01ee"+
		"\u01ef\7\27\2\2\u01ef\u01f0\5\16\b\2\u01f0\u01f1\7\30\2\2\u01f1\u01f3"+
		"\3\2\2\2\u01f2\u01ec\3\2\2\2\u01f2\u01f3\3\2\2\2\u01f3\u01f5\3\2\2\2\u01f4"+
		"\u01f6\7:\2\2\u01f5\u01f4\3\2\2\2\u01f5\u01f6\3\2\2\2\u01f6\u01f7\3\2"+
		"\2\2\u01f7\u01f9\7\7\2\2\u01f8\u01fa\7:\2\2\u01f9\u01f8\3\2\2\2\u01f9"+
		"\u01fa\3\2\2\2\u01fa\u01fb\3\2\2\2\u01fb\u01fc\5\16\b\2\u01fc\'\3\2\2"+
		"\2\u01fd\u0200\5\f\7\2\u01fe\u0200\5\16\b\2\u01ff\u01fd\3\2\2\2\u01ff"+
		"\u01fe\3\2\2\2\u0200)\3\2\2\2\u0201\u0203\7\35\2\2\u0202\u0204\7:\2\2"+
		"\u0203\u0202\3\2\2\2\u0203\u0204\3\2\2\2\u0204\u0205\3\2\2\2\u0205\u0207"+
		"\5(\25\2\u0206\u0208\7:\2\2\u0207\u0206\3\2\2\2\u0207\u0208\3\2\2\2\u0208"+
		"\u0209\3\2\2\2\u0209\u020b\7\36\2\2\u020a\u020c\7:\2\2\u020b\u020a\3\2"+
		"\2\2\u020b\u020c\3\2\2\2\u020c\u020d\3\2\2\2\u020d\u0211\79\2\2\u020e"+
		"\u0210\t\2\2\2\u020f\u020e\3\2\2\2\u0210\u0213\3\2\2\2\u0211\u020f\3\2"+
		"\2\2\u0211\u0212\3\2\2\2\u0212\u0214\3\2\2\2\u0213\u0211\3\2\2\2\u0214"+
		"\u0215\7\37\2\2\u0215+\3\2\2\2\u0216\u0218\7\35\2\2\u0217\u0219\7:\2\2"+
		"\u0218\u0217\3\2\2\2\u0218\u0219\3\2\2\2\u0219\u021a\3\2\2\2\u021a\u021c"+
		"\5(\25\2\u021b\u021d\7:\2\2\u021c\u021b\3\2\2\2\u021c\u021d\3\2\2\2\u021d"+
		"\u021e\3\2\2\2\u021e\u0220\7\36\2\2\u021f\u0221\7:\2\2\u0220\u021f\3\2"+
		"\2\2\u0220\u0221\3\2\2\2\u0221\u0222\3\2\2\2\u0222\u0226\79\2\2\u0223"+
		"\u0225\t\2\2\2\u0224\u0223\3\2\2\2\u0225\u0228\3\2\2\2\u0226\u0224\3\2"+
		"\2\2\u0226\u0227\3\2\2\2\u0227\u022a\3\2\2\2\u0228\u0226\3\2\2\2\u0229"+
		"\u022b\5\"\22\2\u022a\u0229\3\2\2\2\u022a\u022b\3\2\2\2\u022b\u022f\3"+
		"\2\2\2\u022c\u022e\t\2\2\2\u022d\u022c\3\2\2\2\u022e\u0231\3\2\2\2\u022f"+
		"\u022d\3\2\2\2\u022f\u0230\3\2\2\2\u0230\u0251\3\2\2\2\u0231\u022f\3\2"+
		"\2\2\u0232\u0236\7 \2\2\u0233\u0237\5\f\7\2\u0234\u0235\7:\2\2\u0235\u0237"+
		"\5\16\b\2\u0236\u0233\3\2\2\2\u0236\u0234\3\2\2\2\u0237\u0239\3\2\2\2"+
		"\u0238\u023a\7:\2\2\u0239\u0238\3\2\2\2\u0239\u023a\3\2\2\2\u023a\u023b"+
		"\3\2\2\2\u023b\u023d\7\36\2\2\u023c\u023e\7:\2\2\u023d\u023c\3\2\2\2\u023d"+
		"\u023e\3\2\2\2\u023e\u023f\3\2\2\2\u023f\u0243\79\2\2\u0240\u0242\t\2"+
		"\2\2\u0241\u0240\3\2\2\2\u0242\u0245\3\2\2\2\u0243\u0241\3\2\2\2\u0243"+
		"\u0244\3\2\2\2\u0244\u0247\3\2\2\2\u0245\u0243\3\2\2\2\u0246\u0248\5\""+
		"\22\2\u0247\u0246\3\2\2\2\u0247\u0248\3\2\2\2\u0248\u024c\3\2\2\2\u0249"+
		"\u024b\t\2\2\2\u024a\u0249\3\2\2\2\u024b\u024e\3\2\2\2\u024c\u024a\3\2"+
		"\2\2\u024c\u024d\3\2\2\2\u024d\u0250\3\2\2\2\u024e\u024c\3\2\2\2\u024f"+
		"\u0232\3\2\2\2\u0250\u0253\3\2\2\2\u0251\u024f\3\2\2\2\u0251\u0252\3\2"+
		"\2\2\u0252\u0268\3\2\2\2\u0253\u0251\3\2\2\2\u0254\u0256\7!\2\2\u0255"+
		"\u0257\7:\2\2\u0256\u0255\3\2\2\2\u0256\u0257\3\2\2\2\u0257\u0258\3\2"+
		"\2\2\u0258\u025c\79\2\2\u0259\u025b\t\2\2\2\u025a\u0259\3\2\2\2\u025b"+
		"\u025e\3\2\2\2\u025c\u025a\3\2\2\2\u025c\u025d\3\2\2\2\u025d\u0260\3\2"+
		"\2\2\u025e\u025c\3\2\2\2\u025f\u0261\5\"\22\2\u0260\u025f\3\2\2\2\u0260"+
		"\u0261\3\2\2\2\u0261\u0265\3\2\2\2\u0262\u0264\t\2\2\2\u0263\u0262\3\2"+
		"\2\2\u0264\u0267\3\2\2\2\u0265\u0263\3\2\2\2\u0265\u0266\3\2\2\2\u0266"+
		"\u0269\3\2\2\2\u0267\u0265\3\2\2\2\u0268\u0254\3\2\2\2\u0268\u0269\3\2"+
		"\2\2\u0269\u026d\3\2\2\2\u026a\u026c\t\2\2\2\u026b\u026a\3\2\2\2\u026c"+
		"\u026f\3\2\2\2\u026d\u026b\3\2\2\2\u026d\u026e\3\2\2\2\u026e\u0270\3\2"+
		"\2\2\u026f\u026d\3\2\2\2\u0270\u0271\7\37\2\2\u0271-\3\2\2\2\u0272\u0274"+
		"\7\"\2\2\u0273\u0275\7:\2\2\u0274\u0273\3\2\2\2\u0274\u0275\3\2\2\2\u0275"+
		"\u0276\3\2\2\2\u0276\u027a\79\2\2\u0277\u0279\t\2\2\2\u0278\u0277\3\2"+
		"\2\2\u0279\u027c\3\2\2\2\u027a\u0278\3\2\2\2\u027a\u027b\3\2\2\2\u027b"+
		"\u0283\3\2\2\2\u027c\u027a\3\2\2\2\u027d\u027f\5\62\32\2\u027e\u0280\7"+
		":\2\2\u027f\u027e\3\2\2\2\u027f\u0280\3\2\2\2\u0280\u0281\3\2\2\2\u0281"+
		"\u0282\79\2\2\u0282\u0284\3\2\2\2\u0283\u027d\3\2\2\2\u0283\u0284\3\2"+
		"\2\2\u0284\u0288\3\2\2\2\u0285\u0287\t\2\2\2\u0286\u0285\3\2\2\2\u0287"+
		"\u028a\3\2\2\2\u0288\u0286\3\2\2\2\u0288\u0289\3\2\2\2\u0289\u028b\3\2"+
		"\2\2\u028a\u0288\3\2\2\2\u028b\u028c\7#\2\2\u028c/\3\2\2\2\u028d\u029a"+
		"\7$\2\2\u028e\u028f\7:\2\2\u028f\u029b\5\16\b\2\u0290\u0292\7\b\2\2\u0291"+
		"\u0293\7:\2\2\u0292\u0291\3\2\2\2\u0292\u0293\3\2\2\2\u0293\u0294\3\2"+
		"\2\2\u0294\u0296\5\16\b\2\u0295\u0297\7:\2\2\u0296\u0295\3\2\2\2\u0296"+
		"\u0297\3\2\2\2\u0297\u0298\3\2\2\2\u0298\u0299\7\t\2\2\u0299\u029b\3\2"+
		"\2\2\u029a\u028e\3\2\2\2\u029a\u0290\3\2\2\2\u029b\61\3\2\2\2\u029c\u02a7"+
		"\5\64\33\2\u029d\u02a1\79\2\2\u029e\u02a0\t\2\2\2\u029f\u029e\3\2\2\2"+
		"\u02a0\u02a3\3\2\2\2\u02a1\u029f\3\2\2\2\u02a1\u02a2\3\2\2\2\u02a2\u02a4"+
		"\3\2\2\2\u02a3\u02a1\3\2\2\2\u02a4\u02a6\5\64\33\2\u02a5\u029d\3\2\2\2"+
		"\u02a6\u02a9\3\2\2\2\u02a7\u02a5\3\2\2\2\u02a7\u02a8\3\2\2\2\u02a8\63"+
		"\3\2\2\2\u02a9\u02a7\3\2\2\2\u02aa\u02ab\5\"\22\2\u02ab\65\3\2\2\2\u02ac"+
		"\u02b2\7%\2\2\u02ad\u02b3\5\f\7\2\u02ae\u02af\7:\2\2\u02af\u02b1\5\16"+
		"\b\2\u02b0\u02ae\3\2\2\2\u02b0\u02b1\3\2\2\2\u02b1\u02b3\3\2\2\2\u02b2"+
		"\u02ad\3\2\2\2\u02b2\u02b0\3\2\2\2\u02b3\67\3\2\2\2\u02b4\u02b5\7\65\2"+
		"\2\u02b59\3\2\2\2\u02b6\u02b8\7\6\2\2\u02b7\u02b6\3\2\2\2\u02b7\u02b8"+
		"\3\2\2\2\u02b8\u02b9\3\2\2\2\u02b9\u02ba\7\31\2\2\u02ba\u02bb\7:\2\2\u02bb"+
		"\u02bc\7\65\2\2\u02bc\u02bd\7:\2\2\u02bd\u02be\7&\2\2\u02be\u02bf\7:\2"+
		"\2\u02bf\u02c0\5@!\2\u02c0\u02c1\7:\2\2\u02c1\u02c2\7\'\2\2\u02c2\u02c3"+
		"\7:\2\2\u02c3\u02c4\5> \2\u02c4;\3\2\2\2\u02c5\u02c7\5:\36\2\u02c6\u02c8"+
		"\7:\2\2\u02c7\u02c6\3\2\2\2\u02c7\u02c8\3\2\2\2\u02c8\u02c9\3\2\2\2\u02c9"+
		"\u02cd\79\2\2\u02ca\u02cc\t\2\2\2\u02cb\u02ca\3\2\2\2\u02cc\u02cf\3\2"+
		"\2\2\u02cd\u02cb\3\2\2\2\u02cd\u02ce\3\2\2\2\u02ce\u02d6\3\2\2\2\u02cf"+
		"\u02cd\3\2\2\2\u02d0\u02d2\5D#\2\u02d1\u02d3\7:\2\2\u02d2\u02d1\3\2\2"+
		"\2\u02d2\u02d3\3\2\2\2\u02d3\u02d4\3\2\2\2\u02d4\u02d5\79\2\2\u02d5\u02d7"+
		"\3\2\2\2\u02d6\u02d0\3\2\2\2\u02d6\u02d7\3\2\2\2\u02d7\u02db\3\2\2\2\u02d8"+
		"\u02da\t\2\2\2\u02d9\u02d8\3\2\2\2\u02da\u02dd\3\2\2\2\u02db\u02d9\3\2"+
		"\2\2\u02db\u02dc\3\2\2\2\u02dc\u02de\3\2\2\2\u02dd\u02db\3\2\2\2\u02de"+
		"\u02df\7(\2\2\u02df=\3\2\2\2\u02e0\u02e3\7)\2\2\u02e1\u02e3\58\35\2\u02e2"+
		"\u02e0\3\2\2\2\u02e2\u02e1\3\2\2\2\u02e3?\3\2\2\2\u02e4\u02f4\7)\2\2\u02e5"+
		"\u02f0\5B\"\2\u02e6\u02e8\7:\2\2\u02e7\u02e6\3\2\2\2\u02e7\u02e8\3\2\2"+
		"\2\u02e8\u02e9\3\2\2\2\u02e9\u02eb\78\2\2\u02ea\u02ec\7:\2\2\u02eb\u02ea"+
		"\3\2\2\2\u02eb\u02ec\3\2\2\2\u02ec\u02ed\3\2\2\2\u02ed\u02ef\5B\"\2\u02ee"+
		"\u02e7\3\2\2\2\u02ef\u02f2\3\2\2\2\u02f0\u02ee\3\2\2\2\u02f0\u02f1\3\2"+
		"\2\2\u02f1\u02f4\3\2\2\2\u02f2\u02f0\3\2\2\2\u02f3\u02e4\3\2\2\2\u02f3"+
		"\u02e5\3\2\2\2\u02f4A\3\2\2\2\u02f5\u02f6\58\35\2\u02f6\u02f7\7:\2\2\u02f7"+
		"\u02f8\7\65\2\2\u02f8C\3\2\2\2\u02f9\u02fb\5\34\17\2\u02fa\u02f9\3\2\2"+
		"\2\u02fa\u02fb\3\2\2\2\u02fb\u0307\3\2\2\2\u02fc\u02fe\7:\2\2\u02fd\u02fc"+
		"\3\2\2\2\u02fd\u02fe\3\2\2\2\u02fe\u02ff\3\2\2\2\u02ff\u0303\79\2\2\u0300"+
		"\u0302\t\2\2\2\u0301\u0300\3\2\2\2\u0302\u0305\3\2\2\2\u0303\u0301\3\2"+
		"\2\2\u0303\u0304\3\2\2\2\u0304\u0306\3\2\2\2\u0305\u0303\3\2\2\2\u0306"+
		"\u0308\5\"\22\2\u0307\u02fd\3\2\2\2\u0307\u0308\3\2\2\2\u0308\u030d\3"+
		"\2\2\2\u0309\u030b\5\"\22\2\u030a\u0309\3\2\2\2\u030a\u030b\3\2\2\2\u030b"+
		"\u030d\3\2\2\2\u030c\u02fa\3\2\2\2\u030c\u030a\3\2\2\2\u030dE\3\2\2\2"+
		"\u030e\u030f\7*\2\2\u030f\u0310\7:\2\2\u0310\u0311\7\65\2\2\u0311\u0312"+
		"\7:\2\2\u0312\u0313\7+\2\2\u0313\u0314\7:\2\2\u0314\u0315\7\65\2\2\u0315"+
		"G\3\2\2\2\u0316\u0317\7\6\2\2\u0317\u0319\7:\2\2\u0318\u0316\3\2\2\2\u0318"+
		"\u0319\3\2\2\2\u0319\u031a\3\2\2\2\u031a\u031b\7,\2\2\u031b\u031c\7:\2"+
		"\2\u031c\u031d\7\65\2\2\u031d\u031e\7:\2\2\u031e\u031f\7&\2\2\u031f\u0320"+
		"\7:\2\2\u0320\u0321\5@!\2\u0321\u0322\7:\2\2\u0322\u0323\7\'\2\2\u0323"+
		"\u0324\7:\2\2\u0324\u0325\5> \2\u0325I\3\2\2\2\u0088MRX]chnsw{\u0081\u0086"+
		"\u008c\u0091\u0097\u009c\u00a2\u00a7\u00ac\u00b2\u00b7\u00bd\u00c2\u00c8"+
		"\u00d9\u00df\u00e3\u00e6\u00e8\u00ec\u00f0\u00f7\u00fb\u00ff\u0103\u0107"+
		"\u010b\u0111\u0115\u0119\u0124\u0128\u012c\u0131\u013a\u013e\u0142\u014b"+
		"\u014f\u0154\u0158\u015f\u0163\u0167\u016b\u016d\u0172\u0176\u017a\u0180"+
		"\u0184\u0189\u018c\u019c\u01ab\u01af\u01b2\u01b4\u01b8\u01be\u01c4\u01c9"+
		"\u01d1\u01d5\u01db\u01e1\u01ec\u01f2\u01f5\u01f9\u01ff\u0203\u0207\u020b"+
		"\u0211\u0218\u021c\u0220\u0226\u022a\u022f\u0236\u0239\u023d\u0243\u0247"+
		"\u024c\u0251\u0256\u025c\u0260\u0265\u0268\u026d\u0274\u027a\u027f\u0283"+
		"\u0288\u0292\u0296\u029a\u02a1\u02a7\u02b0\u02b2\u02b7\u02c7\u02cd\u02d2"+
		"\u02d6\u02db\u02e2\u02e7\u02eb\u02f0\u02f3\u02fa\u02fd\u0303\u0307\u030a"+
		"\u030c\u0318";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}