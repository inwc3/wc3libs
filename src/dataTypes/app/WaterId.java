package dataTypes.app;

import misc.Id;
import misc.ObjId;

public class WaterId extends ObjId {
	protected WaterId(String idString) {
		super(idString);
	}
	
	public static WaterId valueOf(Id val) {
		return new WaterId(val.toString());
	}
}
