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
			
			for (int i = 0; i < vals.length; i++) {
				vals[i] = _bytes.get(_pos++);
			}
			
			return vals;
		} catch (IndexOutOfBoundsException e) {
			throw new StreamException(this, e.getMessage());
		}
	}

	public byte[] readBytes(int size, String label) throws StreamException {
		try {
			byte[] vals = new byte[size];

			logBegin();

			for (int i = 0; i < vals.length; i++) {
				vals[i] = _bytes.get(_pos++);
			}

			log("bytes", label, vals);

			return vals;
		} catch (IndexOutOfBoundsException e) {
			throw new StreamException(this);
		}
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
		byte[] buf = new byte[1024];
		int len;

		while ((len = inStream.read(buf, 0, buf.length)) != -1) {
			_bytes.addAll(buf, len);
		}
	}

	public void read(@Nonnull File file) throws IOException {
		InputStream inStream = Files.newInputStream(file.toPath());

		read(inStream);

		inStream.close();
	}

	@Nonnull
	public byte[] writeToByteArray() {
		ByteList bytes = _bytes;

		if (bytes.size() > Integer.MAX_VALUE) throw new RuntimeException("size out of bounds " + bytes.size());

		byte[] buf = new byte[(int) bytes.size()];

		for (int i = 0; i < bytes.size(); i++) {
			buf[i] = bytes.get(i);
		}

		return buf;
	}

	protected String readNullTerminatedStringUtf8() throws StreamException {
		try {
			final long startPos = getPos();
			long cutPos = startPos;

			while ((cutPos < size()) && (get(cutPos) != 0)) {
				cutPos += 1;
			}

			final int len = Math.toIntExact(cutPos - startPos);
			final byte[] retBytes = new byte[len];

			for (int i = 0; i < len; i++) {
				retBytes[i] = get(startPos + i);
			}

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
