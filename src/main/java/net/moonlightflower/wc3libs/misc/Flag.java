package net.moonlightflower.wc3libs.misc;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.DataTypeInfo;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.LinkedHashSet;
import java.util.Map;

abstract public class Flag<T extends DataType> {
	private final FieldId _field;

	@Nonnull
	public FieldId getFieldId() {
		return _field;
	}

	private final int _pos;

	public int getPos() {
		return _pos;
	}

	@Override
	public String toString() {
		return _field.toString();
	}

	private static class ClassAssignment {
		private final Map<FieldId, Flag> _states = new LinkedHashMap<>();

		private void addState(@Nonnull FieldId fieldId, @Nonnull Flag state) {
			_states.put(fieldId, state);
		}

		private ClassAssignment() {

		}
	}

	private final static Map<Class<? extends Flag>, ClassAssignment> _typeValues = new LinkedHashMap<>();

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
	public static <T extends Flag> Collection<Flag> allValues(@Nonnull Class<T> specificClass) {
		initializeClass(specificClass);

		Collection<Flag> ret = new LinkedHashSet<>();

		for (Map.Entry<Class<? extends Flag>, ClassAssignment> entry : _typeValues.entrySet()) {
			Class<? extends Flag> otherClass = entry.getKey();

			if (otherClass.isAssignableFrom(specificClass)) {
				Collection<Flag> states = entry.getValue()._states.values();

				for (Flag state : states) {
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
	public static <T extends Flag> Collection<T> values(@Nonnull Class<T> specificClass) {
		initializeClass(specificClass);

		Collection<T> ret = new LinkedHashSet<>();

		for (Map.Entry<Class<? extends Flag>, ClassAssignment> entry : _typeValues.entrySet()) {
			Class<? extends Flag> otherClass = entry.getKey();

			if (otherClass.isAssignableFrom(specificClass)) {
				Collection<Flag> states = entry.getValue()._states.values();

				for (Flag state : states) {
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
	public static Flag valueByField(@Nonnull Class<? extends Flag> specificClass, @Nonnull FieldId fieldId) {
		for (Flag state : values(specificClass)) {
			if (state.getFieldId().equals(fieldId)) return state;
		}

		//return _typeValues.get(specificClass)._states.get(fieldId);
		return null;
	}

	public Flag(@Nonnull String fieldIdS, int pos) {
		_field = FieldId.valueOf(fieldIdS);
		_pos = pos;

		Class<? extends Flag> specificClass = getClass();

		if (!_typeValues.containsKey(specificClass)) _typeValues.put(specificClass, new ClassAssignment());

		_typeValues.get(specificClass).addState(getFieldId(), this);
	}
}
