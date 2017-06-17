package net.moonlightflower.wc3libs.misc;

import net.moonlightflower.wc3libs.dataTypes.app.Wc3String;

public class Id extends Wc3String {
	@Override
	public int hashCode() {
		if (getVal().length() != 4) return super.hashCode();

		int ret = 0;
		
		ret += ((int) getVal().charAt(0)) * 256*256*256;
		ret += ((int) getVal().charAt(1)) * 256*256;
		ret += ((int) getVal().charAt(2)) * 256;
		ret += ((int) getVal().charAt(3)) * 1;
		
		return ret;
	}
	
	@Override
	public boolean equals(Object other) {
		if (this == other) return true;

		return ((other instanceof Id) || (other instanceof Id)) && (hashCode() == other.hashCode());
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
	
	static public Id valueOf(String idString) {
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