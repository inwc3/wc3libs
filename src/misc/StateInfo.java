package misc;

import dataTypes.DataType;

public class StateInfo {
	private Class<? extends DataType> _type;
	
	public Class<? extends DataType> getType() {
		return _type;
	}
	
	private boolean _isList;
	
	public boolean isList() {
		return _isList;
	}
	
	protected StateInfo(Class<? extends DataType> type, boolean isList) {
		_type = type;
		_isList = isList;
	}
	
	public static StateInfo listOf(Class<? extends DataType> type) {
		return new StateInfo(type, true);
	}
	
	public static StateInfo valueOf(Class<? extends DataType> type) {
		return new StateInfo(type, false);
	}
}
