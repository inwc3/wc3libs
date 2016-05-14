package bin;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import dataTypes.Stringable;
import dataTypes.app.Int;
import dataTypes.app.Real;
import misc.Id;

/**
 * deals with wc3's binary encoding
 */
public abstract class Wc3bin {
	public static class StreamException extends IOException {
		private Stream _stream;
		
		public Stream getStream() {
			return _stream;
		}
		
		public StreamException(Stream stream) {
			_stream = stream;
		}
	}
	
	public static class Stream {
		private int _pos = 0;
		private List<Byte> _bytes = new ArrayList<>();
		
		private List<String> _logLines = new ArrayList<>();
		
		public List<String> getLogLines() {
			return _logLines;
		}
		
		public void log(String type, String label, Object val, int startPos, int len) {
			if (label == null) label = "?";
			
			StringBuilder bytesPart = new StringBuilder("");
			
			for (int i = startPos; i < startPos + len; i++) {
				if (bytesPart.length() > 0) bytesPart.append(" ");
				
				if (i < _bytes.size()) {					
					bytesPart.append(String.format("%02X", _bytes.get(i)));
				} else {
					bytesPart.append(String.format("?"));
				}
			}
			
			String line = String.format("%s [%s]: %s [%s]", label, type, val, bytesPart);
			
			_logLines.add(line);
		}
		
		public void printLog(OutputStream target) throws IOException {
			for (String line : getLogLines()) {
				target.write(line.getBytes(StandardCharsets.UTF_8));
				target.write(System.lineSeparator().getBytes(StandardCharsets.UTF_8));
			}
		}
		
		public void printLog() throws IOException {
			printLog(System.err);
			
			System.err.flush();
		}
		
		public int getPos() {
			return _pos;
		}
		
		public int size() {
			return _bytes.size();
		}
		
		private void ensureCap(int size) {
			int toAdd = size - _bytes.size();

			for (int i = 1; i <= toAdd; i++) {
				_bytes.add(null);
			}
		}

		private Integer readByte_priv() throws StreamException {
			try {
				int res = _bytes.get(_pos) & 0xFF;;
				
				_pos++;

				return res;
			} catch (IndexOutOfBoundsException e) {
				throw new StreamException(this);
			}
		}
		
		public Integer readByte(String label) throws StreamException {
			int oldPos = _pos;
			
			try {				
				Integer val = readByte_priv();
				
				log("byte", label, val, oldPos, _pos - oldPos);
				
				return val;
			} catch (StreamException e) {
				log("byte", label, null, oldPos, _pos - oldPos);
				
				throw e;
			}
		}

		public Integer readByte() throws StreamException {
			return readByte(null);
		}
		
		public void writeByte(int val) {
			ensureCap(_pos + 1);

			_bytes.set(_pos, (byte) val);
			_pos++;
		}
		
		private Short readShort_priv() throws StreamException {
			try {
				short res = _bytes.get(_pos);
				
				res += _bytes.get(_pos + 1) * 256;
				
				_pos += 2;
				
				return res;
			} catch (IndexOutOfBoundsException e) {
				throw new StreamException(this);
			}
		}
		
		public short readShort(String label) throws StreamException {
			int oldPos = _pos;
			
			try {				
				Short val = readShort_priv();
				
				log("short", label, val, oldPos, _pos - oldPos);
				
				return val;
			} catch (StreamException e) {
				log("short", label, null, oldPos, _pos - oldPos);
				
				throw e;
			}
		}
		
		public Short readShort() throws StreamException {
			return readShort(null);
		}
		
		public void writeShort(short val) {
			ensureCap(_pos + 2);
			
			_bytes.set(_pos, (byte) (val % 256));
			_bytes.set(_pos + 1, (byte) (val % (256^2) / 256));
			
			_pos += 2;
		}

		private Integer readInt_priv() throws StreamException {
			try {
				int res = _bytes.get(_pos);

				res += _bytes.get(_pos + 1) * 256;

				res += _bytes.get(_pos + 2) * 256 * 256;

				res += _bytes.get(_pos + 3) * 256 * 256 * 256;

				_pos += 4;

				return res;
			} catch (IndexOutOfBoundsException e) {
				throw new StreamException(this);
			}
		}

