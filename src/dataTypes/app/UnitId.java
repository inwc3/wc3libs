package dataTypes.app;

import misc.ObjId;

public class UnitId extends ObjId {
	protected UnitId(String idString) {
		super(idString);
	}
	
	public static UnitId valueOf(ObjId id) {
		return new UnitId(id.toString());
	}
}
