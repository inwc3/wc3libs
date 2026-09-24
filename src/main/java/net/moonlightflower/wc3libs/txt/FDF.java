package net.moonlightflower.wc3libs.txt;

import net.moonlightflower.wc3libs.antlr.FDFLexer;
import net.moonlightflower.wc3libs.antlr.FDFParser;
import net.moonlightflower.wc3libs.port.Context;
import net.moonlightflower.wc3libs.port.MpqPort;
import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FDF extends UTF8 {
	private static String dequote(@Nullable String s) {
		if (s == null) return null;

		if (s.length() >= 2 && s.charAt(0) == '\"' && s.charAt(s.length() - 1) == '\"') {
			StringBuilder result = new StringBuilder(s.length() - 2);
			for (int i = 1; i < s.length() - 1; i++) {
				char c = s.charAt(i);
				if (c == '\\' && i + 1 < s.length() - 1) {
					char escaped = s.charAt(i + 1);
					if (escaped == '\\' || escaped == '\"') {
						result.append(escaped);
						i++;
						continue;
					}
				}
				result.append(c);
			}
			return result.toString();
		}

		return s;
	}

	private static String quote(@Nonnull String value) {
		if (value.indexOf('\r') >= 0 || value.indexOf('\n') >= 0) {
			throw new IllegalArgumentException("FDF string values cannot contain line breaks");
		}
		return "\"" + value.replace("\\", "\\\\").replace("\"", "\\\"") + "\"";
	}
	
	public static int indexOfRegex(@Nonnull String input, @Nonnull String regex, int startPos) {
		Matcher matcher = Pattern.compile(regex).matcher(input);
		
		if (!matcher.find(startPos)) return -1;
		
		return matcher.start(1);
	}

	@Nullable
	public static List<String> tokenize(@Nonnull String line) {
		if (line.length() == 0) return null;
		
		List<String> ret = new ArrayList<>();
		
		int startPos = 0;
		
		while (startPos < line.length()) {
			if (line.charAt(startPos) == '\"') {
				int endPos = line.indexOf('\"', startPos + 1);
				
				if (endPos == -1) {
					endPos = line.length() - 1;
				}
				
				String val = line.substring(startPos, endPos + 1);
				
				val = dequote(val);
				
				ret.add(val);
				
				startPos = endPos + 1;
			} else {
				int endPos = line.indexOf(',', startPos);

				if (endPos == -1) {
					endPos = indexOfRegex(line, "\\s", startPos);
					
					if (endPos == -1) {
						endPos = line.length() - 1;
					}
				} else {
					endPos = endPos - 1;
				}

				String val = (endPos < startPos) ? "" : line.substring(startPos, endPos + 1);
				
				ret.add(val);
				
				startPos = endPos + 2;
			}
		}
		
		return ret;
	}
	
	private final Map<String, String> _map = new LinkedHashMap<>();
	private boolean _writeable = true;

	@Nonnull
	public TXT toTXT() {
		TXT txt = new TXT();
		
		for (Map.Entry<String, String> entry : _map.entrySet()) {
			txt.set(entry.getKey(), entry.getValue());
		}
		
		return txt;
	}
	
	private void read(@Nonnull InputStream inStream) throws IOException {
		UTF8 reader = new UTF8(inStream);
		String input = reader.readAll();
		if (input == null) input = "";
		
		CharStream antlrStream = CharStreams.fromString(input);
		
		Lexer lexer = new FDFLexer(antlrStream);
		
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		FDFParser parser = new FDFParser(tokens);
		boolean[] syntaxError = {false};
		BaseErrorListener errorListener = new BaseErrorListener() {
			@Override
			public void syntaxError(Recognizer<?, ?> recognizer, Object offendingSymbol, int line, int charPositionInLine,
			                        String msg, RecognitionException e) {
				syntaxError[0] = true;
			}
		};
		lexer.removeErrorListeners();
		lexer.addErrorListener(errorListener);
		parser.removeErrorListeners();
		parser.addErrorListener(errorListener);
		
		Map<String, String> newEntries = new LinkedHashMap<>();
		
		parser.addParseListener(new ParseTreeListener() {
			@Override
			public void enterEveryRule(ParserRuleContext arg0) {
			}

			@Override
			public void exitEveryRule(ParserRuleContext context) {
				if (context instanceof FDFParser.StringAssignContext) {
					FDFParser.StringAssignContext stringAssignContext = (FDFParser.StringAssignContext) context;

					newEntries.put(stringAssignContext.id.getText(), dequote(stringAssignContext.val.getText()));
				}
			}

			@Override
			public void visitErrorNode(ErrorNode arg0) {
			}

			@Override
			public void visitTerminal(TerminalNode arg0) {
			}
			
		});
		
		parser.root();
		_writeable = !syntaxError[0];
		
		_map.putAll(newEntries);
	}

	/**
	 * Returns the StringList entries represented by this FDF. Frame definitions are not
	 * currently modeled by this class; use {@link #minify(String)} to compact a complete
	 * FDF source without parsing or discarding its frame definitions.
	 */
	@Nonnull
	public Map<String, String> getEntries() {
		return java.util.Collections.unmodifiableMap(_map);
	}

	public void set(@Nonnull String id, @Nonnull String value) {
		if (!id.matches("[A-Za-z][A-Za-z0-9_]*")) {
			throw new IllegalArgumentException("Invalid FDF StringList identifier: " + id);
		}
		_map.put(id, value);
	}

	/** Writes the modeled StringList as UTF-8, preserving insertion order. */
	public void write(@Nonnull Writer writer) throws IOException {
		write(writer, false);
	}

	/** Writes the modeled StringList as UTF-8 with optional compact formatting. */
	public void write(@Nonnull Writer writer, boolean minified) throws IOException {
		ensureWriteable();

		if (minified) {
			writer.write("StringList{");
			boolean first = true;
			for (Map.Entry<String, String> entry : _map.entrySet()) {
				if (!first) writer.write(',');
				writer.write(entry.getKey());
				writer.write(' ');
				writer.write(quote(entry.getValue()));
				first = false;
			}
			writer.write('}');
			return;
		}

		writer.write("StringList {\n");
		for (Map.Entry<String, String> entry : _map.entrySet()) {
			writer.write("\t");
			writer.write(entry.getKey());
			writer.write(" ");
			writer.write(quote(entry.getValue()));
			writer.write(",\n");
		}
		writer.write("}\n");
	}

	public void write(@Nonnull OutputStream outputStream) throws IOException {
		write(outputStream, false);
	}

	public void write(@Nonnull OutputStream outputStream, boolean minified) throws IOException {
		Writer writer = new OutputStreamWriter(outputStream, StandardCharsets.UTF_8);
		write(writer, minified);
		writer.flush();
	}

	public void write(@Nonnull File file) throws IOException {
		ensureWriteable();
		try (Writer writer = Files.newBufferedWriter(file.toPath(), StandardCharsets.UTF_8)) {
			write(writer);
		}
	}

	public void write(@Nonnull File file, boolean minified) throws IOException {
		ensureWriteable();
		try (Writer writer = Files.newBufferedWriter(file.toPath(), StandardCharsets.UTF_8)) {
			write(writer, minified);
		}
	}

	private void ensureWriteable() {
		if (!_writeable) {
			throw new IllegalStateException("This FDF contains definitions outside the supported StringList model; use FDF.minify to preserve them.");
		}
	}

	/**
	 * Removes FDF comments and unnecessary whitespace without interpreting frame
	 * definitions. Whitespace inside quoted strings is preserved.
	 */
	@Nonnull
	public static String minify(@Nonnull String input) {
		StringBuilder out = new StringBuilder(input.length());
		boolean pendingWhitespace = false;
		boolean inString = false;
		boolean escaped = false;

		for (int i = 0; i < input.length();) {
			char c = input.charAt(i);
			if (inString) {
				out.append(c);
				i++;
				if (escaped) escaped = false;
				else if (c == '\\') escaped = true;
				else if (c == '\"') inString = false;
				continue;
			}

			if (c == '\"') {
				appendPendingSpace(out, pendingWhitespace, c);
				pendingWhitespace = false;
				inString = true;
				out.append(c);
				i++;
				continue;
			}

			if (Character.isWhitespace(c)) {
				pendingWhitespace = true;
				i++;
				continue;
			}

			if (c == '/' && i + 1 < input.length() && input.charAt(i + 1) == '/') {
				pendingWhitespace = true;
				i += 2;
				while (i < input.length() && input.charAt(i) != '\r' && input.charAt(i) != '\n') i++;
				continue;
			}

			if (c == '/' && i + 1 < input.length() && input.charAt(i + 1) == '*') {
				int end = input.indexOf("*/", i + 2);
				if (end < 0) throw new IllegalArgumentException("Unterminated FDF block comment at index " + i);
				pendingWhitespace = true;
				i = end + 2;
				continue;
			}

			appendPendingSpace(out, pendingWhitespace, c);
			pendingWhitespace = false;
			out.append(c);
			i++;
		}

		if (inString) throw new IllegalArgumentException("Unterminated FDF string literal");
		return out.toString();
	}

	/** Minifies an FDF source file while preserving frame definitions and string contents. */
	public static void minify(@Nonnull File input, @Nonnull File output) throws IOException {
		String source = Files.readString(input.toPath(), StandardCharsets.UTF_8);
		Files.writeString(output.toPath(), minify(source), StandardCharsets.UTF_8);
	}

	private static void appendPendingSpace(@Nonnull StringBuilder out, boolean pendingWhitespace, char next) {
		if (!pendingWhitespace || out.length() == 0) return;
		char previous = out.charAt(out.length() - 1);
		boolean previousOperator = "+-*/=!<>&|".indexOf(previous) >= 0;
		boolean nextOperator = "+-*/=!<>&|".indexOf(next) >= 0;
		if ((isWordChar(previous) && isWordChar(next))
				|| (isWordChar(previous) && next == '\"')
				|| (previous == '\"' && (next == '\"' || isWordChar(next)))
				|| (previousOperator && (isWordChar(next) || nextOperator))
				|| (nextOperator && isWordChar(previous))
				|| (previous == '/' && (next == '/' || next == '*'))) {
			out.append(' ');
		}
	}

	private static boolean isWordChar(char c) {
		return Character.isLetterOrDigit(c) || c == '_' || c == '.';
	}
	public FDF(@Nonnull File file) throws IOException {
		this(Files.newInputStream(file.toPath()));
	}
	
	public FDF(@Nonnull InputStream inStream) throws IOException {
		super();
		
		read(inStream);
	}

	public FDF() {
		super();
	}

	@Nonnull
	protected static MpqPort getMpqPort() {
		return Context.getService(MpqPort.class);
	}

	@Nonnull
	public static FDF ofGameFile(@Nonnull File inFile) throws Exception {
		MpqPort.Out.Result portResult = getMpqPort().getGameFiles(inFile);
		
		if (!portResult.getExports().containsKey(inFile)) throw new IOException(String.format("could not extract %s file", inFile.toString()));
		
		byte[] bytes = portResult.getExports().get(inFile).getOutBytes();
		
		InputStream inStream = new ByteArrayInputStream(bytes);
		
		FDF fdf = new FDF(inStream);
		
		inStream.close();
		
		return fdf;
	}
}
