package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.DataType;

import javax.annotation.Nonnull;

public class Char extends DataType {
	private final char _val;

	@Nonnull
	public Character getVal() {
		return _val;
	}
	
	@Override
	public Object toSLKVal() {
		return toString();
	}

	@Override
	public Object toTXTVal() {
		return toString();
	}

	private Char(char val) {
		_val = val;
	}

	@Nonnull
	public static Char valueOf(char val) {
		return new Char(val);
	}

	@Override
	public DataType decode(Object val) {
		return new Char(val.toString().charAt(0));
	}
}