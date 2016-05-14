package dataTypes.app;

import misc.ObjId;

public class DestructableId extends ObjId {
	protected DestructableId(String idString) {
		super(idString);
	}

	public static DestructableId valueOf(ObjId id) {
		return new DestructableId(id.toString());
	}
}
