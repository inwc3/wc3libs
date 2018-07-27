package net.moonlightflower.wc3libs.txt;

import javax.annotation.Nonnull;
import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Vector;

public class UTF8 {
	public static String BOM = "\uFEFF";

	private final Vector<String> _lines = new Vector<>();
	private final Iterator<String> _linesItr;
	
	public String readLine() {
		if (!_linesItr.hasNext()) {
			return null;
		}
		
		return _linesItr.next();
	}

	public String readAll(boolean addLineSeparator) {
		StringBuilder sb = new StringBuilder();
		String line = readLine();

		if (line == null) return null;

		sb.append(line);

		while ((line = readLine()) != null) {
			if (addLineSeparator) sb.append(System.lineSeparator());
			sb.append(line);
		}

		return sb.toString();
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

	private List<String> readLines(String s, boolean includeLineEnding) throws IOException {
		List<String> ret = new ArrayList<>();
		int pos = 0;

		int endPos = pos;

		if (s.length() == 0) return ret;

		while (true) {
			if (s.charAt(endPos) == '\n') {
				if (includeLineEnding) {
					ret.add(s.substring(pos, endPos + 1));
				} else {
					ret.add(s.substring(pos, endPos));
				}

				pos = endPos + 1;

				endPos = pos;
			} else if (pos + 2 < s.length() && s.substring(pos, pos + 2).equals("\r\n")) {
				if (includeLineEnding) {
					endPos += 1;

					ret.add(s.substring(pos, endPos + 1));
				} else {
					ret.add(s.substring(pos, endPos));

					endPos += 1;
				}

				pos = endPos + 1;

				endPos = pos;
			} else if (s.charAt(pos) == '\r') {
				if (includeLineEnding) {
					ret.add(s.substring(pos, endPos + 1));
				} else {
					ret.add(s.substring(pos, endPos));
				}

				pos = endPos + 1;

				endPos = pos;
			} else {
				endPos++;
			}

			if (endPos > s.length() - 1) {
				if (pos != endPos) ret.add(s.substring(pos, s.length()));

				break;
			}
		}

		/*for (String line : ret) {
			System.out.print("line: ");

			for (byte b : line.getBytes()) {
				System.out.print(b + " ");
			}

			System.out.println();
		}*/

		return ret;
	}

	private String inStreamToString(InputStream inStream) throws IOException {
		ByteArrayOutputStream os = new ByteArrayOutputStream();
		byte[] buf = new byte[4096];

		int length;

		while ((length = inStream.read(buf)) != -1) {
			os.write(buf, 0, length);
		}

		return new String(os.toByteArray(), StandardCharsets.UTF_8);
	}

	public UTF8(@Nonnull InputStream inStream, boolean includeBOM, boolean includeLineEndings) throws IOException {
		String s = inStreamToString(inStream);

		List<String> lines = readLines(s, includeLineEndings);

		if (!includeBOM && !lines.isEmpty() && lines.get(0).startsWith(BOM)) lines.set(0, lines.get(0).substring(BOM.length()));

		_lines.addAll(lines);

		_linesItr = _lines.iterator();
	}

	public UTF8(@Nonnull InputStream inStream) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(inStream, StandardCharsets.UTF_8));

		String line = reader.readLine();

		if (line != null) {
			if (line.startsWith(BOM)) line = line.substring(BOM.length());

			_lines.addElement(line);

			while ((line = reader.readLine()) != null) {
				_lines.add(line);
			}
		}
		
		reader.close();
		
		_linesItr = _lines.iterator();
	}
	
	public UTF8() {
		_linesItr = _lines.iterator();
	}
}
