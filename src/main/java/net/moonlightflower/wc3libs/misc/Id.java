package net.moonlightflower.wc3libs.misc;

import net.moonlightflower.wc3libs.dataTypes.app.War3String;

public class Id extends War3String {
	@Override
	public int hashCode() {
		if (getVal().length() != 4) return super.hashCode();

		return Math.decode(getVal(), Math.CODE_ASCII);
	}
	
	@Override
	public boolean equals(Object other) {
		if (this == other) return true;

		if (other instanceof Id) return equals((Id) other);

		return super.equals(other);
	}

	public boolean equals(Id other) {
		return hashCode() == other.hashCode();
	}
	
	/*@Override
	public int hashCode() {
		return toString().toLowerCase().hashCode();
	}
	
	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
		
		if (!(other instanceof Id)) return false;

		return (hashCode() == other.hashCode());
	}*/
	
	@Override
	public Object toSLKVal() {
		return getVal();
	}
	
	@Override
	public Object toTXTVal() {
		return getVal();
	}
	
	protected Id(String val) {
		super(val);
	}
	
	public static Id valueOf(String idString) {
		return new Id(idString);
	}

	@Override
	public Id decode(Object val) {
		return Id.valueOf(val.toString());
	}
	
	public Id lower() {
		return Id.valueOf(getVal().toLowerCase());
	}
}