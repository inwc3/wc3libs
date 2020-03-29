package net.moonlightflower.wc3libs.bin;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

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
			for (int i = 0; i < len; i++) {
				_bytes.add(buf[i]);
			}
		}
	}

	public void read(@Nonnull File file) throws IOException {
		InputStream inStream = new FileInputStream(file);

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
		_inStream = new FileInputStream(file);

		read(_inStream);
	}
}
