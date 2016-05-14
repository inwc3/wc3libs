package txt;

import misc.FieldId;

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
	
	public TXTState(TXTSectionId sectionId, String fieldIdString) {
		_sectionId = sectionId;
		_fieldId = FieldId.valueOf(fieldIdString);
	}

	public TXTState(TXTSectionId sectionId, String fieldIdString, T defVal) {
		this(sectionId, fieldIdString);
		
		_defVal = defVal;
	}
}
