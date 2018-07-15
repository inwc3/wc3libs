package net.moonlightflower.wc3libs.bin;

import net.moonlightflower.wc3libs.dataTypes.app.War3Int;
import net.moonlightflower.wc3libs.dataTypes.app.War3Real;
import net.moonlightflower.wc3libs.dataTypes.app.War3String;
import net.moonlightflower.wc3libs.misc.Id;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * deals with wc3's binary encoding
 */
public class Wc3BinInputStream extends BinInputStream {
	@Nonnull
	public Byte readByte(@Nonnull String label) throws StreamException {
		Byte val = null;

		try {
			logBegin();

			val = readByte();

			return val;
		} finally {
			log("byte", label, val);
		}
	}

	@Override
	@Nonnull
	public byte[] readBytes(int size, @Nonnull String label) throws StreamException {
		byte[] val = null;

		try {
			logBegin();

			val = readBytes(size);

			return val;
		} finally {
			log("bytes", label, val);
		}
	}

	public short readUByte() throws StreamException {
		return (short) (readByte() & 0xFF);
	}

	@Nonnull
	public Short readUByte(@Nonnull String label) throws StreamException {
		Short val = null;

		try {
			logBegin();
			
			val = readUByte();
			
			return val;
		} finally {
			log("byte", label, val);
		}
	}

	public short readInt8() throws StreamException {
		try {
			return (short) readByte();
		} catch (IndexOutOfBoundsException e) {
			throw new StreamException(this);
		}
	}

	@Nonnull
	public Short readInt8(@Nonnull String label) throws StreamException {
		Short val = null;

		try {
			logBegin();

			val = readInt8();

			return val;
		} finally {
			log("int8", label, val);
		}
	}

	public short readUInt8() throws StreamException {
		return (short) (readInt8() & 0xFF);
	}

	@Nonnull
	public Short readUInt8(@Nonnull String label) throws StreamException {
		Short val = null;

		try {
			logBegin();

			val = readUInt8();

			return val;
		} finally {
			log("uint8", label, val);
		}
	}

	public short readInt16() throws StreamException {
		try {
			byte[] sub = readBytes(2);
			
			return ByteBuffer.wrap(sub).order(ByteOrder.LITTLE_ENDIAN).getShort();
		} catch (IndexOutOfBoundsException e) {
			throw new StreamException(this);
		}
	}

	@Nonnull
	public Short readInt16(@Nonnull String label) throws StreamException {
		Short val = null;

		try {
			logBegin();
			
			val = readInt16();
			
			return val;
		} finally {
			log("short", label, val);
		}
	}

	public int readUInt16() throws StreamException {
		return (readInt16() & 0xFFFF);
	}

	@Nonnull
	public Integer readUInt16(@Nonnull String label) throws StreamException {
		Integer val = null;

		try {
			logBegin();

			val = readUInt16();

			return val;
		} finally {
			log("uint16", label, val);
		}
	}

	public int readInt32() throws StreamException {
		try {
			byte[] sub = readBytes(4);

			return ByteBuffer.wrap(sub).order(ByteOrder.LITTLE_ENDIAN).getInt();
		} catch (IndexOutOfBoundsException e) {
			throw new StreamException(this);
		}
	}

	@Nonnull
	public Integer readInt32(@Nonnull String label) throws StreamException {
		Integer val = null;

		try {
			logBegin();
			
			val = readInt32();
			
			return val;
		} finally {
			log("int32", label, val);
		}
	}

	public long readUInt32() throws StreamException {
		//noinspection PointlessBitwiseExpression
		return (readInt32() & 0xFFFFFFFF);
	}

	@Nonnull
	public Long readUInt32(@Nonnull String label) throws StreamException {
		Long val = null;

		try {
			logBegin();

			val = readUInt32();

			return val;
		} finally {
			log("uint32", label, val);
		}
	}

	@Nonnull
	public War3Int readWc3Int() throws StreamException {
		return War3Int.valueOf(readInt32());
	}

	@Nonnull
	public War3Int readWc3Int(@Nonnull String label) throws StreamException {
		War3Int val = null;

		try {
			logBegin();

			val = readWc3Int();

			return val;
		} finally {
			log("wc3int", label, val);
		}
	}
	
	public char readChar() throws StreamException {
		try {
			return (char) readByte();
		} catch (IndexOutOfBoundsException e) {
			throw new StreamException(this);
		}
	}

	@Nonnull
	public Character readChar(@Nullable String label) throws StreamException {
		Character val = null;

		try {	
			logBegin();
			
			val = readChar();
			
			return val;
		} finally {
			log("char", label, val);
		}
	}

