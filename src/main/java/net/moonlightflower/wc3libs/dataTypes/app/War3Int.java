package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.War3Num;

import javax.annotation.Nonnull;

public class War3Int extends DataType implements War3Num {
	public static String name() {
		return "War3Int";
	}
	
	public static War3Int getDefVal() {
		return new War3Int(0);
	}
	
	private int _val;
	
	public Integer getVal() {
		return _val;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof War3Int)
			return equals((War3Int) other);
		
		return super.equals(other);
	}
	
	public boolean equals(War3Int other) {
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
	
	protected War3Int(int val) {
		super();
		
		_val = val;
	}
	
	public War3Int() {
		
	}

	@Nonnull
	public static War3Int valueOf(int val) {
		return new War3Int(val);
	}

	public static War3Int valueOf(Object val) {
		try {
			return valueOf(Integer.parseInt(val.toString()));
		} catch (NumberFormatException e) {
		}
		
		return null;
	}
	
	@Override
	public War3Int decode(Object val) {
		return valueOf(val);
	}
	
	public static War3Int decodeStatic(Object val) {
		return valueOf(val);
	}
}