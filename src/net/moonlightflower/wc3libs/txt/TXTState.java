package net.moonlightflower.wc3libs.txt;

import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.misc.FieldId;

public class TXTState<T> {
	private T _defVal = null;
	
	public T getDefVal() {
		return _defVal;
	}
	
	private TXTSectionId _sectionId;
	
	public TXTSectionId getSectionId() {
		return _sectionId;
	}
	
	private FieldId _fieldId;
	
	public FieldId getFieldId() {
		return _fieldId;
	}
	
	public TXTState(TXTSectionId sectionId, String fieldIdString, DataTypeInfo typeInfo) {
		_sectionId = sectionId;
		_fieldId = FieldId.valueOf(fieldIdString);
	}

	public TXTState(TXTSectionId sectionId, String fieldIdString, DataTypeInfo typeInfo, T defVal) {
		this(sectionId, fieldIdString, typeInfo);
		
		_defVal = defVal;
	}
}
