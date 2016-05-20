package net.moonlightflower.wc3libs.dataTypes;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;

import net.moonlightflower.wc3libs.dataTypes.app.Wc3String;

public class DataList<T extends DataType> extends DataType implements List<T> {	
	private List<T> _list = new ArrayList<>();
	
	public Object getVal() {
		return _list;
	}
	
	@Override
	public String toString() {
		StringBuilder s = new StringBuilder();
		
		for (int i = 0; i < _list.size(); i++) {
			if (i > 0) {
				s.append(",");
			}
			
			s.append(_list.get(i));
		}
		
		return s.toString();
	}
	
	public List<T> asList() {
		return _list;
	}
	
	private DataTypeInfo _elementsTypeInfo;
	
	public DataTypeInfo getElementsTypeInfo() {
		return _elementsTypeInfo;
	}
	
	@Override
	public DataTypeInfo getTypeInfo() {
		return new DataTypeInfo(getClass(), getElementsTypeInfo());
	}
	
	@Override
	public Object toSLKVal() {
		StringBuilder s = new StringBuilder();
		
		for (T el : asList()) {
			if (s.toString() != null) s.append(",");
			
			s.append(el.toSLKVal().toString());
		}
		
		return s.toString();
	}

	@Override
	public Object toTXTVal() {
		StringBuilder s = new StringBuilder();
		
		for (T el : asList()) {
			if (s.toString() != null) s.append(",");
			
			s.append(el.toTXTVal().toString());
		}
		
		return s.toString();
	}

	public DataList(DataTypeInfo elementsTypeInfo) {
		_elementsTypeInfo = elementsTypeInfo;
	}
	
	public DataList(Class<? extends DataType> elementsType) {
		this(new DataTypeInfo(elementsType));
	}

	@Override
	public boolean add(T e) {
		return _list.add(e);
	}

	@Override
	public void add(int index, T element) {
		_list.add(index, element);
	}

	@Override
	public boolean addAll(Collection c) {
		return _list.addAll(c);
	}

	@Override
	public boolean addAll(int index, Collection c) {
		return _list.addAll(index, c);
	}

	@Override
	public void clear() {
		_list.clear();
	}

	@Override
	public boolean contains(Object o) {
		return _list.contains(o);
	}

	@Override
	public boolean containsAll(Collection c) {
		return _list.containsAll(c);
	}

	@Override
	public T get(int index) {
		return _list.get(index);
	}

	@Override
	public int indexOf(Object o) {
		return _list.indexOf(o);
	}

	@Override
	public boolean isEmpty() {
		return _list.isEmpty();
	}

	@Override
	public Iterator iterator() {
		return _list.iterator();
	}

	@Override
	public int lastIndexOf(Object o) {
		return _list.lastIndexOf(o);
	}

	@Override
	public ListIterator listIterator() {
		return _list.listIterator();
	}

	@Override
	public ListIterator listIterator(int index) {
		return _list.listIterator(index);
	}

	@Override
	public boolean remove(Object o) {
		return _list.remove(o);
	}

	@Override
	public T remove(int index) {
		return _list.remove(index);
	}

	@Override
	public boolean removeAll(Collection c) {
		return _list.removeAll(c);
	}

	@Override
	public boolean retainAll(Collection c) {
		return _list.retainAll(c);
	}

	@Override
	public T set(int index, T element) {
		return _list.set(index, element);
	}

	@Override
	public int size() {
		return _list.size();
	}

	@Override
	public List subList(int fromIndex, int toIndex) {
		return _list.subList(fromIndex, toIndex);
	}

	@Override
	public Object[] toArray() {
		return _list.toArray();
	}

	@Override
	public Object[] toArray(Object[] a) {
		return _list.toArray(a);
	}

	public DataList<T> decode(Object val, Class<? extends DataType> elementsType) {
		DataList<T> ret = new DataList<>(elementsType);
		
		for (String s : val.toString().split(",")) {
			try {
				ret.add((T) elementsType.newInstance().decode(val));
			} catch (InstantiationException | IllegalAccessException e) {
				e.printStackTrace();
			}
		}
		
		return ret;
	}

	@Override
	public DataList<T> decode(Object val) {
		return decode(val, Wc3String.class);
	}
	
	@Override
	public DataList<T> clone() {
		DataList<T> other = new DataList<>(getElementsTypeInfo());
		
		for (T val : this) {
			if (val instanceof DataList) {
				val = (T) ((DataList) val).clone();
			}
			
			other.add(val);
		}
		
		return other;
	}
}
