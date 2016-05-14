package dataTypes.app;

import misc.Id;
import misc.ObjId;

public class UberSplatId extends ObjId {
	protected UberSplatId(String idString) {
		super(idString);
	}
	
	public static UberSplatId valueOf(Id val) {
		return new UberSplatId(val.toString());
	}
}
