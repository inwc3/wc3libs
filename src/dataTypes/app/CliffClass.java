package dataTypes.app;

import dataTypes.DataType;

public class CliffClass extends Wc3String {
	public final static CliffClass CLIFF1 = new CliffClass("c1");
	public final static CliffClass CLIFF2 = new CliffClass("c2");

	private CliffClass(String name) {
		super(name);
	}

	@Override
	public CliffClass decode(Object val) {
		return new CliffClass(val.toString());
	}
}
