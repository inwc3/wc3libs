package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.misc.ObjId;

public class AbilId extends ObjId {
	protected AbilId(String idString) {
		super(idString);
	}

	public static AbilId valueOf(ObjId id) {
		return new AbilId(id.toString());
	}

	public static AbilId decodeStatic(Object val) {
		if (val == null) return null;

		return AbilId.valueOf(ObjId.valueOf(val.toString()));
	}
}
