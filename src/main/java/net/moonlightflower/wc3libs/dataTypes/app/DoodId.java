package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.misc.ObjId;

import javax.annotation.Nonnull;

public class DoodId extends ObjId {
	protected DoodId(String idString) {
		super(idString);
	}

	@Nonnull
	public static DoodId valueOf(ObjId id) {
		return new DoodId(id.toString());
	}
}
