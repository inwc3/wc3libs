package net.moonlightflower.wc3libs.misc;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;
import net.moonlightflower.wc3libs.dataTypes.Serializable;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

abstract public class State<T extends DataType> {
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

	@Override
	public String toString() {
		return _field.toString();
	}

	private DataTypeInfo _info;

	@Nonnull
	public DataTypeInfo getInfo() {
		return _info;
	}

	public T getVal(@Nullable DataType val) {
		if (getInfo().getType().isInstance(val)) {
			return (T) getInfo().getType().cast(val);
		}

		return null;
	}

	public T tryCastVal(DataType val) throws DataTypeInfo.CastException {
		return (T) getInfo().tryCastVal(val);
		/*Class<? extends DataType> type = getInfo().getType();
		
		try {
			if (type.newInstance() instanceof DataList) {
				return (T) new DataList().decode(val, type);
			} else {		
				return (T) type.newInstance().decode(val);
			}
		} catch (InstantiationException e) {
		} catch (IllegalAccessException e) {
		}
		
		return null;*/
	}

	private static class ClassAssignment {
		private final Map<FieldId, State> _states = new LinkedHashMap<>();

		private void addState(@Nonnull FieldId fieldId, @Nonnull State state) {
			_states.put(fieldId, state);
		}

		private ClassAssignment() {

		}
	}

	private final static Map<Class<? extends State>, ClassAssignment> _typeValues = new LinkedHashMap<>();

	private static void initializeClass(Class c) {
		try {
			Class.forName(c.getName(), true, c.getClassLoader());

			Class enclosingClass = c.getEnclosingClass();

			if (enclosingClass != null) initializeClass(enclosingClass);

			Class superClass = c.getSuperclass();

			if (superClass != null) initializeClass(superClass);
		} catch (ClassNotFoundException e) {
			throw new AssertionError(e);
		}
	}

	@Nonnull
	public static <T extends State> Collection<State> allValues(@Nonnull Class<T> specificClass) {
		initializeClass(specificClass);

		Collection<State> ret = new LinkedHashSet<>();

		for (Map.Entry<Class<? extends State>, ClassAssignment> entry : _typeValues.entrySet()) {
			Class<? extends State> otherClass = entry.getKey();

			if (otherClass.isAssignableFrom(specificClass)) {
				Collection<State> states = entry.getValue()._states.values();

				for (State state : states) {
					/*if (specificClass.isInstance(state)) {
						System.out.println("is instance");
						ret.add(specificClass.cast(state));
					}*/
					ret.add(state);
				}
			}
		}

		//if (!_typeValues.containsKey(specificClass)) return null;

		//return new LinkedHashSet<>(_typeValues.get(specificClass)._states.values());

		return ret;
	}

	@Nonnull
	public static <T extends State> Collection<T> values(@Nonnull Class<T> specificClass) {
		initializeClass(specificClass);

		Collection<T> ret = new LinkedHashSet<>();

		for (Map.Entry<Class<? extends State>, ClassAssignment> entry : _typeValues.entrySet()) {
			Class<? extends State> otherClass = entry.getKey();

			if (otherClass.isAssignableFrom(specificClass)) {
				Collection<State> states = entry.getValue()._states.values();

				for (State state : states) {
					if (specificClass.isInstance(state)) {
						ret.add(specificClass.cast(state));
					}
				}
			}
		}

		//if (!_typeValues.containsKey(specificClass)) return null;

		//return new LinkedHashSet<>(_typeValues.get(specificClass)._states.values());

		return ret;
	}

	@Nullable
	public static State valueByField(@Nonnull Class<? extends State> specificClass, @Nonnull FieldId fieldId) {
		for (State state : values(specificClass)) {
			if (state.getFieldId().equals(fieldId)) return state;
		}

		//return _typeValues.get(specificClass)._states.get(fieldId);
		return null;
	}

	public State(@Nonnull String fieldIdS, @Nonnull DataTypeInfo info, @Nullable T defVal) {
		_field = FieldId.valueOf(fieldIdS);
		_info = info;
		_defVal = defVal;

		Class<? extends State> specificClass = getClass();

		if (!_typeValues.containsKey(specificClass)) _typeValues.put(specificClass, new ClassAssignment());

		_typeValues.get(specificClass).addState(getFieldId(), this);
	}
}
