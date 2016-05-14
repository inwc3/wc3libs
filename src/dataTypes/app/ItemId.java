package dataTypes.app;

import misc.Id;
import misc.ObjId;

public class ItemId extends ObjId {
	protected ItemId(String idString) {
		super(idString);
	}

	public static ItemId valueOf(Id val) {
		return new ItemId(val.toString());
	}
}
