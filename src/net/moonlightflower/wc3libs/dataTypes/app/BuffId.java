package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.misc.ObjId;

public class BuffId extends ObjId {
	protected BuffId(String idString) {
		super(idString);
	}
	
	public static BuffId valueOf(ObjId id) {
		return new BuffId(id.toString());
	}
}