		public Integer readInt(String label) throws StreamException {
			int oldPos = _pos;
			
			try {				
				Integer val = readInt_priv();
				
				log("int", label, val, oldPos, _pos - oldPos);
				
				return val;
			} catch (StreamException e) {
				log("int", label, null, oldPos, _pos - oldPos);
				
				throw e;
			}
		}
		
		public Integer readInt() throws StreamException {
			return readInt(null);
		}
		
		public Int readWc3Int() throws StreamException {
			return Int.valueOf(readInt());
		}

		public Int readWc3Int(String label) throws StreamException {
			return Int.valueOf(readInt(label));
		}
		
		private final static byte[] _intBytes = new byte[4];
		
		private final static ByteBuffer _intBuf = ByteBuffer.wrap(_intBytes); 

		public void writeInt(int val) {
			ensureCap(_pos + 4);
			
			_intBuf.rewind();
			_intBuf.putInt(val);

			_bytes.set(_pos, _intBytes[0]);
			_bytes.set(_pos + 1, _intBytes[1]);
			_bytes.set(_pos + 2, _intBytes[2]);
			_bytes.set(_pos + 3, _intBytes[3]);

			_pos += 4;
		}

		public void writeInt(Int val) {
			writeInt(val.getVal());
		}
		
		private Character readChar_priv() throws StreamException {
			try {
				char res = (char) _bytes.get(_pos).byteValue();

				_pos += 1;

				return res;
			} catch (IndexOutOfBoundsException e) {
				throw new StreamException(this);
			}
		}

		public Character readChar(String label) throws StreamException {
			int oldPos = _pos;
			
			try {				
				Character val = readChar_priv();
				
				log("char", label, val, oldPos, _pos - oldPos);
				
				return val;
			} catch (StreamException e) {
				log("char", label, null, oldPos, _pos - oldPos);
				
				throw e;
			}
		}
		
		public Character readChar() throws StreamException {
			return readChar(null);
		}
		
		public void writeChar(char val) {
			ensureCap(_pos + 1);

			_bytes.set(_pos, (byte) val);

			_pos += 1;
		}

		private String readString_priv() throws StreamException {
			try {
				int cutPos = _pos;

				while ((cutPos < _bytes.size()) && (_bytes.get(cutPos) != 0)) {				
					cutPos += 1;
				}

				int size = cutPos - _pos;

				if (size == 0) {
					_pos = cutPos + 1;
					
					if (_pos >= _bytes.size()) {
						_pos = _bytes.size() - 1;
					}
					
					return "";
				}
				
				byte[] retBytes = new byte[size];

				for (int i = 0; i < size; i++) {
					retBytes[i] = _bytes.get(_pos + i);
				}
				
				_pos = cutPos + 1;

				if (_pos >= _bytes.size()) {
					_pos = _bytes.size() - 1;
				}

				return new String(retBytes, StandardCharsets.UTF_8);
			} catch (IndexOutOfBoundsException e) {
				throw new StreamException(this);
			}
		}
		
		public String readString(String label) throws StreamException {
			int oldPos = _pos;
			
			try {				
				String val = readString_priv();
				
				log("string", label, val, oldPos, _pos - oldPos);
				
				return val;
			} catch (StreamException e) {
				log("string", label, null, oldPos, _pos - oldPos);
				
				throw e;
			}
		}
		
		public String readString() throws StreamException {
			return readString(null);
		}
		
		public byte[] stringToByteArray(String val) {
			byte[] buf = val.getBytes(StandardCharsets.UTF_8);

			return val.getBytes(StandardCharsets.UTF_8);
		}

		public void writeString(String val) {
			if (val == null) val = "";
			
			byte[] valBytes = stringToByteArray(val);
			
			ensureCap(_pos + valBytes.length + 1);

			for (byte valByte : valBytes) {
				_bytes.set(_pos, valByte);

				_pos += 1;
			}
			
			_bytes.set(_pos, (byte) 0);

			_pos += 1;
		}

		public void writeString(Stringable val) {
			writeString(val.toString());
		}
		
