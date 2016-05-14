package dataTypes;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

import dataTypes.app.Bool;
import dataTypes.app.Real;
import misc.TypeInfo;

public class DataTypeInfo extends TypeInfo<DataType> {
	@Override
	public Class<? extends DataType> getType() {
		return super.getType();
	}
	
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
	
	public DataType getDefVal() {		
		try {
			Method method = getType().getDeclaredMethod("getDefVal");
			
			return (DataType) method.invoke(null);
		} catch (IllegalAccessException e) {
		} catch (IllegalArgumentException e) {
		} catch (InvocationTargetException e) {
		} catch (NoSuchMethodException e) {
		} catch (SecurityException e) {
		}
		
		return null;
	}
	
	public String getTranslatorSection() {		
		try {
			Method method = getType().getDeclaredMethod("getTranslatorSection");
			
			return (String) method.invoke(null);
		} catch (IllegalAccessException e) {
		} catch (IllegalArgumentException e) {
		} catch (InvocationTargetException e) {
		} catch (NoSuchMethodException e) {
		} catch (SecurityException e) {
		}
		
		return null;
	}
	
	public DataType tryCastVal(DataType val) {
		if (val == null) return null;		
		
		Class<? extends DataType> type = getType();
		
		try {
			if (type == DataList.class) {
				return new DataList<>(getGenerics()[0]).decode(val);
			} else {
				Method c = type.getDeclaredMethod("decodeStatic", Object.class);

				if (c == null) return null;
				
				return ((DataType) c.invoke(null, val));
				//return type.newInstance().decode(val);
			}
		} catch (IllegalAccessException e) {
			System.out.println("illegal");
		} catch (IllegalArgumentException e) {
			System.out.println("illegalArg");
		} catch (InvocationTargetException e) {
			System.out.println("invoc");
		} catch (NoSuchMethodException e) {
			System.out.println("noSuch");
		} catch (SecurityException e) {
			System.out.println("secu");
		}
		
		return null;
	}
	
	public DataTypeInfo(Class<? extends DataType> type, Class<? extends DataType> generics) {
		super(type, new DataTypeInfo(generics));
	}
	
	public DataTypeInfo(Class<? extends DataType> type, DataTypeInfo[] generics) {
		super(type, generics);
	}
	
	public DataTypeInfo(Class<? extends DataType> type, DataTypeInfo generics) {
		this(type, generics.getType());
	}

	public DataTypeInfo(Class<? extends DataType> type) {
		super(type);
	}
}
