// Generated from grammar\FDF.g4 by ANTLR 4.5.3

	package net.moonlightflower.wc3libs.misc.antlr.out.grammar;

import org.antlr.v4.runtime.Lexer;
import org.antlr.v4.runtime.CharStream;
import org.antlr.v4.runtime.Token;
import org.antlr.v4.runtime.TokenStream;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.atn.*;
import org.antlr.v4.runtime.dfa.DFA;
import org.antlr.v4.runtime.misc.*;

@SuppressWarnings({"all", "warnings", "unchecked", "unused", "cast"})
public class FDFLexer extends Lexer {
	static { RuntimeMetaData.checkVersion("4.5.3", RuntimeMetaData.VERSION); }

	protected static final DFA[] _decisionToDFA;
	protected static final PredictionContextCache _sharedContextCache =
		new PredictionContextCache();
	public static final int
		T__0=1, CURLY_L=2, CURLY_R=3, COMMA=4, WS=5, ID=6, STRING_LITERAL=7;
	public static String[] modeNames = {
		"DEFAULT_MODE"
	};

	public static final String[] ruleNames = {
		"T__0", "CURLY_L", "CURLY_R", "COMMA", "WS", "ID", "ID_START", "ID_TAIL", 
		"STRING_LITERAL"
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


	public FDFLexer(CharStream input) {
		super(input);
		_interp = new LexerATNSimulator(this,_ATN,_decisionToDFA,_sharedContextCache);
	}

	@Override
	public String getGrammarFileName() { return "FDF.g4"; }

	@Override
	public String[] getRuleNames() { return ruleNames; }

	@Override
	public String getSerializedATN() { return _serializedATN; }

	@Override
	public String[] getModeNames() { return modeNames; }

	@Override
	public ATN getATN() { return _ATN; }

	public static final String _serializedATN =
		"\3\u0430\ud6d1\u8206\uad2d\u4417\uaef1\u8d80\uaadd\2\t?\b\1\4\2\t\2\4"+
		"\3\t\3\4\4\t\4\4\5\t\5\4\6\t\6\4\7\t\7\4\b\t\b\4\t\t\t\4\n\t\n\3\2\3\2"+
		"\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\2\3\3\3\3\3\4\3\4\3\5\3\5\3\6\3\6\3"+
		"\7\3\7\7\7+\n\7\f\7\16\7.\13\7\3\b\3\b\3\t\5\t\63\n\t\3\n\3\n\3\n\3\n"+
		"\7\n9\n\n\f\n\16\n<\13\n\3\n\3\n\2\2\13\3\3\5\4\7\5\t\6\13\7\r\b\17\2"+
		"\21\2\23\t\3\2\7\5\2\13\f\17\17\"\"\4\2C\\c|\6\2\62;C\\aac|\6\2\f\f\17"+
		"\17$$^^\4\2$$^^?\2\3\3\2\2\2\2\5\3\2\2\2\2\7\3\2\2\2\2\t\3\2\2\2\2\13"+
		"\3\2\2\2\2\r\3\2\2\2\2\23\3\2\2\2\3\25\3\2\2\2\5 \3\2\2\2\7\"\3\2\2\2"+
		"\t$\3\2\2\2\13&\3\2\2\2\r(\3\2\2\2\17/\3\2\2\2\21\62\3\2\2\2\23\64\3\2"+
		"\2\2\25\26\7U\2\2\26\27\7v\2\2\27\30\7t\2\2\30\31\7k\2\2\31\32\7p\2\2"+
		"\32\33\7i\2\2\33\34\7N\2\2\34\35\7k\2\2\35\36\7u\2\2\36\37\7v\2\2\37\4"+
		"\3\2\2\2 !\7}\2\2!\6\3\2\2\2\"#\7\177\2\2#\b\3\2\2\2$%\7.\2\2%\n\3\2\2"+
		"\2&\'\t\2\2\2\'\f\3\2\2\2(,\5\17\b\2)+\5\21\t\2*)\3\2\2\2+.\3\2\2\2,*"+
		"\3\2\2\2,-\3\2\2\2-\16\3\2\2\2.,\3\2\2\2/\60\t\3\2\2\60\20\3\2\2\2\61"+
		"\63\t\4\2\2\62\61\3\2\2\2\63\22\3\2\2\2\64:\7$\2\2\659\n\5\2\2\66\67\7"+
		"^\2\2\679\t\6\2\28\65\3\2\2\28\66\3\2\2\29<\3\2\2\2:8\3\2\2\2:;\3\2\2"+
		"\2;=\3\2\2\2<:\3\2\2\2=>\7$\2\2>\24\3\2\2\2\7\2,\628:\2";
	public static final ATN _ATN =
		new ATNDeserializer().deserialize(_serializedATN.toCharArray());
	static {
		_decisionToDFA = new DFA[_ATN.getNumberOfDecisions()];
		for (int i = 0; i < _ATN.getNumberOfDecisions(); i++) {
			_decisionToDFA[i] = new DFA(_ATN.getDecisionState(i), i);
		}
	}
}