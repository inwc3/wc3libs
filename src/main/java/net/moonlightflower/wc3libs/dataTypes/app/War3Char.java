package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.DataType;

import javax.annotation.Nonnull;

public class War3Char extends DataType {
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

	private War3Char(char val) {
		_val = val;
	}

	@Nonnull
	public static War3Char valueOf(char val) {
		return new War3Char(val);
	}

	@Override
	public DataType decode(Object val) {
		return new War3Char(val.toString().charAt(0));
	}
}