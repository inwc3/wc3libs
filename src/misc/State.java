package misc;

import dataTypes.DataList;
import dataTypes.DataType;

abstract public class State<T extends DataType> {
	private StateInfo _info;
	
	public StateInfo getInfo() {
		return _info;
	}
	
	public T tryCastVal(DataType val) {
		Class<? extends DataType> type = getInfo().getType();
		
		try {
			if (type.newInstance() instanceof DataList) {
				return (T) new DataList().decode(val, type);
			} else {		
				return (T) type.newInstance().decode(val);
			}
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		}
		
		return null;
	}
	
	public State(StateInfo info) {
		_info = info;
	}
}
