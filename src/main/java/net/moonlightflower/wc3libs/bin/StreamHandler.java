package net.moonlightflower.wc3libs.bin;

public abstract class StreamHandler {
	protected abstract void read(Wc3BinInputStream stream);
	protected abstract void write(Wc3BinInputStream stream);
	
	private Wc3BinInputStream _stream;
	
	public Wc3BinInputStream getStream() {
		return _stream;
	}
	
	public void execReading() {
		read(getStream());
	}

	public void execWriting() {
		write(getStream());
	}
	
	public StreamHandler(Wc3BinInputStream stream) {
		_stream = stream;
	}
}
