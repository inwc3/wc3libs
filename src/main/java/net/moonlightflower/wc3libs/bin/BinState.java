package net.moonlightflower.wc3libs.bin;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.misc.FieldId;
import net.moonlightflower.wc3libs.misc.State;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class BinState<T extends DataType> extends State<T> {
	private FieldId _fieldId;
	
	public FieldId getFieldId() {
		return _fieldId;
	}
	
	@Override
	public String toString() {
		return _fieldId.toString();
	}
	
	private T _defVal = null;

	@Nullable
	public T getDefVal() {
		return _defVal;
	}
	
	public BinState(@Nonnull DataTypeInfo info) {
		super(info);
	}
	
	public BinState(@Nonnull DataTypeInfo typeInfo, @Nonnull String idString, @Nullable T defVal) {
		this(typeInfo);
		
		_fieldId = FieldId.valueOf(idString);
		_defVal = defVal;
	}
}
