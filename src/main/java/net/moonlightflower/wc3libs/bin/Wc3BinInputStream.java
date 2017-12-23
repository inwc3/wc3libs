package net.moonlightflower.wc3libs.bin;

import net.moonlightflower.wc3libs.dataTypes.app.Int;
import net.moonlightflower.wc3libs.dataTypes.app.Real;
import net.moonlightflower.wc3libs.dataTypes.app.Wc3String;
import net.moonlightflower.wc3libs.misc.Id;

import javax.annotation.Nonnull;
import java.io.*;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;

/**
 * deals with wc3's binary encoding
 */
public class Wc3BinInputStream extends BinInputStream {
	public Integer readUByte() throws StreamException {
		return readByte() & 0xFF;
	}
	
	public Integer readUByte(@Nonnull String label) throws StreamException {
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
	
	private short readInt16_priv() throws StreamException {
		try {
			short res = (short) (readByte() + readByte() * 256);
			
			return res;
		} catch (IndexOutOfBoundsException e) {
			throw new StreamException(this);
		}
	}
	
	public short readInt16(@Nonnull String label) throws StreamException {
		try {
			logBegin();
			
			Short val = readInt16_priv();
			
			log("short", label, val);
			
			return val;
		} catch (StreamException e) {
			log("short", label, null);
			
			throw e;
		}
	}
	
	public Short readInt16() throws StreamException {
		return readInt16_priv();
	}
	
	public int readUInt16() throws StreamException {
		return (readInt16() & 0xFFFF);
	}
	
	public Integer readInt32() throws StreamException {
		try {
			byte[] sub = readBytes(4);

			Integer res = ByteBuffer.wrap(sub).order(ByteOrder.LITTLE_ENDIAN).getInt();

			return res;
		} catch (IndexOutOfBoundsException e) {
			throw new StreamException(this);
		}
	}

	public Integer readInt32(@Nonnull String label) throws StreamException {
		try {
			logBegin();
			
			Integer val = readInt32();
			
			log("int", label, val);
			
			return val;
		} catch (StreamException e) {
			log("int", label, null);
			
			throw e;
		}
	}
	
	public long readUInt32() throws StreamException {
		return (readInt32() & 0xFFFFFFFF);
	}

	public long readUInt32(@Nonnull String label) throws StreamException {
		return (readInt32(label) & 0xFFFFFFFF);
	}

	public Int readWc3Int() throws StreamException {
		return Int.valueOf(readInt32());
	}

	public Int readWc3Int(@Nonnull String label) throws StreamException {
		return Int.valueOf(readInt32(label));
	}
	
	private Character readChar_priv() throws StreamException {
		try {
			char res = (char) readByte();

			return res;
		} catch (IndexOutOfBoundsException e) {
			throw new StreamException(this);
		}
	}

	public Character readChar(@Nonnull String label) throws StreamException {
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
	
	public String readString(@Nonnull String label) throws StreamException {
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

	public Id readId(@Nonnull String label) throws StreamException {
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

	private Float readFloat8_priv() throws StreamException {
		try {
			byte[] sub = readBytes(4);

			float res = ByteBuffer.wrap(sub).order(ByteOrder.LITTLE_ENDIAN).getFloat();

			//float res = Float.intBitsToFloat((int) (((_bytes.get(_pos) & 0xFFL) << 24) | ((_bytes.get(_pos) & 0xFFL) << 16) | ((_bytes.get(_pos) & 0xFFL) << 8) | ((_bytes.get(_pos) & 0xFFL) << 0)));
			
			return res;
		} catch (IndexOutOfBoundsException e) {
			throw new StreamException(this);
		}
	}

	public Float readFloat8(@Nonnull String label) throws StreamException {
		try {
			logBegin();
			
			Float val = readFloat8_priv();
			
			log("float", label, val);
			
			return val;
		} catch (StreamException e) {
			log("float", label, null);
			
			throw e;
		}
	}
	
	public Float readFloat8() throws StreamException {
		return readFloat8(null);
	}

	public Real readReal(@Nonnull String label) throws StreamException {
		return Real.valueOf(readFloat8(label));
	}

	public Real readReal() throws StreamException {
		return readReal(null);
	}

	public void pack() throws StreamException {
		Wc3BinOutputStream newStream = Packed.compress(this);

		_bytes = newStream._bytes;
	}

	public Wc3BinInputStream(@Nonnull InputStream inputStream) throws IOException {
		super(inputStream);
	}

	public Wc3BinInputStream(@Nonnull File file) throws IOException {
		super(file);
	}

	public static void checkFormatVer(@Nonnull String string, int targetVersion, int actualVersion) {
		assert (actualVersion == targetVersion) : string;
	}
}
