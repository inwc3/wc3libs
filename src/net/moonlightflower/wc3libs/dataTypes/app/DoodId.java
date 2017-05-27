package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.misc.ObjId;

public class DoodId extends ObjId {
	protected DoodId(String idString) {
		super(idString);
	}

	public static DoodId valueOf(ObjId id) {
		return new DoodId(id.toString());
	}
}
