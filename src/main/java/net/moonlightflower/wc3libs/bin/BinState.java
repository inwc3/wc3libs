package net.moonlightflower.wc3libs.bin;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.misc.FieldId;
import net.moonlightflower.wc3libs.misc.State;

public class BinState<T extends DataType> extends State<T> {
	private FieldId _fieldId;
	
	public FieldId getFieldId() {
		return _fieldId;
	}
	
	private T _defVal = null;
	
	public T getDefVal() {
		return _defVal;
	}
	
	public BinState(DataTypeInfo info) {
		super(info);
	}
	
	public BinState(DataTypeInfo typeInfo, String idString, T defVal) {
		this(typeInfo);
		
		_fieldId = FieldId.valueOf(idString);
		_defVal = defVal;
	}
}
