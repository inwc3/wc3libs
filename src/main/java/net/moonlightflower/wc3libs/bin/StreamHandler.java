package net.moonlightflower.wc3libs.bin;

import javax.annotation.Nonnull;

public abstract class StreamHandler {
	protected abstract void read(@Nonnull Wc3BinInputStream stream);
	protected abstract void write(@Nonnull Wc3BinInputStream stream);
	
	private Wc3BinInputStream _stream;

	@Nonnull
	public Wc3BinInputStream getStream() {
		return _stream;
	}
	
	public void execReading() {
		read(getStream());
	}

	public void execWriting() {
		write(getStream());
	}
	
	public StreamHandler(@Nonnull Wc3BinInputStream stream) {
		_stream = stream;
	}
}
