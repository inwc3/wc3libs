package dataTypes.app;

import misc.Id;
import misc.ObjId;

public class UpgradeId extends ObjId {
	protected UpgradeId(String idString) {
		super(idString);
	}
	
	public static UpgradeId valueOf(Id val) {
		return new UpgradeId(val.toString());
	}
}
