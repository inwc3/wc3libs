package misc;

import dataTypes.DataType;

public class SafeStateInfo<T extends DataType> extends StateInfo {
	public SafeStateInfo(Class<T> type, boolean asList) {
		super(type, asList);
	}
	
	public SafeStateInfo(Class<T> type) {
		this(type, false);
	}
}
