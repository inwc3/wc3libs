package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.misc.ObjId;

public class LightningId extends ObjId {
	protected LightningId(String idString) {
		super(idString);
	}
	
	public static LightningId valueOf(ObjId id) {
		return new LightningId(id.toString());
	}
}
