package net.moonlightflower.wc3libs.slk;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.misc.ObjId;

import java.util.ArrayList;
import java.util.List;

public abstract class ObjSLK<Self extends SLK<Self, ObjIdType, ObjType>, ObjIdType extends ObjId, ObjType extends SLK.Obj<? extends ObjIdType>> extends SLK<Self, ObjIdType, ObjType> {
    static public class State<T extends DataType> extends SLKState<T> {
        private static List<State> _values = new ArrayList<>();

        public static List<State> values() {
            return _values;
        }

        public State(String idString, DataTypeInfo typeInfo, T defVal) {
            super(idString, typeInfo, defVal);

            _values.add(this);
        }

        public State(String idString, DataTypeInfo typeInfo) {
            this(idString, typeInfo, (T) typeInfo.getDefVal());
        }

        public State(String idString, Class<T> type) {
            this(idString, new DataTypeInfo(type));
        }

        public State(String idString, Class<T> type, T defVal) {
            this(idString, new DataTypeInfo(type), defVal);
        }
    }
}
