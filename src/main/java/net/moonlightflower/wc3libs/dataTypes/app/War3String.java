package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.Stringable;

import java.util.Objects;

public class War3String extends DataType implements Stringable {
	private static final War3String EMPTY_STRING = new War3String("");

	public static String name() {
		return "String";
	}

	public static War3String getDefVal() {
		return EMPTY_STRING;
	}

  private String _val;

  public void set_val(String _val) {
    this._val = _val;
  }

	public String getVal() {
		return _val;
	}

	@Override
	public String toString() {
		return getVal();
	}

	@Override
	public int hashCode() {
		return Objects.hashCode(getVal());
	}

	@Override
	public boolean equals(Object other) {
		if (other instanceof War3String war3String)
			return equals(war3String);
		if (other instanceof String)
			return equals(War3String.valueOf(other));

		return super.equals(other);
	}

	/**
	 * Compares the strings.
	 * <p>
	 * This used to compare hash codes, which for strings is a 32-bit digest of
	 * the value rather than the value: any two colliding strings compared equal.
	 * These are used as object and field identifiers throughout the library, so
	 * a collision silently merged two unrelated entries.
	 */
	public boolean equals(War3String other) {
		return Objects.equals(getVal(), other.getVal());
	}

	@Override
	public Object toSLKVal() {
		return toString();
	}

	@Override
	public Object toTXTVal() {
		return toString();
	}

	public War3String() {

	}

	public War3String(String val, String... aliases) {
		_val = val;
	}

	public static War3String valueOf(Object val) {
		if (val == null) return null;

		return new War3String(val.toString());
	}

	@Override
	public War3String decode(Object val) {
		return valueOf(val);
	}

	public static War3String decodeStatic(Object val) {
		return valueOf(val);
	}
}
