package net.moonlightflower.wc3libs.txt;

import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.antlr.v4.runtime.*;
import org.antlr.v4.runtime.tree.ErrorNode;
import org.antlr.v4.runtime.tree.ParseTreeListener;
import org.antlr.v4.runtime.tree.TerminalNode;
import org.antlr.v4.tool.LexerGrammar;

import net.moonlightflower.wc3libs.misc.antlr.out.grammar.FDFLexer;
import net.moonlightflower.wc3libs.misc.antlr.out.grammar.FDFParser;
import net.moonlightflower.wc3libs.misc.antlr.out.grammar.FDFParser.StringAssignContext;
import net.moonlightflower.wc3libs.port.LadikMpqPort;
import net.moonlightflower.wc3libs.port.MpqPort;

public class FDF extends UTF8 {
	private static String stripComments(String val) {
		val = val.replaceAll("//.*", "");
		
		Pattern pattern = Pattern.compile(String.format("%s.*%s", Pattern.quote("/*"), Pattern.quote("*/")), Pattern.DOTALL);
		
		Matcher matcher = pattern.matcher(val);
		
		val = matcher.replaceAll("");
		
		return val;
	}
	
	private static String dequote(String s) {
		if ((s.charAt(0) == '\"') && (s.charAt(s.length() - 1) == '\"')) {
			s = s.substring(1, s.length() - 1);
		}
		
		return s;
	}
	
	public static int indexOfRegex(String input, String regex, int startPos) {
		Matcher matcher = Pattern.compile(regex).matcher(input);
		
		if (!matcher.find(startPos)) return -1;
		
		return matcher.start(1);
	}
	
	public static List<String> tokenize(String line) {
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
	
	private final Map<String, String> _map = new HashMap<>();
	
	public TXT toTXT() {
		TXT txt = new TXT();
		
		for (Map.Entry<String, String> entry : _map.entrySet()) {
			txt.set(entry.getKey(), entry.getValue());
		}
		
		return txt;
	}
	
	private void read(InputStream inStream) throws IOException {
		UTF8 reader = new UTF8(inStream);
		
		String input = stripComments(reader.readAll());
		
		ANTLRInputStream antlrStream = new ANTLRInputStream(input);
		
		Lexer lexer = new FDFLexer(antlrStream);
		
		CommonTokenStream tokens = new CommonTokenStream(lexer);
		
		FDFParser parser = new FDFParser(tokens);
		
		Map<String, String> newEntries = new HashMap<>();
		
		parser.addParseListener(new ParseTreeListener() {
			@Override
			public void enterEveryRule(ParserRuleContext arg0) {
				// TODO Auto-generated method stub
				
			}

			@Override
			public void exitEveryRule(ParserRuleContext context) {
				if (context instanceof FDFParser.StringAssignContext) {
					FDFParser.StringAssignContext stringAssignContext = (StringAssignContext) context;

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
	
	private void read2(InputStream inStream) throws IOException {
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
	
	public FDF(InputStream inStream) throws IOException {
		super();
		
		read(inStream);
	}
	
	public static FDF ofGameFile(File inFile) throws Exception {
		MpqPort.Out.Result portResult = MpqPort.getDefaultImpl().getGameFiles(inFile);
		
		if (!portResult.getExports().containsKey(inFile)) throw new IOException(String.format("could not extract %s file", inFile.toString()));
		
		byte[] bytes = portResult.getExports().get(inFile).getOutBytes();
		
		InputStream inStream = new ByteArrayInputStream(bytes);
		
		FDF fdf = new FDF(inStream);
		
		inStream.close();
		
		return fdf;
	}
}
