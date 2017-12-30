package net.moonlightflower.wc3libs.txt;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.Serializable;
import net.moonlightflower.wc3libs.misc.FieldId;
import net.moonlightflower.wc3libs.misc.State;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class TXTState<T extends DataType> extends State<T> {
	private final T _defVal;

	@Nullable
	public T getDefVal() {
		return _defVal;
	}
	
	private final TXTSectionId _sectionId;

	@Nonnull
	public TXTSectionId getSectionId() {
		return _sectionId;
	}
	
	private final FieldId _fieldId;

	@Nonnull
	public FieldId getFieldId() {
		return _fieldId;
	}

	public TXTState(@Nonnull TXTSectionId sectionId, @Nonnull String fieldIdString, @Nonnull DataTypeInfo typeInfo, @Nullable T defVal) {
		super(typeInfo);
		
		_sectionId = sectionId;
		_fieldId = FieldId.valueOf(fieldIdString);
		_defVal = defVal;
	}
	
	public TXTState(@Nonnull TXTSectionId sectionId, @Nonnull String fieldIdString, @Nonnull DataTypeInfo typeInfo) {
		this(sectionId, fieldIdString, typeInfo, null);
	}
}
