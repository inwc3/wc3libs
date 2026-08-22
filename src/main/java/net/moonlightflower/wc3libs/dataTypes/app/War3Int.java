package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.War3Num;

import javax.annotation.Nonnull;
import java.util.Objects;

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
		if (other instanceof War3Int war3Int)
			return equals(war3Int);
		
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

	public static War3Int valueOf(Integer val) {
		if (val == null) return null;

		return valueOf(val.intValue());
	}

	public static War3Int valueOf(Object val) throws DataTypeInfo.CastException {
		try {
			return valueOf(Integer.parseInt(val.toString()));
		} catch (NumberFormatException e) {
			throw new DataTypeInfo.CastException(e);
		}
	}
	
	@Override
	public War3Int decode(Object val) throws DataTypeInfo.CastException {
		return valueOf(val);
	}
	
	public static War3Int decodeStatic(Object val) throws DataTypeInfo.CastException {
		return valueOf(val);
	}
}