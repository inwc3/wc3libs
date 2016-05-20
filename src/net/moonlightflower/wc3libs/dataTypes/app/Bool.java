package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.DataType;

public class Bool extends DataType {
	public static String name() {
		return "Bool";
	}
	
	public static Bool getDefVal() {
		return new Bool(false);
	}
	
	private boolean _val;

	public boolean getVal() {
		return _val;
	}

	@Override
	public String toString() {
		return new Boolean(getVal()).toString();
	}
	
	@Override
	public Object toSLKVal() {
		return getVal();
	}

	@Override
	public Object toTXTVal() {
		return getVal();
	}
	
	private Bool(boolean val) {
		_val = val;
	}
	
	public static Bool valueOf(boolean val) {
		return new Bool(val);
	}

	public static Bool valueOf(Object val) {
		String s = val.toString();
		
		switch (s) {
		case "0":
		case "false":
			return valueOf(false);
		case "1":
		case "true":
			return valueOf(true);
		}
		
		return null;
	}
	
	@Override
	public Bool decode(Object val) {
		return valueOf(val);
	}
	
	public static Bool decodeStatic(Object val) {
		return valueOf(val);
	}
}
