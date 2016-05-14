package dataTypes.app;

import misc.ObjId;

public class SpawnId extends ObjId {
	protected SpawnId(String idString) {
		super(idString);
	}
	
	public static SpawnId valueOf(ObjId id) {
		return new SpawnId(id.toString());
	}
}
