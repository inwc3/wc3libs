package net.moonlightflower.wc3libs.bin;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.misc.MetaFieldId;
import net.moonlightflower.wc3libs.misc.State;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class MetaState<T extends DataType> extends State<MetaFieldId, T> {
	public MetaState(@Nonnull String idS, @Nonnull DataTypeInfo typeInfo, @Nullable T defVal) {
		super(MetaFieldId.valueOf(idS), typeInfo, defVal);
	}
	
	public MetaState(@Nonnull String idString, @Nonnull DataTypeInfo typeInfo) {
		this(idString, typeInfo, null);
	}
}
