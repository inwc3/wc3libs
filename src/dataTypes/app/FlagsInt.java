package dataTypes.app;

public class FlagsInt {
	public static class Flag {
		int _pos;
		
		public int getPos() {
			return _pos;
		}
		
		public Flag(int pos) {
			_pos = pos;
		}
	}
	
	int _val;
	
	public int toInt() {
		return _val;
	}
	
	public void setVal(int val) {
		_val = val;
	}
	
	public boolean containsFlag(Flag flag) {
		return ((_val & (1 << flag.getPos())) != 0);
	}
	
	public void setPos(int pos, boolean val) {
		if (val) {
			setVal(toInt() | (1 << pos));
		} else {
			setVal(toInt() & ~(1 << pos));
		}
	}
	
	public void setFlag(Flag flag, boolean val) {
		int pos = flag.getPos();

		setPos(pos, val);
	}
	
	protected FlagsInt() {
		
	}
	
	protected FlagsInt(int val) {
		_val = val;
	}
	
	public static FlagsInt valueOf(int val) {
		return new FlagsInt(val);
	}
}
