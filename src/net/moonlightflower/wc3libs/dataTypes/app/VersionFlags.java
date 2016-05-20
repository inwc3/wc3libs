package net.moonlightflower.wc3libs.dataTypes.app;

public class VersionFlags extends Int {
	public final static VersionFlags NONE = new VersionFlags(0x0);
	public final static VersionFlags ROC = new VersionFlags(0x1);
	public final static VersionFlags TFT = new VersionFlags(0x2);
	
	public VersionFlags(int val) {
		super(val);
	}
}
