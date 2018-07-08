package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.misc.ObjId;

import javax.annotation.Nonnull;

public class BuffCode extends ObjId {
	protected BuffCode(String idString) {
		super(idString);
	}

	@Nonnull
	public static BuffCode valueOf(ObjId id) {
		return new BuffCode(id.toString());
	}

	public static BuffCode decodeStatic(Object val) {
		if (val == null) return null;

		return BuffCode.valueOf(ObjId.valueOf(val.toString()));
	}
}
