package dataTypes.app;

import misc.ObjId;

public class SplatId extends ObjId {
	protected SplatId(String idString) {
		super(idString);
	}
	
	public static SplatId valueOf(ObjId id) {
		return new SplatId(id.toString());
	}
}
