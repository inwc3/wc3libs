package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.misc.ObjId;

import javax.annotation.Nonnull;

public class UnitId extends ObjId {
	protected UnitId(String idString) {
		super(idString);
	}

	@Nonnull
	public static UnitId valueOf(ObjId id) {
		return new UnitId(id.toString());
	}

	public static UnitId decodeStatic(Object val) {
		if (val == null) return null;

		return UnitId.valueOf(ObjId.valueOf(val.toString()));
	}
}
