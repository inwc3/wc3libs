package net.moonlightflower.wc3libs.bin;

import java.util.HashMap;
import java.util.Map;

import net.moonlightflower.wc3libs.dataTypes.DataType;

public abstract class Bin {
	private Map<BinState<?>, DataType> _vals = new HashMap<>();
	
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
