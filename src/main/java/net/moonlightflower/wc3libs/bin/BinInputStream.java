package net.moonlightflower.wc3libs.bin;

import javax.annotation.Nonnull;
import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

public class BinInputStream extends BinStream implements AutoCloseable {
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

			_bytes.copyInto(_pos, vals, 0, size);

			_pos += size;

			return vals;
		} catch (IndexOutOfBoundsException | NegativeArraySizeException e) {
			throw new StreamException(this, String.valueOf(e.getMessage()));
		}
	}

	public byte[] readBytes(int size, String label) throws StreamException {
		logBegin();

		byte[] vals = readBytes(size);

		log("bytes", label, vals);

		return vals;
	}

	public void rewind() {
		_logLines.clear();
		_pos = 0;
	}
	
	public void rewind(long delta) {
		delta = Math.min(_pos, delta);
		
		_pos -= delta;
	}

	public void skip(long delta) {
		_pos += delta;
	}

	public void read(@Nonnull InputStream inStream) throws IOException {
		byte[] buf = new byte[8192];
		int len;

		while ((len = inStream.read(buf, 0, buf.length)) != -1) {
			_bytes.addAll(buf, len);
		}
	}

	public void read(@Nonnull File file) throws IOException {
		try (InputStream inStream = Files.newInputStream(file.toPath())) {
			read(inStream);
		}
	}

	@Nonnull
	public byte[] writeToByteArray() {
		return _bytes.toArray();
	}

	protected String readNullTerminatedStringUtf8() throws StreamException {
		try {
			final long startPos = getPos();
			final long cutPos = _bytes.indexOfZero(startPos);

			final int len = Math.toIntExact(cutPos - startPos);
			final byte[] retBytes = new byte[len];

			_bytes.copyInto(startPos, retBytes, 0, len);

			// Consume terminating zero when present, otherwise move to EOF.
			setPos((cutPos < size()) ? (cutPos + 1) : cutPos);

			return new String(retBytes, StandardCharsets.UTF_8);
		} catch (IndexOutOfBoundsException | ArithmeticException e) {
			throw new StreamException(this);
		}
	}

	private InputStream _inStream;

	@Override
	public void close() throws IOException {
		_inStream.close();
	}

	public BinInputStream(@Nonnull InputStream inStream) throws IOException {
		_inStream = inStream;

		read(_inStream);
	}

	public BinInputStream(@Nonnull File file) throws IOException {
		_inStream = Files.newInputStream(file.toPath());

		read(_inStream);
	}

}
