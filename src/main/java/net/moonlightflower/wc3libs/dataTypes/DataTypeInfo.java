package net.moonlightflower.wc3libs.dataTypes;

import net.moonlightflower.wc3libs.misc.TypeInfo;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class DataTypeInfo extends TypeInfo<DataType> {
	@Nonnull
	@Override
	public Class<? extends DataType> getType() {
		return super.getType();
	}

	@Nullable
	@Override
	public DataTypeInfo[] getGenerics() {
		TypeInfo[] superGenerics = super.getGenerics();
		
		if (superGenerics == null) return null;
		
		DataTypeInfo[] ret = new DataTypeInfo[superGenerics.length];
		
		for (int i = 0; i < superGenerics.length; i++) {
			ret[i] = (DataTypeInfo) superGenerics[i];
		}
		
		return ret;
	}

	@Nullable
	public DataType getDefVal() {		
		try {
			Method method = getType().getDeclaredMethod("getDefVal");
			
			return (DataType) method.invoke(null);
		} catch (IllegalAccessException | SecurityException | NoSuchMethodException | InvocationTargetException | IllegalArgumentException ignored) {
		}

        return null;
	}

	@Nullable
	public String getTranslatorSection() {		
		try {
			Method method = getType().getDeclaredMethod("getTranslatorSection");
			
			return (String) method.invoke(null);
		} catch (IllegalAccessException | SecurityException | NoSuchMethodException | InvocationTargetException | IllegalArgumentException ignored) {
		}

        return null;
	}

	public static class CastException extends Exception {
		public CastException(@Nonnull Exception enclosedException) {
			super(enclosedException);
		}
	}

	@Nullable
	public DataType tryCastVal(@Nullable DataType val) throws CastException {
		if (val == null) return null;		
		
		Class<? extends DataType> type = getType();
		
		try {
			if (type == DataList.class) {
				return new DataList<>(getGenerics()[0]).decode(val);
			} else {
				Method c = type.getDeclaredMethod("decodeStatic", Object.class);

				assert (c != null) : "decodeStatic not declared on " + type;
				//if (c == null) return null;
				
				return ((DataType) c.invoke(null, val));
				//return type.newInstance().decode(val);
			}
		} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException | NoSuchMethodException | SecurityException e) {
			throw new CastException(e);
		}
	}
	
	public DataTypeInfo(@Nonnull Class<? extends DataType> type, Class<? extends DataType> generics) {
		super(type, new DataTypeInfo(generics));
	}
	
	public DataTypeInfo(@Nonnull Class<? extends DataType> type, DataTypeInfo[] generics) {
		super(type, generics);
	}
	
	public DataTypeInfo(@Nonnull Class<? extends DataType> type, @Nonnull DataTypeInfo generics) {
		this(type, generics.getType());
	}

	public DataTypeInfo(@Nonnull Class<? extends DataType> type) {
		super(type);
	}
}
