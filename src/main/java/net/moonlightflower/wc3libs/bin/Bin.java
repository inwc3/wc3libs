package net.moonlightflower.wc3libs.bin;

import net.moonlightflower.wc3libs.dataTypes.DataType;

import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Bin {
	private Map<BinState<?>, DataType> _vals = new LinkedHashMap<>();
	
	public Map<BinState<?>, DataType> getVals() {
		return _vals;
	}
	
	protected DataType get(BinState<?> state) {
		return getVals().get(state);
	}
	
	protected void set(BinState<?> state, DataType val) {
		_vals.put(state, val);
	}
}
