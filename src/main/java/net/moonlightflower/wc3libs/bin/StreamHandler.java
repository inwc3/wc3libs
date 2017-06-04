package net.moonlightflower.wc3libs.bin;

import net.moonlightflower.wc3libs.bin.Wc3BinStream;

public abstract class StreamHandler {
	protected abstract void read(Wc3BinStream stream);
	protected abstract void write(Wc3BinStream stream);
	
	private Wc3BinStream _stream;
	
	public Wc3BinStream getStream() {
		return _stream;
	}
	
	public void execReading() {
		read(getStream());
	}

	public void execWriting() {
		write(getStream());
	}
	
	public StreamHandler(Wc3BinStream stream) {
		_stream = stream;
	}
}
