package net.moonlightflower.wc3libs.bin;

import java.io.IOException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;

import net.moonlightflower.wc3libs.bin.BinStream.StreamException;

public class StdBinStream extends BinStream {
	public byte readByte(String label) throws IOException {
		logBegin();
		
		byte ret = readByte();
		
		log("byte", label, ret);
		
		return ret;
	}
	
	public int readWord() throws IOException {
		try {
			int ret = (readByte() & 0xFF) + ((readByte() & 0xFF) << 8);
			
			return ret;
		} catch (IndexOutOfBoundsException e) {
			throw new IOException();
		}
	}

	public int readWord(String label) throws IOException {
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
			int ret = readByte() + (readByte() << 8) + (readByte() << 16) + (readByte() << 24);
			
			return ret;
		} catch (IndexOutOfBoundsException e) {
			throw new IOException();
		}
	}
	
	public int readDoubleWord(String label) throws IOException {
		logBegin();
		
		int ret = readDoubleWord();
		
		log("dword", label, ret);
		
		return ret;
	}
	
	public String readString() throws StreamException {
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

	public String readString(String label) throws IOException {
		logBegin();
		
		String ret = readString();
		
		log("string", label, ret);
		
		return ret;
	}
	
	public StdBinStream() {
		super();
	}

	public StdBinStream(InputStream inStream) throws IOException {
		super(inStream);
	}
}
