package net.moonlightflower.wc3libs.txt.app.jass;

import com.sun.org.apache.xpath.internal.operations.Variable;
import net.moonlightflower.wc3libs.antlr.JassLexer;
import net.moonlightflower.wc3libs.antlr.JassParser;
import net.moonlightflower.wc3libs.antlr.LightJassParser;
import net.moonlightflower.wc3libs.misc.Math;
import net.moonlightflower.wc3libs.txt.UTF8;
import org.antlr.v4.runtime.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Function;
import java.util.function.Predicate;

public class Jass {
	public final static File GAME_PATH = new File("war3map.j");

	private List<Token> _tokens;

	public List<Token> getTokens() {
		return _tokens;
	}

	private JassParser.RootContext _rootContext;

	public JassParser.RootContext getRootContext() {
		if (_rootContext == null) {
			TokenStream tokenStream = new CommonTokenStream(new ListTokenSource(_tokens));

			JassParser parser = new JassParser(tokenStream);

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
					Token newToken = new CommonTokenFactory().create(JassLexer.NEW_LINES, "\n");

					newTokens.add(newToken);
				}
			} else {
				newTokens.add(token);
			}
		}

		newTokens.removeIf(token -> (token.getType() == JassLexer.COMMENT_SINGLE));

		return newTokens;
	}

	public static JassParser transform(@Nonnull String input) {
		CharStream antlrStream = CharStreams.fromString(input);

		Lexer lexer = new JassLexer(antlrStream);

		CommonTokenStream tokenStream = new CommonTokenStream(lexer);

		tokenStream.fill();

		//List<Token> tokens = new ArrayList<>(tokenStream.getTokens());

		//_tokens = stripComments(tokens);

		//TokenStream tokenStream = new CommonTokenStream(new ListTokenSource(_tokens));

		JassParser parser = new JassParser(tokenStream);

		return parser;
	}

	private void read(@Nonnull InputStream inStream) throws IOException {
		UTF8 reader = new UTF8(inStream, false, true);
		
		String input = reader.readAll(false);

		CharStream antlrStream = CharStreams.fromString(input);
		
		Lexer lexer = new JassLexer(antlrStream);
		
		CommonTokenStream tokenStream = new CommonTokenStream(lexer);

		tokenStream.fill();

		List<Token> tokens = new ArrayList<>(tokenStream.getTokens());

		_tokens = stripComments(tokens);

		/*for (Token token : _tokens) {
			System.out.println(JassLexer.VOCABULARY.getSymbolicName(token.getType()) + " -> " + token.getText());
		}*/
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