package net.moonlightflower.wc3libs.bin;

import java.util.ArrayList;
import java.util.List;

import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MetaState<T> {
	private T _defVal = null;

	@Nullable
	public T getDefVal() {
		return _defVal;
	}
	
	private MetaFieldId _id;
	
	public MetaFieldId getId() {
		return _id;
	}
	
	private final static List<MetaState<?>> all = new ArrayList<>();
	
	@Override
	public String toString() {
		return _id.toString();
	}
	
	public MetaState(@Nonnull String idString, @Nonnull DataTypeInfo typeInfo, @Nullable T defVal) {
		_id = MetaFieldId.valueOf(idString);
		_defVal = defVal;
		
		all.add(this);
	}
	
	public MetaState(@Nonnull String idString, @Nonnull DataTypeInfo typeInfo) {
		this(idString, typeInfo, null);
	}
}
