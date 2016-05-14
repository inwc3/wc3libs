package dataTypes.app;

import dataTypes.DataType;

public class Char extends DataType {
	private char _val;
	
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

	public Char(char val) {
		_val = val;
	}

	@Override
	public DataType decode(Object val) {
		return new Char(val.toString().charAt(0));
	}
}