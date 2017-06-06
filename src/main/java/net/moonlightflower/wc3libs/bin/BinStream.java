package net.moonlightflower.wc3libs.bin;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;
import java.util.Stack;

public class BinStream {
	public static class StreamException extends IOException {
		private BinStream _stream;
		
		@Override
		public String toString() {
			StringBuilder sb = new StringBuilder();
			
			for (String s : _stream.getLogLines()) {
				sb.append(System.lineSeparator());
				
				sb.append(s);
			}
			
			return sb.toString();
		}

		public BinStream getStream() {
			return _stream;
		}
		
		public StreamException(BinStream stream) {
			_stream = stream;
		}
	}
	
	private int _pos = 0;
	protected List<Byte> _bytes = new ArrayList<>();
	
	private List<String> _logLines = new ArrayList<>();
	
	public List<String> getLogLines() {
		return _logLines;
	}
	
	public void log(String s) {
		_logLines.add(s);
	}
	
	private int _logStartPos = 0;
	
	public void log(String type, String label, Object val, String valFormat) {
		int startPos = _logStartPos;
		int endPos = _pos;
		
		int len = endPos - startPos;
		
		if (label == null) label = "?";
		
		StringBuilder bytesPart = new StringBuilder("");
		
		for (int i = startPos; i < startPos + len; i++) {
			if (bytesPart.length() > 0) bytesPart.append(" ");
			
			if (i < _bytes.size()) {					
				bytesPart.append(String.format("%02X", _bytes.get(i)));
			} else {
				bytesPart.append("?");
			}
		}
		
		String line = String.format("%s [%s]: %s [%s]", label, type, String.format(valFormat, val), bytesPart);
		
		StringBuilder sb = new StringBuilder();
		
		for (String group : _curGroupStack) {
			sb.append(group);
			sb.append("_");
		}
		
		sb.append(line);
		
		_logLines.add(sb.toString());
	}
	
	public void logBegin() {
		_logStartPos = _pos;
	}
	
	public void log(String type, String label, Object val) {
		log(type, label, val, "%s");
	}
	
	private Stack<String> _curGroupStack = new Stack<>();
	
	public void beginGroup(String label) {
		_curGroupStack.push(label);
	}
	
	public void endGroup() {
		_curGroupStack.pop();
	}
	
	public void printLog(OutputStream outStream) throws IOException {
		for (String line : getLogLines()) {
			outStream.write(line.getBytes(StandardCharsets.UTF_8));
			outStream.write(System.lineSeparator().getBytes(StandardCharsets.UTF_8));
		}
	}
	
	public void printLog() throws IOException {
		printLog(System.err);
		
		System.err.flush();
	}
	
	public int getPos() {
		return _pos;
	}
	
	public byte get(int index) {
		return _bytes.get(index);
	}
	
	public int size() {
		return _bytes.size();
	}
	
	public boolean eof() {
		return (_pos >= _bytes.size());
	}
	
	private void ensureCap(int size) {
		int toAdd = size - _bytes.size();

		for (int i = 1; i <= toAdd; i++) {
			_bytes.add(null);
		}
	}
	
	public byte readByte() throws StreamException {
		try {
			return _bytes.get(_pos++);
		} catch (IndexOutOfBoundsException e) {
			throw new StreamException(this);
		}
	}
	
	public byte[] readBytes(int size) throws StreamException {
		try {
			byte[] vals = new byte[size];
			
			for (int i = 0; i < vals.length; i++) {
				vals[i] = _bytes.get(_pos++);
			}
			
			return vals;
		} catch (IndexOutOfBoundsException e) {
			throw new StreamException(this);
		}
	}
	
	public void writeByte(byte val) {
		ensureCap(_pos + 1);

		_bytes.set(_pos++, val);
	}
	
	public void writeBytes(byte[] vals) {
		ensureCap(_pos + vals.length);

		for (byte val : vals) {
			_bytes.set(_pos++, val);
		}
	}
	
	public void setPos(int pos) {
		_pos = pos;
	}
	
	public void rewind() {
		_logLines.clear();
		_pos = 0;
	}
	
	public void rewind(int delta) {
		delta = Math.min(_pos, delta);
		
		_pos -= delta;
	}
	
	public void read(InputStream inStream) throws IOException {
		byte[] buf = new byte[1024];
		int len;

		while ((len = inStream.read(buf, 0, buf.length)) != -1) {
			for (int i = 0; i < len; i++) {
				_bytes.add(buf[i]);
			}
		}
		
		/*OutputStream out = new FileOutputStream("D:\\test.txt");
		
		for (Byte b : _bytes) {
			out.write(b);
		}
		
		out.close();*/
	}
	
	public void writeTo(OutputStream outStream) throws IOException {			
		List<Byte> bytes = _bytes;

		byte[] buf = new byte[bytes.size()];
		
		for (int i = 0; i < bytes.size(); i++) {
			buf[i] = bytes.get(i);
		}

		outStream.write(buf);

		outStream.flush();
	}
	
	public void writeTo(File file) throws IOException {
		OutputStream outStream = new FileOutputStream(file);
		
		writeTo(outStream);
		
		outStream.close();
	}
	
	public BinStream(InputStream inStream) throws IOException {
		read(inStream);
	}
	
	public BinStream(File file) throws IOException {
		if (file == null) throw new IOException("no file");
		
		InputStream inStream = new FileInputStream(file);
		
		read(inStream);
		
		inStream.close();
	}
	
	public BinStream() {
	}
}
