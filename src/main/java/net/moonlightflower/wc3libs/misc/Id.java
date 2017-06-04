package net.moonlightflower.wc3libs.misc;

import net.moonlightflower.wc3libs.dataTypes.app.Wc3String;

public class Id extends Wc3String {
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
}