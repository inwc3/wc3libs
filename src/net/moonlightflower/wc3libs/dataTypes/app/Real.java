package net.moonlightflower.wc3libs.dataTypes.app;

import net.moonlightflower.wc3libs.dataTypes.DataType;
import net.moonlightflower.wc3libs.dataTypes.Wc3Num;

public class Real extends DataType implements Wc3Num {
	public static String name() {
		return "Real";
	}

	public static Real getDefVal() {
		return new Real(0F);
	}
	
	private float _val;
	
	public Float getVal() {
		return _val;
	}
	
	@Override
	public String toString() {
		return getVal().toString();
	}
	
	@Override
	public float toFloat() {
		return _val;
	}
	
	public double toDouble() {
		return getVal();
	}
	
	@Override
	public Object toSLKVal() {
		return getVal();
	}

	@Override
	public Object toTXTVal() {
		return getVal();
	}

	public int intValue() {
		return getVal().intValue();
	}
	
	public Real(float val) {
		super();
		
		_val = val;
	}
	
	public static Real valueOf(float val) {
		return new Real(val);
	}

	public static Real valueOf(Object val) {
		try {
			Real ret = valueOf(Float.parseFloat(val.toString()));
			
			System.out.println("ret " + ret.getVal());
			
			return ret;
		} catch (NumberFormatException e) {
		}
		
		return null;
	}
	
	public Real() {
		
	}
	
	@Override
	public Real decode(Object val) {
		return valueOf(val);
	}
	
	public static Real decodeStatic(Object val) {
		return valueOf(val);
	}
}