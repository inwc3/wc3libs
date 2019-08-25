package net.moonlightflower.wc3libs.slk;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.Serializable;
import net.moonlightflower.wc3libs.misc.FieldId;
import net.moonlightflower.wc3libs.misc.State;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

public class SLKState<T extends DataType> extends State<FieldId, T> {
	public SLKState(@Nonnull String fieldIdS, @Nonnull DataTypeInfo typeInfo, @Nullable T defVal) {
		super(FieldId.valueOf(fieldIdS), typeInfo, defVal);
	}

	public SLKState(@Nonnull String fieldIdS, @Nonnull DataTypeInfo typeInfo) {
		this(fieldIdS, typeInfo, (T) typeInfo.getDefVal());
	}

	public SLKState(@Nonnull String idString, @Nonnull Class<T> type) {
		this(idString, new DataTypeInfo(type));
	}

	public SLKState(@Nonnull String idString, @Nonnull Class<T> type, @Nullable T defVal) {
		this(idString, new DataTypeInfo(type), defVal);
	}
}
