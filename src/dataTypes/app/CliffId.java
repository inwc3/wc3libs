package dataTypes.app;

import misc.Id;
import misc.ObjId;

public class CliffId extends ObjId {
	protected CliffId(String idString) {
		super(idString);
	}
	
	public static CliffId valueOf(Id val) {
		return new CliffId(val.toString());
	}
}
