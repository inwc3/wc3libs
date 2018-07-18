package net.moonlightflower.wc3libs.txt;

import net.moonlightflower.wc3libs.antlr.JassLexer;
import net.moonlightflower.wc3libs.antlr.JassParser;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

import java.io.*;
import java.nio.charset.StandardCharsets;
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

		CharStream antlrStream = CharStreams.fromString(input);
		
		Lexer lexer = new JassLexer(antlrStream);
		
		CommonTokenStream tokenStream = new CommonTokenStream(lexer);

		tokenStream.fill();

		List<Token> tokens = tokenStream.getTokens();

		_tokens = stripComments(tokens);
	}
	
	public Jass(InputStream inStream) throws IOException {
		read(inStream);
	}
	
	public Jass(File file) throws IOException {
		InputStream inStream = new FileInputStream(file);
		
		read(inStream);
		
		inStream.close();
	}

	public Jass(String s) {
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