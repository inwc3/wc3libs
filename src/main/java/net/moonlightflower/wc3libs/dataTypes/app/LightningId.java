package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.misc.ObjId;

import javax.annotation.Nonnull;

public class LightningId extends ObjId {
	protected LightningId(String idString) {
		super(idString);
	}

	@Nonnull
	public static LightningId valueOf(ObjId id) {
		return new LightningId(id.toString());
	}
}
