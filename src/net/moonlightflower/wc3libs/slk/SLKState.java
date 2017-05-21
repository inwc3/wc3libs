package net.moonlightflower.wc3libs.slk;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.Serializable;
import net.moonlightflower.wc3libs.misc.FieldId;
import net.moonlightflower.wc3libs.misc.State;

public class SLKState<T extends DataType> extends State<T> {	
	private T _defVal = null;
	
	public T getDefVal() {
		return _defVal;
	}
	
	private FieldId _field;
	
	public FieldId getFieldId() {
		return _field;
	}
	
	public SLKState(String idString, DataTypeInfo typeInfo) {
		super(typeInfo);
		
		_field = FieldId.valueOf(idString);
	}

	public SLKState(String idString, DataTypeInfo typeInfo, T defVal) {
		this(idString, typeInfo);
		
		_defVal = defVal;
	}
}
