package net.moonlightflower.wc3libs.bin;

import net.moonlightflower.wc3libs.misc.Id;

public class MetaFieldId extends Id {	
	protected MetaFieldId(String idString) {
		super(idString);
	}
	
	static public MetaFieldId valueOf(String idString) {
		return new MetaFieldId(idString);
	}
	
	public static MetaFieldId valueOf(Id id) {
		return valueOf(id.toString());
	}
}