		private Id readId_priv() throws StreamException {
			try {
				byte[] sub = new byte[4];

				sub[0] = _bytes.get(_pos);
				sub[1] = _bytes.get(_pos + 1);
				sub[2] = _bytes.get(_pos + 2);
				sub[3] = _bytes.get(_pos + 3);

				String res = null;

				try {
					res = new String(sub, "ascii");
				} catch (UnsupportedEncodingException e) {
					e.printStackTrace();
				}

				_pos += 4;

				return Id.valueOf(res);
			} catch (IndexOutOfBoundsException e) {
				throw new StreamException(this);
			}
		}

		public Id readId(String label) throws StreamException {
			int oldPos = _pos;
			
			try {				
				Id val = readId_priv();
				
				log("id", label, val, oldPos, _pos - oldPos);
				
				return val;
			} catch (StreamException e) {
				log("id", label, null, oldPos, _pos - oldPos);
				
				throw e;
			}
		}
		
		public Id readId() throws StreamException {
			return readId(null);
		}
		
		public void writeId(Id val) {
			byte[] sub = val.toString().getBytes(StandardCharsets.US_ASCII);

			ensureCap(_pos + 4);

			_bytes.set(_pos, sub[0]);
			_bytes.set(_pos + 1, sub[1]);
			_bytes.set(_pos + 2, sub[2]);
			_bytes.set(_pos + 3, sub[3]);
			
			_pos += 4;
		}

		private Float readFloat_priv() throws StreamException {
			try {
				byte[] sub = new byte[4];

				sub[0] = _bytes.get(_pos);
				sub[1] = _bytes.get(_pos + 1);
				sub[2] = _bytes.get(_pos + 2);
				sub[3] = _bytes.get(_pos + 3);

				float res = ByteBuffer.wrap(sub).order(ByteOrder.LITTLE_ENDIAN).getFloat();

				//float res = Float.intBitsToFloat((int) (((_bytes.get(_pos) & 0xFFL) << 24) | ((_bytes.get(_pos) & 0xFFL) << 16) | ((_bytes.get(_pos) & 0xFFL) << 8) | ((_bytes.get(_pos) & 0xFFL) << 0)));

				_pos += 4;
				
				return res;
			} catch (IndexOutOfBoundsException e) {
				_pos += 4;
				
				throw new StreamException(this);
			}
		}

		public Float readFloat(String label) throws StreamException {
			int oldPos = _pos;
			
			try {				
				Float val = readFloat_priv();
				
				log("float", label, val, oldPos, _pos - oldPos);
				
				return val;
			} catch (StreamException e) {
				log("float", label, null, oldPos, _pos - oldPos);
				
				throw e;
			}
		}
		
		public Float readFloat() throws StreamException {
			return readFloat(null);
		}
		
		public void writeFloat(float val) {
			ByteBuffer buf = ByteBuffer.allocate(4).putFloat(val);

			ensureCap(_pos + 4);

			_bytes.set(_pos, buf.get(0));
			_bytes.set(_pos + 1, buf.get(1));
			_bytes.set(_pos + 2, buf.get(2));
			_bytes.set(_pos + 3, buf.get(3));

			_pos += 4;
		}
		
		public void writeFloat(Real val) {
			writeFloat(val.toFloat());
		}
		
		public void rewind() {
			_logLines.clear();
			_pos = 0;
		}
		
		public void write(OutputStream outStream) throws IOException {			
			List<Byte> bytes = _bytes;

			byte[] buf = new byte[bytes.size()];
			
			for (int i = 0; i < bytes.size(); i++) {
				buf[i] = (Byte) bytes.get(i);
			}

			outStream.write(buf);

			outStream.flush();
		}
		
		public void write(File file) throws IOException {
			OutputStream outStream = new FileOutputStream(file);
			
			write(outStream);
			
			outStream.close();
		}
		
		public void read(InputStream inStream) throws IOException {
			int val;
			
			while ((val = inStream.read()) != -1) _bytes.add((byte) val);
		}
		
		public Stream(InputStream inStream) throws IOException {
			read(inStream);
		}
		
		public Stream(File file) throws IOException {
			InputStream inStream = new FileInputStream(file);
			
			read(inStream);
			
			inStream.close();
		}
		
		public Stream() {
		}
	}

	public static void checkFormatVer(String string, int targetVersion, int actualVersion) {
		assert (actualVersion == targetVersion);
	}
}
