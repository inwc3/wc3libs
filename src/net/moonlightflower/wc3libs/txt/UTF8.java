package net.moonlightflower.wc3libs.txt;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Iterator;
import java.util.Vector;

public class UTF8 {
	private Vector<String> _lines = new Vector<>();
	private Iterator<String> _linesItr;
	
	public String readLine() {
		if (!_linesItr.hasNext()) {
			return null;
		}
		
		return _linesItr.next();
	}
	
	public String readAll() {
		StringBuilder sb = new StringBuilder();
		String line = readLine();
		
		if (line == null) return null;
		
		sb.append(line);
		
		while ((line = readLine()) != null) {
			sb.append(System.lineSeparator());
			sb.append(line);
		}
		
		return sb.toString();
	}
	
	public UTF8(InputStream inStream) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, StandardCharsets.UTF_8));
		
		String line = reader.readLine();
		
		if (line.startsWith("\uFEFF")) line = line.substring(1);
		
		_lines.addElement(line);
		
		while ((line = reader.readLine()) != null) {
			_lines.add(line);
		};
		
		reader.close();
		
		_linesItr = _lines.iterator();
	}
}
