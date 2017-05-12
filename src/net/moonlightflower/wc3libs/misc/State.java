package net.moonlightflower.wc3libs.misc;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.Serializable;

abstract public class State<T extends Serializable> {
	private DataTypeInfo _info;
	
	public DataTypeInfo getInfo() {
		return _info;
	}
	
	public T tryCastVal(DataType val) {
		return (T) getInfo().tryCastVal(val);
		/*Class<? extends DataType> type = getInfo().getType();
		
		try {
			if (type.newInstance() instanceof DataList) {
				return (T) new DataList().decode(val, type);
			} else {		
				return (T) type.newInstance().decode(val);
			}
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		}
		
		return null;*/
	}
	
	public State(DataTypeInfo info) {
		_info = info;
	}
}
