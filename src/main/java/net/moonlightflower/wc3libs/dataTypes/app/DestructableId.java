package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.misc.ObjId;

import javax.annotation.Nonnull;

public class DestructableId extends ObjId {
	protected DestructableId(String idString) {
		super(idString);
	}

	@Nonnull
	public static DestructableId valueOf(ObjId id) {
		return new DestructableId(id.toString());
	}
}
