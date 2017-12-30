package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.Wc3Num;

import javax.annotation.Nonnull;

public class Wc3Int extends DataType implements Wc3Num {
	public static String name() {
		return "Wc3Int";
	}
	
	public static Wc3Int getDefVal() {
		return new Wc3Int(0);
	}
	
	private int _val;
	
	public Integer getVal() {
		return _val;
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof Wc3Int)
			return equals((Wc3Int) other);
		
		return super.equals(other);
	}
	
	public boolean equals(Wc3Int other) {
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
	
	protected Wc3Int(int val) {
		super();
		
		_val = val;
	}
	
	public Wc3Int() {
		
	}

	@Nonnull
	public static Wc3Int valueOf(int val) {
		return new Wc3Int(val);
	}

	public static Wc3Int valueOf(Object val) {
		try {
			return valueOf(Integer.parseInt(val.toString()));
		} catch (NumberFormatException e) {
		}
		
		return null;
	}
	
	@Override
	public Wc3Int decode(Object val) {
		return valueOf(val);
	}
	
	public static Wc3Int decodeStatic(Object val) {
		return valueOf(val);
	}
}