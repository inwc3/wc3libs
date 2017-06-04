package net.moonlightflower.wc3libs.bin;

public abstract class Format<T> {
	private T _enumVal;
	
	public T toEnum() {
		return _enumVal;
	}
	
	private int _version;
	
	public int getVersion() {
		return _version;
	}
	
	public Format(T enumVal, int val) {
		_enumVal = enumVal;
		_version = val;
	}
}
