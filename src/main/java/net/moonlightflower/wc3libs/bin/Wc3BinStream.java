package net.moonlightflower.wc3libs.bin;

import net.moonlightflower.wc3libs.dataTypes.Stringable;
import net.moonlightflower.wc3libs.dataTypes.app.Int;
import net.moonlightflower.wc3libs.dataTypes.app.Real;
import net.moonlightflower.wc3libs.dataTypes.app.Wc3String;
import net.moonlightflower.wc3libs.misc.Id;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

/**
 * deals with wc3's binary encoding
 */
public class Wc3BinStream extends BinStream {	
	public Integer readUByte() throws StreamException {
		return readByte() & 0xFF;
	}
	
	public Integer readUByte(String label) throws StreamException {
		try {
			logBegin();
			
			Integer val = readUByte();
			
			log("byte", label, val);
			
			return val;
		} catch (StreamException e) {
			log("byte", label, null);
			
			throw e;
		}
	}
	
	public void writeUByte(int val) {
		writeByte((byte) val);
	}
	
	private short readShort_priv() throws StreamException {
		try {
			short res = (short) (readByte() + readByte() * 256);
			
			return res;
		} catch (IndexOutOfBoundsException e) {
			throw new StreamException(this);
		}
	}
	
	public short readShort(String label) throws StreamException {
		try {
			logBegin();
			
			Short val = readShort_priv();
			
			log("short", label, val);
			
			return val;
		} catch (StreamException e) {
			log("short", label, null);
			
			throw e;
		}
	}
	
	public Short readShort() throws StreamException {
		return readShort_priv();
	}
	
	public int readUShort() throws StreamException {
		return (readShort() & 0xFFFF);
	}
	
	private final static byte[] _shortBytes = new byte[2];
	
	private final static ByteBuffer _shortBuf = ByteBuffer.wrap(_shortBytes);
	
	public void writeShort(short val) {
		_shortBuf.rewind();
		_shortBuf.order(ByteOrder.LITTLE_ENDIAN);
		
		_shortBuf.putShort(val);
		
		writeBytes(_shortBytes);
	}

	public void writeUShort(int val) {
		writeShort((short) val);
	}
	
	public Integer readInt() throws StreamException {
		try {
			byte[] sub = readBytes(4);

			Integer res = ByteBuffer.wrap(sub).order(ByteOrder.LITTLE_ENDIAN).getInt();

			return res;
		} catch (IndexOutOfBoundsException e) {
			throw new StreamException(this);
		}
	}

	public Integer readInt(String label) throws StreamException {
		try {
			logBegin();
			
			Integer val = readInt();
			
			log("int", label, val);
			
			return val;
		} catch (StreamException e) {
			log("int", label, null);
			
			throw e;
		}
	}
	
	public long readUInt() throws StreamException {
		return (readInt() & 0xFFFFFFFF);
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
		_intBuf.rewind();
		_intBuf.order(ByteOrder.LITTLE_ENDIAN);
		
		_intBuf.putInt(val);

		writeBytes(_intBytes);
	}

	public void writeInt(Int val) {
		writeInt(val.getVal());
	}
	
	public void writeUInt(long val) {
		writeInt((int) val);
	}
	
	private Character readChar_priv() throws StreamException {
		try {
			char res = (char) readByte();

			return res;
		} catch (IndexOutOfBoundsException e) {
			throw new StreamException(this);
		}
	}

	public Character readChar(String label) throws StreamException {
		try {	
			logBegin();
			
			Character val = readChar_priv();
			
			log("char", label, val);
			
			return val;
		} catch (StreamException e) {
			log("char", label, null);
			
			throw e;
		}
	}
	
	public Character readChar() throws StreamException {
		return readChar(null);
	}
	
	public void writeChar(char val) {
		writeByte((byte) val);
	}

	private String readString_priv() throws StreamException {
		try {
			int cutPos = getPos();

			while ((cutPos < size()) && (get(cutPos) != 0)) {				
				cutPos += 1;
			}

			int size = cutPos - getPos();

			if (size == 0) {				
				setPos(Math.min(cutPos + 1, size() - 1));
				
				return "";
			}
			
			byte[] retBytes = new byte[size];

			for (int i = 0; i < size; i++) {
				retBytes[i] = get(getPos() + i);
			}

			setPos(Math.min(cutPos + 1, size() - 1));

			return new String(retBytes, StandardCharsets.UTF_8);
		} catch (IndexOutOfBoundsException e) {
			throw new StreamException(this);
		}
	}
	
