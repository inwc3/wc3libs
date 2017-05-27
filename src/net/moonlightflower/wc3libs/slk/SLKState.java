package net.moonlightflower.wc3libs.slk;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.Serializable;
import net.moonlightflower.wc3libs.misc.FieldId;
import net.moonlightflower.wc3libs.misc.State;

public class SLKState<T extends DataType> extends State<T> {	
	private final T _defVal;
	
	public T getDefVal() {
		return _defVal;
	}
	
	private final FieldId _field;
	
	public FieldId getFieldId() {
		return _field;
	}
	
	public SLKState(String idString, DataTypeInfo typeInfo, T defVal) {
		super(typeInfo);
		
		_field = FieldId.valueOf(idString);
		_defVal = defVal;
	}
	
	public SLKState(String idString, DataTypeInfo typeInfo) {
		this(idString, typeInfo, null);
	}
}
