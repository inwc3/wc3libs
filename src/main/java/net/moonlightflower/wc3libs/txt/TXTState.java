package net.moonlightflower.wc3libs.txt;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.Serializable;
import net.moonlightflower.wc3libs.misc.FieldId;
import net.moonlightflower.wc3libs.misc.State;

public class TXTState<T extends DataType> extends State<T> {
	private final T _defVal;
	
	public T getDefVal() {
		return _defVal;
	}
	
	private final TXTSectionId _sectionId;
	
	public TXTSectionId getSectionId() {
		return _sectionId;
	}
	
	private final FieldId _fieldId;
	
	public FieldId getFieldId() {
		return _fieldId;
	}

	public TXTState(TXTSectionId sectionId, String fieldIdString, DataTypeInfo typeInfo, T defVal) {
		super(typeInfo);
		
		_sectionId = sectionId;
		_fieldId = FieldId.valueOf(fieldIdString);
		_defVal = defVal;
	}
	
	public TXTState(TXTSectionId sectionId, String fieldIdString, DataTypeInfo typeInfo) {
		this(sectionId, fieldIdString, typeInfo, null);
	}
}
