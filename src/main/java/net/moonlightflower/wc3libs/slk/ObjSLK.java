package net.moonlightflower.wc3libs.slk;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.misc.FieldId;
import net.moonlightflower.wc3libs.misc.ObjId;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public abstract class ObjSLK<Self extends SLK<Self, ObjIdType, ObjType>, ObjIdType extends ObjId, ObjType extends SLK.Obj<? extends ObjIdType>> extends SLK<Self, ObjIdType, ObjType> {
    public static class State<T extends DataType> extends SLKState<T> {
        public State(@Nonnull String idString, @Nonnull DataTypeInfo typeInfo, @Nullable T defVal) {
            super(idString, typeInfo, defVal);
        }

        public State(@Nonnull String idString, @Nonnull DataTypeInfo typeInfo) {
            this(idString, typeInfo, (T) typeInfo.getDefVal());
        }

        public State(@Nonnull String idString, @Nonnull Class<T> type) {
            this(idString, new DataTypeInfo(type));
        }

        public State(@Nonnull String idString, @Nonnull Class<T> type, @Nullable T defVal) {
            this(idString, new DataTypeInfo(type), defVal);
        }
    }
}
