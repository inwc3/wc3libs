// Generated from E:/work/wc3libs/src/main/antlr\Jass.g4 by ANTLR 4.7
package net.moonlightflower.wc3libs.antlr;

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
		REAL_LITERAL=49, STRING_LITERAL=50, COMMENT_SINGLE=51, COMMENT_BLOCK=52, 
		ID=53, CURLY_L=54, CURLY_R=55, COMMA=56, NEW_LINE=57, WS=58;
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
		null, null, null, null, null, null, null, null, null, null, "'{'", "'}'", 
		"','"
	};
	private static final String[] _SYMBOLIC_NAMES = {
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, null, null, null, null, null, 
		null, null, null, null, null, null, null, "BOOL_LITERAL", "INT_LITERAL", 
		"DEC_INT_LITERAL", "OCT_INT_LITERAL", "HEX_INT_LITERAL", "ID_INT_LITERAL", 
		"REAL_LITERAL", "STRING_LITERAL", "COMMENT_SINGLE", "COMMENT_BLOCK", "ID", 
		"CURLY_L", "CURLY_R", "COMMA", "NEW_LINE", "WS"
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
			_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
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
				}
				setState(151);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,14,_ctx);
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
				_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
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
					}
					setState(162);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,16,_ctx);
				}
				}
				}
				setState(167);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(171);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEW_LINE || _la==WS) {
				{
				{
				setState(168);
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
				setState(173);
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
	}

	public final GlobalsBlockContext globalsBlock() throws RecognitionException {
		GlobalsBlockContext _localctx = new GlobalsBlockContext(_ctx, getState());
		enterRule(_localctx, 2, RULE_globalsBlock);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(174);
			match(T__0);
			setState(176);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(175);
				match(WS);
				}
			}

			setState(178);
			match(NEW_LINE);
			setState(182);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(179);
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
				setState(184);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,20,_ctx);
			}
			setState(198);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__3 || _la==ID) {
				{
				{
				setState(185);
				globalDec();
				setState(187);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(186);
					match(WS);
					}
				}

				setState(189);
				match(NEW_LINE);
				setState(193);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(190);
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
					setState(195);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,22,_ctx);
				}
				}
				}
				setState(200);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(204);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEW_LINE || _la==WS) {
				{
				{
				setState(201);
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
				setState(206);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(207);
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
	}

	public final VarNameContext varName() throws RecognitionException {
		VarNameContext _localctx = new VarNameContext(_ctx, getState());
		enterRule(_localctx, 4, RULE_varName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(209);
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
	}

	public final FuncNameContext funcName() throws RecognitionException {
		FuncNameContext _localctx = new FuncNameContext(_ctx, getState());
		enterRule(_localctx, 6, RULE_funcName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(211);
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
	}

	public final GlobalDecContext globalDec() throws RecognitionException {
		GlobalDecContext _localctx = new GlobalDecContext(_ctx, getState());
		enterRule(_localctx, 8, RULE_globalDec);
		int _la;
		try {
			setState(236);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,29,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(213);
				((GlobalDecContext)_localctx).type = typeName();
				setState(214);
				match(WS);
				setState(215);
				match(T__2);
				setState(216);
				match(WS);
				setState(217);
				((GlobalDecContext)_localctx).name = varName();
				}
				break;
			case 2:
				enterOuterAlt(_localctx, 2);
				{
				setState(221);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__3) {
					{
					setState(219);
					match(T__3);
					setState(220);
					match(WS);
					}
				}

				setState(223);
				((GlobalDecContext)_localctx).type = typeName();
				setState(224);
				match(WS);
				setState(225);
				((GlobalDecContext)_localctx).name = varName();
				setState(234);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,28,_ctx) ) {
				case 1:
					{
					setState(227);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==WS) {
						{
						setState(226);
						match(WS);
						}
					}

					setState(229);
					match(T__4);
					setState(231);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==WS) {
						{
						setState(230);
						match(WS);
						}
					}

					setState(233);
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
	}

	public final SurroundedExprContext surroundedExpr() throws RecognitionException {
		SurroundedExprContext _localctx = new SurroundedExprContext(_ctx, getState());
		enterRule(_localctx, 10, RULE_surroundedExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(238);
			match(T__5);
			setState(240);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(239);
				match(WS);
				}
			}

			setState(242);
			expr(0);
			setState(244);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(243);
				match(WS);
				}
			}

			setState(246);
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
			setState(326);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,47,_ctx) ) {
			case 1:
				{
				setState(249);
				match(T__5);
				setState(251);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(250);
					match(WS);
					}
				}

				setState(253);
				expr(0);
				setState(255);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(254);
					match(WS);
					}
				}

				setState(257);
				match(T__6);
				setState(259);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(258);
					match(WS);
					}
				}

				setState(261);
				_la = _input.LA(1);
				if ( !(_la==T__7 || _la==T__8) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(263);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(262);
					match(WS);
					}
				}

				setState(265);
				match(T__5);
				setState(267);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(266);
					match(WS);
					}
				}

				setState(269);
				expr(0);
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
				match(T__6);
				}
				break;
			case 2:
				{
				setState(275);
				match(T__5);
				setState(277);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(276);
					match(WS);
					}
				}

				setState(279);
				expr(0);
				setState(281);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(280);
					match(WS);
					}
				}

				setState(283);
				match(T__6);
				setState(285);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(284);
					match(WS);
					}
				}

				setState(287);
				_la = _input.LA(1);
				if ( !(_la==T__7 || _la==T__8) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(288);
				match(WS);
				setState(289);
				expr(11);
				}
				break;
			case 3:
				{
				setState(291);
				match(T__9);
				setState(304);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case WS:
					{
					setState(292);
					match(WS);
					setState(293);
					expr(0);
					}
					break;
				case T__5:
					{
					setState(294);
					match(T__5);
					setState(296);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==WS) {
						{
						setState(295);
						match(WS);
						}
					}

					setState(298);
					expr(0);
					setState(300);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==WS) {
						{
						setState(299);
						match(WS);
						}
					}

					setState(302);
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
				setState(306);
				literal();
				}
				break;
			case 5:
				{
				setState(307);
				_la = _input.LA(1);
				if ( !(_la==T__10 || _la==T__11) ) {
				_errHandler.recoverInline(this);
				}
				else {
					if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
					_errHandler.reportMatch(this);
					consume();
				}
				setState(309);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(308);
					match(WS);
					}
				}

				setState(311);
				expr(7);
				}
				break;
			case 6:
				{
				setState(312);
				funcExpr();
				}
				break;
			case 7:
				{
				setState(313);
				varName();
				}
				break;
			case 8:
				{
				setState(314);
				arrayRead();
				}
				break;
			case 9:
				{
				setState(315);
				funcRef();
				}
				break;
			case 10:
				{
				setState(316);
				match(T__5);
				setState(318);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(317);
					match(WS);
					}
				}

				setState(320);
				expr(0);
				setState(322);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(321);
					match(WS);
					}
				}

				setState(324);
				match(T__6);
				}
				break;
			}
			_ctx.stop = _input.LT(-1);
			setState(369);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,56,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					if ( _parseListeners!=null ) triggerExitRuleEvent();
					_prevctx = _localctx;
					{
					setState(367);
					_errHandler.sync(this);
					switch ( getInterpreter().adaptivePredict(_input,55,_ctx) ) {
					case 1:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(328);
						if (!(precpred(_ctx, 14))) throw new FailedPredicateException(this, "precpred(_ctx, 14)");
						setState(329);
						match(WS);
						setState(330);
						_la = _input.LA(1);
						if ( !(_la==T__7 || _la==T__8) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(331);
						match(WS);
						setState(332);
						expr(15);
						}
						break;
					case 2:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(333);
						if (!(precpred(_ctx, 8))) throw new FailedPredicateException(this, "precpred(_ctx, 8)");
						setState(335);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==WS) {
							{
							setState(334);
							match(WS);
							}
						}

						setState(337);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__10) | (1L << T__11) | (1L << T__12) | (1L << T__13))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(339);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==WS) {
							{
							setState(338);
							match(WS);
							}
						}

						setState(341);
						expr(9);
						}
						break;
					case 3:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(342);
						if (!(precpred(_ctx, 6))) throw new FailedPredicateException(this, "precpred(_ctx, 6)");
						setState(344);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==WS) {
							{
							setState(343);
							match(WS);
							}
						}

						setState(346);
						_la = _input.LA(1);
						if ( !((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__14) | (1L << T__15) | (1L << T__16) | (1L << T__17) | (1L << T__18) | (1L << T__19))) != 0)) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(348);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==WS) {
							{
							setState(347);
							match(WS);
							}
						}

						setState(350);
						expr(7);
						}
						break;
					case 4:
						{
						_localctx = new ExprContext(_parentctx, _parentState);
						pushNewRecursionContext(_localctx, _startState, RULE_expr);
						setState(351);
						if (!(precpred(_ctx, 12))) throw new FailedPredicateException(this, "precpred(_ctx, 12)");
						setState(352);
						match(WS);
						setState(353);
						_la = _input.LA(1);
						if ( !(_la==T__7 || _la==T__8) ) {
						_errHandler.recoverInline(this);
						}
						else {
							if ( _input.LA(1)==Token.EOF ) matchedEOF = true;
							_errHandler.reportMatch(this);
							consume();
						}
						setState(355);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==WS) {
							{
							setState(354);
							match(WS);
							}
						}

						setState(357);
						match(T__5);
						setState(359);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==WS) {
							{
							setState(358);
							match(WS);
							}
						}

						setState(361);
						expr(0);
						setState(363);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==WS) {
							{
							setState(362);
							match(WS);
							}
						}

						setState(365);
						match(T__6);
						}
						break;
					}
					} 
				}
				setState(371);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,56,_ctx);
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
	}

	public final FuncExprContext funcExpr() throws RecognitionException {
		FuncExprContext _localctx = new FuncExprContext(_ctx, getState());
		enterRule(_localctx, 14, RULE_funcExpr);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(372);
			funcName();
			setState(374);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(373);
				match(WS);
				}
			}

			setState(376);
			match(T__5);
			setState(378);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,58,_ctx) ) {
			case 1:
				{
				setState(377);
				match(WS);
				}
				break;
			}
			setState(380);
			arg_list();
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
	}

	public final Arg_listContext arg_list() throws RecognitionException {
		Arg_listContext _localctx = new Arg_listContext(_ctx, getState());
		enterRule(_localctx, 16, RULE_arg_list);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(400);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__5) | (1L << T__9) | (1L << T__10) | (1L << T__11) | (1L << T__22) | (1L << BOOL_LITERAL) | (1L << INT_LITERAL) | (1L << REAL_LITERAL) | (1L << STRING_LITERAL) | (1L << ID))) != 0)) {
				{
				setState(386);
				expr(0);
				setState(397);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,62,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(388);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==WS) {
							{
							setState(387);
							match(WS);
							}
						}

						setState(390);
						match(COMMA);
						setState(392);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==WS) {
							{
							setState(391);
							match(WS);
							}
						}

						setState(394);
						expr(0);
						}
						} 
					}
					setState(399);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,62,_ctx);
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
	}

	public final ArrayReadContext arrayRead() throws RecognitionException {
		ArrayReadContext _localctx = new ArrayReadContext(_ctx, getState());
		enterRule(_localctx, 18, RULE_arrayRead);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(402);
			varName();
			setState(403);
			match(T__20);
			setState(404);
			expr(0);
			setState(405);
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
	}

	public final FuncRefContext funcRef() throws RecognitionException {
		FuncRefContext _localctx = new FuncRefContext(_ctx, getState());
		enterRule(_localctx, 20, RULE_funcRef);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(407);
			match(T__22);
			setState(408);
			match(WS);
			setState(409);
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
	}

	public final LiteralContext literal() throws RecognitionException {
		LiteralContext _localctx = new LiteralContext(_ctx, getState());
		enterRule(_localctx, 22, RULE_literal);
		try {
			setState(416);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case BOOL_LITERAL:
				enterOuterAlt(_localctx, 1);
				{
				setState(411);
				match(BOOL_LITERAL);
				}
				break;
			case INT_LITERAL:
				enterOuterAlt(_localctx, 2);
				{
				setState(412);
				match(INT_LITERAL);
				}
				break;
			case REAL_LITERAL:
				enterOuterAlt(_localctx, 3);
				{
				setState(413);
				match(REAL_LITERAL);
				}
				break;
			case STRING_LITERAL:
				enterOuterAlt(_localctx, 4);
				{
				setState(414);
				match(STRING_LITERAL);
				}
				break;
			case T__22:
				enterOuterAlt(_localctx, 5);
				{
				setState(415);
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
	}

	public final LocalVarDecContext localVarDec() throws RecognitionException {
		LocalVarDecContext _localctx = new LocalVarDecContext(_ctx, getState());
		enterRule(_localctx, 24, RULE_localVarDec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(418);
			match(T__23);
			setState(440);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,68,_ctx) ) {
			case 1:
				{
				{
				setState(419);
				match(WS);
				setState(420);
				typeName();
				setState(421);
				match(WS);
				setState(422);
				match(T__2);
				setState(423);
				match(WS);
				setState(424);
				((LocalVarDecContext)_localctx).name = varName();
				}
				}
				break;
			case 2:
				{
				{
				setState(426);
				match(WS);
				setState(427);
				typeName();
				setState(428);
				match(WS);
				setState(429);
				((LocalVarDecContext)_localctx).name = varName();
				setState(438);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,67,_ctx) ) {
				case 1:
					{
					setState(431);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==WS) {
						{
						setState(430);
						match(WS);
						}
					}

					setState(433);
					match(T__4);
					setState(435);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==WS) {
						{
						setState(434);
						match(WS);
						}
					}

					setState(437);
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
	}

	public final LocalVarDec_listContext localVarDec_list() throws RecognitionException {
		LocalVarDec_listContext _localctx = new LocalVarDec_listContext(_ctx, getState());
		enterRule(_localctx, 26, RULE_localVarDec_list);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(442);
			localVarDec();
			setState(456);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,71,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(444);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==WS) {
						{
						setState(443);
						match(WS);
						}
					}

					setState(446);
					match(NEW_LINE);
					setState(450);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==NEW_LINE || _la==WS) {
						{
						{
						setState(447);
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
						setState(452);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(453);
					localVarDec();
					}
					} 
				}
				setState(458);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,71,_ctx);
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
	}

	public final Statement2Context statement2() throws RecognitionException {
		Statement2Context _localctx = new Statement2Context(_ctx, getState());
		enterRule(_localctx, 28, RULE_statement2);
		try {
			setState(461);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__24:
				enterOuterAlt(_localctx, 1);
				{
				setState(459);
				callFunc();
				}
				break;
			case T__26:
				enterOuterAlt(_localctx, 2);
				{
				setState(460);
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
	}

	public final StatementContext statement() throws RecognitionException {
		StatementContext _localctx = new StatementContext(_ctx, getState());
		enterRule(_localctx, 30, RULE_statement);
		try {
			setState(469);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__24:
				enterOuterAlt(_localctx, 1);
				{
				setState(463);
				callFunc();
				}
				break;
			case T__25:
				enterOuterAlt(_localctx, 2);
				{
				setState(464);
				setVar();
				}
				break;
			case T__26:
				enterOuterAlt(_localctx, 3);
				{
				setState(465);
				selection();
				}
				break;
			case T__31:
				enterOuterAlt(_localctx, 4);
				{
				setState(466);
				loop();
				}
				break;
			case T__33:
				enterOuterAlt(_localctx, 5);
				{
				setState(467);
				exitwhen();
				}
				break;
			case T__34:
				enterOuterAlt(_localctx, 6);
				{
				setState(468);
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
	}

	public final Statement_listContext statement_list() throws RecognitionException {
		Statement_listContext _localctx = new Statement_listContext(_ctx, getState());
		enterRule(_localctx, 32, RULE_statement_list);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(471);
			statement();
			setState(485);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,76,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(473);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==WS) {
						{
						setState(472);
						match(WS);
						}
					}

					setState(475);
					match(NEW_LINE);
					setState(479);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==NEW_LINE || _la==WS) {
						{
						{
						setState(476);
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
						setState(481);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(482);
					statement();
					}
					} 
				}
				setState(487);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,76,_ctx);
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
	}

	public final CallFuncContext callFunc() throws RecognitionException {
		CallFuncContext _localctx = new CallFuncContext(_ctx, getState());
		enterRule(_localctx, 34, RULE_callFunc);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(488);
			match(T__24);
			setState(489);
			match(WS);
			setState(490);
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
	}

	public final SetVarContext setVar() throws RecognitionException {
		SetVarContext _localctx = new SetVarContext(_ctx, getState());
		enterRule(_localctx, 36, RULE_setVar);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(492);
			match(T__25);
			setState(493);
			match(WS);
			setState(494);
			((SetVarContext)_localctx).name = varName();
			setState(502);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,78,_ctx) ) {
			case 1:
				{
				setState(496);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(495);
					match(WS);
					}
				}

				setState(498);
				match(T__20);
				setState(499);
				((SetVarContext)_localctx).index = expr(0);
				setState(500);
				match(T__21);
				}
				break;
			}
			setState(505);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(504);
				match(WS);
				}
			}

			setState(507);
			match(T__4);
			setState(509);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(508);
				match(WS);
				}
			}

			setState(511);
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
	}

	public final ConditionContext condition() throws RecognitionException {
		ConditionContext _localctx = new ConditionContext(_ctx, getState());
		enterRule(_localctx, 38, RULE_condition);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(515);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,81,_ctx) ) {
			case 1:
				{
				setState(513);
				surroundedExpr();
				}
				break;
			case 2:
				{
				setState(514);
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
	}

	public final Selection2Context selection2() throws RecognitionException {
		Selection2Context _localctx = new Selection2Context(_ctx, getState());
		enterRule(_localctx, 40, RULE_selection2);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(517);
			match(T__26);
			setState(519);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(518);
				match(WS);
				}
			}

			setState(521);
			condition();
			setState(523);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(522);
				match(WS);
				}
			}

			setState(525);
			match(T__27);
			setState(527);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(526);
				match(WS);
				}
			}

			setState(529);
			match(NEW_LINE);
			setState(533);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEW_LINE || _la==WS) {
				{
				{
				setState(530);
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
				setState(535);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(536);
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
	}

	public final SelectionContext selection() throws RecognitionException {
		SelectionContext _localctx = new SelectionContext(_ctx, getState());
		enterRule(_localctx, 42, RULE_selection);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(538);
			match(T__26);
			setState(540);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(539);
				match(WS);
				}
			}

			setState(542);
			condition();
			setState(544);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(543);
				match(WS);
				}
			}

			setState(546);
			match(T__27);
			setState(548);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(547);
				match(WS);
				}
			}

			setState(550);
			match(NEW_LINE);
			setState(554);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,89,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(551);
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
				setState(556);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,89,_ctx);
			}
			setState(558);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__31) | (1L << T__33) | (1L << T__34))) != 0)) {
				{
				setState(557);
				statement_list();
				}
			}

			setState(563);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,91,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(560);
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
				setState(565);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,91,_ctx);
			}
			setState(597);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==T__29) {
				{
				{
				setState(566);
				match(T__29);
				setState(570);
				_errHandler.sync(this);
				switch (_input.LA(1)) {
				case T__5:
					{
					setState(567);
					surroundedExpr();
					}
					break;
				case WS:
					{
					{
					setState(568);
					match(WS);
					setState(569);
					expr(0);
					}
					}
					break;
				default:
					throw new NoViableAltException(this);
				}
				setState(573);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(572);
					match(WS);
					}
				}

				setState(575);
				match(T__27);
				setState(577);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(576);
					match(WS);
					}
				}

				setState(579);
				match(NEW_LINE);
				setState(583);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,95,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(580);
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
					setState(585);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,95,_ctx);
				}
				setState(587);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__31) | (1L << T__33) | (1L << T__34))) != 0)) {
					{
					setState(586);
					statement_list();
					}
				}

				setState(592);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,97,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(589);
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
					setState(594);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,97,_ctx);
				}
				}
				}
				setState(599);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(620);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__30) {
				{
				setState(600);
				match(T__30);
				setState(602);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(601);
					match(WS);
					}
				}

				setState(604);
				match(NEW_LINE);
				setState(608);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,100,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(605);
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
					setState(610);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,100,_ctx);
				}
				setState(612);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__31) | (1L << T__33) | (1L << T__34))) != 0)) {
					{
					setState(611);
					statement_list();
					}
				}

				setState(617);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,102,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(614);
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
					setState(619);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,102,_ctx);
				}
				}
			}

			setState(625);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEW_LINE || _la==WS) {
				{
				{
				setState(622);
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
				setState(627);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(628);
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
	}

	public final LoopContext loop() throws RecognitionException {
		LoopContext _localctx = new LoopContext(_ctx, getState());
		enterRule(_localctx, 44, RULE_loop);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(630);
			match(T__31);
			setState(632);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(631);
				match(WS);
				}
			}

			setState(634);
			match(NEW_LINE);
			setState(638);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,106,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(635);
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
				setState(640);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,106,_ctx);
			}
			setState(647);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__31) | (1L << T__33) | (1L << T__34))) != 0)) {
				{
				setState(641);
				loopBody();
				setState(643);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(642);
					match(WS);
					}
				}

				setState(645);
				match(NEW_LINE);
				}
			}

			setState(652);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEW_LINE || _la==WS) {
				{
				{
				setState(649);
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
				setState(654);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(655);
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
	}

	public final ExitwhenContext exitwhen() throws RecognitionException {
		ExitwhenContext _localctx = new ExitwhenContext(_ctx, getState());
		enterRule(_localctx, 46, RULE_exitwhen);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(657);
			match(T__33);
			setState(670);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case WS:
				{
				{
				setState(658);
				match(WS);
				setState(659);
				expr(0);
				}
				}
				break;
			case T__5:
				{
				setState(660);
				match(T__5);
				setState(662);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(661);
					match(WS);
					}
				}

				setState(664);
				expr(0);
				setState(666);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(665);
					match(WS);
					}
				}

				setState(668);
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
	}

	public final LoopBodyContext loopBody() throws RecognitionException {
		LoopBodyContext _localctx = new LoopBodyContext(_ctx, getState());
		enterRule(_localctx, 48, RULE_loopBody);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(672);
			loopBodyLine();
			setState(683);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,114,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(673);
					match(NEW_LINE);
					setState(677);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==NEW_LINE || _la==WS) {
						{
						{
						setState(674);
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
						setState(679);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(680);
					loopBodyLine();
					}
					} 
				}
				setState(685);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,114,_ctx);
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
	}

	public final LoopBodyLineContext loopBodyLine() throws RecognitionException {
		LoopBodyLineContext _localctx = new LoopBodyLineContext(_ctx, getState());
		enterRule(_localctx, 50, RULE_loopBodyLine);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(686);
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
	}

	public final Rule_returnContext rule_return() throws RecognitionException {
		Rule_returnContext _localctx = new Rule_returnContext(_ctx, getState());
		enterRule(_localctx, 52, RULE_rule_return);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(688);
			match(T__34);
			setState(694);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__5:
				{
				setState(689);
				surroundedExpr();
				}
				break;
			case T__28:
			case T__29:
			case T__30:
			case NEW_LINE:
			case WS:
				{
				setState(692);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,115,_ctx) ) {
				case 1:
					{
					setState(690);
					match(WS);
					setState(691);
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
	}

	public final TypeNameContext typeName() throws RecognitionException {
		TypeNameContext _localctx = new TypeNameContext(_ctx, getState());
		enterRule(_localctx, 54, RULE_typeName);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(696);
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
	}

	public final FuncDecContext funcDec() throws RecognitionException {
		FuncDecContext _localctx = new FuncDecContext(_ctx, getState());
		enterRule(_localctx, 56, RULE_funcDec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(699);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(698);
				match(T__3);
				}
			}

			setState(701);
			match(T__22);
			setState(702);
			match(WS);
			setState(703);
			((FuncDecContext)_localctx).name = match(ID);
			setState(704);
			match(WS);
			setState(705);
			match(T__35);
			setState(706);
			match(WS);
			setState(707);
			((FuncDecContext)_localctx).params = funcParam_list();
			setState(708);
			match(WS);
			setState(709);
			match(T__36);
			setState(710);
			match(WS);
			setState(711);
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
	}

	public final FuncContext func() throws RecognitionException {
		FuncContext _localctx = new FuncContext(_ctx, getState());
		enterRule(_localctx, 58, RULE_func);
		int _la;
		try {
			int _alt;
			enterOuterAlt(_localctx, 1);
			{
			setState(713);
			funcDec();
			setState(715);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==WS) {
				{
				setState(714);
				match(WS);
				}
			}

			setState(717);
			match(NEW_LINE);
			setState(721);
			_errHandler.sync(this);
			_alt = getInterpreter().adaptivePredict(_input,119,_ctx);
			while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
				if ( _alt==1 ) {
					{
					{
					setState(718);
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
				setState(723);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,119,_ctx);
			}
			setState(730);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,121,_ctx) ) {
			case 1:
				{
				setState(724);
				((FuncContext)_localctx).body = funcBody();
				setState(726);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==WS) {
					{
					setState(725);
					match(WS);
					}
				}

				setState(728);
				match(NEW_LINE);
				}
				break;
			}
			setState(735);
			_errHandler.sync(this);
			_la = _input.LA(1);
			while (_la==NEW_LINE || _la==WS) {
				{
				{
				setState(732);
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
				setState(737);
				_errHandler.sync(this);
				_la = _input.LA(1);
			}
			setState(738);
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
	}

	public final FuncReturnTypeContext funcReturnType() throws RecognitionException {
		FuncReturnTypeContext _localctx = new FuncReturnTypeContext(_ctx, getState());
		enterRule(_localctx, 60, RULE_funcReturnType);
		try {
			setState(742);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__38:
				enterOuterAlt(_localctx, 1);
				{
				setState(740);
				match(T__38);
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				setState(741);
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
	}

	public final FuncParam_listContext funcParam_list() throws RecognitionException {
		FuncParam_listContext _localctx = new FuncParam_listContext(_ctx, getState());
		enterRule(_localctx, 62, RULE_funcParam_list);
		int _la;
		try {
			int _alt;
			setState(759);
			_errHandler.sync(this);
			switch (_input.LA(1)) {
			case T__38:
				enterOuterAlt(_localctx, 1);
				{
				setState(744);
				match(T__38);
				}
				break;
			case ID:
				enterOuterAlt(_localctx, 2);
				{
				{
				setState(745);
				((FuncParam_listContext)_localctx).params = funcParam();
				setState(756);
				_errHandler.sync(this);
				_alt = getInterpreter().adaptivePredict(_input,126,_ctx);
				while ( _alt!=2 && _alt!=org.antlr.v4.runtime.atn.ATN.INVALID_ALT_NUMBER ) {
					if ( _alt==1 ) {
						{
						{
						setState(747);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==WS) {
							{
							setState(746);
							match(WS);
							}
						}

						setState(749);
						match(COMMA);
						setState(751);
						_errHandler.sync(this);
						_la = _input.LA(1);
						if (_la==WS) {
							{
							setState(750);
							match(WS);
							}
						}

						setState(753);
						funcParam();
						}
						} 
					}
					setState(758);
					_errHandler.sync(this);
					_alt = getInterpreter().adaptivePredict(_input,126,_ctx);
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
	}

	public final FuncParamContext funcParam() throws RecognitionException {
		FuncParamContext _localctx = new FuncParamContext(_ctx, getState());
		enterRule(_localctx, 64, RULE_funcParam);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(761);
			typeName();
			setState(762);
			match(WS);
			setState(763);
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
	}

	public final FuncBodyContext funcBody() throws RecognitionException {
		FuncBodyContext _localctx = new FuncBodyContext(_ctx, getState());
		enterRule(_localctx, 66, RULE_funcBody);
		int _la;
		try {
			setState(784);
			_errHandler.sync(this);
			switch ( getInterpreter().adaptivePredict(_input,133,_ctx) ) {
			case 1:
				enterOuterAlt(_localctx, 1);
				{
				setState(766);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if (_la==T__23) {
					{
					setState(765);
					localVarDec_list();
					}
				}

				setState(779);
				_errHandler.sync(this);
				switch ( getInterpreter().adaptivePredict(_input,131,_ctx) ) {
				case 1:
					{
					setState(769);
					_errHandler.sync(this);
					_la = _input.LA(1);
					if (_la==WS) {
						{
						setState(768);
						match(WS);
						}
					}

					setState(771);
					match(NEW_LINE);
					setState(775);
					_errHandler.sync(this);
					_la = _input.LA(1);
					while (_la==NEW_LINE || _la==WS) {
						{
						{
						setState(772);
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
						setState(777);
						_errHandler.sync(this);
						_la = _input.LA(1);
					}
					setState(778);
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
				setState(782);
				_errHandler.sync(this);
				_la = _input.LA(1);
				if ((((_la) & ~0x3f) == 0 && ((1L << _la) & ((1L << T__24) | (1L << T__25) | (1L << T__26) | (1L << T__31) | (1L << T__33) | (1L << T__34))) != 0)) {
					{
					setState(781);
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
	}

	public final TypeDecContext typeDec() throws RecognitionException {
		TypeDecContext _localctx = new TypeDecContext(_ctx, getState());
		enterRule(_localctx, 68, RULE_typeDec);
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(786);
			match(T__39);
			setState(787);
			match(WS);
			setState(788);
			((TypeDecContext)_localctx).name = match(ID);
			setState(789);
			match(WS);
			setState(790);
			match(T__40);
			setState(791);
			match(WS);
			setState(792);
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
	}

	public final NativeDecContext nativeDec() throws RecognitionException {
		NativeDecContext _localctx = new NativeDecContext(_ctx, getState());
		enterRule(_localctx, 70, RULE_nativeDec);
		int _la;
		try {
			enterOuterAlt(_localctx, 1);
			{
			setState(796);
			_errHandler.sync(this);
			_la = _input.LA(1);
			if (_la==T__3) {
				{
				setState(794);
				match(T__3);
				setState(795);
				match(WS);
				}
			}

			setState(798);
			match(T__41);
			setState(799);
			match(WS);
			setState(800);
			((NativeDecContext)_localctx).name = match(ID);
			setState(801);
			match(WS);
			setState(802);
			match(T__35);
			setState(803);
			match(WS);
			setState(804);
			((NativeDecContext)_localctx).params = funcParam_list();
			setState(805);
			match(WS);
			setState(806);
			match(T__36);
			setState(807);
			match(WS);
			setState(808);
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
		"\3\u608b\ua72a\u8133\ub9ed\u417c\u3be7\u7786\u5964\3<\u032d\4\2\t\2\4"+
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
		"\2\u00a4\13\2\7\2\u00a6\n\2\f\2\16\2\u00a9\13\2\3\2\7\2\u00ac\n\2\f\2"+
		"\16\2\u00af\13\2\3\3\3\3\5\3\u00b3\n\3\3\3\3\3\7\3\u00b7\n\3\f\3\16\3"+
		"\u00ba\13\3\3\3\3\3\5\3\u00be\n\3\3\3\3\3\7\3\u00c2\n\3\f\3\16\3\u00c5"+
		"\13\3\7\3\u00c7\n\3\f\3\16\3\u00ca\13\3\3\3\7\3\u00cd\n\3\f\3\16\3\u00d0"+
		"\13\3\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3\6\3\6\3\6\3\6\3\6\3\6\5\6\u00e0"+
		"\n\6\3\6\3\6\3\6\3\6\5\6\u00e6\n\6\3\6\3\6\5\6\u00ea\n\6\3\6\5\6\u00ed"+
		"\n\6\5\6\u00ef\n\6\3\7\3\7\5\7\u00f3\n\7\3\7\3\7\5\7\u00f7\n\7\3\7\3\7"+
		"\3\b\3\b\3\b\5\b\u00fe\n\b\3\b\3\b\5\b\u0102\n\b\3\b\3\b\5\b\u0106\n\b"+
		"\3\b\3\b\5\b\u010a\n\b\3\b\3\b\5\b\u010e\n\b\3\b\3\b\5\b\u0112\n\b\3\b"+
		"\3\b\3\b\3\b\5\b\u0118\n\b\3\b\3\b\5\b\u011c\n\b\3\b\3\b\5\b\u0120\n\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u012b\n\b\3\b\3\b\5\b\u012f\n"+
		"\b\3\b\3\b\5\b\u0133\n\b\3\b\3\b\3\b\5\b\u0138\n\b\3\b\3\b\3\b\3\b\3\b"+
		"\3\b\3\b\5\b\u0141\n\b\3\b\3\b\5\b\u0145\n\b\3\b\3\b\5\b\u0149\n\b\3\b"+
		"\3\b\3\b\3\b\3\b\3\b\3\b\5\b\u0152\n\b\3\b\3\b\5\b\u0156\n\b\3\b\3\b\3"+
		"\b\5\b\u015b\n\b\3\b\3\b\5\b\u015f\n\b\3\b\3\b\3\b\3\b\3\b\5\b\u0166\n"+
		"\b\3\b\3\b\5\b\u016a\n\b\3\b\3\b\5\b\u016e\n\b\3\b\3\b\7\b\u0172\n\b\f"+
		"\b\16\b\u0175\13\b\3\t\3\t\5\t\u0179\n\t\3\t\3\t\5\t\u017d\n\t\3\t\3\t"+
		"\5\t\u0181\n\t\3\t\3\t\3\n\3\n\5\n\u0187\n\n\3\n\3\n\5\n\u018b\n\n\3\n"+
		"\7\n\u018e\n\n\f\n\16\n\u0191\13\n\5\n\u0193\n\n\3\13\3\13\3\13\3\13\3"+
		"\13\3\f\3\f\3\f\3\f\3\r\3\r\3\r\3\r\3\r\5\r\u01a3\n\r\3\16\3\16\3\16\3"+
		"\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\3\16\5\16\u01b2\n\16\3\16"+
		"\3\16\5\16\u01b6\n\16\3\16\5\16\u01b9\n\16\5\16\u01bb\n\16\3\17\3\17\5"+
		"\17\u01bf\n\17\3\17\3\17\7\17\u01c3\n\17\f\17\16\17\u01c6\13\17\3\17\7"+
		"\17\u01c9\n\17\f\17\16\17\u01cc\13\17\3\20\3\20\5\20\u01d0\n\20\3\21\3"+
		"\21\3\21\3\21\3\21\3\21\5\21\u01d8\n\21\3\22\3\22\5\22\u01dc\n\22\3\22"+
		"\3\22\7\22\u01e0\n\22\f\22\16\22\u01e3\13\22\3\22\7\22\u01e6\n\22\f\22"+
		"\16\22\u01e9\13\22\3\23\3\23\3\23\3\23\3\24\3\24\3\24\3\24\5\24\u01f3"+
		"\n\24\3\24\3\24\3\24\3\24\5\24\u01f9\n\24\3\24\5\24\u01fc\n\24\3\24\3"+
		"\24\5\24\u0200\n\24\3\24\3\24\3\25\3\25\5\25\u0206\n\25\3\26\3\26\5\26"+
		"\u020a\n\26\3\26\3\26\5\26\u020e\n\26\3\26\3\26\5\26\u0212\n\26\3\26\3"+
		"\26\7\26\u0216\n\26\f\26\16\26\u0219\13\26\3\26\3\26\3\27\3\27\5\27\u021f"+
		"\n\27\3\27\3\27\5\27\u0223\n\27\3\27\3\27\5\27\u0227\n\27\3\27\3\27\7"+
		"\27\u022b\n\27\f\27\16\27\u022e\13\27\3\27\5\27\u0231\n\27\3\27\7\27\u0234"+
		"\n\27\f\27\16\27\u0237\13\27\3\27\3\27\3\27\3\27\5\27\u023d\n\27\3\27"+
		"\5\27\u0240\n\27\3\27\3\27\5\27\u0244\n\27\3\27\3\27\7\27\u0248\n\27\f"+
		"\27\16\27\u024b\13\27\3\27\5\27\u024e\n\27\3\27\7\27\u0251\n\27\f\27\16"+
		"\27\u0254\13\27\7\27\u0256\n\27\f\27\16\27\u0259\13\27\3\27\3\27\5\27"+
		"\u025d\n\27\3\27\3\27\7\27\u0261\n\27\f\27\16\27\u0264\13\27\3\27\5\27"+
		"\u0267\n\27\3\27\7\27\u026a\n\27\f\27\16\27\u026d\13\27\5\27\u026f\n\27"+
		"\3\27\7\27\u0272\n\27\f\27\16\27\u0275\13\27\3\27\3\27\3\30\3\30\5\30"+
		"\u027b\n\30\3\30\3\30\7\30\u027f\n\30\f\30\16\30\u0282\13\30\3\30\3\30"+
		"\5\30\u0286\n\30\3\30\3\30\5\30\u028a\n\30\3\30\7\30\u028d\n\30\f\30\16"+
		"\30\u0290\13\30\3\30\3\30\3\31\3\31\3\31\3\31\3\31\5\31\u0299\n\31\3\31"+
		"\3\31\5\31\u029d\n\31\3\31\3\31\5\31\u02a1\n\31\3\32\3\32\3\32\7\32\u02a6"+
		"\n\32\f\32\16\32\u02a9\13\32\3\32\7\32\u02ac\n\32\f\32\16\32\u02af\13"+
		"\32\3\33\3\33\3\34\3\34\3\34\3\34\5\34\u02b7\n\34\5\34\u02b9\n\34\3\35"+
		"\3\35\3\36\5\36\u02be\n\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36\3\36"+
		"\3\36\3\36\3\36\3\37\3\37\5\37\u02ce\n\37\3\37\3\37\7\37\u02d2\n\37\f"+
		"\37\16\37\u02d5\13\37\3\37\3\37\5\37\u02d9\n\37\3\37\3\37\5\37\u02dd\n"+
		"\37\3\37\7\37\u02e0\n\37\f\37\16\37\u02e3\13\37\3\37\3\37\3 \3 \5 \u02e9"+
		"\n \3!\3!\3!\5!\u02ee\n!\3!\3!\5!\u02f2\n!\3!\7!\u02f5\n!\f!\16!\u02f8"+
		"\13!\5!\u02fa\n!\3\"\3\"\3\"\3\"\3#\5#\u0301\n#\3#\5#\u0304\n#\3#\3#\7"+
		"#\u0308\n#\f#\16#\u030b\13#\3#\5#\u030e\n#\3#\5#\u0311\n#\5#\u0313\n#"+
		"\3$\3$\3$\3$\3$\3$\3$\3$\3%\3%\5%\u031f\n%\3%\3%\3%\3%\3%\3%\3%\3%\3%"+
		"\3%\3%\3%\3%\2\3\16&\2\4\6\b\n\f\16\20\22\24\26\30\32\34\36 \"$&(*,.\60"+
		"\62\64\668:<>@BDFH\2\b\3\2;<\3\3;;\3\2\n\13\3\2\r\16\3\2\r\20\3\2\21\26"+
		"\2\u03a0\2M\3\2\2\2\4\u00b0\3\2\2\2\6\u00d3\3\2\2\2\b\u00d5\3\2\2\2\n"+
		"\u00ee\3\2\2\2\f\u00f0\3\2\2\2\16\u0148\3\2\2\2\20\u0176\3\2\2\2\22\u0192"+
		"\3\2\2\2\24\u0194\3\2\2\2\26\u0199\3\2\2\2\30\u01a2\3\2\2\2\32\u01a4\3"+
		"\2\2\2\34\u01bc\3\2\2\2\36\u01cf\3\2\2\2 \u01d7\3\2\2\2\"\u01d9\3\2\2"+
		"\2$\u01ea\3\2\2\2&\u01ee\3\2\2\2(\u0205\3\2\2\2*\u0207\3\2\2\2,\u021c"+
		"\3\2\2\2.\u0278\3\2\2\2\60\u0293\3\2\2\2\62\u02a2\3\2\2\2\64\u02b0\3\2"+
		"\2\2\66\u02b2\3\2\2\28\u02ba\3\2\2\2:\u02bd\3\2\2\2<\u02cb\3\2\2\2>\u02e8"+
		"\3\2\2\2@\u02f9\3\2\2\2B\u02fb\3\2\2\2D\u0312\3\2\2\2F\u0314\3\2\2\2H"+
		"\u031e\3\2\2\2JL\t\2\2\2KJ\3\2\2\2LO\3\2\2\2MK\3\2\2\2MN\3\2\2\2N]\3\2"+
		"\2\2OM\3\2\2\2PR\5F$\2QS\7<\2\2RQ\3\2\2\2RS\3\2\2\2ST\3\2\2\2TX\7;\2\2"+
		"UW\t\2\2\2VU\3\2\2\2WZ\3\2\2\2XV\3\2\2\2XY\3\2\2\2Y\\\3\2\2\2ZX\3\2\2"+
		"\2[P\3\2\2\2\\_\3\2\2\2][\3\2\2\2]^\3\2\2\2^c\3\2\2\2_]\3\2\2\2`b\t\2"+
		"\2\2a`\3\2\2\2be\3\2\2\2ca\3\2\2\2cd\3\2\2\2ds\3\2\2\2ec\3\2\2\2fh\5H"+
		"%\2gi\7<\2\2hg\3\2\2\2hi\3\2\2\2ij\3\2\2\2jn\7;\2\2km\t\2\2\2lk\3\2\2"+
		"\2mp\3\2\2\2nl\3\2\2\2no\3\2\2\2or\3\2\2\2pn\3\2\2\2qf\3\2\2\2ru\3\2\2"+
		"\2sq\3\2\2\2st\3\2\2\2tw\3\2\2\2us\3\2\2\2vx\7<\2\2wv\3\2\2\2wx\3\2\2"+
		"\2xy\3\2\2\2y{\5\4\3\2z|\7<\2\2{z\3\2\2\2{|\3\2\2\2|}\3\2\2\2}\u0081\7"+
		";\2\2~\u0080\t\2\2\2\177~\3\2\2\2\u0080\u0083\3\2\2\2\u0081\177\3\2\2"+
		"\2\u0081\u0082\3\2\2\2\u0082\u0091\3\2\2\2\u0083\u0081\3\2\2\2\u0084\u0086"+
		"\5H%\2\u0085\u0087\7<\2\2\u0086\u0085\3\2\2\2\u0086\u0087\3\2\2\2\u0087"+
		"\u0088\3\2\2\2\u0088\u008c\t\3\2\2\u0089\u008b\t\2\2\2\u008a\u0089\3\2"+
		"\2\2\u008b\u008e\3\2\2\2\u008c\u008a\3\2\2\2\u008c\u008d\3\2\2\2\u008d"+
		"\u0090\3\2\2\2\u008e\u008c\3\2\2\2\u008f\u0084\3\2\2\2\u0090\u0093\3\2"+
		"\2\2\u0091\u008f\3\2\2\2\u0091\u0092\3\2\2\2\u0092\u0097\3\2\2\2\u0093"+
		"\u0091\3\2\2\2\u0094\u0096\t\2\2\2\u0095\u0094\3\2\2\2\u0096\u0099\3\2"+
		"\2\2\u0097\u0095\3\2\2\2\u0097\u0098\3\2\2\2\u0098\u00a7\3\2\2\2\u0099"+
		"\u0097\3\2\2\2\u009a\u009c\5<\37\2\u009b\u009d\7<\2\2\u009c\u009b\3\2"+
		"\2\2\u009c\u009d\3\2\2\2\u009d\u009e\3\2\2\2\u009e\u00a2\7;\2\2\u009f"+
		"\u00a1\t\2\2\2\u00a0\u009f\3\2\2\2\u00a1\u00a4\3\2\2\2\u00a2\u00a0\3\2"+
		"\2\2\u00a2\u00a3\3\2\2\2\u00a3\u00a6\3\2\2\2\u00a4\u00a2\3\2\2\2\u00a5"+
		"\u009a\3\2\2\2\u00a6\u00a9\3\2\2\2\u00a7\u00a5\3\2\2\2\u00a7\u00a8\3\2"+
		"\2\2\u00a8\u00ad\3\2\2\2\u00a9\u00a7\3\2\2\2\u00aa\u00ac\t\2\2\2\u00ab"+
		"\u00aa\3\2\2\2\u00ac\u00af\3\2\2\2\u00ad\u00ab\3\2\2\2\u00ad\u00ae\3\2"+
		"\2\2\u00ae\3\3\2\2\2\u00af\u00ad\3\2\2\2\u00b0\u00b2\7\3\2\2\u00b1\u00b3"+
		"\7<\2\2\u00b2\u00b1\3\2\2\2\u00b2\u00b3\3\2\2\2\u00b3\u00b4\3\2\2\2\u00b4"+
		"\u00b8\7;\2\2\u00b5\u00b7\t\2\2\2\u00b6\u00b5\3\2\2\2\u00b7\u00ba\3\2"+
		"\2\2\u00b8\u00b6\3\2\2\2\u00b8\u00b9\3\2\2\2\u00b9\u00c8\3\2\2\2\u00ba"+
		"\u00b8\3\2\2\2\u00bb\u00bd\5\n\6\2\u00bc\u00be\7<\2\2\u00bd\u00bc\3\2"+
		"\2\2\u00bd\u00be\3\2\2\2\u00be\u00bf\3\2\2\2\u00bf\u00c3\7;\2\2\u00c0"+
		"\u00c2\t\2\2\2\u00c1\u00c0\3\2\2\2\u00c2\u00c5\3\2\2\2\u00c3\u00c1\3\2"+
		"\2\2\u00c3\u00c4\3\2\2\2\u00c4\u00c7\3\2\2\2\u00c5\u00c3\3\2\2\2\u00c6"+
		"\u00bb\3\2\2\2\u00c7\u00ca\3\2\2\2\u00c8\u00c6\3\2\2\2\u00c8\u00c9\3\2"+
		"\2\2\u00c9\u00ce\3\2\2\2\u00ca\u00c8\3\2\2\2\u00cb\u00cd\t\2\2\2\u00cc"+
		"\u00cb\3\2\2\2\u00cd\u00d0\3\2\2\2\u00ce\u00cc\3\2\2\2\u00ce\u00cf\3\2"+
		"\2\2\u00cf\u00d1\3\2\2\2\u00d0\u00ce\3\2\2\2\u00d1\u00d2\7\4\2\2\u00d2"+
		"\5\3\2\2\2\u00d3\u00d4\7\67\2\2\u00d4\7\3\2\2\2\u00d5\u00d6\7\67\2\2\u00d6"+
		"\t\3\2\2\2\u00d7\u00d8\58\35\2\u00d8\u00d9\7<\2\2\u00d9\u00da\7\5\2\2"+
		"\u00da\u00db\7<\2\2\u00db\u00dc\5\6\4\2\u00dc\u00ef\3\2\2\2\u00dd\u00de"+
		"\7\6\2\2\u00de\u00e0\7<\2\2\u00df\u00dd\3\2\2\2\u00df\u00e0\3\2\2\2\u00e0"+
		"\u00e1\3\2\2\2\u00e1\u00e2\58\35\2\u00e2\u00e3\7<\2\2\u00e3\u00ec\5\6"+
		"\4\2\u00e4\u00e6\7<\2\2\u00e5\u00e4\3\2\2\2\u00e5\u00e6\3\2\2\2\u00e6"+
		"\u00e7\3\2\2\2\u00e7\u00e9\7\7\2\2\u00e8\u00ea\7<\2\2\u00e9\u00e8\3\2"+
		"\2\2\u00e9\u00ea\3\2\2\2\u00ea\u00eb\3\2\2\2\u00eb\u00ed\5\16\b\2\u00ec"+
		"\u00e5\3\2\2\2\u00ec\u00ed\3\2\2\2\u00ed\u00ef\3\2\2\2\u00ee\u00d7\3\2"+
		"\2\2\u00ee\u00df\3\2\2\2\u00ef\13\3\2\2\2\u00f0\u00f2\7\b\2\2\u00f1\u00f3"+
		"\7<\2\2\u00f2\u00f1\3\2\2\2\u00f2\u00f3\3\2\2\2\u00f3\u00f4\3\2\2\2\u00f4"+
		"\u00f6\5\16\b\2\u00f5\u00f7\7<\2\2\u00f6\u00f5\3\2\2\2\u00f6\u00f7\3\2"+
		"\2\2\u00f7\u00f8\3\2\2\2\u00f8\u00f9\7\t\2\2\u00f9\r\3\2\2\2\u00fa\u00fb"+
		"\b\b\1\2\u00fb\u00fd\7\b\2\2\u00fc\u00fe\7<\2\2\u00fd\u00fc\3\2\2\2\u00fd"+
		"\u00fe\3\2\2\2\u00fe\u00ff\3\2\2\2\u00ff\u0101\5\16\b\2\u0100\u0102\7"+
		"<\2\2\u0101\u0100\3\2\2\2\u0101\u0102\3\2\2\2\u0102\u0103\3\2\2\2\u0103"+
		"\u0105\7\t\2\2\u0104\u0106\7<\2\2\u0105\u0104\3\2\2\2\u0105\u0106\3\2"+
		"\2\2\u0106\u0107\3\2\2\2\u0107\u0109\t\4\2\2\u0108\u010a\7<\2\2\u0109"+
		"\u0108\3\2\2\2\u0109\u010a\3\2\2\2\u010a\u010b\3\2\2\2\u010b\u010d\7\b"+
		"\2\2\u010c\u010e\7<\2\2\u010d\u010c\3\2\2\2\u010d\u010e\3\2\2\2\u010e"+
		"\u010f\3\2\2\2\u010f\u0111\5\16\b\2\u0110\u0112\7<\2\2\u0111\u0110\3\2"+
		"\2\2\u0111\u0112\3\2\2\2\u0112\u0113\3\2\2\2\u0113\u0114\7\t\2\2\u0114"+
		"\u0149\3\2\2\2\u0115\u0117\7\b\2\2\u0116\u0118\7<\2\2\u0117\u0116\3\2"+
		"\2\2\u0117\u0118\3\2\2\2\u0118\u0119\3\2\2\2\u0119\u011b\5\16\b\2\u011a"+
		"\u011c\7<\2\2\u011b\u011a\3\2\2\2\u011b\u011c\3\2\2\2\u011c\u011d\3\2"+
		"\2\2\u011d\u011f\7\t\2\2\u011e\u0120\7<\2\2\u011f\u011e\3\2\2\2\u011f"+
		"\u0120\3\2\2\2\u0120\u0121\3\2\2\2\u0121\u0122\t\4\2\2\u0122\u0123\7<"+
		"\2\2\u0123\u0124\5\16\b\r\u0124\u0149\3\2\2\2\u0125\u0132\7\f\2\2\u0126"+
		"\u0127\7<\2\2\u0127\u0133\5\16\b\2\u0128\u012a\7\b\2\2\u0129\u012b\7<"+
		"\2\2\u012a\u0129\3\2\2\2\u012a\u012b\3\2\2\2\u012b\u012c\3\2\2\2\u012c"+
		"\u012e\5\16\b\2\u012d\u012f\7<\2\2\u012e\u012d\3\2\2\2\u012e\u012f\3\2"+
		"\2\2\u012f\u0130\3\2\2\2\u0130\u0131\7\t\2\2\u0131\u0133\3\2\2\2\u0132"+
		"\u0126\3\2\2\2\u0132\u0128\3\2\2\2\u0133\u0149\3\2\2\2\u0134\u0149\5\30"+
		"\r\2\u0135\u0137\t\5\2\2\u0136\u0138\7<\2\2\u0137\u0136\3\2\2\2\u0137"+
		"\u0138\3\2\2\2\u0138\u0139\3\2\2\2\u0139\u0149\5\16\b\t\u013a\u0149\5"+
		"\20\t\2\u013b\u0149\5\6\4\2\u013c\u0149\5\24\13\2\u013d\u0149\5\26\f\2"+
		"\u013e\u0140\7\b\2\2\u013f\u0141\7<\2\2\u0140\u013f\3\2\2\2\u0140\u0141"+
		"\3\2\2\2\u0141\u0142\3\2\2\2\u0142\u0144\5\16\b\2\u0143\u0145\7<\2\2\u0144"+
		"\u0143\3\2\2\2\u0144\u0145\3\2\2\2\u0145\u0146\3\2\2\2\u0146\u0147\7\t"+
		"\2\2\u0147\u0149\3\2\2\2\u0148\u00fa\3\2\2\2\u0148\u0115\3\2\2\2\u0148"+
		"\u0125\3\2\2\2\u0148\u0134\3\2\2\2\u0148\u0135\3\2\2\2\u0148\u013a\3\2"+
		"\2\2\u0148\u013b\3\2\2\2\u0148\u013c\3\2\2\2\u0148\u013d\3\2\2\2\u0148"+
		"\u013e\3\2\2\2\u0149\u0173\3\2\2\2\u014a\u014b\f\20\2\2\u014b\u014c\7"+
		"<\2\2\u014c\u014d\t\4\2\2\u014d\u014e\7<\2\2\u014e\u0172\5\16\b\21\u014f"+
		"\u0151\f\n\2\2\u0150\u0152\7<\2\2\u0151\u0150\3\2\2\2\u0151\u0152\3\2"+
		"\2\2\u0152\u0153\3\2\2\2\u0153\u0155\t\6\2\2\u0154\u0156\7<\2\2\u0155"+
		"\u0154\3\2\2\2\u0155\u0156\3\2\2\2\u0156\u0157\3\2\2\2\u0157\u0172\5\16"+
		"\b\13\u0158\u015a\f\b\2\2\u0159\u015b\7<\2\2\u015a\u0159\3\2\2\2\u015a"+
		"\u015b\3\2\2\2\u015b\u015c\3\2\2\2\u015c\u015e\t\7\2\2\u015d\u015f\7<"+
		"\2\2\u015e\u015d\3\2\2\2\u015e\u015f\3\2\2\2\u015f\u0160\3\2\2\2\u0160"+
		"\u0172\5\16\b\t\u0161\u0162\f\16\2\2\u0162\u0163\7<\2\2\u0163\u0165\t"+
		"\4\2\2\u0164\u0166\7<\2\2\u0165\u0164\3\2\2\2\u0165\u0166\3\2\2\2\u0166"+
		"\u0167\3\2\2\2\u0167\u0169\7\b\2\2\u0168\u016a\7<\2\2\u0169\u0168\3\2"+
		"\2\2\u0169\u016a\3\2\2\2\u016a\u016b\3\2\2\2\u016b\u016d\5\16\b\2\u016c"+
		"\u016e\7<\2\2\u016d\u016c\3\2\2\2\u016d\u016e\3\2\2\2\u016e\u016f\3\2"+
		"\2\2\u016f\u0170\7\t\2\2\u0170\u0172\3\2\2\2\u0171\u014a\3\2\2\2\u0171"+
		"\u014f\3\2\2\2\u0171\u0158\3\2\2\2\u0171\u0161\3\2\2\2\u0172\u0175\3\2"+
		"\2\2\u0173\u0171\3\2\2\2\u0173\u0174\3\2\2\2\u0174\17\3\2\2\2\u0175\u0173"+
		"\3\2\2\2\u0176\u0178\5\b\5\2\u0177\u0179\7<\2\2\u0178\u0177\3\2\2\2\u0178"+
		"\u0179\3\2\2\2\u0179\u017a\3\2\2\2\u017a\u017c\7\b\2\2\u017b\u017d\7<"+
		"\2\2\u017c\u017b\3\2\2\2\u017c\u017d\3\2\2\2\u017d\u017e\3\2\2\2\u017e"+
		"\u0180\5\22\n\2\u017f\u0181\7<\2\2\u0180\u017f\3\2\2\2\u0180\u0181\3\2"+
		"\2\2\u0181\u0182\3\2\2\2\u0182\u0183\7\t\2\2\u0183\21\3\2\2\2\u0184\u018f"+
		"\5\16\b\2\u0185\u0187\7<\2\2\u0186\u0185\3\2\2\2\u0186\u0187\3\2\2\2\u0187"+
		"\u0188\3\2\2\2\u0188\u018a\7:\2\2\u0189\u018b\7<\2\2\u018a\u0189\3\2\2"+
		"\2\u018a\u018b\3\2\2\2\u018b\u018c\3\2\2\2\u018c\u018e\5\16\b\2\u018d"+
		"\u0186\3\2\2\2\u018e\u0191\3\2\2\2\u018f\u018d\3\2\2\2\u018f\u0190\3\2"+
		"\2\2\u0190\u0193\3\2\2\2\u0191\u018f\3\2\2\2\u0192\u0184\3\2\2\2\u0192"+
		"\u0193\3\2\2\2\u0193\23\3\2\2\2\u0194\u0195\5\6\4\2\u0195\u0196\7\27\2"+
		"\2\u0196\u0197\5\16\b\2\u0197\u0198\7\30\2\2\u0198\25\3\2\2\2\u0199\u019a"+
		"\7\31\2\2\u019a\u019b\7<\2\2\u019b\u019c\5\b\5\2\u019c\27\3\2\2\2\u019d"+
		"\u01a3\7-\2\2\u019e\u01a3\7.\2\2\u019f\u01a3\7\63\2\2\u01a0\u01a3\7\64"+
		"\2\2\u01a1\u01a3\5\26\f\2\u01a2\u019d\3\2\2\2\u01a2\u019e\3\2\2\2\u01a2"+
		"\u019f\3\2\2\2\u01a2\u01a0\3\2\2\2\u01a2\u01a1\3\2\2\2\u01a3\31\3\2\2"+
		"\2\u01a4\u01ba\7\32\2\2\u01a5\u01a6\7<\2\2\u01a6\u01a7\58\35\2\u01a7\u01a8"+
		"\7<\2\2\u01a8\u01a9\7\5\2\2\u01a9\u01aa\7<\2\2\u01aa\u01ab\5\6\4\2\u01ab"+
		"\u01bb\3\2\2\2\u01ac\u01ad\7<\2\2\u01ad\u01ae\58\35\2\u01ae\u01af\7<\2"+
		"\2\u01af\u01b8\5\6\4\2\u01b0\u01b2\7<\2\2\u01b1\u01b0\3\2\2\2\u01b1\u01b2"+
		"\3\2\2\2\u01b2\u01b3\3\2\2\2\u01b3\u01b5\7\7\2\2\u01b4\u01b6\7<\2\2\u01b5"+
		"\u01b4\3\2\2\2\u01b5\u01b6\3\2\2\2\u01b6\u01b7\3\2\2\2\u01b7\u01b9\5\16"+
		"\b\2\u01b8\u01b1\3\2\2\2\u01b8\u01b9\3\2\2\2\u01b9\u01bb\3\2\2\2\u01ba"+
		"\u01a5\3\2\2\2\u01ba\u01ac\3\2\2\2\u01bb\33\3\2\2\2\u01bc\u01ca\5\32\16"+
		"\2\u01bd\u01bf\7<\2\2\u01be\u01bd\3\2\2\2\u01be\u01bf\3\2\2\2\u01bf\u01c0"+
		"\3\2\2\2\u01c0\u01c4\7;\2\2\u01c1\u01c3\t\2\2\2\u01c2\u01c1\3\2\2\2\u01c3"+
		"\u01c6\3\2\2\2\u01c4\u01c2\3\2\2\2\u01c4\u01c5\3\2\2\2\u01c5\u01c7\3\2"+
		"\2\2\u01c6\u01c4\3\2\2\2\u01c7\u01c9\5\32\16\2\u01c8\u01be\3\2\2\2\u01c9"+
		"\u01cc\3\2\2\2\u01ca\u01c8\3\2\2\2\u01ca\u01cb\3\2\2\2\u01cb\35\3\2\2"+
		"\2\u01cc\u01ca\3\2\2\2\u01cd\u01d0\5$\23\2\u01ce\u01d0\5,\27\2\u01cf\u01cd"+
		"\3\2\2\2\u01cf\u01ce\3\2\2\2\u01d0\37\3\2\2\2\u01d1\u01d8\5$\23\2\u01d2"+
		"\u01d8\5&\24\2\u01d3\u01d8\5,\27\2\u01d4\u01d8\5.\30\2\u01d5\u01d8\5\60"+
		"\31\2\u01d6\u01d8\5\66\34\2\u01d7\u01d1\3\2\2\2\u01d7\u01d2\3\2\2\2\u01d7"+
		"\u01d3\3\2\2\2\u01d7\u01d4\3\2\2\2\u01d7\u01d5\3\2\2\2\u01d7\u01d6\3\2"+
		"\2\2\u01d8!\3\2\2\2\u01d9\u01e7\5 \21\2\u01da\u01dc\7<\2\2\u01db\u01da"+
		"\3\2\2\2\u01db\u01dc\3\2\2\2\u01dc\u01dd\3\2\2\2\u01dd\u01e1\7;\2\2\u01de"+
		"\u01e0\t\2\2\2\u01df\u01de\3\2\2\2\u01e0\u01e3\3\2\2\2\u01e1\u01df\3\2"+
		"\2\2\u01e1\u01e2\3\2\2\2\u01e2\u01e4\3\2\2\2\u01e3\u01e1\3\2\2\2\u01e4"+
		"\u01e6\5 \21\2\u01e5\u01db\3\2\2\2\u01e6\u01e9\3\2\2\2\u01e7\u01e5\3\2"+
		"\2\2\u01e7\u01e8\3\2\2\2\u01e8#\3\2\2\2\u01e9\u01e7\3\2\2\2\u01ea\u01eb"+
		"\7\33\2\2\u01eb\u01ec\7<\2\2\u01ec\u01ed\5\20\t\2\u01ed%\3\2\2\2\u01ee"+
		"\u01ef\7\34\2\2\u01ef\u01f0\7<\2\2\u01f0\u01f8\5\6\4\2\u01f1\u01f3\7<"+
		"\2\2\u01f2\u01f1\3\2\2\2\u01f2\u01f3\3\2\2\2\u01f3\u01f4\3\2\2\2\u01f4"+
		"\u01f5\7\27\2\2\u01f5\u01f6\5\16\b\2\u01f6\u01f7\7\30\2\2\u01f7\u01f9"+
		"\3\2\2\2\u01f8\u01f2\3\2\2\2\u01f8\u01f9\3\2\2\2\u01f9\u01fb\3\2\2\2\u01fa"+
		"\u01fc\7<\2\2\u01fb\u01fa\3\2\2\2\u01fb\u01fc\3\2\2\2\u01fc\u01fd\3\2"+
		"\2\2\u01fd\u01ff\7\7\2\2\u01fe\u0200\7<\2\2\u01ff\u01fe\3\2\2\2\u01ff"+
		"\u0200\3\2\2\2\u0200\u0201\3\2\2\2\u0201\u0202\5\16\b\2\u0202\'\3\2\2"+
		"\2\u0203\u0206\5\f\7\2\u0204\u0206\5\16\b\2\u0205\u0203\3\2\2\2\u0205"+
		"\u0204\3\2\2\2\u0206)\3\2\2\2\u0207\u0209\7\35\2\2\u0208\u020a\7<\2\2"+
		"\u0209\u0208\3\2\2\2\u0209\u020a\3\2\2\2\u020a\u020b\3\2\2\2\u020b\u020d"+
		"\5(\25\2\u020c\u020e\7<\2\2\u020d\u020c\3\2\2\2\u020d\u020e\3\2\2\2\u020e"+
		"\u020f\3\2\2\2\u020f\u0211\7\36\2\2\u0210\u0212\7<\2\2\u0211\u0210\3\2"+
		"\2\2\u0211\u0212\3\2\2\2\u0212\u0213\3\2\2\2\u0213\u0217\7;\2\2\u0214"+
		"\u0216\t\2\2\2\u0215\u0214\3\2\2\2\u0216\u0219\3\2\2\2\u0217\u0215\3\2"+
		"\2\2\u0217\u0218\3\2\2\2\u0218\u021a\3\2\2\2\u0219\u0217\3\2\2\2\u021a"+
		"\u021b\7\37\2\2\u021b+\3\2\2\2\u021c\u021e\7\35\2\2\u021d\u021f\7<\2\2"+
		"\u021e\u021d\3\2\2\2\u021e\u021f\3\2\2\2\u021f\u0220\3\2\2\2\u0220\u0222"+
		"\5(\25\2\u0221\u0223\7<\2\2\u0222\u0221\3\2\2\2\u0222\u0223\3\2\2\2\u0223"+
		"\u0224\3\2\2\2\u0224\u0226\7\36\2\2\u0225\u0227\7<\2\2\u0226\u0225\3\2"+
		"\2\2\u0226\u0227\3\2\2\2\u0227\u0228\3\2\2\2\u0228\u022c\7;\2\2\u0229"+
		"\u022b\t\2\2\2\u022a\u0229\3\2\2\2\u022b\u022e\3\2\2\2\u022c\u022a\3\2"+
		"\2\2\u022c\u022d\3\2\2\2\u022d\u0230\3\2\2\2\u022e\u022c\3\2\2\2\u022f"+
		"\u0231\5\"\22\2\u0230\u022f\3\2\2\2\u0230\u0231\3\2\2\2\u0231\u0235\3"+
		"\2\2\2\u0232\u0234\t\2\2\2\u0233\u0232\3\2\2\2\u0234\u0237\3\2\2\2\u0235"+
		"\u0233\3\2\2\2\u0235\u0236\3\2\2\2\u0236\u0257\3\2\2\2\u0237\u0235\3\2"+
		"\2\2\u0238\u023c\7 \2\2\u0239\u023d\5\f\7\2\u023a\u023b\7<\2\2\u023b\u023d"+
		"\5\16\b\2\u023c\u0239\3\2\2\2\u023c\u023a\3\2\2\2\u023d\u023f\3\2\2\2"+
		"\u023e\u0240\7<\2\2\u023f\u023e\3\2\2\2\u023f\u0240\3\2\2\2\u0240\u0241"+
		"\3\2\2\2\u0241\u0243\7\36\2\2\u0242\u0244\7<\2\2\u0243\u0242\3\2\2\2\u0243"+
		"\u0244\3\2\2\2\u0244\u0245\3\2\2\2\u0245\u0249\7;\2\2\u0246\u0248\t\2"+
		"\2\2\u0247\u0246\3\2\2\2\u0248\u024b\3\2\2\2\u0249\u0247\3\2\2\2\u0249"+
		"\u024a\3\2\2\2\u024a\u024d\3\2\2\2\u024b\u0249\3\2\2\2\u024c\u024e\5\""+
		"\22\2\u024d\u024c\3\2\2\2\u024d\u024e\3\2\2\2\u024e\u0252\3\2\2\2\u024f"+
		"\u0251\t\2\2\2\u0250\u024f\3\2\2\2\u0251\u0254\3\2\2\2\u0252\u0250\3\2"+
		"\2\2\u0252\u0253\3\2\2\2\u0253\u0256\3\2\2\2\u0254\u0252\3\2\2\2\u0255"+
		"\u0238\3\2\2\2\u0256\u0259\3\2\2\2\u0257\u0255\3\2\2\2\u0257\u0258\3\2"+
		"\2\2\u0258\u026e\3\2\2\2\u0259\u0257\3\2\2\2\u025a\u025c\7!\2\2\u025b"+
		"\u025d\7<\2\2\u025c\u025b\3\2\2\2\u025c\u025d\3\2\2\2\u025d\u025e\3\2"+
		"\2\2\u025e\u0262\7;\2\2\u025f\u0261\t\2\2\2\u0260\u025f\3\2\2\2\u0261"+
		"\u0264\3\2\2\2\u0262\u0260\3\2\2\2\u0262\u0263\3\2\2\2\u0263\u0266\3\2"+
		"\2\2\u0264\u0262\3\2\2\2\u0265\u0267\5\"\22\2\u0266\u0265\3\2\2\2\u0266"+
		"\u0267\3\2\2\2\u0267\u026b\3\2\2\2\u0268\u026a\t\2\2\2\u0269\u0268\3\2"+
		"\2\2\u026a\u026d\3\2\2\2\u026b\u0269\3\2\2\2\u026b\u026c\3\2\2\2\u026c"+
		"\u026f\3\2\2\2\u026d\u026b\3\2\2\2\u026e\u025a\3\2\2\2\u026e\u026f\3\2"+
		"\2\2\u026f\u0273\3\2\2\2\u0270\u0272\t\2\2\2\u0271\u0270\3\2\2\2\u0272"+
		"\u0275\3\2\2\2\u0273\u0271\3\2\2\2\u0273\u0274\3\2\2\2\u0274\u0276\3\2"+
		"\2\2\u0275\u0273\3\2\2\2\u0276\u0277\7\37\2\2\u0277-\3\2\2\2\u0278\u027a"+
		"\7\"\2\2\u0279\u027b\7<\2\2\u027a\u0279\3\2\2\2\u027a\u027b\3\2\2\2\u027b"+
		"\u027c\3\2\2\2\u027c\u0280\7;\2\2\u027d\u027f\t\2\2\2\u027e\u027d\3\2"+
		"\2\2\u027f\u0282\3\2\2\2\u0280\u027e\3\2\2\2\u0280\u0281\3\2\2\2\u0281"+
		"\u0289\3\2\2\2\u0282\u0280\3\2\2\2\u0283\u0285\5\62\32\2\u0284\u0286\7"+
		"<\2\2\u0285\u0284\3\2\2\2\u0285\u0286\3\2\2\2\u0286\u0287\3\2\2\2\u0287"+
		"\u0288\7;\2\2\u0288\u028a\3\2\2\2\u0289\u0283\3\2\2\2\u0289\u028a\3\2"+
		"\2\2\u028a\u028e\3\2\2\2\u028b\u028d\t\2\2\2\u028c\u028b\3\2\2\2\u028d"+
		"\u0290\3\2\2\2\u028e\u028c\3\2\2\2\u028e\u028f\3\2\2\2\u028f\u0291\3\2"+
		"\2\2\u0290\u028e\3\2\2\2\u0291\u0292\7#\2\2\u0292/\3\2\2\2\u0293\u02a0"+
		"\7$\2\2\u0294\u0295\7<\2\2\u0295\u02a1\5\16\b\2\u0296\u0298\7\b\2\2\u0297"+
		"\u0299\7<\2\2\u0298\u0297\3\2\2\2\u0298\u0299\3\2\2\2\u0299\u029a\3\2"+
		"\2\2\u029a\u029c\5\16\b\2\u029b\u029d\7<\2\2\u029c\u029b\3\2\2\2\u029c"+
		"\u029d\3\2\2\2\u029d\u029e\3\2\2\2\u029e\u029f\7\t\2\2\u029f\u02a1\3\2"+
		"\2\2\u02a0\u0294\3\2\2\2\u02a0\u0296\3\2\2\2\u02a1\61\3\2\2\2\u02a2\u02ad"+
		"\5\64\33\2\u02a3\u02a7\7;\2\2\u02a4\u02a6\t\2\2\2\u02a5\u02a4\3\2\2\2"+
		"\u02a6\u02a9\3\2\2\2\u02a7\u02a5\3\2\2\2\u02a7\u02a8\3\2\2\2\u02a8\u02aa"+
		"\3\2\2\2\u02a9\u02a7\3\2\2\2\u02aa\u02ac\5\64\33\2\u02ab\u02a3\3\2\2\2"+
		"\u02ac\u02af\3\2\2\2\u02ad\u02ab\3\2\2\2\u02ad\u02ae\3\2\2\2\u02ae\63"+
		"\3\2\2\2\u02af\u02ad\3\2\2\2\u02b0\u02b1\5\"\22\2\u02b1\65\3\2\2\2\u02b2"+
		"\u02b8\7%\2\2\u02b3\u02b9\5\f\7\2\u02b4\u02b5\7<\2\2\u02b5\u02b7\5\16"+
		"\b\2\u02b6\u02b4\3\2\2\2\u02b6\u02b7\3\2\2\2\u02b7\u02b9\3\2\2\2\u02b8"+
		"\u02b3\3\2\2\2\u02b8\u02b6\3\2\2\2\u02b9\67\3\2\2\2\u02ba\u02bb\7\67\2"+
		"\2\u02bb9\3\2\2\2\u02bc\u02be\7\6\2\2\u02bd\u02bc\3\2\2\2\u02bd\u02be"+
		"\3\2\2\2\u02be\u02bf\3\2\2\2\u02bf\u02c0\7\31\2\2\u02c0\u02c1\7<\2\2\u02c1"+
		"\u02c2\7\67\2\2\u02c2\u02c3\7<\2\2\u02c3\u02c4\7&\2\2\u02c4\u02c5\7<\2"+
		"\2\u02c5\u02c6\5@!\2\u02c6\u02c7\7<\2\2\u02c7\u02c8\7\'\2\2\u02c8\u02c9"+
		"\7<\2\2\u02c9\u02ca\5> \2\u02ca;\3\2\2\2\u02cb\u02cd\5:\36\2\u02cc\u02ce"+
		"\7<\2\2\u02cd\u02cc\3\2\2\2\u02cd\u02ce\3\2\2\2\u02ce\u02cf\3\2\2\2\u02cf"+
		"\u02d3\7;\2\2\u02d0\u02d2\t\2\2\2\u02d1\u02d0\3\2\2\2\u02d2\u02d5\3\2"+
		"\2\2\u02d3\u02d1\3\2\2\2\u02d3\u02d4\3\2\2\2\u02d4\u02dc\3\2\2\2\u02d5"+
		"\u02d3\3\2\2\2\u02d6\u02d8\5D#\2\u02d7\u02d9\7<\2\2\u02d8\u02d7\3\2\2"+
		"\2\u02d8\u02d9\3\2\2\2\u02d9\u02da\3\2\2\2\u02da\u02db\7;\2\2\u02db\u02dd"+
		"\3\2\2\2\u02dc\u02d6\3\2\2\2\u02dc\u02dd\3\2\2\2\u02dd\u02e1\3\2\2\2\u02de"+
		"\u02e0\t\2\2\2\u02df\u02de\3\2\2\2\u02e0\u02e3\3\2\2\2\u02e1\u02df\3\2"+
		"\2\2\u02e1\u02e2\3\2\2\2\u02e2\u02e4\3\2\2\2\u02e3\u02e1\3\2\2\2\u02e4"+
		"\u02e5\7(\2\2\u02e5=\3\2\2\2\u02e6\u02e9\7)\2\2\u02e7\u02e9\58\35\2\u02e8"+
		"\u02e6\3\2\2\2\u02e8\u02e7\3\2\2\2\u02e9?\3\2\2\2\u02ea\u02fa\7)\2\2\u02eb"+
		"\u02f6\5B\"\2\u02ec\u02ee\7<\2\2\u02ed\u02ec\3\2\2\2\u02ed\u02ee\3\2\2"+
		"\2\u02ee\u02ef\3\2\2\2\u02ef\u02f1\7:\2\2\u02f0\u02f2\7<\2\2\u02f1\u02f0"+
		"\3\2\2\2\u02f1\u02f2\3\2\2\2\u02f2\u02f3\3\2\2\2\u02f3\u02f5\5B\"\2\u02f4"+
		"\u02ed\3\2\2\2\u02f5\u02f8\3\2\2\2\u02f6\u02f4\3\2\2\2\u02f6\u02f7\3\2"+
		"\2\2\u02f7\u02fa\3\2\2\2\u02f8\u02f6\3\2\2\2\u02f9\u02ea\3\2\2\2\u02f9"+
		"\u02eb\3\2\2\2\u02faA\3\2\2\2\u02fb\u02fc\58\35\2\u02fc\u02fd\7<\2\2\u02fd"+
		"\u02fe\7\67\2\2\u02feC\3\2\2\2\u02ff\u0301\5\34\17\2\u0300\u02ff\3\2\2"+
		"\2\u0300\u0301\3\2\2\2\u0301\u030d\3\2\2\2\u0302\u0304\7<\2\2\u0303\u0302"+
		"\3\2\2\2\u0303\u0304\3\2\2\2\u0304\u0305\3\2\2\2\u0305\u0309\7;\2\2\u0306"+
		"\u0308\t\2\2\2\u0307\u0306\3\2\2\2\u0308\u030b\3\2\2\2\u0309\u0307\3\2"+
		"\2\2\u0309\u030a\3\2\2\2\u030a\u030c\3\2\2\2\u030b\u0309\3\2\2\2\u030c"+
		"\u030e\5\"\22\2\u030d\u0303\3\2\2\2\u030d\u030e\3\2\2\2\u030e\u0313\3"+
		"\2\2\2\u030f\u0311\5\"\22\2\u0310\u030f\3\2\2\2\u0310\u0311\3\2\2\2\u0311"+
		"\u0313\3\2\2\2\u0312\u0300\3\2\2\2\u0312\u0310\3\2\2\2\u0313E\3\2\2\2"+
		"\u0314\u0315\7*\2\2\u0315\u0316\7<\2\2\u0316\u0317\7\67\2\2\u0317\u0318"+
		"\7<\2\2\u0318\u0319\7+\2\2\u0319\u031a\7<\2\2\u031a\u031b\7\67\2\2\u031b"+
		"G\3\2\2\2\u031c\u031d\7\6\2\2\u031d\u031f\7<\2\2\u031e\u031c\3\2\2\2\u031e"+
		"\u031f\3\2\2\2\u031f\u0320\3\2\2\2\u0320\u0321\7,\2\2\u0321\u0322\7<\2"+
		"\2\u0322\u0323\7\67\2\2\u0323\u0324\7<\2\2\u0324\u0325\7&\2\2\u0325\u0326"+
		"\7<\2\2\u0326\u0327\5@!\2\u0327\u0328\7<\2\2\u0328\u0329\7\'\2\2\u0329"+
		"\u032a\7<\2\2\u032a\u032b\5> \2\u032bI\3\2\2\2\u0089MRX]chnsw{\u0081\u0086"+
		"\u008c\u0091\u0097\u009c\u00a2\u00a7\u00ad\u00b2\u00b8\u00bd\u00c3\u00c8"+
		"\u00ce\u00df\u00e5\u00e9\u00ec\u00ee\u00f2\u00f6\u00fd\u0101\u0105\u0109"+
		"\u010d\u0111\u0117\u011b\u011f\u012a\u012e\u0132\u0137\u0140\u0144\u0148"+
		"\u0151\u0155\u015a\u015e\u0165\u0169\u016d\u0171\u0173\u0178\u017c\u0180"+
		"\u0186\u018a\u018f\u0192\u01a2\u01b1\u01b5\u01b8\u01ba\u01be\u01c4\u01ca"+
		"\u01cf\u01d7\u01db\u01e1\u01e7\u01f2\u01f8\u01fb\u01ff\u0205\u0209\u020d"+
		"\u0211\u0217\u021e\u0222\u0226\u022c\u0230\u0235\u023c\u023f\u0243\u0249"+
		"\u024d\u0252\u0257\u025c\u0262\u0266\u026b\u026e\u0273\u027a\u0280\u0285"+
		"\u0289\u028e\u0298\u029c\u02a0\u02a7\u02ad\u02b6\u02b8\u02bd\u02cd\u02d3"+
		"\u02d8\u02dc\u02e1\u02e8\u02ed\u02f1\u02f6\u02f9\u0300\u0303\u0309\u030d"+
		"\u0310\u0312\u031e";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}