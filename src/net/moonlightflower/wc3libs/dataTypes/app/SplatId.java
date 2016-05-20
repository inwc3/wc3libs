package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.misc.ObjId;

public class SplatId extends ObjId {
	protected SplatId(String idString) {
		super(idString);
	}
	
	public static SplatId valueOf(ObjId id) {
		return new SplatId(id.toString());
	}
}
