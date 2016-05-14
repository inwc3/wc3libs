package bin;

import dataTypes.DataType;
import misc.FieldId;
import misc.State;
import misc.StateInfo;

public class BinState<T extends DataType> extends State<T> {
	private FieldId _fieldId;
	
	public FieldId getFieldId() {
		return _fieldId;
	}
	
	private T _defVal = null;
	
	public T getDefVal() {
		return _defVal;
	}
	
	public BinState(StateInfo info) {
		super(info);
	}
	
	public BinState(StateInfo typeInfo, String idString, T defVal) {
		this(typeInfo);
		
		_fieldId = FieldId.valueOf(idString);
		_defVal = defVal;
	}
}