	@Nonnull
	public String readString() throws StreamException {
		try {
			long cutPos = getPos();

			while ((cutPos < size()) && (get(cutPos) != 0)) {				
				cutPos += 1;
			}

			long size = cutPos - getPos();

			if (size == 0) {				
				setPos(Math.min(cutPos + 1, size() - 1));
				
				return "";
			}

			StringBuilder sb = new StringBuilder();

			while (size > 0) {
				int sizeI = (int) size;

				byte[] retBytes = new byte[sizeI];

				for (int i = 0; i < size; i++) {
					retBytes[i] = get(getPos() + i);
				}

				setPos(Math.min(cutPos + 1, size() - 1));

				//TODO: split bytes so that utf8 is sure to yield valid strings
				sb.append(new String(retBytes, StandardCharsets.UTF_8));

				size -= sizeI;
			}

			return sb.toString();
		} catch (IndexOutOfBoundsException e) {
			throw new StreamException(this);
		}
	}

	@Nonnull
	public String readString(@Nullable String label) throws StreamException {
		String val = null;

		try {
			logBegin();
			
			val = readString();
			
			return val;
		} finally {
			log("string", label, val);
		}
	}

	@Nonnull
	public War3String readWc3String() throws StreamException {
		return War3String.valueOf(readString());
	}

	@Nonnull
	public War3String readWc3String(@Nullable String label) throws StreamException {
		War3String val = null;

		try {
			logBegin();

			val = readWc3String();

			return val;
		} finally {
			log("wc3string", label, val);
		}
	}

	@Nonnull
	public Id readId() throws StreamException {
		Id val = null;

		try {
			byte[] sub = readBytes(4);

			return Id.valueOf(new String(sub, StandardCharsets.US_ASCII));
		} catch (IndexOutOfBoundsException e) {
			throw new StreamException(this);
		}
	}

	@Nonnull
	public Id readId(@Nullable String label) throws StreamException {
		Id val = null;

		try {
			logBegin();
			
			val = readId();
			
			return val;
		} finally {
			log("id", label, val);
		}
	}

	public float readFloat32() throws StreamException {
		try {
			byte[] sub = readBytes(4);

			//float res = Float.intBitsToFloat((int) (((_bytes.get(_pos) & 0xFFL) << 24) | ((_bytes.get(_pos) & 0xFFL) << 16) | ((_bytes.get(_pos) & 0xFFL) << 8) | ((_bytes.get(_pos) & 0xFFL) << 0)));
			
			return ByteBuffer.wrap(sub).order(ByteOrder.LITTLE_ENDIAN).getFloat();
		} catch (IndexOutOfBoundsException e) {
			throw new StreamException(this);
		}
	}

	@Nonnull
	public Float readFloat32(@Nullable String label) throws StreamException {
		Float val = null;

		try {
			logBegin();
			
			val = readFloat32();
			
			return val;
		} finally {
			log("float32", label, val);
		}
	}

	@Nonnull
	public War3Real readReal() throws StreamException {
		return War3Real.valueOf(readFloat32());
	}

	@Nonnull
	public War3Real readReal(@Nullable String label) throws StreamException {
		War3Real val = null;

		try {
			logBegin();

			val = War3Real.valueOf(readFloat32());

			return val;
		} finally {
			log("real", label, val);
		}
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

	public void checkFormatVersion(int expectedVersion, int actualVersion) throws StreamException {
		if (actualVersion != expectedVersion) throw new StreamException(this, "version mismatch" + System.lineSeparator() + "expected: " + expectedVersion + System.lineSeparator() + "actual: " + actualVersion);
	}

	@Nonnull
	public <EncodingFormat extends Format> EncodingFormat getFormat(@Nonnull Class<EncodingFormat> formatClass, int actualVersion) throws StreamException {
		Map<Integer, Format> all = Format.getAll(formatClass);

		if (!all.containsKey(actualVersion)) {
			StringBuilder sb = new StringBuilder();

			for (Format<?> format : all.values()) {
				if (sb.length() > 0) sb.append(System.lineSeparator());

				sb.append(format.getVersion());
			}

			throw new StreamException(this, "unknown format " + actualVersion + System.lineSeparator() + "supported formats:" + System.lineSeparator() + sb.toString());
		}

		try {
			//noinspection unchecked
			return (EncodingFormat) formatClass.getMethod("valueOf", Integer.class).invoke(null, actualVersion);
		} catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
			throw new RuntimeException(e);
		}
	}
}
