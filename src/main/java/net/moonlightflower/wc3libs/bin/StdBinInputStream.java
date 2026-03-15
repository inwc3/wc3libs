package net.moonlightflower.wc3libs.bin;

import javax.annotation.Nonnull;
import java.io.File;
import java.io.IOException;
import java.io.InputStream;

public class StdBinInputStream extends BinInputStream {
	public byte readByte(@Nonnull String label) throws IOException {
		logBegin();
		
		byte ret = readByte();
		
		log("byte", label, ret);
		
		return ret;
	}
	
	public int readWord() throws IOException {
		try {
			return (readByte() & 0xFF) + ((readByte() & 0xFF) << 8);
		} catch (IndexOutOfBoundsException e) {
			throw new IOException();
		}
	}

	public int readWord(@Nonnull String label) throws IOException {
		logBegin();
		
		int ret = readWord();
		
		log("word", label, ret);
		
		return ret;
	}
	
	public int readUWord() throws IOException {
		return readWord() & 0xFFFF;
	}
	
	public int readUWord(String label) throws IOException {
		return readWord(label) & 0xFFFF;
	}
	
	public int readDoubleWord() throws IOException {
		try {
			return readByte() + (readByte() << 8) + (readByte() << 16) + (readByte() << 24);
		} catch (IndexOutOfBoundsException e) {
			throw new IOException();
		}
	}
	
	public int readDoubleWord(@Nonnull String label) throws IOException {
		logBegin();
		
		int ret = readDoubleWord();
		
		log("dword", label, ret);
		
		return ret;
	}
	
	public String readString() throws StreamException {
		return readNullTerminatedStringUtf8();
	}

	public String readString(@Nonnull String label) throws IOException {
		logBegin();
		
		String ret = readString();
		
		log("string", label, ret);
		
		return ret;
	}

	public StdBinInputStream(@Nonnull InputStream inStream) throws IOException {
		super(inStream);
	}

	public StdBinInputStream(@Nonnull File file) throws IOException {
		super(file);
	}
}
