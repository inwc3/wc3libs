package net.moonlightflower.wc3libs.misc;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.Serializable;

import javax.annotation.Nonnull;

abstract public class State<T extends DataType> {
	private DataTypeInfo _info;

	@Nonnull
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
	
	public State(@Nonnull DataTypeInfo info) {
		_info = info;
	}
}
