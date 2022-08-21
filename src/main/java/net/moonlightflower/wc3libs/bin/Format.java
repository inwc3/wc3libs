package net.moonlightflower.wc3libs.bin;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.*;

public abstract class Format<T> {
	public static class InvalidFormatException extends RuntimeException {
		private final Integer _version;
		private final Format _format;

		@Override
		@Nonnull
		public String toString() {
			if (_format != null) return _format.toString();

			if (_version != null) return _version.toString();

			return super.toString();
		}

		public InvalidFormatException(int version) {
			_version = version;
			_format = null;
		}

		public InvalidFormatException(@Nonnull Format format) {
			_version = null;
			_format = format;
		}
	}

	private final static Map<Class<? extends Format>, Map<Integer, Format>> _instanceMap = new LinkedHashMap<>();

	@Nonnull
	public static <FormatType extends Format> Map<Integer, Format> getAll(@Nonnull Class<FormatType> formatClass) {
		return _instanceMap.get(formatClass);
	}

	@Nullable
	public static <FormatType extends Format> FormatType get(@Nonnull Class<FormatType> formatClass, int version) {
		//noinspection unchecked
		return (FormatType) _instanceMap.get(formatClass).get(version);
	}

	@Nullable
	public static Format valueOf(@Nonnull Integer version) {
		return null;
	}

	private final T _enumVal;

	@Nonnull
	public T toEnum() {
		return _enumVal;
	}
	
	private final Integer _version;

	@Nonnull
	public Integer getVersion() {
		return _version;
	}
	
	public Format(@Nonnull T enumVal, @Nullable Integer val) {
		_enumVal = enumVal;
		_version = val;

		if (_version != null) {
			if (!_instanceMap.containsKey(getClass())) _instanceMap.put(getClass(), new LinkedHashMap<>());

			if (_instanceMap.get(getClass()).containsKey(_version)) throw new RuntimeException(_version + " already used");

			_instanceMap.get(getClass()).put(_version, this);
		}
	}
}
