package net.moonlightflower.wc3libs.txt;

import net.moonlightflower.wc3libs.antlr.JassLexer;
import net.moonlightflower.wc3libs.antlr.JassParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.*;
import java.util.HashMap;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;


public class Jass {
	private static String stripComments(String val) {
		val = val.replaceAll("//.*", "");
		
		Pattern pattern = Pattern.compile(String.format("%s.*%s", Pattern.quote("/*"), Pattern.quote("*/")), Pattern.DOTALL);
		
		Matcher matcher = pattern.matcher(val);
		
		val = matcher.replaceAll("");
		
		return val;
	}
	
	private void read(InputStream inStream) throws IOException {
		UTF8 reader = new UTF8(inStream);
		
		String input = stripComments(reader.readAll());
		
		input = input.replaceAll("\r\n", "\n");
		
		ANTLRInputStream antlrStream = new ANTLRInputStream(input);
		
		Lexer lexer = new JassLexer(antlrStream);
		
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		JassParser parser = new JassParser(tokens);
		
		Map<String, String> newEntries = new HashMap<>();
		
		parser.addParseListener(new ParseTreeListener() {
			@Override
			public void enterEveryRule(ParserRuleContext context) {
				// TODO Auto-generated method stub
				System.err.println("enter: " + context.toString(parser, context.getParent()));
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
				
				System.err.println("exit: " + context.toString(parser, context.getParent()) + "->" + context.getText());
			}

			@Override
			public void visitErrorNode(ErrorNode arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void visitTerminal(TerminalNode arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		parser.root();
		
		BufferedWriter tokenOut = new BufferedWriter(new FileWriter("D:\\tokens.txt"));

		for (int i = 0; i < tokens.getTokens().size(); i++) {
			Token token = tokens.getTokens().get(i);
			
			if (token.getType() == -1) continue;
			
			String tokenS = token.getLine()+";"+token.getCharPositionInLine() + "->" + lexer.getTokenNames()[token.getType()];

			tokenOut.write(tokenS + ";");
			tokenOut.newLine();
		}
		
		tokenOut.close();
		
		//_map.putAll(newEntries);
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