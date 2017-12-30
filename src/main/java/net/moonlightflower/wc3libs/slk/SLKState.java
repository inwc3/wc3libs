package net.moonlightflower.wc3libs.slk;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.Serializable;
import net.moonlightflower.wc3libs.misc.FieldId;
import net.moonlightflower.wc3libs.misc.State;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SLKState<T extends DataType> extends State<T> {	
	private final T _defVal;

	@Nullable
	public T getDefVal() {
		return _defVal;
	}
	
	private final FieldId _field;

	@Nonnull
	public FieldId getFieldId() {
		return _field;
	}
	
	public SLKState(@Nonnull String idString, @Nonnull DataTypeInfo typeInfo, @Nullable T defVal) {
		super(typeInfo);
		
		_field = FieldId.valueOf(idString);
		_defVal = defVal;
	}
	
	public SLKState(@Nonnull String idString, @Nonnull DataTypeInfo typeInfo) {
		this(idString, typeInfo, null);
	}
}
