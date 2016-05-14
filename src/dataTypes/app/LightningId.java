package dataTypes.app;

import misc.ObjId;

public class LightningId extends ObjId {
	protected LightningId(String idString) {
		super(idString);
	}
	
	public static LightningId valueOf(ObjId id) {
		return new LightningId(id.toString());
	}
}
