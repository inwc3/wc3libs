package net.moonlightflower.wc3libs.txt;

import net.moonlightflower.wc3libs.antlr.JassLexer;
import net.moonlightflower.wc3libs.antlr.JassParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


public class Jass {
	public final static File GAME_PATH = new File("war3map.j");

	private List<Token> _tokens;

	public List<Token> getTokens() {
		return _tokens;
	}

	private JassParser.RootContext _rootContext;

	public JassParser.RootContext getRootContext() {
		return _rootContext;
	}

	private static List<Token> stripComments(List<Token> tokens) {
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
	
	private void read(InputStream inStream) throws IOException {
		UTF8 reader = new UTF8(inStream, false, true);
		
		String input = reader.readAll(false);

		/*System.out.println(input);

		for (byte b : input.getBytes()) {
			System.out.print(String.format("%x", b) + " ");
		}

		System.out.println();*/

		//input = stripComments(input);

		//input = input.replaceAll("\r\n", "\n");

		CharStream antlrStream = CharStreams.fromString(input);
		
		Lexer lexer = new JassLexer(antlrStream);
		
		CommonTokenStream tokenStream = new CommonTokenStream(lexer);

		tokenStream.fill();

		List<Token> tokens = tokenStream.getTokens();

		/*for (Token token : tokens) {
			System.out.println(token.getText());
		}*/

		_tokens = stripComments(tokens);

		//System.out.println(tokens);

		JassParser parser = new JassParser(new CommonTokenStream(new ListTokenSource(_tokens)));
		
		Map<String, String> newEntries = new LinkedHashMap<>();
		
		parser.addParseListener(new ParseTreeListener() {
			@Override
			public void enterEveryRule(ParserRuleContext context) {
//				System.err.println("enter: " + context.toString(parser, context.getParent()));
			}

			@Override
			public void exitEveryRule(ParserRuleContext context) {
				if (context instanceof JassParser.GlobalDecContext) {
					JassParser.GlobalDecContext globalDecContext = (JassParser.GlobalDecContext) context;

					//newEntries.put(globalDecContext.id.getText(), dequote(globalDecContext.val.getText()));
					//System.out.println(globalDecContext.type.getText() + "->" + globalDecContext.name.getText());
				}
				
				if (context instanceof JassParser.FuncContext) {
					JassParser.FuncContext funcContext = (JassParser.FuncContext) context;
					
					//System.out.println(funcContext.name.getText() + ";" + funcContext.params.getText() + ";" + funcContext.returnType.getText());
				}
				
				if (context instanceof JassParser.StatementContext) {
					JassParser.StatementContext statementContext = (JassParser.StatementContext) context;
					
					//System.out.println(statementContext.name.getText() + ";" + statementContext.params.getText() + ";" + statementContext.returnType.getText());
				}

				if (context instanceof JassParser.SetVarContext) {
					JassParser.SetVarContext setVarContext = (JassParser.SetVarContext) context;
					
					//System.out.println(setVarContext.name.getText() + ";" + setVarContext.val.getText());
				}

				if (context instanceof JassParser.LoopBodyContext) {
					//System.out.println("loopbody exit");
				}
				if (context instanceof JassParser.LoopContext) {
					//System.out.println("loop exit");
				}
				
				if (!(context instanceof JassParser.RootContext)) {
					//System.err.println("exit " + context.toString(parser, context.getParent()) + "->" + context.getText() + "|||");
				}
				
//				System.err.println("exit: " + context.toString(parser, context.getParent()) + "->" + context.getText());
			}

			@Override
			public void visitErrorNode(ErrorNode arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void visitTerminal(TerminalNode arg0) {
				// TODO Auto-generated method stub
				//System.out.println(arg0);
			}
			
		});
		
		_rootContext = parser.root();

		/*for (Token token : _tokens) {
			if (token.getType() == JassLexer.INT_LITERAL) {
				String text = token.getText();

				int val;

				if (text.startsWith("0x") || text.startsWith("0X")) {
					val = Math.decode(text.substring(2).toLowerCase(), Math.CODE_HEX);
				} else if (text.startsWith("$")) {
					val = Math.decode(text.substring(1), Math.CODE_HEX);
				} else if (text.startsWith("0")) {
					val = Math.decode(text.substring(1), Math.CODE_OCT);
				} else if (text.startsWith("'")) {
					val = Math.decode(text.substring(1, text.length() - 1), Math.CODE_ASCII);
				} else {
					val = Math.decode(text, Math.CODE_DEC);
				}

				Id id = Id.valueOf(Math.encode(val, Math.CODE_ASCII));

				System.out.println(val + "->" + id);
			}
		}*/
	}
	
	public Jass(InputStream inStream) throws IOException {
		read(inStream);
	}
	
	public Jass(File file) throws IOException {
		InputStream inStream = new FileInputStream(file);
		
		read(inStream);
		
		inStream.close();
	}
}