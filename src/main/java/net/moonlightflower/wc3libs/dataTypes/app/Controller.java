package net.moonlightflower.wc3libs.dataTypes.app;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

public class Controller extends War3Int {
	private final static Map<Integer, Controller> _indexMap = new LinkedHashMap<>();
	private final static Map<String, Controller> _labelMap = new LinkedHashMap<>();
	
	public final static Controller CPU = new Controller(2, "COMPUTER");
	public final static Controller HUMAN = new Controller(1, "USER");
	public final static Controller NEUTRAL = new Controller(3, "NEUTRAL");
	public final static Controller RESCUABLE = new Controller(4, "RESCUABLE");

	@Override
	public boolean equals(Object other) {
		if (other instanceof Controller)
			return equals((Controller) other);

		return super.equals(other);
	}

	public boolean equals(Controller other) {
		return getVal().equals(other.getVal());
	}

	private final int _val;
	private final String _label;
	
	@Override
	public String toString() {
		return _label;
	}
	
	private Controller(int val, @Nonnull String label) {
		super(val);
		
		_indexMap.put(val, this);
		_labelMap.put(label, this);
		
		_label = label;
		_val = val;
	}

	@Nullable
	public static Controller valueOf(Integer val) {
		return _indexMap.get(val);
	}
}
