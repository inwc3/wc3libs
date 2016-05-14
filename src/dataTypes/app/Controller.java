package dataTypes.app;

import java.util.HashMap;
import java.util.Map;

public class Controller extends Int {
	public final static Controller CPU = new Controller(2);
	public final static Controller HUMAN = new Controller(1);
	public final static Controller NEUTRAL = new Controller(3);
	public final static Controller RESCUABLE = new Controller(4);
	
	private static Map<Integer, Controller> _map = new HashMap<>();
	
	private Controller(int val) {
		super(val);
		
		_map.put(val, this);
	}
	
	public static Controller valueOf(int val) {
		return _map.get(val);
	}
}
