package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.misc.ObjId;

public class UnitId extends ObjId {
	protected UnitId(String idString) {
		super(idString);
	}
	
	public static UnitId valueOf(ObjId id) {
		return new UnitId(id.toString());
	}
}