	public String readString(String label) throws StreamException {
		try {
			logBegin();
			
			String val = readString_priv();
			
			log("string", label, val);
			
			return val;
		} catch (StreamException e) {
			log("string", label, null);
			
			throw e;
		}
	}
	
	public String readString() throws StreamException {
		return readString(null);
	}
	
	public Wc3String readWc3String() throws StreamException {
		return Wc3String.valueOf(readString());
	}
	
	public byte[] stringToByteArray(String val) {
		return val.getBytes(StandardCharsets.UTF_8);
	}

	public void writeString(String val) {
		if (val == null) val = "";
		
		byte[] valBytes = stringToByteArray(val);
		
		writeBytes(valBytes);
		
		writeByte((byte) 0);
	}

	public void writeString(Stringable val) {
		if (val == null)
			writeString((String) null);
		else
			writeString(val.toString());
	}
	
	private Id readId_priv() throws StreamException {
		try {
			byte[] sub = readBytes(4);

			String res = null;

			try {
				res = new String(sub, "ascii");
			} catch (UnsupportedEncodingException e) {
				e.printStackTrace();
			}

			return Id.valueOf(res);
		} catch (IndexOutOfBoundsException e) {
			throw new StreamException(this);
		}
	}

	public Id readId(String label) throws StreamException {
		try {
			logBegin();
			
			Id val = readId_priv();
			
			log("id", label, val);
			
			return val;
		} catch (StreamException e) {
			log("id", label, null);
			
			throw e;
		}
	}
	
	public Id readId() throws StreamException {
		return readId(null);
	}
	
	public void writeId(Id val) {
		if (val == null) val = Id.valueOf("\0\0\0\0");
		
		byte[] sub = val.toString().getBytes(StandardCharsets.US_ASCII);

		writeBytes(sub);
	}

	private Float readFloat_priv() throws StreamException {
		try {
			byte[] sub = readBytes(4);

			float res = ByteBuffer.wrap(sub).order(ByteOrder.LITTLE_ENDIAN).getFloat();

			//float res = Float.intBitsToFloat((int) (((_bytes.get(_pos) & 0xFFL) << 24) | ((_bytes.get(_pos) & 0xFFL) << 16) | ((_bytes.get(_pos) & 0xFFL) << 8) | ((_bytes.get(_pos) & 0xFFL) << 0)));
			
			return res;
		} catch (IndexOutOfBoundsException e) {
			throw new StreamException(this);
		}
	}

	public Float readFloat(String label) throws StreamException {
		try {
			logBegin();
			
			Float val = readFloat_priv();
			
			log("float", label, val);
			
			return val;
		} catch (StreamException e) {
			log("float", label, null);
			
			throw e;
		}
	}
	
	public Float readFloat() throws StreamException {
		return readFloat(null);
	}
	
	public Real readReal() throws StreamException {
		return Real.valueOf(readFloat());
	}
	
	public void writeFloat(float val) {
		ByteBuffer buf = ByteBuffer.allocate(4);
		
		buf.order(ByteOrder.LITTLE_ENDIAN);
		
		buf.putFloat(val);

		writeBytes(buf.array());
	}
	
	public void writeFloat(Real val) {
		writeFloat(val.toFloat());
	}
	
	public void writeReal(Real val) {
		writeFloat(val);
	}
	
	public void pack() throws StreamException {
		Wc3BinStream newStream = Packed.compress(this);
		
		_bytes = newStream._bytes;
	}
	
	public Wc3BinStream(InputStream inStream) throws IOException {
		super(inStream);
	}
	
	public Wc3BinStream(File file) throws IOException {
		super(file);
	}
	
	public Wc3BinStream() {
		super();
	}

	public static void checkFormatVer(String string, int targetVersion, int actualVersion) {
		assert (actualVersion == targetVersion) : string;
	}
}
