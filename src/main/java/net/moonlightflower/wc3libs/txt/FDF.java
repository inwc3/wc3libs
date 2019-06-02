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
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class FDF extends UTF8 {
	private static String stripComments(@Nullable String val) {
		if (val == null) return null;

		val = val.replaceAll("//.*", "");
		
		Pattern pattern = Pattern.compile(String.format("%s.*%s", Pattern.quote("/*"), Pattern.quote("*/")), Pattern.DOTALL);
		
		Matcher matcher = pattern.matcher(val);
		
		val = matcher.replaceAll("");
		
		return val;
	}
	
	private static String dequote(@Nullable String s) {
		if (s == null) return null;

		if ((s.charAt(0) == '\"') && (s.charAt(s.length() - 1) == '\"')) {
			s = s.substring(1, s.length() - 1);
		}
		
		return s;
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
		
		String input = stripComments(reader.readAll());
		
		CharStream antlrStream = CharStreams.fromString(input);
		
		Lexer lexer = new FDFLexer(antlrStream);
		
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		FDFParser parser = new FDFParser(tokens);
		
		Map<String, String> newEntries = new LinkedHashMap<>();
		
		parser.addParseListener(new ParseTreeListener() {
			@Override
			public void enterEveryRule(ParserRuleContext arg0) {
				// TODO Auto-generated method stub
				
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
				// TODO Auto-generated method stub
				
			}

			@Override
			public void visitTerminal(TerminalNode arg0) {
				// TODO Auto-generated method stub
				
			}
			
		});
		
		parser.root();
		
		_map.putAll(newEntries);
	}
	
	private void read2(@Nonnull InputStream inStream) throws IOException {
		UTF8 reader = new UTF8(inStream);
		
		String input = stripComments(reader.readAll());
		
		int escapedBackslashReplaceChar = 1;
		
		while (input.indexOf(escapedBackslashReplaceChar) != -1) {
			escapedBackslashReplaceChar++;
		};
		
		int escapedQuoteReplaceChar = escapedBackslashReplaceChar + 1;
		
		while (input.indexOf(escapedQuoteReplaceChar) != -1) {
			escapedQuoteReplaceChar++;
		};
		
		input = input.replaceAll("\\\\", Character.toString((char) escapedBackslashReplaceChar));
		
		input = input.replaceAll("\\\\\"", Character.toString((char) escapedQuoteReplaceChar));
		
		BufferedWriter out = new BufferedWriter(new FileWriter(new File("D:\\fdf.txt")));
		
		out.write(input);
		
		out.close();
		
		//input = input.replaceAll("\"", "");
		
		input = input.replaceAll("\\p{Cntrl}", "");
		
		Pattern pattern = Pattern.compile("StringList\\s*\\{(.*)\\}");
		
		Matcher matcher = pattern.matcher(input);
		
		if (matcher.find()) {
			String content = matcher.group(1);

			Matcher contentMatcher = Pattern.compile("[\\s*(\\w+)\\s+\\\"(.*)\\\"]+").matcher(input);
			
			if (contentMatcher.find()) {
				for (int i = 0; i < contentMatcher.groupCount() / 2; i++) {
					String key = contentMatcher.group(i * 2);
					String val = contentMatcher.group(i * 2 + 1);

					_map.put(key, val);
				}
			};
			
			/*List<String> tokens = tokenize(content);
			
			for (int i = 0; i < tokens.size() / 2; i++) {
				String line = tokens.get(i);
				
				//Matcher lineMatcher = Pattern.compile("\\s*()\\s+()")line.
				
				String key = tokens.get(i * 2);
				String val = tokens.get(i * 2 + 1);
				System.out.println(key + "->" + val);
				_map.put(key, val);
			}*/
		}
	}

	public FDF(@Nonnull File file) throws IOException {
		this(new FileInputStream(file));
	}
	
	public FDF(@Nonnull InputStream inStream) throws IOException {
		super();
		
		read(inStream);
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
