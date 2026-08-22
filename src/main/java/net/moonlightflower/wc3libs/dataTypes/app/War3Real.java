package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.War3Num;
import java.util.Objects;

public class War3Real extends DataType implements War3Num {
	public static String name() {
		return "War3Real";
	}

	public final static War3Real getDefVal() {
		return new War3Real(0F);
	}
	
	private float _val;
	
	public Float getVal() {
		return _val;
	}

	/**
	 * Consistent with {@link #equals(Object)}, which this class overrides
	 * without having overridden this: instances that compare equal hashed
	 * differently, so a set or map key made of them did not work.
	 */
	@Override
	public int hashCode() {
		return Objects.hashCode(getVal());
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof War3Real)
			return equals((War3Real) other);

		return super.equals(other);
	}

	public boolean equals(War3Real other) {
		return getVal().equals(other.getVal());
	}

	@Override
	public String toString() {
		return getVal().toString();
	}
	
	@Override
	public float toFloat() {
		return _val;
	}
	
	public double toDouble() {
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

	public int intValue() {
		return getVal().intValue();
	}
	
	public War3Real(float val) {
		super();
		
		_val = val;
	}
	
	public static War3Real valueOf(float val) {
		return new War3Real(val);
	}

	public static War3Real valueOf(Object val) {
		try {
			War3Real ret = valueOf(Float.parseFloat(val.toString()));
			
			return ret;
		} catch (NumberFormatException e) {
		}
		
		return null;
	}
	
	public War3Real() {
		
	}
	
	@Override
	public War3Real decode(Object val) {
		return valueOf(val);
	}
	
	public static War3Real decodeStatic(Object val) {
		return valueOf(val);
	}
}