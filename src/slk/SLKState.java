package slk;

import dataTypes.DataType;
import misc.FieldId;
import misc.State;
import misc.StateInfo;

public class SLKState<T extends DataType> extends State<T> {	
	private T _defVal = null;
	
	public T getDefVal() {
		return _defVal;
	}
	
	private FieldId _field;
	
	public FieldId getFieldId() {
		return _field;
	}
	
	public SLKState(String idString, StateInfo typeInfo) {
		super(typeInfo);
		
		_field = FieldId.valueOf(idString);
	}

	public SLKState(String idString, StateInfo typeInfo, T defVal) {
		this(idString, typeInfo);
		
		_defVal = defVal;
	}
}
