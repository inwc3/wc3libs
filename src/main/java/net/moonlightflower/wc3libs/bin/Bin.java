package net.moonlightflower.wc3libs.bin;

import net.moonlightflower.wc3libs.dataTypes.DataType;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

public abstract class Bin {
	private final Map<BinState<?>, DataType> _vals = new LinkedHashMap<>();

	@Nonnull
	public Map<BinState<?>, DataType> getVals() {
		return new LinkedHashMap<>(_vals);
	}
	
	public DataType get(@Nonnull BinState<?> state) {
		return getVals().get(state);
	}
	
	public void set(@Nonnull BinState<?> state, @Nullable DataType val) {
		_vals.put(state, val);
	}
}
