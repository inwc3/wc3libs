package dataTypes.app;

import misc.ObjId;

public class BuffId extends ObjId {
	protected BuffId(String idString) {
		super(idString);
	}
	
	public static BuffId valueOf(ObjId id) {
		return new BuffId(id.toString());
	}
}
