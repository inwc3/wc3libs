package dataTypes.app;

import misc.ObjId;

public class AbilId extends ObjId {
	protected AbilId(String idString) {
		super(idString);
	}

	public static AbilId valueOf(ObjId id) {
		return new AbilId(id.toString());
	}
}
