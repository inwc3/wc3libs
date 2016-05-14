package bin;

import bin.Wc3bin.Stream;

public abstract class StreamHandler {
	protected abstract void read(Stream stream);
	protected abstract void write(Stream stream);
	
	Stream _stream;
	
	public Stream getStream() {
		return _stream;
	}
	
	public void execReading() {
		read(getStream());
	}

	public void execWriting() {
		write(getStream());
	}
	
	public StreamHandler(Stream stream) {
		_stream = stream;
	}
}
