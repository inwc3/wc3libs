package bin;

import java.util.ArrayList;
import java.util.List;

public class MetaState<T> {
	private T _defVal = null;
	
	public T getDefVal() {
		return _defVal;
	}
	
	private MetaFieldId _id;
	
	public MetaFieldId getId() {
		return _id;
	}
	
	private static List<MetaState> all = new ArrayList<>();
	
	@Override
	public String toString() {
		return _id.toString();
	}
	
	public MetaState(String idString) {
		_id = MetaFieldId.valueOf(idString);
		
		all.add(this);
	}
	
	public MetaState(String idString, T defVal) {
		this(idString);
		
		_defVal = defVal;
	}
}
