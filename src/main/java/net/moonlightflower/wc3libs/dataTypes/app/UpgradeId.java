package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.misc.Id;
import net.moonlightflower.wc3libs.misc.ObjId;

public class UpgradeId extends ObjId {
	protected UpgradeId(String idString) {
		super(idString);
	}
	
	public static UpgradeId valueOf(Id val) {
		return new UpgradeId(val.toString());
	}
}
