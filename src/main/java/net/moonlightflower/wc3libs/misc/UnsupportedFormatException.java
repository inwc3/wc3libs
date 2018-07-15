package net.moonlightflower.wc3libs.misc;

import javax.annotation.Nonnull;

public class UnsupportedFormatException extends Exception {
	private String _msg;
	
	@Override
	public String toString() {
		return _msg;
	}
	
	public UnsupportedFormatException(@Nonnull String msg) {
		_msg = msg;
	}
}
