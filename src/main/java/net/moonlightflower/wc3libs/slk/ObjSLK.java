package net.moonlightflower.wc3libs.slk;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.misc.ObjId;

import java.util.*;

public abstract class ObjSLK<Self extends SLK<Self, ObjIdType, ObjType>, ObjIdType extends ObjId, ObjType extends SLK.Obj<? extends ObjIdType>> extends SLK<Self, ObjIdType, ObjType> {
    static public class State<T extends DataType> extends SLKState<T> {
        private static Map<Class<? extends State>, Collection<State>> _typeValues = new LinkedHashMap<>();

        public static Collection<? extends State> values(Class<? extends State> specificClass) {
            if (!_typeValues.containsKey(specificClass)) return null;

            return _typeValues.get(specificClass);
        }

        public State(String idString, DataTypeInfo typeInfo, T defVal) {
            super(idString, typeInfo, defVal);

            Class<? extends State> specificClass = getClass();

            if (!_typeValues.containsKey(specificClass)) _typeValues.put(specificClass, new LinkedHashSet<>());

            _typeValues.get(specificClass).add(this);
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
