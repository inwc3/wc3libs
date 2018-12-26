package net.moonlightflower.wc3libs.txt;

import com.sun.org.apache.xpath.internal.operations.Variable;
import net.moonlightflower.wc3libs.antlr.JassLexer;
import net.moonlightflower.wc3libs.antlr.JassParser;
import org.antlr.v4.runtime.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Jass {
	public final static File GAME_PATH = new File("war3map.j");

	public static class Script {
		public static class Expr {

		}

		public static class TypeDec {
			private String _name;
			private String _parent;

			public TypeDec(@Nonnull String name, @Nonnull String parent) {
				_name = name;
				_parent = parent;
			}
		}

		private List<TypeDec> _typeDecs = new ArrayList<>();

		public static class VarDecl {
			private boolean _isConstant;
			private String _type;
			private boolean _isArray;
			private String _name;
			private String _val;

			public VarDecl(boolean isConstant, @Nonnull String type, boolean isArray, @Nonnull String name, @Nonnull String val) {
				_isConstant = isConstant;
				_type = type;
				_isArray = isArray;
				_name = name;
				_val = val;
			}
		}

		private List<VarDecl> _globalVars = new ArrayList<>();

		public static class Param {
			private String _type;
			private String _name;

			public Param(@Nonnull String type, @Nonnull String name) {
				_type = type;
				_name = name;
			}
		}

		public static class FuncDecl {
			private boolean _isConstant;
			private String _name;
			private List<Param> _params;
			private String _returnType;

			public FuncDecl(boolean isConstant, @Nonnull String name, List<Param> params, @Nullable String returnType) {
				_isConstant = isConstant;
				_name = name;
				_params = params;
				_returnType = returnType;
			}
		}

		private List<FuncDecl> _nativeDecls = new ArrayList<>();

		public static class Func {
			private FuncDecl _decl;

			public abstract static class Statement {
				private String _stmt;
				private boolean _isDebug;

				public static Statement makeStatement(@Nonnull String stmt) {
					_stmt = stmt;
				}

				public static Statement makeStatement(@Nonnull JassParser.StatementContext stmt) {
					_stmt = stmt;

					_isDebug = (stmt.debug() != null);
				}
			}

			public static class SetVarStatement extends Statement {
				private String _name;
				private Expr _val;
			}

			public static class Body {
				private List<VarDecl> _localVars;
				private List<Statement> _stmts;

				public Body(@Nonnull List<VarDecl> localVars, @Nonnull List<Statement> stmts) {
					_localVars = localVars;
					_stmts = stmts;
				}
			}

			private Body _body;

			public final static String CONFIG_NAME = "config";
			public final static String MAIN_NAME = "main";

			public Func(@Nonnull FuncDecl decl, @Nonnull Body body) {
				_decl = decl;
				_body = body;
			}
		}

		private List<Func> _funcs = new ArrayList<>();

		public Script(@Nonnull JassParser.RootContext rootContext) {
			for (JassParser.TypeDecContext typeDecContext : rootContext.typeDec()) {
				String name = typeDecContext.name.getText();
				String parent = typeDecContext.parent.getText();

				_typeDecs.add(new TypeDec(name, parent));
			}

			JassParser.GlobalsBlockContext globalsBlockContext = rootContext.globalsBlock();

			for (JassParser.GlobalDecContext globalDecContext : globalsBlockContext.globalDec()) {
				boolean isConstant = globalDecContext.CONST_DECL() != null;

				JassParser.TypeNameContext typeNameContext = globalDecContext.type;

				String typeName = typeNameContext.getText();

				boolean isArray = globalDecContext.ARRAY_DECL() != null;

				JassParser.VarNameContext nameContext = globalDecContext.name;

				String name = nameContext.ID().getText();

				JassParser.ExprContext valContext = globalDecContext.val;

				String val = valContext.getText();

				_globalVars.add(new VarDecl(isConstant, typeName, isArray, name, val));
			}

			for (JassParser.NativeDecContext nativeDecContext : rootContext.nativeDec()) {
				boolean isConstant = nativeDecContext.CONST_DECL() != null;

				String name = nativeDecContext.name.getText();

				List<Param> params = new ArrayList<>();

				JassParser.FuncParam_listContext paramList = nativeDecContext.params;

				for (JassParser.FuncParamContext funcParamContext : paramList.funcParam()) {
					String typeName = funcParamContext.typeName().getText();
					String paramName = funcParamContext.ID().getText();

					params.add(new Param(typeName, paramName));
				}

				String returnType = nativeDecContext.returnType.getText();

				_nativeDecls.add(new FuncDecl(isConstant, name, params, returnType));
			}

			for (JassParser.FuncContext funcContext : rootContext.func()) {
				List<Param> params = new ArrayList<>();

				JassParser.FuncParam_listContext paramList = funcContext.funcDec().params;

				for (JassParser.FuncParamContext funcParamContext : paramList.funcParam()) {
					String typeName = funcParamContext.typeName().getText();
					String paramName = funcParamContext.ID().getText();

					params.add(new Param(typeName, paramName));
				}

				FuncDecl decl = new FuncDecl(funcContext.funcDec().CONST_DECL() != null, funcContext.funcDec().name.getText(), params, funcContext.funcDec().returnType.getText());

				List<VarDecl> localVars = new ArrayList<>();

				for (JassParser.LocalVarDecContext localVarDecContext : funcContext.body.localVarDec_list().localVarDec()) {
					boolean isConstant = false;

					JassParser.TypeNameContext typeNameContext = localVarDecContext.typeName();

					String typeName = typeNameContext.getText();

					boolean isArray = localVarDecContext.ARRAY_DECL() != null;

					JassParser.VarNameContext nameContext = localVarDecContext.name;

					String name = nameContext.ID().getText();

					JassParser.ExprContext valContext = localVarDecContext.expr();

					String val = valContext.getText();

					localVars.add(new VarDecl(isConstant, typeName, isArray, name, val));
				}

				List<Func.Statement> stmts = new ArrayList<>();

				for (JassParser.StatementContext statementContext : funcContext.body.statement_list().statement()) {
					String stmt = statementContext.getText();

					stmts.add(new Func.Statement(stmt));
				}

				Func.Body body = new Func.Body(localVars, stmts);

				_funcs.add(new Func(decl, body));
			}
		}

		public Script() {

		}
	}

	private List<Token> _tokens;

	public List<Token> getTokens() {
		return _tokens;
	}

	private JassParser.RootContext _rootContext;

	public JassParser.RootContext getRootContext() {
		if (_rootContext == null) {
			JassParser parser = new JassParser(new CommonTokenStream(new ListTokenSource(_tokens)));

			_rootContext = parser.root();
		}

		return _rootContext;
	}

	@Override
	public String toString() {
		return _rootContext.toString();
	}

	@Nonnull
	private static List<Token> stripComments(@Nonnull List<Token> tokens) {
		List<Token> newTokens = new ArrayList<>();

		for (Token token : tokens) {
			if (token.getType() == JassLexer.COMMENT_BLOCK) {
				String s = token.getText();

				s = s.replaceAll("\r\n", "n");
				s = s.replaceAll("\r", "n");

				s = s.replaceAll("[^\n]", "");

				for (int i = 0; i < s.length() + 1; i++) {
					Token newToken = new CommonTokenFactory().create(JassLexer.NEW_LINE, "\n");

					newTokens.add(newToken);
				}
			} else {
				newTokens.add(token);
			}
		}

		newTokens.removeIf(token -> (token.getType() == JassLexer.COMMENT_SINGLE));

		return newTokens;
	}
	
	private void read(@Nonnull InputStream inStream) throws IOException {
		UTF8 reader = new UTF8(inStream, false, true);
		
		String input = reader.readAll(false);

		CharStream antlrStream = CharStreams.fromString(input);
		
		Lexer lexer = new JassLexer(antlrStream);
		
		CommonTokenStream tokenStream = new CommonTokenStream(lexer);

		tokenStream.fill();

		List<Token> tokens = tokenStream.getTokens();

		_tokens = stripComments(tokens);
	}
	
	public Jass(@Nonnull InputStream inStream) throws IOException {
		read(inStream);
	}
	
	public Jass(@Nonnull File file) throws IOException {
		InputStream inStream = new FileInputStream(file);
		
		read(inStream);
		
		inStream.close();
	}

	public Jass(@Nonnull String s) {
		try {
			InputStream inStream = new ByteArrayInputStream(s.getBytes());

			read(inStream);

			inStream.close();
		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	public Jass() {
		_rootContext = new JassParser.RootContext(null, -1);
	}
}