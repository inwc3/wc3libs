package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.misc.ObjId;

public class AbilCode extends ObjId {
	protected AbilCode(String idString) {
		super(idString);
	}

	public static AbilCode valueOf(ObjId id) {
		return new AbilCode(id.toString());
	}

	public static AbilCode decodeStatic(Object val) {
		if (val == null) return null;

		return AbilCode.valueOf(ObjId.valueOf(val.toString()));
	}
}
