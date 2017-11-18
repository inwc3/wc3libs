package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.Stringable;

public class Wc3String extends DataType implements Stringable {
	public static String name() {
		return "String";
	}

	public static Wc3String getDefVal() {
		return null;
	}
	
	private String _val;
	
	public String getVal() {
		return _val;
	}

	@Override
	public String toString() {
		return getVal();
	}
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	@Override
	public boolean equals(Object other) {
		if (other instanceof Wc3String)
			return equals((Wc3String) other);
		if (other instanceof String)
			return equals(Wc3String.valueOf(other));

		return super.equals(other);
	}

	public boolean equals(Wc3String other) {
		return (hashCode() == other.hashCode());
	}

	@Override
	public Object toSLKVal() {
		return toString();
	}

	@Override
	public Object toTXTVal() {
		return toString();
	}
	
	public Wc3String() {
		
	}
	
	public Wc3String(String val, String... aliases) {
		_val = val;
	}
	
	public static Wc3String valueOf(Object val) {
		if (val == null) return null;
		
		return new Wc3String(val.toString());
	}

	@Override
	public Wc3String decode(Object val) {
		return valueOf(val);
	}
	
	public static Wc3String decodeStatic(Object val) {
		return valueOf(val);
	}
}