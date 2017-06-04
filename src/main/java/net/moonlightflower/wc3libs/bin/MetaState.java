package net.moonlightflower.wc3libs.bin;

import java.util.ArrayList;
import java.util.List;

import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;

public class MetaState<T> {
	private T _defVal = null;
	
	public T getDefVal() {
		return _defVal;
	}
	
	private MetaFieldId _id;
	
	public MetaFieldId getId() {
		return _id;
	}
	
	private static List<MetaState<?>> all = new ArrayList<>();
	
	@Override
	public String toString() {
		return _id.toString();
	}
	
	public MetaState(String idString, DataTypeInfo typeInfo, T defVal) {
		_id = MetaFieldId.valueOf(idString);
		_defVal = defVal;
		
		all.add(this);
	}
	
	public MetaState(String idString, DataTypeInfo typeInfo) {
		this(idString, typeInfo, null);
	}
}
