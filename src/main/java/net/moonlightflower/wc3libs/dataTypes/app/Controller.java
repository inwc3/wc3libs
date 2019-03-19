package net.moonlightflower.wc3libs.dataTypes.app;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.util.LinkedHashMap;
import java.util.Map;

public class Controller extends War3Int {
	private final static Map<Integer, Controller> _indexMap = new LinkedHashMap<>();
	private final static Map<String, Controller> _labelMap = new LinkedHashMap<>();
	
	public final static Controller COMPUTER = new Controller(2, "COMPUTER", "MAP_CONTROL_COMPUTER");
	public final static Controller NEUTRAL = new Controller(3, "NEUTRAL", "MAP_CONTROL_NEUTRAL");
	public final static Controller RESCUABLE = new Controller(4, "RESCUABLE", "MAP_CONTROL_RESCUABLE");
	public final static Controller USER = new Controller(1, "USER", "MAP_CONTROL_USER");

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
	private final String _jassExpr;

	public String getJassExpr() {
		return _jassExpr;
	}

	@Override
	public String toString() {
		return _label;
	}
	
	private Controller(int val, @Nonnull String label, String jassExpr) {
		super(val);
		
		_indexMap.put(val, this);
		_labelMap.put(label, this);
		
		_label = label;
		_val = val;
		_jassExpr = jassExpr;
	}

	@Nullable
	public static Controller valueOf(Integer val) {
		return _indexMap.get(val);
	}

	@Nullable
	public static Controller valueOf(String val) {
		return _labelMap.get(val);
	}
}
