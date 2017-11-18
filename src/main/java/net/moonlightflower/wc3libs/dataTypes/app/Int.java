package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.Wc3Num;

public class Int extends DataType implements Wc3Num {
	public static String name() {
		return "Int";
	}
	
	public static Int getDefVal() {
		return new Int(0);
	}
	
	private int _val;
	
	public Integer getVal() {
		return _val;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof Int)
			return equals((Int) other);
		
		return super.equals(other);
	}
	
	public boolean equals(Int other) {
		return getVal().equals(other.getVal());
	}
	
	@Override
	public String toString() {
		return getVal().toString();
	}
	
	public int toInt() {
		return getVal();
	}
	
	@Override
	public float toFloat() {
		return getVal();
	}
	
	@Override
	public Object toSLKVal() {
		return getVal();
	}

	@Override
	public Object toTXTVal() {
		return getVal();
	}
	
	protected Int(int val) {
		super();
		
		_val = val;
	}
	
	public Int() {
		
	}
	
	public static Int valueOf(int val) {
		return new Int(val);
	}

	public static Int valueOf(Object val) {
		try {
			return valueOf(Integer.parseInt(val.toString()));
		} catch (NumberFormatException e) {
		}
		
		return null;
	}
	
	@Override
	public Int decode(Object val) {
		return valueOf(val);
	}
	
	public static Int decodeStatic(Object val) {
		return valueOf(val);
	}
}