package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.ObjId;

public class UberSplatId extends ObjId {
	protected UberSplatId(String idString) {
		super(idString);
	}
	
	public static UberSplatId valueOf(Id val) {
		return new UberSplatId(val.toString());
	}
}
