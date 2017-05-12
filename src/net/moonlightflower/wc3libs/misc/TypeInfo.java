package net.moonlightflower.wc3libs.misc;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class TypeInfo<boundType> {
	private Class<? extends boundType> _type;
	
	public Class<? extends boundType> getType() {
		return (Class<? extends boundType>) _type;
	}
	
	private TypeInfo<? extends boundType>[] _generics;
	
	public TypeInfo<? extends boundType>[] getGenerics() {
		return (TypeInfo<? extends boundType>[]) _generics;
	}
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		
		sb.append(getClass() + " -> ");
		
		sb.append(getType());
		
		TypeInfo<? extends boundType>[] generics = getGenerics();
		
		if (generics != null) {
			sb.append("[");
			
			for (int i = 0; i < generics.length; i++) {
				sb.append(generics[i]);
				
				if (i > 0) {
					sb.append(";");
				}
			}
			
			sb.append("]");
		}
		
		return sb.toString();
	}

	public String getTypeName() {		
		/*try {
			Method method = getType().getDeclaredMethod("name");
			
			if (method != null) {
				return (String) method.invoke(null);
			}
		} catch (IllegalAccessException e) {
		} catch (IllegalArgumentException e) {
		} catch (InvocationTargetException e) {
		} catch (NoSuchMethodException e) {
		} catch (SecurityException e) {
		}
		
		return getType().getSimpleName();*/
		
		StringBuilder sb = new StringBuilder();
		
		sb.append(getType().getSimpleName());
		
		TypeInfo<? extends boundType>[] generics = getGenerics();
		
		if (generics != null) {
			sb.append("<");
			
			for (int i = 0; i < generics.length; i++) {
				sb.append(generics[i].getTypeName());
				
				if (i > 0) {
					sb.append(";");
				}
			}
			
			sb.append(">");
		}
		
		return sb.toString();
	}
	
	@Override
	public int hashCode() {
		return toString().hashCode();
	}
	
	@Override
	public boolean equals(Object other) {
		if (this == other) return true;
		
		if (!(other instanceof TypeInfo)) return false;

		return (hashCode() == other.hashCode());
	}
	
	public TypeInfo(Class<? extends boundType> type, Class<? extends boundType>[] generics) {
		_type = type;
		
		TypeInfo[] genericsInfo = new TypeInfo[generics.length];
		int c = 0;
		
		for (Class<? extends boundType> generic : generics) {
			genericsInfo[c] = new TypeInfo<boundType>(generic);
			
			c++;
		}
		
		_generics = genericsInfo;
	}

	public TypeInfo(Class<? extends boundType> type, TypeInfo<? extends boundType>[] generics) {
		_type = type;
		_generics = generics;
	}
	
	public TypeInfo(Class<? extends boundType> type, TypeInfo<? extends boundType> generics) {
		this(type, new TypeInfo[]{generics});
	}
	
	public TypeInfo(Class<? extends boundType> type, Class<? extends boundType> generics) {
		this(type, new Class[]{generics});
	}
	
	public TypeInfo(Class<? extends boundType> type) {
		_type = type;
		_generics = null;
	}
}
